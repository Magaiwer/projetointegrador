package projetointegrador.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projetointegrador.model.Audit;
import projetointegrador.repository.AuditRepository;


@Service
public class AuditService {

    public AuditService() {

    }

    @Transactional
    public void save(Audit audit) {
        AuditRepository auditRepository = BeanUtil.getBean(AuditRepository.class);

        audit.setUsuario(UsuarioService.usuarioLogado);
        auditRepository.save(audit);
    }
}
