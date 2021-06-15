package cl.everis.clientes.dto.error;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/**
 * Clase DTO de error
 */

@Getter
@Builder
public class ErrorDTO implements Serializable {

    private final int statusCode;
    private final String message;

    public ErrorDTO(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

}
