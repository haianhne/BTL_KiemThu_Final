/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/module-info.java to edit this template
 */

module com.btl.quanlythuvien {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    
    opens com.btl.quanlythuvien to javafx.fxml;
    exports com.btl.quanlythuvien;
    exports com.btl.pojo;
}
