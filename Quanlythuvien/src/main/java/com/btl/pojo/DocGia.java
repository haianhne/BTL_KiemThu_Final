/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.pojo;

import java.sql.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Admin
 */
public class DocGia {
    private int maDG;
    private String ho;
    private String ten;
    private String gioiTinh;
    private Date ngaySinh;
    private int maDoiTuong;
    private int maBoPhan;
    private Date hanTheBD;
    private Date hanTheKT;
    private String email;
    private String diaChi;
    private String sdt;
    private String doiTuong;
    private String boPhan;   
    public DocGia() {
    }
    public DocGia(String ho,String ten, String gioiTinh, Date ngaySinh, int maDoiTuong, int maBoPhan, Date hanTheBD, Date hanTheKT, String email, String diaChi, String sdt) {
        this.ho = ho;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.hanTheBD= hanTheBD;
        this.hanTheKT= hanTheKT;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.maBoPhan = maBoPhan;
        this.maDoiTuong = maDoiTuong;
        this.email = email;
    }
    public DocGia(int maDG, String ten, String gioiTinh, Date ngaySinh, String doiTuong, String boPhan, Date hanTheBD, Date hanTheKT, String email, String diaChi, String sdt) {
        this.maDG = maDG;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.hanTheBD= hanTheBD;
        this.hanTheKT= hanTheKT;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.boPhan = boPhan;
        this.doiTuong = doiTuong;
        this.email = email;
    }
    public DocGia(int maDG,String ho, String ten, String gioiTinh, Date ngaySinh, int maDoiTuong , int maboPhan, Date hanTheBD, Date hanTheKT, String email, String diaChi, String sdt) {
        this.maDG = maDG;
        this.ho = ho;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.hanTheBD= hanTheBD;
        this.hanTheKT= hanTheKT;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.maDoiTuong = maDoiTuong;
        this.maBoPhan = maboPhan;
        this.email = email;
    }
    public DocGia(int maDG,String ho, String ten, String gioiTinh, Date ngaySinh, String doiTuong , String boPhan, Date hanTheBD, Date hanTheKT, String email, String diaChi, String sdt) {
        this.maDG = maDG;
        this.ho = ho;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.hanTheBD= hanTheBD;
        this.hanTheKT= hanTheKT;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.doiTuong = doiTuong;
        this.boPhan = boPhan;
        this.email = email;
    }

   
    /**
     * @return the maDG
     */
    public int getMaDG() {
        return maDG;
    }

    /**
     * @param maDG the maDG to set
     */
    public void setMaDG(int maDG) {
        this.maDG = maDG;
    }

    /**
     * @return the ten
     */
    public String getTen() {
        return ten;
    }

    /**
     * @param ten the ten to set
     */
    public void setTen(String ten) {
        this.ten = ten;
    }

    /**
     * @return the gioiTinh
     */
    public String getGioiTinh() {
        return gioiTinh;
    }

    /**
     * @param gioiTinh the gioiTinh to set
     */
    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    /**
     * @return the maDoiTuong
     */
    public int getMaDoiTuong() {
        return maDoiTuong;
    }

    /**
     * @param maDoiTuong the maDoiTuong to set
     */
    public void setMaDoiTuong(int maDoiTuong) {
        this.maDoiTuong = maDoiTuong;
    }

    /**
     * @return the maBoPhan
     */
    public int getMaBoPhan() {
        return maBoPhan;
    }

    /**
     * @param maBoPhan the maBoPhan to set
     */
    public void setMaBoPhan(int maBoPhan) {
        this.maBoPhan = maBoPhan;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the diaChi
     */
    public String getDiaChi() {
        return diaChi;
    }

    /**
     * @param diaChi the diaChi to set
     */
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    /**
     * @return the sdt
     */
    public String getSdt() {
        return sdt;
    }

    /**
     * @param sdt the sdt to set
     */
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    /**
     * @return the doiTuong
     */
    public String getDoiTuong() {
        return doiTuong;
    }

    /**
     * @param doiTuong the doiTuong to set
     */
    public void setDoiTuong(String doiTuong) {
        this.doiTuong = doiTuong;
    }

    /**
     * @return the ngaySinh
     */
    public Date getNgaySinh() {
        return ngaySinh;
    }

    /**
     * @param ngaySinh the ngaySinh to set
     */
    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    /**
     * @return the hanTheBD
     */
    public Date getHanTheBD() {
        return hanTheBD;
    }

    /**
     * @param hanTheBD the hanTheBD to set
     */
    public void setHanTheBD(Date hanTheBD) {
        this.hanTheBD = hanTheBD;
    }

    /**
     * @return the hanTheKT
     */
    public Date getHanTheKT() {
        return hanTheKT;
    }

    /**
     * @param hanTheKT the hanTheKT to set
     */
    public void setHanTheKT(Date hanTheKT) {
        this.hanTheKT = hanTheKT;
    }

    /**
     * @return the boPhan
     */
    public String getBoPhan() {
        return boPhan;
    }

    /**
     * @param boPhan the boPhan to set
     */
    public void setBoPhan(String boPhan) {
        this.boPhan = boPhan;
    }

    /**
     * @return the ho
     */
    public String getHo() {
        return ho;
    }

    /**
     * @param ho the ho to set
     */
    public void setHo(String ho) {
        this.ho = ho;
    }   
}
