package ru.kata.spring.boot_security.demo.Dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserByUsername(String username) {
        TypedQuery<User> query = entityManager
                .createQuery("FROM User user LEFT JOIN FETCH user.roles WHERE user.username =:username", User.class)
                .setParameter("username", username);
        return query.getResultList().stream().findFirst().orElseThrow();
    }

    @Override
    public boolean existsByUsername(String username) {
        TypedQuery<User> query = entityManager
                .createQuery("FROM User WHERE username = :username", User.class)
                .setParameter("username", username);
        return !query.getResultList().isEmpty();
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getUserById(Long id) {
        User user = entityManager.find(User.class, id);
        if (user == null) {
            throw new EntityNotFoundException("User with this ID not found");
        }
        return user;
    }

    @Override
    public void deleteUsers(Long id) {
        User user = entityManager.find(User.class, id);

        if (user == null) {
            throw new EntityNotFoundException("It is not possible to delete a user with this ID");
        }
        entityManager.remove(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void saveUser(User user) {
        if (!existsByUsername(user.getUsername())) {
            entityManager.persist(user);
        }
    }

}
