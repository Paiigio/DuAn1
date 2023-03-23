/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.ThanhPhan;

import DomainModels.ChucVu;
import DomainModels.HoaDon;
import Service.HoaDonChiTietService;
import Service.HoaDonService;
import ViewModel.HoaDonChiTietModel;
import ViewModel.HoaDonModel;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

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

    public HoaDonJpanel() {
        initComponents();
        dtm = (DefaultTableModel) tblHoaDon.getModel();
        dt = (DefaultTableModel) tblHDCT.getModel();
        loadTable();

    }

    private void loadTable() {
        ArrayList<HoaDonModel> listSP = hds.getAllHoaDon();
        dtm.setRowCount(0);
        for (HoaDonModel s : listSP) {
            dtm.addRow(new Object[]{
                s.getKh(),
                s.getNv(),
                s.getCp(),
                s.getMa(), s.getThanhTien(), s.getHinhThucThanhToan() == 1 ? "Tiền mặt" : "Chuyển khoản", s.getNgayThanhToan(),
                s.getTrangThai() == 1 ? "Đã thanh toán" : "Chưa thanh toán", s.getNgayTao(), s.getNgaySua()
            });

        }
    }

    private void loadTableCTT() {
        ArrayList<HoaDonModel> listSP = hds.getAllHoaDonCTT();
        dtm.setRowCount(0);
        for (HoaDonModel s : listSP) {
            dtm.addRow(new Object[]{
                s.getKh(),
                s.getNv(),
                s.getCp(),
                s.getMa(), s.getThanhTien(), s.getHinhThucThanhToan() == 1 ? "Tiền mặt" : "Chuyển khoản", s.getNgayThanhToan(),
                s.getTrangThai() == 1 ? "Đã thanh toán" : "Chưa thanh toán", s.getNgayTao(), s.getNgaySua()
            });

        }
    }

    private void loadTableTT() {
        ArrayList<HoaDonModel> listSP = hds.getAllHoaDonTT();
        dtm.setRowCount(0);
        for (HoaDonModel s : listSP) {
            dtm.addRow(new Object[]{
                s.getKh(),
                s.getNv(),
                s.getCp(),
                s.getMa(), s.getThanhTien(), s.getHinhThucThanhToan() == 1 ? "Tiền mặt" : "Chuyển khoản", s.getNgayThanhToan(),
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
                        .addGroup(JHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 892, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 892, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(463, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 670, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 218, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbbTTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbTTItemStateChanged
        String tt = cbbTT.getSelectedItem().toString();
        if (tt.equalsIgnoreCase("Đã thanh toán")) {
            loadTableTT();
        } else if (tt.equalsIgnoreCase("Chưa thanh toán")) {
            loadTableCTT();
        } else if (tt.equalsIgnoreCase("ALL")) {
            loadTable();
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
                    h.getIdctsp().getGiaBan(),
                    h.getSl(),
                    Double.valueOf(h.getIdctsp().getGiaBan() * h.getSl()).longValue()
                });
            }
            
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JHoaDon;
    private javax.swing.JComboBox<String> cbbTT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblHDCT;
    private javax.swing.JTable tblHoaDon;
    // End of variables declaration//GEN-END:variables
}
