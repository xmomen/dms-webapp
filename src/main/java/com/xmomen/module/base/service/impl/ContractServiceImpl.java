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
		contract.setIsAuditor(1);
		contract.setIsDel(0);
		contract = mybatisDao.saveByModel(contract);
		//保存合同明细
		for(CreateContractItem createContractItem : createContract.getContractItemList()){
			CdContractItem contractItem = new CdContractItem();
			contractItem.setCdItemId(createContractItem.getCdItemId());
			contractItem.setCdContractId(contract.getId());
			contractItem.setContractType(createContractItem.getContractType());
			contractItem.setDiscount(createContractItem.getDiscount());
			contractItem.setContractValue(createContractItem.getContractValue());
			mybatisDao.save(contractItem);
		}
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
		
		CdContractItem contractItemdb = new CdContractItem();
		contractItemdb.setCdContractId(id);
		List<CdContractItem> contractItemdbs = mybatisDao.selectByModel(contractItemdb);
		mybatisDao.deleteAllByModel(contractItemdbs);
		//保存合同明细
		for(CreateContractItem createContractItem : updateContract.getContractItemList()){
			CdContractItem contractItem = new CdContractItem();
			contractItem.setCdItemId(createContractItem.getCdItemId());
			contractItem.setCdContractId(contract.getId());
			contractItem.setContractType(createContractItem.getContractType());
			contractItem.setDiscount(createContractItem.getDiscount());
			contractItem.setContractValue(createContractItem.getContractValue());
			mybatisDao.save(contractItem);
		}
				
	}
	@Transactional
	public void delete(Integer id) {
		CdContract contract = mybatisDao.selectByPrimaryKey(CdContract.class, id);
		contract.setIsDel(1);
		mybatisDao.update(contract);
	}

}
