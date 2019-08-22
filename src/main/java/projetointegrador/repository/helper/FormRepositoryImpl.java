package projetointegrador.repository.helper;

import projetointegrador.model.Form;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class FormRepositoryImpl implements FormQueries {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Form> findByFormsWithEntities() {
        return entityManager
                .createQuery("select f from Form f INNER JOIN  f.entity", Form.class)
                .getResultList();
    }
}
