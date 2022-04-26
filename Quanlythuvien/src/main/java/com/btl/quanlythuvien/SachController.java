/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.quanlythuvien;

import com.btl.services.QuanLySach;
import com.btl.pojo.Sach;
import com.btl.conf.Utils;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author ACER
 */
public class SachController implements Initializable {
    @FXML private TableView<Sach> twSach;
    @FXML private Button thoat;
    @FXML private Button btThemS;
    @FXML private Button btSuaS;
    @FXML private Button btTimKiemS;
    @FXML private Button btTrangChuBack;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.loadTBSach();
        try {
            this.loadTableData(null);
        } catch (SQLException ex) {
            Logger.getLogger(SachController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        List<Sach> s= ql.getSach(kw);
        this.twSach.setItems(FXCollections.observableList(s));
    }
    
    public void addHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ThemSach.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Them sach");
            stage.show();
            Stage stage2 = (Stage) btThemS.getScene().getWindow();
            stage2.close();
    }
    public void delHandler(ActionEvent event) throws IOException, SQLException {
        
        TextInputDialog diag = new TextInputDialog();
        diag.setHeaderText("nhập mã sách muốn xoá: ");
        Optional<String> ans = diag.showAndWait();
        if (ans.isPresent()) {
            int id = Integer.parseInt(ans.get());
            QuanLySach s = new QuanLySach();
            try {
                s.delSach(id);
                Utils.getBox("Xoá thành công!!!", Alert.AlertType.INFORMATION).show();
                this.loadTableData(null);
            } catch (SQLException ex) {
                Logger.getLogger(SachController.class.getName()).log(Level.SEVERE, null, ex);
                Utils.getBox("xoá không thành công!!!", Alert.AlertType.WARNING).show();
            }
        }
    }
    public void SearchHandler(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("TimKiem.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Tim kiem");
            stage.show();
            Stage stage2 = (Stage) btTimKiemS.getScene().getWindow();
            stage2.close();
    }
    public void UpdateHandler(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("CapNhatSach.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Cap nhat");
            stage.show();
            Stage stage2 = (Stage) btSuaS.getScene().getWindow();
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
