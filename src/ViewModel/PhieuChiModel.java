/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import DomainModels.NhanVien;
import DomainModels.PhieuNhap;
import java.util.Date;

/**
 *
 * @author duong
 */
public class PhieuChiModel {
        private String IDPC;
    private PhieuNhap IDPN;
    private NhanVien IDNV;
    private String MA;
    private float gia;
    private Date NgayChi;
    private Date NgayTao;
    private Date NgayNhap;

    public PhieuChiModel() {
    }

    public PhieuChiModel(String IDPC, PhieuNhap IDPN, NhanVien IDNV, String MA, float gia, Date NgayChi, Date NgayTao, Date NgayNhap) {
        this.IDPC = IDPC;
        this.IDPN = IDPN;
        this.IDNV = IDNV;
        this.MA = MA;
        this.gia = gia;
        this.NgayChi = NgayChi;
        this.NgayTao = NgayTao;
        this.NgayNhap = NgayNhap;
    }

    public String getIDPC() {
        return IDPC;
    }

    public void setIDPC(String IDPC) {
        this.IDPC = IDPC;
    }

    public PhieuNhap getIDPN() {
        return IDPN;
    }

    public void setIDPN(PhieuNhap IDPN) {
        this.IDPN = IDPN;
    }

    public NhanVien getIDNV() {
        return IDNV;
    }

    public void setIDNV(NhanVien IDNV) {
        this.IDNV = IDNV;
    }

    public String getMA() {
        return MA;
    }

    public void setMA(String MA) {
        this.MA = MA;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public Date getNgayChi() {
        return NgayChi;
    }

    public void setNgayChi(Date NgayChi) {
        this.NgayChi = NgayChi;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Date NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

}
