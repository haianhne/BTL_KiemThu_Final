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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author ACER
 */
public class ThongKeController implements Initializable {
    @FXML private DatePicker dBDau;
    @FXML private DatePicker dKThuc;
    @FXML private TableView<ThueSach> tbView1;
    @FXML private TableView<ThueSach> tbView2;
    @FXML private Label lbTong;
    @FXML private Button btThueSachBack;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.loadTable1();
        this.loadTable2();
    }
    private void loadTable1(){
        TableColumn colTen = new TableColumn("Doc gia");
        colTen.setCellValueFactory(new PropertyValueFactory("ten"));
        colTen.setPrefWidth(150);
        
        TableColumn colTSach = new TableColumn("Sach");
        colTSach.setCellValueFactory(new PropertyValueFactory("tenSach"));
        colTSach.setPrefWidth(200);
        
        TableColumn colTT = new TableColumn("Tinh Trang");
        colTT.setCellValueFactory(new PropertyValueFactory("tinhTrang"));
        colTT.setPrefWidth(100);
        
        TableColumn colTien = new TableColumn("Tien Phat");
        colTien.setCellValueFactory(new PropertyValueFactory("tienPhat"));
        colTien.setPrefWidth(100);
        
        this.tbView1.getColumns().addAll(colTen, colTSach ,colTT, colTien);
    }
    private void loadTable2(){
        TableColumn colTSach = new TableColumn("Sach");
        colTSach.setCellValueFactory(new PropertyValueFactory("tenSach"));
        colTSach.setPrefWidth(150);
        
        TableColumn colSL = new TableColumn("SL");
        colSL.setCellValueFactory(new PropertyValueFactory("soLuong"));
        colSL.setPrefWidth(50);
        
        this.tbView2.getColumns().addAll(colTSach, colSL);
    }
    
    public void HienThi(ActionEvent event) throws SQLException{
        if(this.dBDau.getValue() == null || this.dKThuc.getValue() == null)
            Utils.getBox("Bạn chưa nhập ngày bắt đầu hoặc ngày kết thúc!", Alert.AlertType.INFORMATION).show();
        else if(this.dKThuc.getValue().isBefore(this.dBDau.getValue()))
        {
            Utils.getBox("Ngày kết thúc phải bằng hoặc sau ngày bắt đầu", Alert.AlertType.INFORMATION).show();
        }
        else
        {
            Utils.getBox("Thành công", Alert.AlertType.INFORMATION).show();
            Date dBD = Date.valueOf(this.dBDau.getValue());
            Date dKT = Date.valueOf(this.dKThuc.getValue());
            QuanLyThueSach s = new QuanLyThueSach();

            List<ThueSach> ts = s.getMuonSach(dBD, dKT);
            this.tbView1.setItems(FXCollections.observableList(ts));
            int kq;
            kq = s.TienPhat(dBD, dKT);
            this.lbTong.setText(String.valueOf(kq));

            List<ThueSach> sl = s.SoLuong(dBD, dKT);
            this.tbView2.setItems(FXCollections.observableList(sl));
        }
    }
    public void extHandler(ActionEvent event) throws IOException {
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
