package hhplus.cleanarchitecture.repository;

import hhplus.cleanarchitecture.domain.Registration;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaRegistrationRepository implements RegistrationRepository{

    private final EntityManager em;
    @Override
    public void save(Registration registration) {
        em.persist(registration);
    }

    @Override
    public List<Registration> findAllRegistrationsByUserId(Long userId) {
        return em.createQuery("select r from Registration r where r.user = :user", Registration.class)
                .setParameter("user", userId)
                .getResultList();
    }
}
