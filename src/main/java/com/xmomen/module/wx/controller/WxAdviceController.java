package com.xmomen.module.wx.controller;

import com.xmomen.module.advice.model.AdviceModel;
import com.xmomen.module.advice.service.AdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 获取最新快报咨询
 *
 * @author tanxinzheng
 * @version 1.0.0
 * @date 2017-3-29 0:27:52
 */
@RestController
@RequestMapping(value = "/wx/advice")
public class WxAdviceController {

    @Autowired
    AdviceService adviceService;


    /**
     * 获取最新的快报
     */
    @RequestMapping(value = "/getLastNew", method = RequestMethod.GET)
    public List<AdviceModel> getLastNew() {
        return adviceService.getAdviceModelList();
    }
}
