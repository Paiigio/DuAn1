/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service.Interface;

import DomainModels.PhieuNhap;
import ViewModel.PhieuNhapModel;
import java.util.ArrayList;

/**
 *
 * @author duong
 */
public interface IPhieuNhapService {
     public ArrayList<PhieuNhapModel> getAllPhieuNhap();
     public PhieuNhapModel insertPN(PhieuNhapModel cv);
       public PhieuNhapModel updatePN(PhieuNhapModel cv) ;
}
