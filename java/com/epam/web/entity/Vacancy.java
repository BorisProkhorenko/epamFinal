package com.epam.web.entity;

public class Vacancy implements Identifiable {

    public final static String TABLE_NAME = "vacancy";
    private final Long id;
    private final String name;
    private final String salary;
    private final String responsibility;
    private final String description;
    private final String requirements;


    public Vacancy(Long id, String name, String salary, String responsibility, String description, String requirements) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.responsibility = responsibility;
        this.description = description;
        this.requirements = requirements;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSalary() {
        return salary;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public String getDescription() {
        return description;
    }

    public String getRequirements() {
        return requirements;
    }


}
