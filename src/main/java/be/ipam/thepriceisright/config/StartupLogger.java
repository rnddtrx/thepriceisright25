package be.ipam.thepriceisright.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupLogger {

    private final SwaggerStartupLogger swaggerStartupLogger;

    public StartupLogger(SwaggerStartupLogger swaggerStartupLogger) {
        this.swaggerStartupLogger = swaggerStartupLogger;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onStartup() {

        System.out.println();
        System.out.println("==============================================");
        System.out.println("  \uD83C\uDF40 The Price Is Right - Application Started  ");
        System.out.println("==============================================");

        System.out.println("""
                ___________.__                          .__               .__                .__       .__     __  \s
                \\__    ___/|  |__   ____   _____________|__| ____  ____   |__| ______ _______|__| ____ |  |___/  |_\s
                  |    |   |  |  \\_/ __ \\  \\____ \\_  __ \\  |/ ___\\/ __ \\  |  |/  ___/ \\_  __ \\  |/ ___\\|  |  \\   __\\
                  |    |   |   Y  \\  ___/  |  |_> >  | \\/  \\  \\__\\  ___/  |  |\\___ \\   |  | \\/  / /_/  >   Y  \\  | \s
                  |____|   |___|  /\\___  > |   __/|__|  |__|\\___  >___  > |__/____  >  |__|  |__\\___  /|___|  /__| \s
                                \\/     \\/  |__|                 \\/    \\/          \\/           /_____/      \\/     \s
                """);

        System.out.println("=====================================================================");
        System.out.println("\uD83D\uDCD3 Swagger UI available at: " + swaggerStartupLogger.getSwaggerUIUrl());
        System.out.println("\uD83C\uDF10 OpenAPI docs available at: " + swaggerStartupLogger.getApiDocsUrl());
        System.out.println("=====================================================================");
    }
}
