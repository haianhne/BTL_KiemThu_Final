/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.pojo;

import java.sql.Date;

/**
 *
 * @author ACER
 */
public class ThueSach {
    private int maDG;
    private int idSach;
    private String ten;
    private String tenSach;
    private String tinhTrang;
    private Date ngayMuon;
    private Date hanTra;
    private Date ngayTra;
    private int soNgay;
    private int tienPhat;
    private int soLuong;
    
    public ThueSach()
    {
    }

    public ThueSach(String tenSach, int soLuong)
    {
        this.tenSach = tenSach;
        this.soLuong = soLuong;
    }
    
    public ThueSach(int maDG, int idSach, String tinhTrang, Date ngayMuon, Date hanTra)
    {
        this.maDG = maDG;
        this.idSach = idSach;
        this.tinhTrang = tinhTrang;
        this.ngayMuon = ngayMuon;
        this.hanTra = hanTra;
    }
    
    public ThueSach(int maDG, int idSach, Date ngayMuon, Date ngayTra, int soNgay, int tienPhat)
    {
        this.maDG = maDG;
        this.idSach = idSach;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.soNgay = soNgay;
        this.tienPhat = tienPhat;
    }
    public ThueSach(int maDG, String ten, int idSach, String tenSach, String tinhTrang, Date ngayMuon, Date hanTra, Date ngayTra, int soNgay, int tienPhat)
    {
        this.maDG = maDG;
        this.ten = ten;
        this.idSach = idSach;
        this.tenSach = tenSach;
        this.tinhTrang = tinhTrang;
        this.ngayMuon = ngayMuon;
        this.hanTra = hanTra;
        this.ngayTra = ngayTra;
        this.tienPhat = tienPhat;
    }
    
    public int getMaDG() {
        return maDG;
    }

    public void setMaDG(int maDG) {
        this.maDG = maDG;
    }

    public int getIdSach() {
        return idSach;
    }

    public void setIdSach(int idSach) {
        this.idSach = idSach;
    }

    public String getTen() {
        return ten;
    }


    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTenSach() {
        return tenSach;
    }
    
    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }


    public Date getHanTra() {
        return hanTra;
    }

    public void setHan(Date hanTra) {
        this.hanTra = hanTra;
    }
    
    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public int getTienPhat() {
        return tienPhat;
    }

    public void setTienPhat(int tienPhat) {
        this.tienPhat = tienPhat;
    }

    /**
     * @return the tinhTrang
     */
    public String isTinhTrang() {
        return tinhTrang;
    }

    /**
     * @param tinhTrang the tinhTrang to set
     */
    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    /**
     * @return the soNgay
     */
    public int getSoNgay() {
        return soNgay;
    }

    /**
     * @param soNgay the soNgay to set
     */
    public void setSoNgay(int soNgay) {
        this.soNgay = soNgay;
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
}
