/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import java.util.Date;

/**
 *
 * @author duong
 */
public class ChucVuModel {
        private String id;
    private String ma;
    private String tenCV;
    private Date ngayTao;
    private Date ngaySua;

    public ChucVuModel() {
    }

    public ChucVuModel(String ma, String tenCV) {
        this.ma = ma;
        this.tenCV = tenCV;
    }
    
    public ChucVuModel(String id, String ma, String tenCV, Date ngayTao, Date ngaySua) {
        this.id = id;
        this.ma = ma;
        this.tenCV = tenCV;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTenCV() {
        return tenCV;
    }

    public void setTenCV(String tenCV) {
        this.tenCV = tenCV;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }


  
}
