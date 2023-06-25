package org.experiment2.work2;

import java.util.ArrayList;
import java.util.List;

class PCB {
    String name;  // 进程名
    int pointer;  // 指针
    int requiredTime;  // 要求运行时间
    int executedTime;  // 已运行时间
    String status;  // 状态

    public PCB(String name, int requiredTime) {
        this.name = name;
        this.pointer = -1;
        this.requiredTime = requiredTime;
        this.executedTime = 0;
        this.status = "R";
    }
}

public class Scheduler {
    public static void main(String[] args) {
        // 创建五个进程的进程控制块 PCB
        List<PCB> pcbList = new ArrayList<>();
        pcbList.add(new PCB("Q1", 2));
        pcbList.add(new PCB("Q2", 3));
        pcbList.add(new PCB("Q3", 1));
        pcbList.add(new PCB("Q4", 2));
        pcbList.add(new PCB("Q5", 4));

        // 设置指针和初始标志单元
        int[] pointers = new int[]{1, 2, 3, 4, 0};
        int flag = 2;

        // 输出初始状态
        System.out.println("CPUTIME:0");
        for (PCB pcb : pcbList) {
            System.out.println(pcb.name + " 0 " + pcb.requiredTime + " " + pcb.status);
        }

        int cpuTime = 1;
        while (true) {
            boolean allFinished = true;

            // 遍历进程控制块
            for (int i = 0; i < pcbList.size(); i++) {
                PCB pcb = pcbList.get(i);
                if (pcb.status.equals("R")) {
                    allFinished = false;

                    // 模拟运行一个单位时间
                    pcb.executedTime++;

                    // 输出选中进程的进程名和进程队列变化
                    System.out.println("CPUTIME:" + cpuTime);
                    System.out.println(pcb.name);

                    // 判断进程是否执行结束
                    if (pcb.executedTime == pcb.requiredTime) {
                        pcb.status = "E";  // 修改状态为“结束”

                        // 将指针值送到前一个进程的指针位置
                        int prevIndex = (i == 0) ? pcbList.size() - 1 : i - 1;
                        pcbList.get(prevIndex).pointer = pcb.pointer;
                    }

                    // 更新标志单元和指针值
                    if (pcb.pointer == -1) {
                        pcb.pointer = pointers[pcbList.size() - 1];
                    } else {
                        pcb.pointer = pointers[pcb.pointer];
                    }


                    // 输出进程控制块的动态变化过程
                    for (PCB p : pcbList) {
                        System.out.print(p.name + " " + p.executedTime + " " + p.requiredTime + " " + p.status + " ");
                    }
                    System.out.println();
                }
            }

            // 判断是否所有进程都已结束
            if (allFinished) {
                break;
            }

            cpuTime++;
        }
    }
}
