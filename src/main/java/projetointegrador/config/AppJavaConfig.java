/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetointegrador.config;

import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import projetointegrador.security.SecurityInterceptor;

import javax.interceptor.Interceptors;
import java.io.IOException;
import java.util.ResourceBundle;

@Configuration
@EnableAspectJAutoProxy
public class AppJavaConfig {
	
    @Autowired
    SpringFXMLLoader springFXMLLoader;


    @Bean
    public ResourceBundle resourceBundle() {
        return ResourceBundle.getBundle("Bundle");
    }
    
    @Bean
    @Lazy(value = true) //Stage only created after Spring context bootstap
    public StageManager stageManager(Stage stage) throws IOException {
        return new StageManager(springFXMLLoader, stage);
    }
}
