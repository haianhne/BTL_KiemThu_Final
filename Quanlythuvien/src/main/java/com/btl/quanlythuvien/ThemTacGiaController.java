/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.quanlythuvien;

import com.btl.conf.Utils;
import com.btl.services.QuanLySach;
import com.btl.services.QuanLyTacGia;
import com.btl.pojo.Sach;
import com.btl.pojo.TacGia;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author ACER
 */
public class ThemTacGiaController implements Initializable {
    @FXML private TextField txtThemTenTG;
    @FXML private ComboBox<Sach> cbTenSach;
    @FXML private Button btTG;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.txtThemTenTG.setText(null);
        this.cbTenSach.getSelectionModel().select(null);
        QuanLySach s = new QuanLySach();
        List<Sach> cates;
        try {
            cates = s.sachThemTG();
            this.cbTenSach.setItems(FXCollections.observableList(cates));
        } catch (SQLException ex) {
            Logger.getLogger(ThemSachController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public void addTGHandler (){
        if(this.txtThemTenTG.getText() == null || this.cbTenSach.getSelectionModel().isEmpty())
        {
            Utils.getBox("Vui lòng nhập đầy đủ thông tin", Alert.AlertType.INFORMATION).show();
        }
        else if(ThemSachController.isNumeric(this.txtThemTenTG.getText()) == true)
        {
            Utils.getBox("Vui lòng nhập giá trị chữ cho tên tác giả", Alert.AlertType.INFORMATION).show();
        }
        else
        {
            TacGia s = new TacGia(this.cbTenSach.getSelectionModel().getSelectedItem().getMaSach(),
                    this.txtThemTenTG.getText());
            QuanLyTacGia qltg = new QuanLyTacGia();
            try {
                qltg.addTG(s);
                Utils.getBox("Thêm thành công!!!", Alert.AlertType.INFORMATION).show();
            } catch (SQLException ex) {
                Logger.getLogger(ThemSachController.class.getName()).log(Level.SEVERE, null, ex);
                Utils.getBox("Thêm không thành công!!!", Alert.AlertType.WARNING).show();
            }
        }
    }
    public void resetThemTGHandler(ActionEvent event){
        this.txtThemTenTG.setText(null);
        this.cbTenSach.getSelectionModel().select(null);
    }
    public void qlHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Sach.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage stage2 = (Stage) btTG.getScene().getWindow();
        stage2.close();
    }
}
