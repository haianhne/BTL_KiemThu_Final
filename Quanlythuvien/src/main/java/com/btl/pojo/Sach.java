/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.pojo;

import java.util.Date;

/**
 *
 * @author ACER
 */
public class Sach {
    private int maSach;
    private String tenSach;
    private String tenTacGia;
    private String moTa;
    private int namXuatBan;
    private String noiXuatBan;
    private int maDanhMuc;
    private String danhMuc;
    private int maViTri;
    private String tenKe;
    private String maKhu;
    private Date ngayNhapSach;
    private int soLuong;
    public Sach()
    {
        
    }
    public Sach(int maSach, String tenSach){
        this.maSach = maSach;
        this.tenSach = tenSach;
    }
    public Sach(int maSach, String tenSach, String moTa, int namXuatBan, String noiXuatBan, int maDanhMuc, int maViTri, int soLuong) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.moTa = moTa;
        this.namXuatBan = namXuatBan;
        this.noiXuatBan = noiXuatBan;
        this.maDanhMuc = maDanhMuc;
        this.maViTri = maViTri;
        this.soLuong = soLuong;
    }
     public Sach(int maSach, String tenSach, String moTa, int namXuatBan, String noiXuatBan, String danhMuc, String viTri, int soLuong) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.moTa = moTa;
        this.namXuatBan = namXuatBan;
        this.noiXuatBan = noiXuatBan;
        this.danhMuc = danhMuc;
        this.tenKe = viTri;
        this.soLuong = soLuong;
    }

    public Sach(int maSach, String tenSach,String tenTacGia, String moTa, int namXuatBan, String noiXuatBan, String danhMuc, String tenKe, String maKhu, int soLuong) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tenTacGia = tenTacGia;
        this.moTa = moTa;
        this.namXuatBan = namXuatBan;
        this.noiXuatBan = noiXuatBan;
        this.danhMuc = danhMuc;
        this.tenKe = tenKe;
        this.maKhu = maKhu;
        this.soLuong = soLuong;
    }
    
    
    @Override
    public String toString() {
        return this.getTenSach();
    }


    public String xuatThongTinsach()
    {
        return String.format("\nMa sach: %d\nTen sach: %s\nTen tac gia: %s\nMo ta: %s\nNam xuat ban: %d\nNoi xuat ban: %s\nDanh muc %s\nTen ke: %s\nMa khu %s\nNgay nhap sach: %s\nSo luong: %d\n",
                this.maSach,this.tenSach,this.tenTacGia,this.moTa,this.namXuatBan,this.noiXuatBan,this.danhMuc,this.tenKe,this.maKhu, this.ngayNhapSach,this.soLuong);
    }
   

    /**
     * @return the maSach
     */
    public int getMaSach() {
        return maSach;
    }

    /**
     * @param maSach the maSach to set
     */
    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    /**
     * @return the tenSach
     */
    public String getTenSach() {
        return tenSach;
    }

    /**
     * @param tenSach the tenSach to set
     */
    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    /**
     * @return the moTa
     */
    public String getMoTa() {
        return moTa;
    }

    /**
     * @param moTa the moTa to set
     */
    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    /**
     * @return the namXuatBan
     */
    public int getNamXuatBan() {
        return namXuatBan;
    }

    /**
     * @param namXuatBan the namXuatBan to set
     */
    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    /**
     * @return the noiXuatBan
     */
    public String getNoiXuatBan() {
        return noiXuatBan;
    }

    /**
     * @param noiXuatBan the noiXuatBan to set
     */
    public void setNoiXuatBan(String noiXuatBan) {
        this.noiXuatBan = noiXuatBan;
    }

    /**
     * @return the maDanhMuc
     */
    public int getMaDanhMuc() {
        return maDanhMuc;
    }

    /**
     * @param maDanhMuc the maDanhMuc to set
     */
    public void setMaDanhMuc(int maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    /**
     * @return the danhMuc
     */
    

    /**
     * @return the maViTri
     */
    public int getMaViTri() {
        return maViTri;
    }

    /**
     * @param maViTri the maViTri to set
     */
    public void setMaViTri(int maViTri) {
        this.maViTri = maViTri;
    }

    /**
     * @return the maKhu
     */
    

    /**
     * @return the viTri
     */
   

    /**
     * @return the ngayNhapSach
     */
    public Date getNgayNhapSach() {
        return ngayNhapSach;
    }

    /**
     * @param ngayNhapSach the ngayNhapSach to set
     */
    public void setNgayNhapSach(Date ngayNhapSach) {
        this.ngayNhapSach = ngayNhapSach;
    }

    /**
     * @return the soLuong
     */
    public int getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong the soLuong to set
     */
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    /**
     * @return the danhMuc
     */
    public String getDanhMuc() {
        return danhMuc;
    }

    /**
     * @param danhMuc the danhMuc to set
     */
    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }

    /**
     * @return the tenTacGia
     */
    public String getTenTacGia() {
        return tenTacGia;
    }

    /**
     * @param tenTacGia the tenTacGia to set
     */
    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    /**
     * @return the tenKe
     */
    public String getTenKe() {
        return tenKe;
    }

    /**
     * @param tenKe the tenKe to set
     */
    public void setTenKe(String tenKe) {
        this.tenKe = tenKe;
    }

    /**
     * @return the maKhu
     */
    public String getMaKhu() {
        return maKhu;
    }

    /**
     * @param maKhu the maKhu to set
     */
    public void setMaKhu(String maKhu) {
        this.maKhu = maKhu;
    }
}
