package hhplus.cleanarchitecture.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hhplus.cleanarchitecture.controller.RegistrationDto;
import hhplus.cleanarchitecture.domain.Registration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        List<RegistrationDto> registrations = registrationService.findRegistrationsByUserId(1L);
        for (RegistrationDto registration : registrations) {
            System.out.println("registration: " + registration);
        }
    }

}