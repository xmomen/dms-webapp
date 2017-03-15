package com.xmomen.module.base.controller;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.utils.AssertExt;
import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.base.entity.*;
import com.xmomen.module.base.mapper.CouponMapper;
import com.xmomen.module.base.model.*;
import com.xmomen.module.base.service.CouponService;
import com.xmomen.module.export.model.UploadFileVo;
import com.xmomen.module.export.util.PrintUtils;
import com.xmomen.module.logger.Log;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by Jeng on 2016/3/30.
 */
@RestController
public class CouponController {

    @Autowired
    CouponService couponService;

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 卡券列表
     *
     * @param limit
     * @param offset
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/coupon", method = RequestMethod.GET)
    @Log(actionName = "查询卡券列表")
    public Page<CouponModel> getCouponList(@RequestParam(value = "limit") Integer limit,
                                           @RequestParam(value = "offset") Integer offset,
                                           @RequestParam(value = "couponNumber", required = false) String couponNumber,
                                           @RequestParam(value = "couponType", required = false) Integer couponType,
                                           @RequestParam(value = "couponCategoryId", required = false) Integer couponCategoryId,
                                           @RequestParam(value = "isSend", required = false) Integer isSend,
                                           @RequestParam(value = "cdCompanyId", required = false) Integer cdCompanyId,
                                           @RequestParam(value = "customerMangerId", required = false) Integer customerMangerId,
                                           @RequestParam(value = "isUseful", required = false) Integer isUseful,
                                           @RequestParam(value = "auditDateStart", required = false) String auditDateStart,
                                           @RequestParam(value = "auditDateEnd", required = false) String auditDateEnd,
                                           @RequestParam(value = "isOver", required = false) Integer isOver,
                                           @RequestParam(value = "batch", required = false) String batch,
                                           @RequestParam(value = "keyword", required = false) String keyword) {
        CouponQuery couponQuery = new CouponQuery();
        couponQuery.setKeyword(keyword);
        couponQuery.setCdCompanyId(cdCompanyId);
        couponQuery.setCouponCategoryId(couponCategoryId);
        couponQuery.setCouponNumber(couponNumber);
        couponQuery.setCouponType(couponType);
        couponQuery.setCustomerMangerId(customerMangerId);
        couponQuery.setIsOver(isOver);
        couponQuery.setIsSend(isSend);
        couponQuery.setIsUseful(isUseful);

        if (StringUtilsExt.isNotBlank(auditDateStart)) {
            couponQuery.setAuditDateStart(auditDateStart.substring(0, 10));
        }

        if (StringUtilsExt.isNotBlank(auditDateEnd)) {
            couponQuery.setAuditDateEnd(auditDateEnd.substring(0, 10));
        }

        if (!StringUtils.isBlank(batch)) {
            couponQuery.setBatch(batch);
        }
        //客服经理过滤 如果有客服组权限则不过滤
        if (SecurityUtils.getSubject().hasRole(AppConstants.CUSTOMER_MANAGER_PERMISSION_CODE) && !SecurityUtils.getSubject().hasRole(AppConstants.CUSTOMER_PERMISSION_CODE)) {
            Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
            couponQuery.setManagerId(userId);
        }
        return couponService.queryCoupon(couponQuery, limit, offset);
    }

    /**
     * 卡券列表
     *
     * @param id
     */
    @RequestMapping(value = "/coupon/{id}", method = RequestMethod.GET)
    @Log(actionName = "查询卡券")
    public CdCoupon getUserList(@PathVariable(value = "id") Integer id) {
        return couponService.getCoupon(id);
    }

    /**
     * 新增卡券
     *
     * @param createCoupon
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/coupon", method = RequestMethod.POST)
    @Log(actionName = "新增卡券")
    public CdCoupon createCoupon(@RequestBody @Valid CreateCoupon createCoupon, BindingResult bindingResult) throws ArgumentValidException {
        if (bindingResult != null && bindingResult.hasErrors()) {
            throw new ArgumentValidException(bindingResult);
        }
        CdCoupon cdCoupon = new CdCoupon();
        cdCoupon.setCouponType(createCoupon.getCouponType());
        cdCoupon.setCouponCategory(createCoupon.getCouponCategory());
        cdCoupon.setCouponDesc(createCoupon.getCouponDesc());
        cdCoupon.setCouponNumber(createCoupon.getCouponNumber());
        cdCoupon.setCouponPassword(createCoupon.getCouponPassword());
        cdCoupon.setBeginTime(createCoupon.getBeginTime());
        cdCoupon.setEndTime(createCoupon.getEndTime());
        cdCoupon.setCouponValue(createCoupon.getCouponValue());
        cdCoupon.setIsGift(createCoupon.getIsGift());
        cdCoupon.setIsUsed(createCoupon.getIsUsed());
        cdCoupon.setIsUseful(createCoupon.getIsUseful());
        cdCoupon.setNotes(createCoupon.getNotes());
        cdCoupon.setPaymentType(createCoupon.getPaymentType());
        cdCoupon.setUserPrice(createCoupon.getUserPrice());
        return couponService.createCoupon(cdCoupon);
    }

    /**
     * 更新卡券
     *
     * @param id
     * @param updateCoupon
     * @param bindingResult
     * @throws ArgumentValidException
     */
    @RequestMapping(value = "/coupon/{id}", method = RequestMethod.PUT)
    @Log(actionName = "更新卡券")
    public void updateCoupon(@PathVariable(value = "id") Integer id,
                             @RequestBody @Valid UpdateCoupon updateCoupon, BindingResult bindingResult) throws ArgumentValidException {
        if (bindingResult != null && bindingResult.hasErrors()) {
            throw new ArgumentValidException(bindingResult);
        }
        CdCoupon cdCoupon = new CdCoupon();
        cdCoupon.setId(id);
        cdCoupon.setCouponCategory(updateCoupon.getCouponCategory());
        cdCoupon.setCouponType(updateCoupon.getCouponType());
        cdCoupon.setCouponDesc(updateCoupon.getCouponDesc());
        cdCoupon.setCouponNumber(updateCoupon.getCouponNumber());
        cdCoupon.setCouponPassword(updateCoupon.getCouponPassword());
        cdCoupon.setBeginTime(updateCoupon.getBeginTime());
        cdCoupon.setEndTime(updateCoupon.getEndTime());
        cdCoupon.setCouponValue(updateCoupon.getCouponValue());
        cdCoupon.setIsGift(updateCoupon.getIsGift());
        cdCoupon.setIsUsed(updateCoupon.getIsUsed());
        cdCoupon.setUserPrice(updateCoupon.getUserPrice());
        cdCoupon.setIsUseful(updateCoupon.getIsUseful());
        cdCoupon.setNotes(updateCoupon.getNotes());
        cdCoupon.setPaymentType(updateCoupon.getPaymentType());
        couponService.updateCoupon(cdCoupon);
    }

    /**
     * 删除卡券
     *
     * @param id
     */
    @RequestMapping(value = "/coupon/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除卡券")
    public void deleteCoupon(@PathVariable(value = "id") Long id) {
        mybatisDao.deleteByPrimaryKey(CdCoupon.class, id);
    }

    /**
     * @param id
     */
    @RequestMapping(value = "/coupon/sendOneCoupon", method = RequestMethod.GET)
    @Log(actionName = "发放单卡")
    public void sendOneCoupon(
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "companyId") Integer companyId,
            @RequestParam(value = "customerMangerId") Integer customerMangerId,
            @RequestParam(value = "couponNumber") String couponNumber,
            @RequestParam(value = "batch") String batch,
            @RequestParam(value = "isGift") Integer isGift) {
        couponService.sendOneCoupon(id, companyId, customerMangerId, couponNumber, batch, isGift);
    }

    /**
     * @param id
     */
    @RequestMapping(value = "/coupon/sendMoreCoupon", method = RequestMethod.GET)
    @Log(actionName = "批量发放卡")
    public void sendMoreCoupon(
            @RequestParam(value = "companyId") Integer companyId,
            @RequestParam(value = "customerMangerId") Integer customerMangerId,
            @RequestParam(value = "couponNumberList") String couponNumberList,
            @RequestParam(value = "batch") String batch,
            @RequestParam(value = "isGift") Integer isGift) {
        String[] couponNumbers = couponNumberList.split(",");
        for (int i = 0, length = couponNumbers.length; i < length; i++) {
            String couponNumber = couponNumbers[i];
            CdCoupon coupon = new CdCoupon();
            coupon.setCouponNumber(couponNumber);
//    		coupon.setCouponType(1);
            coupon.setIsSend(0);
            coupon.setIsUseful(0);
            coupon = mybatisDao.selectOneByModel(coupon);
            if (coupon != null) {
                couponService.sendOneCoupon(coupon.getId(), companyId, customerMangerId, coupon.getCouponNumber(), batch, isGift);
            }
        }
    }

    /**
     * 根据批次号修改
     */
    @RequestMapping(value = "/coupon/updateBatchCoupon", method = RequestMethod.GET)
    @Log(actionName = "根据批次号修改")
    public void updateBatchCoupon(
            @RequestParam(value = "companyId") Integer companyId,
            @RequestParam(value = "customerMangerId") Integer customerMangerId,
            @RequestParam(value = "couponNumberList") String couponNumberList) {
        String[] couponNumbers = couponNumberList.split(",");
        for (int i = 0, length = couponNumbers.length; i < length; i++) {
            String couponNumber = couponNumbers[i];
            CdCoupon coupon = new CdCoupon();
            coupon.setCouponNumber(couponNumber);
            coupon = mybatisDao.selectOneByModel(coupon);
            coupon.setCdCompanyId(companyId);
            coupon.setCdUserId(customerMangerId);
            mybatisDao.updateByModel(coupon);
        }
    }

    /**
     * 批量修改卡类型
     */
    @RequestMapping(value = "/coupon/updateBatchCouponType", method = RequestMethod.GET)
    @Log(actionName = "批量修改卡类型")
    public void updateBatchCouponType(
            @RequestParam(value = "couponCategoryId") Integer couponCategoryId,
            @RequestParam(value = "couponNumberList") String couponNumberList) {
        String[] couponNumbers = couponNumberList.split(",");
        for (int i = 0, length = couponNumbers.length; i < length; i++) {
            String couponNumber = couponNumbers[i];
            CdCoupon coupon = new CdCoupon();
            coupon.setCouponNumber(couponNumber);
            coupon = mybatisDao.selectOneByModel(coupon);
            coupon.setCouponCategory(couponCategoryId);
            mybatisDao.updateByModel(coupon);
        }
    }

    /**
     * @param id
     * @throws ParseException
     */
    @RequestMapping(value = "/coupon/activityAddress", method = RequestMethod.POST)
    @Log(actionName = "活动送货地址信息")
    public void activityAddress(@RequestBody CouponActivityAddressHead couponActivityAddressHead) throws ParseException {
        CdActivityAddress activityAddress = new CdActivityAddress();
        activityAddress.setCouponNumber(couponActivityAddressHead.getCouponNumber());
        List<CdActivityAddress> activityAddressList = mybatisDao.selectByModel(activityAddress);
        mybatisDao.deleteAllByModel(activityAddressList);
        for (CouponActivityAddress couponActivityAddress : couponActivityAddressHead.getCouponActivityAddressList()) {

            activityAddress = new CdActivityAddress();
            activityAddress.setConsignmentAddress(couponActivityAddress.getConsignmentAddress());
            activityAddress.setConsignmentPhone(couponActivityAddress.getConsignmentPhone());
            activityAddress.setConsignmentName(couponActivityAddress.getConsignmentName());
            activityAddress.setCouponNumber(couponActivityAddressHead.getCouponNumber());
            activityAddress.setSendTime(couponActivityAddress.getSendTime());
            activityAddress.setSendCount(couponActivityAddress.getSendCount());
            mybatisDao.save(activityAddress);

            //查找客户 进行添加或者修改第三个地址
            if (StringUtilsExt.isNotBlank(couponActivityAddress.getConsignmentPhone())) {
                CdMember member = new CdMember();
                member.setPhoneNumber(couponActivityAddress.getConsignmentPhone());
                List<CdMember> members = mybatisDao.selectByModel(member);
                if (members != null && members.size() > 0) {
                    member = members.get(0);
                    if (StringUtilsExt.isNotBlank(couponActivityAddress.getConsignmentAddress()))
                        member.setSpareAddress2(couponActivityAddress.getConsignmentAddress());
                    if (StringUtilsExt.isNotBlank(couponActivityAddress.getConsignmentName()))
                        member.setSpareName2(couponActivityAddress.getConsignmentName());
                    member.setSpareTel2(couponActivityAddress.getConsignmentPhone());
                    mybatisDao.update(member);
                }
            }
        }
    }

    /**
     * 审核金额
     *
     * @param id
     */
    @RequestMapping(value = "/coupon/{id}/audit", method = RequestMethod.PUT)
    @Log(actionName = "审核金额")
    public void audit(@PathVariable(value = "id") Integer id,
                      @RequestParam(value = "locked") Boolean locked) {
        this.couponService.auditCoupon(id, locked);
    }

    /**
     * 批量审核金额
     *
     * @param id
     */
    @RequestMapping(value = "/coupon/batchAudit", method = RequestMethod.PUT)
    @Log(actionName = "批量审核金额")
    public void batchAudit(@RequestParam(value = "ids") String ids,
                           @RequestParam(value = "locked") Boolean locked) {
        String[] idchars = ids.split(",");
        for (String id : idchars) {
            this.couponService.auditCoupon(Integer.parseInt(id), locked);
        }
    }

    /**
     * 退卡
     *
     * @param id
     */
    @RequestMapping(value = "/coupon/{id}/returnCoupon", method = RequestMethod.PUT)
    @Log(actionName = "退卡")
    public void returnCoupon(@PathVariable(value = "id") Integer id) {
        couponService.returnCoupon(id);
    }

    /**
     * 完结卡
     *
     * @param id
     */
    @RequestMapping(value = "/coupon/{id}/overCoupon", method = RequestMethod.PUT)
    @Log(actionName = "完结卡")
    public void overCoupon(@PathVariable(value = "id") Integer id,
                           @RequestParam(value = "isOver") Integer isOver) {
        CdCoupon coupon = new CdCoupon();
        coupon.setIsOver(isOver);
        coupon.setId(id);
        mybatisDao.update(coupon);
    }

    @RequestMapping(value = "/coupon/receivedPrice", method = RequestMethod.GET)
    @Log(actionName = "财务实收金额添加")
    public void received(
            @RequestParam(value = "couponId") Integer couponId,
            @RequestParam(value = "couponNumber") String couponNumber,
            @RequestParam(value = "receivedPrice", required = false) BigDecimal receivedPrice,
            @RequestParam(value = "isAutoAudit", required = false) Integer isAutoAudit) {
        CdCouponRefExample couponRefExample = new CdCouponRefExample();
        couponRefExample.createCriteria().andCdCouponIdEqualTo(couponId)
                .andRefTypeEqualTo("RECEIVED_PRICE");
        CdCouponRef couponRef = mybatisDao.selectOneByExample(couponRefExample);
        if (couponRef == null) {
            couponRef = new CdCouponRef();
            couponRef.setCdCouponId(couponId);
            couponRef.setCouponNumber(couponNumber);
            couponRef.setRefName("财务实收金额");
            couponRef.setRefType("RECEIVED_PRICE");
            couponRef.setRefValue(receivedPrice.toString());
            mybatisDao.save(couponRef);
        }
        else {
            couponRef.setRefValue(receivedPrice.toString());
            mybatisDao.update(couponRef);
        }
        if (isAutoAudit == 1) {
            CdCoupon coupon = new CdCoupon();
            coupon.setIsUseful(1);
            coupon.setId(couponId);
            if (coupon.getCouponValue() == null) {
                coupon.setCouponValue(receivedPrice);
            }
            if (coupon.getCouponType() == 1) {
                coupon.setUserPrice(receivedPrice);
            }
            mybatisDao.update(coupon);
        }
    }

    @RequestMapping(value = "/coupon/readCard", method = RequestMethod.GET)
    @Log(actionName = "读卡")
    public ReadCardVo readCard(
            @RequestParam(value = "couponNo") String couponNo,
            @RequestParam(value = "password", required = false) String password
    ) {
        CouponQuery couponQuery = new CouponQuery();
        couponQuery.setCouponNumber(couponNo);
        if (StringUtilsExt.isNotEmpty(password)) {
            couponQuery.setPassword(password);
        }
        return mybatisDao.getSqlSessionTemplate().selectOne(CouponMapper.CouponMapperNameSpace + "getCouponByCouponNo", couponQuery);
    }

    @RequestMapping(value = "/coupon/cardRecharge", method = RequestMethod.GET)
    @Log(actionName = "充值")
    public void cardRecharge(
            @RequestParam(value = "couponNo") String couponNo,
            @RequestParam(value = "rechargePrice") BigDecimal rechargePrice
    ) {
        AssertExt.notNull("couponNo", "卡号不能为空");
        AssertExt.notNull("rechargePrice", "充值金额不能为空");
        couponService.cardRecharge(couponNo, rechargePrice);
    }


    @RequestMapping(value = "/coupon/exchangeCard", method = RequestMethod.GET)
    @Log(actionName = "换卡")
    public void exchangeCard(
            @RequestParam(value = "oldCouponNo") String oldCouponNo,
            @RequestParam(value = "oldPassword") String oldPassword,
            @RequestParam(value = "newCouponNo") String newCouponNo,
            @RequestParam(value = "newPassword") String newPassword
    ) {
        AssertExt.notNull("couponNo", "卡号不能为空");
        AssertExt.notNull("rechargePrice", "充值金额不能为空");
        couponService.exchangeCard(oldCouponNo, oldPassword, newCouponNo, newPassword);
    }


    @RequestMapping(value = "/coupon/updateBalance", method = RequestMethod.GET)
    @Log(actionName = "更新余额")
    public void exchangeCard(
            @RequestParam(value = "couponNo") String couponNo,
            @RequestParam(value = "updatePrice") BigDecimal updatePrice,
            @RequestParam(value = "remark") String remark
    ) {
        couponService.updateBalance(couponNo, updatePrice, remark);
    }

    /**
     * 卡劵导入
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/coupon/importExcel", method = RequestMethod.POST)
    public void importExcel(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(0);
            params.setHeadRows(1);
            params.setNeedSave(false);
            try {
                List<CouponReportModel> couponImportList = ExcelImportUtil.importExcel(file.getInputStream(), CouponReportModel.class, params);
                CdCouponCategory couponCategory = new CdCouponCategory();
                List<CdCouponCategory> cdCouponCategoryList = mybatisDao.selectByModel(couponCategory);
                for (CouponReportModel couponImport : couponImportList) {
                    for (CdCouponCategory cdCouponCategory : cdCouponCategoryList) {
                        if (cdCouponCategory.getCategoryName().equals(couponImport.getCategoryName())) {
                            couponImport.setCouponCategoryId(cdCouponCategory.getId());
                            couponService.importCoupon(couponImport);
                        }
                    }

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
    }

    @RequestMapping(value = "/coupon/downCouponImportTemplate")
    public void downAsnImportTemplate(HttpServletRequest request, HttpServletResponse response) {
        UploadFileVo uploadFile = new UploadFileVo();
        uploadFile.setRequest(request);
        uploadFile.setResponse(response);
        uploadFile.setExtend("xlsx");
        uploadFile.setTitleField("卡劵导入模板");
        uploadFile.setRealPath("/WEB-INF/excelFile/couponExcel.xlsx");
        PrintUtils.viewOrDownloadFile(uploadFile);
    }
}
