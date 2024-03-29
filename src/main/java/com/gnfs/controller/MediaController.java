/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.controller;

import com.gnfs.util.JUtils;
import com.gnfs.util.Popup;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author Richard Narh
 */
public class MediaController implements Initializable {
    private static final Logger log = LoggerFactory.getLogger(MediaController.class);
    
    @FXML
    private TilePane imageTilePane;
    @FXML
    private ImageView imageView;
    @FXML
    private TreeView<?> mediaTree;

    private File selectedFile;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTreeView();
    }    
    
    public void initTreeView(){
        TreeItem rootItem = new TreeItem("GHANA NATIONAL FIRE SERVICE - MEDIA DATA");
        TreeItem childItem = null;
        Path parentRoot = new JUtils().getRootPath();
        try {
            List<TreeItem> childItemList = new LinkedList<>();
            List<Path> directories = Files.walk(parentRoot).filter(Files::isDirectory).collect(Collectors.toList());
            directories.remove(parentRoot);
            log.debug("directories: {}",directories.toString());
            for (Path path : directories) {
                childItem = new TreeItem(path.getFileName());
                childItemList.add(childItem);
            }
            
            for (TreeItem treeItem : childItemList) {
                for (Path path : directories) {
                    if(path.getFileName().equals(treeItem.getValue())){
                        List<String> files = JUtils.listFiles(path.toString(),false);
                        files.forEach(file -> {
                            treeItem.getChildren().add(new TreeItem(file));
                        });
                    }
                }
            }
        } catch (IOException e) {
            e.getMessage();
        }
        
        mediaTree.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem> observable, TreeItem oldValue, TreeItem newValue) {
                try {
                    if(imageView != null) imageView.setImage(null);
                    String filename = newValue != null ? String.valueOf(newValue.getValue()) : null;
                    log.debug("oldValue: {}", oldValue);
                    log.debug("newValue: {}", filename);
                    
                    File filePath = Paths.get(parentRoot + File.separator + filename).toFile();
                    log.debug("newpath: {}",filePath.toString());
                    if(filePath.isDirectory()){
                        log.debug("Hello Directory");
                    }else{
                        System.out.println("Not Directory");
                        List<Path> directories = Files.walk(parentRoot).filter(Files::isDirectory).collect(Collectors.toList());
                        directories.remove(parentRoot);

                        for (Path path : directories) {
                            if (path.toFile().isDirectory()) {
                                List<String> files = JUtils.listFiles(path.toString(), true);
                                for (String file : files) {
                                    File f = new File(file);
                                    log.debug("File: {}", f.getAbsolutePath());
                                    if (f.getName().equalsIgnoreCase(filename)) {
                                        filePath = f;
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }
                    if(filePath.isFile()){
                        selectedFile = filePath;
                        log.debug("File found: {}",filePath.getAbsolutePath());
                        imageView = createImageView(filePath);
                        imageTilePane.getChildren().addAll(imageView);
                    }else{
                        String[] files = filePath.list();
                        for (int i = 0; i < files.length; i++) {
                            log.debug("files: {}"+filePath + File.separator + files[i]);
                            String file = filePath + File.separator + files[i];
                            imageView = createImageView(new File(file));
                            imageTilePane.getChildren().addAll(imageView);
                        }
                    }
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        });
        
        rootItem.getChildren().add(childItem);
        rootItem.setExpanded(true);
        mediaTree.setShowRoot(false);
        mediaTree.setRoot(rootItem);
    }
    
    private ImageView createImageView(File imageFile) {
        try {
            final Image image = new Image(new FileInputStream(imageFile), 450, 0, true,true);
            imageView = new ImageView(image);
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        }
        return imageView;
    }

    @FXML
    public void deleteAction(ActionEvent event) {
        Window owner = ((Node) event.getSource()).getScene().getWindow();
        if(selectedFile != null){
            try {
                imageView.setImage(null);
                System.gc();
                log.debug("selectedFile: {}",selectedFile.getAbsolutePath());
                boolean isDeleted = Files.deleteIfExists(Paths.get(selectedFile.getAbsolutePath()));
                if(isDeleted){
                    initTreeView();
                    Popup.info(owner, "File deleted.");
                }
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }
}
