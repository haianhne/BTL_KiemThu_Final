/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.pojo;

/**
 *
 * @author ACER
 */
public class TacGia {
    private int idSach;
    private String tenTacGia;
    private String tenTacGia2;
    

    public TacGia(int idSach, String tenTacGia) {
        this.idSach = idSach;
        this.tenTacGia = tenTacGia;
    }
    public TacGia(int idSach, String tenTacGia, String tenTacGia2) {
        this.idSach = idSach;
        this.tenTacGia = tenTacGia;
        this.tenTacGia2 = tenTacGia2;
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
     * @return the idSach
     */
    public int getIdSach() {
        return idSach;
    }

    /**
     * @param idSach the idSach to set
     */
    public void setIdSach(int idSach) {
        this.idSach = idSach;
    }

    /**
     * @return the tenTacGia2
     */
    public String getTenTacGia2() {
        return tenTacGia2;
    }

    /**
     * @param tenTacGia2 the tenTacGia2 to set
     */
    public void setTenTacGia2(String tenTacGia2) {
        this.tenTacGia2 = tenTacGia2;
    }
}
