package projetointegrador;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import projetointegrador.model.Material;
import projetointegrador.repository.FaceRepository;
import projetointegrador.repository.LayerRepository;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculeResistenceTest {

    @Autowired(required = true)
    private FaceRepository faceRepository;

    @Autowired(required = true)
    private LayerRepository layerRepository;


    @Test
    @After
    public void calculateResistence() {


        layerRepository.findByFace(1L)
                .forEach(layer -> {

                            BigDecimal condutividade = layer
                                    .getMaterials()
                                    .stream()
                                    .map(Material::getCondutividadeTermica)
                                    .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
                            Assert.assertEquals(BigDecimal.TEN, condutividade);
                        }
                );






/*        MensagemCB result = mensagemCB.getMensagemCBList().stream()
                .filter(item -> item.getVeiID() == 263888).findFirst().orElse(null);*/


    }


}

