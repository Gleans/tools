package store.zabbix.tools.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "SysUser")
@TableName(value = "sys_user")
public class SysUser {
    /**
     * 主键ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    @ApiModelProperty(name = "主键ID")
    private Integer userId;

    /**
     * 用户名
     */
    @TableField(value = "username")
    @ApiModelProperty(name = "用户名")
    private String username;

    @TableField(value = "password")
    @ApiModelProperty(name = "")
    private String password;

    /**
     * 随机盐
     */
    @TableField(value = "salt")
    @ApiModelProperty(name = "随机盐")
    private String salt;

    /**
     * 简介
     */
    @TableField(value = "phone")
    @ApiModelProperty(name = "简介")
    private String phone;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    @ApiModelProperty(name = "头像")
    private String avatar;

    /**
     * 部门ID
     */
    @TableField(value = "dept_id")
    @ApiModelProperty(name = "部门ID")
    private Integer deptId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(name = "创建时间")
    private Long createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(name = "修改时间")
    private Long updateTime;

    /**
     * 0-正常，9-锁定
     */
    @TableField(value = "lock_flag")
    @ApiModelProperty(name = "0-正常，9-锁定")
    private String lockFlag;

    /**
     * 0-正常，1-删除
     */
    @TableField(value = "del_flag")
    @ApiModelProperty(name = "0-正常，1-删除")
    private String delFlag;

    /**
     * 微信openid
     */
    @TableField(value = "wx_openid")
    @ApiModelProperty(name = "微信openid")
    private String wxOpenid;

    /**
     * QQ openid
     */
    @TableField(value = "qq_openid")
    @ApiModelProperty(name = "QQ openid")
    private String qqOpenid;

    /**
     * 头像地址
     */
    @TableField(value = "head_picture")
    @ApiModelProperty(name = "头像地址")
    private String headPicture;

    public static final String COL_USER_ID = "user_id";

    public static final String COL_USERNAME = "username";

    public static final String COL_PASSWORD = "password";

    public static final String COL_SALT = "salt";

    public static final String COL_PHONE = "phone";

    public static final String COL_AVATAR = "avatar";

    public static final String COL_DEPT_ID = "dept_id";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_LOCK_FLAG = "lock_flag";

    public static final String COL_DEL_FLAG = "del_flag";

    public static final String COL_WX_OPENID = "wx_openid";

    public static final String COL_QQ_OPENID = "qq_openid";

    public static final String COL_HEAD_PICTURE = "head_picture";
}