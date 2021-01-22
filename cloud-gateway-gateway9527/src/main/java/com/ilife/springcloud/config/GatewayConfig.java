package com.ilife.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    /**
     * 将localhost:9527/guonei 转发到http://news.baidu.com/guonei
     * @param routeBuilder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeBuilder) {
        RouteLocatorBuilder.Builder routes = routeBuilder.routes();
        routes.route("path_rote_baidu", r -> r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }
    @Bean
    public RouteLocator customRouteLocator2(RouteLocatorBuilder routeBuilder) {
        RouteLocatorBuilder.Builder routes = routeBuilder.routes();
        routes.route("path_rote_baidu", r -> r.path("/guoji").uri("http://news.baidu.com/guoji")).build();
        return routes.build();
    }
}
