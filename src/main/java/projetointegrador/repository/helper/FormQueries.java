package projetointegrador.repository.helper;

import projetointegrador.model.Form;

import java.util.List;

public interface FormQueries {

    List<Form> findByFormsWithEntities();
}
