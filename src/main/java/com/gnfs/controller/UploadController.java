/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.controller;

import com.gnfs.model.Params;
import com.gnfs.util.JUtils;
import com.gnfs.util.Popup;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author Richard Narh
 */
public class UploadController implements Initializable {
   @FXML
   private ListView fileListView;
   @FXML
   private Button btnChoosFile;
   @FXML
   private Text fileCount;
   @FXML
   private AnchorPane filePane;
   @FXML
   private Button btnSaveFiles;

   private String receiptText;
   private List<File> fileList = new LinkedList<>();
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        receiptText = Params.newInstance().getData().toString();
        System.out.println("receiptText: "+receiptText);
    }    
    
    @FXML
    public void handleFileDrop(DragEvent event) {
        String files = null;
        fileList = event.getDragboard().getFiles();
        if(fileList.size() > 1){
            files = fileList.stream().map(File::getAbsolutePath).collect(Collectors.joining("\n"));
        }else{
            files = fileList.get(0).getAbsolutePath();
        }
        fileListView.getItems().add(files);
        fileCount.setText(String.valueOf(fileList.size()));
        event.setDropCompleted(true);
        event.consume();
    }

    @FXML
    public void handleFileOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.LINK);
        }
        event.consume();
    }
    
    @FXML
    public void handleFilleChooser(ActionEvent event) {
        Window owner = ((Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG"),
                 new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG"),
                 new FileChooser.ExtensionFilter("JPEG files (*.jpeg)", "*.jpeg"),
                 new FileChooser.ExtensionFilter("GIF files (*.gif)", "*.gif")
        );
        fileList = fileChooser.showOpenMultipleDialog(owner);
        if (fileList != null && !fileList.isEmpty()) {
            fileCount.setText(String.valueOf(fileList.size()));
            fileListView.getItems().add(fileList.stream().map(File::getAbsolutePath).collect(Collectors.joining("\n")));
        }
    }  

    @FXML
    public void handleSaveFiles(ActionEvent event) {
        Window owner = ((Node) event.getSource()).getScene().getWindow();
        if (receiptText == null) {
            Popup.error(owner, "There is no premises found for the upload");
            return;
        }
        Path destFolder = new JUtils().getRootPath(receiptText);
        if (fileList != null && !fileList.isEmpty()) {
            try {
                if (fileList.size() == 1) {
                    File sourceFile = fileList.get(0);
                    FileUtils.copyToDirectory(sourceFile, destFolder.toFile());
                } else {
                    fileList.forEach(sourceFile -> {
                        try {
                            FileUtils.copyToDirectory(sourceFile, destFolder.toFile());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            } catch (IOException e) {
                e.getMessage();
            }
            Popup.info(owner, "saved successful");
        }else{
            Popup.error(owner, "No file selected");
        }
    }
}
