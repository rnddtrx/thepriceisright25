package be.ipam.thepriceisright.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
public class SwaggerStartupLogger {

    private static final Logger log = LoggerFactory.getLogger(SwaggerStartupLogger.class);
    private final Environment env;

    public SwaggerStartupLogger(Environment env) {
        this.env = env;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void logSwaggerUrl() {

        String port = env.getProperty("local.server.port", "8080");

        String swaggerUrl = getSwaggerUIUrl();
        String apiUrl = getApiDocsUrl();

        log.info("------------------------------------------------------------");
        log.info("Swagger UI available at: {}", swaggerUrl);
        log.info("OpenAPI docs available at: {}", apiUrl);
        log.info("------------------------------------------------------------");
    }

    public String getSwaggerUIUrl() {
        String port = env.getProperty("local.server.port", "8080");
        return "http://localhost:" + port + "/swagger-ui.html";
    }

    public String getApiDocsUrl() {
        String port = env.getProperty("local.server.port", "8080");
        return "http://localhost:" + port + "/v3/api-docs";
    }
}

