package com.apiJava.api.dao;

import com.apiJava.api.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Repository
@Transactional

public class CredencialDaoImp implements CredencialDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Usuario userByCredencials(Usuario usuario) {
        String query = "From Usuario WHERE email = :email";
        List<Usuario> list = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();

        if(list.isEmpty()) {
            return null;
        }

        String passwordHashed = list.get(0).getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if(argon2.verify(passwordHashed, usuario.getPassword())) {
            return list.get(0);
        }
        return null;
    }
}
