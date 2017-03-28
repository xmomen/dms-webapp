package com.xmomen.module.base.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.base.model.*;

import java.util.List;

public interface ItemDetailService {

    public List<ItemDetailModel> queryItemDetailList(ItemDetailQuery itemDetailQuery);

    public Page<ItemDetailModel> queryItemDetailList(ItemDetailQuery itemDetailQuery, Integer offset, Integer limit);

    public void createItemDetail(CreateItemDetail createItemDetail);

    public void updateItemDetail(Integer id, UpdateItemDetail updateItemDetail);

    public void delete(Integer id);

}	
