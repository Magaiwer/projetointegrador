package projetointegrador.service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import projetointegrador.service.UsuarioService;

public class GenericException extends Exception{

   // private static Logger LOGGER = LoggerFactory.getLogger();
    public GenericException(String message) {
        super(message);
       // MDC.put("user", UsuarioService.usuarioLogado.getNome());
        //LOGGER.error(message);
    }
}
