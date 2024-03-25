package hhplus.cleanarchitecture.service;

import hhplus.cleanarchitecture.controller.RegistrationDto;
import hhplus.cleanarchitecture.domain.Lecture;
import hhplus.cleanarchitecture.domain.Registration;
import hhplus.cleanarchitecture.domain.User;
import hhplus.cleanarchitecture.repository.LectureRepository;
import hhplus.cleanarchitecture.repository.RegistrationRepository;
import hhplus.cleanarchitecture.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;

    @Transactional
    public RegistrationDto register(Long userId, Long lectureId) {
        User user = userRepository.findUserByUserId(userId);
        Lecture lecture = lectureRepository.findLectureByLectureId(lectureId);

        Registration registration = Registration.createRegistration(user, lecture);
        registrationRepository.save(registration);
        return Registration.toDto(registration);
    }

    public List<RegistrationDto> findRegistrationsByUserId(Long userId) {
        return registrationRepository.findRegistrationsByUserId(userId)
                .stream()
                .map(Registration::toDto)
                .collect(Collectors.toList());
    }
}
