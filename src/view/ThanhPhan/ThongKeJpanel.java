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
import ViewModel.IMEIModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import responsitory.IMEIResponsitory;

public class ThongKeJpanel extends javax.swing.JPanel {

    private IHoaDonChiTietService ihdct = new HoaDonChiTietService();
    private INSXService insx = new NSXService();
    private ICTSanPhamService ictsp = new CTSanPhamService();
    private IIMEIService iimei = new IMEIService();
    private IMEIResponsitory imeirp = new IMEIResponsitory();
    private DefaultTableModel dtm;
    private DefaultTableModel dtm3;
    private DefaultTableModel dtm2;
    private DefaultTableModel dtmBH;
    DefaultComboBoxModel dcm = new DefaultComboBoxModel();

    public ThongKeJpanel() {
        initComponents();
        load();

        dtm = (DefaultTableModel) this.tblDoanhThu.getModel();
        dtm2 = (DefaultTableModel) this.tblMayBanDuoc.getModel();
        dtm3 = (DefaultTableModel) this.tblHoaDonHuy.getModel();
        dtmBH = (DefaultTableModel) this.tblBaoHanh.getModel();
        pie_Chart.setChartType(Pie_Chart.PeiChartType.DEFAULT);
        cbbNSX.setModel(dcm);
        curveLine_Chart.addLegend("Doanh Thu", Color.decode("#e65c00"), Color.decode("#F9D423"));
        loadCBB();
        Date d = new Date();
        jdcNgayBD.setDate(d);
        jdcNgayKT.setDate(d);

    }

    private void loadCBB() {
        ArrayList<NSXModel> listNSX = insx.getAllNSX();
        dcm.addElement("ALL");
        for (NSXModel x : listNSX) {
            dcm.addElement(new NSX(x.getId(), x.getMa(), x.getTen(), null, null));
        }
    }
    Locale localeVN = new Locale("vi", "VN");
    NumberFormat vn = NumberFormat.getInstance(localeVN);

    public void loadBH() {

        ArrayList<HoaDonChiTietModel> listHDCT = ihdct.getAllHoaDonCT_BH();
        dtmBH.setRowCount(0);
        int total = 0;
        for (HoaDonChiTietModel x : listHDCT) {
            if (x.getIdhd().getTrangThai() == 1) {
                total += 1;
                Object[] rowData = {
                    x.getIdhd().getMa(),
                    x.getIdctsp().getSp().getMa(),
                    vn.format(x.getThanhTien()) + " VND",
                    1,
                    x.getIdhd().getNgayThanhToan()

                };

                dtmBH.addRow(rowData);
            }
        }

        Object[] sum = {
            "Tổng số lượng",
            "",
            "",
            total,
            ""

        };
        dtmBH.addRow(sum);
    }

    public void loadTableDoanhThu() {

        ArrayList<HoaDonChiTietModel> list = ihdct.getAllHoaDonCT();
        dtm.setRowCount(0);
        double total = 0;
        for (HoaDonChiTietModel x : list) {
            if (x.getIdhd().getTrangThai() == 1) {
                total += x.getThanhTien();
                Object[] rowData = {
                    x.getIdhd().getNv().getHoTen(),
                    x.getIdhd().getMa(),
                    x.getIdhd().getNgayThanhToan(),
                    vn.format(x.getThanhTien()) + " VND"

                };

                dtm.addRow(rowData);
            }
        }

        Object[] sum = {
            "Tổng Tiền",
            "",
            "",
            vn.format(total) + " VND"
        };
        dtm.addRow(sum);
    }

    public void loadTableSanPham() {

        int dem = 0;
        dtm2.setRowCount(0);
        for (Map.Entry<String, Integer> entry : imeirp.amountsImeiSell().entrySet()) {
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

    public void loadTableHDHuy() {
        dtm3.setRowCount(0);
        int count = 0;
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat vn = NumberFormat.getInstance(localeVN);
        ArrayList<HoaDonChiTietModel> list = ihdct.getAllHoaDonCT();
        for (HoaDonChiTietModel x : list) {
            if (x.getIdhd().getTrangThai() == 2) {
                Object[] rowData = {
                    x.getIdhd().getMa(),
                    x.getIdctsp().getSp().getTen() + " " + x.getIdctsp().getMs() + " " + x.getIdctsp().getDl(),

                    vn.format(x.getThanhTien()),                



                    x.getNgayTao(),
                    x.getIdhd().getNgaySua(),
                    x.getIdhd().getGhiChu()
                };
                dtm3.addRow(rowData);
                count++;
            }
        }

        Object[] sum = {
            "Tổng",
            "",
            "",
            "",
            "",
            count
        };
        dtm3.addRow(sum);
    }

    public void setDataChart() {
        ArrayList<HoaDonChiTietModel> list = ihdct.getAllHoaDonCT();
        LinkedHashMap<String, Double> map = new LinkedHashMap<>();

        double total = 0, valueOfKey = 0;
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
            if (x.getIdhd().getTrangThai() == 1) {
                LocalDate lcd = LocalDate.parse(x.getNgayTao().toString());
                String month = UpperCaseFirst(subString(lcd.getMonth().toString().toLowerCase()));

                valueOfKey = map.get(month);
                valueOfKey += x.getThanhTien();
                map.put(month, valueOfKey);
            }
        }
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            curveLine_Chart.addData(new ModelChart(entry.getKey(), entry.getValue()));
        }
        curveLine_Chart.start();
    }

    public void loadBieuDoTron() {
        pie_Chart.clearData();
        int index = 0, count;
        ArrayList<IMEIModel> list = iimei.getIMEIByTT();
        for (int i = 0; i < list.size(); i++) {
            count = 0;
            boolean check = true;
            String nsx = list.get(i).getCtsp().getSp().getNsx().getTen();
            if (i > 0) {
                for (int j = i - 1; j > 0; j--) {
                    if (list.get(j).getCtsp().getSp().getNsx().getTen().equalsIgnoreCase(nsx)) {
                        check = false;
                    }
                }

            }
            if (check) {
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j).getCtsp().getSp().getNsx().getTen().equalsIgnoreCase(nsx)) {
                        count++;
                    }
                }
                pie_Chart.addData(new ModelPieChart(nsx, count, getColor(index++)));
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
        btnLocNgay = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMayBanDuoc = new javax.swing.JTable();
        cbbNSX = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDonHuy = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblBaoHanh = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
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
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        tblDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên Nhân Viên", "Mã Hóa Đơn", "Ngày Thanh Toán", "Thành Tiền"
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

        jdcNgayBD.setDateFormatString("yyyy-MM-dd");

        jdcNgayKT.setDateFormatString("yyyy-MM-dd");

        jLabel2.setText("đến ngày");

        jLabel3.setText("Từ ngày");

        btnLocNgay.setText("Lọc ngày");
        btnLocNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocNgayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3)
                        .addGap(12, 12, 12)
                        .addComponent(jdcNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLocNgay)
                        .addGap(0, 17, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbLocThang, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbLocThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdcNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLocNgay)
                            .addComponent(jdcNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
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
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(43, 43, 43)
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        jPanel7.setOpaque(false);

        tblHoaDonHuy.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HD", "Sản Phẩm", "Thành Tiền", "Ngày Tạo", "Ngày hủy", "Lý do hủy"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblHoaDonHuy);
        if (tblHoaDonHuy.getColumnModel().getColumnCount() > 0) {
            tblHoaDonHuy.getColumnModel().getColumn(5).setHeaderValue("Lý do hủy");
        }

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 51, 0));
        jLabel7.setText("Hóa đơn hủy ");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 51, 0));
        jLabel8.setText("Bảo hành");

        tblBaoHanh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HD", "Sản Phẩm", "Thành Tiền", "Số lượng ", "Ngày thanh toán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblBaoHanh);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(480, 480, 480)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 95, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/excel.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(147, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout JBangThongKeLayout = new javax.swing.GroupLayout(JBangThongKe);
        JBangThongKe.setLayout(JBangThongKeLayout);
        JBangThongKeLayout.setHorizontalGroup(
            JBangThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JBangThongKeLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 236, Short.MAX_VALUE))
        );
        JBangThongKeLayout.setVerticalGroup(
            JBangThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JBangThongKeLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addComponent(JBangThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
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
        jLabel9.setText("Biểu đồ tròn thể hiện số lượng sản phẩm đã bán 2023");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 0, 0));
        jLabel10.setText("Biểu đồ đường cong thể hiện doanh thu qua từng tháng năm 2023");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 245, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(40, 40, 40))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
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
                .addGap(0, 284, Short.MAX_VALUE))
        );
        jpnBieuDoLayout.setVerticalGroup(
            jpnBieuDoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnBieuDoLayout.createSequentialGroup()
                .addComponent(JBieuDoThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 277, Short.MAX_VALUE))
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
        String thangCbb = this.cbbLocThang.getSelectedItem().toString();
        ArrayList<HoaDonChiTietModel> list = ihdct.getAllHoaDonCT();
        dtm.setRowCount(0);
        double total = 0;
        if (thangCbb.equalsIgnoreCase("All")) {
            loadTableDoanhThu();
        } else {
            for (HoaDonChiTietModel x : list) {
                if (x.getIdhd().getTrangThai() == 1) {
                    LocalDate lcd = LocalDate.parse(x.getNgayTao().toString());
                    String thangTao = UpperCaseFirst(subString(lcd.getMonth().toString().toLowerCase()));
                    if (thangTao.equalsIgnoreCase(thangCbb)) {
                        total += x.getThanhTien();
                        Object[] rowData = {
                            x.getIdhd().getNv().getHoTen(),
                            x.getIdhd().getMa(),
                            x.getNgayTao(),
                            vn.format(x.getThanhTien()) + " VND"
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
            vn.format(total) + " VND"
        };
        dtm.addRow(sum);
    }//GEN-LAST:event_cbbLocThangActionPerformed

    private void cbbNSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNSXActionPerformed
        String nsx = this.cbbNSX.getSelectedItem().toString();
        int dem = 0, sum;
        dtm2.setRowCount(0);
        ArrayList<HoaDonChiTietModel> list = ihdct.getAllHoaDonCT();
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

    private void btnLocNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocNgayActionPerformed
        Date ngayBD = jdcNgayBD.getDate();
        Date ngayKT = jdcNgayKT.getDate();
        if (ngayBD.after(ngayKT)) {
            JOptionPane.showMessageDialog(null, "Ngay bat dau phai truoc ngay ket thuc");
            return;
        }
        ArrayList<HoaDonChiTietModel> list = ihdct.getAllHoaDonCT();
        dtm.setRowCount(0);
        double total = 0;
        for (HoaDonChiTietModel x : list) { // 2 cái ch? khác m?agif làmijagilaf2 cái ngày bd v?i ngày kt ko khá
            if (x.getIdhd().getTrangThai() == 1) {
                if ((x.getIdhd().getNgayThanhToan().after(ngayBD) && x.getIdhd().getNgayThanhToan().before(ngayKT)) || (x.getIdhd().getNgayThanhToan()== ngayBD)) {
//            if (x.getIdhd().getNgayThanhToan().compareTo(ngayBD) == 0){
                System.out.println(x);
                    total += x.getThanhTien();
                    Object[] rowData = {
                        x.getIdhd().getNv().getHoTen(),
                        x.getIdhd().getMa(),
                        x.getIdhd().getNgayThanhToan(),
                        vn.format(x.getThanhTien()) + " VND"
                    };
                    dtm.addRow(rowData);
                }
            }
        }

        Object[] sum = {
            "",
            "",
            "Tổng Tiền",
            vn.format(total) + " VND"
        };
        dtm.addRow(sum);
    }//GEN-LAST:event_btnLocNgayActionPerformed

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        curveLine_Chart.clear();
        loadBieuDoTron();
        setDataChart();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        loadTableDoanhThu();
        loadTableSanPham();
        loadTableHDHuy();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Bạn có muốn tiếp tục xuất file  không?", "Thông báo", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Doanh Thu");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(0);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Danh Sách Doanh Thu");

            row = sheet.createRow(1);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Tên Nhân Viên");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã Hóa Đơn");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Ngày Tạo");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Thành Tiền");
            ArrayList<HoaDonChiTietModel> list = ihdct.getAllHoaDonCT();
            dtm.setRowCount(0);
            Locale localeVN = new Locale("vi", "VN");
            NumberFormat vn = NumberFormat.getInstance(localeVN);
            for (int i = 0; i < list.size(); i++) {
                row = sheet.createRow(2 + i);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(list.get(i).getIdhd().getNv().getHoTen());
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(list.get(i).getIdhd().getMa());
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(list.get(i).getNgayTao());
                cell = row.createCell(3, CellType.NUMERIC);
                cell.setCellValue(list.get(i).getThanhTien());
            }
            File f = new File("D:\\DuAn1_FInal\\excel\\DoanhThuExcel.xlsx");

            try {
                FileOutputStream fis = new FileOutputStream(f);
                wb.write(fis);
                fis.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            JOptionPane.showMessageDialog(null, "Done !!!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error !!!");
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JBangThongKe;
    private javax.swing.JPanel JBieuDoThongKe;
    private javax.swing.JButton btnLocNgay;
    private javax.swing.JComboBox<String> cbbLocThang;
    private javax.swing.JComboBox<String> cbbNSX;
    private CurveLineChart.CurveLine_Chart curveLine_Chart;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private CurveLineChart.JPanelShadow jPanelShadow;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JDateChooser jdcNgayBD;
    private com.toedter.calendar.JDateChooser jdcNgayKT;
    private javax.swing.JPanel jpnBang;
    private javax.swing.JPanel jpnBieuDo;
    private PieChart.Pie_Chart pie_Chart;
    private javax.swing.JTable tblBaoHanh;
    private javax.swing.JTable tblDoanhThu;
    private javax.swing.JTable tblHoaDonHuy;
    private javax.swing.JTable tblMayBanDuoc;
    // End of variables declaration//GEN-END:variables

    private void load() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    pie_Chart.clearData();
                    curveLine_Chart.start();
                    loadTableDoanhThu();
                    loadTableSanPham();
                    loadTableHDHuy();
                    loadBieuDoTron();
                    setDataChart();
                    curveLine_Chart.clear();
                    loadBieuDoTron();
                    loadBH();
                    setDataChart();

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
        }).start();
    }
}
