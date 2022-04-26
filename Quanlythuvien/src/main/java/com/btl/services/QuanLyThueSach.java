/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.services;

import com.btl.conf.jdbcUtils;
import com.btl.pojo.ThueSach;
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
public class QuanLyThueSach {
    public List<ThueSach> getThueSach(String kw) throws SQLException {
        try (Connection cnn = jdbcUtils.getConn()) {
          String sql = "SELECT muonsach.maDG, CONCAT(Ho,' ',Ten) AS hoTen, muonsach.idSach, TenSach, TinhTrang, NgayMuon, HanTra, NgayTra, SoNgay, TienPhat FROM((muonsach INNER JOIN docgia ON docgia.maDG = muonsach.maDG) INNER JOIN sach ON sach.idSach = muonsach.idSach)"; 
            if (kw != null && !kw.isEmpty())
                sql += "WHERE (muonsach.maDG = ? AND TinhTrang = 'C')";
            
            PreparedStatement stm = cnn.prepareStatement(sql);
            if (kw != null && !kw.isEmpty())
                stm.setString(1, kw);
            
            ResultSet rs = stm.executeQuery();
 
            List<ThueSach> kq = new ArrayList<>();
            
            while (rs.next()) {
            int maDG = rs.getInt("muonsach.maDG");
            String ten = rs.getString("hoTen");
            int idSach = rs.getInt("muonsach.idSach");
            String tenSach = rs.getString("TenSach");
            String tinhTrang = rs.getString("TinhTrang");
            Date ngayMuon = rs.getDate("NgayMuon");
            Date hanTra = rs.getDate("HanTra");
            Date ngayTra = rs.getDate("NgayTra");
            int soNgay = rs.getInt("SoNgay");
            int tienPhat = rs.getInt("TienPhat");

            ThueSach ts = new ThueSach(maDG, ten, idSach, tenSach, tinhTrang, ngayMuon, hanTra, ngayTra, soNgay, tienPhat);
            kq.add(ts);
            }
            return kq;
        }
    }
    public List<ThueSach> getMuonSach(Date dBD, Date dKT) throws SQLException {
        try (Connection cnn = jdbcUtils.getConn()) {
            String sql = "SELECT muonsach.maDG, CONCAT(Ho,' ',Ten) AS hoTen, muonsach.idSach, TenSach, TinhTrang, NgayMuon, HanTra, NgayTra, SoNgay, TienPhat FROM((muonsach INNER JOIN docgia ON docgia.maDG = muonsach.maDG) INNER JOIN sach ON sach.idSach = muonsach.idSach) WHERE NgayMuon BETWEEN ? AND ?";
            PreparedStatement stm = cnn.prepareStatement(sql);
            stm.setDate(1, dBD);
            stm.setDate(2, dKT);
            
            ResultSet rs = stm.executeQuery();
 
            List<ThueSach> kq = new ArrayList<>();
            while (rs.next()) {
            int maDG = rs.getInt("muonsach.maDG");
            String ten = rs.getString("hoTen");
            int idSach = rs.getInt("muonsach.idSach");
            String tenSach = rs.getString("TenSach");
            String tinhTrang = rs.getString("TinhTrang");
            Date ngayMuon = rs.getDate("NgayMuon");
            Date hanTra = rs.getDate("HanTra");
            Date ngayTra = rs.getDate("NgayTra");
            int soNgay = rs.getInt("SoNgay");
            int tienPhat = rs.getInt("TienPhat");

            ThueSach ts = new ThueSach(maDG, ten, idSach, tenSach, tinhTrang, ngayMuon, hanTra, ngayTra, soNgay, tienPhat);
            kq.add(ts);
            }
            return kq;
        }
    }
    public int TienPhat(Date dBD, Date dKT) throws SQLException {
        int count = 0;
        try (Connection cnn = jdbcUtils.getConn()) {
          PreparedStatement stm = cnn.prepareStatement("SELECT SUM(TienPhat) FROM muonsach WHERE NgayMuon BETWEEN ? AND ?");
            stm.setDate(1, dBD);
            stm.setDate(2, dKT);
            
            ResultSet rs = stm.executeQuery();
            rs.next();
            count = rs.getInt(1);
        }
        return count;
    }
    
    public List<ThueSach> SoLuong(Date dBD, Date dKT) throws SQLException {
        try (Connection cnn = jdbcUtils.getConn()) {
            PreparedStatement stm = cnn.prepareStatement("SELECT TenSach, COUNT(muonsach.maDG) AS SoLuong FROM muonsach INNER JOIN sach ON sach.idSach = muonsach.idSach WHERE NgayMuon BETWEEN ? AND ? GROUP BY TenSach;");
            stm.setDate(1, dBD);
            stm.setDate(2, dKT);
            
            ResultSet rs = stm.executeQuery();
 
            List<ThueSach> q = new ArrayList<>();
            while (rs.next()) {
               String tenSach = rs.getString("TenSach");
               int soLuong = rs.getInt("SoLuong");
               
            ThueSach ts = new ThueSach(tenSach, soLuong);
            q.add(ts);    
            }    
            return q;
        }
    }

    public int Check(int num) throws SQLException {
        int count = 0;
        try (Connection cnn = jdbcUtils.getConn()) {
            PreparedStatement stm = cnn.prepareStatement("SELECT count(TinhTrang) FROM muonsach WHERE (maDG = ? AND TinhTrang = 'C')");
            stm.setInt(1, num);
            
            ResultSet rs = stm.executeQuery();
            rs.next();
            count = rs.getInt(1);
        }
        return count;
    }
    
    public void addThueSach(ThueSach ts) throws SQLException {
            try (Connection conn = jdbcUtils.getConn()) {
                conn.setAutoCommit(false);
                PreparedStatement stm1 = conn.prepareStatement("INSERT INTO muonsach(maDG, idSach, TinhTrang, NgayMuon, HanTra) VALUES(?,?,'C',?,?)");
                stm1.setInt(1, ts.getMaDG());
                stm1.setInt(2, ts.getIdSach());
                stm1.setDate(3, ts.getNgayMuon());
                stm1.setDate(4, ts.getHanTra());
                stm1.executeUpdate();
                conn.commit();
            }
    }
    
    public void updThueSach(ThueSach ts) throws SQLException {
            try (Connection conn = jdbcUtils.getConn()) {
                conn.setAutoCommit(false);
                PreparedStatement stm2 = conn.prepareStatement("UPDATE muonsach Set TinhTrang = 'R', NgayTra = ?, SoNgay = ?, TienPhat = ? WHERE muonsach.maDG = ? AND muonsach.idSach = ? AND NgayMuon = ?");
                stm2.setDate(1, ts.getNgayTra());
                stm2.setInt(2, ts.getSoNgay());
                stm2.setInt(3, ts.getTienPhat());
                stm2.setInt(4, ts.getMaDG());
                stm2.setInt(5, ts.getIdSach());
                stm2.setDate(6, ts.getNgayMuon());
                stm2.executeUpdate();
                conn.commit();
            }
    }
}
