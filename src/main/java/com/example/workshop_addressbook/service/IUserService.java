package com.example.workshop_addressbook.service;

import com.example.workshop_addressbook.dto.UserDTO;

public interface IUserService {
    String registerUser(UserDTO userDTO);
    String loginUser(String email, String password);
}