//package store.zabbix.tools.gateway.config;
//
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.nacos.api.NacosFactory;
//import com.alibaba.nacos.api.config.ConfigService;
//import com.alibaba.nacos.api.config.listener.Listener;
//import com.alibaba.nacos.api.exception.NacosException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
//import org.springframework.cloud.gateway.route.RouteDefinition;
//import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.context.ApplicationEventPublisherAware;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
//import javax.annotation.PostConstruct;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.Executor;
//
//@Slf4j
//@Component
//public class NacosDynamicRouteService implements ApplicationEventPublisherAware {
//
//    private final static String dataId = "gateway-router";
//
//    private final static String group = "DEFAULT_GROUP";
//
//    @Value("${spring.cloud.nacos.config.server-addr}")
//    private String serverAddr;
//
//    @Autowired
//    private RouteDefinitionWriter routeDefinitionWriter;
//
//    private ApplicationEventPublisher applicationEventPublisher;
//
//    private static final List<String> ROUTE_LIST = new ArrayList<>();
//
//    @PostConstruct
//    public void dynamicRouteByNacosListener() {
//        try {
//            ConfigService configService = NacosFactory.createConfigService(serverAddr);
//            configService.getConfig(dataId, group, 5000);
//            configService.addListener(dataId, group, new Listener() {
//                @Override
//                public void receiveConfigInfo(String configInfo) {
//                    clearRoute();
//                    try {
//                        List<RouteDefinition> gatewayRouteDefinitions = JSONObject.parseArray(configInfo, RouteDefinition.class);
//                        for (RouteDefinition routeDefinition : gatewayRouteDefinitions) {
//                            log.info("routeDefinition:{}", routeDefinition.toString());
//                            addRoute(routeDefinition);
//                        }
//                        publish();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public Executor getExecutor() {
//                    return null;
//                }
//            });
//        } catch (NacosException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void clearRoute() {
//        for (String id : ROUTE_LIST) {
//            this.routeDefinitionWriter.delete(Mono.just(id)).subscribe();
//        }
//        ROUTE_LIST.clear();
//    }
//
//    private void addRoute(RouteDefinition definition) {
//        try {
//            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
//            ROUTE_LIST.add(definition.getId());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void publish() {
//        this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this.routeDefinitionWriter));
//    }
//
//    @Override
//    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
//        this.applicationEventPublisher = applicationEventPublisher;
//    }
//}