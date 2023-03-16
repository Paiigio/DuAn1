/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;



import DomainModels.IMEI;
import Service.Interface.IIMEIService;
import ViewModel.IMEIModel;
import java.util.ArrayList;
import responsitory.IMEIResponsitory;

/**
 *
 * @author duong
 */
public class IMEIService implements  IIMEIService{
private IMEIResponsitory im = new IMEIResponsitory();
    @Override
    public ArrayList<IMEIModel> getAllIMEI() {
                ArrayList<IMEIModel> list = new ArrayList<>();
        ArrayList<IMEI> i = im.getAllIMEI();
        for (IMEI x : i) {
            list.add( new IMEIModel(x.getId(), x.getMa(), x.getNgayTao(), x.getGhiChu(), x.getTrangThai()));
            
        }
        return list;
    }

    @Override
    public IMEIModel insertIMEI(IMEIModel i) {
         ArrayList<IMEI> list= im.getAllIMEI();
          for (IMEI x : list) {
            if(x.getMa().equalsIgnoreCase(i.getMa())){
                return null;
            }
            
        }
        var x= im.insertIMEI(new IMEI(i.getId(), i.getMa(), i.getNgayTao(), i.getGhiChu(), i.getTrangThai()));
        return  new IMEIModel(x.getId(), x.getMa(),x.getNgayTao(),x.getGhiChu(),x.getTrangThai());
    }

    @Override
    public IMEIModel updateIMEI(IMEIModel i) {
        var x= im.updateIMEI(new IMEI(i.getId(), i.getMa(), i.getNgayTao(), i.getGhiChu(), i.getTrangThai()));
        return  new IMEIModel(x.getId(), x.getMa(),x.getNgayTao(),x.getGhiChu(),x.getTrangThai());
    }

    @Override
    public Integer deleteIMEI(String ma) {
             return im.deleteIMEI(ma);
    }
    
}
