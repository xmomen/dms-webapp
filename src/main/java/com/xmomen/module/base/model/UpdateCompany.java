package com.xmomen.module.base.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class UpdateCompany implements Serializable{
    /**
     * 单位编号
     */
    private String companyCode;

    /**
     * 单位名称
     */
	@NotBlank
	@NotNull
    private String companyName;

    /**
     * 单位地址
     */
    private String companyAddress;

    /**
     * 单位联系人
     */
    private String companyLeader;

    /**
     * 联系人电话
     */
    private String companyLeaderTel;
    private List<Integer> customerManagerIds;
	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyLeader() {
		return companyLeader;
	}

	public void setCompanyLeader(String companyLeader) {
		this.companyLeader = companyLeader;
	}

	public String getCompanyLeaderTel() {
		return companyLeaderTel;
	}

	public void setCompanyLeaderTel(String companyLeaderTel) {
		this.companyLeaderTel = companyLeaderTel;
	}

	public List<Integer> getCustomerManagerIds() {
		return customerManagerIds;
	}

	public void setCustomerManagerIds(List<Integer> customerManagerIds) {
		this.customerManagerIds = customerManagerIds;
	}
}
