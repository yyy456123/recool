package com.github.permissiondog.os.exp3.p4.commands;

import com.github.permissiondog.os.exp3.p4.Command;
import com.github.permissiondog.os.exp3.p4.NEUOS;

import java.io.IOException;

public class Md implements Command {
    @Override
    public void run(NEUOS os) throws IOException {
        System.out.print("请输入要创建的文件夹名 > ");
        var dirName = os.in().readLine().trim();
        os.getWorkingDirectory().makeDir(dirName);
    }

    @Override
    public String getCommandName() {
        return "md";
    }

    @Override
    public String getDescription() {
        return "创建目录";
    }

    @Override
    public boolean needLogin() {
        return true;
    }
}
