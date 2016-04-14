package com.xmomen.module.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.base.entity.CdContract;
import com.xmomen.module.base.entity.CdContractItem;
import com.xmomen.module.base.entity.CdItem;
import com.xmomen.module.base.model.CreateContract;
import com.xmomen.module.base.model.CreateContractItem;
import com.xmomen.module.base.model.UpdateContract;
import com.xmomen.module.base.service.ContractService;
@Service
public class ContractServiceImpl implements ContractService {
	@Autowired
	MybatisDao mybatisDao;
	@Override
	@Transactional
	public void createContract(CreateContract createContract) {
		CdContract contract = new CdContract();
		contract.setBeginTime(createContract.getBeginTime());
		contract.setEndTime(createContract.getEndTime());
		contract.setCdCompanyId(createContract.getCdCompanyId());
		contract.setContractCode(createContract.getContractCode());
		contract.setContractName(createContract.getContractName());
		contract.setCdMemberId(createContract.getCdMemberId());
		contract.setIsAuditor(0);
		mybatisDao.save(contract);
	}
	@Transactional
	public void updateContract(Integer id, UpdateContract updateContract) {
		CdContract contract = new CdContract();
		contract.setId(id);
		contract.setBeginTime(updateContract.getBeginTime());
		contract.setEndTime(updateContract.getEndTime());
		contract.setCdCompanyId(updateContract.getCdCompanyId());
		contract.setContractCode(updateContract.getContractCode());
		contract.setContractName(updateContract.getContractName());
		contract.setCdMemberId(updateContract.getCdMemberId());
		mybatisDao.update(contract);
	}
	@Transactional
	public void delete(Integer id) {
		 mybatisDao.deleteByPrimaryKey(CdContract.class, id);		
	}

}
