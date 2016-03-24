package com.xmomen.module.system.service;


import com.xmomen.module.system.mapper.LogMapper;
import com.xmomen.module.system.model.LogModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jeng on 16/3/20.
 */
@Service
public class LogService {

    @Autowired
    LogMapper logMapper;

    @Transactional
    public void insertLog(LogModel logModel){
        logMapper.insertLog(logModel);
    }
}
