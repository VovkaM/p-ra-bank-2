package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousPhoneTransferDto;
import com.bank.antifraud.service.SuspiciousPhoneTransferService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class SuspiciousPhoneTransferControllerTest {
    @Mock
    private SuspiciousPhoneTransferService service;

    @InjectMocks
    private SuspiciousPhoneTransferController controller;

    private final long id = 1L;

    @Test
    @DisplayName("Чтение SuspiciousAccountTransferDto по id, позитивный сценарий")
    void readByIdPositiveTest() {

        final SuspiciousPhoneTransferDto suspiciousPhoneTransferDto = new SuspiciousPhoneTransferDto();
        when(service.findById(id)).thenReturn(suspiciousPhoneTransferDto);

        final ResponseEntity<SuspiciousPhoneTransferDto> responseEntity = controller.read(id);
        assertAll(
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode()),
                () -> assertEquals(suspiciousPhoneTransferDto, responseEntity.getBody())
        );

    }

    @Test
    @DisplayName("Создание SuspiciousPhoneTransferDto, позитивный сценарий")
    void createSuspiciousPhoneTransferPositiveTest() {
        final SuspiciousPhoneTransferDto suspiciousPhoneTransferDto = new SuspiciousPhoneTransferDto();
        when(service.save(suspiciousPhoneTransferDto)).thenReturn(suspiciousPhoneTransferDto);

        final ResponseEntity<SuspiciousPhoneTransferDto> responseEntity = controller.create(suspiciousPhoneTransferDto);
        assertAll(
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode()),
                () -> assertEquals(suspiciousPhoneTransferDto, responseEntity.getBody())
        );
    }

    @Test
    @DisplayName("Редактирование SuspiciousPhoneTransfer по id, позитивный сценарий")
    void updateSuspiciousPhoneTransferByIdPositiveTest() {

        final SuspiciousPhoneTransferDto suspiciousPhoneTransferDto = new SuspiciousPhoneTransferDto();
        when(service.update(id, suspiciousPhoneTransferDto)).thenReturn(suspiciousPhoneTransferDto);

        final ResponseEntity<SuspiciousPhoneTransferDto> responseEntity =
                controller.update(suspiciousPhoneTransferDto, id);
        assertAll(
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode()),
                () -> assertEquals(suspiciousPhoneTransferDto, responseEntity.getBody())
        );
    }

    @Test
    @DisplayName("Чтение SuspiciousPhoneTransferDto по List id, позитивный сценарий")
    void readAllByIdListPositiveTest() {
        final List<Long> idsList = Arrays.asList(1L, 2L, 3L);
        final List<SuspiciousPhoneTransferDto> suspiciousPhoneTransferDtoList =
                Arrays.asList(new SuspiciousPhoneTransferDto(),
                        new SuspiciousPhoneTransferDto(),
                        new SuspiciousPhoneTransferDto());
        when(service.findAllById(idsList)).thenReturn(suspiciousPhoneTransferDtoList);

        final ResponseEntity<List<SuspiciousPhoneTransferDto>> listResponseEntity = controller.readAll(idsList);

        assertAll(
                () -> assertEquals(HttpStatus.OK, listResponseEntity.getStatusCode()),
                () -> assertEquals(suspiciousPhoneTransferDtoList, listResponseEntity.getBody())
        );

    }
}
