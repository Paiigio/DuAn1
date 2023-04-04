/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service.Interface;

import ViewModel.IMEIModel;
import java.util.ArrayList;

/**
 *
 * @author duong
 */
public interface IIMEIService {

    public ArrayList<IMEIModel> getAllIMEI();

    public IMEIModel insertIMEI(IMEIModel i);

    public IMEIModel updateIMEI(IMEIModel i);

    public Integer deleteIMEI(String ma);

    public ArrayList<IMEIModel> selectSL(String id);

    public ArrayList<IMEIModel> getIMEIByNote(String note);

    public IMEIModel getTrangThaiByIMEI(String imei);

    public int updateIMEI_ThanhToan(String ma);
    public Integer updateIMEI_HuyHang(String ma);
}
