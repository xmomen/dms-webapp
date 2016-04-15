package com.xmomen.module.base.controller;

import java.util.HashMap;
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
import com.xmomen.module.base.mapper.ContractItemMapper;
import com.xmomen.module.base.model.ContractItemModel;
import com.xmomen.module.base.model.CreateContractItem;
import com.xmomen.module.base.model.UpdateContractItem;
import com.xmomen.module.base.service.ContractItemService;
import com.xmomen.module.logger.Log;
@RestController
public class ContractItemController {
	@Autowired
	ContractItemService contractItemService;
	@Autowired
	ContractItemMapper contractItemMapper;
	@Autowired
    MybatisDao mybatisDao;
    /**
     * 查询合同价格信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/contractItem", method = RequestMethod.GET)
    @Log(actionName = "查询合同价格信息")
    public Page<ContractItemModel> getMemberList(@RequestParam(value = "limit") Integer limit,
            @RequestParam(value = "offset") Integer offset,
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "keyword", required = false) String keyword){
    	 Map map = new HashMap<String,Object>();
         map.put("id", id);
         map.put("keyword", keyword);
        return (Page<ContractItemModel>) mybatisDao.selectPage(ContractItemMapper.ContractItemMapperNameSpace + "getContractItemList", map, limit, offset);
    }
    @RequestMapping(value = "/contractItem", method = RequestMethod.POST)
    @Log(actionName = "新增合同价格")
    public void createCompany(@RequestBody @Valid CreateContractItem createContractItem, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        contractItemService.createContractItem(createContractItem);
    }
    
    /**
     *  修改
     * @param id
     */
    @RequestMapping(value = "/contractItem/{id}", method = RequestMethod.PUT)
    @Log(actionName = "修改合同价格")
    public void updateMember(@PathVariable(value = "id") Integer id,
                                @RequestBody @Valid UpdateContractItem updateContractItem, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        contractItemService.updateContractItem(id, updateContractItem);
    }
    
    /**
     *  删除
     * @param id
     */
    @RequestMapping(value = "/contractItem/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除合同价格信息")
    public void deleteMember(@PathVariable(value = "id") Integer id){
    	contractItemService.delete(id);
    }
}
