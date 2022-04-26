/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.docgia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.btl.conf.jdbcUtils;
import com.btl.pojo.DocGia;
import com.btl.services.QuanLyDocGia;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Acer
 */
public class DocGiaTester {
   QuanLyDocGia qlDG = new QuanLyDocGia();
   @Test
   public void testQuantity() throws SQLException{
       Connection conn = jdbcUtils.getConn ();
       Statement stm = conn.createStatement();
       ResultSet rs = stm.executeQuery("SELECT maDG, CONCAT(Ho,' ',Ten) AS hoTen,DoiTuong FROM docgia INNER JOIN doituong ON doituong.iddoituong  = docgia.maDoiTuong;");
       List<String> kq = new ArrayList<>();
       while(rs.next()){
           int id = rs.getInt("maDG");
           System.out.println(id);
           String tenDG = rs.getString("hoTen");
           System.out.println(tenDG);
           String maDT = rs.getString("DoiTuong");
           System.out.println(maDT);
       }
       Set<String> kq2 = new HashSet<>(kq);
       Assertions.assertEquals(kq.size(), kq2.size());
       if(conn != null)
           conn.close();
   }
   @Test
   //Them doc gia
    public void Test1() throws SQLException{
       DocGia d = new DocGia("Nguyen","Nhan", "Nam", null , 1, 4, java.sql.Date.valueOf(LocalDate.now()), java.sql.Date.valueOf(LocalDate.now()), "sda3@gmail.com", "342 Lý ", "01267672394");
       int a = qlDG.getDocGia(null).size();
       qlDG.addDG(d);
       Assertions.assertNotNull(qlDG.get1DocGia(d.getSdt()));
       Assertions.assertEquals(a + 1, qlDG.getDocGia(null).size());
    }
    @Test
    public void Test2() throws SQLException{
       DocGia d = qlDG.get1DocGia("01267672394");
       d.setEmail("TanNguyen321@gmail.com");
       qlDG.updateDG(d);
       Assertions.assertEquals("TanNguyen321@gmail.com", qlDG.get1DocGia("01267672394").getEmail());
    }
    @Test
    public void Test3() throws SQLException{
       DocGia d = qlDG.get1DocGia("01267672394");
       int a = qlDG.getDocGia(null).size();
       Assertions.assertNotNull(qlDG.get1DocGia("01267672394"));
       qlDG.delDG(d.getMaDG()); 
       Assertions.assertNull(qlDG.get1DocGia("01267672394"));
       Assertions.assertEquals(a - 1, qlDG.getDocGia(null).size());
    }
    @Test 
    public void TestDGbySDTInvlid() {
       DocGia dg;
       try {
           dg = qlDG.get1DocGia("05644353345");
           Assertions.assertNull(dg);
       } catch (SQLException ex) {
           Logger.getLogger(DocGiaTester.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
   
    @Test 
    public void TestDGbySDTValid() {
       DocGia dg;
       try {
           dg = qlDG.get1DocGia("0943454345");
           Assertions.assertEquals(dg.getTen(),"N");
           Assertions.assertEquals(dg.getEmail(),"fsdfds@ou.edu.vn" );
           Assertions.assertEquals("Hà Nội", dg.getDiaChi());
       } catch (SQLException ex) {
           Logger.getLogger(DocGiaTester.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
}
