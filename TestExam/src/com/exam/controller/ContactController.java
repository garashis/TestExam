package com.exam.controller;

import static com.exam.constants.Constants.CONTACT_VIEW;
import static com.exam.constants.Constants.DATA;
import static com.exam.constants.Constants.ID;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.exam.bo.Contact;
import com.exam.service.ContactService;

@Controller
public class ContactController {

    private static final Logger logger = Logger.getLogger(ContactController.class);

    private String CONTACT_LIST = "contacts";

    @Autowired
    ServletContext context;

    @Autowired
    ContactService service;

    /**
     * Show Home page.
     *
     * @param model the model
     * @param authentication the authentication
     * @return the model and view
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView homePage(ModelAndView model, Authentication authentication) {
        logger.debug("HomePage controller is executed for user : " + authentication.getName());
        model.addObject(CONTACT_LIST, getContactList(authentication));
        model.setViewName("home");
        return model;
    }

    /**
     * Show New/Edit Contact page.
     *
     * @return the model and view
     */
    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public ModelAndView contact() {
        logger.debug("Contact controller is executed");
        return new ModelAndView(CONTACT_VIEW, "contactForm", new Contact());
    }

    /**
     * Adds the contact.
     *
     * @param contact the contact
     * @param result the result
     * @param model the model
     * @param authentication the authentication
     * @return the model and view
     */
    @RequestMapping(value = "/addContact", method = RequestMethod.POST)
    public ModelAndView addContact(@Valid @ModelAttribute("contactForm") Contact contact, BindingResult result,
            ModelAndView model, Authentication authentication) {
        logger.debug("Add Contact controller is executed");
        if (result.hasErrors()) {
            model.setViewName(CONTACT_VIEW);//return "contact";
            return model;
        }

        List<Contact> list = service.addOrEditContact(contact, getContactList(authentication), generateId());

        getData(authentication).put(authentication.getName(), list);

        model.addObject(CONTACT_LIST, list);
        model.setViewName("redirect:home");
        return model;
    }

    /**
     * Delete contact.
     *
     * @param id the id
     * @param model the model
     * @param authentication the authentication
     * @return the model and view
     */
    @RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
    public ModelAndView deleteContact(@RequestParam("id") Integer id, ModelAndView model,
            Authentication authentication) {
        logger.debug("Delete Contact controller is executed");
        List<Contact> list = service.deleteContact(getData(authentication).get(authentication.getName()), id);

        model.addObject(CONTACT_LIST, list);
        model.setViewName("redirect:home");
        return model;
    }

    /**
     * Update contact.
     *
     * @param id the id
     * @param model the model
     * @param authentication the authentication
     * @return the model and view
     */
    @RequestMapping(value = "/updateContact", method = RequestMethod.GET)
    public ModelAndView updateContact(@RequestParam("id") Integer id, ModelAndView model,
            Authentication authentication) {
        logger.debug("Update Contact controller is executed");
        List<Contact> list = getData(authentication).get(authentication.getName());
        for (Contact contact : list) {
            if (contact.getId() == id) {
                model.addObject("contactForm", contact);
                break;
            }
        }

        model.setViewName(CONTACT_VIEW);
        return model;
    }

    /**
     * Generate id.
     *
     * @return the int
     */
    private int generateId() {
        Integer id = (Integer) context.getAttribute(ID);
        if (null == id) {
            context.setAttribute(ID, new Integer(1));
            return 1;
        } else {
            Integer newId = new Integer(id + 1);
            context.setAttribute(ID, newId);
            return newId;
        }
    }

    /**
     * Gets the data.
     *
     * @param authentication the authentication
     * @return the data
     */
    private Map<String, List<Contact>> getData(Authentication authentication) {
        @SuppressWarnings("unchecked")
        Map<String, List<Contact>> data = (Map<String, List<Contact>>) context.getAttribute(DATA);
        if (null == data) {
            data = Collections.synchronizedMap(new HashMap<String, List<Contact>>());
            context.setAttribute(DATA, data);
        }

        return data;
    }

    /**
     * Gets the contact list.
     *
     * @param authentication the authentication
     * @return the contact list
     */
    private List<Contact> getContactList(Authentication authentication) {

        List<Contact> list = getData(authentication).get(authentication.getName());
        return list;
    }
}
