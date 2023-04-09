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

    public HoaDonModel upadteHD(HoaDonModel nv);

    public HoaDonModel upadteHD_ThanhToan(HoaDonModel nv);

    public HoaDonModel updateTinhTrangHD(HoaDonModel nv);

    public HoaDonModel updateHuyHD(HoaDonModel nv);

    public HoaDonModel updateCopounHD(HoaDonModel nv);

    public HoaDonModel getHDByMaHD(String ma);

    public ArrayList<HoaDonModel> getAllHoaDonTT();

    public ArrayList<HoaDonModel> getAllHoaDonCTT();
}
