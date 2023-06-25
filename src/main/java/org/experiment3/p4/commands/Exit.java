package com.github.permissiondog.os.exp3.p4.commands;

import com.github.permissiondog.os.exp3.p4.Command;
import com.github.permissiondog.os.exp3.p4.NEUOS;

public class Exit implements Command {
    @Override
    public void run(NEUOS os) {
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
