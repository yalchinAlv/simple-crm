package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Customer {

    private String id;
    private String companyName;
    private String companyLegalName;
    private String sector;
    private String industry;
    private String country;
    private String city;
    private String billingAddress;
    private String legalAddress;
    private String acqChannel;
    private String cpName;
    private String cpEmail;
    private String cpLandNum;
    private String cpCellNum;
    private String cpPhoto;
    private String salesOwner;
    private List<String> legalOwners;
    private List<String> techOwners;
    private List<Lead> leads;

    public Customer() {
        this.id = UUID.randomUUID().toString();
    }

    public Customer(String companyName, String companyLegalName, String sector, String industry, String country, String city, String billingAddress, String legalAddress, String acqChannel, String cpName, String cpEmail, String cpLandNum, String cpCellNum, String cpPhoto, String salesOwner) {
        this.id = UUID.randomUUID().toString();
        this.companyName = companyName;
        this.companyLegalName = companyLegalName;
        this.sector = sector;
        this.industry = industry;
        this.country = country;
        this.city = city;
        this.billingAddress = billingAddress;
        this.legalAddress = legalAddress;
        this.acqChannel = acqChannel;
        this.cpName = cpName;
        this.cpEmail = cpEmail;
        this.cpLandNum = cpLandNum;
        this.cpCellNum = cpCellNum;
        this.cpPhoto = cpPhoto;
        this.salesOwner = salesOwner;
        this.legalOwners = new ArrayList<>();
        this.techOwners = new ArrayList<>();
        this.leads = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLegalName() {
        return companyLegalName;
    }

    public void setCompanyLegalName(String companyLegalName) {
        this.companyLegalName = companyLegalName;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(String legalAddress) {
        this.legalAddress = legalAddress;
    }

    public String getAcqChannel() {
        return acqChannel;
    }

    public void setAcqChannel(String acqChannel) {
        this.acqChannel = acqChannel;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public String getCpEmail() {
        return cpEmail;
    }

    public void setCpEmail(String cpEmail) {
        this.cpEmail = cpEmail;
    }

    public String getCpLandNum() {
        return cpLandNum;
    }

    public void setCpLandNum(String cpLandNum) {
        this.cpLandNum = cpLandNum;
    }

    public String getCpCellNum() {
        return cpCellNum;
    }

    public void setCpCellNum(String cpCellNum) {
        this.cpCellNum = cpCellNum;
    }

    public String getCpPhoto() {
        return cpPhoto;
    }

    public void setCpPhoto(String cpPhoto) {
        this.cpPhoto = cpPhoto;
    }

    public String getSalesOwner() {
        return salesOwner;
    }

    public void setSalesOwner(String salesOwner) {
        this.salesOwner = salesOwner;
    }

    public List<String> getLegalOwners() {
        return legalOwners;
    }

    public void setLegalOwners(List<String> legalOwners) {
        this.legalOwners = legalOwners;
    }

    public List<String> getTechOwners() {
        return techOwners;
    }

    public void setTechOwners(List<String> techOwners) {
        this.techOwners = techOwners;
    }

    public List<Lead> getLeads() {
        return leads;
    }

    public void setLeads(List<Lead> leads) {
        for (Lead lead : leads)
            addLead(lead);
    }

    public void addLead(Lead lead) {
        leads.add(lead);
        lead.setOwner(this);
    }
}
