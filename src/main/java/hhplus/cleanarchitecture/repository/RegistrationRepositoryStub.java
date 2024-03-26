package hhplus.cleanarchitecture.repository;

import hhplus.cleanarchitecture.domain.Registration;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RegistrationRepositoryStub implements RegistrationRepository {
    private Registration registration;
    private List<Registration> registrations = new ArrayList<>();

    @Override
    public void save(Registration registration) {
        this.registration = registration;
        this.registrations.add(registration);
    }

    @Override
    public List<Registration> findRegistrationsByUserId(Long userId) {
        return this.registrations;
    }

    @Override
    public List<Registration> findRegistrationsByLectureId(Long lectureId) {
        return this.registrations;
    }

    public void setRegistrationsSize(int size) {
        for (int i = 0; i < size; i++) {
            Registration registration = Registration.createRegistration(null, null);
            this.registrations.add(registration);
        }
    }

}
