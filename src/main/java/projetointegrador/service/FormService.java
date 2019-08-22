package projetointegrador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import projetointegrador.model.Form;
import projetointegrador.repository.FormRepository;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = {"forms"})
public class FormService {

    @Autowired
    private FormRepository formRepository;

    private  List<Form> forms = new ArrayList<>();

    @Cacheable
    public List<Form> loadForms() {
        Optional<Form> form = formRepository.findByFormsWithEntities().stream().findFirst();
        return this.forms = formRepository.findByFormsWithEntities();
    }

    public Optional<Form> findFormByEntityName(String entityName) {
       return this.forms.stream()
                .filter(f -> f.getEntity().getName().equals(entityName)).findFirst();
    }
}
