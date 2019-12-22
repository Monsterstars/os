/**
 * Description:
 *     A Process Class：
 *         A Process Have Name, ArrivalTime. ServiceTime, RemainTime, FinishTime, WholeTime. WeightWholeTime.
 *         Add Getter$Setter Functions
 *
 * Author : Monster
 * Date : 2019.10
 */
public class process {
    private String name;
    private int arrivalTime;
    private int serviceTime;
    private int remainServiceTime;
    private int finishTime;
    private int wholeTime;
    private double weightWholeTime;

    public process(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getRemainServiceTime() {
        return remainServiceTime;
    }

    public void setRemainServiceTime(int remainServiceTime) {
        this.remainServiceTime = remainServiceTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public int getWholeTime() {
        return wholeTime;
    }

    public void setWholeTime(int wholeTime) {
        this.wholeTime = wholeTime;
    }

    public double getWeightWholeTime() {
        return weightWholeTime;
    }

    public void setWeightWholeTime(double weightWholeTime) {
        this.weightWholeTime = weightWholeTime;
    }
}