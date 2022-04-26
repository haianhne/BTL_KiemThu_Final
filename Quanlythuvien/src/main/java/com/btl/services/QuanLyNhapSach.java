/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.services;

import com.btl.conf.jdbcUtils;
import com.btl.pojo.NhapSach;
import java.sql.Connection;
import java.sql.Date;
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
public class QuanLyNhapSach {
    public List<NhapSach> getNhapSach () throws SQLException{
        List<NhapSach> nhapSach = new ArrayList<>();
        try(Connection connect = jdbcUtils.getConn()){
            Statement stm = connect.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM nhapsach");
            
            while(rs.next())
            {
                int id = rs.getInt("idsach");
                Date ngay = rs.getDate("ngaynhapsach");
                int soLuong = rs.getInt("SoLuong");
                nhapSach.add(new NhapSach(id,ngay,soLuong));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyNhapSach.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nhapSach;
    }
}
