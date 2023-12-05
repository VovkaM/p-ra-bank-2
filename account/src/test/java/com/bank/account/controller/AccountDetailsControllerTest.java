package com.bank.account.controller;

import com.bank.account.dto.AccountDetailsDto;
import com.bank.account.service.AccountDetailsService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountDetailsControllerTest {

    @Mock
    private AccountDetailsService service;

    @InjectMocks
    private AccountDetailsController controller;

    @Test
    @DisplayName("Чтение AccountDetailsDto по id, позитивный сценарий")
    void readByIdPositiveTest() {
        final long id = 1L;
        final AccountDetailsDto accountDetailsDto = new AccountDetailsDto();
        when(service.findById(id)).thenReturn(accountDetailsDto);

        final AccountDetailsDto resDto = controller.read(id);

        assertEquals(resDto, accountDetailsDto);
    }

    @Test
    @DisplayName("Создание AccountDetailsDto, позитивный сценарий")
    void createAccountDetailsPositiveTest() {
        final AccountDetailsDto accountDetailsDto = new AccountDetailsDto();
        when(service.save(accountDetailsDto)).thenReturn(accountDetailsDto);

        final ResponseEntity<AccountDetailsDto> responseEntity = controller.create(accountDetailsDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(accountDetailsDto, responseEntity.getBody());
    }

    @Test
    @DisplayName("Редактирование AccountDetails по id, позитивный сценарий")
    void updateAccountDetailsByIdPositiveTest() {
        final long id = 1L;
        final AccountDetailsDto accountDetailsDto = new AccountDetailsDto();
        when(service.update(id, accountDetailsDto)).thenReturn(accountDetailsDto);

        final ResponseEntity<AccountDetailsDto> responseEntity = controller.update(id, accountDetailsDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(accountDetailsDto, responseEntity.getBody());
    }

    @Test
    @DisplayName("Чтение AccountDetailsDto по List id, позитивный сценарий")
    void readAllByIdListPositiveTest() {
        final List<Long> idList = Arrays.asList(1L, 2L, 3L);
        final List<AccountDetailsDto> accountDetailsDtoList =
                Arrays.asList(new AccountDetailsDto(), new AccountDetailsDto(), new AccountDetailsDto());
        when(service.findAllById(idList)).thenReturn(accountDetailsDtoList);

        final ResponseEntity<List<AccountDetailsDto>> listResponseEntity = controller.readAll(idList);

        assertEquals(HttpStatus.OK, listResponseEntity.getStatusCode());
        assertEquals(accountDetailsDtoList, listResponseEntity.getBody());
    }
}