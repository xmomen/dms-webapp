package com.xmomen.module.member.service.impl;

import com.xmomen.module.member.entity.MemberAddress;
import com.xmomen.module.member.entity.MemberAddressExample;
import com.xmomen.module.member.mapper.MemberAddressMapperExt;
import com.xmomen.module.member.model.MemberAddressCreate;
import com.xmomen.module.member.model.MemberAddressQuery;
import com.xmomen.module.member.model.MemberAddressUpdate;
import com.xmomen.module.member.model.MemberAddressModel;
import com.xmomen.module.member.service.MemberAddressService;
import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-3-28 12:53:37
 * @version 1.0.0
 */
@Service
public class MemberAddressServiceImpl implements MemberAddressService {

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 新增客户地址
     *
     * @param memberAddressModel 新增客户地址对象参数
     * @return MemberAddressModel    客户地址领域对象
     */
    @Override
    @Transactional
    public MemberAddressModel createMemberAddress(MemberAddressModel memberAddressModel) {
        MemberAddress memberAddress = createMemberAddress(memberAddressModel.getEntity());
        if(memberAddress != null){
            return getOneMemberAddressModel(memberAddress.getId());
        }
        return null;
    }

    /**
     * 新增客户地址实体对象
     *
     * @param memberAddress 新增客户地址实体对象参数
     * @return MemberAddress 客户地址实体对象
     */
    @Override
    @Transactional
    public MemberAddress createMemberAddress(MemberAddress memberAddress) {
        return mybatisDao.insertByModel(memberAddress);
    }

    /**
    * 批量新增客户地址
    *
    * @param memberAddressModels 新增客户地址对象集合参数
    * @return List<MemberAddressModel>    客户地址领域对象集合
    */
    @Override
    @Transactional
    public List<MemberAddressModel> createMemberAddresss(List<MemberAddressModel> memberAddressModels) {
        List<MemberAddressModel> memberAddressModelList = null;
        for (MemberAddressModel memberAddressModel : memberAddressModels) {
            memberAddressModel = createMemberAddress(memberAddressModel);
            if(memberAddressModel != null){
                if(memberAddressModelList == null){
                    memberAddressModelList = new ArrayList<>();
                }
                memberAddressModelList.add(memberAddressModel);
            }
        }
        return memberAddressModelList;
    }

    /**
     * 更新客户地址
     *
     * @param memberAddressModel 更新客户地址对象参数
     */
    @Override
    @Transactional
    public void updateMemberAddress(MemberAddressModel memberAddressModel) {
        mybatisDao.update(memberAddressModel.getEntity());
    }

    /**
     * 更新客户地址实体对象
     *
     * @param memberAddress 新增客户地址实体对象参数
     * @return MemberAddress 客户地址实体对象
     */
    @Override
    @Transactional
    public void updateMemberAddress(MemberAddress memberAddress) {
        mybatisDao.update(memberAddress);
    }

    /**
     * 删除客户地址
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteMemberAddress(String[] ids) {
        MemberAddressExample memberAddressExample = new MemberAddressExample();
        memberAddressExample.createCriteria().andIdIn(Arrays.<String>asList((String[]) ids));
        mybatisDao.deleteByExample(memberAddressExample);
    }

    /**
    * 删除客户地址
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deleteMemberAddress(String id) {
        mybatisDao.deleteByPrimaryKey(MemberAddress.class, id);
    }

    /**
     * 查询客户地址领域分页对象（带参数条件）
     *
     * @param limit     每页最大数
     * @param offset    页码
     * @param memberAddressQuery 查询参数
     * @return Page<MemberAddressModel>   客户地址参数对象
     */
    @Override
    public Page<MemberAddressModel> getMemberAddressModelPage(int limit, int offset, MemberAddressQuery memberAddressQuery) {
        return (Page<MemberAddressModel>) mybatisDao.selectPage(MemberAddressMapperExt.MemberAddressMapperNameSpace + "getMemberAddressModel", memberAddressQuery, limit, offset);
    }

    /**
     * 查询客户地址领域分页对象（无参数条件）
     *
     * @param limit  每页最大数
     * @param offset 页码
     * @return Page<MemberAddressModel> 客户地址领域对象
     */
    @Override
    public Page<MemberAddressModel> getMemberAddressModelPage(int limit, int offset) {
        return (Page<MemberAddressModel>) mybatisDao.selectPage(MemberAddressMapperExt.MemberAddressMapperNameSpace + "getMemberAddressModel", null, limit, offset);
    }

    /**
     * 查询客户地址领域集合对象（带参数条件）
     *
     * @param memberAddressQuery 查询参数对象
     * @return List<MemberAddressModel> 客户地址领域集合对象
     */
    @Override
    public List<MemberAddressModel> getMemberAddressModelList(MemberAddressQuery memberAddressQuery) {
        return mybatisDao.getSqlSessionTemplate().selectList(MemberAddressMapperExt.MemberAddressMapperNameSpace + "getMemberAddressModel", memberAddressQuery);
    }

    /**
     * 查询客户地址领域集合对象（无参数条件）
     *
     * @return List<MemberAddressModel> 客户地址领域集合对象
     */
    @Override
    public List<MemberAddressModel> getMemberAddressModelList() {
        return mybatisDao.getSqlSessionTemplate().selectList(MemberAddressMapperExt.MemberAddressMapperNameSpace + "getMemberAddressModel");
    }

    /**
     * 查询客户地址实体对象
     *
     * @param id 主键
     * @return MemberAddress 客户地址实体对象
     */
    @Override
    public MemberAddress getOneMemberAddress(String id) {
        return mybatisDao.selectByPrimaryKey(MemberAddress.class, id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return MemberAddressModel 客户地址领域对象
     */
    @Override
    public MemberAddressModel getOneMemberAddressModel(String id) {
        MemberAddressQuery memberAddressQuery = new MemberAddressQuery();
        memberAddressQuery.setId(id);
        return mybatisDao.getSqlSessionTemplate().selectOne(MemberAddressMapperExt.MemberAddressMapperNameSpace + "getMemberAddressModel", memberAddressQuery);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param memberAddressQuery 客户地址查询参数对象
     * @return MemberAddressModel 客户地址领域对象
     */
    @Override
    public MemberAddressModel getOneMemberAddressModel(MemberAddressQuery memberAddressQuery) throws TooManyResultsException {
        return mybatisDao.getSqlSessionTemplate().selectOne(MemberAddressMapperExt.MemberAddressMapperNameSpace + "getMemberAddressModel", memberAddressQuery);
    }
}
