package edu.pkch.serialize;

public class Employee extends Person {
    private static final long serialVersionUID = 2L;

    private final String company;
    private final String team;

    public Employee(String company, String team) {
        this.company = company;
        this.team = team;
    }

    public String getCompany() {
        return company;
    }

    public String getTeam() {
        return team;
    }
}
