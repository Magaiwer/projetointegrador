package projetointegrador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetointegrador.model.MaterialAbsortancia;
import projetointegrador.repository.MaterialAbsortanciaRepository;
import projetointegrador.service.exception.FieldRequeridException;

import java.math.BigDecimal;

@Service
public class MaterialAbsortanciaService  {

    @Autowired
    private MaterialAbsortanciaRepository materialAbsortanciaRepository;
    public void save(MaterialAbsortancia materialAbsortancia)  {

        if(materialAbsortancia.getSuperficie().isEmpty()) {
            throw  new FieldRequeridException("Superfice é obrigatória");
        }

        if(materialAbsortancia.getAlfaIni().doubleValue() == 0 ) {
            throw  new FieldRequeridException("Alfa ini é obrigatório");
        }

        if(materialAbsortancia.getAlfaIni().doubleValue() == 0 ) {
            throw  new FieldRequeridException("Alfa fim é obrigatório");
        }

        if(materialAbsortancia.getAlfaIni().doubleValue() > materialAbsortancia.getAlfaFim().doubleValue()) {
            throw  new FieldRequeridException("Alfa ini não pode ser maior que o alfa fim");
        }

        if(materialAbsortancia.getBetaIni().doubleValue() > materialAbsortancia.getBetaFim().doubleValue()) {
            throw  new FieldRequeridException("Beta ini não pode ser maior que o Beta fim");
        }

        materialAbsortanciaRepository.save(materialAbsortancia);

    }
}
