package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<Role> getRolesByNames(List<String> roleNames) {

        return new HashSet<>(entityManager.createQuery("FROM Role role WHERE role.name in (:roleNames)")
                .setParameter("roleNames", roleNames)
                .getResultList());
    }
}
