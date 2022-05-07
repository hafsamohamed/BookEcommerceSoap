package gov.iti.jets.persistence.impl;

import gov.iti.jets.persistence.UserDao;
import gov.iti.jets.persistence.entities.User;
import gov.iti.jets.persistence.util.ManagerFactory;
import gov.iti.jets.presentation.dtos.UserDto;
import jakarta.persistence.*;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private final static EntityManagerFactory entityManagerFactory = ManagerFactory.getEntityManagerFactory();
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public boolean checkEmail(String email) {
        List<User> userList = entityManager.createQuery("select e from User e where e.email = ?1")
                .setParameter(1, email)
                .getResultList();
        entityManager.close();
        return userList.size() > 0;
    }

    @Override
    public User addUser(User user) {
        user = null;
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
            entityManager.close();
        } catch (NoResultException nre) {
        }
        entityManager.close();
        return user;
    }

    @Override
    public User login(String email, String password) throws NoResultException {
        User user = null;
        try {
            Query query = entityManager.createQuery("SELECT user FROM User user WHERE user.email=:email");
            query.setParameter("email", email);
            user = (User) query.getSingleResult();
        } catch (NoResultException nre) {
        }
        entityManager.close();
        return user;
    }

    @Override
    public User getUser(int id) {
        User user = null;
        try {
            user = entityManager.find(User.class, id);
        } catch (NoResultException nre) {
        }
        entityManager.close();
        return user;
    }

    @Override
    public List<User> getUsers() {
        TypedQuery<User> query = entityManager.createQuery("select m from User m", User.class);
        List<User> messages = query.getResultList();
        entityManager.close();
        return messages;
    }

    public String findUserUUIDByEmail(String email) {
        return entityManager.createQuery("select u.uuid from User u where u.email = ?1", String.class)
                .setParameter(1, email)
                .getSingleResult();
    }

    @Override
    public User updateUser(UserDto user, int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        String select = "SELECT user FROM User user WHERE user.id=:id";

        Query query = entityManager.createQuery(select);
        query.setParameter("id", id);

        User updateduser = (User) query.getSingleResult();

        updateduser.setBirthDate(user.getBirthDate());
        updateduser.setEmail(user.getEmail());
        updateduser.setPhoneNumber(user.getPhoneNumber());
        updateduser.setUserName(user.getUserName());
        updateduser.setDetailedAddress(user.getDetailedAddress());
        updateduser.setCity(user.getCity());
        updateduser.setCountry(user.getCountry());
        updateduser.setUserType(user.getUserType());
        updateduser.setPassword(user.getPassword());
        updateduser.setWallet(user.getWallet());

        entityManager.persist(updateduser);
        transaction.commit();
        entityManager.close();
        return updateduser;
    }

    @Override
    public User removeUser(int id) {
        User user = null;
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            String select = "SELECT p FROM User p where p.id=:id";
            Query query = entityManager.createQuery(select);
            query.setParameter("id", id);
            List<User> users = query.getResultList();
            if (users.isEmpty()) {
                return null;
            }
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(users.get(0));
            transaction.commit();
            user = users.get(0);
            entityManager.close();
        } catch (NoResultException nre) {
        }
        entityManager.close();
        return user;
    }
}
