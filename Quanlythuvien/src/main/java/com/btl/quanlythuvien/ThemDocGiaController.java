/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.quanlythuvien;

import com.btl.conf.Utils;
import com.btl.services.QuanLyBoPhan;
import com.btl.services.QuanLyDoiTuong;
import com.btl.services.QuanLyDocGia;
import com.btl.pojo.BoPhan;
import com.btl.pojo.DocGia;
import com.btl.pojo.DoiTuong;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author ACER
 */
public class ThemDocGiaController implements Initializable {
    @FXML
    private TextField txtHo;
    @FXML
    private TextField txtTen;
    @FXML
    private TextField txtGT;
    @FXML
    private DatePicker dpNS;
    @FXML
    private ComboBox<DoiTuong> cbDT;
    @FXML
    private ComboBox<BoPhan> cbBP;
    @FXML
    private DatePicker dpBD;
    @FXML
    private DatePicker dpKT;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtDC;
    @FXML
    private TextField txtSdt;
    @FXML
    private Button btDGBack;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.txtHo.setText(null);
        this.txtTen.setText(null);
        this.txtGT.setText(null);
        this.dpNS.setValue(null);
        this.cbDT.getSelectionModel().select(null);
        this.cbBP.getSelectionModel().select(null);
        this.dpBD.setValue(null);
        this.dpKT.setValue(null);
        this.txtEmail.setText(null);
        this.txtDC.setText(null);
        this.txtSdt.setText(null);
        // TODO
        QuanLyDoiTuong s = new QuanLyDoiTuong();
        List<DoiTuong> cates = s.getDoiTuong();
        
        this.cbDT.setItems(FXCollections.observableList(cates));
        QuanLyBoPhan b = new QuanLyBoPhan();
        List<BoPhan> cate = b.getBoPhan();
        this.cbBP.setItems(FXCollections.observableList(cate));
    
    }    
    public void addDGHandler(ActionEvent event) throws SQLException {
        if(this.txtDC.getText() == null || this.txtEmail.getText() == null || this.txtGT.getText() == null
            || this.txtHo.getText() == null || this.txtSdt.getText() == null || this.txtTen.getText() == null
            || this.cbBP.getSelectionModel().isEmpty() || this.cbDT.getSelectionModel().isEmpty() || this.dpBD.getValue() == null 
            || this.dpKT.getValue() == null || this.dpNS.getValue() == null)
        {
            Utils.getBox("Vui lòng nhập đầy đủ nội dung", Alert.AlertType.INFORMATION).show();
        }
        else if(ThemSachController.isNumeric(this.txtHo.getText()) == true)
        {
            Utils.getBox("Vui lòng nhập giá trị chữ cho họ độc giả ", Alert.AlertType.INFORMATION).show();
        }
        else if (ThemSachController.isNumeric(this.txtTen.getText()) == true)
        {
            Utils.getBox("Vui lòng nhập giá trị chữ cho tên độc giả", Alert.AlertType.INFORMATION).show();
        }
        else if(ThemSachController.isNumeric(this.txtGT.getText()) == true)
        {
            Utils.getBox("Vui lòng nhập giá trị chữ cho giới tính độc giả", Alert.AlertType.INFORMATION).show();
        }
        else if (this.txtEmail.getText().contains("@") == false)
        {
            Utils.getBox("Vui lòng nhập đúng cấu trúc của một email có bao gồm kí tự @", Alert.AlertType.INFORMATION).show();
        }
        else if(ThemSachController.isNumeric(this.txtDC.getText()) == true)
        {
            Utils.getBox("Vui lòng nhập giá trị đúng cho địa chỉ của độc giả", Alert.AlertType.INFORMATION).show();
        }
        else if(ThemSachController.isNumeric(this.txtSdt.getText()) == false)
        {
            Utils.getBox("Vui lòng nhập giá trị số cho số điện thoại của độc giả", Alert.AlertType.INFORMATION).show();
        }
        else
        {
            DocGia q = new DocGia( 
                    this.txtHo.getText(), this.txtTen.getText(), this.txtGT.getText(), Date.valueOf(this.dpNS.getValue()),
                    this.cbDT.getSelectionModel().getSelectedItem().getMaDT(),
                    this.cbBP.getSelectionModel().getSelectedItem().getMaBP(),
                    Date.valueOf(this.dpBD.getValue()),Date.valueOf(this.dpKT.getValue()),this.txtEmail.getText(), this.txtDC.getText(), this.txtSdt.getText());

            QuanLyDocGia s = new QuanLyDocGia();

            try {
                s.addDG(q);
                Utils.getBox("Thêm thành công!!!", Alert.AlertType.INFORMATION).show();
            } catch (SQLException ex) {
                Logger.getLogger(ThemDocGiaController.class.getName()).log(Level.SEVERE, null, ex);
                Utils.getBox("Thêm không thành công!!!", Alert.AlertType.WARNING).show();
            }
        }
     }
      public void resetDGHandler(ActionEvent event){
        this.txtHo.setText(null);
        this.txtTen.setText(null);
        this.txtGT.setText(null);
        this.dpNS.setValue(null);
        this.cbDT.getSelectionModel().select(null);
        this.cbBP.getSelectionModel().select(null);
        this.dpBD.setValue(null);
        this.dpKT.setValue(null);
        this.txtEmail.setText(null);
        this.txtDC.setText(null);
        this.txtSdt.setText(null);
    }
    public void exHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("DocGia.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage stage2 = (Stage) btDGBack.getScene().getWindow();
        stage2.close();
    }
}
