package hhplus.cleanarchitecture.repository.impl;

import hhplus.cleanarchitecture.domain.Registration;
import hhplus.cleanarchitecture.repository.RegistrationRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaRegistrationRepository implements RegistrationRepository {

    private final EntityManager em;
    @Override
    public void save(Registration registration) {
        em.persist(registration);
    }

    @Override
    public List<Registration> findRegistrationsByUserId(Long userId) {
        return em.createQuery("select r from Registration r where r.user.id = :userId", Registration.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Registration> findRegistrationsByLectureId(Long lectureId) {
        return em.createQuery("select r from Registration r where r.lecture.id = :lectureId", Registration.class)
                .setParameter("lectureId", lectureId)
                .getResultList();
    }
}
