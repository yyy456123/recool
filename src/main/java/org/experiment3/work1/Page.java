package org.experiment3.work1;

public class Page {
    private int number;
    private boolean inMemory;
    private int blockNumber;
    private int diskPosition;

    public Page(int number, boolean inMemory, int blockNumber, int diskPosition) {
        this.number = number;
        this.inMemory = inMemory;
        this.blockNumber = blockNumber;
        this.diskPosition = diskPosition;
    }

    public Page(int number, boolean inMemory, int diskPosition) {
        this.number = number;
        this.inMemory = inMemory;
        this.blockNumber = -1;
        this.diskPosition = diskPosition;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isInMemory() {
        return inMemory;
    }

    public void setInMemory(boolean inMemory) {
        this.inMemory = inMemory;
    }

    public int getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(int blockNumber) {
        this.blockNumber = blockNumber;
    }

    public int getDiskPosition() {
        return diskPosition;
    }

    public void setDiskPosition(int diskPosition) {
        this.diskPosition = diskPosition;
    }
}
