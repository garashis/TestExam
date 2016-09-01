package com.exam.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.exam.bo.Contact;
import com.exam.service.ContactService;

@Service("com.exam.service.impl.ContactServiceImpl")
public class ContactServiceImpl implements ContactService {
    private static final Logger logger = Logger.getLogger(ContactServiceImpl.class);

    @Override
    public List<Contact> addOrEditContact(Contact contact, List<Contact> list, int id) {
        logger.info("addOrEditContact service is executed");
        //        List<Contact> list = getContactList(authentication);
        if (null == list) {
            list = new ArrayList<Contact>();
        }

        if (contact.getId() == 0) {
            //New Contact Request
            contact.setId(id);
            list.add(contact);
            logger.info("Contact added with id : " + id);
        } else {
            //Update Contact Request
            for (Contact con : list) {
                if (con.getId() == contact.getId()) {
                    con.setCity(contact.getCity());
                    con.setDob(contact.getDob());
                    con.setFirstName(contact.getFirstName());
                    con.setLastName(contact.getLastName());
                    con.setSsn(contact.getSsn());
                    con.setState(contact.getState());
                    con.setStreet(contact.getStreet());
                    con.setZip(contact.getZip());
                    break;
                }
            }
        }
        return list;
    }

    @Override
    public List<Contact> deleteContact(List<Contact> list, int id) {
        logger.info("deleteContact service is executed for contact id : " + id);
        Contact contact = new Contact();
        contact.setId(id);
        list.remove(contact);
        return list;
    }
}
