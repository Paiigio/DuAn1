/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service.Interface;

import DomainModels.HoaDon;
import ViewModel.HoaDonModel;
import java.util.ArrayList;

/**
 *
 * @author duong
 */
public interface IHoaDonService {
    public ArrayList<HoaDonModel> getAllHoaDon();
    public HoaDonModel insertHD(HoaDonModel nv);
    public HoaDonModel updateTinhTrangHD(HoaDonModel nv);
       public ArrayList<HoaDonModel> getAllHoaDonTT();
          public ArrayList<HoaDonModel> getAllHoaDonCTT() ;
}
