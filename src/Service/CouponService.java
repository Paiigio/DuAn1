
package Service;

import DomainModels.Coupon;
import Service.Interface.ICouponService;
import ViewModel.CouponModel;
import java.util.ArrayList;
import responsitory.CouponReponsitory;

/**
 *
 * @author WellCome Win1021H2
 */
public class CouponService implements ICouponService{
private CouponReponsitory cpr = new CouponReponsitory();
    @Override
    public ArrayList<CouponModel> getAllCoupon() {
        ArrayList<CouponModel> list = new ArrayList<>();
        ArrayList<Coupon> cp = cpr.getAllCoupon();
        for (Coupon x : cp) {
            list.add( new CouponModel(x.getId(), x.getMa(), x.getHanSuDung(), x.getHinhThuc(), x.getGiamGia(), x.getNgayTao(), x.getNgaySua()));
            
        }
        return list;
    }

    @Override
    public CouponModel insertCP(CouponModel cp) {
         ArrayList<Coupon> list= cpr.getAllCoupon();
          for (Coupon x : list) {
            if(x.getMa().equalsIgnoreCase(cp.getMa())){
                return null;
            }
            
        }
        var x= cpr.insertCP(new Coupon(cp.getId(), cp.getMa(), cp.getHanSuDung(), cp.getHinhThuc(), cp.getGiamGia(), cp.getNgayTao(), null));
        return  new CouponModel(x.getId(), x.getMa(),x.getHanSuDung(),x.getHinhThuc(),x.getGiamGia(),x.getNgayTao(),null);
    }

    @Override
    public CouponModel updateCP(CouponModel cp) {
          var x= cpr.updateCP(new Coupon(cp.getId(), cp.getMa(), cp.getHanSuDung(), cp.getHinhThuc(), cp.getGiamGia(), cp.getNgayTao(), cp.getNgaySua()));
        return  new CouponModel(x.getId(), x.getMa(),x.getHanSuDung(),x.getHinhThuc(),x.getGiamGia(),x.getNgayTao(),x.getNgaySua());
    }

    @Override
    public Integer deleteCP(String id) {
             return cpr.deleteCP(id);
    }  
    
        @Override
    public CouponModel getCouponByMa(String ma) {
          var x= cpr.getCPByMa(ma);
        return  new CouponModel(x.getId(), x.getMa(),x.getHanSuDung(),x.getHinhThuc(),x.getGiamGia(),x.getNgayTao(),x.getNgaySua());
    }
}
