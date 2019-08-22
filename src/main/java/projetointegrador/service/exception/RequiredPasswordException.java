package projetointegrador.service.exception;

public class RequiredPasswordException extends RuntimeException {

    public RequiredPasswordException(String message) {
        super(message);
    }
}
