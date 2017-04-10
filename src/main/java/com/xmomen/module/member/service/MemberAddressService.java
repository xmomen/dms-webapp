package com.xmomen.module.member.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.member.model.MemberAddressQuery;
import com.xmomen.module.member.model.MemberAddressModel;
import com.xmomen.module.member.entity.MemberAddress;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.io.Serializable;
import java.util.List;

/**
 * @author tanxinzheng
 * @version 1.0.0
 * @date 2017-3-29 0:27:52
 */
public interface MemberAddressService {

    /**
     * 新增客户地址
     *
     * @param memberAddressModel 新增客户地址对象参数
     * @return MemberAddressModel    客户地址领域对象
     */
    public MemberAddressModel createMemberAddress(MemberAddressModel memberAddressModel);

    /**
     * 新增客户地址实体对象
     *
     * @param memberAddress 新增客户地址实体对象参数
     * @return MemberAddress 客户地址实体对象
     */
    public MemberAddress createMemberAddress(MemberAddress memberAddress);

    /**
     * 批量新增客户地址
     *
     * @param MemberAddressModel 新增客户地址对象集合参数
     * @return List<MemberAddressModel>    客户地址领域对象集合
     */
    List<MemberAddressModel> createMemberAddresss(List<MemberAddressModel> memberAddressModels);

    /**
     * 更新客户地址
     *
     * @param memberAddressModel 更新客户地址对象参数
     */
    public void updateMemberAddress(MemberAddressModel memberAddressModel);

    /**
     * 更新客户地址实体对象
     *
     * @param memberAddress 新增客户地址实体对象参数
     * @return MemberAddress 客户地址实体对象
     */
    public void updateMemberAddress(MemberAddress memberAddress);

    /**
     * 批量删除客户地址
     *
     * @param ids 主键数组
     */
    public void deleteMemberAddress(String[] ids);

    /**
     * 删除客户地址
     *
     * @param id 主键
     */
    public void deleteMemberAddress(String id);

    /**
     * 查询客户地址领域分页对象（带参数条件）
     *
     * @param memberAddressQuery 查询参数
     * @param limit              每页最大数
     * @param offset             页码
     * @return Page<MemberAddressModel>   客户地址参数对象
     */
    public Page<MemberAddressModel> getMemberAddressModelPage(int limit, int offset, MemberAddressQuery memberAddressQuery);

    /**
     * 查询客户地址领域分页对象（无参数条件）
     *
     * @param limit  每页最大数
     * @param offset 页码
     * @return Page<MemberAddressModel> 客户地址领域对象
     */
    public Page<MemberAddressModel> getMemberAddressModelPage(int limit, int offset);

    /**
     * 查询客户地址领域集合对象（带参数条件）
     *
     * @param memberAddressQuery 查询参数对象
     * @return List<MemberAddressModel> 客户地址领域集合对象
     */
    public List<MemberAddressModel> getMemberAddressModelList(MemberAddressQuery memberAddressQuery);

    /**
     * 查询客户地址领域集合对象（无参数条件）
     *
     * @return List<MemberAddressModel> 客户地址领域集合对象
     */
    public List<MemberAddressModel> getMemberAddressModelList();

    /**
     * 查询客户地址实体对象
     *
     * @param id 主键
     * @return MemberAddress 客户地址实体对象
     */
    public MemberAddress getOneMemberAddress(String id);

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return MemberAddressModel 客户地址领域对象
     */
    public MemberAddressModel getOneMemberAddressModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param memberAddressQuery 客户地址查询参数对象
     * @return MemberAddressModel 客户地址领域对象
     */
    public MemberAddressModel getOneMemberAddressModel(MemberAddressQuery memberAddressQuery) throws TooManyResultsException;

    /**
     * 设置默认收货地址
     *
     * @param addressId
     */
    public void defaultAddress(String addressId);

    public List<MemberAddressModel> getMemberAddressModels(MemberAddressQuery memberAddressQuery);
}
