/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.quanlythuvien;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author ACER
 */
public class DangNhapController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML private TextField admin;
    @FXML private PasswordField password;
    @FXML private Button btnDangNhap;

    private double xOffset, yOffset;

    public void newWindow(String tenTrang) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(tenTrang));
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);

        root.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void kiemTraTK(KeyEvent e) {
        if (admin.getText().length() > 45 || password.getText().length() > 45) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setHeaderText("Lỗi:Bạn đang nhập nhiều hơn số ký tự cho phép");
            alert1.showAndWait();
        }
    }
    @FXML
    private void loginHandler(ActionEvent event) throws Exception {
        if (event.getTarget() == btnDangNhap) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Đăng nhập");
            if (admin.getText().equals("admin") && password.getText().equals("123456")) {
                newWindow("TrangChu.fxml");   
                Stage stage = (Stage) btnDangNhap.getScene().getWindow();
                stage.close();
            }
            else if(admin.getText().isEmpty()){
                alert1.setHeaderText("Vui lòng nhập username!");
                alert1.showAndWait();
            }
            else if(password.getText().isEmpty()){
                alert1.setHeaderText("Vui lòng nhập password!");
                alert1.showAndWait();
            }else{
                alert1.setHeaderText("Tài khoản hoặc mặt khẩu không đúng!");
                alert1.showAndWait();               
                }
        }
    }
    @FXML
    public void Enter(KeyEvent event) throws IOException, Exception {
        if (event.getCode() == KeyCode.ENTER) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Đăng nhập");
            if (admin.getText().length() <= 45 || password.getText().length() <= 45) {

                if (admin.getText().equals("admin") && password.getText().equals("123456")) {
                    newWindow("TrangChu.fxml");
                } else {
                    alert1.setHeaderText("Tài khoản hoặc mặt khẩu không đúng!");
                    alert1.showAndWait();
                }
            } else {
                alert1.setHeaderText("Lỗi:Bạn đang nhập nhiều hơn số ký tự cho phép");
                alert1.showAndWait();
            }
        }
    }
}
