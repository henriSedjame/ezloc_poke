package io.github.hsedjame.ezlocgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@SpringBootApplication
@EnableConfigurationProperties({GatewayRules.class})
public class EzlocGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(EzlocGatewayApplication.class, args);
    }


    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder, GatewayRules rules) {
        RouteLocatorBuilder.Builder routes = builder.routes();

        rules.getRules().forEach((k,v) ->
            routes.route(p -> {
                var path = String.format("/%s/**", k);
                var fromRegex = String.format("%s(?<segment>/?.*)", k);
                var toRegex = String.format("%s/$\\{segment}", v.getPrefixPath());
                var uri = String.format("http://%s:%s", v.getHost(), v.getPort());

                return p
                        .path(path)
                        .filters(f -> f.rewritePath(fromRegex, toRegex))
                        .uri(uri)
                        .id(k);
            })
        );

        return routes.build();
    }

}

