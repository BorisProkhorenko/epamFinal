package com.epam.web.entity;

import com.epam.web.enums.Gender;
import com.epam.web.enums.Role;

public class Applicant extends User {

    public final static String APPLICANT = "applicant";

    private final static Role ROLE = Role.APPLICANT;
    private final String name;
    private final Gender gender;
    private final Integer age;
    private final String photo;
    private final String contacts;
    private final String education;
    private final String experience;
    private final String skills;

    public Applicant(Long id, String username, String password, boolean isBlocked, String name, Gender gender,
                     Integer age, String photo, String contacts, String education, String experience, String skills) {
        super(id, username, password, ROLE, isBlocked);
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.photo = photo;
        this.contacts = contacts;
        this.education = education;
        this.experience = experience;
        this.skills = skills;
    }

    public Applicant(User user, String name, Gender gender, Integer age, String photo, String contacts,
                     String education, String experience, String skills) {
        super(user.getId(),
                user.getUsername(),
                user.getPassword(),
                ROLE,
                user.getIsBlocked());
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.photo = photo;
        this.contacts = contacts;
        this.education = education;
        this.experience = experience;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public String getPhoto() {
        return photo;
    }

    public String getContacts() {
        return contacts;
    }

    public String getEducation() {
        return education;
    }

    public String getExperience() {
        return experience;
    }

    public String getSkills() {
        return skills;
    }


}
