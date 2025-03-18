package com.example.workshop_addressbook.service;

import com.example.workshop_addressbook.model.AddressBookEntry;
import com.example.workshop_addressbook.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookService {
    @Autowired
    private AddressBookRepository addressBookRepository;

    public List<AddressBookEntry> getAllContacts() {
        return addressBookRepository.findAll();
    }
}