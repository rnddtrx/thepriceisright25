package be.ipam.thepriceisright.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI priceIsRightApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("The Price Is Right API")
                        .description("API REST pour gérer les produits et leurs prix")
                        .version("1.0.0"));
    }
}