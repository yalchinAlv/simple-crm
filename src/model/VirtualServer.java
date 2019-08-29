package model;

public class VirtualServer {

    private int virtualCores;
    private int virtualRam;
    private int disk_space;
    private String disk_type;

    public VirtualServer(int virtualCores, int virtualRam, int storage, String disk_type) {
        this.virtualCores = virtualCores;
        this.virtualRam = virtualRam;
        this.disk_space = storage;
        this.disk_type = disk_type;
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

    public int getDisk_space() {
        return disk_space;
    }

    public void setDisk_space(int disk_space) {
        this.disk_space = disk_space;
    }

    public String getDisk_type() {
        return disk_type;
    }

    public void setDisk_type(String disk_type) {
        this.disk_type = disk_type;
    }
}
