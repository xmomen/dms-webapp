package com.xmomen.module.system.service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.system.entity.SysTask;
import com.xmomen.module.system.entity.SysTaskExample;
import com.xmomen.module.system.mapper.TaskMapper;
import com.xmomen.module.system.model.CreateTask;
import com.xmomen.module.system.model.TaskModel;
import com.xmomen.module.system.model.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * Created by Jeng on 16/4/2.
 */
@Service
public class TaskService {

    @Autowired
    MybatisDao mybatisDao;

    @Transactional
    public SysTask createTask(CreateTask createTask){
        SysTask sysTask = new SysTask();
        sysTask.setTaskHeadId(createTask.getTaskHeadId());
        sysTask.setExecutorId(createTask.getExecutorId());
        sysTask.setCreateTime(mybatisDao.getSysdate());
        sysTask.setTaskStatus(0);
        return mybatisDao.insertByModel(sysTask);
    }

    public Page<TaskModel> getTaskList(TaskQuery taskQuery, Integer limit, Integer offset){
        return (Page<TaskModel>) mybatisDao.selectPage(TaskMapper.TASK_MAPPER_NAMESPACE + ".", taskQuery, limit, offset);
    }

    public SysTask getTask(Integer id){
        return mybatisDao.selectByPrimaryKey(SysTask.class, id);
    }

    public void cancelTask(Integer... ids){
        SysTaskExample sysTaskExample = new SysTaskExample();
        sysTaskExample.createCriteria().andIdIn(CollectionUtils.arrayToList(ids));
        SysTask sysTask = new SysTask();
        sysTask.setTaskStatus(4);
        mybatisDao.updateByExampleSelective(sysTask, sysTaskExample);
    }

    public void updateTask(SysTask sysTask){
        mybatisDao.update(sysTask);
    }

    public void deleteTask(Integer id){
        mybatisDao.deleteByPrimaryKey(SysTask.class, id);
    }

    public void deleteTask(Integer[] ids){
        SysTaskExample sysTaskExample = new SysTaskExample();
        sysTaskExample.createCriteria().andIdIn(CollectionUtils.arrayToList(ids));
        mybatisDao.deleteByExample(sysTaskExample);
    }


}
