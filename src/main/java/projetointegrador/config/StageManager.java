package projetointegrador.config;

import javafx.fxml.LoadException;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import projetointegrador.Util.EFxmlView;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projetointegrador.Util.MessagesUtil;

import java.io.IOException;
import java.util.Objects;

public class StageManager {

    private Logger LOGGER = LoggerFactory.getLogger(StageManager.class);
    private final Stage primaryStage;
    private final SpringFXMLLoader springFXMLLoader;

    public StageManager(SpringFXMLLoader springFXMLLoader, Stage stage) {
        this.springFXMLLoader = springFXMLLoader;
        this.primaryStage = stage;
    }

    public void switchScene(final EFxmlView view) {
        Parent viewRootNodeHierarchy = loadViewNodeHierarchy(view.getFxmlFile());
        show(viewRootNodeHierarchy, view.getTitle());
    }

    public void switchScene(AnchorPane body, final EFxmlView view) {
        Parent viewRootNodeHierarchy = loadViewNodeHierarchy(view.getFxmlFile());
        body.getChildren().clear();
        body.getChildren().add(viewRootNodeHierarchy);
        resize(viewRootNodeHierarchy);
    }

    private void resize(Node no) {
        AnchorPane.setBottomAnchor(no, 0.0);
        AnchorPane.setLeftAnchor(no, 0.0);
        AnchorPane.setTopAnchor(no, 0.0);
        AnchorPane.setRightAnchor(no, 0.0);

    }



    private void show(final Parent rootnode, String title) {
        Scene scene = prepareScene(rootnode);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();
        
        try {
            primaryStage.show();
        } catch (Exception e) {
            LOGGER.error("Erro ao carregar a cena " + e.getMessage());
            MessagesUtil.showMessageError("Erro ao carregar o formulário! Tente novamente");
        }
    }
    
    private Scene prepareScene(Parent rootnode){
        Scene scene = primaryStage.getScene();

        if (scene == null) {
            scene = new Scene(rootnode);
        }
        scene.setRoot(rootnode);
        return scene;
    }

    private Parent loadViewNodeHierarchy(String fxmlFilePath) {
        Parent rootNode = null;
        try {
            rootNode = springFXMLLoader.load(fxmlFilePath);
            Objects.requireNonNull(rootNode, "A Root FXML node não pode ser nullo");
        } catch (IOException | RuntimeException e) {
            LOGGER.error("A Root FXML node não pode ser nullo " + e.getMessage());
            MessagesUtil.showMessageError("Formulário não encontrado !");
           //Platform.exit();
        }
        return rootNode;
    }

}
