package projetointegrador.listeners;

import org.springframework.stereotype.Component;
import projetointegrador.model.Audit;
import projetointegrador.model.enums.Command;
import projetointegrador.service.AuditService;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import java.time.LocalDateTime;

@Component
public class AuditListeners {

    private void beforeOperation(Object object, Command command) {
        Audit audit = new Audit();
        audit.setCommand(command);
        audit.setNewValue(object.toString());
        audit.setCreatedAt(LocalDateTime.now());

        audit.setEntityName(object.getClass().getSimpleName());

        new AuditService().save(audit);
    }

    @PostUpdate
    void onUpdate(Object object) {
        beforeOperation(object, Command.UPDATE);
    }

    @PostPersist
     void onInsert(Object object) {
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
