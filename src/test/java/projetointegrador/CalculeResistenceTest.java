package projetointegrador;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import projetointegrador.model.Layer;
import projetointegrador.model.Material;
import projetointegrador.repository.FaceRepository;
import projetointegrador.repository.LayerRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

  /*      List<Layer> layers = layerRepository.findByFace(1L);


        layers.forEach(layer -> {

                    BigDecimal condutividade = layer
                            .getMaterials()
                            .stream()
                            .map(Material::getCondutividadeTermica)
                            .findFirst().orElse(BigDecimal.ZERO);

                    layer.setResistence(layer.calculateResistence(condutividade));

                }
        );*/

        List<Layer> layers = new ArrayList<>();

        Layer layer1 = new Layer();
        layer1.setId(1L);
        layer1.setThickness(new BigDecimal(8));

        Material material1 = new Material();
        material1.setCondutividadeTermica(new BigDecimal(2));
        material1.setId(1L);

        Material material2 = new Material();
        material2.setCondutividadeTermica(new BigDecimal(2));
        material2.setId(2L);


        layer1.setMaterials(Arrays.asList(material1, material2));

        layer1.getLayerMaterials()
                .forEach(layerMaterial -> {
                    layerMaterial.calculateResistence(layer1.getThickness());

                });

        System.out.println(layer1.getResistenceTotal());


    }


}

