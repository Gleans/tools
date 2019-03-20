package store.zabbix.toolseurekaclient.util;

import org.springframework.security.core.context.SecurityContextHolder;
import store.zabbix.toolseurekaclient.beans.ResultBean;
import store.zabbix.toolseurekaclient.model.MyUserDetails;
/**
 * 被验证的委托人的身份
 * created by cfa  2018-08-02 下午 3:29
 **/
public class UserUtils {

    public static MyUserDetails getCurrentUser() {
        MyUserDetails myUserDetails=new MyUserDetails();
        Object userInfo= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userInfo!=null&&!userInfo.toString().contains("anonymousUser")){
            myUserDetails= (MyUserDetails) userInfo;
        }else{
            myUserDetails.setId(ResultBean.NO_LOGIN);
            myUserDetails.setUsername(ResultBean.TOURIST);
        }
        return myUserDetails;
    }

}
