package com.xmomen.module.order.controller;

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
import com.xmomen.module.order.mapper.TradeRecordMapper;
import com.xmomen.module.order.model.TradeRecordModel;

/**
 */
@RestController
public class TradeRecordController {
    @Autowired
    MybatisDao mybatisDao;

    /**
     * 查看交易记录
     * @param limit
     * @param offset
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/tradeRecord", method = RequestMethod.GET)
    @Log(actionName = " 查看交易记录")
    public Page<TradeRecordModel> getUserList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "keyword", required = false) String keyword){
    	 Map param = new HashMap();
         param.put("keyword", keyword);
        return  (Page<TradeRecordModel>) mybatisDao.selectPage(TradeRecordMapper.TradeRecordMapperNameSpace + "getTradeRecordList", param, limit, offset);
    }
}
