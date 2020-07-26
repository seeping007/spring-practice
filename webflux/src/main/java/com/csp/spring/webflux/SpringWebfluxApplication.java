package com.csp.spring.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author CSP
 * @since 2019/1/30
 */
@SpringBootApplication
public class SpringWebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebfluxApplication.class, args);

        CityWebClient cityWebClient = new CityWebClient();
        System.out.println(cityWebClient.getResult());
    }
}
