import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

public class RR {

    /**
     * Description:
     *     mProcessCount : Count(s) Of Process(es).
     *     mReadyQueue : A Queue For Process(es) That Ready To Run.
     *     mUnreachQueue : A Queue For Process(es) Is Not Finish.
     *     mTimeSlice : Time Slice.
     *     mExecutedQueue : A Queue For Process(es) Finish.
     * Author : Monster
     * Date : 2019.10
     */
    private int mProcessCount;
    private Queue<process> mReadyQueue;
    private Queue<process> mUnreachQueue;
    private int mTimeSlice;
    private Queue<process> mExecutedQueue;
    private double mTotalWholeTime = 0.0;
    private double mTotalWeightWholeTime = 0.0;


    /**
     * Constructor Function
     * @param processCount
     * @param processQueue
     * @param timeSlice
     */
    private RR(int processCount, Queue<process> processQueue, int timeSlice) {
        this.mProcessCount = processCount;
        this.mUnreachQueue = processQueue;
        this.mReadyQueue = new LinkedBlockingQueue();
        this.mTimeSlice = timeSlice;
        this.mExecutedQueue = new LinkedList();
    }


    /**
     * RR Algorithm
     */
    private void RRAlgorithm() {
        mReadyQueue.add(mUnreachQueue.poll());
        process currProcess = mReadyQueue.poll();
        int currTime = executeProcess(currProcess, 0);
        while(!mReadyQueue.isEmpty() || !mUnreachQueue.isEmpty()) {
            while(!mUnreachQueue.isEmpty()) {
                if(mUnreachQueue.peek().getArrivalTime() <= currTime) {
                    mReadyQueue.add(mUnreachQueue.poll());
                } else {
                    break;
                }
            }
            if(currProcess.getRemainServiceTime() > 0) mReadyQueue.add(currProcess);
            if(!mReadyQueue.isEmpty()) {
                currProcess = mReadyQueue.poll();
                currTime = executeProcess(currProcess, currTime);
            }
            else {
                currTime = mUnreachQueue.peek().getArrivalTime();
            }
        }
    }

    /**
     * A Function To Execute A Process
     * @param currProcess The Process Which Is Going to Execute
     * @param currTime The Time Before The Process Execute
     * @return Current Time
     */
    private int executeProcess(process currProcess, int currTime) {
        if(currProcess.getRemainServiceTime() - mTimeSlice <= 0) {
            showExecuteMessage(currTime, currTime += currProcess.getRemainServiceTime(), currProcess.getName());
            currProcess.setFinishTime(currTime);
            currProcess.setRemainServiceTime(0);
            calculeteWholeTime(currProcess);
            calculateWeightWholeTime(currProcess);
            mTotalWholeTime += currProcess.getWholeTime();
            mTotalWeightWholeTime += currProcess.getWeightWholeTime();
            mExecutedQueue.add(currProcess);
        }
        else {
            showExecuteMessage(currTime, currTime += mTimeSlice, currProcess.getName());
            currProcess.setRemainServiceTime(currProcess.getRemainServiceTime() - mTimeSlice);
        }
        return currTime;
    }

    /**
     * Calculet Whole Time
     * @param process Just A Process
     */
    private void calculeteWholeTime(process process) {
        process.setWholeTime(process.getFinishTime() - process.getArrivalTime());
    }

    /**
     * Calculate Weight Whole Time
     * @param process Just A Process
     */
    private void calculateWeightWholeTime(process process) {
        process.setWeightWholeTime(process.getWholeTime() / (double)process.getServiceTime());
    }

    /**
     * Print Execute Messages
     * @param startTime A Process Start Time
     * @param endTime A Process End Time
     * @param name The Name Of The Process
     */
    private void showExecuteMessage(int startTime, int endTime, String name) {
        System.out.println(startTime + " -- " + endTime + ":[process" + name + "] is running");
    }

    /**
     * Print The Results
     */
    private void showResult() {
        System.out.print("process ");
        System.out.print("turnaround time ");
        System.out.println("weight turnaround time ");
        process process ;
        while(!mExecutedQueue.isEmpty()) {
            process = mExecutedQueue.poll();
            System.out.print("process" + process.getName() + " ");
            System.out.print("            " + process.getWholeTime() + " ");
            System.out.println("              " + process.getWeightWholeTime() + " ");
        }
        System.out.println("average turnaround time:" + mTotalWholeTime / (double) mProcessCount);
        System.out.println("average weight turnaround time:" + mTotalWeightWholeTime / (double) mProcessCount);
    }

    /**
     * Print All Information
     * @param queue Process(es) Queue
     */
    private static void printAll(Queue<process> queue) {
        System.out.print("process ");
        System.out.print("reach time ");
        System.out.println("running time ");
        process process;
        while (!queue.isEmpty()){
            process = queue.poll();
            System.out.print("process" + process.getName() + " ");
            System.out.print(" " + process.getArrivalTime() + " ");
            System.out.println(" " + process.getServiceTime() + " ");
        }
    }

    /**
     * Entrance For Program Start
     */
    public static void RRprocess(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the count(s) for process(es):");
        int processCount = scanner.nextInt();
        if(processCount < 1) return;
        Queue<process> queue = new LinkedBlockingQueue();
        System.out.println("Input the reach time for every process(in order by reach time):");
        for(int i=0; i<processCount; i++) {
            process process = new process((char)(i + 65) + "");
            process.setArrivalTime(scanner.nextInt());
            queue.add(process);
        }
        System.out.println("Input the service time for every process(in order by reach time):");
        for(int i=0; i<processCount; i++) {
            process process = queue.poll();
            int time = scanner.nextInt();
            process.setServiceTime(time);
            process.setRemainServiceTime(time);
            queue.add(process);
        }
        System.out.println("Input the time slice:");
        int timeSlice = scanner.nextInt();
        RR rr = new RR(processCount, queue, timeSlice);
        RR.printAll(new LinkedBlockingQueue(queue));
        rr.RRAlgorithm();
        rr.showResult();
    }
}