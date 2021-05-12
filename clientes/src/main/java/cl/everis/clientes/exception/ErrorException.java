package cl.everis.clientes.exception;

import org.springframework.http.HttpStatus;

public class ErrorException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private HttpStatus httpStatus;
    private Object object;


    public ErrorException(HttpStatus httpStatus, Object object) {
        super(httpStatus.getReasonPhrase());
        this.httpStatus = httpStatus;
        this.object = object;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }


    public Object getBody(){
        return object;
    }


}