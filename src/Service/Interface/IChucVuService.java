/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service.Interface;

import DomainModels.ChucVu;
import ViewModel.ChucVuModel;
import java.util.ArrayList;

/**
 *
 * @author duong
 */
public interface IChucVuService {
    public ArrayList<ChucVuModel> getAllChucVu() ;
    public ChucVuModel insertCV(ChucVuModel cv);
    public ChucVuModel updateCV(ChucVuModel cv);
    public Integer deleteCV(String ma);
}
