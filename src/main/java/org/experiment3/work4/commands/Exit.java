package org.experiment3.work4.commands;


import org.experiment3.work4.Command;
import org.experiment3.work4.MyOS;

public class Exit implements Command {
    @Override
    public void run(MyOS os) {
        os.exit();
    }

    @Override
    public String getCommandName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "退出系统";
    }

    @Override
    public boolean needLogin() {
        return false;
    }
}
