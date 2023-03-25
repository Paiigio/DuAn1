/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.ThanhPhan;

import DomainModels.ChucVu;
import DomainModels.Coupon;
import DomainModels.HoaDon;
import DomainModels.KhachHang;
import DomainModels.NhanVien;
import Service.HoaDonChiTietService;
import Service.HoaDonService;
import Utilites.JDBC_Helper;
import ViewModel.HoaDonChiTietModel;
import ViewModel.HoaDonModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import responsitory.CouponReponsitory;
import responsitory.KhachHangResponsitory;
import responsitory.NhanVienResponsitory;

/**
 *
 * @author duong
 */
public class HoaDonJpanel extends javax.swing.JPanel {

    private DefaultTableModel dtm = new DefaultTableModel();
    private DefaultTableModel dt = new DefaultTableModel();
    private HoaDonService hds = new HoaDonService();
    private HoaDonChiTietService hdcts = new HoaDonChiTietService();
    DefaultComboBoxModel<HoaDon> dcmCV;
    private KhachHangResponsitory khr = new KhachHangResponsitory();
    private NhanVienResponsitory nvr = new NhanVienResponsitory();
    private CouponReponsitory cr = new CouponReponsitory();
    long count, soTrang, Trang = 1;

    public HoaDonJpanel() {
        initComponents();
        dtm = (DefaultTableModel) tblHoaDon.getModel();
        dt = (DefaultTableModel) tblHDCT.getModel();
        countHD();
        if (count % 3 == 0) {
            soTrang = count / 3;
        } else {
            soTrang = count / 3 + 1;
        }
        loadTable(1);
        lblTrang.setText("1/" + soTrang);
    }

    
    public void countHD() {
        try {
            String sql = "SELECT count(*) From HOADON";
            ResultSet rs = JDBC_Helper.excuteQuery(sql);
            while (rs.next()) {
                count = rs.getLong(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public void countHDTT() {
        try {
            String sql = "SELECT count(*) From HOADON WHERE TRANGTHAI=1";
            ResultSet rs = JDBC_Helper.excuteQuery(sql);
            while (rs.next()) {
                count = rs.getLong(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
            public void countHDCTT() {
        try {
            String sql = "SELECT count(*) From HOADON WHERE TRANGTHAI=0";
            ResultSet rs = JDBC_Helper.excuteQuery(sql);
            while (rs.next()) {
                count = rs.getLong(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<HoaDonModel> getAllHoaDon() {
        ArrayList<HoaDonModel> list = new ArrayList<>();
        String sql = "SELECT TOP 3 * FROM HOADON where MAHD not in (SELECT TOP " + (Trang * 3 - 3) + " MAHD FROM HOADON)";
        ResultSet rs = JDBC_Helper.excuteQuery(sql);
        try {
            while (rs.next()) {
                KhachHang kh = khr.getKHByID(rs.getString(2));
                NhanVien nv = nvr.getNVByID(rs.getString(3));
                Coupon cp = cr.getCPByID(rs.getString(4));
                list.add(new HoaDonModel(rs.getString(1), kh, nv, cp, rs.getString(5), rs.getFloat(6), rs.getInt(7), rs.getDate(8), rs.getInt(9),
                        rs.getDate(10), rs.getDate(11)));
            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return list;
    }

    public ArrayList<HoaDonModel> getAllHoaDonTT() {
        ArrayList<HoaDonModel> list = new ArrayList<>();
        String sql = "SELECT TOP 3 * FROM HOADON where MAHD not in (SELECT TOP " + (Trang * 3 - 3) + " MAHD FROM HOADON) AND TRANGTHAI=1";
        ResultSet rs = JDBC_Helper.excuteQuery(sql);
        try {
            while (rs.next()) {
                KhachHang kh = khr.getKHByID(rs.getString(2));
                NhanVien nv = nvr.getNVByID(rs.getString(3));
                Coupon cp = cr.getCPByID(rs.getString(4));
                list.add(new HoaDonModel(rs.getString(1), kh, nv, cp, rs.getString(5), rs.getFloat(6), rs.getInt(7), rs.getDate(8), rs.getInt(9),
                        rs.getDate(10), rs.getDate(11)));
            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return list;
    }

        public ArrayList<HoaDonModel> getAllHoaDonCTT() {
        ArrayList<HoaDonModel> list = new ArrayList<>();
        String sql = "SELECT TOP 3 * FROM HOADON where MAHD not in (SELECT TOP " + (Trang * 3 - 3) + " MAHD FROM HOADON) AND TRANGTHAI=0";
        ResultSet rs = JDBC_Helper.excuteQuery(sql);
        try {
            while (rs.next()) {
                KhachHang kh = khr.getKHByID(rs.getString(2));
                NhanVien nv = nvr.getNVByID(rs.getString(3));
                Coupon cp = cr.getCPByID(rs.getString(4));
                list.add(new HoaDonModel(rs.getString(1), kh, nv, cp, rs.getString(5), rs.getFloat(6), rs.getInt(7), rs.getDate(8), rs.getInt(9),
                        rs.getDate(10), rs.getDate(11)));
            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return list;
    }
    private void loadTable(long Trang) {
        ArrayList<HoaDonModel> listSP = getAllHoaDon();
        dtm.setRowCount(0);
        for (HoaDonModel s : listSP) {
            dtm.addRow(new Object[]{
                s.getKh(),
                s.getNv(),
                s.getCp(),
                s.getMa(), 
                Double.valueOf(s.getThanhTien()).longValue(), 
                s.getHinhThucThanhToan() == 1 ? "Tiền mặt" : "Chuyển khoản", s.getNgayThanhToan(),
                s.getTrangThai() == 1 ? "Đã thanh toán" : "Chưa thanh toán", s.getNgayTao(), s.getNgaySua()
            });

        }
    }

    private void loadTableCTT(long Trang) {
        ArrayList<HoaDonModel> listSP = getAllHoaDonCTT();
        dtm.setRowCount(0);
        for (HoaDonModel s : listSP) {
            dtm.addRow(new Object[]{
                s.getKh(),
                s.getNv(),
                s.getCp(),
                s.getMa(), Double.valueOf(s.getThanhTien()).longValue(),  s.getHinhThucThanhToan() == 1 ? "Tiền mặt" : "Chuyển khoản", s.getNgayThanhToan(),
                s.getTrangThai() == 1 ? "Đã thanh toán" : "Chưa thanh toán", s.getNgayTao(), s.getNgaySua()
            });

        }
    }

    private void loadTableTT(long Trang) {
        ArrayList<HoaDonModel> listSP = getAllHoaDonTT();
        dtm.setRowCount(0);
        for (HoaDonModel s : listSP) {
            dtm.addRow(new Object[]{
                s.getKh(),
                s.getNv(),
                s.getCp(),
                s.getMa(),Double.valueOf(s.getThanhTien()).longValue(),  s.getHinhThucThanhToan() == 1 ? "Tiền mặt" : "Chuyển khoản", s.getNgayThanhToan(),
                s.getTrangThai() == 1 ? "Đã thanh toán" : "Chưa thanh toán", s.getNgayTao(), s.getNgaySua()
            });

        }
    }

    public String getIDHoaDon(String ma) {
        ArrayList<HoaDonModel> hd = hds.getAllHoaDon();
        for (HoaDonModel h : hd) {
            if (h.getMa().equals(ma)) {
                return h.getId();
            }
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JHoaDon = new javax.swing.JPanel();
        cbbTT = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHDCT = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        lblTrang = new javax.swing.JLabel();
        btnTrangTruoc = new javax.swing.JButton();
        btnTiep = new javax.swing.JButton();
        txtDenTrang = new javax.swing.JTextField();
        btnDen = new javax.swing.JButton();
        btnTrangCuoi = new javax.swing.JButton();
        btnTrangDau = new javax.swing.JButton();

        setName(""); // NOI18N

        JHoaDon.setOpaque(false);

        cbbTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALL", "Đã thanh toán", "Chưa thanh toán" }));
        cbbTT.setToolTipText("");
        cbbTT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbTTItemStateChanged(evt);
            }
        });

        tblHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HD", "Tên SP", "Số lượng", "Đơn giá", "Thành Tiền"
            }
        ));
        jScrollPane1.setViewportView(tblHDCT);

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã KH", "Mã NV", "Mã CP", "Mã HD", "Thành Tiền", "Hình thức TT", "Ngày TT", "Trạng thái", "Ngày tạo", "Ngày sửa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
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
        jScrollPane2.setViewportView(tblHoaDon);
        if (tblHoaDon.getColumnModel().getColumnCount() > 0) {
            tblHoaDon.getColumnModel().getColumn(7).setPreferredWidth(150);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Hóa Đơn");

        lblTrang.setText("jLabel2");

        btnTrangTruoc.setText("<");
        btnTrangTruoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangTruocActionPerformed(evt);
            }
        });

        btnTiep.setText(">");
        btnTiep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTiepActionPerformed(evt);
            }
        });

        btnDen.setText("Đến");

        btnTrangCuoi.setText(">>");

        btnTrangDau.setText("<<");

        javax.swing.GroupLayout JHoaDonLayout = new javax.swing.GroupLayout(JHoaDon);
        JHoaDon.setLayout(JHoaDonLayout);
        JHoaDonLayout.setHorizontalGroup(
            JHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JHoaDonLayout.createSequentialGroup()
                .addGroup(JHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JHoaDonLayout.createSequentialGroup()
                        .addGap(381, 381, 381)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(263, 263, 263)
                        .addComponent(cbbTT, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JHoaDonLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(JHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 892, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 892, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(JHoaDonLayout.createSequentialGroup()
                                .addGap(273, 273, 273)
                                .addComponent(btnTrangDau)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTrangTruoc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTrang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTiep)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTrangCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDenTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDen)))))
                .addContainerGap(554, Short.MAX_VALUE))
        );
        JHoaDonLayout.setVerticalGroup(
            JHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JHoaDonLayout.createSequentialGroup()
                .addGroup(JHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JHoaDonLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(cbbTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JHoaDonLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(JHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JHoaDonLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JHoaDonLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTrangTruoc)
                            .addComponent(lblTrang)
                            .addComponent(btnTiep)
                            .addComponent(btnTrangCuoi)
                            .addComponent(btnTrangDau)
                            .addComponent(txtDenTrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDen))))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(JHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(433, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(JHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(152, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbbTTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbTTItemStateChanged
        String tt = cbbTT.getSelectedItem().toString();
        if (tt.equalsIgnoreCase("Đã thanh toán")) {
            countHDTT();
            if (count % 3 == 0) {
                soTrang = count / 3;
            } else {
                soTrang = count / 3 + 1;
            }
            loadTableTT(1);
            lblTrang.setText("1/" + soTrang);
        } else if (tt.equalsIgnoreCase("Chưa thanh toán")) {
            countHDCTT();
            if (count % 3 == 0) {
                soTrang = count / 3;
            } else {
                soTrang = count / 3 + 1;
            }
            loadTableCTT(1);
            lblTrang.setText("1/" + soTrang);
        } else if (tt.equalsIgnoreCase("ALL")) {
            countHD();
            if (count % 3 == 0) {
                soTrang = count / 3;
            } else {
                soTrang = count / 3 + 1;
            }
            loadTable(1);
            lblTrang.setText("1/" + soTrang);
        }

    }//GEN-LAST:event_cbbTTItemStateChanged

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        dt.setRowCount(0);
        int row = tblHoaDon.getSelectedRow();
        String ma = tblHoaDon.getValueAt(row, 3).toString();
        String id = getIDHoaDon(ma);
        ArrayList<HoaDonChiTietModel> listHD = hdcts.getAllHoaDonCT();
        for (HoaDonChiTietModel h : listHD) {

            if (h.getIdhd() != null && h.getIdhd().getId().equals(id)) {
                dt.addRow(new Object[]{
                    h.getIdhd().getMa(),
                    h.getIdctsp().getSp().getTen(),
                    h.getSl(),
                    Double.valueOf(h.getIdctsp().getGiaBan()).longValue(),
                    Double.valueOf(h.getIdctsp().getGiaBan() * h.getSl()).longValue()
                });
            }

        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnTrangTruocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangTruocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTrangTruocActionPerformed

    private void btnTiepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTiepActionPerformed
          if (Trang < soTrang) {
            Trang++;
            loadTable(Trang);
            lblTrang.setText("" + Trang);
            lblTrang.setText(Trang + "/" + soTrang);
        }
    }//GEN-LAST:event_btnTiepActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JHoaDon;
    private javax.swing.JButton btnDen;
    private javax.swing.JButton btnTiep;
    private javax.swing.JButton btnTrangCuoi;
    private javax.swing.JButton btnTrangDau;
    private javax.swing.JButton btnTrangTruoc;
    private javax.swing.JComboBox<String> cbbTT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTrang;
    private javax.swing.JTable tblHDCT;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtDenTrang;
    // End of variables declaration//GEN-END:variables
}
