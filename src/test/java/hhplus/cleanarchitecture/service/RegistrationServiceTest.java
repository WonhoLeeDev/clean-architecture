package hhplus.cleanarchitecture.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class RegistrationServiceTest {

    @Autowired RegistrationService registrationService;

    @Test
    void registerTest() {
        registrationService.register(1L, 1L);
    }

    @Test
    void findRegistrationsTest() throws JsonProcessingException {
        registrationService.register(1L, 3L);
        registrationService.findRegistrationsByUserId(1L);
    }

}