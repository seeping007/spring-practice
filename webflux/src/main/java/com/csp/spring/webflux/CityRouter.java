package com.csp.spring.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author chensiping
 * @since 2020-06-24
 */
@Configuration
public class CityRouter {

    @Bean
    public RouterFunction<ServerResponse> routeCity(CityHandler cityHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/hello").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                        cityHandler::helloCity);
    }
}
