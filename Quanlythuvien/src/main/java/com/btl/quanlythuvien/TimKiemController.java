/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.quanlythuvien;

import com.btl.services.QuanLySach;
import com.btl.pojo.Sach;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author ACER
 */
public class TimKiemController implements Initializable {
    @FXML private TextField txtTim;
    @FXML private RadioButton rdTimTS;
    @FXML private RadioButton rdTimTG;
    @FXML private RadioButton rdTimNSX;
    @FXML private RadioButton rdTimDM;
    @FXML private RadioButton rdTimNamSX;
    @FXML private TableView<Sach> twSach;
    @FXML private Button thoat;
    @FXML private Button btSach;
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
    private void LoadTBData(String kw) throws SQLException
    {
        QuanLySach ql = new QuanLySach();
        if(this.rdTimTS.isSelected() == true)
                this.twSach.setItems(FXCollections.observableList(ql.timTenSach(kw)));
        if(this.rdTimTG.isSelected() == true)
                this.twSach.setItems(FXCollections.observableList(ql.timTenTG(kw)));
        if(this.rdTimNSX.isSelected() == true)
                this.twSach.setItems(FXCollections.observableList(ql.timNSX(kw)));
        if(this.rdTimNamSX.isSelected() == true)
                this.twSach.setItems(FXCollections.observableList(ql.timNamXB(kw)));
        if(this.rdTimDM.isSelected() == true)
                this.twSach.setItems(FXCollections.observableList(ql.timDanhMuc(kw)));
    }
    // Group
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.loadTBSach();
        try {
            this.LoadTBData(null);
        } catch (SQLException ex) {
            Logger.getLogger(SachController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.txtTim.textProperty().addListener(evt -> {
            try {
                this.LoadTBData(this.txtTim.getText());
            } catch (SQLException ex) {
                Logger.getLogger(TimKiemController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
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
}
