package com.github.permissiondog.os.exp3.p4.commands;

import com.github.permissiondog.os.exp3.p4.Command;
import com.github.permissiondog.os.exp3.p4.NEUOS;

import java.io.IOException;

public class Logout implements Command {
    @Override
    public void run(NEUOS os) {
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
