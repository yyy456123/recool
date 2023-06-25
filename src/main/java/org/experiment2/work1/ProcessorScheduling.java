package org.experiment2.work1;

import java.util.ArrayList;
import java.util.List;

class Process {
    private String name;
    private int priority;
    private int remainingTime;
    private String status;

    public Process(String name, int priority, int remainingTime) {
        this.name = name;
        this.priority = priority;
        this.remainingTime = remainingTime;
        this.status = "ready";
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}

public class ProcessorScheduling {
    public static void main(String[] args) {
        List<Process> processQueue = new ArrayList<>();
        processQueue.add(new Process("P1", 1, 2));
        processQueue.add(new Process("P2", 5, 3));
        processQueue.add(new Process("P3", 3, 1));
        processQueue.add(new Process("P4", 4, 2));
        processQueue.add(new Process("P5", 2, 4));

        int currentTime = 0;
        System.out.println("INPUT NAME , NEEDTIME AND PRIORITY");
        for (Process process : processQueue) {
            System.out.println(process.getName() + " " + process.getRemainingTime() + " " + process.getPriority());
        }

        System.out.println("OUTPUT OF PRIORITY:");
        List<Process> completedProcesses = new ArrayList<>(); // 新建一个空列表用于保存已完成的进程
        while (!processQueue.isEmpty()) {
            Process currentProcess = processQueue.get(0);
            currentProcess.setStatus("working");
            System.out.println("CPUTIME:" + currentTime);
            for (Process process : processQueue) {
                System.out.println(process.getName() + " " + currentTime + " " + process.getRemainingTime()
                        + " " + process.getPriority() + " " + process.getStatus());
            }

            currentTime++;
            currentProcess.setPriority(currentProcess.getPriority() - 1);
            currentProcess.setRemainingTime(currentProcess.getRemainingTime() - 1);

            if (currentProcess.getRemainingTime() > 0) {
                processQueue.add(currentProcess);
            } else {
                currentProcess.setStatus("finish");
                completedProcesses.add(currentProcess); // 将已完成的进程添加到 completedProcesses 列表中
            }

            processQueue.remove(currentProcess);
        }

        System.out.println("NAME RoundTime WaitingTime");
        int totalRoundTime = 0;
        int totalWaitingTime = 0;
        for (Process process : completedProcesses) { // 遍历已完成的进程列表进行计算和输出结果
            int roundTime = currentTime;
            int waitingTime = roundTime - process.getRemainingTime();
            totalRoundTime += roundTime;
            totalWaitingTime += waitingTime;
            System.out.println(process.getName() + " " + roundTime + " " + waitingTime);
        }
        System.out.println("Average RoundTime: " + (double) totalRoundTime / completedProcesses.size());
        System.out.println("Average WaitingTime: " + (double) totalWaitingTime / completedProcesses.size());
    }
}