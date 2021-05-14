package cl.everis.clientes.exception;

import cl.everis.clientes.dto.error.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> methodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {

        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        StringBuilder errorMessage = new StringBuilder();
        fieldErrors.forEach(f -> errorMessage.append(f.getField() + ": " + f.getDefaultMessage() +  ".  "));

        ErrorDTO errorDTO = new ErrorDTO(String.valueOf(HttpStatus.BAD_REQUEST.value()), errorMessage.toString());
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ErrorException.class})
    public ResponseEntity<Object> manageErrorCustomException(ErrorException exception) {
        ErrorDTO errorDTO=ErrorDTO.builder().statusCode(exception.getHttpStatus().toString())
                .message(exception.getBody().toString()).build();
        return new ResponseEntity<>(errorDTO, exception.getHttpStatus());
    }
}
