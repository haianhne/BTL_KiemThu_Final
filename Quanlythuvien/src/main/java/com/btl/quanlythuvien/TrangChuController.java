/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.quanlythuvien;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 *
 * @author ACER
 */
public class TrangChuController implements Initializable {
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML private Button thoat;
    @FXML private Button btQLSach;
    @FXML private Button btQLMuonTra;
    @FXML private Button btQLDocGia;
//    /**
//     * Initializes the controller class.
//     */
    public void userLogout(ActionEvent event) throws IOException {        
            if (event.getTarget() == thoat) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bạn có muốn thoát không?", ButtonType.OK, ButtonType.CLOSE);
            alert.setTitle("Thoat");
            alert.setHeaderText("Thoát");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
               System.exit(0);
            }
        }
    }

    public void dgHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("DocGia.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Doc Gia");
        stage.show();
        Stage stage2 = (Stage) btQLDocGia.getScene().getWindow();
        stage2.close();
    }
    
    
    public void mtHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ThueSach.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Thue Sach");
        stage.show();
        Stage stage2 = (Stage) btQLMuonTra.getScene().getWindow();
        stage2.close();
    }
    public void sHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Sach.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Sach");
        stage.show();
        Stage stage2 = (Stage) btQLSach.getScene().getWindow();
        stage2.close();
    }
}
