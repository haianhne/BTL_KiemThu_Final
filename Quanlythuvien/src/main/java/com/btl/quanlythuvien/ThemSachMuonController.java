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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author ACER
 */
public class ThemSachMuonController implements Initializable {
    @FXML private TextField txtmaDG1;
    @FXML private TextField txtIDSach;
    @FXML private DatePicker dMuon;
    @FXML private DatePicker dHan;
    @FXML private TableView<ThueSach> tbChiTiet;
    @FXML private Button btMuonSachBack;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.txtmaDG1.setText(null);
        this.txtIDSach.setText(null);
        this.dMuon.setValue(null);
        this.dHan.setValue(null);
        this.tbChiTiet.getSelectionModel().select(null);
        this.loadTable1();
        this.txtmaDG1.textProperty().addListener(evt -> {
            try {
                    this.loadTableChiTiet(this.txtmaDG1.getText());
            } catch (SQLException ex) {
                Logger.getLogger(ThemSachMuonController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    public void loadTable1() {
        TableColumn colMaDG = new TableColumn("Ma DG");
        colMaDG.setCellValueFactory(new PropertyValueFactory("maDG"));
        colMaDG.setPrefWidth(50);
        
        TableColumn colTen = new TableColumn("Ten doc gia");
        colTen.setCellValueFactory(new PropertyValueFactory("ten"));
        colTen.setPrefWidth(150);
        
        TableColumn colTSach = new TableColumn("Ten sach");
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
           
        this.tbChiTiet.getColumns().addAll(colMaDG, colTen, colTSach ,colTT, colNgayMuon, colHan);
    }
    
    private void loadTableChiTiet(String kw) throws SQLException {
        QuanLyThueSach s = new QuanLyThueSach();
        List<ThueSach> ts = s.getThueSach(kw);
        this.tbChiTiet.setItems(FXCollections.observableList(ts));
    }

    public void addMuonSachHandler(ActionEvent event) throws SQLException {
        if(this.txtmaDG1.getText() == null || this.txtIDSach.getText() == null 
                || this.dMuon.getValue() == null || this.dHan.getValue() == null)
        {
            Utils.getBox("Vui lòng nhập đầy đủ thông tin", Alert.AlertType.INFORMATION).show();
        }
        else if(ThemSachController.isNumeric(this.txtmaDG1.getText()) == false)
        {
            Utils.getBox("Vui lòng nhập giá trị số cho mã độc giả", Alert.AlertType.INFORMATION).show();
        }
        else if(ThemSachController.isNumeric(this.txtIDSach.getText()) == false)
        {
            Utils.getBox("Vui lòng nhập giá trị số cho mã sách(id sách)", Alert.AlertType.INFORMATION).show();
        }
        else 
        {
            QuanLyThueSach s = new QuanLyThueSach();
            int mdg = Integer.parseInt(this.txtmaDG1.getText());
            int count;
            count = s.Check(mdg);
            if(count >= 5){
                Utils.getBox("Đã vượt số sách bạn có thể mượn", Alert.AlertType.INFORMATION).show();
            }
            else {
                try {
                    ThueSach ts = new ThueSach (Integer.parseInt(this.txtmaDG1.getText()), Integer.parseInt(this.txtIDSach.getText()),"C",
                            Date.valueOf(this.dMuon.getValue()), Date.valueOf(this.dHan.getValue()));
                    s.addThueSach(ts);
                    this.tbChiTiet.getItems().clear();
                    this.tbChiTiet.setItems(FXCollections.observableArrayList(s.getThueSach("")));
                    Utils.getBox("Thêm thành công!!!", Alert.AlertType.INFORMATION).show();
                } catch (SQLException ex) {
                    Logger.getLogger(ThemSachMuonController.class.getName()).log(Level.SEVERE, null, ex);
                    Utils.getBox("Thêm không thành công!!!", Alert.AlertType.WARNING).show();
                }
            }
        }
    }
    public void resetMuonSachHandler(ActionEvent event){
        this.txtmaDG1.setText(null);
        this.txtIDSach.setText(null);
        this.dMuon.setValue(null);
        this.dHan.setValue(null);
        this.tbChiTiet.getSelectionModel().select(null);
    }
    
    public void exHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MuonSach.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage stage2 = (Stage) btMuonSachBack.getScene().getWindow();
        stage2.close();
    }
}
