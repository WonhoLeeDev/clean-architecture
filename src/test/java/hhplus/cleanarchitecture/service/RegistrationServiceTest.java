package hhplus.cleanarchitecture.service;

import hhplus.cleanarchitecture.controller.dto.LectureDto;
import hhplus.cleanarchitecture.controller.dto.RegistrationDto;
import hhplus.cleanarchitecture.domain.Registration;
import hhplus.cleanarchitecture.domain.RegistrationStatus;
import hhplus.cleanarchitecture.repository.LectureRepositoryStub;
import hhplus.cleanarchitecture.repository.RegistrationRepositoryStub;
import hhplus.cleanarchitecture.repository.UserRepositoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

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
    void register_ThrowsException_ifRegistrationFull() {
        // given
        int registrationNumber = LectureDto.MAX_REGISTRATION_NUM;
        registrationRepositoryStub.setRegistrationsSize(registrationNumber);

        // when&then
        assertThat(registrationNumber).isEqualTo(30);
        assertThrows(RuntimeException.class, () -> {
            registrationService.register(1L, 10L);
        });
    }

    @DisplayName("userId로 해당 강의가 이미 등록되었으면, 등록에 실패한다.")
    @Test
    void register_ThrowsException_ifAlreadyRegistered() {
        // given
        Long userId = 1L;
        Long lectureId = 10L;
        registrationService.register(userId, lectureId);

        // when&then
        assertThrows(IllegalStateException.class, () -> {
            registrationService.register(userId, lectureId);
        });
    }

    @DisplayName("userId로 등록된 수강목록 조회에 성공한다.")
    @Test
    void findRegistrationsByUserId_Success() {
        // given
        Long userId = 1L;
        registrationService.register(userId, 10L);
        registrationService.register(userId, 20L);

        // when
        List<RegistrationDto> expectation = registrationService.findRegistrationsByUserId(userId);
        List<Registration> result = registrationRepositoryStub.findRegistrationsByUserId(userId);

        // then
        assertThat(expectation.size()).isEqualTo(result.size());
        for (Registration registration : result) {
            if (registration.getLecture().getId() != 10L && registration.getLecture().getId() != 20L) {
                fail();
            }
        }
    }

    @DisplayName("수강신청 상태를 조회한다.")
    @Test
    void getRegistrationStatus_Success() {
        // given
        registrationService.register(1L, 10L);

        // when
        RegistrationStatus registered = registrationService.getRegistrationStatus(1L, 10L);
        RegistrationStatus unregistered = registrationService.getRegistrationStatus(1L, 20L);

        // then
        assertThat(registered).isEqualTo(RegistrationStatus.REGISTERED);
        assertThat(unregistered).isEqualTo(RegistrationStatus.UNREGISTERED);
    }


}