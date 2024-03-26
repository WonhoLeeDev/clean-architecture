package hhplus.cleanarchitecture.repository;

import hhplus.cleanarchitecture.domain.Registration;

import java.util.List;

public interface RegistrationRepository {

    public void save(Registration registration);

    public List<Registration> findRegistrationsByUserId(Long userId);

    public List<Registration> findRegistrationsByLectureId(Long lectureId);
}
