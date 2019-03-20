package store.zabbix.toolseurekaclient.service;

import store.zabbix.toolseurekaclient.dao.ResourceMapper;
import store.zabbix.toolseurekaclient.dao.RoleResourceMapper;
import store.zabbix.toolseurekaclient.model.Resource;
import store.zabbix.toolseurekaclient.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;


/**
 * 系统启动时将资源和权限的对应信息关联起来
 * 过滤器调用安全元数据源
 *
 */
@Service
public class MatchUrlSecurity implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private RoleResourceMapper roleResourceMapper;
    /*@Autowired
    private HttpServletRequest request;*/

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

//    private static Map<String, Collection<ConfigAttribute>> resourceUrl = null;

    /**
     * 在Web服务器启动时，提取系统中的所有权限。
     * 先找出所有的资源url列表
     * 然后遍历每个资源url，循环执行如下操作
     * 根据匹配关系找到对应的角色列表
     * 将匹配的角色列表封装，存入map
     * 被@PostConstruct修饰的方法会在服务器加载Servle的时候运行，并且只会被服务器执行一次。
     * PostConstruct在构造函数之后执行,init()方法之前执行。
     * 一定要加上@PostConstruct注解
     */
    @PostConstruct
    private void loadResourceDefine() {
        resourceMap = new HashMap<>();
        StringBuilder urlHttp;
        List<Resource> resourceList = resourceMapper.findAll();
        for (Resource resource : resourceList) {
            urlHttp=new StringBuilder();
            List<Role> roles = roleResourceMapper.findRolesByResourceUrl(resource.getId());
            String url = resource.getUrl();
            String method = resource.getMethod();
            Collection<ConfigAttribute> atts = new ArrayList<>();
            for (Role role : roles) {
                ConfigAttribute ca = new SecurityConfig(role.getType());
                atts.add(ca);
            }
            urlHttp.append(url).append(",").append(method);
            resourceMap.put(urlHttp.toString(), atts);
        }
        System.out.println("url is all："+resourceMap.keySet().toString());
    }

    /**
     * 根据URL，找到相关的权限配置
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // object 是一个URL，被用户请求的url。
        FilterInvocation filterInvocation = (FilterInvocation) object;
        if (resourceMap == null) {
            loadResourceDefine();
        }
       Iterator<String> ite = resourceMap.keySet().iterator();
        while (ite.hasNext()) {
            String resURL = ite.next();
            String[] resURLs = resURL.split(",");
            String url=resURLs[0];
            String method=resURLs[1];
            RequestMatcher requestMatcher = new AntPathRequestMatcher(url,method);
            if(requestMatcher.matches(filterInvocation.getHttpRequest())) {
                return resourceMap.get(resURL);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return new ArrayList<>();
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

}
