/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.Sach;

import com.btl.conf.jdbcUtils;
import com.btl.docgia.DocGiaTester;
import com.btl.pojo.DocGia;
import com.btl.pojo.Sach;
import com.btl.pojo.TacGia;
import com.btl.services.QuanLyDanhMuc;
import com.btl.services.QuanLyTacGia;
import com.btl.services.QuanLyViTri;
import com.btl.services.QuanLySach;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Acer
 */
public class SachTester {
    
    QuanLySach sachService = new QuanLySach();
    QuanLyTacGia tacGiaService = new QuanLyTacGia();
    QuanLyDanhMuc danhMucService = new QuanLyDanhMuc();
    QuanLyViTri viTriService = new QuanLyViTri();
    
    private static Connection conn;
   @BeforeAll
   public static void beforeAll() throws SQLException{
       conn = jdbcUtils.getConn ();
   }
   @AfterAll
   public static void afterAll() throws SQLException{
       if(conn != null)
           conn.close();
   }
   
    @Test 
   public void TestSachIDInvlid() {
       Sach s;
       try {
           s = sachService.get1Sach("8");
           Assertions.assertNull(s);
       } catch (SQLException ex) {
           Logger.getLogger(SachTester.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   @Test 
   public void TestSachIDValid() {
       Sach s;
       try {
           s = sachService.get1Sach("1");
           Assertions.assertEquals("Kiểm thử phần mềm", s.getTenSach());
           Assertions.assertEquals(2015, s.getNamXuatBan());
           Assertions.assertEquals("Tài liệu", s.getDanhMuc());
           Assertions.assertEquals("ĐH Mở HCM", s.getNoiXuatBan());
       } catch (SQLException ex) {
           Logger.getLogger(SachTester.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
           
   @Test
   public void tenSachTrung() throws SQLException{
       Statement stm = conn.createStatement();
       ResultSet rs = stm.executeQuery("SELECT * FROM sach");
       List<String> kq = new ArrayList<>();
       while(rs.next())
       {
           String tenSach = rs.getString("TenSach");
           System.out.println(tenSach);
           kq.add(tenSach);
           
       }
       Set<String> kq2 = new HashSet<>(kq);
       Assertions.assertEquals(kq.size(), kq2.size());
   }
}
