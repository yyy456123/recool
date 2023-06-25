package com.github.permissiondog.os.exp3.p4.commands;

import com.github.permissiondog.os.exp3.p4.Command;
import com.github.permissiondog.os.exp3.p4.NEUOS;

import java.io.IOException;

public class Write implements Command {
    @Override
    public void run(NEUOS os) throws IOException {
        System.out.print("请输入写出的文件名> ");
        var filename = os.in().readLine();
        var file = os.getWorkingDirectory().getChild(filename);
        if (file == null) {
            System.out.println("\033[31m文件 " + filename + " 不存在\033[0m");
            return;
        }
        if (!file.writable()) {
            System.out.println("\033[31m文件 " + filename + " 无写权限\033[0m");
            return;
        }
        System.out.println("请输入文件内容, 以单行 :q 结束输入");
        var data = new StringBuilder();
        String temp;
        while (!(temp = os.in().readLine()).equals(":q")) {
            data.append(temp);
            data.append("\n");
        }
        data.deleteCharAt(data.length() - 1);
        file.setData(data.toString());
    }

    @Override
    public String getCommandName() {
        return "write";
    }

    @Override
    public String getDescription() {
        return "写出文件";
    }

    @Override
    public boolean needLogin() {
        return true;
    }
}
