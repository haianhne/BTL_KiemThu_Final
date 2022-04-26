/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.services;

import com.btl.conf.jdbcUtils;
import com.btl.pojo.Sach;
import com.btl.pojo.TacGia;
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
public class QuanLySach {
    public List<Sach> getSach(String kw) throws SQLException {
        List<Sach> kqS = new ArrayList<>();
        try(Connection connect = jdbcUtils.getConn()){
            String sql = "SELECT sach.idsach,TenSach ,tentg,Mota, NamXuatBan,NoiXuatBan,DanhMuc,TenKe,MaKhu,SoLuong FROM sach \n" +
                                            "INNER JOIN tacgia ON tacgia.idSach = sach.idsach\n" +
                                            "INNER JOIN danhmuc ON danhmuc.iddanhmuc = sach.MaDanhMuc INNER JOIN vitri ON vitri.idvitri = sach.MaViTri ";
            if (kw != null && !kw.isEmpty())
                sql += " WHERE sach.idSach = ?";
            
            try (PreparedStatement stm = connect.prepareStatement(sql)) {
                if (kw != null && !kw.isEmpty())
                    stm.setString(1, kw);
                
                ResultSet rs = stm.executeQuery();
                while(rs.next())
                {
                    int maSach = rs.getInt("idsach");
                    String ten = rs.getString("TenSach");
                    String tentg = rs.getString("tentg");
                    String mota = rs.getString("Mota");
                    int namXuatBan = rs.getInt("NamXuatBan");
                    String noiXuatBan = rs.getString("NoiXuatBan");
                    String tenDanhMuc = rs.getString("DanhMuc");
                    String tenKe = rs.getString("TenKe");
                    String maKhu = rs.getString("MaKhu");
                    int soLuong = rs.getInt("SoLuong");
                    Sach s = new Sach(maSach,ten, tentg ,mota , namXuatBan , noiXuatBan , tenDanhMuc ,tenKe, maKhu , soLuong );
                    kqS.add(s);
                }
            }
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySach.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kqS;
    }
    public Sach get1Sach(String kw) throws SQLException {
        Sach kqS = null;
        try(Connection connect = jdbcUtils.getConn()){
            String sql = "SELECT sach.idsach,TenSach ,tentg,Mota, NamXuatBan,NoiXuatBan,DanhMuc,TenKe,MaKhu,SoLuong FROM sach \n" +
                                            "INNER JOIN tacgia ON tacgia.idSach = sach.idsach\n" +
                                            "INNER JOIN danhmuc ON danhmuc.iddanhmuc = sach.MaDanhMuc INNER JOIN vitri ON vitri.idvitri = sach.MaViTri ";
            if (kw != null && !kw.isEmpty())
                sql += " WHERE sach.idSach = ?";
            
            PreparedStatement stm = connect.prepareStatement(sql);
            if (kw != null && !kw.isEmpty())
                stm.setString(1, kw);
            ResultSet rs = stm.executeQuery();
            if(!rs.next())
                return null;
            else{
                int maSach = rs.getInt("idsach");
                String ten = rs.getString("TenSach");
                String tentg = rs.getString("tentg");
                String mota = rs.getString("Mota");
                int namXuatBan = rs.getInt("NamXuatBan");
                String noiXuatBan = rs.getString("NoiXuatBan");
                String tenDanhMuc = rs.getString("DanhMuc");
                String tenKe = rs.getString("TenKe");
                String maKhu = rs.getString("MaKhu");
                int soLuong = rs.getInt("SoLuong");
                kqS = new Sach(maSach,ten, tentg ,mota , namXuatBan , noiXuatBan , tenDanhMuc ,tenKe, maKhu , soLuong );
            }
        }
        return kqS;
    }
    public List<Sach> sachThemTG () throws SQLException{
        List<Sach> s = new ArrayList<>();
        Connection conn;
        try {
            conn = jdbcUtils.getConn();
            
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT idSach, TenSach FROM sach");
            
            while (rs.next()) {
                int id = rs.getInt("idSach");
                String name = rs.getString("TenSach");
                s.add(new Sach(id, name));
            }
            
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySach.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
//    public void xuatThongtinSach () throws SQLException
//    {
//        List<Sach> a = this.getSach();
//        for (Sach i : a)
//        {
//            System.out.printf("=== THONG TIN SACH THU %d ===" , i.getMaSach());
//            System.out.println(i.xuatThongTinsach());
//        }
//    }
    public List<Sach> timTenSach(String kw) throws SQLException
    {
        List<Sach> timT = new ArrayList<>();
        
        try (Connection connect = jdbcUtils.getConn()) {
            String sql = "SELECT s.idSach,TenSach ,tentg,Mota, NamXuatBan,NoiXuatBan,DanhMuc,TenKe,MaKhu,s.SoLuong FROM sach s\n" +
                        "INNER JOIN tacgia t ON t.idSach = s.idSach\n" +
                        "INNER JOIN danhmuc m ON m.iddanhmuc = s.MaDanhMuc INNER JOIN vitri v ON v.idvitri = s.MaViTri ";
            if(kw != null && !kw.isEmpty())
            {
                sql += "WHERE TenSach like concat('%',?,'%')";
                
            }
            PreparedStatement stm = connect.prepareStatement(sql);
            if(kw != null && !kw.isEmpty())
                stm.setString(1, kw);
            ResultSet rs = stm.executeQuery();
             while(rs.next())
            {
                int maSach = rs.getInt("idsach");
                String ten = rs.getString("TenSach");
                String tentg = rs.getString("tentg");
                String mota = rs.getString("Mota");
                int namXuatBan = rs.getInt("NamXuatBan");
                String noiXuatBan = rs.getString("NoiXuatBan");
                String tenDanhMuc = rs.getString("DanhMuc");
                String tenKe = rs.getString("TenKe");
                String maKhu = rs.getString("MaKhu");
                int soLuong = rs.getInt("SoLuong");
                Sach s = new Sach(maSach,ten, tentg ,mota , namXuatBan , noiXuatBan , tenDanhMuc ,tenKe, maKhu , soLuong );
                timT.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySach.class.getName()).log(Level.SEVERE, null, ex);
        }
        return timT;
    }
    
    public List<Sach> timTenTG(String kw) throws SQLException
    {
        List<Sach> timT = new ArrayList<>();
        
        try (Connection connect = jdbcUtils.getConn()) {
            String sql = "SELECT s.idSach,TenSach ,tentg,Mota, NamXuatBan,NoiXuatBan,DanhMuc,TenKe,MaKhu,s.SoLuong FROM sach s\n" +
                        "INNER JOIN tacgia t ON t.idSach = s.idSach\n" +
                        "INNER JOIN danhmuc m ON m.iddanhmuc = s.MaDanhMuc INNER JOIN vitri v ON v.idvitri = s.MaViTri ";
            if(kw != null && !kw.isEmpty())
            {
                sql += "WHERE tentg like concat('%',?,'%')";
                
            }
            PreparedStatement stm = connect.prepareStatement(sql);
            if(kw != null && !kw.isEmpty())
                stm.setString(1, kw);
            ResultSet rs = stm.executeQuery();
           while(rs.next())
            {
                int maSach = rs.getInt("idsach");
                String ten = rs.getString("TenSach");
                String tentg = rs.getString("tentg");
                String mota = rs.getString("Mota");
                int namXuatBan = rs.getInt("NamXuatBan");
                String noiXuatBan = rs.getString("NoiXuatBan");
                String tenDanhMuc = rs.getString("DanhMuc");
                String tenKe = rs.getString("TenKe");
                String maKhu = rs.getString("MaKhu");
                int soLuong = rs.getInt("SoLuong");
                Sach s = new Sach(maSach,ten, tentg ,mota , namXuatBan , noiXuatBan , tenDanhMuc ,tenKe, maKhu , soLuong );
                timT.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySach.class.getName()).log(Level.SEVERE, null, ex);
        }
        return timT;
    }
    public List<Sach> timNSX(String kw) throws SQLException
    {
        List<Sach> timT = new ArrayList<>();
        
        try (Connection connect = jdbcUtils.getConn()) {
            String sql = "SELECT s.idSach,TenSach ,tentg,Mota, NamXuatBan,NoiXuatBan,DanhMuc,TenKe,MaKhu,s.SoLuong FROM sach s\n" +
                        "INNER JOIN tacgia t ON t.idSach = s.idSach\n" +
                        "INNER JOIN danhmuc m ON m.iddanhmuc = s.MaDanhMuc INNER JOIN vitri v ON v.idvitri = s.MaViTri ";
            if(kw != null && !kw.isEmpty())
            {
                sql += "WHERE NoiXuatBan like concat('%',?,'%')";
                
            }
            PreparedStatement stm = connect.prepareStatement(sql);
            if(kw != null && !kw.isEmpty())
                stm.setString(1, kw);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                int maSach = rs.getInt("idsach");
                String ten = rs.getString("TenSach");
                String tentg = rs.getString("tentg");
                String mota = rs.getString("Mota");
                int namXuatBan = rs.getInt("NamXuatBan");
                String noiXuatBan = rs.getString("NoiXuatBan");
                String tenDanhMuc = rs.getString("DanhMuc");
                String tenKe = rs.getString("TenKe");
                String maKhu = rs.getString("MaKhu");
                int soLuong = rs.getInt("SoLuong");
                Sach s = new Sach(maSach,ten, tentg ,mota , namXuatBan , noiXuatBan , tenDanhMuc ,tenKe, maKhu , soLuong );
                timT.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySach.class.getName()).log(Level.SEVERE, null, ex);
        }
        return timT;
    }
    public List<Sach> timNamXB(String kw) throws SQLException
    {
        List<Sach> timT = new ArrayList<>();
        
        try (Connection connect = jdbcUtils.getConn()) {
            String sql = "SELECT s.idSach,TenSach ,tentg,Mota, NamXuatBan,NoiXuatBan,DanhMuc,TenKe,MaKhu,s.SoLuong FROM sach s\n" +
                        "INNER JOIN tacgia t ON t.idSach = s.idSach\n" +
                        "INNER JOIN danhmuc m ON m.iddanhmuc = s.MaDanhMuc INNER JOIN vitri v ON v.idvitri = s.MaViTri ";
            if(kw != null && !kw.isEmpty())
            {
                sql += "WHERE NamXuatBan like concat('%',?,'%')";
                
            }
            PreparedStatement stm = connect.prepareStatement(sql);
            if(kw != null && !kw.isEmpty())
                stm.setString(1, kw);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                int maSach = rs.getInt("idsach");
                String ten = rs.getString("TenSach");
                String tentg = rs.getString("tentg");
                String mota = rs.getString("Mota");
                int namXuatBan = rs.getInt("NamXuatBan");
                String noiXuatBan = rs.getString("NoiXuatBan");
                String tenDanhMuc = rs.getString("DanhMuc");
                String tenKe = rs.getString("TenKe");
                String maKhu = rs.getString("MaKhu");
                int soLuong = rs.getInt("SoLuong");
                Sach s = new Sach(maSach,ten, tentg ,mota , namXuatBan , noiXuatBan , tenDanhMuc ,tenKe, maKhu , soLuong );
                timT.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySach.class.getName()).log(Level.SEVERE, null, ex);
        }
        return timT;
    }
    public List<Sach> timDanhMuc(String kw) throws SQLException
    {
        List<Sach> timT = new ArrayList<>();
        
        try (Connection connect = jdbcUtils.getConn()) {
           String sql = "SELECT s.idSach,TenSach ,tentg,Mota, NamXuatBan,NoiXuatBan,DanhMuc,TenKe,MaKhu,s.SoLuong FROM sach s\n" +
                        "INNER JOIN tacgia t ON t.idSach = s.idSach\n" +
                        "INNER JOIN danhmuc m ON m.iddanhmuc = s.MaDanhMuc INNER JOIN vitri v ON v.idvitri = s.MaViTri ";
            if(kw != null && !kw.isEmpty())
            {
                sql += "WHERE DanhMuc like concat('%',?,'%')";
                
            }
            PreparedStatement stm = connect.prepareStatement(sql);
            if(kw != null && !kw.isEmpty())
                stm.setString(1, kw);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                int maSach = rs.getInt("idsach");
                String ten = rs.getString("TenSach");
                String tentg = rs.getString("tentg");
                String mota = rs.getString("Mota");
                int namXuatBan = rs.getInt("NamXuatBan");
                String noiXuatBan = rs.getString("NoiXuatBan");
                String tenDanhMuc = rs.getString("DanhMuc");
                String tenKe = rs.getString("TenKe");
                String maKhu = rs.getString("MaKhu");
                int soLuong = rs.getInt("SoLuong");
                Sach s = new Sach(maSach,ten, tentg ,mota , namXuatBan , noiXuatBan , tenDanhMuc ,tenKe, maKhu , soLuong );
                timT.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySach.class.getName()).log(Level.SEVERE, null, ex);
        }
        return timT;
    }
    public void addSach(Sach s) throws SQLException {
        
            try (Connection conn = jdbcUtils.getConn()) {
                conn.setAutoCommit(false);
                
                PreparedStatement stm = conn.prepareStatement("INSERT INTO sach"
                        + "(idSach,TenSach,Mota, NamXuatBan,NoiXuatBan,MaDanhMuc,MaViTri,SoLuong) VALUES(?,?,?,?,?,?,?,?)");
                stm.setInt(1, s.getMaSach());
                stm.setString(2, s.getTenSach());
                stm.setString(3, s.getMoTa());
                stm.setInt(4, s.getNamXuatBan());
                stm.setString(5, s.getNoiXuatBan());
                stm.setInt(6, s.getMaDanhMuc());
                stm.setInt(7, s.getMaViTri());
                stm.setInt(8, s.getSoLuong());
                stm.executeUpdate();
                conn.commit();
            } catch (SQLException ex){
                Logger.getLogger(QuanLySach.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    public void delSach(int maSach) throws SQLException{
        try (Connection cnn = jdbcUtils.getConn()) {
            String sql = "DELETE FROM sach WHERE idSach = ?";
            
            PreparedStatement stm2 = cnn.prepareStatement(sql);
            stm2.setInt(1, maSach);
            stm2.execute();  
            
        }
    }
    public void updateSach(Sach s) throws SQLException {
            try (Connection conn = jdbcUtils.getConn()) {
                conn.setAutoCommit(false);
                PreparedStatement stm2 = conn.prepareStatement(
                        "UPDATE sach Set TenSach = ?, Mota = ?, NamXuatBan =?, NoiXuatBan =?, MaDanhMuc =?, MaViTri = ?, SoLuong = ? WHERE idSach = ?");
                stm2.setString(1, s.getTenSach());
                stm2.setString(2, s.getMoTa());
                stm2.setInt(3, s.getNamXuatBan());
                stm2.setString(4, s.getNoiXuatBan());
                stm2.setInt(5, s.getMaDanhMuc());
                stm2.setInt(6, s.getMaViTri());
                stm2.setInt(7,s.getSoLuong());
                stm2.setInt(8, s.getMaSach());
                stm2.executeUpdate();
                conn.commit();
        }
    }
    public void updateTG(TacGia tg) throws SQLException {
            try (Connection conn = jdbcUtils.getConn()) {
                conn.setAutoCommit(false);
                PreparedStatement stm2 = conn.prepareStatement(
                        "update tacgia set tentg = ? where idSach = ? and tentg = ?" );
                stm2.setString(1, tg.getTenTacGia2());
                stm2.setInt(2, tg.getIdSach());
                stm2.setString(3, tg.getTenTacGia());
                stm2.executeUpdate();
                conn.commit();
        }
    }
}
