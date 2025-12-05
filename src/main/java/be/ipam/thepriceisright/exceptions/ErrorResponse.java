package be.ipam.thepriceisright.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(description = "Error response")
public class ErrorResponse {

    @Schema(example = "2025-11-25T15:15:00")
    public LocalDateTime timestamp;

    @Schema(example = "/products/1001000")
    public String path;

    @Schema(example = "404")
    public int status;

    @Schema(example = "Product not found with id: 1")
    public String message;

    public ErrorResponse(HttpStatus status, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status.value();
        this.message = message;
        this.path = path;
    }
}
