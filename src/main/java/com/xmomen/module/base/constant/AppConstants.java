package com.xmomen.module.base.constant;

import java.io.Serializable;

/**
 * Created by Jeng on 16/5/10.
 */
public class AppConstants implements Serializable {

    /**
     * 客服经理角色代码
     */
    public static final String CUSTOMER_MANAGER_PERMISSION_CODE = "customer_manager";

    /**
     * 客服组
     */
    public static final String CUSTOMER_PERMISSION_CODE = "kehuzu";

    /**
     * 后台组
     */
    public static final String HOU_TAI_CODE = "houtaibu";

    /**
     * 管理员
     */
    public static final String ADMIN = "admin";

    /**
     * 超级管理员
     */
    public static final String SUPER_ADMIN = "super_admin";

    /**
     * 物流中心
     */
    public static final String WULIUZXB = "wuliuzxb";

    /**
     * 财务组
     */
    public static final String CWU = "cwu";

    public static final String PACKAGE_PERMISSION_CODE = "baozhuangzu";

    public static final String PACKING_PERMISSION_CODE = "zhuangxiangzu";
    /**
     * 运输
     */
    public static final String YUN_SHU_PERMISSION_CODE = "yunshubu";

    /**
     * 快递商
     */
    public static final String KUAI_DI_SHANG = "kuaidishang";

    /**
     * 用户sessionUserId键值
     */
    public static final String SESSION_USER_ID_KEY = "user_id";
    
    public static final String PC_PASSWORD_SALT = "dms_pc";

    public static final int STOCK_CHANGE_TYPE_IN = 1;//入库
    public static final int STOCK_CHANGE_TYPE_BROKEN = 2;//破损
    public static final int STOCK_CHANGE_TYPE_CANCEL = 3;//核销
}
