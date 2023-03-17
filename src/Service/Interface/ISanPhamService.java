/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service.Interface;

import ViewModel.SanPhamModel;
import java.util.ArrayList;

/**
 *
 * @author duong
 */
public interface ISanPhamService {

    public ArrayList<SanPhamModel> getAllSanPham();

    public SanPhamModel insertSP(SanPhamModel sp);

    public SanPhamModel updateSP(SanPhamModel sp);

    public Integer deleteSP(String ma);
}
