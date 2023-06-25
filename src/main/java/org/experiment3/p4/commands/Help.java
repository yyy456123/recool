package com.github.permissiondog.os.exp3.p4.commands;

import com.github.permissiondog.os.exp3.p4.Command;
import com.github.permissiondog.os.exp3.p4.NEUOS;

public class Help implements Command {
    @Override
    public void run(NEUOS os) {
        System.out.println("命令列表:");
        os.getCommandList().forEach(cmd ->
            System.out.println(cmd.getCommandName() + " - " + cmd.getDescription())
        );
    }

    @Override
    public String getCommandName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "查看命令列表";
    }

    @Override
    public boolean needLogin() {
        return false;
    }
}
