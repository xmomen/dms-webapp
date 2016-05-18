package com.xmomen.module.order.service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.utils.DateUtils;
import com.xmomen.module.base.entity.CdCoupon;
import com.xmomen.module.base.entity.CdCouponExample;
import com.xmomen.module.base.model.ItemModel;
import com.xmomen.module.base.model.ItemQuery;
import com.xmomen.module.base.service.ItemService;
import com.xmomen.module.order.entity.*;
import com.xmomen.module.order.mapper.OrderMapper;
import com.xmomen.module.order.model.*;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.config.Ini;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Jeng on 16/4/5.
 */
@Service
public class PackingService {

    @Autowired
    MybatisDao mybatisDao;

    public Page<PackingModel> getPackingList(PackingQuery packingQuery, Integer limit, Integer offset){
        return null;
    }

    @Transactional
    public TbPacking create(CreatePacking createPacking){
        TbPackingRecordExample tbPackingRecordExample = new TbPackingRecordExample();
        //tbPackingRecordExample.createCriteria().andPackingNoEqualTo();
        return null;
    }

    @Transactional
    public void delete(Integer packingId){
        TbPackingRecordExample tbPackingRecordExample = new TbPackingRecordExample();
        tbPackingRecordExample.createCriteria().andPackingIdEqualTo(packingId);
        mybatisDao.deleteByExample(tbPackingRecordExample);
        mybatisDao.deleteByPrimaryKey(TbPacking.class, packingId);
    }
}
