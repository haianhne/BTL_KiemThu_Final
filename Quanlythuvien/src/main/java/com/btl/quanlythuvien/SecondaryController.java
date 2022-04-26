/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.quanlythuvien;

import java.io.IOException;
import javafx.fxml.FXML;

/**
 *
 * @author ACER
 */
public class SecondaryController {
     @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}
