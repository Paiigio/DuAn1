/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import DomainModels.*;
import java.util.Date;

/**
 *
 * @author WellCome Win1021H2
 */
public class IMEIModel {
    private String id;
    private CTSanPham ctsp;
    private String ma;
    private Date ngayTao;
    private String ghiChu;
    private int trangThai;

    public IMEIModel() {
    }

    public IMEIModel(String id, CTSanPham ctsp, String ma, Date ngayTao, String ghiChu, int trangThai) {
        this.id = id;
        this.ctsp = ctsp;
        this.ma = ma;
        this.ngayTao = ngayTao;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }

    public CTSanPham getCtsp() {
        return ctsp;
    }

    public void setCtsp(CTSanPham ctsp) {
        this.ctsp = ctsp;
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

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
}
