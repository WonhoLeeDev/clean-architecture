package hhplus.cleanarchitecture.service;

import hhplus.cleanarchitecture.controller.dto.LectureDto;
import hhplus.cleanarchitecture.domain.Registration;
import hhplus.cleanarchitecture.repository.LectureRepositoryStub;
import hhplus.cleanarchitecture.repository.RegistrationRepositoryStub;
import hhplus.cleanarchitecture.repository.UserRepositoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RegistrationServiceTest {

    RegistrationService registrationService;

    RegistrationRepositoryStub registrationRepositoryStub;
    UserRepositoryStub userRepositoryStub;
    LectureRepositoryStub lectureRepositoryStub;

    @BeforeEach
    void setUp() {
        registrationRepositoryStub = new RegistrationRepositoryStub();
        userRepositoryStub = new UserRepositoryStub();
        lectureRepositoryStub = new LectureRepositoryStub();

        registrationService = new RegistrationService(
                registrationRepositoryStub,
                userRepositoryStub,
                lectureRepositoryStub
        );
    }

    @DisplayName("수강신청에 성공한다.")
    @Test
    void register_Success() {
        // given
        Long userId = 1L;
        Long lectureId = 10L;

        // when
        registrationService.register(userId, lectureId);
        Registration registration = registrationRepositoryStub.getRegistration();

        // then
        assertThat(userId).isEqualTo(registration.getUser().getId());
        assertThat(lectureId).isEqualTo(registration.getLecture().getId());
    }

    @DisplayName("수강신청인원이 30명이 초과되면 수강신청에 실패한다.")
    @Test
    void savePointHistory_ThrowsException_ifRegistrationFull() {
        // given
        int registrationNumber = LectureDto.MAX_REGISTRATION_NUM;
        registrationRepositoryStub.setRegistrationsSize(registrationNumber);

        // when&then
        assertThat(registrationNumber).isEqualTo(30);
        assertThrows(RuntimeException.class, () -> {
            registrationService.register(1L, 10L);
        });
    }

}