package com.apiJava.api.dao;

import com.apiJava.api.models.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Usuario> getUsers() {
        String query = "FROM Usuario ";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void deleteUser(int id) {
        Usuario usuario = entityManager.find(Usuario.class,id);
        entityManager.remove(usuario);
    }

    @Override
    public void createUser(Usuario usuario) {
        entityManager.merge(usuario);
    }

}
