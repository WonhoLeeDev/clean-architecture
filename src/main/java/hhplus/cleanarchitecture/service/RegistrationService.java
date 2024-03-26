package hhplus.cleanarchitecture.service;

import hhplus.cleanarchitecture.controller.dto.LectureDto;
import hhplus.cleanarchitecture.controller.dto.RegistrationDto;
import hhplus.cleanarchitecture.domain.Lecture;
import hhplus.cleanarchitecture.domain.Registration;
import hhplus.cleanarchitecture.domain.RegistrationStatus;
import hhplus.cleanarchitecture.domain.User;
import hhplus.cleanarchitecture.repository.LectureRepository;
import hhplus.cleanarchitecture.repository.RegistrationRepository;
import hhplus.cleanarchitecture.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;

    private final ConcurrentHashMap<Long, Object> lockMap = new ConcurrentHashMap<>();

    @Transactional
    public RegistrationDto register(Long userId, Long lectureId) {
        return (RegistrationDto) lockMap.compute(userId, (k, v) -> {
            if (registrationRepository.findRegistrationsByLectureId(lectureId).size() == LectureDto.MAX_REGISTRATION_NUM) {
                throw new RuntimeException("수강인원 초과");
            }

            User user = userRepository.findUserByUserId(userId);
            Lecture lecture = lectureRepository.findLectureByLectureId(lectureId);

            Registration registration = Registration.createRegistration(user, lecture);
            registrationRepository.save(registration);
            return Registration.toDto(registration);
        });
    }

    public List<RegistrationDto> findRegistrationsByUserId(Long userId) {
        return registrationRepository.findRegistrationsByUserId(userId)
                .stream()
                .map(Registration::toDto)
                .collect(Collectors.toList());
    }

    public RegistrationStatus isLectureRegistered(Long userId, Long lectureId) {
        return registrationRepository.findRegistrationsByUserId(userId)
                .stream()
                .anyMatch(registration -> registration.getLecture().getId().equals(lectureId))
                ? RegistrationStatus.REGISTERED : RegistrationStatus.UNREGISTERED;
    }
}
