package projetointegrador.listeners;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import projetointegrador.model.Audit;
import projetointegrador.model.Usuario;
import projetointegrador.model.enums.Command;
import projetointegrador.service.AuditService;
import projetointegrador.service.BeanUtil;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import java.time.LocalDateTime;

@Component
public class AuditListeners {

    public AuditListeners() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    private void beforeOperation(Object object, Command command) {
        Audit audit = new Audit();
        audit.setCommand(command);
        audit.setNewValue(object.toString());
        audit.setCreatedAt(LocalDateTime.now());

        new AuditService().save(audit);

    }

    @PostUpdate
    private void onUpdate(Object object) {
        beforeOperation(object, Command.UPDATE);
    }

    @PostPersist
    private void onInsert(Object object) {
        beforeOperation(object, Command.INSERT);

    }

    @PostRemove
    private void onDelete(Object object) {
        beforeOperation(object, Command.DELETE);
    }

    @PostLoad
    private void onLoad(Object object) {
    }
}
