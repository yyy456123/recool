package org.experiment3.work4.commands;


import org.experiment3.work4.Command;
import org.experiment3.work4.MyOS;

public class Help implements Command {
    @Override
    public void run(MyOS os) {
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
