package org.experiment3.work4.commands;


import org.experiment3.work4.Command;
import org.experiment3.work4.MyOS;

public class Logout implements Command {
    @Override
    public void run(MyOS os) {
        os.logout();
    }

    @Override
    public String getCommandName() {
        return "logout";
    }

    @Override
    public String getDescription() {
        return "登出系统";
    }

    @Override
    public boolean needLogin() {
        return true;
    }
}
