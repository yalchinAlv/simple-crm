package model;

import java.util.ArrayList;
import java.util.List;

public class Server {

    private int cores;
    private int ram;
    private List<VirtualServer> virtualServers;

    public Server(int cores, int ram) {
        this.cores = cores;
        this.ram = ram;
        virtualServers = new ArrayList<>();
    }

    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public boolean addVirtualServer(int virtualCores, int virtualRam, int storage, String disk_type) {

        // not enough resource available
        if (virtualCores > cores || virtualRam > ram)
            return false;

        // allocate the virtual server resources
        cores -= virtualCores;
        ram -= virtualRam;

        // add the virtual server
        virtualServers.add(new VirtualServer(virtualCores, virtualRam, storage, disk_type));

        return true;
    }

    public List<VirtualServer> getVirtualServers() {
        return virtualServers;
    }
}
