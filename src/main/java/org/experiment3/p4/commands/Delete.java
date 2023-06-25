package com.github.permissiondog.os.exp3.p4.commands;

import com.github.permissiondog.os.exp3.p4.Command;
import com.github.permissiondog.os.exp3.p4.NEUOS;

import java.io.IOException;

public class Delete implements Command {
    @Override
    public void run(NEUOS os) throws IOException {
        System.out.print("请输入要删除的文件名> ");
        var filename = os.in().readLine();
        var file = os.getWorkingDirectory().getChild(filename);
        if (file == null) {
            System.out.println("\033[31m文件 " + filename + " 不存在\033[0m");
            return;
        }
        os.getWorkingDirectory().removeChild(filename);
    }

    @Override
    public String getCommandName() {
        return "delete";
    }

    @Override
    public String getDescription() {
        return "删除文件";
    }

    @Override
    public boolean needLogin() {
        return true;
    }
}
