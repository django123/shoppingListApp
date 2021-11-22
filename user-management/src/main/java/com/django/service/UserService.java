package com.django.service;

import com.django.domain.UserApp;
import com.django.exceptions.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface UserService {

    UserApp register(String firstName, String lastName, String username, String email, String password) throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException;

    List<UserApp> getEmployees();

    UserApp findEmployeeByUsername(String username);

    UserApp findEmployeeByEmail(String email);

    UserApp addNewEmployee(String firstName, String lastName, String username, String email, String role, boolean isNonLocked, boolean isActive, String password,MultipartFile profileImage) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException;

    UserApp updateEmployee(String currentUsername, String newFirstName, String newLastName, String newUsername, String newEmail, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException;

    void deleteEmployee(String username) throws IOException;

    void resetPassword(String email) throws MessagingException, EmailNotFoundException;

    UserApp updateProfileImage(String username, MultipartFile profileImage) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException;
}
