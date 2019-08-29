package model;

import java.util.UUID;

public class VirtualServer {

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
}
