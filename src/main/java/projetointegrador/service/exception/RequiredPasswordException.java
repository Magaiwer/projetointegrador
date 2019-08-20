package projetointegrador.service.exception;

public class RequiredPasswordException extends RuntimeException {
    public RequiredPasswordException(String senha_é_obrigatória) {
    }
}
