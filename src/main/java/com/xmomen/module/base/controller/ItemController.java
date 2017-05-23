package com.xmomen.module.base.controller;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.xmomen.module.base.model.*;

import com.xmomen.module.resource.entity.Resource;
import com.xmomen.module.resource.service.ResourceService;
import com.xmomen.module.resource.service.ResourceUtilsService;
import com.xmomen.module.wx.util.DateUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.base.mapper.ItemMapper;
import com.xmomen.module.base.service.ItemService;
import com.xmomen.module.logger.Log;

@RestController
public class ItemController {
    @Autowired
    ItemService itemService;
    @Autowired
    ItemMapper itemMapper;
    @Autowired
    MybatisDao mybatisDao;
    @Autowired
    ResourceService resourceService;

    /**
     * 查询产品信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/item", method = RequestMethod.GET)
    @Log(actionName = "查询产品信息")
    public Page<ItemModel> getMemberList(@RequestParam(value = "limit") Integer limit,
                                         @RequestParam(value = "offset") Integer offset,
                                         @RequestParam(value = "id", required = false) Integer id,
                                         @RequestParam(value = "companyId", required = false) Integer companyId,
                                         @RequestParam(value = "keyword", required = false) String keyword,
                                         @RequestParam(value = "sellStatus", required = false) Integer sellStatus,
                                         @RequestParam(value = "itemType", required = false) Integer itemType,
                                         @RequestParam(value = "sellUnit", required = false) String sellUnit,
                                         @RequestParam(value = "excludeStock", required = false) Integer excludeStock,
                                         @RequestParam(value = "ids", required = false) Integer[] ids,
                                         @RequestParam(value = "exclude_ids", required = false) Integer[] exclude_ids) {
        ItemQuery itemQuery = new ItemQuery();
        itemQuery.setId(id);
        itemQuery.setKeyword(keyword);
        if (companyId != null) {
            itemQuery.setCompanyId(companyId);
        }
        if (sellStatus != null) {
            itemQuery.setSellStatus(sellStatus);
        }
        if (itemType != null) {
            itemQuery.setItemType(itemType);
        }
        if (exclude_ids != null) {
            itemQuery.setExcludeIds(exclude_ids);
        }
        if (ids != null) {
            itemQuery.setIds(ids);
        }
        if (excludeStock != null) {
            itemQuery.setExcludeStock(excludeStock);
        }
        if (StringUtils.isNotBlank(sellUnit)) {
            itemQuery.setSellUnit(sellUnit);
        }
        return itemService.queryItemList(itemQuery, offset, limit);
    }

    /**
     * 新增产品
     *
     * @param createItem
     * @param bindingResult
     * @throws ArgumentValidException
     */
    @RequestMapping(value = "/item", method = RequestMethod.POST)
    @Log(actionName = "新增产品")
    public void createCompany(@RequestBody @Valid CreateItem createItem, BindingResult bindingResult) throws ArgumentValidException {
        if (bindingResult != null && bindingResult.hasErrors()) {
            throw new ArgumentValidException(bindingResult);
        }
        itemService.createItem(createItem);
    }

    /**
     * 修改
     *
     * @param id
     */
    @RequestMapping(value = "/item/{id}", method = RequestMethod.PUT)
    @Log(actionName = "修改产品")
    public void updateMember(@PathVariable(value = "id") Integer id,
                             @RequestBody @Valid UpdateItem updateItem, BindingResult bindingResult) throws ArgumentValidException {
        if (bindingResult != null && bindingResult.hasErrors()) {
            throw new ArgumentValidException(bindingResult);
        }
        itemService.updateItem(id, updateItem);
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除产品信息")
    public void deleteMember(@PathVariable(value = "id") Integer id) {
        itemService.delete(id);
    }

    /**
     * 查询子产品
     *
     * @param parentId
     * @return
     */
    @RequestMapping(value = "/getChildItem", method = RequestMethod.GET)
    public List<ItemChildModel> getChildItem(@RequestParam(value = "parentId", required = false) Integer parentId) {
        List<ItemChildModel> childItems = new ArrayList<ItemChildModel>();
        Map map = new HashMap<String, Object>();
        map.put("parentId", parentId);
        childItems = mybatisDao.getSqlSessionTemplate().selectList(ItemMapper.ItemMapperNameSpace + "getChildItemList", map);
        return childItems;
    }

    @RequestMapping(value = "/item/picture", method = RequestMethod.POST)
    @ResponseBody
    public Resource importExcel(HttpServletRequest request, HttpServletResponse response) {
        String itemId = request.getParameter("itemId");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            try {
                String imagePath = "/itemPicture/" + DateUtils.getNowDateString("yyyy-MM-dd") + "/";
                // 文件保存路径
                String filePath = request.getSession().getServletContext().getRealPath("/") + imagePath;
                File fileDir = new File(filePath);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                String imageName = UUID.randomUUID() + "." + file.getOriginalFilename().split("\\.")[1];
                //图片全地址
                String imageFullPath = filePath + imageName;
                // 转存文件
                file.transferTo(new File(imageFullPath));

                //上传到FastDFS
                String remotePath = ResourceUtilsService.uploadFile(new File(imageFullPath));
                if (StringUtils.isNotEmpty(remotePath)) {
                    //保存资源文件
                    Resource resource = new Resource();
                    resource.setEntityId(itemId);
                    resource.setEntityType("cd_item");
                    resource.setPath(remotePath);
                    resource.setIsDefault(0);
                    resource.setResourceType("PICTURE");
                    this.resourceService.createResource(resource);
                    String fullPath = ResourceUtilsService.getWholeHttpPath(resource.getPath());
                    resource.setPath(fullPath);
                    return resource;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    /**
     * 默认封面图片
     *
     * @param resourceId 资源目录id
     * @return
     */
    @RequestMapping(value = "/item/defaultImage", method = RequestMethod.GET)
    @ResponseBody
    public Boolean defaultImage(
            @RequestParam(value = "resourceId") String resourceId) {
        this.itemService.defaultImage(resourceId);
        return Boolean.TRUE;
    }

}
