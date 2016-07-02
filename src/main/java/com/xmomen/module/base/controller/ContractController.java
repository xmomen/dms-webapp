package com.xmomen.module.base.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.base.entity.CdContract;
import com.xmomen.module.base.mapper.ContractMapper;
import com.xmomen.module.base.model.ContractModel;
import com.xmomen.module.base.model.CreateContract;
import com.xmomen.module.base.model.UpdateContract;
import com.xmomen.module.base.service.ContractService;
import com.xmomen.module.logger.Log;
@RestController
public class ContractController {
	@Autowired
	ContractService contractService;
	@Autowired
	ContractMapper contractMapper;
	@Autowired
    MybatisDao mybatisDao;
    /**
     * 查询合同信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/contract", method = RequestMethod.GET)
    @Log(actionName = "查询合同信息")
    public Page<ContractModel> getMemberList(@RequestParam(value = "limit") Integer limit,
            @RequestParam(value = "offset") Integer offset,
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "keyword", required = false) String keyword){
    	 Map map = new HashMap<String,Object>();
         map.put("id", id);
         map.put("keyword", keyword);
        return (Page<ContractModel>) mybatisDao.selectPage(ContractMapper.ContractMapperNameSpace + "getContractList", map, limit, offset);
    }
    @RequestMapping(value = "/contract", method = RequestMethod.POST)
    @Log(actionName = "新增合同")
    public void createCompany(@RequestBody @Valid CreateContract createContract, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        contractService.createContract(createContract);
    }
    
    /**
     * 合同查看
     * @return
     */
    @RequestMapping(value = "/contract/{id}", method = RequestMethod.GET)
    @Log(actionName = "查询合同")
    public ContractModel getContractDetail(@PathVariable(value = "id") Integer id){
    	//查询合同
    	 Map map = new HashMap<String,Object>();
         map.put("id", id);
    	 List<ContractModel> contracts = mybatisDao.getSqlSessionTemplate().selectList(ContractMapper.ContractMapperNameSpace + "getContractListAndDetail", map);
    	 if(contracts != null && !contracts.isEmpty() && contracts.size() == 1){
             return contracts.get(0);
         }
    	 return null;
    }
    
    /**
     *  修改
     * @param id
     */
    @RequestMapping(value = "/contract/{id}", method = RequestMethod.PUT)
    @Log(actionName = "修改合同")
    public void updateMember(@PathVariable(value = "id") Integer id,
                                @RequestBody @Valid UpdateContract updateContract, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        contractService.updateContract(id, updateContract);
    }
    
    /**
     *  删除
     * @param id
     */
    @RequestMapping(value = "/contract/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除合同信息")
    public void deleteMember(@PathVariable(value = "id") Integer id){
    	contractService.delete(id);
    }
    
}
