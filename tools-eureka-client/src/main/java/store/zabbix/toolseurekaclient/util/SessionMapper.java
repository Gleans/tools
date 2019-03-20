package store.zabbix.toolseurekaclient.util;

import org.apache.ibatis.session.SqlSessionFactory;
import store.zabbix.toolseurekaclient.dao.UserMapper;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

@Repository
public class SessionMapper extends SqlSessionDaoSupport {
    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    /**
     * @SuppressWarings注解:用于抑制编译器产生警告信息。
     *unchecked:禁止相对于未经检查的操作的警告
     * @Param [tablename]
     * @return java.util.Map
     * @Author cfa  2018-07-21
     **/
    @SuppressWarnings("unchecked")
    public Map queryResultMaps(String methodname){
        ResultHandler handler = new ResultHandler();
        //"."+后跟ClassMapper中的方法名
        this.getSqlSession().select(UserMapper.class.getName()+"."+methodname, handler);
        @SuppressWarnings("rawtypes")
        Map map = handler.getMappedResults();
        return map;
    }
}
