package com.btl.quanlythuvien;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import com.btl.conf.Utils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import com.btl.pojo.DocGia;
import com.btl.services.QuanLyDocGia;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Acer
 */
public class DocGiaController implements Initializable {
    @FXML private TableView<DocGia> tbDocGia;
    @FXML private TextField txtKeyword;

    /**
     * Initializes the controller class.
     */
     @FXML private Button thoat;
     @FXML private Button btDG;
     @FXML private Button btSuaDG;
     @FXML private Button btTrangChuBack;
//    /**
//     * Initializes the controller class.
//     */
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//         TODO
//        QLDoiTuong s = new QLDoiTuong();
//        List<DoiTuong> cates = s.getDoiTuong();
//        
//        this.cbDoiTuong.setItems(FXCollections.observableList(cates));
        
        this.loadTableColumns();
        try {
            this.loadTableData(null);
        } catch (SQLException ex) {
            Logger.getLogger(DocGiaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.txtKeyword.textProperty().addListener(evt -> {
            try {
                this.loadTableData(this.txtKeyword.getText());
            } catch (SQLException ex) {
                Logger.getLogger(DocGiaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
    private void loadTableColumns() {
        TableColumn colId = new TableColumn("Ma doc gia");
        colId.setCellValueFactory(new PropertyValueFactory("maDG"));
        colId.setPrefWidth(100);
        
        TableColumn colName = new TableColumn("Ten doc gia");
        colName.setCellValueFactory(new PropertyValueFactory("ten"));
        colName.setPrefWidth(200);
        
        TableColumn colGT = new TableColumn("Gioi Tinh");
        colGT.setCellValueFactory(new PropertyValueFactory("gioiTinh"));
        colGT.setPrefWidth(100);
        
        TableColumn colNgaySinh = new TableColumn("Ngay sinh");
        colNgaySinh.setCellValueFactory(new PropertyValueFactory("ngaySinh"));
        colNgaySinh.setPrefWidth(200);
        
        TableColumn colDoiTuong = new TableColumn("Doi tuong");
        colDoiTuong.setCellValueFactory(new PropertyValueFactory("doiTuong"));
        colDoiTuong.setPrefWidth(150);
        
        TableColumn colBP = new TableColumn("Bo Phan");
        colBP.setCellValueFactory(new PropertyValueFactory("boPhan"));
        colBP.setPrefWidth(150);
        
        TableColumn colHTBD = new TableColumn("Han the BD");
        colHTBD.setCellValueFactory(new PropertyValueFactory("hanTheBD"));
        colHTBD.setPrefWidth(200);
        
        TableColumn colHTKT = new TableColumn("Han the KT");
        colHTKT.setCellValueFactory(new PropertyValueFactory("hanTheKT"));
        colHTKT.setPrefWidth(200);
        
        TableColumn colEmail = new TableColumn("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        colEmail.setPrefWidth(200);
        
        TableColumn colDC = new TableColumn("Dia chi");
        colDC.setCellValueFactory(new PropertyValueFactory("diaChi"));
        colDC.setPrefWidth(200);
        
        TableColumn colSDT = new TableColumn("Sdt");
        colSDT.setCellValueFactory(new PropertyValueFactory("sdt"));
        colSDT.setPrefWidth(100);
        this.tbDocGia.getColumns().addAll(colId, colName ,colGT, colNgaySinh, colDoiTuong, colBP,
                colHTBD, colHTKT, colEmail, colDC, colSDT);
    }
    
    private void loadTableData(String kw) throws SQLException {
        QuanLyDocGia s = new QuanLyDocGia();
        List<DocGia> dg= s.getDocGia(kw);
//        System.out.println(dg.size());
        this.tbDocGia.setItems(FXCollections.observableList(dg));
    }
    public void addHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ThemDocGia.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Them doc gia");
            stage.show();
            Stage stage2 = (Stage) btDG.getScene().getWindow();
            stage2.close();
    }
     public void delHandler(ActionEvent event) throws IOException, SQLException {
        TextInputDialog diag = new TextInputDialog();
        diag.setHeaderText("nhập maDG muốn xoá: ");
        Optional<String> ans = diag.showAndWait();
        if (ans.isPresent()) {
            int id = Integer.parseInt(ans.get());
            QuanLyDocGia s = new QuanLyDocGia();
          
            try {
                s.delDG(id);
                Utils.getBox("Xoá thành công!!!", Alert.AlertType.INFORMATION).show();
                this.loadTableData(null);
            } catch (SQLException ex) {
                Logger.getLogger(ThemDocGiaController.class.getName()).log(Level.SEVERE, null, ex);
                Utils.getBox("xoá không thành công!!!", Alert.AlertType.WARNING).show();
            }
        }
    }
    public void UDDGHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("SuaDocGia.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Sua doc gia");
            stage.show();
            Stage stage2 = (Stage) btSuaDG.getScene().getWindow();
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
