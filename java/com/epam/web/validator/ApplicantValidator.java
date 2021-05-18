package com.epam.web.validator;

import com.epam.web.entity.Applicant;

public class ApplicantValidator implements Validator<Applicant> {

    private final static String NAME_PATTERN = "[a-zA-Z0-9&#; ]{1,1600}";
    private final static int MIN_AGE = 20;
    private final static int MAX_AGE = 99;
    private final static int MAX_PHOTO = 1800;
    private final static int MAX = 7000;

    @Override
    public boolean validate(Applicant item) {
        String name = item.getName();
        int age = item.getAge();
        String contacts = item.getContacts();
        int contactsLength = contacts.length();
        String photo = item.getPhoto();
        int photoLength = photo.length();
        String education = item.getEducation();
        int educationLength = education.length();
        String experience = item.getExperience();
        int experienceLength = experience.length();
        String skills = item.getSkills();
        int skillsLength = skills.length();

        return name.matches(NAME_PATTERN) && age >= MIN_AGE && age <= MAX_AGE && photoLength <= MAX_PHOTO
                && contactsLength <= MAX && educationLength <= MAX
                && experienceLength <= MAX && skillsLength <= MAX;
    }
}
