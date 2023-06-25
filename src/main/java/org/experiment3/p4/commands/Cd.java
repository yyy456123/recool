package com.github.permissiondog.os.exp3.p4.commands;

import com.github.permissiondog.os.exp3.p4.Command;
import com.github.permissiondog.os.exp3.p4.NEUOS;

import java.io.IOException;

public class Cd implements Command {
    @Override
    public void run(NEUOS os) throws IOException {
        System.out.print("请输入要切换的目录 > ");
        var dir = os.in().readLine();

        var wd = dir.startsWith("/") ? os.getRoot() : os.getWorkingDirectory();
        var dirs = dir.split("[/\\\\]+");
        for (var d : dirs) {
            wd = wd.getChild(d);
        }
        if (wd == null) {
            System.out.println("\033[31m目录 " + dir + " 不存在\033[0m");
            return;
        }
        os.setWorkingDirectory(wd);
    }

    @Override
    public String getCommandName() {
        return "cd";
    }

    @Override
    public String getDescription() {
        return "切换工作目录";
    }

    @Override
    public boolean needLogin() {
        return true;
    }
}
