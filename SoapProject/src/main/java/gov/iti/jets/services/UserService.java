package gov.iti.jets.services;
import gov.iti.jets.persistence.entities.User;
import gov.iti.jets.presentation.dtos.UserDto;
import jakarta.jws.WebService;
import jakarta.persistence.NoResultException;

import java.util.List;

@WebService
public interface UserService {
    boolean checkEmail(String email);
    UserDto addUser (UserDto user);
    UserDto getUser (int id);
    List<User> getUsers ();
    UserDto login(String email, String password) throws NoResultException;
    UserDto updateUser(UserDto user, int id);
    UserDto removeUser(int id);

}
