/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author Richard Narh
 */
public class FxPageLoader {
    private String fxml;
    private Window owner;

    public FxPageLoader() {
    }
    
    public FxPageLoader(String fxml) {
        this.fxml = fxml;
    }
    
    public FxPageLoader(Window owner) {
        this.owner = owner;
    }

    public void loadFxml() {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml+".fxml"));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
           e.getMessage();
        }
    }

    public Stage loadFxml(String fxml, String title) {
         Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml+".fxml"));
            stage.initModality(null);
            stage.setTitle(title);
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.getMessage();
        }
        return stage;
    }

    public Stage loadFxml(String fxml, String title, Modality modality) {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml+".fxml"));
            stage.initModality(modality);
            stage.setTitle(title);
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.getMessage();
        }
        return stage;
    }

    public void loadFxml(String fxml, String title, Modality modality, boolean resizable) {
        Stage stage = loadFxml(fxml, title, modality);
        stage.setResizable(resizable);
        stage.initOwner(owner);
        startStage(stage);
    }
    
    private Stage startStage(Stage s){
        s.show();
        return s;
    }
}
