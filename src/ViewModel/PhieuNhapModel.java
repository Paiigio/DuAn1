/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import DomainModels.NhaPhanPhoi;
import DomainModels.NhanVien;
import java.util.Date;

/**
 *
 * @author duong
 */
public class PhieuNhapModel {
     private String id;
    private NhaPhanPhoi nhaPhanPhoi;
    private NhanVien nhanVien;
    private String maPN;
    private float TongGia;
    private Date ngayTao;

    public PhieuNhapModel() {
    }

    public PhieuNhapModel(String id, NhaPhanPhoi nhaPhanPhoi, NhanVien nhanVien, String maPN, float TongGia, Date ngayTao) {
        this.id = id;
        this.nhaPhanPhoi = nhaPhanPhoi;
        this.nhanVien = nhanVien;
        this.maPN = maPN;
        this.TongGia = TongGia;
        this.ngayTao = ngayTao;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNhaPhanPhoi(NhaPhanPhoi nhaPhanPhoi) {
        this.nhaPhanPhoi = nhaPhanPhoi;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public void setMaPN(String maPN) {
        this.maPN = maPN;
    }

    public void setTongGia(float TongGia) {
        this.TongGia = TongGia;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getId() {
        return id;
    }

    public NhaPhanPhoi getNhaPhanPhoi() {
        return nhaPhanPhoi;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public String getMaPN() {
        return maPN;
    }

    public float getTongGia() {
        return TongGia;
    }

    public Date getNgayTao() {
        return ngayTao;
    }
 
}
