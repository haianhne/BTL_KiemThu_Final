/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.Sach;

import com.btl.docgia.DocGiaTester;
import com.btl.pojo.ThueSach;
import com.btl.services.QuanLyThueSach;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Acer
 */
public class MuonSachTester {
    QuanLyThueSach qlTS = new QuanLyThueSach();
    @Test
   public void addMuonSachTest() throws SQLException
   {
        ThueSach ts = new ThueSach();
        ts.setMaDG(1);
        ts.setIdSach(1);
        ts.setNgayMuon(java.sql.Date.valueOf(LocalDate.now()));
        ts.setHan(java.sql.Date.valueOf(LocalDate.now()));
        int a = qlTS.getThueSach(null).size();
        qlTS.addThueSach(ts);
        Assertions.assertEquals(a + 1, qlTS.getThueSach(null).size());
   }
    @Test 
    public void TestCheckInvlid() {
       int count;
       try {
           count = qlTS.Check(3);
           Assertions.assertNotEquals(1, count);
       } catch (SQLException ex) {
           Logger.getLogger(DocGiaTester.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
   
    @Test 
    public void TestCheckValid() {
       int count;
       try {
           count = qlTS.Check(3);
           Assertions.assertEquals(5, count);
       } catch (SQLException ex) {
           Logger.getLogger(DocGiaTester.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
}
