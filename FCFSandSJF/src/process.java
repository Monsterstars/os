/**
 * @Auther: Monster
 * @Date: 2019/10/26
 * @Description:
 *     A Process Class：
 *         A Process Have Name, ArrivalTime. ServiceTime, RemainTime, FinishTime, WholeTime. WeightWholeTime.
 *         Add Getter$Setter Functions
 *
 */
public class process {

    private int arrivalTime;
    private int serviceTime;
    private int finishTime;
    private int startTime;
    private int WholeTime;
    private double weightWholeTime;
    private String name;

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

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getWholeTime() {
        return WholeTime;
    }

    public void setWholeTime(int wholeTime) {
        WholeTime = wholeTime;
    }

    public double getWeightWholeTime() {
        return weightWholeTime;
    }

    public void setWeightWholeTime(double weightWholeTime) {
        this.weightWholeTime = weightWholeTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public process(int x, int y, String name) {
        this.arrivalTime = x;
        this.serviceTime = y;
        this.name = name;
    }

    public process() {
    }
}
