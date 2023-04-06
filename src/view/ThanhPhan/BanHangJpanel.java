/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.ThanhPhan;

import DomainModels.CTSanPham;
import DomainModels.Coupon;
import DomainModels.HoaDon;
import DomainModels.KhachHang;
import DomainModels.NhanVien;
import Service.CTSanPhamService;
import Service.HoaDonChiTietService;
import Service.HoaDonService;
import Service.IMEIService;
import Service.Interface.ICTSanPhamService;
import Service.Interface.IHoaDonChiTietService;
import Service.Interface.IHoaDonService;
import Service.Interface.IIMEIService;
import Service.Interface.IKhachHangService;
import Service.KhachHangService;
import Utilites.DB_Context;
import ViewModel.CTSanPhamModel;
import ViewModel.HoaDonChiTietModel;
import ViewModel.HoaDonModel;
import ViewModel.IMEIModel;
import ViewModel.KhachHangModel;
import ViewModel.NhanVienModel;
import java.awt.Color;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import view.QuetQR;

/**
 *
 * @author duong
 */
public class BanHangJpanel extends javax.swing.JPanel {

    private DefaultTableModel dtmSP = new DefaultTableModel();
    private ICTSanPhamService iCTSanPhamService = new CTSanPhamService();
    private DefaultTableModel dtmHD = new DefaultTableModel();
    private IHoaDonService iHoaDonService = new HoaDonService();
    private DefaultTableModel dtmGH = new DefaultTableModel();
    private ArrayList<String> listIMEI = new ArrayList<>();
    private IHoaDonChiTietService iHoaDonChiTietService = new HoaDonChiTietService();
    private IIMEIService iIMEIService = new IMEIService();
    private IKhachHangService iKhachHangService = new KhachHangService();

    /**
     * Creates new form BanHangJpanel1
     */
    public BanHangJpanel() {

        initComponents();
        dtmSP = (DefaultTableModel) tblSanPham.getModel();
        dtmHD = (DefaultTableModel) tblHoaDon.getModel();
        dtmGH = (DefaultTableModel) tblGioHang.getModel();
        btnThay.setEnabled(false);
        btnKH.setEnabled(false);
        load();
        System.out.println(giamGia("5%", 130000));
        tblHoaDon.getTableHeader().setBackground(Color.red);
        tblGioHang.getTableHeader().setBackground(Color.red);
    }

    private float giamGia(String hinhThuc, float giaTien) {
        float gia = 0;
        if (hinhThuc.contains("%")) {
            gia = giaTien - (Float.valueOf(hinhThuc.trim().substring(0, hinhThuc.length() - 1)) / 100 * giaTien);
            System.out.println(Float.valueOf(hinhThuc.trim().substring(0, hinhThuc.length() - 1)) / 100);
        } else {
            gia = giaTien - Integer.valueOf(hinhThuc);
        }
        return gia;
    }

    private void loadSP() {
        ArrayList<CTSanPhamModel> listSP = iCTSanPhamService.getAllCTSanPham();
        dtmSP.setRowCount(0);
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat vn = NumberFormat.getInstance(localeVN);
        for (CTSanPhamModel x : listSP) {
            ArrayList<IMEIModel> listIMEI = iIMEIService.selectSL(x.getId());
            dtmSP.addRow(new Object[]{
                x.getMa(),
                x.getSp().getTen() + " " + x.getDl().getSoDungLuong() + "GB " + x.getMs().getTen(),
                x.getCtkm(),
                listIMEI.size(),
                vn.format(x.getGiaBan()),
                x.getCtkm() == null ? vn.format(x.getGiaBan()) : vn.format(giamGia(x.getCtkm().getHinhThuc(), x.getGiaBan()))
            });
        }
    }

    private void load() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    loadSP();
                    loadHDCho();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThongKeJpanel.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }).start();
    }

    private void loadHD() {
        ArrayList<HoaDonModel> listHD = iHoaDonService.getAllHoaDon();
        dtmHD.setRowCount(0);
        Collections.sort(listHD, Comparator.comparing(HoaDon -> HoaDon.getMa()));

        for (HoaDonModel x : listHD) {
            dtmHD.addRow(new Object[]{
                x.getMa(),
                x.getNv().getHoTen(),
                x.getKh().getHoTen(),
                x.getNgayTao(),
                getTTHD(x.getTrangThai())
            });
        }
    }
    public String getTTHD(int tt){
    if(tt==0){
        return "Chưa thanh toán";
    }else if(tt==1){
        return "Đã thanh toán";
    }else{
        return "Đã hủy";
    }
}
    private void loadHDCho() {
        ArrayList<HoaDonModel> listHD = iHoaDonService.getAllHoaDonCTT();
        dtmHD.setRowCount(0);
        Collections.sort(listHD, Comparator.comparing(HoaDon -> HoaDon.getMa()));

        for (HoaDonModel x : listHD) {
            dtmHD.addRow(new Object[]{
                x.getMa(),
                x.getNv().getHoTen(),
                x.getKh().getHoTen(),
                x.getNgayTao(),
                getTTHD(x.getTrangThai())
            });
        }
    }

    private void loadHDTT() {
        ArrayList<HoaDonModel> listHD = iHoaDonService.getAllHoaDonTT();
        dtmHD.setRowCount(0);
        Collections.sort(listHD, Comparator.comparing(HoaDon -> HoaDon.getMa()));
        for (HoaDonModel x : listHD) {
            dtmHD.addRow(new Object[]{
                x.getMa(),
                x.getNv().getHoTen(),
                x.getKh().getHoTen(),
                x.getNgayTao(),
                x.getTrangThai() == 0 ? "Chưa thanh toán" : "Đã thanh toán"
            });
        }
    }

    private void loadGioHang() {
        int index = tblHoaDon.getSelectedRow();
        dtmGH.setRowCount(0);
        String maHD = tblHoaDon.getValueAt(index, 0).toString();
        String maIMEI = "";
        int dem = 0;
        ArrayList<HoaDonChiTietModel> listHDCT = iHoaDonChiTietService.getAllHoaDonCT();
        ArrayList<HoaDonChiTietModel> listNEW = new ArrayList<>();
        // lấy ra list HDCT có mã HD đang chọn
        for (HoaDonChiTietModel hd : listHDCT) {
            if (hd.getIdhd().getMa() != null && hd.getIdhd().getMa().equals(maHD)) {
                listNEW.add(hd);
            }
        }
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat vn = NumberFormat.getInstance(localeVN);
        // lấy ra hóa đơn chi tiết có trong hóa đơn và mã imei
        dtmGH.setRowCount(0);
        for (HoaDonChiTietModel hdct : listNEW) {

            // lấy ra những mã imei có mã sản phẩm đang có trong hóa đơn chi tiết
            dem++;
            dtmGH.addRow(new Object[]{
                dem,
                hdct.getIdctsp().getSp().getTen() + " " + hdct.getIdctsp().getDl().getSoDungLuong() + " " + hdct.getIdctsp().getMs().getTen(),
                vn.format(hdct.getDongia()),
                hdct.getSl(),
                hdct.getIdctsp().getCtkm(),
                hdct.getIdctsp().getCtkm() == null ? vn.format(hdct.getDongia()) : vn.format(giamGia(hdct.getIdctsp().getCtkm().getHinhThuc(), hdct.getDongia())),
                hdct.getGhiChu()
            });

        }

    }

    public void loadGioHang1(String id) {
        int index = tblHoaDon.getSelectedRow();
        dtmGH.setRowCount(0);
        String maHD = tblHoaDon.getValueAt(index, 0).toString();
        String maIMEI = "";
        int dem = 0;
        ArrayList<HoaDonChiTietModel> listHDCT = iHoaDonChiTietService.getAllHoaDonCT();
        ArrayList<HoaDonChiTietModel> listNEW = new ArrayList<>();
        // lấy ra list HDCT có mã HD đang chọn
        for (HoaDonChiTietModel hd : listHDCT) {
            if (hd.getIdhd().getMa() != null && hd.getIdhd().getMa().equals(maHD)) {
                listNEW.add(hd);
            }
        }
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat vn = NumberFormat.getInstance(localeVN);
        // lấy ra hóa đơn chi tiết có trong hóa đơn và mã imei
        for (HoaDonChiTietModel hdct : listNEW) {

            dem++;
            dtmGH.addRow(new Object[]{
                dem,
                hdct.getIdctsp().getSp().getTen() + " " + hdct.getIdctsp().getDl().getSoDungLuong() + " " + hdct.getIdctsp().getMs().getTen(),
                vn.format(hdct.getDongia()),
                hdct.getSl(),
                hdct.getIdctsp().getCtkm(),
                hdct.getIdctsp().getCtkm() == null ? vn.format(hdct.getDongia()) : vn.format(giamGia(hdct.getIdctsp().getCtkm().getHinhThuc(), hdct.getDongia())),
                hdct.getGhiChu()
            });

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JBanHang = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        btnXoaCTSP = new javax.swing.JButton();
        btnQR = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiemSP = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        btnKH = new javax.swing.JButton();
        btnThay = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        txtTongTien = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txtGiamGia = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtTienKH = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txtGhiChu = new javax.swing.JTextField();
        btnThanhToan = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        txtMaCoupon = new javax.swing.JTextField();
        cbbPhuongThucThanhToan = new javax.swing.JComboBox<>();
        btnInHD = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        btnTaoHoaDon = new javax.swing.JButton();
        cbbTrangThaiHoaDon = new javax.swing.JComboBox<>();
        btnHuyHD = new javax.swing.JButton();

        JBanHang.setBackground(new java.awt.Color(238, 232, 170));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ hàng"));
        jPanel3.setOpaque(false);

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên SP", "Đơn giá", "Số lượng", "CTKM", "Thành tiền", "IMEI"
            }
        ));
        jScrollPane4.setViewportView(tblGioHang);

        btnXoaCTSP.setText("Xóa ");
        btnXoaCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaCTSPActionPerformed(evt);
            }
        });

        btnQR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/qr-scan.png"))); // NOI18N
        btnQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQRActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnXoaCTSP)
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnQR, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnXoaCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnQR, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách sản phẩm"));
        jPanel4.setOpaque(false);

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản Phẩm", "CTKM", "Số lượng tồn", "Đơn giá", "Thành tiền"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblSanPham);

        jLabel1.setText("Tìm kiếm");

        txtTimKiemSP.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemSPCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Đơn hàng"));
        jPanel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.setOpaque(false);

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.setOpaque(false);

        jLabel30.setText("Số điện thoại KH");

        jLabel31.setText("Tên KH");

        txtTenKH.setEnabled(false);

        btnKH.setText("Chọn");
        btnKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKHActionPerformed(evt);
            }
        });

        btnThay.setText("Thay");
        btnThay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThay, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnKH, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtSDT, txtTenKH});

        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThay))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel32.setText("Mã HĐ");

        txtMaHD.setEditable(false);
        txtMaHD.setEnabled(false);

        txtTongTien.setEditable(false);
        txtTongTien.setEnabled(false);
        txtTongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongTienActionPerformed(evt);
            }
        });

        jLabel33.setText("Tổng tiền");

        txtGiamGia.setEditable(false);
        txtGiamGia.setEnabled(false);

        jLabel34.setText("Giảm giá");

        jLabel35.setText("Phương thức thanh toán");

        txtTienKH.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienKHCaretUpdate(evt);
            }
        });

        jLabel36.setText("Tiền khách đưa");

        txtTienThua.setEditable(false);
        txtTienThua.setEnabled(false);

        jLabel37.setText("Tiền thừa");

        jLabel39.setText("Tên NV");

        jLabel40.setText("Mã Coupon");

        jLabel41.setText("Ghi chú");

        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        jLabel38.setText("VND");

        jLabel42.setText("VND");

        jLabel43.setText("VND");

        jLabel44.setText("VND");

        cbbPhuongThucThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển Khoản", "Thẻ" }));
        cbbPhuongThucThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbPhuongThucThanhToanActionPerformed(evt);
            }
        });

        btnInHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/print.png"))); // NOI18N
        btnInHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMaCoupon)
                                    .addComponent(txtGiamGia)
                                    .addComponent(cbbPhuongThucThanhToan, 0, 132, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel44))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtTienKH, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(84, 84, 84)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnInHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(59, 59, 59))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36)
                    .addComponent(jLabel42))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jLabel37)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(cbbPhuongThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(txtMaCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel41)
                    .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInHD, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn"));
        jPanel8.setOpaque(false);

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã HĐ", "Tên NV", "Tên KH", "Ngày tạo", "Tình trạng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblHoaDon);
        if (tblHoaDon.getColumnModel().getColumnCount() > 0) {
            tblHoaDon.getColumnModel().getColumn(0).setMaxWidth(65);
            tblHoaDon.getColumnModel().getColumn(1).setMaxWidth(180);
            tblHoaDon.getColumnModel().getColumn(2).setMaxWidth(180);
            tblHoaDon.getColumnModel().getColumn(3).setMaxWidth(100);
            tblHoaDon.getColumnModel().getColumn(4).setMaxWidth(100);
        }

        btnTaoHoaDon.setText("Tạo hóa đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        cbbTrangThaiHoaDon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chưa TT", "Đã thanh toán", "Hóa Đơn Hủy" }));
        cbbTrangThaiHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTrangThaiHoaDonActionPerformed(evt);
            }
        });

        btnHuyHD.setText("Hủy hóa đơn ");
        btnHuyHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnHuyHD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(cbbTrangThaiHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(cbbTrangThaiHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTaoHoaDon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHuyHD)
                        .addGap(0, 61, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout JBanHangLayout = new javax.swing.GroupLayout(JBanHang);
        JBanHang.setLayout(JBanHangLayout);
        JBanHangLayout.setHorizontalGroup(
            JBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JBanHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );
        JBanHangLayout.setVerticalGroup(
            JBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JBanHangLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JBanHangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(JBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        String tienThua = txtTienThua.getText();
        String tongTien = txtTongTien.getText();
        float thanhTien = Float.valueOf(tongTien);
        float tienKH = Float.valueOf(txtTienKH.getText());
        int tienThua_number = 0;
        if (tienThua != null) {
            tienThua_number = Integer.valueOf(tienThua);
        }
        if (tienKH < thanhTien) {
            JOptionPane.showMessageDialog(null, "Chưa đủ điều kiện thanh toán");
        }

        HoaDonModel newHD = new HoaDonModel();
        newHD.setMa(txtMaHD.getText());
        newHD.setThanhTien(thanhTien);
        newHD.setHinhThucThanhToan(cbbPhuongThucThanhToan.getSelectedIndex());
        newHD.setTrangThai(1);
        Coupon cp = new Coupon();
        cp.setMa(txtMaCoupon.getText());
        newHD.setCp(cp);
        String idHD = "";
        ArrayList<HoaDonModel> listHDM = iHoaDonService.getAllHoaDon();
        for (HoaDonModel x : listHDM) {
            if (x.getMa() != null && x.getMa().equals(txtMaHD.getText())) {
                idHD = x.getId();
            }
        }
        if (idHD == null) {
            return;
        }
        ArrayList<HoaDonChiTietModel> listHDCTByIDHD = iHoaDonChiTietService.getAllHoaDonCTBYIDHD(idHD);
        if (iHoaDonService.upadteHD_ThanhToan(newHD) != null) {
            System.out.println(newHD.getTrangThai());
            System.out.println(newHD.getNgayThanhToan());
            for (HoaDonChiTietModel u : listHDCTByIDHD) {
                if (u.getGhiChu() != null && iIMEIService.getTrangThaiByIMEI(u.getGhiChu()).getTrangThai() == 1) {
                    JOptionPane.showMessageDialog(null, "Sản phẩm có mã IMEI:" + u.getGhiChu() + " đã bán");
                    iHoaDonChiTietService.delete(u.getGhiChu(), idHD);
                    loadGioHang();
                    return;
                }
                iIMEIService.updateIMEI_ThanhToan(u.getGhiChu());
            }
            JOptionPane.showMessageDialog(this, "Thanh toán thành công");

            loadHDTT();
            btnThanhToan.setEnabled(false);
            btnInHD.setEnabled(true);

        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQRActionPerformed
        new QuetQR(tblGioHang, tblHoaDon, this).setVisible(true);

    }//GEN-LAST:event_btnQRActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        int index = tblHoaDon.getSelectedRow();
        if (index >= 0) {
            btnKH.setEnabled(true);
            btnThay.setEnabled(true);
        }
        int hinhThuc = 0;
        String maHD = tblHoaDon.getValueAt(index, 0).toString();
        ArrayList<HoaDonModel> list = iHoaDonService.getAllHoaDon();
        for (HoaDonModel x : list) {
            if (x.getMa() != null && x.getMa().equals(maHD)) {
                hinhThuc = x.getHinhThucThanhToan();
            }
        }
        cbbPhuongThucThanhToan.setSelectedIndex(hinhThuc);
        txtMaHD.setText(tblHoaDon.getValueAt(index, 0).toString());
        txtTenKH.setText(tblHoaDon.getValueAt(index, 2).toString());
        txtTenNV.setText(tblHoaDon.getValueAt(index, 1).toString());
        if (tblHoaDon.getValueAt(index, 4).toString().equalsIgnoreCase("đã thanh toán")) {
            btnThanhToan.setEnabled(false);
            btnInHD.setEnabled(true);
        } else {
            btnThanhToan.setEnabled(true);
             btnInHD.setEnabled(false);
        }
        TongTien();
        loadGioHang();
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        ArrayList<HoaDonModel> listHD = iHoaDonService.getAllHoaDon();
        KhachHangModel khMD = iKhachHangService.getTimKH("0000000000");
        KhachHang kh = new KhachHang();
        kh.setId(khMD.getId());
        kh.setMaKH(khMD.getMaKH());
        kh.setHoTen(khMD.getHoTen());
        kh.setSdt(khMD.getSdt());
        int soHD = listHD.size() + 1;
        String maHD = "HD" + soHD;
        NhanVienModel nv = view.Login.nv;
        NhanVien nvNew = new NhanVien(nv.getId(), nv.getMa(), nv.getHoTen());
        HoaDonModel hd = new HoaDonModel();
        hd.setNv(nvNew);
        hd.setKh(kh);
        hd.setMa(maHD);
        hd.setTrangThai(0);
        hd.setCp(null);
        hd.setThanhTien(0);
        hd.setHinhThucThanhToan(0);
        if (iHoaDonService.insertHD(hd) != null) {
            JOptionPane.showMessageDialog(null, "Thêm hóa đơn thành công!");
            loadHDCho();
        } else {
            JOptionPane.showMessageDialog(null, "Thêm thất bại");
            return;
        }
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int indexSP = tblSanPham.getSelectedRow();
        ArrayList<HoaDonChiTietModel> listHDCT = iHoaDonChiTietService.getAllHoaDonCT();
        int indexHD = tblHoaDon.getSelectedRow();
        if (indexHD < 0) {
            JOptionPane.showMessageDialog(null, "Mời bạn chọn hóa đơn!");
            return;
        }
        String maHD = tblHoaDon.getValueAt(indexHD, 0).toString();
        // Lấy ID Hóa Đơn
        HoaDon hd = new HoaDon();
        ArrayList<HoaDonModel> listHD = iHoaDonService.getAllHoaDon();
        for (HoaDonModel h : listHD) {
            if (h.getMa() != null && h.getMa().equals(maHD)) {
                hd.setId(h.getId());
            }
        }
        System.out.println("ID Hóa đơn: " + hd.getId());
        // Check số lượng tồn
        String soLuongTon = tblSanPham.getValueAt(indexSP, 3).toString();
        if (Integer.valueOf(soLuongTon) <= 0) {
            JOptionPane.showMessageDialog(null, "Số lượng tồn không đủ");
            return;
        }
        // Lấy Id CTSP
        String maSP = tblSanPham.getValueAt(indexSP, 0).toString();
        CTSanPham c = new CTSanPham();
        ArrayList<CTSanPhamModel> listCTSP = iCTSanPhamService.getAllCTSanPham();
        for (CTSanPhamModel x : listCTSP) {
            if (x.getMa() != null && x.getMa().equals(maSP)) {
                c.setId(x.getId());
            }
        }
        int dem = 0;
        String ghiChu = "";
        ArrayList<IMEIModel> listIMEINEW = iIMEIService.selectSL(c.getId());
        // list imei được chọn từ ctsp còn tồn
        ArrayList<HoaDonChiTietModel> listHDCTNEW = iHoaDonChiTietService.getAllHoaDonCTBYIDHD(hd.getId());
        // nhập vào imei
        String maIMEI = (String) JOptionPane.showInputDialog(null, "Mời bạn chọn mã IMEI", "Lựa chọn", JOptionPane.INFORMATION_MESSAGE, null, null, "Mã IMEI");
        if (maIMEI.isEmpty()) {
            return;
        }
        for (IMEIModel ss : listIMEINEW) {
            System.out.println("IMEI " + ss.getMa());
            if (ss.getMa() != null) {
                if (ss.getMa().equals(maIMEI)) {
                    ghiChu = ss.getMa();
                    dem++;
                }
            }
        }
        if (dem == 0) {
            JOptionPane.showMessageDialog(null, "Sai mã IMEI hoặc mã imei không tồn tại");
            return;
        }
        // kiểm tra imei uvằ nhập vào
        for (HoaDonChiTietModel i : listHDCTNEW) {
            if (i.getGhiChu() != null && i.getGhiChu().equals(maIMEI)) {
                JOptionPane.showMessageDialog(null, "IMEI đã tồn tại trong đơn hàng");
                return;
            }
        }
        System.out.println("Mã IMEI SAU" + maIMEI);
        String donGia = tblSanPham.getValueAt(indexSP, 4).toString();
        String thanhTien = tblSanPham.getValueAt(indexSP, 5).toString();
        HoaDonChiTietModel hdct = new HoaDonChiTietModel();
        hdct.setSl(1);
        hdct.setIdctsp(c);
        hdct.setIdhd(hd);
        hdct.setGhiChu(maIMEI);
        hdct.setDongia(Float.valueOf(donGia.replace(".", "")));
        hdct.setThanhTien(Float.valueOf(thanhTien.replace(".", "")));
        if (hdct == null) {
            return;
        }

        iHoaDonChiTietService.insertHDCT(hdct);
        TongTien();
        loadGioHang();
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void cbbTrangThaiHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTrangThaiHoaDonActionPerformed
        if (cbbTrangThaiHoaDon.getSelectedIndex() == 0) {
            loadHDCho();
        }
        if (cbbTrangThaiHoaDon.getSelectedIndex() == 1) {
            loadHDTT();
        }
        if (cbbTrangThaiHoaDon.getSelectedIndex() == 2) {

        }
    }//GEN-LAST:event_cbbTrangThaiHoaDonActionPerformed

    private void btnXoaCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaCTSPActionPerformed
        int indexGH = tblGioHang.getSelectedRow();
        String ghiChu = tblGioHang.getValueAt(indexGH, 6).toString();
        String idHDCT = "";
        ArrayList<HoaDonChiTietModel> listHDCTModel = iHoaDonChiTietService.getAllHoaDonCT();
        for (HoaDonChiTietModel g : listHDCTModel) {
            if (g.getGhiChu() != null && g.getGhiChu().equals(ghiChu)) {
                idHDCT = g.getId();
            }
        }
        if (JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không?", "Thông báo", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            return;
        }
        if (iHoaDonChiTietService.deleteHDCT(idHDCT) != null) {
            JOptionPane.showMessageDialog(null, "Xóa thành công");
            TongTien();
            loadGioHang();
        }
    }//GEN-LAST:event_btnXoaCTSPActionPerformed

    private void btnKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKHActionPerformed
        KhachHang_BanHang k1 = new KhachHang_BanHang();
        k1.setVisible(true);


    }//GEN-LAST:event_btnKHActionPerformed

    private void btnThayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThayActionPerformed
        KhachHang_BanHang b2 = new KhachHang_BanHang();
        txtTenKH.setText(KhachHang_BanHang.k111.getHoTen());
        txtSDT.setText(KhachHang_BanHang.k111.getSdt());
        int indexHD = tblHoaDon.getSelectedRow();
        String maHD = tblHoaDon.getValueAt(indexHD, 0).toString();
        KhachHangModel kh = iKhachHangService.getTimKH(KhachHang_BanHang.k111.getSdt());
        KhachHang khNew = new KhachHang();
        khNew.setId(kh.getId());
        HoaDonModel hd = new HoaDonModel();
        hd.setKh(khNew);
        hd.setMa(maHD);
        if (iHoaDonService.upadteHD(hd) != null) {
            JOptionPane.showMessageDialog(null, "Cập nhật thành công");
            loadHD();
        }

    }//GEN-LAST:event_btnThayActionPerformed

    private void txtTongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongTienActionPerformed

    }//GEN-LAST:event_txtTongTienActionPerformed

    private void txtTienKHCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienKHCaretUpdate
        // Tiền thừa
        String tienKH = "";
        int tienThanhToan = 0;
        if (!txtTienKH.getText().trim().isEmpty()) {
            tienKH = txtTienKH.getText();
            tienThanhToan = Integer.valueOf(tienKH);
        }
        int tong = Integer.valueOf(txtTongTien.getText());
        if (tienThanhToan >= tong) {
            txtTienThua.setText(String.valueOf(tienThanhToan - tong));
        } else {
            txtTienThua.setText("");

        }
    }//GEN-LAST:event_txtTienKHCaretUpdate

    private void cbbPhuongThucThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbPhuongThucThanhToanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbPhuongThucThanhToanActionPerformed

    private void btnInHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHDActionPerformed

        String h1 = txtMaHD.getText();
        try {
            Hashtable map = new Hashtable();
            JasperReport rpt = JasperCompileManager.compileReport("src\\view\\HoaDon.jrxml");
            map.put("sMAHD", h1);
            System.out.println(h1);

            JasperPrint p = JasperFillManager.fillReport(rpt, map, DB_Context.getConnection());

            JasperViewer.viewReport(p, false);
        } catch (JRException ex) {
            Logger.getLogger(BanHangJpanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnInHD.setEnabled(false);
    }//GEN-LAST:event_btnInHDActionPerformed

    private void txtTimKiemSPCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemSPCaretUpdate
        String timKiem = txtTimKiemSP.getText();
        ArrayList<CTSanPhamModel> list = iCTSanPhamService.getAllCTSanPham();
        ArrayList<CTSanPhamModel> listNEW = new ArrayList<>();
        String tenSP = "";
        for (CTSanPhamModel x : list) {
            tenSP = x.getSp().getTen() + " " + x.getMs().getTen() + " " + x.getDl().getSoDungLuong();
            if (x.getSp().getTen() != null && tenSP.toLowerCase().contains(timKiem.toLowerCase())) {
                listNEW.add(x);
            }
        }
        if (listNEW.size() < 0) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu");
            return;
        }
        dtmSP.setRowCount(0);
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat vn = NumberFormat.getInstance(localeVN);
        for (CTSanPhamModel c : listNEW) {
            ArrayList<IMEIModel> listIMEI = iIMEIService.selectSL(c.getId());
            dtmSP.addRow(new Object[]{
                c.getMa(),
                c.getSp().getTen() + " " + c.getDl().getSoDungLuong() + "GB " + c.getMs().getTen(),
                c.getCtkm(),
                listIMEI.size(),
                vn.format(c.getGiaBan()),
                c.getCtkm() == null ? vn.format(c.getGiaBan()) : vn.format(giamGia(c.getCtkm().getHinhThuc(), c.getGiaBan()))
            });
        }
    }//GEN-LAST:event_txtTimKiemSPCaretUpdate

    private void btnHuyHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyHDActionPerformed
        int row = tblHoaDon.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "mời bạn chọn HĐ");
            return;
        }
        HoaDonModel hds = new HoaDonModel();
      String lydo=  JOptionPane.showInputDialog(this, "Nhập lí do hủy hóa đơn");
        String mahd = tblHoaDon.getValueAt(row, 0).toString();
        hds.setMa(mahd);
        hds.setGhiChu(lydo);
        if (iHoaDonService.updateHuyHD(hds) != null) {
            JOptionPane.showMessageDialog(this, "xóa hóa đơn thành công");
            loadHDCho();loadHDTT();
            loadSP();
        }
        
        
    }//GEN-LAST:event_btnHuyHDActionPerformed
    private void TongTien() {
        int indexHD = tblHoaDon.getSelectedRow();
        String maHD = tblHoaDon.getValueAt(indexHD, 0).toString();
        String idHD = "";
        ArrayList<HoaDonModel> listHDM = iHoaDonService.getAllHoaDon();
        for (HoaDonModel x : listHDM) {
            if (x.getMa() != null && x.getMa().equals(maHD)) {
                idHD = x.getId();
            }
        }
        ArrayList<HoaDonChiTietModel> listHDCTM = iHoaDonChiTietService.getAllHoaDonCTBYIDHD(idHD);
        int tong = 0;
        for (HoaDonChiTietModel y : listHDCTM) {
            tong += y.getThanhTien();
        }
        txtTongTien.setText(String.valueOf(tong));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JBanHang;
    private javax.swing.JButton btnHuyHD;
    private javax.swing.JButton btnInHD;
    private javax.swing.JButton btnKH;
    private javax.swing.JButton btnQR;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThay;
    private javax.swing.JButton btnXoaCTSP;
    private javax.swing.JComboBox<String> cbbPhuongThucThanhToan;
    private javax.swing.JComboBox<String> cbbTrangThaiHoaDon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtMaCoupon;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTienKH;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTimKiemSP;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables

}
