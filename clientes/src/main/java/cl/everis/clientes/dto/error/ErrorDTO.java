package cl.everis.clientes.dto.error;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorDTO {

    private String statusCode;
    private String message;

    public ErrorDTO(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

}
