package com.exam.util;

import static com.exam.constants.Constants.DATA;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.exam.bo.Contact;

@Component
public class DataUtil {
    @Autowired
    static ServletContext context;

    public static List<Contact> getContactList(Authentication authentication) {
        Map<String, List<Contact>> data = (Map<String, List<Contact>>) context.getAttribute(DATA);
        if (null == data) {
            data = Collections.synchronizedMap(new HashMap<String, List<Contact>>());
            context.setAttribute(DATA, data);
        }

        List<Contact> list = data.get(authentication.getName());
        return list;
    }

}
