package com.xmomen.module.base.service;

import com.xmomen.module.base.entity.CdMember;
import com.xmomen.module.base.model.CreateMember;
import com.xmomen.module.base.model.MemberModel;
import com.xmomen.module.base.model.UpdateMember;
import com.xmomen.module.member.model.MemberAddressModel;

public interface MemberService {
    public CdMember createMember(CreateMember createMember);

    public void updateMember(Integer id, UpdateMember updateMember);

    public void delete(Integer id);

    public CdMember bindMember(String mobile, String name, String openId, Integer memberId) throws Exception;

    public void updateMobile(Integer id, String mobile);

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return MemberModel 客户对象
     */
    public CdMember getOneMemberModel(String id);

    public CdMember findMember(CdMember query);
    
    public boolean updatePassword(Integer id, String newPassword, String oldPassword);
    /**
     * openId绑定用户
     *
     * @param openId
     * @return
     */
    public CdMember bindMember(String openId);
    
    public CdMember updateMember(CdMember updateMember);
    
    public boolean resetPassword(Integer id, String newPassword);
}
