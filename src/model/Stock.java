package model;

import java.util.ArrayList;
import java.util.List;

public class Stock {

    private int availableCores;
    private int availableRam;
    private int usedCores;
    private int usedRam;

    public Stock() {
    }

    public Stock(int availableCores, int availableRam, int usedCores, int usedRam) {
        this.availableCores = availableCores;
        this.availableRam = availableRam;
        this.usedCores = usedCores;
        this.usedRam = usedRam;
    }

    public int getAvailableCores() {
        return availableCores;
    }

    public void setAvailableCores(int availableCores) {
        this.availableCores = availableCores;
    }

    public int getAvailableRam() {
        return availableRam;
    }

    public void setAvailableRam(int availableRam) {
        this.availableRam = availableRam;
    }

    public int getUsedCores() {
        return usedCores;
    }

    public void setUsedCores(int usedCores) {
        this.usedCores = usedCores;
    }

    public int getUsedRam() {
        return usedRam;
    }

    public void setUsedRam(int usedRam) {
        this.usedRam = usedRam;
    }
}
