package model;

import java.util.ArrayList;
import java.util.List;

public class Stock {

    private List<Server> servers;

    public Stock() {
        servers = new ArrayList<>();
    }

    public void addServer(int cores, int ram) {
        servers.add(new Server(cores, ram));
    }

    public List<Server> getServers() {
        return servers;
    }
}
