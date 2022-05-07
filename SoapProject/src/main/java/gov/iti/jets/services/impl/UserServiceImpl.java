package gov.iti.jets.services.impl;

import gov.iti.jets.persistence.UserDao;
import gov.iti.jets.persistence.entities.User;
import gov.iti.jets.persistence.impl.UserDaoImpl;
import gov.iti.jets.persistence.util.mappers.UserMapper;
import gov.iti.jets.presentation.dtos.UserDto;
import gov.iti.jets.services.UserService;
import jakarta.jws.WebService;
import jakarta.persistence.NoResultException;

import java.util.List;
import java.util.UUID;

@WebService(endpointInterface = "gov.iti.jets.services.UserService")
public class UserServiceImpl implements UserService {

    @Override
    public boolean checkEmail(String email) {
        UserDao userDao = new UserDaoImpl();
        return userDao.checkEmail(email);
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        UserDto dto = null;
        try {
            UserDao userDao = new UserDaoImpl();

            UserDto user2 = UserMapper.INSTANCE.userToDto(userDao.login(userDto.getEmail(), userDto.getPassword()));
            if (user2 != null) {
                System.out.println("there is user hava this email and password please login");
                return null;
            } else {
                User user = UserMapper.INSTANCE.userDtoToEntity(userDto);
                user.setUuid(UUID.randomUUID().toString());
                System.out.println("Successfully Added");
                User user3 = userDao.addUser(user);
                dto = UserMapper.INSTANCE.userToDto(user3);
                return dto;
            }
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public UserDto getUser(int id) {
        UserDto dto = null;
        try {
            UserDao userDao = new UserDaoImpl();
            User user = userDao.getUser(id);
            if (user == null) {
                System.out.println("You need to register first");
                return null;
            }
            User user2 = userDao.getUser(id);
            dto = UserMapper.INSTANCE.userToDto(user2);
            return dto;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public List<User> getUsers() {
        UserDao userDao = new UserDaoImpl();
        return userDao.getUsers();
    }

    @Override
    public UserDto login(String email, String password) throws NoResultException {
        UserDto dto = null;
        try {
            UserDao userDao = new UserDaoImpl();
            User user = userDao.login(email, password);
            dto = UserMapper.INSTANCE.userToDto(user);
            return dto;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UserDto updateUser(UserDto userDto, int id) {
        UserDto dto = null;
        try {
            UserDao userDao = new UserDaoImpl();
            UserDto user2 = UserMapper.INSTANCE.userToDto(userDao.login(userDto.getEmail(), userDto.getPassword()));
            if (user2 == null) {
                System.out.println("There is NO user hava this email and password");
                return null;
            }
            System.out.println("Successfully Updated");
            dto = UserMapper.INSTANCE.userToDto(userDao.updateUser(userDto, user2.getId()));
            return dto;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public UserDto removeUser(int id) {
        UserDto dto = null;
        try {
            UserDao userDao = new UserDaoImpl();
            UserDto user = UserMapper.INSTANCE.userToDto(userDao.getUser(id));
            if (user == null) {
                System.out.println("There is no user to delete it ");
                return null;
            }
            System.out.println("Successfully Deteted");
            dto = UserMapper.INSTANCE.userToDto(userDao.removeUser(id));
            return dto;
        } catch (Exception e) {
            return null;
        }

    }

    public String getUserUUIDbyEmail(String email) {
        UserDao userDao = new UserDaoImpl();
        return userDao.findUserUUIDByEmail(email);
    }
}
