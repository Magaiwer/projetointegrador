package projetointegrador.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import projetointegrador.model.Audit;
import projetointegrador.model.Form;
import projetointegrador.repository.AuditRepository;

import java.util.Optional;


@Service
public class AuditService {

    private Logger LOGGER = LoggerFactory.getLogger(AuditService.class);

    public void save(Audit audit) {
        AuditRepository auditRepository = BeanUtil.getBean(AuditRepository.class);

        audit.setUsuario(UsuarioService.usuarioLogado);
        Optional<Form> form = searchForm(audit);
        System.out.println(form.toString());

        try {
            if (!form.isPresent() || form.get().isAudit()){
                auditRepository.save(audit);
            }

        } catch (Exception ex) {
            MDC.put("user", UsuarioService.usuarioLogado.getNome());
            LOGGER.error(ex.getMessage());
        }
    }

    private Optional<Form> searchForm(Audit audit) {
        FormService formService = BeanUtil.getBean(FormService.class);
        return formService.findFormByEntityName(audit.getEntityName());
    }
}
