package com.xmomen.module.receipt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.logger.Log;
import com.xmomen.module.receipt.entity.TbReceivingCodeRequest;
import com.xmomen.module.receipt.mapper.ReceivingCodeRequestMapper;

@RestController
public class ReceivingCodeRequestController {
	@Autowired
    MybatisDao mybatisDao;
	
    /**
     * 查询收货码请求
     * @param id
     * @return
     */
    @RequestMapping(value = "/receivingCodeRequest", method = RequestMethod.GET)
    @Log(actionName = "查询收货码请求")
    public Page<TbReceivingCodeRequest> getExpressList(@RequestParam(value = "limit") Integer limit,
            @RequestParam(value = "offset") Integer offset,
            @RequestParam(value = "orderNo", required = false) String orderNo,
            @RequestParam(value = "requestTimeStart", required = false) String requestTimeStart,
            @RequestParam(value = "requestTimeEnd", required = false) String requestTimeEnd,
            @RequestParam(value = "keyword", required = false) String keyword){
    	 Map map = new HashMap<String,Object>();
         map.put("orderNo", orderNo);
         map.put("keyword", keyword);
         map.put("requestTimeStart", requestTimeStart);
         map.put("requestTimeEnd", requestTimeEnd);
        return (Page<TbReceivingCodeRequest>) mybatisDao.selectPage(ReceivingCodeRequestMapper.ReceivingCodeRequestMapperNameSpace + "getReceivingCodeRequestList", map, limit, offset);
    }
    
}
