/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DomainModels.MauSac;
import Service.Interface.IMauSacService;
import ViewModel.MauSacModel;
import java.util.ArrayList;
import responsitory.MauSacResponsitory;

/**
 *
 * @author duong
 */
public class MauSacService implements IMauSacService{
    private  MauSacResponsitory msr = new MauSacResponsitory();
    @Override
    public ArrayList<MauSacModel> getAllMauSac(int position , int pageSize) {
                    ArrayList<MauSacModel> list = new ArrayList<>();
        ArrayList<MauSac> cv = msr.getAllMauSac(position,pageSize);
        for (MauSac x : cv) {
            list.add( new MauSacModel(x.getId(), x.getMa(),x.getTen()));
            
        }
        return list;
    }

    @Override
    public MauSacModel insertMS(MauSacModel cv) {
        var x= msr.insertMS(new MauSac(cv.getId(), cv.getMa(), cv.getTen()));
        return  new MauSacModel(x.getId(), x.getMa(),x.getTen());
    }

    @Override
    public MauSacModel updateMS(MauSacModel cv) {
                var x= msr.updateMS(new MauSac(cv.getId(), cv.getMa(), cv.getTen()));
        return  new MauSacModel(x.getId(), x.getMa(),x.getTen());
    }

    @Override
    public Integer deleteMS(String ma) {
                   return msr.deleteMS(ma);
    }
    @Override
    public long countAll(){
        return msr.totalCount();
    }
}
