package projetointegrador;

import projetointegrador.Util.EFxmlView;
import projetointegrador.config.StageManager;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class App extends Application {

    protected ConfigurableApplicationContext springContext;
    protected StageManager stageManager;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        springContext = springBootApplicationContext();
    }

    @Override
    public void start(Stage primaryStage) {
        stageManager = springContext.getBean(StageManager.class, primaryStage);
        stageManager.switchScene(EFxmlView.LOGIN);
    }

    @Override
    public void stop() {
        springContext.stop();
    }

    private ConfigurableApplicationContext springBootApplicationContext() {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(App.class);
        String[] args = getParameters().getRaw().stream().toArray(String[]::new);
        return builder.run(args);
    }
}
