package hhplus.cleanarchitecture.repository;

import hhplus.cleanarchitecture.domain.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaUserRepository implements UserRepository {

    private final EntityManager em;

    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public User findUserByUserId(Long userId) {
        User user = em.find(User.class, userId);
        if (user == null) {
            user = new User(userId, null, null);
        }
        return user;
    }
}
