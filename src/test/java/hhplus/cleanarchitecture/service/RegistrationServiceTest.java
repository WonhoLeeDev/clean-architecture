package hhplus.cleanarchitecture.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RegistrationServiceTest {

    @Autowired RegistrationService registrationService;

    @Test
    void registerTest() {
        registrationService.register(1L, 1L);
    }

}