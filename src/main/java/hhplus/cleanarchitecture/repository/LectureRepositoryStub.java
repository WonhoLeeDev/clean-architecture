package hhplus.cleanarchitecture.repository;

import hhplus.cleanarchitecture.domain.Lecture;

import java.util.List;

public class LectureRepositoryStub implements LectureRepository {
    @Override
    public void save(Lecture lecture) {

    }

    @Override
    public List<Lecture> findAllLectures() {
        return null;
    }

    @Override
    public Lecture findLectureByLectureId(Long lectureId) {
        return new Lecture(lectureId, null, null);
    }
}
