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
public class IMEIService implements IIMEIService {

    private IMEIResponsitory im = new IMEIResponsitory();

    @Override
    public ArrayList<IMEIModel> getAllIMEI() {
        ArrayList<IMEIModel> list = new ArrayList<>();
        ArrayList<IMEI> i = im.getAllIMEI();
        for (IMEI x : i) {
            list.add(new IMEIModel(x.getId(), x.getCtsp(), x.getMa(), x.getNgayTao(), x.getGhiChu(), x.getTrangThai()));

        }
        return list;
    }

    @Override
    public ArrayList<IMEIModel> getIMEIByTT() {
        ArrayList<IMEIModel> list = new ArrayList<>();
        ArrayList<IMEI> i = im.getIMEIByTT();
        for (IMEI x : i) {
            list.add(new IMEIModel(x.getId(), x.getCtsp(), x.getMa(), x.getNgayTao(), x.getGhiChu(), x.getTrangThai()));

        }
        return list;
    }

    @Override
    public IMEIModel insertIMEI(IMEIModel i) {
        ArrayList<IMEI> list = im.getAllIMEI();
        for (IMEI x : list) {
            if (x.getMa().equalsIgnoreCase(i.getMa())) {
                return null;
            }

        }
        var x = im.insertIMEI(new IMEI(i.getId(), i.getCtsp(), i.getMa(), i.getNgayTao(), i.getGhiChu(), i.getTrangThai()));
        return new IMEIModel(x.getId(), x.getCtsp(), x.getMa(), x.getNgayTao(), x.getGhiChu(), x.getTrangThai());
    }

    @Override
    public ArrayList<IMEIModel> getIMEIByNote(String note) {
        ArrayList<IMEIModel> list = new ArrayList<>();
        ArrayList<IMEI> i = im.getIMEIByNote(note);
        for (IMEI x : i) {
            list.add(new IMEIModel(x.getId(), x.getCtsp(), x.getMa(), x.getNgayTao(), x.getGhiChu(), x.getTrangThai()));
        }
        return list;
    }

    @Override
    public IMEIModel updateIMEI(IMEIModel i) {
        var x = im.updateIMEI(new IMEI(i.getId(), i.getCtsp(), i.getMa(), i.getNgayTao(), i.getGhiChu(), i.getTrangThai()));
        return new IMEIModel(x.getId(), x.getCtsp(), x.getMa(), x.getNgayTao(), x.getGhiChu(), x.getTrangThai());
    }

    @Override
    public Integer deleteIMEI(String ma) {
        return im.deleteIMEI(ma);
    }

    @Override
    public ArrayList<IMEIModel> selectSL(String id) {
        ArrayList<IMEIModel> list = new ArrayList<>();
        ArrayList<IMEI> i = im.selectSL(id);
        for (IMEI x : i) {
            list.add(new IMEIModel(x.getId(), x.getCtsp(), x.getMa(), x.getNgayTao(), x.getGhiChu(), x.getTrangThai()));
        }
        return list;
    }

    @Override
    public IMEIModel getTrangThaiByIMEI(String imei) {
        IMEI x = im.getTrangThaiByIMEI(imei);
        return new IMEIModel(x.getId(), x.getCtsp(), x.getMa(), x.getNgayTao(), x.getGhiChu(), x.getTrangThai());
    }

    @Override
    public int updateIMEI_ThanhToan(String ma) {
        return im.updateIMEI_ThanhToan(ma);
    }

    @Override
    public Integer updateIMEI_HuyHang(String ma) {
        return im.updateIMEI_HuyHang(ma);
    }

    @Override
    public ArrayList<IMEIModel> getTimImei(String IMEI) {
        ArrayList<IMEIModel> list = new ArrayList<>();
        ArrayList<IMEI> nv = im.getTimImei(IMEI);
          for (IMEI x : nv) {
            list.add(new IMEIModel(x.getId(), x.getCtsp(), x.getMa(), x.getNgayTao(), x.getGhiChu(), x.getTrangThai()));

        }
        return list;
    }

}
