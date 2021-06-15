package cl.everis.clientes.exception;

import cl.everis.clientes.dto.error.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Clase que captura las excepciones de la aplicacion
 */

@ControllerAdvice
public class ErrorHandler {

    /**
     * Captura exception ErrorException
     */
    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<?> manageErrorCustomException(ErrorException exception){
        ErrorDTO errorDetailsDTO =
                new ErrorDTO(exception.getHttpStatus().value(), exception.getMessage());
        return new ResponseEntity<>(errorDetailsDTO, exception.getHttpStatus());
    }
}
