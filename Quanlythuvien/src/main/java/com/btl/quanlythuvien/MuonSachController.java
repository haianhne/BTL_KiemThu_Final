/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.quanlythuvien;

import com.btl.pojo.DocGia;
import com.btl.services.QuanLyDocGia;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.Button;
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
public class MuonSachController implements Initializable {
    @FXML private TextField txtmaDG;
    @FXML private TableView<DocGia> tbCheck;
    @FXML private Label lbTBao;
    @FXML private Button btTtuc;
    @FXML private Button btThueSachBack;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.loadTable();
        this.btTtuc.setVisible(false);
        try {
            this.loadTableCheck(null);
        } catch (SQLException ex) {
            Logger.getLogger(MuonSachController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.txtmaDG.textProperty().addListener(evt -> {
            try {
                if(this.txtmaDG.getText() == ""){
                    this.tbCheck.getItems().clear();
                    this.btTtuc.setVisible(false);
                    lbTBao.setText("");
                }
                this.loadTableCheck(this.txtmaDG.getText());
            } catch (SQLException ex) {
                Logger.getLogger(MuonSachController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.tbCheck.setRowFactory(et -> {
            TableRow row = new TableRow();
            row.setOnMouseClicked(r -> {
                  
                DocGia d = this.tbCheck.getSelectionModel().getSelectedItem();
                this.txtmaDG.setText(Integer.toString(d.getMaDG()));
                Date ngay = d.getHanTheKT();
                LocalDate dnow = java.time.LocalDate.now();
                Date date = java.sql.Date.valueOf(dnow);
                String tb = "";
                if (ngay.compareTo(date) < 0){
                    tb = "Thẻ đã hết hạn sử dụng";
                    this.btTtuc.setVisible(false);
                }
                else{
                    tb = "Thẻ bình thường";
                    this.btTtuc.setVisible(true);
                }
                lbTBao.setText(tb.toString());
            });
            return row;
        });
    }
    private void loadTable() {
        TableColumn colMa = new TableColumn("Ma doc gia");
        colMa.setCellValueFactory(new PropertyValueFactory("maDG"));
        colMa.setPrefWidth(100);
        
        TableColumn colID = new TableColumn("Ten doc gia");
        colID.setCellValueFactory(new PropertyValueFactory("ten"));
        colID.setPrefWidth(200);
        
        TableColumn colHTBD = new TableColumn("Han the BD");
        colHTBD.setCellValueFactory(new PropertyValueFactory("hanTheBD"));
        colHTBD.setPrefWidth(120);
        
        TableColumn colHTKT = new TableColumn("Han the KT");
        colHTKT.setCellValueFactory(new PropertyValueFactory("hanTheKT"));
        colHTKT.setPrefWidth(120);
        
        this.tbCheck.getColumns().addAll(colMa, colID,
                colHTBD, colHTKT);
    }
    
    private void loadTableCheck(String kw) throws SQLException {
        QuanLyDocGia s = new QuanLyDocGia();
        List<DocGia> dg= s.getDocGiaID(kw);
        this.tbCheck.setItems(FXCollections.observableList(dg));
    }
    
    public void msHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ThemSachMuon.fxml"));
        
//        ThemSachMuonController controller = fxmlLoader.getController();
//        controller.setNum(5);

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
   public void exitHandler(ActionEvent event) throws IOException {
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
