package projetointegrador.service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import projetointegrador.service.UsuarioService;

public class GenericException extends Exception{
    private Logger logger;

    public GenericException(String message, Object object) {
        super(message);
        MDC.put("user", UsuarioService.usuarioLogado.getNome());
        this.logger = LoggerFactory.getLogger(object.getClass());
    }
}
