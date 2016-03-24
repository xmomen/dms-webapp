package com.xmomen.module.schedule.controller;

import com.xmomen.module.schedule.QuartzManager;
import com.xmomen.module.schedule.controller.vo.AddQuartzJob;
import com.xmomen.module.schedule.controller.vo.ScheduleJobVo;
import com.xmomen.module.schedule.controller.vo.UpdateQuartzJob;
import com.xmomen.module.schedule.service.ScheduleJobService;
import com.xmomen.module.schedule.entity.ScheduleJob;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.support.SpringContextUtil;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import org.quartz.Job;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jeng on 2016/2/23.
 */
@RestController
public class ScheduleController {

    public static List<AddQuartzJob> data = new ArrayList<AddQuartzJob>();

    @Autowired
    ScheduleJobService scheduleJobService;

    /**
     * 新增定时任务
     * @param addQuartzJob
     */
    @RequestMapping(value = "/schedule", method = RequestMethod.POST)
    public void addQuartzJob(@RequestBody @Valid AddQuartzJob addQuartzJob, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        String triggerId = addQuartzJob.getJobTriggerId();
        if(!SpringContextUtil.getApplicationContext().containsBean(triggerId)){
            throw new IllegalArgumentException("所选模板文件错误，此模板不属于Job接口实现类");
        }
        Object obj = SpringContextUtil.getApplicationContext().getBean(triggerId);
        if(obj == null){
            throw new IllegalArgumentException("所选模板文件错误，此模板不属于Job接口实现类");
        }
        if(obj instanceof Job){
            Job job = (Job) obj;
            try {
                ScheduleJob scheduleJob = new ScheduleJob();
                scheduleJob.setJobName(addQuartzJob.getJobName());
                scheduleJob.setJobGroup(addQuartzJob.getJobGroup());
                scheduleJob.setCronExpression(addQuartzJob.getCronExpression());
                scheduleJob.setJobDescription(addQuartzJob.getDescription());
                scheduleJob.setTriggerId(addQuartzJob.getJobTriggerId());
                scheduleJob.setJobStatus(addQuartzJob.getStarted() ? Trigger.STATE_NORMAL : Trigger.STATE_PAUSED);
                scheduleJobService.addScheduleJob(scheduleJob);
                QuartzManager.addJob(addQuartzJob.getJobName(), addQuartzJob.getJobGroup(), job.getClass(), addQuartzJob.getCronExpression());
                if(Trigger.STATE_PAUSED == scheduleJob.getJobStatus()){
                    QuartzManager.pauseJob(addQuartzJob.getJobName(), addQuartzJob.getJobGroup());
                }
            } catch (SchedulerException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                throw new IllegalArgumentException("错误的cron表达式");
            }
        }else{
            throw new IllegalArgumentException("所选模板文件错误，此模板不属于Job接口实现类");
        }
    }

    /**
     * 获取定时任务列表
     * @return
     */
    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    public Page<ScheduleJobVo> getQuartzJob(@RequestParam(value = "keyword", required = false) String keyword,
                                           @RequestParam(value = "limit") Integer limit,
                                           @RequestParam(value = "offset") Integer offset){
        return scheduleJobService.queryScheduleJobs(keyword, limit, offset);
    }

    /**
     * 修改定时任务
     * @return
     */
    @RequestMapping(value = "/schedule/{id}", method = RequestMethod.PUT)
    public void updateQuartzJob(@PathVariable(value = "id") Integer id,
                                @RequestBody @Valid UpdateQuartzJob updateQuartzJob, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        scheduleJobService.updateScheduleJob(id, updateQuartzJob);
    }

    /**
     * 获取定时任务模板
     * @return
     */
    @RequestMapping(value = "/schedule/template", method = RequestMethod.GET)
    public List<Map<String, String>> getQuartzTemplate(){
        Map<String, Job> jobs = SpringContextUtil.getApplicationContext().getBeansOfType(Job.class);
        List<Map<String, String>> strings = new ArrayList<Map<String, String>>();
        for (Map.Entry<String, Job> stringJobEntry : jobs.entrySet()) {
            Map<String, String> stringMap = new HashMap<String, String>();
            stringMap.put("triggerName", stringJobEntry.getValue().getClass().getName());
            stringMap.put("triggerKey", stringJobEntry.getKey());
            strings.add(stringMap);
        }
        return strings;
    }

    /**
     * 删除定时任务
     * @return
     */
    @RequestMapping(value = "/schedule/{id}", method = RequestMethod.DELETE)
    public void deleteQuartzJob(@PathVariable(value = "id") Integer id){
        scheduleJobService.deleteScheduleJob(id);
    }

}
