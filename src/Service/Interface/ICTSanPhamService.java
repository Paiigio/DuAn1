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

    public CTSanPhamModel insertCTSanPham(CTSanPhamModel ctsp);

    public CTSanPhamModel updateCTSanPham(CTSanPhamModel ctsp);

    public Integer deleteCTSanPham(String ma);
}
