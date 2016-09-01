package com.exam.service;

import java.util.List;

import com.exam.bo.Contact;

public interface ContactService {

    /**
     * Add or edit the contact.
     *
     * @param contact the contact
     * @param list the list
     * @param id the id
     * @return the list
     */
    List<Contact> addOrEditContact(Contact contact, List<Contact> list, int id);

    /**
     * Delete the contact.
     *
     * @param list the list
     * @param id the id
     * @return the list
     */
    List<Contact> deleteContact(List<Contact> list, int id);

}
