package model;

import java.util.UUID;

public class VirtualServer {

    public static final int[][] VS_TYPES = {
            {1, 2, 10},
            {2, 4, 20},
            {4, 8, 40},
            {4, 16, 50},
            {8, 16, 100},
            {8, 32, 110},
            {8, 64, 120},
            {12, 24, 240},
            {12, 36, 250},
            {24, 48, 300},
            {24, 96, 310},
            {32, 64, 620},
            {32, 128, 630},
            {32, 192, 640},
            {32, 256, 650}
    };

    public static final double K_72 = 0.05;
    public static final double K_10 = 0.07;
    public static final double SSD = 0.09;

    private String id;
    private int virtualCores;
    private int virtualRam;
    private int diskSpace;
    private String diskType;

    public VirtualServer(){
        this.id = UUID.randomUUID().toString();
    }

    public VirtualServer(int virtualCores, int virtualRam, int storage, String diskType) {
        this.id = UUID.randomUUID().toString();
        this.virtualCores = virtualCores;
        this.virtualRam = virtualRam;
        this.diskSpace = storage;
        this.diskType = diskType;
    }

    public int getVirtualCores() {
        return virtualCores;
    }

    public void setVirtualCores(int virtualCores) {
        this.virtualCores = virtualCores;
    }

    public int getVirtualRam() {
        return virtualRam;
    }

    public void setVirtualRam(int virtualRam) {
        this.virtualRam = virtualRam;
    }

    public int getDiskSpace() {
        return diskSpace;
    }

    public void setDiskSpace(int diskSpace) {
        this.diskSpace = diskSpace;
    }

    public String getDiskType() {
        return diskType;
    }

    public void setDiskType(String diskType) {
        this.diskType = diskType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getMonthlyFee() {
        double fee = 0;
        for (int[] vs : VS_TYPES) {
            if (vs[0] == virtualCores && vs[1] == virtualRam) {
                fee = vs[2];
            }
        }

        if (diskType.equals("7.2K"))
            fee += K_72 * diskSpace;
        else if (diskType.equals("10K"))
            fee += K_10 * diskSpace;
        else if (diskType.equals("SSD"))
            fee += SSD * diskSpace;
        else
            return -1;

        return fee;
    }
}
