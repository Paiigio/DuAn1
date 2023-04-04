/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service.Interface;

import DomainModels.KhachHang;
import ViewModel.KhachHangModel;
import java.util.ArrayList;

/**
 *
 * @author duong
 */
public interface IKhachHangService {

    public ArrayList<KhachHangModel> getAllKH();

    public KhachHangModel getTimKH(String sdt);
      public ArrayList<KhachHangModel> getTimSDT(String sdt);

    public KhachHangModel insertKH(KhachHangModel nv);

    public KhachHangModel updateKH(KhachHangModel nv);

    public KhachHangModel getKHByMa(String ma);

}
