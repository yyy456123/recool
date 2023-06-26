package org.experiment3.work4.commands;


import org.experiment3.work4.Command;
import org.experiment3.work4.MyOS;

import java.io.IOException;

public class Md implements Command {
    @Override
    public void run(MyOS os) throws IOException {
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
