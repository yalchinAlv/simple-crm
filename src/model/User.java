package model;

import java.util.UUID;

public class User {

    public enum Role {
        SUPER_ADMIN("Super Admin"),
        LEGAL_TEAM("Legal Team"),
        TECHNICAL_TEAM("Technical Team"),
        SALES("Sales Team"),
        TECHNICAL_SUPPORT_TEAM("Technical Support Team"),
        FINANCE("Finance"),
        MARKETING("Marketing"),
        CUSTOMER("Customer");

        private String value;
        private Role(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }

    private String id;
    private String password;
    private String fullName;
    private String role;

    public User() {
        this.id = UUID.randomUUID().toString();
    }

    public User(String password, String fullName, String role) {
        this.id = UUID.randomUUID().toString();
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
