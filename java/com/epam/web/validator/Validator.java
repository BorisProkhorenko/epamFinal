package com.epam.web.validator;

import com.epam.web.entity.Identifiable;

/**
 * This interface represents that it's implementations will be used for validating {@link Identifiable}
 * before it will be approved for DAO layer
 *
 * @param <T> must be an implementation of {@link Identifiable} interface
 * @author Boris Prokhorenko
 * @see Identifiable
 */
public interface Validator<T extends Identifiable> {

    /**
     * Method validates {@link Identifiable} for compliance with Data Base column valid values
     *
     * @param item {@link Identifiable}
     * @return true if object is valid, otherwise - false
     */
    boolean validate(T item);
}
