package projetointegrador.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXChipView;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import projetointegrador.Util.MessagesUtil;
import projetointegrador.fileTransfer.FileClient;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ClientSocketController implements Initializable {

    @FXML
    private JFXTextField txtIPServer;

    @FXML
    private JFXTextField txtPathDest;

    @FXML
    private JFXButton btnSelectFile;

    @FXML
    private JFXButton btnSendFile;

    @FXML
    private Label lbFile;

    @FXML
    private Label lbSize;

    @FXML
    private JFXChipView<File> chipViewAttach;

    @FXML
    private JFXTextField txtPort;

    private FileClient fileClient;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initListeners();

        txtIPServer.setText("localhost");
        txtPort.setText("5566");
        txtPathDest.setText("/home/user/Downloads/Wayk");
    }

    private void initListeners() {
        btnSelectFile.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();

            File fileSelected = fileChooser.showOpenDialog(new Stage());

            if (fileSelected != null) {
                bindFile(fileSelected);
            }
        });

        btnSendFile.setOnAction(event -> new Thread(() -> {
            if (fileClient != null) {
                try {
                    Socket socket = new Socket(fileClient.getIpServer(), Integer.parseInt(fileClient.getPort()));

                    fileClient.sendFile(socket);

                    socket.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start());

    }

    private void bindFile(File fileSelected) {
        double size = fileSelected.length();
        fileClient = new FileClient();
        fileClient.convertBytes(fileSelected);
        fileClient.setName(fileSelected.getName());
        fileClient.setSize(size);
        fileClient.setIpServer(txtIPServer.getText());
        fileClient.setPort(txtPort.getText());
        fileClient.setOutputDir(txtPathDest.getText().trim());

        lbFile.setText("Arquivo: " + fileSelected.getName());
        lbSize.setText("Tamanho: " + fileClient.getSizeMB() + "Mb");
        lbFile.setVisible(true);
        lbSize.setVisible(true);
    }


}
