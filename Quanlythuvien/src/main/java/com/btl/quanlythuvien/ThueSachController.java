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
public class ThueSachController implements Initializable {
    @FXML private Button thoat;
    @FXML private Button btTrangChuBack;
    @FXML private Button btTraS;
    @FXML private Button btMuonS;
    @FXML private Button btThongKe;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
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
    public void muonHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MuonSach.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Muon sach");
        stage.show();
        Stage stage2 = (Stage) btMuonS.getScene().getWindow();
        stage2.close();
    }
    public void traHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("TraSach.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Tra Sach");
        stage.show();
        Stage stage2 = (Stage) btTraS.getScene().getWindow();
        stage2.close();
    }
    public void thongKeHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ThongKe.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Thong Ke");
        stage.show();
        Stage stage2 = (Stage) btThongKe.getScene().getWindow();
        stage2.close();
    }
    public void qlHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("TrangChu.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage stage2 = (Stage) btTrangChuBack.getScene().getWindow();
        stage2.close();
    }
}
