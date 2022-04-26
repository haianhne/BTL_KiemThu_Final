/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.quanlythuvien;

import com.btl.services.QuanLySach;
import com.btl.services.QuanLyDanhMuc;
import com.btl.services.QuanLyViTri;
import com.btl.pojo.Sach;
import com.btl.pojo.DanhMuc;
import com.btl.pojo.ViTri;
import com.btl.conf.Utils;
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
public class ThemSachController implements Initializable {
    @FXML private TextField txtMaS;
    @FXML private TextField txtTenS;
    @FXML private TextField txtMoTa;
    @FXML private TextField txtNXB;
    @FXML private TextField txtNoiXB;
    @FXML private ComboBox<DanhMuc> cbDanhMuc;
    @FXML private ComboBox<ViTri> cbViTri;
    @FXML private TextField txtSL;
    @FXML private Button btThemTG;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        QuanLyDanhMuc dm = new QuanLyDanhMuc();
        List<DanhMuc> cates;
        try {
            cates = dm.getDanhMuc();
            this.cbDanhMuc.setItems(FXCollections.observableList(cates));
        } catch (SQLException ex) {
            Logger.getLogger(ThemSachController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        QuanLyViTri b = new QuanLyViTri();
        List<ViTri> cate;
        try {
            cate = b.getViTri();
            this.cbViTri.setItems(FXCollections.observableList(cate));
        } catch (SQLException ex) {
            Logger.getLogger(ThemSachController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public static boolean isNumeric(String s)
    {
        try{
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
    private boolean checkInputNull(){
        if (this.txtMaS.getText()== null ||this.txtTenS.getText() == null|| this.txtMoTa.getText() == null
                || this.txtNXB.getText() == null || this.txtNoiXB.getText()== null
                || this.cbDanhMuc.getSelectionModel().isEmpty() || this.cbViTri.getSelectionModel().isEmpty())
        {
            return true;
        }
        return false;
    }
    public void addSachHandler (){
        if(checkInputNull() == true)
        {
            Utils.getBox("Vui lòng nhập đầy đủ thông tin", Alert.AlertType.INFORMATION).show();
        }
        else if(ThemSachController.isNumeric(this.txtMaS.getText()) == false)
        {
            Utils.getBox("Vui lòng nhập giá trị số cho mã sách", Alert.AlertType.INFORMATION).show();
        }
        else if (ThemSachController.isNumeric(this.txtTenS.getText()) == true)
        {
            Utils.getBox("Vui lòng nhập giá trị chữ cho tên sách", Alert.AlertType.INFORMATION).show();
        }
        else if (ThemSachController.isNumeric(this.txtMoTa.getText()) == true)
        {
            Utils.getBox("Vui lòng nhập giá trị chữ cho phần mô tả", Alert.AlertType.INFORMATION).show();
        }
        else if (ThemSachController.isNumeric(this.txtNXB.getText()) == false)
        {
            Utils.getBox("Vui lòng nhập giá trị số cho năm xuất bản", Alert.AlertType.INFORMATION).show();
        }
        else if (ThemSachController.isNumeric(this.txtNoiXB.getText()) == true)
        {
            Utils.getBox("Vui lòng nhập giá trị chữ cho nơi xuất bản", Alert.AlertType.INFORMATION).show();
        }
        else if(ThemSachController.isNumeric(this.txtSL.getText()) == false)
        {
            Utils.getBox("Vui lòng nhập giá trị số cho số lượng", Alert.AlertType.INFORMATION).show();
        }
        else
        {
            Sach s = new Sach(Integer.parseInt(this.txtMaS.getText()),this.txtTenS.getText()
                    ,this.txtMoTa.getText(),Integer.parseInt(this.txtNXB.getText()),
                    this.txtNoiXB.getText(), this.cbDanhMuc.getSelectionModel().getSelectedItem().getMaDanhMuc(),
                    this.cbViTri.getSelectionModel().getSelectedItem().getViTriID(), Integer.parseInt(this.txtSL.getText()));
            QuanLySach qlS = new QuanLySach();
            try { 
                qlS.addSach(s);
                Utils.getBox("Thêm thành công!!!", Alert.AlertType.INFORMATION).show();
            } catch (SQLException ex) {
                Logger.getLogger(ThemSachController.class.getName()).log(Level.SEVERE, null, ex);
                Utils.getBox("Thêm không thành công!!!", Alert.AlertType.WARNING).show();
            }
        }
    }
     public void tgHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ThemTacGia.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Tac Gia");
        stage.show();
        Stage stage2 = (Stage) btThemTG.getScene().getWindow();
        stage2.close();
    }
    public void resetTSachHandler(ActionEvent event){
        this.txtMaS.setText(null);
        this.txtTenS.setText(null);
        this.txtMoTa.setText(null);
        this.txtNXB.setText(null);
        this.txtNoiXB.setText(null);
        this.cbDanhMuc.getSelectionModel().select(null);
        this.txtSL.setText(null);
        this.cbViTri.getSelectionModel().select(null);
    }
}
