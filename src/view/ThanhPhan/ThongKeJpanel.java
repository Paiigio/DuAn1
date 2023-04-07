package view.ThanhPhan;

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
import ViewModel.NSXModel;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import PieChart.ModelPieChart;
import PieChart.Pie_Chart;
import CurveLineChart.ModelChart;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import responsitory.IMEIResponsitory;

public class ThongKeJpanel extends javax.swing.JPanel {

    private IHoaDonChiTietService ihdct = new HoaDonChiTietService();
    private INSXService insx = new NSXService();
    private ICTSanPhamService ictsp = new CTSanPhamService();
    private IIMEIService iimei = new IMEIService();
    private IMEIResponsitory imeirp = new IMEIResponsitory();
    private DefaultTableModel dtm;
    private DefaultTableModel dtm2;
    DefaultComboBoxModel dcm = new DefaultComboBoxModel();

    public ThongKeJpanel() {
        initComponents();
        load();

        dtm = (DefaultTableModel) this.tblDoanhThu.getModel();
        dtm2 = (DefaultTableModel) this.tblMayBanDuoc.getModel();
        pie_Chart.setChartType(Pie_Chart.PeiChartType.DEFAULT);
        cbbNSX.setModel(dcm);
        curveLine_Chart.addLegend("Doanh Thu", Color.decode("#e65c00"), Color.decode("#F9D423"));
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
                if (x.getIdhd().getTrangThai() == 1) {
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
                        LocalDate lcd = LocalDate.parse(x.getNgayTao().toString());
                        String month = subString(lcd.getMonth().toString().toLowerCase());
                        if (month.equalsIgnoreCase(thangStr)) {
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
//        ArrayList<HoaDonChiTietModel> list = ihdct.getAllHoaDonCT();
//        for (HoaDonChiTietModel x : list) {
//            System.out.println(x.getIdctsp().getSp().getTen());
//        }
//        int sum = 1, dem = 0;
//        dtm2.setRowCount(0);
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).getIdhd().getTrangThai() == 1) {
//                sum = 0;
//                boolean check = true;
//                if (i == list.size()) {
//                    break;
//                }
//
//                if (i > 0) {
//                    for (int j = i; j > 0; j--) {
//                        if (list.get(i).getIdctsp().getId().equals(list.get(j - 1).getIdctsp().getId())) {
//                            check = false;
//                        }
//                    }
//                }
//                if (check) {
//                    for (int j = i; j < list.size(); j++) {
//                        if (list.get(i).getIdctsp().getId().equals(list.get(j).getIdctsp().getId())) {
//                            ++sum;
//                        }
//                    }
//
//                    dem += sum;
//                    Object[] rowData = {
//                        list.get(i).getIdctsp().getSp().getTen() + " " + list.get(i).getIdctsp().getMs() + " " + list.get(i).getIdctsp().getDl(),
//                        sum
//                    };
//                    dtm2.addRow(rowData);
//                }
//            }
//        }

        int dem = 0;
        dtm2.setRowCount(0);
        for(Map.Entry<String, Integer> entry : imeirp.amountsImeiSell().entrySet()) {
            dem += entry.getValue();
            Object[] rowData = {
                entry.getKey(),
                entry.getValue()
            };
            dtm2.addRow(rowData);
        }
        Object[] count = {
            "Tổng",
            dem
        };

        dtm2.addRow(count);
    }

    public String UpperCaseFirst(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    public String subString(String string) {
        return string.substring(0, 3);
    }

    public void setDataChart() {
        ArrayList<HoaDonChiTietModel> list = ihdct.getAllHoaDonCT();
        LinkedHashMap<String, Double> map = new LinkedHashMap<>();

        double total = 0, valueOfKey;
        map.put("Jan", total);
        map.put("Feb", total);
        map.put("Mar", total);
        map.put("Apr", total);
        map.put("May", total);
        map.put("Jun", total);
        map.put("Jul", total);
        map.put("Aug", total);
        map.put("Sep", total);
        map.put("Oct", total);
        map.put("Nov", total);
        map.put("Dec", total);
        for (HoaDonChiTietModel x : list) {
            LocalDate lcd = LocalDate.parse(x.getNgayTao().toString());
            String month = UpperCaseFirst(subString(lcd.getMonth().toString().toLowerCase()));
            System.out.println(month);
            if (map.containsKey(month)) {
                valueOfKey = map.get(month);
                valueOfKey += x.getThanhTien();
                map.put(subString(month), valueOfKey);
//                test
//            map.put("Jan", Double.valueOf(1000));
//            map.put("Feb", Double.valueOf(50000));
//            map.put("Mar", Double.valueOf(20000));
//            map.put("Apr", Double.valueOf(50000));
//            map.put("May", Double.valueOf(20000));
//            map.put("Jun", Double.valueOf(35000));
//            map.put("Jul", Double.valueOf(10000));
//            map.put("Aug", Double.valueOf(10000));
//            map.put("Sep", Double.valueOf(104000));
//            map.put("Oct", Double.valueOf(5000));
//            map.put("Nov", Double.valueOf(35000));
//            map.put("Dec", Double.valueOf(10000));
            }

        }
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            curveLine_Chart.addData(new ModelChart(entry.getKey(), entry.getValue()));
        }
        curveLine_Chart.start();
    }

    public void loadBieuDoTron() {
        pie_Chart.clearData();
        ArrayList<HoaDonChiTietModel> list = ihdct.getAllHoaDonCT();

        int sum, index = 0;
        for (int i = 0; i < list.size(); i++) {
            sum = 0;
            boolean check = true;
            if (i == list.size()) {
                break;
            }
            if (i > 0) {
                for (int j = i; j > 0; j--) {
                    String tenNsx1 = list.get(i).getIdctsp().getSp().getNsx().getTen();
                    String tenNsx2 = list.get(j - 1).getIdctsp().getSp().getNsx().getTen();
                    if (tenNsx1.equalsIgnoreCase(tenNsx2)) {
                        check = false;
                    }
                }
            }
            if (check) {
                for (int j = i; j < list.size(); j++) {
                    String tenNsx1 = list.get(i).getIdctsp().getSp().getNsx().getTen();
                    String tenNsx2 = list.get(j).getIdctsp().getSp().getNsx().getTen();
                    if (tenNsx1.equalsIgnoreCase(tenNsx2)) {
                        ++sum;
                    }
                }

                pie_Chart.addData(new ModelPieChart(list.get(i).getIdctsp().getSp().getNsx().getTen(), sum, getColor(index++)));
            }

        }
    }

    public Color getColor(int index) {
        Color[] color = new Color[]{new Color(255, 102, 102), new Color(58, 55, 227), new Color(206, 215, 33), new Color(29, 184, 85), new Color(94, 217, 214), new Color(43, 43, 250), new Color(200, 169, 86), new Color(50, 255, 194), new Color(155, 92, 49), new Color(232, 144, 158)};
        return color[index];
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jpnBang = new javax.swing.JPanel();
        JBangThongKe = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDoanhThu = new javax.swing.JTable();
        cbbLocThang = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jdcNgayBD = new com.toedter.calendar.JDateChooser();
        jdcNgayKT = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMayBanDuoc = new javax.swing.JTable();
        cbbNSX = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jpnBieuDo = new javax.swing.JPanel();
        JBieuDoThongKe = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        pie_Chart = new PieChart.Pie_Chart();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanelShadow = new CurveLineChart.JPanelShadow();
        curveLine_Chart = new CurveLineChart.CurveLine_Chart();

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        JBangThongKe.setBackground(new java.awt.Color(238, 232, 170));

        jPanel4.setBackground(new java.awt.Color(238, 232, 170));

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Doanh Thu");

        tblDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên Nhân Viên", "Mã Hóa Đơn", "Ngày Tạo", "Thành Tiền"
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
        cbbLocThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALL", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }));
        cbbLocThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocThangActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Lọc theo tháng");

        jdcNgayKT.setDateFormatString("yyyy-MM-dd");

        jLabel2.setText("đến ngày");

        jLabel3.setText("Từ ngày");

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbLocThang, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jdcNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jdcNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jdcNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jdcNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbLocThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
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
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(364, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout JBangThongKeLayout = new javax.swing.GroupLayout(JBangThongKe);
        JBangThongKe.setLayout(JBangThongKeLayout);
        JBangThongKeLayout.setHorizontalGroup(
            JBangThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JBangThongKeLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 401, Short.MAX_VALUE))
        );
        JBangThongKeLayout.setVerticalGroup(
            JBangThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JBangThongKeLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 233, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpnBangLayout = new javax.swing.GroupLayout(jpnBang);
        jpnBang.setLayout(jpnBangLayout);
        jpnBangLayout.setHorizontalGroup(
            jpnBangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnBangLayout.createSequentialGroup()
                .addComponent(JBangThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpnBangLayout.setVerticalGroup(
            jpnBangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnBangLayout.createSequentialGroup()
                .addComponent(JBangThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Bảng", jpnBang);

        jpnBieuDo.setBackground(new java.awt.Color(238, 232, 170));

        jPanel1.setBackground(new java.awt.Color(238, 232, 170));

        jPanel2.setBackground(new java.awt.Color(238, 232, 170));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pie_Chart, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pie_Chart, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 0, 0));
        jLabel9.setText("Biểu đồ tròn thể hiện số lượng sản phẩm đã bán");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 0, 0));
        jLabel10.setText("Biểu đồ đường cong thể hiện doanh thu qua từng tháng");

        jPanel3.setBackground(new java.awt.Color(238, 232, 170));

        jPanelShadow.setBackground(new java.awt.Color(34, 58, 69));
        jPanelShadow.setColorGradient(new java.awt.Color(34, 58, 69));

        curveLine_Chart.setForeground(new java.awt.Color(255, 255, 255));
        curveLine_Chart.setFillColor(true);

        javax.swing.GroupLayout jPanelShadowLayout = new javax.swing.GroupLayout(jPanelShadow);
        jPanelShadow.setLayout(jPanelShadowLayout);
        jPanelShadowLayout.setHorizontalGroup(
            jPanelShadowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelShadowLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(curveLine_Chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelShadowLayout.setVerticalGroup(
            jPanelShadowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelShadowLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(curveLine_Chart, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelShadow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanelShadow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 199, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 272, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(49, 49, 49))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout JBieuDoThongKeLayout = new javax.swing.GroupLayout(JBieuDoThongKe);
        JBieuDoThongKe.setLayout(JBieuDoThongKeLayout);
        JBieuDoThongKeLayout.setHorizontalGroup(
            JBieuDoThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        JBieuDoThongKeLayout.setVerticalGroup(
            JBieuDoThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JBieuDoThongKeLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpnBieuDoLayout = new javax.swing.GroupLayout(jpnBieuDo);
        jpnBieuDo.setLayout(jpnBieuDoLayout);
        jpnBieuDoLayout.setHorizontalGroup(
            jpnBieuDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnBieuDoLayout.createSequentialGroup()
                .addComponent(JBieuDoThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 354, Short.MAX_VALUE))
        );
        jpnBieuDoLayout.setVerticalGroup(
            jpnBieuDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnBieuDoLayout.createSequentialGroup()
                .addComponent(JBieuDoThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 418, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Biểu đồ", jpnBieuDo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbbLocThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocThangActionPerformed
        loadTableDoanhThu();
    }//GEN-LAST:event_cbbLocThangActionPerformed

    private void cbbNSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNSXActionPerformed
        String nsx = cbbNSX.getSelectedItem().toString();
        ArrayList<HoaDonChiTietModel> list = ihdct.getAllHoaDonCT();
        int sum, dem = 0;
        dtm2.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIdhd().getTrangThai() == 1) {
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
        }
        Object[] count = {
            "Tổng",
            dem
        };

        dtm2.addRow(count);
    }//GEN-LAST:event_cbbNSXActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JBangThongKe;
    private javax.swing.JPanel JBieuDoThongKe;
    private javax.swing.JComboBox<String> cbbLocThang;
    private javax.swing.JComboBox<String> cbbNSX;
    private CurveLineChart.CurveLine_Chart curveLine_Chart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private CurveLineChart.JPanelShadow jPanelShadow;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JDateChooser jdcNgayBD;
    private com.toedter.calendar.JDateChooser jdcNgayKT;
    private javax.swing.JPanel jpnBang;
    private javax.swing.JPanel jpnBieuDo;
    private PieChart.Pie_Chart pie_Chart;
    private javax.swing.JTable tblDoanhThu;
    private javax.swing.JTable tblMayBanDuoc;
    // End of variables declaration//GEN-END:variables

    private void load() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
//                    pie_Chart.clearData();
//                    curveLine_Chart.start();
                    loadTableDoanhThu();
                    loadTableSanPham();
                    loadBieuDoTron();
                    setDataChart();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
        }).start();
    }
}
