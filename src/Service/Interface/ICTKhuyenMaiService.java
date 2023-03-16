/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service.Interface;

import DomainModels.CTKhuyenMai;
import ViewModel.CTKhuyenMaiModel;
import java.util.ArrayList;

/**
 *
 * @author duong
 */
public interface ICTKhuyenMaiService {
     public ArrayList<CTKhuyenMaiModel> getAllCTKM();
     public CTKhuyenMaiModel insertCTKM(CTKhuyenMaiModel cv);
     public CTKhuyenMaiModel updateCTKM(CTKhuyenMaiModel cv);
      public ArrayList<CTKhuyenMaiModel> getTimTen(String ten);
}
