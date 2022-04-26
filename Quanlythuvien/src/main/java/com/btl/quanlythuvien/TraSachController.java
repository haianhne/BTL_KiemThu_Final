/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.quanlythuvien;

import com.btl.conf.Utils;
import com.btl.pojo.ThueSach;
import com.btl.services.QuanLyThueSach;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author ACER
 */
public class TraSachController implements Initializable {
    @FXML private TextField maDG2;
    @FXML private Label IDSach;
    @FXML private Label dMuon;
    @FXML private Label dHan;
    @FXML private DatePicker dTra;
    @FXML private Label txtNgay;
    @FXML private Label txtTien;
    @FXML private TableView<ThueSach> tbKetQua;
    @FXML private Button btXNhan;
    @FXML private Button btCNhat;
    @FXML private Button btThueSachBack;
//    private int day;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.maDG2.setText(null);
        this.IDSach.setText(null);
        this.dMuon.setText(null);
        this.dHan.setText(null);
        this.dTra.setValue(null);
        this.txtNgay.setText(null);
        this.txtTien.setText(null);
        this.btXNhan.setVisible(false);
        this.btCNhat.setVisible(false);
        this.loadTable2();
        this.maDG2.textProperty().addListener(evt -> {
            try {
                if(this.maDG2.getText() == "")
                    this.tbKetQua.getItems().clear();
                this.loadTableKetQua(this.maDG2.getText());
            } catch (SQLException ex) {
                Logger.getLogger(TraSachController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        this.tbKetQua.setRowFactory(et -> {
            TableRow row = new TableRow();
            row.setOnMouseClicked(r -> {
                this.btXNhan.setVisible(true);
                this.btCNhat.setVisible(true);
                ThueSach t = this.tbKetQua.getSelectionModel().getSelectedItem();
                this.maDG2.setText(Integer.toString(t.getMaDG()));
                this.IDSach.setText(Integer.toString(t.getIdSach()));
                this.dMuon.setText(String.valueOf(t.getNgayMuon()));
                this.dHan.setText(String.valueOf(t.getHanTra()));
            });
            return row;
        });
        // TODO
    }
    private void loadTable2() {
        TableColumn colTen = new TableColumn("Doc gia");
        colTen.setCellValueFactory(new PropertyValueFactory("ten"));
        colTen.setPrefWidth(150);
        
        TableColumn colTSach = new TableColumn("Sach");
        colTSach.setCellValueFactory(new PropertyValueFactory("tenSach"));
        colTSach.setPrefWidth(200);
        
        TableColumn colTT = new TableColumn("Tinh Trang");
        colTT.setCellValueFactory(new PropertyValueFactory("tinhTrang"));
        colTT.setPrefWidth(100);
        
        TableColumn colNgayMuon = new TableColumn("Ngay muon");
        colNgayMuon.setCellValueFactory(new PropertyValueFactory("ngayMuon"));
        colNgayMuon.setPrefWidth(120);
        
        TableColumn colHan = new TableColumn("Han");
        colHan.setCellValueFactory(new PropertyValueFactory("hanTra"));
        colHan.setPrefWidth(120);
        
        TableColumn colNgayTra = new TableColumn("Ngay Tra");
        colNgayTra.setCellValueFactory(new PropertyValueFactory("ngayTra"));
        colNgayTra.setPrefWidth(120);
        
        TableColumn colNgay = new TableColumn("Ngay vuot");
        colNgay.setCellValueFactory(new PropertyValueFactory("soNgay"));
        colNgay.setPrefWidth(100);
        
        TableColumn colTien = new TableColumn("Tien Phat");
        colTien.setCellValueFactory(new PropertyValueFactory("tienPhat"));
        colTien.setPrefWidth(120);
        
        this.tbKetQua.getColumns().addAll(colTen, colTSach ,colTT, colNgayMuon, colHan, colNgayTra, colNgay, colTien);
    }
    
    private void loadTableKetQua(String kw) throws SQLException {
        if(kw != null && !kw.isEmpty()){
        QuanLyThueSach s = new QuanLyThueSach();
        List<ThueSach> ts = s.getThueSach(kw);
        this.tbKetQua.setItems(FXCollections.observableList(ts));
        }
    }
    
    public void Tinh(ActionEvent event) throws SQLException{
        if(this.dTra.getValue() == null)
            Utils.getBox("Vui lòng chọn giá trị ngày trả để thực hiện tính toán", Alert.AlertType.INFORMATION).show();
        else
        {
            Date dHan = Date.valueOf(this.dHan.getText());
            Date dTra = Date.valueOf(this.dTra.getValue());
    //        if(dTra == null){
    //            Utils.getBox("Vui lòng nhập ngày trả!", Alert.AlertType.INFORMATION).show();
    //        }else{
                int days = daysBetween(dHan, dTra);
                if(days > 0){
                    this.txtNgay.setText(String.valueOf(days));
                    this.txtTien.setText(String.valueOf(days * 5000));
                }
                else
                    {
                    this.txtNgay.setText("0");
                    this.txtTien.setText("0");
                }
//        }   
        }
    }
    
    public int daysBetween(Date d1, Date d2) {
        return (int)((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
    
    public void updateHanler(ActionEvent event) throws SQLException{
        if(this.maDG2.getText() == null)
            Utils.getBox("Bạn chưa nhập mã độc giả!", Alert.AlertType.INFORMATION).show();
        else if(this.dTra.getValue() == null)
            Utils.getBox("Bạn chưa nhập ngày trả!", Alert.AlertType.INFORMATION).show();
        else
        {
            ThueSach ts = new ThueSach(Integer.parseInt(this.maDG2.getText()), Integer.parseInt(this.IDSach.getText()), Date.valueOf(this.dMuon.getText()), Date.valueOf(this.dTra.getValue()), Integer.parseInt(this.txtNgay.getText()), Integer.parseInt(this.txtTien.getText()));
            QuanLyThueSach s = new QuanLyThueSach();
            try {
                s.updThueSach(ts);
                this.tbKetQua.getItems().clear();
                this.tbKetQua.setItems(FXCollections.observableArrayList(s.getThueSach("")));
                Utils.getBox("Cập nhật thành công!!!", Alert.AlertType.INFORMATION).show();
            } catch (SQLException ex) {
                Logger.getLogger(ThemDocGiaController.class.getName()).log(Level.SEVERE, null, ex);
                Utils.getBox("Cập nhật không thành công!!!", Alert.AlertType.WARNING).show();
            }
        }
    }
    
    public void resetTraHandler(ActionEvent event){
        this.maDG2.setText(null);
        this.IDSach.setText(null);
        this.dMuon.setText(null);
        this.dHan.setText(null);
        this.dTra.setValue(null);
        this.txtNgay.setText(null);
        this.txtTien.setText(null);
        this.tbKetQua.getItems().clear();
    }

    public void ex1Handler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ThueSach.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage stage2 = (Stage) btThueSachBack.getScene().getWindow();
        stage2.close();
    }
}
