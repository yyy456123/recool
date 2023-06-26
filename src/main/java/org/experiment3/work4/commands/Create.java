package org.experiment3.work4.commands;



import org.experiment3.work4.Command;
import org.experiment3.work4.MyOS;

import java.io.IOException;

public class Create implements Command {
    @Override
    public void run(MyOS os) throws IOException {
        System.out.print("请输入文件名> ");
        var filename = os.in().readLine();
        System.out.print("请输入保护码(三位二进制 读写执行)> ");
        var protectionCodeStr = os.in().readLine();
        if (protectionCodeStr.length() != 3 || protectionCodeStr.replaceAll("[01]", "").length() != 0) {
            System.out.println("\033[31m保护码格式错误\033[0m");
            return;
        }
        var protectionCode = 0;
        protectionCode += protectionCodeStr.codePointAt(0) == '1' ? 0b100 : 0;
        protectionCode += protectionCodeStr.codePointAt(1) == '1' ? 0b010 : 0;
        protectionCode += protectionCodeStr.codePointAt(2) == '1' ? 0b001 : 0;

        os.create(os.getWorkingDirectory(), filename, protectionCode);
    }

    @Override
    public String getCommandName() {
        return "create";
    }

    @Override
    public String getDescription() {
        return "创建文件";
    }

    @Override
    public boolean needLogin() {
        return true;
    }
}
