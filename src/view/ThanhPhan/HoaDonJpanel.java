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
import java.util.Collections;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
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
        countHDCTT();
        if (count % 5 == 0) {
            soTrang = count / 5;
        } else {
            soTrang = count / 5 + 1;
        }
        loadTable(1);
        lblTrang.setText("1/" + soTrang);
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

    public void countHDH() {
        try {
            String sql = "SELECT count(*) From HOADON WHERE TRANGTHAI=2";
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
        int x = cbbTT.getSelectedIndex();
        int a = 0;
        int b = 0;
        int c = 0;

        if (x == 1) {
            a = 1;
            b = 2;
            c = 0;

        } else if (x == 2) {
            a = 2;
            b = 1;
            c = 0;

        } else {
            a = 0;
            b = 2;
            c = 1;

        }
        ArrayList<HoaDonModel> list = new ArrayList<>();
        String sql = "SELECT TOP 5 * FROM HOADON WHERE MAHD NOT IN (SELECT TOP " + (Trang * 5 - 5) + "  MAHD FROM HOADON WHERE TRANGTHAI!=" + b + " AND TRANGTHAI!=" + c + " ORDER BY MAHD) AND TRANGTHAI=" + a + " ORDER BY MAHD";
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

    private int catMa(String ma) {
        String chuSo = ma.substring(2);
        int so = Integer.valueOf(chuSo);
        return so;
    }

    private void loadTable(long Trang) {
        ArrayList<HoaDonModel> listSP = getAllHoaDon();
        Collections.sort(listSP, (HoaDonModel o1, HoaDonModel o2) -> catMa(o1.getMa()) > catMa(o2.getMa()) ? 1 : -1);
        dtm.setRowCount(0);
        for (HoaDonModel s : listSP) {
            dtm.addRow(new Object[]{
                s.getKh(),
                s.getNv(),
                s.getCp(),
                s.getMa(),
                Double.valueOf(s.getThanhTien()).longValue(),
                s.getHinhThucThanhToan() == 1 ? "Tiền mặt" : "Chuyển khoản", s.getNgayThanhToan(),
                s.getTrangThai() == 0 ? "Chưa thanh toán" : s.getTrangThai() == 1 ? "Đã thanh toán" : "Đơn đã hủy",
                s.getNgayTao(), s.getNgaySua()
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
        txtTim = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setName(""); // NOI18N

        JHoaDon.setBackground(new java.awt.Color(238, 232, 170));

        cbbTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chưa thanh toán", "Đã thanh toán", "Đã hủy" }));
        cbbTT.setToolTipText("");
        cbbTT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbTTItemStateChanged(evt);
            }
        });
        cbbTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTTActionPerformed(evt);
            }
        });

        tblHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HD", "Tên SP", "Số lượng", "Đơn giá", "Bảo hành", "Thành Tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
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
        btnDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDenActionPerformed(evt);
            }
        });

        btnTrangCuoi.setText(">>");
        btnTrangCuoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangCuoiActionPerformed(evt);
            }
        });

        btnTrangDau.setText("<<");
        btnTrangDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangDauActionPerformed(evt);
            }
        });

        txtTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimActionPerformed(evt);
            }
        });

        jLabel2.setText("Tìm hóa đơn");

        javax.swing.GroupLayout JHoaDonLayout = new javax.swing.GroupLayout(JHoaDon);
        JHoaDon.setLayout(JHoaDonLayout);
        JHoaDonLayout.setHorizontalGroup(
            JHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JHoaDonLayout.createSequentialGroup()
                .addGroup(JHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1030, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JHoaDonLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(88, 88, 88)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(cbbTT, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(JHoaDonLayout.createSequentialGroup()
                            .addGap(33, 33, 33)
                            .addGroup(JHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1030, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JHoaDonLayout.createSequentialGroup()
                                    .addComponent(btnTrangDau)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnTrangTruoc)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblTrang)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnTiep)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnTrangCuoi)
                                    .addGap(246, 246, 246)
                                    .addComponent(txtDenTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnDen)
                                    .addGap(50, 50, 50))))))
                .addContainerGap(245, Short.MAX_VALUE))
        );
        JHoaDonLayout.setVerticalGroup(
            JHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JHoaDonLayout.createSequentialGroup()
                .addGroup(JHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JHoaDonLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(JHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JHoaDonLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(27, 27, 27)))
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
                .addContainerGap(94, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(JHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbbTTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbTTItemStateChanged
        String tt = cbbTT.getSelectedItem().toString();
        int x = cbbTT.getSelectedIndex();
        if (tt.equalsIgnoreCase("Đã thanh toán")) {
            countHDTT();
            if (count % 5 == 0) {
                soTrang = count / 5;
            } else {
                soTrang = count / 5 + 1;
            }
            loadTable(1);
            Trang = 1;
            lblTrang.setText("1/" + soTrang);
        } else if (tt.equalsIgnoreCase("Chưa thanh toán")) {
            countHDCTT();
            if (count % 5 == 0) {
                soTrang = count / 5;
            } else {
                soTrang = count / 5 + 1;
            }
            loadTable(1);
            Trang = 1;
            lblTrang.setText("1/" + soTrang);
        } else if (tt.equalsIgnoreCase("Đã hủy")) {
            countHDH();
            if (count % 5 == 0) {
                soTrang = count / 5;
            } else {
                soTrang = count / 5 + 1;
            }
            loadTable(1);
            Trang = 1;
            lblTrang.setText("1/" + soTrang);
        } else {
            soTrang = count / 5 + 1;
        }
        loadTable(1);
        lblTrang.setText("1/" + soTrang);

        System.out.println(cbbTT.getSelectedIndex());
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
                    Double.valueOf(h.getThanhTien()).longValue(),
                    h.getBaoHanh() == 1 ? true : false,
                    h.getBaoHanh() == 1 ? Double.valueOf(h.getThanhTien() * h.getSl() +500000).longValue() : Double.valueOf(h.getThanhTien() * h.getSl()).longValue()
                });
            }

        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnTrangTruocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangTruocActionPerformed
        if (Trang == 1) {
            JOptionPane.showMessageDialog(null, "Bạn đã ở trang đầu");
            return;
        }
        if (Trang > 1) {
            Trang--;
            String tt = cbbTT.getSelectedItem().toString();
            if (tt.equalsIgnoreCase("Đã thanh toán")) {
                loadTable(Trang);
            } else if (tt.equalsIgnoreCase("Chưa thanh toán")) {
                loadTable(Trang);
            } else if (tt.equalsIgnoreCase("Đã hủy")) {
                loadTable(Trang);
            }
            lblTrang.setText(Trang + "/" + soTrang);
        }
        System.out.println(Trang);
    }//GEN-LAST:event_btnTrangTruocActionPerformed

    private void btnTiepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTiepActionPerformed
        if (Trang == soTrang) {
            JOptionPane.showMessageDialog(null, "Bạn đã ở trang cuối");
            return;
        }
        if (Trang < soTrang) {
            Trang++;
            String tt = cbbTT.getSelectedItem().toString();
            if (tt.equalsIgnoreCase("Đã thanh toán")) {
                loadTable(Trang);
            } else if (tt.equalsIgnoreCase("Chưa thanh toán")) {
                loadTable(Trang);
            } else if (tt.equalsIgnoreCase("Đã hủy")) {
                loadTable(Trang);
            }

            lblTrang.setText(Trang + "/" + soTrang);
        }
        System.out.println(Trang);
    }//GEN-LAST:event_btnTiepActionPerformed

    private void btnTrangDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangDauActionPerformed
        if (Trang == 1) {
            JOptionPane.showMessageDialog(null, "Bạn đã ở trang đầu");
            return;
        }
        Trang = 1;
        loadTable(Trang);
        lblTrang.setText("" + Trang);
        lblTrang.setText(Trang + "/" + soTrang);
    }//GEN-LAST:event_btnTrangDauActionPerformed

    private void btnTrangCuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangCuoiActionPerformed
        if (Trang == soTrang) {
            JOptionPane.showMessageDialog(null, "Bạn đã ở trang cuối");
            return;
        }
        Trang = soTrang;
        loadTable(Trang);
        lblTrang.setText("" + Trang);
        lblTrang.setText(Trang + "/" + soTrang);
    }//GEN-LAST:event_btnTrangCuoiActionPerformed

    private void btnDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDenActionPerformed
        if (txtDenTrang.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập trang muốn đến");
            return;
        }
        try {
            int check = Integer.parseInt(txtDenTrang.getText());
            if (check < 1 || check > soTrang) {
                JOptionPane.showMessageDialog(null, "Trang muốn đến không tồn tại");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Trang muốn đến phải là số");
            return;
        }
        Trang = Integer.parseInt(txtDenTrang.getText());
        loadTable(Trang);
        lblTrang.setText("" + Trang);
        lblTrang.setText(Trang + "/" + soTrang);
    }//GEN-LAST:event_btnDenActionPerformed

    private void cbbTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbTTActionPerformed
    public ArrayList<HoaDonModel> getHDTim() {
        ArrayList<HoaDonModel> list = new ArrayList<>();
        String txt = txtTim.getText();
        int x = cbbTT.getSelectedIndex();
        String sql = "SELECT* FROM HOADON WHERE MAHD LIKE '%" + txt + "%' AND TRANGTHAI= " + x;
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

    private void loadTableTim(String ma) {
        ArrayList<HoaDonModel> listSP = getHDTim();
        dtm.setRowCount(0);
        for (HoaDonModel s : listSP) {
            dtm.addRow(new Object[]{
                s.getKh(),
                s.getNv(),
                s.getCp(),
                s.getMa(),
                Double.valueOf(s.getThanhTien()).longValue(),
                s.getHinhThucThanhToan() == 1 ? "Tiền mặt" : "Chuyển khoản", s.getNgayThanhToan(),
                s.getTrangThai() == 0 ? "Chưa thanh toán" : s.getTrangThai() == 1 ? "Đã thanh toán" : "Đơn đã hủy",
                s.getNgayTao(), s.getNgaySua()
            });

        }
    }
    private void txtTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimActionPerformed
        String tim = txtTim.getText().trim();
        if (tim.length() == 0) {
            JOptionPane.showMessageDialog(this, "không đc để trống tìm");
        }
        if (getHDTim().size() > 0) {
            JOptionPane.showMessageDialog(null, "Tìm thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Tìm thất bại");
            return;
        }
        loadTableTim(tim);

    }//GEN-LAST:event_txtTimActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JHoaDon;
    private javax.swing.JButton btnDen;
    private javax.swing.JButton btnTiep;
    private javax.swing.JButton btnTrangCuoi;
    private javax.swing.JButton btnTrangDau;
    private javax.swing.JButton btnTrangTruoc;
    private javax.swing.JComboBox<String> cbbTT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTrang;
    private javax.swing.JTable tblHDCT;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtDenTrang;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
