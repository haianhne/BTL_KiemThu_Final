/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.quanlythuvien;

import com.btl.conf.Utils;
import com.btl.pojo.Sach;
import com.btl.pojo.TacGia;
import com.btl.services.QuanLySach;
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
public class CapNhatTacGiaController implements Initializable {
    @FXML private TableView<Sach> twSach;
    @FXML private TextField txtUDTacGia;
    @FXML private Label maSach;
    @FXML private Label tacGiaO;
    @FXML private TextField txtUDTenSach;
    @FXML private Button btSach;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.txtUDTacGia.setText(null);
        this.maSach.setText(null);
        this.txtUDTenSach.setText(null);
        this.tacGiaO.setText(null);
        
        this.loadTBSach();
        try {
            this.loadTableData(null);
        } catch (SQLException ex) {
            Logger.getLogger(CapNhatTacGiaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.txtUDTenSach.textProperty().addListener(evt -> {
            try{
                this.loadTableData(this.txtUDTenSach.getText());
            } catch (SQLException ex) {
                Logger.getLogger(CapNhatTacGiaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        this.twSach.setRowFactory(et -> {
            TableRow row = new TableRow();
            row.setOnMouseClicked(r -> {
                Sach t = this.twSach.getSelectionModel().getSelectedItem();
                this.txtUDTenSach.setText(String.valueOf(t.getTenSach()));
                this.tacGiaO.setText(String.valueOf(t.getTenTacGia()));
                this.maSach.setText(Integer.toString(t.getMaSach()));
            });
            return row;
        });
        
    }    
    private void loadTBSach()
    {
        TableColumn colTenS = new TableColumn("Ten Sach");
        colTenS.setCellValueFactory(new PropertyValueFactory("tenSach"));
        colTenS.setPrefWidth(200);
        
        TableColumn colTenTG = new TableColumn("Ten tac gia");
        colTenTG.setCellValueFactory(new PropertyValueFactory("tenTacGia"));
        colTenTG.setPrefWidth(200);
        this.twSach.getColumns().addAll(colTenS,colTenTG);
        
    }
    private void loadTableData(String kw) throws SQLException {
        QuanLySach ql = new QuanLySach();
        List<Sach> s= ql.timTenSach(kw);
        this.twSach.setItems(FXCollections.observableList(s));
    }
    public void updateTGHanler(ActionEvent event) throws SQLException{
        if(txtUDTacGia.getText() == null||txtUDTenSach.getText() == null)
        {
            Utils.getBox("Vui lòng nhập đủ nội dung cần có", Alert.AlertType.INFORMATION).show();
        }
         else if(ThemSachController.isNumeric(this.txtUDTacGia.getText()) == true)
        {
            Utils.getBox("Vui lòng nhập giá trị chữ cho tên tác giả", Alert.AlertType.INFORMATION).show();
        }
        else if (ThemSachController.isNumeric(this.txtUDTenSach.getText()) == true)
        {
            Utils.getBox("Vui lòng nhập giá trị chữ cho tên tác giả", Alert.AlertType.INFORMATION).show();
        }
        else
        {
            TacGia tg = new TacGia(Integer.parseInt(this.maSach.getText()),this.tacGiaO.getText(), this.txtUDTacGia.getText());
            QuanLySach qlS = new QuanLySach();
            try {
                qlS.updateTG(tg);
                try {
                this.loadTableData(null);
                } catch (SQLException ex) {
                Logger.getLogger(CapNhatTacGiaController.class.getName()).log(Level.SEVERE, null, ex);
            }
                Utils.getBox("Cập nhật thành công!!!", Alert.AlertType.INFORMATION).show();   
            } catch (SQLException ex) {
                Logger.getLogger(CapNhatTacGiaController.class.getName()).log(Level.SEVERE, null, ex);
                Utils.getBox("Cập nhật không thành công!!!", Alert.AlertType.WARNING).show();
            }
        }
}
    public void resetUDTacGiaHandler(ActionEvent event){
        this.txtUDTacGia.setText(null);
        this.maSach.setText(null);
        this.txtUDTenSach.setText(null);
        this.tacGiaO.setText(null);
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
