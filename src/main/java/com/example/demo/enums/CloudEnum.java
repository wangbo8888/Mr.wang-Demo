package com.example.demo.enums;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:云仓相关的统一枚举类
 * @Author: Mr.wang
 * @Email: mip6282@dingtalk.com
 * @Date: 2021/9/13
 */
public enum CloudEnum {
    /**
     * 寻源异常原因
     */
    FAILED_TO_LOCK_FAILURE("10000", "锁库失败"),
    UNSATISFIED_STORES("10001", "没有满足的门店"),
    ORDER_TIMEOUT("10002", "接单超时"),
    REJECTION("10003", "拒单"),
    /**
     * 派单类型
     */
    ASSIGN_TYPE_AUTO("auto", "自动寻源"),
    ASSIGN_TYPE_RE_AUTO("re", "重新寻源"),
    ASSIGN_TYPE_MANUAL("manual", "手动派单"),
    //小程序商城自提业务
    ASSIGN_TYPE_SYSTEM("system", "系统派单"),
    /**
     * 派单类型(此类型只用对外入参)
     * 使用时千万注意！！！！！！！！！
     */
    AUTO("auto", "自动派单"),
    HAND("hand", "手动派单"),

    /**
     * 寻源枚举：
     * 同成本中心、 就近就全 筛选纬度枚举值
     */
    CITY("city", "同城"),
    COMPANY("company", "同司"),
    COUNTRY("country", "全国"),
    /**
     * 仓库类型
     */
    A01("edc", "电商仓"), //电商仓
    A02("sdc", "门店仓"),// 门店
    A03("cdc", "总仓"),//总仓
    A04("rdc", "分司区域仓"),//分司区域仓
    /**
     * 门店单据类型
     * */
    DISPATCH("dispatch","派单"),
    RECEIPT("receipt","接单"),
    SEND("send","发货"),
    REJECT("reject","拒单"),

    ;


    private String code;
    private String desc;

    CloudEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;

    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDesc(String code) {
        String desc = null;
        if (code == null) return null;
        for (CloudEnum s : CloudEnum.values()) {
            if (code.equalsIgnoreCase(s.getCode())) {
                desc = s.getDesc();
                break;
            }
        }
        return desc;
    }

    public static List<CloudEnum> getAll() {
        List<CloudEnum> allList = new ArrayList<CloudEnum>();
        for (CloudEnum s : CloudEnum.values()) {
            allList.add(s);
        }
        return allList;
    }
    public static List<CloudEnum> getAllLamdar() {

        return Arrays.stream(CloudEnum.values()).collect(Collectors.toList());
    }

}
