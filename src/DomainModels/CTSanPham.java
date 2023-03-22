/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

import java.util.Date;

/**
 *
 * @author WellCome Win1021H2
 */
public class CTSanPham {

    private String id;
    private MauSac ms;
    private CTKhuyenMai ctkm;
    private SanPham sp;
    private DungLuong dl;
    private String ma;
    private String maQR;
    private String hinhAnh;
    private int namBH;
    private Date ngayTao;
    private Date ngaySua;
    private float giaNhap;
    private float giaBan;
    private int trangThai;

    public CTSanPham() {
    }

    public CTSanPham(String id, MauSac ms, CTKhuyenMai ctkm, SanPham sp, DungLuong dl, String ma, String maQR, String hinhAnh, int namBH, Date ngayTao, Date ngaySua, float giaNhap, float giaBan, int trangThai) {
        this.id = id;
        this.ms = ms;
        this.ctkm = ctkm;
        this.sp = sp;
        this.dl = dl;
        this.ma = ma;
        this.maQR = maQR;
        this.hinhAnh = hinhAnh;
        this.namBH = namBH;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.trangThai = trangThai;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }





    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MauSac getMs() {
        return ms;
    }

    public void setMs(MauSac ms) {
        this.ms = ms;
    }

    public CTKhuyenMai getCtkm() {
        return ctkm;
    }

    public void setCtkm(CTKhuyenMai ctkm) {
        this.ctkm = ctkm;
    }

    public SanPham getSp() {
        return sp;
    }

    public void setSp(SanPham sp) {
        this.sp = sp;
    }

    public DungLuong getDl() {
        return dl;
    }

    public void setDl(DungLuong dl) {
        this.dl = dl;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getMaQR() {
        return maQR;
    }

    public void setMaQR(String maQR) {
        this.maQR = maQR;
    }


    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getNamBH() {
        return namBH;
    }

    public void setNamBH(int namBH) {
        this.namBH = namBH;
    }

    public float getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(float giaNhap) {
        this.giaNhap = giaNhap;
    }

    public float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(float giaBan) {
        this.giaBan = giaBan;
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

    @Override
    public String toString() {
        return sp.getTen()+" "+dl.getSoDungLuong()+" "+ms.getTen();
    }



}
