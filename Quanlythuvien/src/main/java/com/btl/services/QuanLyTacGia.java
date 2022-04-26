/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.services;

import com.btl.conf.jdbcUtils;
import com.btl.pojo.TacGia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class QuanLyTacGia {
    public void addTG(TacGia tg) throws SQLException {
        
        try (Connection conn = jdbcUtils.getConn()) {
            conn.setAutoCommit(false);

            PreparedStatement stm1 = conn.prepareStatement("INSERT INTO tacgia(idSach, tentg) VALUES(?,?)");
            stm1.setInt(1, tg.getIdSach());
            stm1.setString(2, tg.getTenTacGia());
            stm1.executeUpdate();
            conn.commit();
        }
    }
    public List<TacGia> getTG(String kw) throws SQLException {
        List<TacGia> kqTG = new ArrayList<>();
        try(Connection connect = jdbcUtils.getConn()){
            String sql = "SELECT idSach, tentg FROM tacgia";
            if (kw != null && !kw.isEmpty())
                sql += " WHERE idSach = ?";
            
            PreparedStatement stm = connect.prepareStatement(sql);
            if (kw != null && !kw.isEmpty())
                stm.setString(1, kw);
            
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                int maSach = rs.getInt("idsach");
                String tentg = rs.getString("tentg");
                TacGia tg = new TacGia(maSach, tentg);
                kqTG.add(tg);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySach.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kqTG;
    }
    public void delTG(int maSach) throws SQLException{
        try (Connection cnn = jdbcUtils.getConn()) {
            String sql = "DELETE FROM tacgia WHERE idSach = ?";
            
            PreparedStatement stm2 = cnn.prepareStatement(sql);
            stm2.setInt(1, maSach);
            stm2.execute();  
            
        }
    }
}
