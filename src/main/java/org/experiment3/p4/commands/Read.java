package com.github.permissiondog.os.exp3.p4.commands;

import com.github.permissiondog.os.exp3.p4.Command;
import com.github.permissiondog.os.exp3.p4.NEUOS;

import java.io.IOException;

public class Read implements Command {
    @Override
    public void run(NEUOS os) throws IOException {
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
