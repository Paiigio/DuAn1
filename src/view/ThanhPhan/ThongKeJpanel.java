package view.ThanhPhan;
//MMM d, y

import DomainModels.NSX;
import Service.CTSanPhamService;
import Service.HoaDonChiTietService;
import Service.IMEIService;
import Service.Interface.ICTSanPhamService;
import Service.Interface.IHoaDonChiTietService;
import Service.Interface.IIMEIService;
import Service.Interface.INSXService;
import Service.NSXService;
import ViewModel.HoaDonChiTietModel;
import ViewModel.IMEIModel;
import ViewModel.NSXModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class ThongKeJpanel extends javax.swing.JPanel {

    private IHoaDonChiTietService ihdct = new HoaDonChiTietService();
    private INSXService insx = new NSXService();
    private ICTSanPhamService ictsp = new CTSanPhamService();
    private IIMEIService iimei = new IMEIService();
    private DefaultTableModel dtm;
    private DefaultTableModel dtm2;
    DefaultComboBoxModel dcm = new DefaultComboBoxModel();

    public ThongKeJpanel() {
        initComponents();
        load();
        dtm = (DefaultTableModel) this.tblDoanhThu.getModel();
        dtm2 = (DefaultTableModel) this.tblMayBanDuoc.getModel();
        cbbNSX.setModel(dcm);
        loadCBB();
    }

    private void loadCBB() {
        ArrayList<NSXModel> listNSX = insx.getAllNSX();
        dcm.addElement("ALL");
        for (NSXModel x : listNSX) {
            dcm.addElement(new NSX(x.getId(), x.getMa(), x.getTen(), null, null));
        }
    }

    public void loadTableDoanhThu() {
        try {
            ArrayList<HoaDonChiTietModel> list = ihdct.getAllHoaDonCT();
            dtm.setRowCount(0);
            double total = 0;
            String thangStr = cbbLocThang.getSelectedItem().toString();

            for (HoaDonChiTietModel x : list) {
//                String ngayTaoStr = x.getNgayTao().toString();
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                int thangNgayTao = sdf.parse(ngayTaoStr).getMonth();
//                System.out.println(ngayTaoStr);
//                System.out.println(thangNgayTao);
                    int thangNgayTao = x.getNgayTao().getMonth();
                    System.out.println(thangNgayTao);
                if (thangStr.equalsIgnoreCase("ALL")) {
                    total += x.getThanhTien();
                    Object[] rowData = {
                        x.getIdhd().getNv().getHoTen(),
                        x.getIdhd().getMa(),
                        x.getNgayTao(),
                        x.getThanhTien() + " VND"
                    };
                    dtm.addRow(rowData);
                } else {
                    int thang = Integer.parseInt(thangStr);
                    if (x.getNgayTao().getMonth() == thang) {
                        total += x.getThanhTien();
                        Object[] rowData = {
                            x.getIdhd().getNv().getHoTen(),
                            x.getIdhd().getMa(),
                            x.getNgayTao(),
                            x.getThanhTien() + " VND"
                        };
                        dtm.addRow(rowData);
                    }
                }
            }
            Object[] sum = {
                "",
                "",
                "Tổng Tiền",
                total + " VND"
            };
            dtm.addRow(sum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTableSanPham() {
        ArrayList<HoaDonChiTietModel> list = ihdct.getAllHoaDonCT();
        for (HoaDonChiTietModel x : list) {
            System.out.println(x.getIdctsp().getSp().getTen());
        }
        int sum = 1, dem = 0;
        dtm2.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            sum = 0;
            boolean check = true;
            if (i == list.size()) {
                break;
            }
            if (i > 0) {
                for (int j = i; j > 0; j--) {
                    if (list.get(i).getIdctsp().getId().equals(list.get(j - 1).getIdctsp().getId())) {
                        check = false;
                    }
                }
            }
            if (check) {
                for (int j = i; j < list.size(); j++) {
                    if (list.get(i).getIdctsp().getId().equals(list.get(j).getIdctsp().getId())) {
                        ++sum;
                    }
                }

                dem += sum;
                Object[] rowData = {
                    list.get(i).getIdctsp().getSp().getTen() + " " + list.get(i).getIdctsp().getMs() + " " + list.get(i).getIdctsp().getDl(),
                    sum
                };
                dtm2.addRow(rowData);
            }

        }
        Object[] count = {
            "Tổng",
            dem
        };

        dtm2.addRow(count);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JThongKe = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDoanhThu = new javax.swing.JTable();
        cbbLocThang = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMayBanDuoc = new javax.swing.JTable();
        cbbNSX = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();

        JThongKe.setBackground(new java.awt.Color(238, 232, 170));

        jPanel4.setBackground(new java.awt.Color(238, 232, 170));

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Doanh Thu");

        tblDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nhân Viên", "MAHD", "Ngày Tạo", "Thành Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDoanhThu);

        cbbLocThang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbbLocThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALL", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cbbLocThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocThangActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Lọc theo tháng");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbLocThang, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbLocThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 51, 0));
        jLabel4.setText("Số máy đã bán được");

        tblMayBanDuoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên sản phẩm", "Số Lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblMayBanDuoc);

        cbbNSX.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbbNSX.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbNSX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNSXActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Lọc theo NSX");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbbNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(380, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout JThongKeLayout = new javax.swing.GroupLayout(JThongKe);
        JThongKe.setLayout(JThongKeLayout);
        JThongKeLayout.setHorizontalGroup(
            JThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JThongKeLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 401, Short.MAX_VALUE))
        );
        JThongKeLayout.setVerticalGroup(
            JThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JThongKeLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 233, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbbLocThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocThangActionPerformed
        loadTableDoanhThu();
    }//GEN-LAST:event_cbbLocThangActionPerformed

    private void cbbNSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNSXActionPerformed
        String nsx = cbbNSX.getSelectedItem().toString();
        ArrayList<HoaDonChiTietModel> list = ihdct.getAllHoaDonCT();
//        for (HoaDonChiTietModel x : list) {
//            System.out.println(x.getIdctsp().getSp().getTen());
//        }
        int sum = 1, dem = 0;
        dtm2.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            sum = 0;
            boolean check = true;
            if (i == list.size()) {
                break;
            }

            if (nsx.equalsIgnoreCase("ALL")) {
                if (i > 0) {
                    for (int j = i; j > 0; j--) {
                        if (list.get(i).getIdctsp().getId().equals(list.get(j - 1).getIdctsp().getId())) {
                            check = false;
                        }
                    }
                }
                if (check) {
                    for (int j = i; j < list.size(); j++) {
                        if (list.get(i).getIdctsp().getId().equals(list.get(j).getIdctsp().getId())) {
                            ++sum;
                        }
                    }

                    dem += sum;
                    Object[] rowData = {
                        list.get(i).getIdctsp().getSp().getTen() + " " + list.get(i).getIdctsp().getMs() + " " + list.get(i).getIdctsp().getDl(),
                        sum
                    };
                    dtm2.addRow(rowData);
                }

            } else {
                if (list.get(i).getIdctsp().getSp().getNsx().getTen().equalsIgnoreCase(nsx)) {
                    if (i > 0) {
                        for (int j = i; j > 0; j--) {
                            if (list.get(i).getIdctsp().getId().equals(list.get(j - 1).getIdctsp().getId())) {
                                check = false;
                            }
                        }
                    }
                    if (check) {
                        for (int j = i; j < list.size(); j++) {
                            if (list.get(i).getIdctsp().getId().equals(list.get(j).getIdctsp().getId())) {
                                ++sum;
                            }
                        }

                        dem += sum;
                        Object[] rowData = {
                            list.get(i).getIdctsp().getSp().getTen() + " " + list.get(i).getIdctsp().getMs() + " " + list.get(i).getIdctsp().getDl(),
                            sum
                        };
                        dtm2.addRow(rowData);
                    }

                }
            }
        }
        Object[] count = {
            "Tổng",
            dem
        };

        dtm2.addRow(count);
    }//GEN-LAST:event_cbbNSXActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JThongKe;
    private javax.swing.JComboBox<String> cbbLocThang;
    private javax.swing.JComboBox<String> cbbNSX;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblDoanhThu;
    private javax.swing.JTable tblMayBanDuoc;
    // End of variables declaration//GEN-END:variables

    private void load() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);

                    loadTableDoanhThu();
                    loadTableSanPham();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
        }).start();
    }
}
