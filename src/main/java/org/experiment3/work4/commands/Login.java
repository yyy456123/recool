package org.experiment3.work4.commands;


import org.experiment3.work4.Command;
import org.experiment3.work4.MyOS;

import java.io.IOException;

public class Login implements Command {
    @Override
    public void run(MyOS os) throws IOException {
        System.out.print("请输入用户名> ");
        var username = os.in().readLine();
        try {
            os.login(username);
        } catch (RuntimeException e) {
            System.out.println("用户名不存在");
        }
    }

    @Override
    public String getCommandName() {
        return "login";
    }

    @Override
    public String getDescription() {
        return "登录系统";
    }

    @Override
    public boolean needLogin() {
        return false;
    }
}
