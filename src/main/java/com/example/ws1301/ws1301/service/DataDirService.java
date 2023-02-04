package com.example.ws1301.ws1301.service;

import java.util.List;
import java.util.Map;

import com.example.ws1301.ws1301.model.Contact;

public interface DataDirService {
    public Contact saveContact(Contact contact);
    public Contact searchContactById(String id);
    public Map<String, Contact> fetchAllcontacts();
    
    
}
