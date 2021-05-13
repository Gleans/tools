package com.github.luca168.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * tips: API 统一返回状态码
 * date 2021/5/13 18:22
 * created by cuifuan@aliyun.com
 */
public enum ResultCodeEnum {

    /* 成功状态码 */
    SUCCESS(10000, "请求成功"),

    /* 系统错误 */
    SYSTEM_INNER_ERROR(11001, "系统内部错误"),


    /* 参数错误 */
    PARAM_IS_INVALID(12001, "参数非法"),
    /* 数据错误：*/
    RESULT_DATA_NONE(12002, "数据未找到"),
    DATA_IS_WRONG(12003, "数据有误"),
    DATA_ALREADY_EXISTED(12004, "数据已存在"),


    /* 接口错误：*/
    INTERFACE_EXCEED_LOAD(13001, "接口负载过高"),
    INTERFACE_REPEAT_COMMIT(13002, "接口重复提交"),

    /* 权限错误：*/
    USER_NOT_LOGGED_IN(14001, "用户未登录"),
    PERMISSION_NO_ACCESS(14003, "无访问权限"),

    /* 微服务 */
    NO_PERMISSION(21001, "服务未授权"),
    SERVICE_DEMOTION(21002, "服务超时"),
    SERVICE_EXCEPTION(21003, "服务被调用方异常"),
    SERVICE_DOWNGRADE(21004, "服务被调用方降级"),

    /* 用户错误 */
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(2003, "用户名或者密码错误"),
    USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
    USER_ACCOUNT_DISABLE(2005, "账号不可用"),
    USER_ACCOUNT_LOCKED(2006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(2007, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(2008, "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(2009, "账号下线"),
    USER_ACCOUNT_NOT_STATUS(2010, "禁用操作失败"),
    USER_ACCOUNT_DELETE_EXIST(2011, "删除失败"),
    USER_ACCOUNT_UPDATE_ID(2012, "修改失败");

    private final Integer code;
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    @Override
    public String toString() {
        return this.name();
    }


    public static String getMessage(String name) {
        for (ResultCodeEnum item : ResultCodeEnum.values()) {
            if (item.name().equals(name)) {
                return item.message;
            }
        }
        return name;
    }

    public static Integer getCode(String name) {
        for (ResultCodeEnum item : ResultCodeEnum.values()) {
            if (item.name().equals(name)) {
                return item.code;
            }
        }
        return null;
    }

    /***
     * 校验重复的code值
     */
    static void main(String[] args) {
        ResultCodeEnum[] apiResultCodes = ResultCodeEnum.values();
        List<Integer> codeList = new ArrayList<>();
        for (ResultCodeEnum apiResultCode : apiResultCodes) {
            if (codeList.contains(apiResultCode.code)) {
                System.out.println(apiResultCode.code);
            } else {
                codeList.add(apiResultCode.code());
            }
            System.out.println(apiResultCode.code() + " " + apiResultCode.message());
        }
    }
}
