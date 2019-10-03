package projetointegrador;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import projetointegrador.model.Component;
import projetointegrador.model.Material;
import projetointegrador.repository.FaceRepository;
import projetointegrador.repository.ComponentRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculeResistanceTest {

    @Autowired(required = true)
    private FaceRepository faceRepository;

    @Autowired(required = true)
    private ComponentRepository componentRepository;


    @Test
    @After
    public void calculateResistance() {

  /*      List<Component> components = layerRepository.findByFace(1L);


        components.forEach(component -> {

                    BigDecimal condutividade = component
                            .getMaterials()
                            .stream()
                            .map(Material::getCondutividadeTermica)
                            .findFirst().orElse(BigDecimal.ZERO);

                    component.setResistence(component.calculateResistence(condutividade));

                }
        );*/

        Component component1 = new Component();
        component1.setId(1L);

        Material material1 = new Material();
        material1.setCondutividadeTermica(new BigDecimal(2));
        material1.setId(1L);

        Material material2 = new Material();
        material2.setCondutividadeTermica(new BigDecimal(2));
        material2.setId(2L);


        component1.setMaterials(Arrays.asList(material1, material2));

        component1.getComponentMaterials()
                .forEach(componentMaterial -> {
                    componentMaterial.calculateResistance(material1.getCondutividadeTermica());
                });

        System.out.println(component1.getResistanceTotal());


    }


}

