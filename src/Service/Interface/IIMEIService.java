/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service.Interface;

import ViewModel.ChucVuModel;
import ViewModel.IMEIModel;
import java.util.ArrayList;

/**
 *
 * @author duong
 */
public interface IIMEIService {
    public ArrayList<IMEIModel> getAllIMEI() ;
    public IMEIModel insertIMEI(IMEIModel i);
    public IMEIModel updateIMEI(IMEIModel i);
    public Integer deleteIMEI(String ma);
}
