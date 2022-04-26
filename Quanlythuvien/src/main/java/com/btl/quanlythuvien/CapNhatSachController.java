/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.quanlythuvien;

import com.btl.conf.Utils;
import com.btl.pojo.DanhMuc;
import com.btl.pojo.Sach;
import com.btl.pojo.ViTri;
import com.btl.services.QuanLyDanhMuc;
import com.btl.services.QuanLySach;
import com.btl.services.QuanLyViTri;
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
public class CapNhatSachController implements Initializable {
    @FXML private TableView<Sach> twSach;
    @FXML private TextField txtTenS;
    @FXML private TextField txtMoTa;
    @FXML private TextField txtNXB;
    @FXML private TextField txtNoiXB;
    @FXML private ComboBox<DanhMuc> cbDanhMuc;
    @FXML private ComboBox<ViTri> cbViTri;
    @FXML private TextField txtSL;
    @FXML private Button btSach;
    @FXML private Button btSuaTG;
    @FXML private Label lbMaSach;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.lbMaSach.setText(null);
        this.txtTenS.setText(null);
        this.txtMoTa.setText(null);
        this.txtNXB.setText(null);
        this.txtNoiXB.setText(null);
        this.cbDanhMuc.getSelectionModel().select(null);
        this.txtSL.setText(null);
        this.cbViTri.getSelectionModel().select(null);
        this.loadTBSach();
        try {
            this.loadTableData(null);
        } catch (SQLException ex) {
            Logger.getLogger(CapNhatSachController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        QuanLyDanhMuc dm = new QuanLyDanhMuc();
        List<DanhMuc> cates;
        try {
            cates = dm.getDanhMuc();
            this.cbDanhMuc.setItems(FXCollections.observableList(cates));
        } catch (SQLException ex) {
            Logger.getLogger(CapNhatSachController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        QuanLyViTri b = new QuanLyViTri();
        List<ViTri> cate;
        try {
            cate = b.getViTri();
            this.cbViTri.setItems(FXCollections.observableList(cate));
        } catch (SQLException ex) {
            Logger.getLogger(CapNhatSachController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.txtTenS.textProperty().addListener(evt -> {
            try{
                this.loadTableData(this.txtTenS.getText());
            } catch (SQLException ex) {
                Logger.getLogger(CapNhatSachController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.twSach.setRowFactory(et -> {
            TableRow row = new TableRow();
            row.setOnMouseClicked(r -> {
                Sach t = this.twSach.getSelectionModel().getSelectedItem();
                this.lbMaSach.setText(Integer.toString(t.getMaSach()));
                this.txtTenS.setText(String.valueOf(t.getTenSach()));
                this.txtMoTa.setText(String.valueOf(t.getMoTa()));
                this.txtNXB.setText(String.valueOf(t.getNamXuatBan()));
                this.txtNoiXB.setText(String.valueOf(t.getNoiXuatBan()));
                this.txtSL.setText(String.valueOf(t.getSoLuong()));
            });
            return row;
        });
        }
    private void loadTBSach()
    {
        TableColumn colMaS = new TableColumn("Ma sach");
        colMaS.setCellValueFactory(new PropertyValueFactory("maSach"));
        colMaS.setPrefWidth(50);
        
        TableColumn colTenS = new TableColumn("Ten Sach");
        colTenS.setCellValueFactory(new PropertyValueFactory("tenSach"));
        colTenS.setPrefWidth(150);
        
        TableColumn colTenTG = new TableColumn("Ten tac gia");
        colTenTG.setCellValueFactory(new PropertyValueFactory("tenTacGia"));
        colTenTG.setPrefWidth(150);
        
        TableColumn colMoTa = new TableColumn("Mo ta");
        colMoTa.setCellValueFactory(new PropertyValueFactory("moTa"));
        colMoTa.setPrefWidth(150);
        
        TableColumn colNamXB = new TableColumn("Nam xuat ban");
        colNamXB.setCellValueFactory(new PropertyValueFactory("namXuatBan"));
        colNamXB.setPrefWidth(150);
        
        TableColumn colNoiXB = new TableColumn("Noi xuat ban");
        colNoiXB.setCellValueFactory(new PropertyValueFactory("noiXuatBan"));
        colNoiXB.setPrefWidth(150);
        
        TableColumn colDanhM = new TableColumn("Danh muc");
        colDanhM.setCellValueFactory(new PropertyValueFactory("danhMuc"));
        colDanhM.setPrefWidth(150);
        
        TableColumn colViTri = new TableColumn("Ten ke");
        colViTri.setCellValueFactory(new PropertyValueFactory("tenKe"));
        colViTri.setPrefWidth(150);
        
        TableColumn colMaKhu = new TableColumn("Ma khu");
        colMaKhu.setCellValueFactory(new PropertyValueFactory("maKhu"));
        colMaKhu.setPrefWidth(150);
        
        TableColumn colSL = new TableColumn("So luong");
        colSL.setCellValueFactory(new PropertyValueFactory("soLuong"));
        colSL.setPrefWidth(150);
        
        this.twSach.getColumns().addAll(colMaS,colTenS,colTenTG,colMoTa,colNamXB,colNoiXB,colDanhM,colViTri,colMaKhu,colSL);
        
    }
    private void loadTableData(String kw) throws SQLException {
        QuanLySach ql = new QuanLySach();
        List<Sach> s= ql.timTenSach(kw);
        this.twSach.setItems(FXCollections.observableList(s));
    }
    private boolean checkInputNull(){
        if (this.txtTenS.getText() == null|| this.txtMoTa.getText() == null
                || this.txtNXB.getText() == null || this.txtNoiXB.getText()== null
                || this.cbDanhMuc.getSelectionModel().isEmpty() || this.cbViTri.getSelectionModel().isEmpty())
        {
            return true;
        }
        return false;
    }
    public void updateHanler(ActionEvent event) throws SQLException{
        if(checkInputNull() == true)
        {
            Utils.getBox("Vui lòng nhập đầy đủ thông tin", Alert.AlertType.INFORMATION).show();
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
            Sach s = new Sach(Integer.parseInt(this.lbMaSach.getText()),this.txtTenS.getText()
                    ,this.txtMoTa.getText(),Integer.parseInt(this.txtNXB.getText()),
                    this.txtNoiXB.getText(), this.cbDanhMuc.getSelectionModel().getSelectedItem().getMaDanhMuc(),
                    this.cbViTri.getSelectionModel().getSelectedItem().getViTriID(), Integer.parseInt(this.txtSL.getText()));
            QuanLySach qlS = new QuanLySach();
            try {
                qlS.updateSach(s);
                this.twSach.getItems().clear();
                this.twSach.setItems(FXCollections.observableArrayList(qlS.getSach("")));
                Utils.getBox("Cập nhật thành công!!!", Alert.AlertType.INFORMATION).show();
            } catch (SQLException ex) {
                Logger.getLogger(CapNhatSachController.class.getName()).log(Level.SEVERE, null, ex);
                Utils.getBox("Cập nhật không thành công!!!", Alert.AlertType.WARNING).show();
            }
        }
    }
    public void updateTGHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("CapNhatTacGia.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Cap nhat tac gia");
        stage.show();
        Stage stage2 = (Stage) btSuaTG.getScene().getWindow();
        stage2.close();
    }
    public void qlHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Sach.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage stage2 = (Stage) btSach.getScene().getWindow();
        stage2.close();
    }
    public void resetUSachHandler(ActionEvent event){
        this.lbMaSach.setText(null);
        this.txtTenS.setText(null);
        this.txtMoTa.setText(null);
        this.txtNXB.setText(null);
        this.txtNoiXB.setText(null);
        this.cbDanhMuc.getSelectionModel().select(null);
        this.txtSL.setText(null);
        this.cbViTri.getSelectionModel().select(null);
    } 
}
