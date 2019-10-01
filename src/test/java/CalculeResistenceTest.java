import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import projetointegrador.model.Material;
import projetointegrador.repository.FaceRepository;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CalculeResistenceTest {

    @Mock
    private FaceRepository faceRepository;


    @Test
    public void calculateResistence() {
        System.out.println(faceRepository.getOne(1L).getName());

/*        faceRepository.findAll().forEach(face -> {
            face.getLayers().forEach(layer -> {


               BigDecimal condutividade = layer.getMaterials()
                       .stream()
                       .map(Material::getCondutividadeTermica)
                       .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);


                Assert.assertEquals(2L, condutividade);

            });
        });*/

/*        MensagemCB result = mensagemCB.getMensagemCBList().stream()
                .filter(item -> item.getVeiID() == 263888).findFirst().orElse(null);*/


    }








}

