package hhplus.cleanarchitecture.repository.impl;

import hhplus.cleanarchitecture.domain.Lecture;
import hhplus.cleanarchitecture.repository.LectureRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaLectureRepository implements LectureRepository {

    private final EntityManager em;

    @Override
    public void save(Lecture lecture) {
        em.persist(lecture);
    }

    @Override
    public List<Lecture> findAllLectures() {
        return em.createQuery("select l from Lecture l", Lecture.class)
                .getResultList();
    }

    @Override
    public Lecture findLectureByLectureId(Long lectureId) {
        Lecture lecture = em.find(Lecture.class, lectureId);
        if (lecture == null) {
            lecture = new Lecture(lectureId, null, null);
        }
        return lecture;
    }
}
