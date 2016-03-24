package com.xmomen.module.schedule.service;

import com.xmomen.module.schedule.QuartzManager;
import com.xmomen.module.schedule.controller.vo.ScheduleJobVo;
import com.xmomen.module.schedule.controller.vo.UpdateQuartzJob;
import com.xmomen.module.schedule.entity.ScheduleJob;
import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.support.SpringContextUtil;
import com.xmomen.module.schedule.mapper.ScheduleMapper;
import org.apache.commons.lang.StringUtils;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Jeng on 2016/2/23.
 */
@Service
public class ScheduleJobService {

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 添加计划任务
     * @param scheduleJob
     */
    @Transactional
    public void addScheduleJob(ScheduleJob scheduleJob){
        mybatisDao.insert(scheduleJob);
    }

    public void initScheduleJobs(){
        ScheduleJob scheduleJob = new ScheduleJob();
        //scheduleJob.setJobStatus(0);
        List<ScheduleJob> scheduleJobList = mybatisDao.selectByModel(scheduleJob);
        for (ScheduleJob job : scheduleJobList) {
            Object obj = SpringContextUtil.getBean(job.getTriggerId());
            if(obj instanceof Job){
                Job trigger = (Job) obj;
                try {
                    if(Trigger.STATE_NORMAL == job.getJobStatus()){
                        QuartzManager.addJob(job.getJobName(), job.getJobGroup(), trigger.getClass(), job.getCronExpression());
                    }else if(Trigger.STATE_PAUSED == job.getJobStatus()){
                        QuartzManager.addJob(job.getJobName(), job.getJobGroup(), trigger.getClass(), job.getCronExpression());
                        QuartzManager.pauseJob(job.getJobName(), job.getJobGroup());
                    }
                } catch (SchedulerException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 查询计划任务
     * @param keyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    public Page<ScheduleJobVo> queryScheduleJobs(String keyword, Integer pageSize, Integer pageNum){
        String string = StringUtils.trimToEmpty(keyword);
        return (Page<ScheduleJobVo>) mybatisDao.selectPage(ScheduleMapper.ScheduleMapperNameSpace + "queryScheduleJobs", string, pageSize, pageNum);
    }

    /**
     * 更新定时脚本
     * @param scheduleId
     * @param updateQuartzJob
     */
    @Transactional
    public void updateScheduleJob(Integer scheduleId, UpdateQuartzJob updateQuartzJob){
        if(scheduleId == null){
            throw new IllegalArgumentException("scheduleId must be not null");
        }
        try {
            ScheduleJob oldSchedule = mybatisDao.selectByPrimaryKey(ScheduleJob.class, scheduleId);
            String jobName = oldSchedule.getJobName();
            String jobGroup = oldSchedule.getJobGroup();
            String triggerId = oldSchedule.getTriggerId();
            String cronExpression = oldSchedule.getCronExpression();
            ScheduleJob scheduleJob = new ScheduleJob();
            scheduleJob.setJobName(updateQuartzJob.getJobName());
            scheduleJob.setJobGroup(updateQuartzJob.getJobGroup());
            scheduleJob.setTriggerId(updateQuartzJob.getJobTriggerId());
            scheduleJob.setJobDescription(updateQuartzJob.getDescription());
            scheduleJob.setCronExpression(updateQuartzJob.getCronExpression());
            scheduleJob.setId(scheduleId);
            if((StringUtils.isNotBlank(updateQuartzJob.getJobName()) && !jobName.equals(updateQuartzJob.getJobName()))
                    || (StringUtils.isNotBlank(updateQuartzJob.getJobGroup()) && !jobGroup.equals(updateQuartzJob.getJobGroup()))
                    || (StringUtils.isNotBlank(updateQuartzJob.getJobTriggerId()) && !triggerId.equals(updateQuartzJob.getJobTriggerId())
                    || (StringUtils.isNotBlank(updateQuartzJob.getCronExpression()) && !cronExpression.equals(updateQuartzJob.getJobTriggerId())))){
                QuartzManager.removeJob(jobName, jobGroup);
                Job job = (Job) SpringContextUtil.getApplicationContext().getBean(updateQuartzJob.getJobTriggerId());
                jobName = updateQuartzJob.getJobName();
                jobGroup = updateQuartzJob.getJobGroup();
                QuartzManager.addJob(jobName, jobGroup, job.getClass(), updateQuartzJob.getCronExpression());
                if(Trigger.STATE_NORMAL != oldSchedule.getJobStatus()){
                    QuartzManager.pauseJob(jobName, jobGroup);
                }
            }
            QuartzManager.getScheduler().getTriggerState(jobName, jobGroup);
            if(UpdateQuartzJob.ACTION_START.equalsIgnoreCase(updateQuartzJob.getAction())){
                scheduleJob.setJobStatus(Trigger.STATE_NORMAL);
                QuartzManager.resumeJob(jobName, jobGroup);
            }else if(UpdateQuartzJob.ACTION_RESTART.equalsIgnoreCase(updateQuartzJob.getAction())){
                scheduleJob.setJobStatus(Trigger.STATE_NORMAL);
                QuartzManager.resumeJob(jobName, jobGroup);
            }else if(UpdateQuartzJob.ACTION_PAUSE.equalsIgnoreCase(updateQuartzJob.getAction())){
                scheduleJob.setJobStatus(Trigger.STATE_PAUSED);
                QuartzManager.pauseJob(jobName, jobGroup);
            }else if(UpdateQuartzJob.ACTION_STOP.equalsIgnoreCase(updateQuartzJob.getAction())){
                scheduleJob.setJobStatus(Trigger.STATE_NONE);
                QuartzManager.unscheduleJob(jobName, jobGroup);
            }
            mybatisDao.updateByModel(scheduleJob);
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 删除定时任务
     * @param scheduleId
     */
    public void deleteScheduleJob(Integer scheduleId){
        ScheduleJob scheduleJob = mybatisDao.selectByPrimaryKey(ScheduleJob.class, scheduleId);
        if(scheduleJob != null){
            try {
                String jobName = scheduleJob.getJobName();
                String jobGroup = scheduleJob.getJobGroup();
                JobDetail jobDetail = QuartzManager.getJobDetail(jobName, jobGroup);
                if(jobDetail == null){
                    return;
                }
                if(QuartzManager.isRunning(jobName, jobGroup)){
                    throw new IllegalArgumentException("此任务正在运行，请关闭任务再重新操作！");
                }
            } catch (SchedulerException e) {
                e.printStackTrace();
                throw new IllegalArgumentException(e);
            }
            mybatisDao.deleteByPrimaryKey(ScheduleJob.class, scheduleId);
        }
    }
}
