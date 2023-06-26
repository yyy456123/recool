package org.experiment3.work4.commands;



import org.experiment3.work4.Command;
import org.experiment3.work4.MyOS;

import java.io.IOException;

public class Delete implements Command {
    @Override
    public void run(MyOS os) throws IOException {
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
