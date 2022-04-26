/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.quanlythuvien;


import com.btl.services.QuanLyBoPhan;
import com.btl.services.QuanLyDoiTuong;
import com.btl.services.QuanLyDocGia;
import com.btl.pojo.DoiTuong;
import com.btl.pojo.BoPhan;
import com.btl.pojo.DocGia;
import com.btl.conf.Utils;
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
public class SuaDocGiaController implements Initializable {
    @FXML private TextField txtHo;
    @FXML private TextField txtTen;
    @FXML private TextField txtGT;
    @FXML private DatePicker dpNS;
    @FXML private ComboBox<DoiTuong> cbDT;
    @FXML private ComboBox<BoPhan> cbBP;
    @FXML private DatePicker dpBD;
    @FXML private DatePicker dpKT;
    @FXML private TextField txtEmail;
    @FXML private TextField txtDC;
    @FXML private TextField txtSdt;
    @FXML private Button btDGBack;
    @FXML private TableView<DocGia> twDocGia;
    @FXML private Label maDG;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.maDG.setText(null);
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
        
        this.loadTableColumns();
        try {
            this.loadTableData(null);
        } catch (SQLException ex) {
            Logger.getLogger(SuaDocGiaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        QuanLyDoiTuong dt = new QuanLyDoiTuong();
        List<DoiTuong> ListDT;
        ListDT = dt.getDoiTuong();
        this.cbDT.setItems(FXCollections.observableList(ListDT));
        
        QuanLyBoPhan bp = new QuanLyBoPhan();
        List<BoPhan> ListBP;
        ListBP = bp.getBoPhan();
        this.cbBP.setItems(FXCollections.observableList(ListBP));
        
        this.twDocGia.setRowFactory(et -> {
            TableRow row = new TableRow();
            row.setOnMouseClicked(r -> {
                DocGia dg = this.twDocGia.getSelectionModel().getSelectedItem();
                this.maDG.setText(Integer.toString(dg.getMaDG()));
                this.txtHo.setText(String.valueOf(dg.getHo()));
                this.txtTen.setText(String.valueOf(dg.getTen()));
                this.txtGT.setText(String.valueOf(dg.getGioiTinh()));
                this.txtEmail.setText(String.valueOf(dg.getEmail()));
                this.txtDC.setText(String.valueOf(dg.getDiaChi()));
                this.txtSdt.setText(String.valueOf(dg.getSdt()));
            });
            return row;
        });
    }    
    private void loadTableColumns() {
        TableColumn colId = new TableColumn("Ma doc gia");
        colId.setCellValueFactory(new PropertyValueFactory("maDG"));
        colId.setPrefWidth(100);
        
        TableColumn colHo = new TableColumn("Ho doc gia");
        colHo.setCellValueFactory(new PropertyValueFactory("Ho"));
        colHo.setPrefWidth(200);
        
        TableColumn colTen = new TableColumn("Ten doc gia");
        colTen.setCellValueFactory(new PropertyValueFactory("Ten"));
        colTen.setPrefWidth(200);
        
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
        this.twDocGia.getColumns().addAll(colId, colHo, colTen ,colGT, colNgaySinh, colDoiTuong, colBP,
                colHTBD, colHTKT, colEmail, colDC, colSDT);
    }
    
    private void loadTableData(String kw) throws SQLException {
        QuanLyDocGia s = new QuanLyDocGia();
        List<DocGia> dg= s.getDocGia2(kw);
//        System.out.println(dg.size());
        this.twDocGia.setItems(FXCollections.observableList(dg));
    }
    
    public void updateHanler(ActionEvent event) throws SQLException{
        if(this.txtHo.getText() == null || this.txtTen.getText() == null || this.txtGT.getText() == null
           || this.dpNS.getValue() == null || this.cbDT.getSelectionModel().isEmpty() || this.cbBP.getSelectionModel().isEmpty()
           || this.dpBD.getValue() == null || this.dpKT.getValue() == null || this.txtEmail.getText() == null
           || this.txtDC.getText() == null || this.txtSdt.getText() == null)
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
            DocGia q = new DocGia(Integer.parseInt(this.maDG.getText()), this.txtHo.getText(), this.txtTen.getText(), this.txtGT.getText(),
                    Date.valueOf(this.dpNS.getValue()),
                    this.cbDT.getSelectionModel().getSelectedItem().getMaDT(),
                    this.cbBP.getSelectionModel().getSelectedItem().getMaBP(), 
                    Date.valueOf(this.dpBD.getValue()), Date.valueOf(this.dpKT.getValue()),
                    this.txtEmail.getText(),
                    this.txtDC.getText(), this.txtSdt.getText());

            QuanLyDocGia s = new QuanLyDocGia();

            try {
                s.updateDG(q);
                Utils.getBox("Cập nhật thành công!!!", Alert.AlertType.INFORMATION).show();
                this.loadTableData(null);
            } catch (SQLException ex) {
                Logger.getLogger(ThemDocGiaController.class.getName()).log(Level.SEVERE, null, ex);
                Utils.getBox("Cập nhật không thành công!!!", Alert.AlertType.WARNING).show();
            }
        }
    }
    
    public void resetDGHandler(ActionEvent event){
        this.maDG.setText(null);
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
