/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.services;

import com.btl.conf.jdbcUtils;
import com.btl.pojo.DanhMuc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class QuanLyDanhMuc {
    public List<DanhMuc> getDanhMuc() throws SQLException{
        List<DanhMuc> dm = new ArrayList<>();
        try(Connection connect = jdbcUtils.getConn()){
            Statement stm = connect.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM danhmuc");
            
            while(rs.next())
            {
                int id = rs.getInt("iddanhmuc");
                String name = rs.getString("DanhMuc");
                dm.add(new DanhMuc(id, name));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyDanhMuc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dm;
    }
    public DanhMuc getDanhMuc2(int MaDM) throws SQLException{
        DanhMuc dm = new DanhMuc();
        try (Connection con = jdbcUtils.getConn()){
            String sql = "SELECT * FROM danhmuc WHERE iddanhmuc = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, MaDM);
            ResultSet rs = stm.executeQuery();
            dm.setMaDanhMuc(rs.getInt("iddanhmuc"));
            dm.setTenDanhMuc("DanhMuc");
        }
        return dm;
    }
}
