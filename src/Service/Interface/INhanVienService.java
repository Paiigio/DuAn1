/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service.Interface;


import DomainModels.NhanVien;
import ViewModel.NhanVienModel;
import java.util.ArrayList;

/**
 *
 * @author duong
 */
public interface INhanVienService {
    public ArrayList<NhanVienModel> getAllNV() ;
    public ArrayList<NhanVienModel> getTimTen(String ten);
    public ArrayList<NhanVienModel> getVaiTro(String id);
     public ArrayList<NhanVienModel> getTT(String id);
    public NhanVienModel insertNV(NhanVienModel cv);
    public NhanVienModel updateNV(NhanVienModel cv);
    public String updatePass(String pass, String ma);
    public Integer deleteNV(String ma); 
}
