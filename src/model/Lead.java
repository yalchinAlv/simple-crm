package model;

import java.time.LocalDateTime;
import java.util.List;

public class Lead {

    private String name;
    private List<VirtualServer> service;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double monthlyFee;

}
