package CurveLineChart;

public class ModelData {

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public ModelData(String month, double cost) {
        this.month = month;
        this.cost = cost;
    }

    public ModelData() {
    }

    private String month;
    private double cost;
}
