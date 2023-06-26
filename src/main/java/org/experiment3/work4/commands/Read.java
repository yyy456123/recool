package org.experiment3.work4.commands;


import org.experiment3.work4.Command;
import org.experiment3.work4.MyOS;

import java.io.IOException;

public class Read implements Command {
    @Override
    public void run(MyOS os) throws IOException {
        System.out.print("请输入读入的文件名> ");
        var filename = os.in().readLine();
        var file = os.getWorkingDirectory().getChild(filename);
        if (file == null) {
            System.out.println("\033[31m文件 " + filename + " 不存在\033[0m");
            return;
        }
        if (!file.readable()) {
            System.out.println("\033[31m文件 " + filename + " 无读权限\033[0m");
            return;
        }
        System.out.println(file.getData());
    }

    @Override
    public String getCommandName() {
        return "read";
    }

    @Override
    public String getDescription() {
        return "读取文件";
    }

    @Override
    public boolean needLogin() {
        return true;
    }
}
