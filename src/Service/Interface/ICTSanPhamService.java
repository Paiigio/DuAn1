/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service.Interface;

import ViewModel.CTSanPhamModel;
import java.util.ArrayList;

/**
 *
 * @author duong
 */
public interface ICTSanPhamService {

    public ArrayList<CTSanPhamModel> getAllCTSanPham();

    public ArrayList<CTSanPhamModel> getAllCTSanPham_DangBan();

    public ArrayList<CTSanPhamModel> getAllCTSanPham_KhongBan();

    public Object getCTSPById(String ma);

    public CTSanPhamModel getCTSPByMa(String ma);

    public CTSanPhamModel insertCTSanPham(CTSanPhamModel ctsp);

    public CTSanPhamModel updateCTSanPham(CTSanPhamModel ctsp);

    public Integer deleteCTSanPham(String ma);

    public CTSanPhamModel updateCTKMSanPham(CTSanPhamModel ctsp);

    public CTSanPhamModel deleteCTKM(CTSanPhamModel ctsp);
}
