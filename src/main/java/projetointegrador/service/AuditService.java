package projetointegrador.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import projetointegrador.model.Audit;
import projetointegrador.model.Form;
import projetointegrador.repository.AuditRepository;
import projetointegrador.repository.FormRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Service
public class AuditService {

    @PersistenceContext
    EntityManager entityManager;

    public void save(Audit audit) {
        AuditRepository auditRepository = BeanUtil.getBean(AuditRepository.class);
        //Optional<Entities> entities = searchEntities(audit);


        //entityManager.getTransaction().commit();


      //  List<Form> forms = searchEntities(audit);




        audit.setUsuario(UsuarioService.usuarioLogado);
        try {

            // auditRepository.save(audit);
        } catch (TransactionSystemException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private List<Form> searchEntities(Audit audit) {
        FormRepository repository = BeanUtil.getBean(FormRepository.class);
        return repository.findByName("Grupo");
        // return repository.findById(2L);
    }
}
