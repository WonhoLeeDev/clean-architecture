package hhplus.cleanarchitecture.repository;

import hhplus.cleanarchitecture.domain.Lecture;
import hhplus.cleanarchitecture.domain.Registration;

import java.util.List;

public interface LectureRepository {

    public void save(Lecture lecture);

    public List<Lecture> findAllLectures();

    public Lecture findLectureByLectureId(Long lectureId);
}
