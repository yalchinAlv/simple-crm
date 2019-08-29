package model;

import java.time.LocalDateTime;
import java.util.List;

public class Lead {

    private String name;
    private List<VirtualServer> service;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double monthlyFee;

    public Lead() {
    }

    public Lead(String name, List<VirtualServer> service, LocalDateTime startDate, LocalDateTime endDate, double monthlyFee) {
        this.name = name;
        this.service = service;
        this.startDate = startDate;
        this.endDate = endDate;
        this.monthlyFee = monthlyFee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<VirtualServer> getService() {
        return service;
    }

    public void setService(List<VirtualServer> service) {
        this.service = service;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public double getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(double monthlyFee) {
        this.monthlyFee = monthlyFee;
    }
}
