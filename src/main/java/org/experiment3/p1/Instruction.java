package com.github.permissiondog.os.exp3.p1;

public class Instruction {
    private String operation;
    private int pageNumber;
    private int cellNumber;

    public Instruction(String operation, int pageNumber, int cellNumber) {
        this.operation = operation;
        this.pageNumber = pageNumber;
        this.cellNumber = cellNumber;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(int cellNumber) {
        this.cellNumber = cellNumber;
    }
}
