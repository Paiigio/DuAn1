/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service.Interface;

import ViewModel.CouponModel;
import java.util.ArrayList;

/**
 *
 * @author WellCome Win1021H2
 */
public interface ICouponService {

    public ArrayList<CouponModel> getAllCoupon();

    public CouponModel insertCP(CouponModel cp);

    public CouponModel updateCP(CouponModel cp);

    public Integer deleteCP(String id);

    public CouponModel getCouponByMa(String ma);
}
