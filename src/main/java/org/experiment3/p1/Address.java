package com.github.permissiondog.os.exp3.p1;

import java.util.HexFormat;

public class Address {
    private boolean pageFault;
    private int address;

    public Address(boolean pageFault, int address) {
        this.pageFault = pageFault;
        this.address = address;
    }

    public boolean isPageFault() {
        return pageFault;
    }

    public void setPageFault(boolean pageFault) {
        this.pageFault = pageFault;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    @Override
    public String toString() {
        if (pageFault) {
            return "*" + address;
        }
        return HexFormat.of().toHexDigits(address);
    }
}
