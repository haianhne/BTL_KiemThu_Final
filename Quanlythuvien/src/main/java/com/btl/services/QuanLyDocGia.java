/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.services;

import com.btl.conf.jdbcUtils;
import com.btl.pojo.DocGia;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class QuanLyDocGia {
    public List<DocGia> getDocGia(String kw) throws SQLException {
         try (Connection cnn = jdbcUtils.getConn()) {
            String sql = "SELECT maDG, CONCAT(Ho,' ',Ten) AS hoTen,GioiTinh,NgaySinh,DoiTuong,BoPhan,HanTheBD,HanTheKT,Email,DiaChi,SDT FROM ((docgia INNER JOIN doituong ON doituong.iddoituong  = docgia.maDoiTuong) INNER JOIN bophan ON bophan.idbophan  = docgia.maBoPhan)";
            if (kw != null && !kw.isEmpty())
                sql += " WHERE ten like concat('%', ?, '%')";
            
            PreparedStatement stm = cnn.prepareStatement(sql);
            if (kw != null && !kw.isEmpty())
                stm.setString(1, kw);
            
            ResultSet rs = stm.executeQuery();
 
            List<DocGia> kq = new ArrayList<>();
            
            while (rs.next()) {
            int maDG = rs.getInt("maDG");
            String ten = rs.getString("hoTen");
            String gioiTinh = rs.getString("GioiTinh");
            Date ngaySinh = rs.getDate("NgaySinh");
            String doiTuong = rs.getString("DoiTuong");
            String boPhan = rs.getString("BoPhan");
            Date hanTheBD = rs.getDate("HanTheBD");
            Date hanTheKT = rs.getDate("HanTheKT");
            String email = rs.getString("Email");
            String diaChi = rs.getString("DiaChi");
            String sdt = rs.getString("SDT");

            DocGia c = new DocGia(maDG,ten,gioiTinh,ngaySinh,doiTuong,boPhan,hanTheBD,hanTheKT,email,diaChi,sdt);
            kq.add(c);
            }

            return kq;
        }
    }
    public List<DocGia> getDocGia2(String kw) throws SQLException {
         try (Connection cnn = jdbcUtils.getConn()) {
            String sql = "SELECT maDG,Ho,Ten,GioiTinh,NgaySinh,DoiTuong,BoPhan,HanTheBD,HanTheKT,Email,DiaChi,SDT FROM ((docgia INNER JOIN doituong ON doituong.iddoituong  = docgia.maDoiTuong) INNER JOIN bophan ON bophan.idbophan  = docgia.maBoPhan)";
            if (kw != null && !kw.isEmpty())
                sql += " WHERE ten like concat('%', ?, '%')";
            
            PreparedStatement stm = cnn.prepareStatement(sql);
            if (kw != null && !kw.isEmpty())
                stm.setString(1, kw);
            
            ResultSet rs = stm.executeQuery();
 
            List<DocGia> kq = new ArrayList<>();
            
            while (rs.next()) {
            int maDG = rs.getInt("maDG");
            String ho = rs.getString("Ho");
            String ten = rs.getString("Ten");
            String gioiTinh = rs.getString("GioiTinh");
            Date ngaySinh = rs.getDate("NgaySinh");
            String doiTuong = rs.getString("DoiTuong");
            String boPhan = rs.getString("BoPhan");
            Date hanTheBD = rs.getDate("HanTheBD");
            Date hanTheKT = rs.getDate("HanTheKT");
            String email = rs.getString("Email");
            String diaChi = rs.getString("DiaChi");
            String sdt = rs.getString("SDT");

            DocGia c = new DocGia(maDG,ho,ten,gioiTinh,ngaySinh,doiTuong,boPhan,hanTheBD,hanTheKT,email,diaChi,sdt);
            kq.add(c);
            }

            return kq;
        }
    }
    public List<DocGia> getDocGiaID(String kw) throws SQLException {
         try (Connection cnn = jdbcUtils.getConn()) {
            String sql = "SELECT maDG, CONCAT(Ho,' ',Ten) AS hoTen,GioiTinh,NgaySinh,DoiTuong,BoPhan,HanTheBD,HanTheKT,Email,DiaChi,SDT FROM ((docgia INNER JOIN doituong ON doituong.iddoituong  = docgia.maDoiTuong) INNER JOIN bophan ON bophan.idbophan  = docgia.maBoPhan)";
            if (kw != null && !kw.isEmpty())
                sql += " WHERE maDG like concat(?, '%')";
            
            PreparedStatement stm = cnn.prepareStatement(sql);
            if (kw != null && !kw.isEmpty())
                stm.setString(1, kw);
            
            ResultSet rs = stm.executeQuery();
 
            List<DocGia> kq = new ArrayList<>();
            
            while (rs.next()) {
            int maDG = rs.getInt("maDG");
            String ten = rs.getString("hoTen");
            String gioiTinh = rs.getString("GioiTinh");
            Date ngaySinh = rs.getDate("NgaySinh");
            String doiTuong = rs.getString("DoiTuong");
            String boPhan = rs.getString("BoPhan");
            Date hanTheBD = rs.getDate("HanTheBD");
            Date hanTheKT = rs.getDate("HanTheKT");
            String email = rs.getString("Email");
            String diaChi = rs.getString("DiaChi");
            String sdt = rs.getString("SDT");

            DocGia c = new DocGia(maDG,ten,gioiTinh,ngaySinh,doiTuong,boPhan,hanTheBD,hanTheKT,email,diaChi,sdt);
            kq.add(c);
            }
            return kq;
        }
    }
    
    public DocGia get1DocGia(String kw) throws SQLException {
        DocGia c = null;
        try(Connection connect = jdbcUtils.getConn()){
            String sql = "SELECT * From docgia WHERE SDT = ?";
            
            PreparedStatement stm = connect.prepareStatement(sql);
            stm.setString(1, kw);
            ResultSet rs = stm.executeQuery();
            if(!rs.next())
                return null;
            else{
            int maDG = rs.getInt("maDG");
            String ten = rs.getString("Ten");
            String ho = rs.getString("Ho");
            String gioiTinh = rs.getString("GioiTinh");
            Date ngaySinh = rs.getDate("NgaySinh");
            int doiTuong = rs.getInt("MaDoiTuong");
            int boPhan = rs.getInt("MaBoPhan");
            Date hanTheBD = rs.getDate("HanTheBD");
            Date hanTheKT = rs.getDate("HanTheKT");
            String email = rs.getString("Email");
            String diaChi = rs.getString("DiaChi");
            String sdt = rs.getString("SDT");
            c = new DocGia(maDG,ho, ten,gioiTinh,ngaySinh,doiTuong,boPhan,hanTheBD,hanTheKT,email,diaChi,sdt);
            }
        }
        return c;
    }
    
    public void addDG(DocGia d) throws SQLException {
        
            try (Connection conn = jdbcUtils.getConn()) {
                conn.setAutoCommit(false);
                
                PreparedStatement stm1 = conn.prepareStatement("INSERT INTO docgia(Ho,Ten,GioiTinh,NgaySinh,MaDoiTuong,MaBoPhan,HanTheBD,HanTheKT,Email,DiaChi,SDT) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
                stm1.setString(1, d.getHo());
                stm1.setString(2, d.getTen());
                stm1.setString(3, d.getGioiTinh());
                stm1.setDate(4, d.getNgaySinh());
                stm1.setInt(5, d.getMaDoiTuong());
                stm1.setInt(6, d.getMaBoPhan());
                stm1.setDate(7, d.getHanTheBD());
                stm1.setDate(8, d.getHanTheKT());
                stm1.setString(9, d.getEmail());
                stm1.setString(10, d.getDiaChi());
                stm1.setString(11, d.getSdt());
                stm1.executeUpdate();
                conn.commit();
            }
        }
    
    public void delDG(int maDG) throws SQLException{
        try (Connection cnn = jdbcUtils.getConn()) {
            String sql = "DELETE FROM docgia WHERE (maDG = ?);";
            
            PreparedStatement stm2 = cnn.prepareStatement(sql);
            stm2.setInt(1, maDG);
            stm2.execute();
        }
    }
    
    public Date CheckThe(String kw) throws SQLException {
        Date hanTheKT;
        try (Connection cnn = jdbcUtils.getConn()) {
          String sql = "SELECT HanTheKT FROM docgia"; 
            if (kw != null && !kw.isEmpty())
                sql += "WHERE maDG = ?";
            PreparedStatement stm = cnn.prepareStatement(sql);
            if (kw != null && !kw.isEmpty())
                stm.setString(1, kw);
            
            ResultSet rs = stm.executeQuery();
            rs.last();
            hanTheKT = rs.getDate("HanTheKT");
            rs.beforeFirst();
        }
        return hanTheKT;
    }
    
    public void updateDG(DocGia dg) throws SQLException {
            try (Connection conn = jdbcUtils.getConn()) {
                conn.setAutoCommit(false);
                PreparedStatement stm2 = conn.prepareStatement("UPDATE docgia Set Ho = ?, Ten = ?, GioiTinh =?, NgaySinh =?, MaDoiTuong = ?," + "MaBoPhan = ?,HanTheBD = ?,HanTheKT =?,Email= ?,DiaChi= ?,SDT =? WHERE maDG = ?");
                stm2.setString(1, dg.getHo());
                stm2.setString(2, dg.getTen());
                stm2.setString(3, dg.getGioiTinh());
                stm2.setDate(4, dg.getNgaySinh());
                stm2.setInt(5, dg.getMaDoiTuong());
                stm2.setInt(6, dg.getMaBoPhan());
                stm2.setDate(7, dg.getHanTheBD());
                stm2.setDate(8, dg.getHanTheKT());
                stm2.setString(9, dg.getEmail());
                stm2.setString(10,dg.getDiaChi());
                stm2.setString(11, dg.getSdt());
                stm2.setInt(12, dg.getMaDG());
                stm2.executeUpdate();
                conn.commit();
        }
    }
}
