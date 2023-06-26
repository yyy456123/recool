package org.experiment3.work4.commands;


import org.experiment3.work4.Command;
import org.experiment3.work4.MyOS;

public class Dir implements Command {
    private final static String DIR_FORMAT = "%-6s %-8s %-4s \033[36m%s\033[0m\n";
    private static final String FILE_FORMAT = "%-6s %-8s %-4s \033[32m%s\033[0m\n";
    @Override
    public void run(MyOS os) {
        System.out.printf("\033[1m%-6s %-8s %-4s %s\033[0m\n", "length", "position", "code", "name");
        System.out.printf(DIR_FORMAT, "", "", "", ".");
        System.out.printf(DIR_FORMAT, "", "", "", "..");
        os.getWorkingDirectory().getDirectories().stream().sorted((a, b) -> {
            if (a.isDirectory() && b.isDirectory() || !a.isDirectory() && !b.isDirectory())
                return a.getFileName().compareTo(b.getFileName());
            if (a.isDirectory()) return -1;
            else return 1;
        }).forEach(file -> {
            if (file.isDirectory()) {
                System.out.printf(DIR_FORMAT, "", "", "", file.getFileName());
            } else {
                System.out.printf(FILE_FORMAT, file.getSize(), file.getPosition(), file.getProtectionCodeStr(), file.getFileName());
            }
        });
    }



    @Override
    public String getCommandName() {
        return "dir";
    }

    @Override
    public String getDescription() {
        return "列出文件目录";
    }

    @Override
    public boolean needLogin() {
        return true;
    }
}
