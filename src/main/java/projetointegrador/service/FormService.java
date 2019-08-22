package projetointegrador.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import projetointegrador.model.Form;
import projetointegrador.repository.FormRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = {"forms"})
public class FormService {

    private Logger LOGGER = LoggerFactory.getLogger(FormService.class);

    @Autowired
    private FormRepository formRepository;

    private List<Form> forms = new ArrayList<>();

    @Cacheable
    public List<Form> loadForms() {
        return this.forms = formRepository.findByFormsWithEntities();
    }

    public Optional<Form> findFormByEntityName(String entityName) {
        return this.forms.stream()
                .filter(f -> f.getEntity().getName().equals(entityName.toLowerCase()))
                .findFirst();
    }


    public void updateAudit(List<Form> forms) {
        try {
            formRepository.saveAll(forms);
            loadForms();
        } catch (RuntimeException ex) {
            LOGGER.error(ex.getMessage());
        }
    }
}
