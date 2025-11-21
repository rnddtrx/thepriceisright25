package be.ipam.thepriceisright.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Error response")
public class ErrorResponse {

    @Schema(example = "404")
    public int status;

    @Schema(example = "Product not found")
    public String message;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
