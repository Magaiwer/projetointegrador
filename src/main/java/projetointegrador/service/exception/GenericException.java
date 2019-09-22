package projetointegrador.service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import projetointegrador.service.UserService;

public class GenericException extends Exception{
    private Logger logger;

    public GenericException(String message, Object object) {
        super(message);
        MDC.put("user", UserService.userLogged.getName());
        this.logger = LoggerFactory.getLogger(object.getClass());
    }
}
