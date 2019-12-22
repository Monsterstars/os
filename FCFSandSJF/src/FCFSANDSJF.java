import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


/**
 * @auther: Monster
 * @date: 2019/10/26
 * @description:
 */
public class FCFSANDSJF {

    /**
     * Print Information
     * @param p Process(es)
     */
    private static void showResult(process[] p) {
        //whole turnaround time
        float sumWT = 0;
        //whole weight turnaround time
        float sumWWT = 0;
        //average turnaround time
        float AverageWT;
        //average weight turnaround time
        float AverageWWT;
        //print result
        for (int i = 0; i < p.length; i++) {
            System.out.println(p[i].getStartTime() + ": process " + p[i].getName() + " is running, finish time is:" + p[i].getFinishTime() + ", turnaround time：" + p[i].getWholeTime() + ",weight turnaround time：" + p[i].getWeightWholeTime());
            sumWT += p[i].getWholeTime();
            sumWWT += p[i].getWeightWholeTime();
        }
        AverageWT = sumWT / p.length;
        AverageWWT = sumWWT / p.length;
        System.out.println("average turnaround time:" + AverageWT);
        System.out.println("average weight turnaround time:" + AverageWWT);
    }

    /**
     * A Function To Find The Next Process(Using In Short Job First)
     * @param list A List Of Process(es)
     * @param now The Time Which
     * @return The Next Process
     */
    private static process FindNextPro(List<process> list, int now) {
        process pro = list.get(0);
        int index = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getServiceTime() < pro.getServiceTime() && list.get(i).getArrivalTime() < now) {
                pro = list.get(i);
                index = i;
            }
        }
        list.remove(index);
        return pro;
    }


    /**
     * First-Come First-Service Algorithm
     * @param p Process(es)
     */
    private static void FCFS(process[] p) {
        for (int i = 0; i < p.length; i++) {

            //calculeteWholeTime
            if (i == 0) {
                p[i].setFinishTime(p[i].getArrivalTime() + p[i].getServiceTime());
            }
            else {
                if (p[i].getArrivalTime() > p[i - 1].getFinishTime()){
                    p[i].setFinishTime(p[i].getArrivalTime() + p[i].getServiceTime());
                    p[i].setStartTime(p[i].getArrivalTime());
                }
                else {
                    p[i].setFinishTime(p[i].getServiceTime() + p[i - 1].getFinishTime());
                    p[i].setStartTime(p[i - 1].getFinishTime());
                }
            }

            //calculateWeightWholeTime
            p[i].setWholeTime(p[i].getFinishTime() - p[i].getArrivalTime());
            p[i].setWeightWholeTime((double) p[i].getWholeTime() / (double) p[i].getServiceTime());
        }
    }

    /**
     * Short Job First
     * @param p Process(es)
     * @return Process(es) How To Run
     */
    public static process[] SJF(process[] p) {

        // what time is it
        int now = 0;

        // a process list which is running
        List<process> list = new LinkedList();
        // the result process list
        List<process> res = new LinkedList();

        p[0].setFinishTime(p[0].getArrivalTime() + p[0].getServiceTime());
        p[0].setWholeTime(p[0].getFinishTime() - p[0].getArrivalTime());
        p[0].setWeightWholeTime(p[0].getWholeTime() / p[0].getServiceTime());
        res.add(p[0]);
        now = p[0].getFinishTime();

        for (int i = 1; i < p.length; i++) {
            list.add(p[i]);
        }
        while (list.size() != 0) {
            process next = FindNextPro(list, now);
            if (next.getArrivalTime() > now) {
                next.setFinishTime(next.getArrivalTime() + next.getServiceTime());
                next.setStartTime(next.getArrivalTime());
            }
            else {
                next.setFinishTime(now + next.getServiceTime());
                next.setStartTime(now);
            }
            now = next.getFinishTime();
            next.setWholeTime(next.getFinishTime() - next.getArrivalTime());
            next.setWeightWholeTime((double) next.getWholeTime() / (double) next.getServiceTime());
            res.add(next);
        }
        return res.toArray(new process[0]);
    }


    /**
     * Entrance For Program Start
     */
    public static void FCFSandSJF() {
        Scanner in = new Scanner(System.in);
        System.out.println("Input The Number Of Process(es):");
        int n = in.nextInt();
        process[] p = new process[n];
        System.out.println("Input the reach time and service time for every process(in order by reach time): ");
        for (int i = 0; i < n; i++) {
            int arrTime = in.nextInt();
            int serTime = in.nextInt();
            String name = (char)(i + 65) + "";
            p[i] = new process(arrTime, serTime, name);
        }
        System.out.println("Choose An Algorithm: 1 for First-Come First-Service and 2 for Short Job First");
        int select = in.nextInt();
        if (select == 1) {
            System.out.println("First-Come First-Service");
            FCFS(p);
            showResult(p);
        }
        else if (select == 2) {
            System.out.println("Short Job First");
            process[] processes = SJF(p);
            showResult(processes);
        }
    }
}