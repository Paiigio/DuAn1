/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.ThanhPhan;

import DomainModels.CTSanPham;
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
import ViewModel.CTSanPhamModel;
import ViewModel.HoaDonChiTietModel;
import ViewModel.HoaDonModel;
import ViewModel.IMEIModel;
import ViewModel.KhachHangModel;
import ViewModel.NhanVienModel;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
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
//    public KhachHangModel kh = new KhachHangModel();

    /**
     * Creates new form BanHangJpanel1
     */
    public BanHangJpanel() {

        initComponents();
        dtmSP = (DefaultTableModel) tblSanPham.getModel();
        dtmHD = (DefaultTableModel) tblHoaDon.getModel();
        dtmGH = (DefaultTableModel) tblGioHang.getModel();
        load();
        

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
                listIMEI.size(),
                vn.format(x.getGiaBan())
            });
        }
    }

    private void load() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    loadSP();
                    loadHD();
//                    loadCBBSP();
//                    loadCBBMS();
//                    loadCBBDL();
//                    loadCBBTKDL();
//                    loadCBBTKMS();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThongKeJpanel.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }).start();
    }

    private void loadHD() {
        ArrayList<HoaDonModel> listHD = iHoaDonService.getAllHoaDon();
        dtmHD.setRowCount(0);
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
        for (HoaDonChiTietModel hdct : listNEW) {
            // lấy ra những mã imei có mã sản phẩm đang có trong hóa đơn chi tiết
            ArrayList<IMEIModel> listI = iIMEIService.selectSL(hdct.getIdctsp().getId());
            for (IMEIModel x : listI) {
                if (x.getGhiChu() != null) {
                    List<String> listString = tachChuoi(x.getGhiChu());
                    System.out.println(listString);
                    for (String s : listString) {
                        System.out.println(s);
                        if (maHD.equals(s)) {
                            maIMEI = x.getMa();

                            dem++;
                            dtmGH.addRow(new Object[]{
                                dem,
                                hdct.getIdctsp().getSp().getTen() + " " + hdct.getIdctsp().getDl().getSoDungLuong() + " " + hdct.getIdctsp().getMs().getTen(),
                                vn.format(hdct.getDongia()),
                                hdct.getSl(),
                                vn.format(hdct.getSl() * hdct.getDongia()),
                                maIMEI
                            });
                        }
                    }
                }
            }
        }

//                if (maHD.trim().equals(tblHoaDon.getValueAt(index, 0).toString())) {
//                    dem++;
//                    for (HoaDonChiTietModel hdct : listNEW) {
//                        dtmGH.addRow(new Object[]{
//                            dem,
//                            hdct.getIdctsp().getSp().getTen() + " " + hdct.getIdctsp().getDl().getSoDungLuong() + " " + hdct.getIdctsp().getMs().getTen(),
//                            vn.format(hdct.getDongia()),
//                            hdct.getSl(),
//                            vn.format(hdct.getSl() * hdct.getDongia()),
//                            maIMEI
//                        });
//
//                }
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
        jLabel32 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        btnVoucher = new javax.swing.JButton();
        txtTongTien = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txtGiamGia = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtThanhToan = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtTienKH = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txtGhiChu = new javax.swing.JTextField();
        btn1 = new javax.swing.JButton();
        btn = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        txtMaCoupon = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        btnTaoHoaDon = new javax.swing.JButton();
        cbbTrangThaiHoaDon = new javax.swing.JComboBox<>();

        JBanHang.setBackground(new java.awt.Color(238, 232, 170));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ hàng"));

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên SP", "Đơn giá", "Số lượng", "Thành tiền", "IMEI"
            }
        ));
        jScrollPane4.setViewportView(tblGioHang);

        btnXoaCTSP.setText("Xóa ");

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
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnXoaCTSP)
                        .addContainerGap(12, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btnQR, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
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

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản Phẩm", "Số lượng tồn", "Đơn giá"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblSanPham);

        jLabel1.setText("Tìm kiếm");

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

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel30.setText("Số điện thoại KH");

        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        jLabel31.setText("Tên KH");

        txtTenKH.setEnabled(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel32.setText("Mã HĐ");

        txtMaHD.setEditable(false);
        txtMaHD.setEnabled(false);

        btnVoucher.setText("Voucher");
        btnVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoucherActionPerformed(evt);
            }
        });

        txtTongTien.setEditable(false);
        txtTongTien.setEnabled(false);

        jLabel33.setText("Tổng tiền");

        txtGiamGia.setEditable(false);
        txtGiamGia.setEnabled(false);

        jLabel34.setText("Giảm giá");

        txtThanhToan.setEditable(false);
        txtThanhToan.setEnabled(false);

        jLabel35.setText("Thanh toán");

        jLabel36.setText("Tiền khách đưa");

        txtTienThua.setEditable(false);
        txtTienThua.setEnabled(false);

        jLabel37.setText("Tiền thừa");

        jLabel39.setText("Tên NV");

        jLabel40.setText("Mã Coupon");

        jLabel41.setText("Ghi chú");

        btn1.setText("jButton9");
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        btn.setText("Thanh Toán");
        btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionPerformed(evt);
            }
        });

        jLabel38.setText("VND");

        jLabel42.setText("VND");

        jLabel43.setText("VND");

        jLabel44.setText("VND");

        jLabel45.setText("VND");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(40, 40, 40)
                                        .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel40))
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(txtMaCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(27, 27, 27)
                                                .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel36)
                                                .addGap(3, 3, 3)
                                                .addComponent(txtTienKH, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(34, 34, 34)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(btnVoucher)
                                .addGap(65, 65, 65)
                                .addComponent(btn1)))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txtThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(txtTienKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(txtMaCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVoucher)
                    .addComponent(btn1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(btn, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn"));

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
            tblHoaDon.getColumnModel().getColumn(0).setMaxWidth(60);
            tblHoaDon.getColumnModel().getColumn(1).setMaxWidth(180);
            tblHoaDon.getColumnModel().getColumn(2).setMaxWidth(180);
            tblHoaDon.getColumnModel().getColumn(3).setMaxWidth(90);
            tblHoaDon.getColumnModel().getColumn(4).setMaxWidth(80);
        }

        btnTaoHoaDon.setText("Tạo hóa đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        cbbTrangThaiHoaDon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Chưa thanh toán", "Đã thanh toán" }));
        cbbTrangThaiHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTrangThaiHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTaoHoaDon)
                    .addComponent(cbbTrangThaiHoaDon, 0, 103, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnTaoHoaDon)
                        .addGap(18, 18, 18)
                        .addComponent(cbbTrangThaiHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 89, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout JBanHangLayout = new javax.swing.GroupLayout(JBanHang);
        JBanHang.setLayout(JBanHangLayout);
        JBanHangLayout.setHorizontalGroup(
            JBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JBanHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(998, Short.MAX_VALUE))
        );
        JBanHangLayout.setVerticalGroup(
            JBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JBanHangLayout.createSequentialGroup()
                .addGroup(JBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JBanHangLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JBanHangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2240, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(20, Short.MAX_VALUE)
                    .addComponent(JBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(20, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 783, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(65, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoucherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVoucherActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn1ActionPerformed

    private void btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActionPerformed

    private void btnQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQRActionPerformed
        QuetQR qr = new QuetQR();
        qr.setVisible(true);
    }//GEN-LAST:event_btnQRActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        int index = tblHoaDon.getSelectedRow();
        txtMaHD.setText(tblHoaDon.getValueAt(index, 0).toString());
        txtTenKH.setText(tblHoaDon.getValueAt(index, 2).toString());
        txtTenNV.setText(tblHoaDon.getValueAt(index, 1).toString());
//        String maIMEI = "";
//        String maHD = "";
//        ArrayList<HoaDonChiTietModel> listHDCT = iHoaDonChiTietService.getAllHoaDonCT();
//        Locale localeVN = new Locale("vi", "VN");
//        NumberFormat vn = NumberFormat.getInstance(localeVN);
//        for (int i = 0; i < listHDCT.size(); ++i) {
//            ArrayList<IMEIModel> listI = iIMEIService.getAllIMEI();
//            for (IMEIModel x : listI) {
//                List<String> listString = tachChuoi(x.getGhiChu());
//                for (String s : listString) {
//                    System.out.println(s);
//                    if (listHDCT.get(i).getIdhd().getMa() != null && listHDCT.get(i).getIdhd().getMa().equals(s)) {
//                        maIMEI = x.getMa();
//                        maHD = listHDCT.get(i).getIdhd().getMa();
//                    }
//                }
//            }
//            dtmGH.setRowCount(0);
//            if (maHD.trim().equals(tblHoaDon.getValueAt(index, 0).toString())) {
//                dtmGH.addRow(new Object[]{
//                    i + 1,
//                    listHDCT.get(i).getIdctsp().getSp().getTen() + " " + listHDCT.get(i).getIdctsp().getDl().getSoDungLuong() + " " + listHDCT.get(i).getIdctsp().getMs().getTen(),
//                    vn.format(listHDCT.get(i).getDongia()),
//                    listHDCT.get(i).getSl(),
//                    vn.format(listHDCT.get(i).getDongia() * listHDCT.get(i).getSl()),
//                    maIMEI
//                });
//            }
//        }
        loadGioHang();
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        ArrayList<HoaDonModel> listHD = iHoaDonService.getAllHoaDon();

        ArrayList<KhachHangModel> listKH = iKhachHangService.getAllKH();
        KhachHang kh = new KhachHang();
        for (KhachHangModel x : listKH) {
            if (x.getSdt() != null && x.getSdt().equals(txtSDT.getText())) {
                kh.setId(x.getId());
                kh.setMaKH(x.getMaKH());
                kh.setHoTen(x.getHoTen());
                kh.setSdt(x.getSdt());
            }
        }
        if (txtSDT.getText().trim().isEmpty()) {
            kh.setId(iKhachHangService.getTimKH("0000000000").get(0).getId());
            kh.setHoTen("Khách lẻ");
            kh.setMaKH("KH9999");

        }
        int soHD = listHD.size() + 1;
        String maHD = "HD" + soHD;
        NhanVienModel nv = view.Login.nv;
        NhanVien nvNew = new NhanVien();
        nvNew.setId(nv.getId());
        nvNew.setHoTen(nv.getHoTen());
        nvNew.setMa(nv.getMa());
        HoaDonModel hd = new HoaDonModel();
        hd.setNv(nvNew);
        hd.setKh(kh);
        hd.setMa(maHD);
        hd.setTrangThai(0);
        hd.setCp(null);
        hd.setThanhTien(0);
        hd.setHinhThucThanhToan(0);
        if (iHoaDonService.insertHD(hd) != null) {
            JOptionPane.showMessageDialog(this, "Thêm hóa đơn thành công!");
            loadHD();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
            return;
        }
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int indexSP = tblSanPham.getSelectedRow();
        ArrayList<HoaDonChiTietModel> listHDCT = iHoaDonChiTietService.getAllHoaDonCT();
        int indexHD = tblHoaDon.getSelectedRow();
        if (indexHD < 0) {
            JOptionPane.showMessageDialog(this, "Moi ban chon don hang");
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
        System.out.println("ID Hóa đơn: "+hd.getId());
        // Check số lượng tồn
        String soLuongTon = tblSanPham.getValueAt(indexSP, 2).toString();
        if (Integer.valueOf(soLuongTon) <= 0) {
            JOptionPane.showMessageDialog(this, "Số lượng tồn không đủ");
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
        System.out.println("ID Chi tiết sản phẩm: "+c.getId());
        // list imei được chọn từ ctsp còn tồn
        ArrayList<IMEIModel> listIMEI = iIMEIService.selectSL(c.getId());
        IMEIModel imei = new IMEIModel();
        // nhập vào imei
        String maIMEI = (String) JOptionPane.showInputDialog(this, "Mời bạn chọn mã IMEI", "Lựa chọn", JOptionPane.INFORMATION_MESSAGE, null, null, "Mã IMEI");
        System.out.println("Mã vừa nhập :"+maIMEI);
        String ghiChu = "";
        String maIM = maIMEI;
        int dem = 0;
        // kiểm tra imei uvằ nhập vào
        for (IMEIModel i : listIMEI) {
            System.out.println("Mã imei trong list"+i.getMa());
            if (i.getMa()!= null && i.getMa().equals(maIMEI)) {
                ghiChu = i.getGhiChu();
                List<String> listS = tachChuoi(i.getGhiChu());
                if (listS != null) {
                    for (String st : listS) {
                        if (maHD.equals(st)) {
                            JOptionPane.showMessageDialog(this, "IMEI đã tồn tại trong đơn hàng");
                            return;
                        } else {
                            dem++;
                            imei.setGhiChu(i.getGhiChu() + " " + maHD);
                        }
                    }
                } else {
                    dem++;
                    imei.setGhiChu(maHD);
                }
                imei.setMa(i.getMa());
                imei.setId(i.getId());
            }
        }
        if (dem == 0) {
            JOptionPane.showMessageDialog(this, "Sai mã IMEI hoặc mã imei không tồn tại");
            return;
        }
        String donGia = tblSanPham.getValueAt(indexSP, 3).toString();
        HoaDonChiTietModel hdct = new HoaDonChiTietModel();
        hdct.setSl(1);
        hdct.setIdctsp(c);
        hdct.setIdhd(hd);
        hdct.setDongia(Float.valueOf(donGia.replace(".", "")));
        hdct.setThanhTien(Float.valueOf(donGia.replace(".", "")));
        if (hdct == null) {
            return;
        }

        iHoaDonChiTietService.insertHDCT(hdct);
        iIMEIService.updateIMEI(imei);
        loadGioHang();
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        String sdt = txtSDT.getText();
        int dem = 0;
        ArrayList<KhachHangModel> listKH = iKhachHangService.getAllKH();
        for (KhachHangModel x : listKH) {
            if (x.getSdt() != null && x.getSdt().equals(sdt)) {
                dem++;
                txtTenKH.setText(x.getHoTen());
            }
        }
        if (dem == 0) {
            txtTenKH.setText("");
            JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng");
            return;
        }
    }//GEN-LAST:event_txtSDTActionPerformed

    private void cbbTrangThaiHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTrangThaiHoaDonActionPerformed
        if (cbbTrangThaiHoaDon.getSelectedIndex() == 0) {
            loadHD();
        }
        if (cbbTrangThaiHoaDon.getSelectedIndex() == 1) {
            ArrayList<HoaDonModel> listHD = iHoaDonService.getAllHoaDonCTT();
            dtmHD.setRowCount(0);
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
        if (cbbTrangThaiHoaDon.getSelectedIndex() == 2) {
            ArrayList<HoaDonModel> listHD = iHoaDonService.getAllHoaDonTT();
            dtmHD.setRowCount(0);
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
    }//GEN-LAST:event_cbbTrangThaiHoaDonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JBanHang;
    private javax.swing.JButton btn;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btnQR;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnVoucher;
    private javax.swing.JButton btnXoaCTSP;
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
    private javax.swing.JLabel jLabel45;
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
    private javax.swing.JTextField txtThanhToan;
    private javax.swing.JTextField txtTienKH;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTimKiemSP;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
private List tachChuoi(String s) {
        List<String> list = new ArrayList<>();
        String[] mang = s.split(" ");
        if (mang == null) {
            return null;
        }
        for (String x : mang) {
            list.add(x);
        }
        return list;
    }
}
