package com.epam.web.entity;

/**
 * Marks entity as Identifiable. That means that marked entity has id - field, which corresponds to primary key
 * in Data Base table
 */
public interface Identifiable {

    /**
     * @return value of id - field, which should have all Identifiable entities
     */
    Long getId();
}
