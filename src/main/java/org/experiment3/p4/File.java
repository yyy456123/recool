package com.github.permissiondog.os.exp3.p4;

import java.util.*;

public class File {
    private File parent;
    private final Map<String, File> children = new HashMap<>();
    private String data;
    private final boolean isDirectory;
    private final String fileName;
    private int protectionCode;
    private File(String fileName, File parent, int protectionCode, boolean isDirectory) {
        this.isDirectory = isDirectory;
        this.parent = parent;
        this.protectionCode = protectionCode;
        this.fileName = fileName;
    }
    public static File file(String filename, File parent, int protectionCode) {
        var f = new File(filename, parent, protectionCode, false);
        f.data = "";
        return f;
    }

    public static File directory(String fileName, File parent) {
        return new File(fileName, parent, 0, true);
    }

    private void ensureDirectory() {
        if (!isDirectory) {
            throw new RuntimeException("not a directory");
        }
    }
    private void ensureFile() {
        if (isDirectory) {
            throw new RuntimeException("not a file");
        }
    }


    public void setData(String data) {
        ensureFile();
        this.data = data;
    }

    public void addChild(File child) {
        ensureDirectory();
        this.children.put(child.getFileName(), child);
        child.setParent(this);
    }

    private void setParent(File parent) {
        this.parent = parent;
    }

    public File getChild(String fileName) {
        ensureDirectory();
        if (".".equals(fileName))   return this;
        if ("..".equals(fileName))  return parent == null ? this : parent;
        return children.get(fileName);
    }

    public List<File> getDirectories() {
        ensureDirectory();
        return children.values().stream().toList();
    }

    public String getFileName() {
        return fileName;
    }

    public String getData() {
        ensureFile();
        return data;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public String getFullPath() {
        var res = new StringBuilder();
        for (var temp = this; temp.parent != null; temp = temp.parent) {
            res.insert(0, "/" + temp.getFileName());
        }

        return res.length() == 0 ? "/" : res.toString();
    }

    public String getPosition() {
        return HexFormat.of().toHexDigits(hashCode());
    }

    public int getSize() {
        return data.length();
    }

    public void makeDir(String dirName) {
        this.addChild(directory(dirName, this));
    }

    public boolean readable() {
        return (0b100 & protectionCode) > 0;
    }

    public boolean writable() {
        return (0b010 & protectionCode) > 0;
    }

    public boolean executable() {
        return (0b001 & protectionCode) > 0;
    }

    public int getProtectionCode() {
        return protectionCode;
    }

    public void setProtectionCode(int protectionCode) {
        this.protectionCode = protectionCode;
    }

    public String getProtectionCodeStr() {
        return toBinary(protectionCode);
    }
    private String toBinary(int num) {
        return String.format("%3s", Integer.toBinaryString(num)).replace(" ", "0");
    }

    public void removeChild(String filename) {
        ensureDirectory();
        children.remove(filename);
    }
}
