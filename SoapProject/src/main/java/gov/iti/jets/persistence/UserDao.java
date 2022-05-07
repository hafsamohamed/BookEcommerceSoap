package gov.iti.jets.persistence;

import gov.iti.jets.persistence.entities.User;
import gov.iti.jets.presentation.dtos.UserDto;
import jakarta.persistence.NoResultException;

import java.util.List;

public interface UserDao {
    boolean checkEmail (String email);
    User addUser(User user);
    User login(String email, String password) throws NoResultException;
    User getUser(int id);
    List<User> getUsers ();
    User updateUser(UserDto user, int id);
    User removeUser(int id);
    String findUserUUIDByEmail(String email);
}
