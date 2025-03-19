package com.example.workshop_addressbook.service;

import com.example.workshop_addressbook.dto.AddressBookDTO;
import com.example.workshop_addressbook.model.AddressBookEntry;

import java.util.List;
import java.util.Optional;

public interface IAddressBookService {
    List<AddressBookDTO> getAllContacts();
    Optional<AddressBookDTO> getContactById(Long id);
    AddressBookDTO addContact(AddressBookDTO contactDTO);
    AddressBookDTO updateContact(Long id, AddressBookDTO contactDTO);
    void deleteContact(Long id);
}