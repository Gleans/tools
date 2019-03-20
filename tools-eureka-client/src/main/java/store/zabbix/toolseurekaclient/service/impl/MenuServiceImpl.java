package store.zabbix.toolseurekaclient.service.impl;

import store.zabbix.toolseurekaclient.dao.MenuMapper;
import store.zabbix.toolseurekaclient.model.Menu;
import store.zabbix.toolseurekaclient.model.MyUserDetails;
import store.zabbix.toolseurekaclient.model.Role;
import store.zabbix.toolseurekaclient.service.MenuService;
import store.zabbix.toolseurekaclient.beans.ResultBean;
import store.zabbix.toolseurekaclient.util.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    private final MenuMapper menuMapper;

    @Autowired
    public MenuServiceImpl(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }


    @Override
    public List<Menu> getMenuByParentid(Integer parentid) {
        return menuMapper.getMenuByParentid(parentid);
    }

    @Override
    public List<Menu> queryHasMenu() {
         MyUserDetails myUserDetails = UserUtils.getCurrentUser();
        if (myUserDetails.getId() != ResultBean.NO_LOGIN) {
            List<Role> roleList = myUserDetails.getRoles();
            Integer[] array = new Integer[roleList.size()];
            for (int i = 0; i < roleList.size(); i++) {
                array[i] = roleList.get(i).getId();
            }
            return menuMapper.queryHasMenu(array);
        } else {
            return new ArrayList<>();
        }
        //测试版本
//        return menuMapper.queryHasMenu(new Integer[]{1});
    }

    @Override
    @Transactional
    public Integer updateByPrimaryKeySelective(Menu menu) {
        logger.info("MenuServiceImpl updateByPrimaryKeySelective menu:"+menu.toString());
        try{
            menuMapper.updateByPrimaryKeySelective(menu);
        }catch (Exception e){
            logger.error("MenuServiceImpl updateByPrimaryKeySelective error:"+e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultBean.FAIL;
        }
        return ResultBean.SUCCESS;
    }
}
