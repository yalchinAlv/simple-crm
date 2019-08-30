package model;

import java.time.LocalDate;
import java.util.List;

public class Lead {

    public enum Status {
        INITIATE("Initiate (10%)"),
        DEMO("Demo"),
        QUALIFY("Qualify (20%)"),
        DEVELOP("Develop (30%)"),
        PROPOSE("Propose (40%)"),
        PROVE("Prove (60%)"),
        CLOSE("Close (80%)"),
        SIGNUP("Signup (100%)"),
        REJECTION("Rejection"),
        POSTPONE("Postpone (0%)"),
        NEW("New");

        private String value;
        private Status(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }

    private Customer owner;
    private String name;
    private String status;
    private List<VirtualServer> service;
    private LocalDate startDate;
    private LocalDate endDate;
    private double monthlyFee;

    private List<VirtualServer> demoService;
    private LocalDate demoStartDate;
    private LocalDate demoEndDate;
    private double demoMonthlyFee;

    public Lead() {
    }

    public Lead(String name, List<VirtualServer> service, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.service = service;
        this.startDate = startDate;
        this.endDate = endDate;

        this.monthlyFee = 0;
        for (VirtualServer vs : service) {
            this.monthlyFee += vs.getMonthlyFee();
        }
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(double monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public void recalculateMonthlyFee() {
        this.monthlyFee = 0;
        for (VirtualServer vs : service) {
            this.monthlyFee += vs.getMonthlyFee();
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<VirtualServer> getDemoService() {
        return demoService;
    }

    public void setDemoService(List<VirtualServer> demoService) {
        this.demoService = demoService;
    }

    public LocalDate getDemoStartDate() {
        return demoStartDate;
    }

    public void setDemoStartDate(LocalDate demoStartDate) {
        this.demoStartDate = demoStartDate;
    }

    public LocalDate getDemoEndDate() {
        return demoEndDate;
    }

    public void setDemoEndDate(LocalDate demoEndDate) {
        this.demoEndDate = demoEndDate;
    }

    public double getDemoMonthlyFee() {
        return demoMonthlyFee;
    }

    public void setDemoMonthlyFee(double demoMonthlyFee) {
        this.demoMonthlyFee = demoMonthlyFee;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }
}
