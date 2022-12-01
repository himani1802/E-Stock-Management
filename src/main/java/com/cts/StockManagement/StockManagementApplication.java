package com.cts.StockManagement;

import com.cts.StockManagement.filter.JwtFilter;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class StockManagementApplication {

    @Bean
    public FilterRegistrationBean jwtFilter() {
        FilterRegistrationBean fb = new FilterRegistrationBean();
        fb.setFilter(new JwtFilter());
        fb.addUrlPatterns("/api/v1.0/*");
        return fb;
    }

    @Configuration
    class OpenApiConfig {
        @Bean
        public OpenAPI customConfig() {
            final String securitySchemeName = "bearerAuth";
            return new OpenAPI().addSecurityItem(new SecurityRequirement()
                            .addList(securitySchemeName))
                    .components(new Components().addSecuritySchemes(securitySchemeName, new SecurityScheme()
                            .name(securitySchemeName).type(SecurityScheme.Type.HTTP).scheme("bearer")
                            .bearerFormat("JWT")));

            }
        }



    public static void main(String[] args) {
        SpringApplication.run(StockManagementApplication.class, args);
    }
}
