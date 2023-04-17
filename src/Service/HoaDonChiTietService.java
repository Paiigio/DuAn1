package Service;

import DomainModels.HoaDon;
import DomainModels.HoaDonChiTiet;
import Service.Interface.IHoaDonChiTietService;
import Service.Interface.IHoaDonService;
import ViewModel.HoaDonChiTietModel;
import ViewModel.HoaDonModel;
import java.util.ArrayList;
import responsitory.HoaDonChiTietResponsitory;
import responsitory.HoaDonResponsitory;

/**
 *
 * @author duong
 */
public class HoaDonChiTietService implements IHoaDonChiTietService {

    private HoaDonChiTietResponsitory hdct = new HoaDonChiTietResponsitory();

    @Override
    public ArrayList<HoaDonChiTietModel> getAllHoaDonCT() {
        ArrayList<HoaDonChiTietModel> list = new ArrayList<>();
        ArrayList<HoaDonChiTiet> hd = hdct.getAllHoaDonCT();
        for (HoaDonChiTiet x : hd) {
            list.add(new HoaDonChiTietModel(x.getId(), x.getIdhd(), x.getIdctsp(), x.getDongia(), x.getSl(), x.getThanhTien(), x.getNgayTao(), x.getNgaySua(), x.getGhiChu(), x.getBaoHanh()));

        }
        return list;
    }
    @Override
    public ArrayList<HoaDonChiTietModel> getAllHoaDonCT_BH() {
        ArrayList<HoaDonChiTietModel> list = new ArrayList<>();
        ArrayList<HoaDonChiTiet> hd = hdct.getAllHoaDonCT_BH();
        for (HoaDonChiTiet x : hd) {
            list.add(new HoaDonChiTietModel(x.getId(), x.getIdhd(), x.getIdctsp(), x.getDongia(), x.getSl(), x.getThanhTien(), x.getNgayTao(), x.getNgaySua(), x.getGhiChu(), x.getBaoHanh()));

        }
        return list;
    }
    @Override
    public HoaDonChiTietModel insertHDCT(HoaDonChiTietModel h) {
        ArrayList<HoaDonChiTiet> ds = hdct.getAllHoaDonCT();
        var x = hdct.insertHDCT(new HoaDonChiTiet(h.getId(), h.getIdhd(), h.getIdctsp(), h.getDongia(), h.getSl(), h.getThanhTien(), h.getNgayTao(), h.getNgaySua(), h.getGhiChu(), h.getBaoHanh()));
        return new HoaDonChiTietModel(x.getId(), x.getIdhd(), x.getIdctsp(), x.getDongia(), x.getSl(), x.getThanhTien(), x.getNgayTao(), x.getNgaySua(), x.getGhiChu(), x.getBaoHanh());
    }

    @Override
    public Integer deleteHDCT(String id) {
        return hdct.deleteHDCT(id);
    }

    @Override
    public ArrayList<HoaDonChiTietModel> getAllHoaDonCTBYIDHD(String id) {
        ArrayList<HoaDonChiTietModel> list = new ArrayList<>();
        ArrayList<HoaDonChiTiet> hd = hdct.getAllHoaDonCTByIDHD(id);
        for (HoaDonChiTiet x : hd) {
            list.add(new HoaDonChiTietModel(x.getId(), x.getIdhd(), x.getIdctsp(), x.getDongia(), x.getSl(), x.getThanhTien(), x.getNgayTao(), x.getNgaySua(), x.getGhiChu(),x.getBaoHanh()));

        }
        return list;
    }

    @Override
    public Integer delete(String ghiChu, String idHD) {
        return hdct.delete(ghiChu, idHD);
    }

    @Override
    public HoaDonChiTietModel updateBaoHanh_Yes(HoaDonChiTietModel h) {
        var x = hdct.updateBaoHanh_Yes(new HoaDonChiTiet(h.getId(), h.getIdhd(), h.getIdctsp(), h.getDongia(), h.getSl(), h.getThanhTien(), h.getNgayTao(), h.getNgaySua(), h.getGhiChu(), h.getBaoHanh()));
        return new HoaDonChiTietModel(x.getId(), x.getIdhd(), x.getIdctsp(), x.getDongia(), x.getSl(), x.getThanhTien(), x.getNgayTao(), x.getNgaySua(), x.getGhiChu(), x.getBaoHanh());

    }

    @Override
    public HoaDonChiTietModel updateBaoHanh_No(HoaDonChiTietModel h) {
        var x = hdct.updateBaoHanh_No(new HoaDonChiTiet(h.getId(), h.getIdhd(), h.getIdctsp(), h.getDongia(), h.getSl(), h.getThanhTien(), h.getNgayTao(), h.getNgaySua(), h.getGhiChu(), h.getBaoHanh()));
        return new HoaDonChiTietModel(x.getId(), x.getIdhd(), x.getIdctsp(), x.getDongia(), x.getSl(), x.getThanhTien(), x.getNgayTao(), x.getNgaySua(), x.getGhiChu(), x.getBaoHanh());

    }

    @Override
    public HoaDonChiTietModel selectIDHDCT(String idHD, String ghiChu) {
        var x = hdct.selectIDHDCT(idHD, ghiChu);
        return new HoaDonChiTietModel(x.getId(), x.getIdhd(), x.getIdctsp(), x.getDongia(), x.getSl(), x.getThanhTien(), x.getNgayTao(), x.getNgaySua(), x.getGhiChu(), x.getBaoHanh());

    }
}
