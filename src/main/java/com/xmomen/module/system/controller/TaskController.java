package com.xmomen.module.system.controller;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.logger.Log;
import com.xmomen.module.system.entity.SysTask;
import com.xmomen.module.system.model.CreateTask;
import com.xmomen.module.system.model.TaskModel;
import com.xmomen.module.system.model.TaskQuery;
import com.xmomen.module.system.service.TaskService;
import com.xmomen.module.system.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Jeng on 16/3/25.
 */
@RestController
public class TaskController {

    @Autowired
    TaskService taskService;

    /**
     * 查询任务信息
     * @param limit
     * @param offset
     * @param taskStatus
     * @param executorIds
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/task", method = RequestMethod.GET)
    @Log(actionName = "查询任务信息")
    public Page<TaskModel> getTaskTree(@RequestParam(value = "limit") Integer limit,
                                       @RequestParam(value = "offset") Integer offset,
                                       @RequestParam(value = "taskStatus", required = false) Integer[] taskStatus,
                                       @RequestParam(value = "executorIds", required = false) Integer[] executorIds,
                                       @RequestParam(value = "keyword", required = false) String keyword){
        TaskQuery taskQuery = new TaskQuery();
        taskQuery.setKeyword(keyword);
        taskQuery.setTaskStatus(taskStatus);
        taskQuery.setExecutorIds(executorIds);
        return taskService.getTaskList(taskQuery, limit, offset);
    }


    /**
     *  删除机构组织
     * @param id
     */
    @RequestMapping(value = "/task/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除任务")
    public void deleteTask(@PathVariable(value = "id") Integer id){
        taskService.deleteTask(id);
    }

    /**
     *  修改机构组织
     * @param id
     */
    @RequestMapping(value = "/task/{id}", method = RequestMethod.PUT)
    @Log(actionName = "修改任务")
    public void updateTask(@PathVariable(value = "id") Integer id,
                                @RequestBody @Valid CreateTask createTask, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        SysTask sysTask = new SysTask();
        sysTask.setId(id);
        taskService.updateTask(sysTask);
    }

}
