package com.bank.account.controller;

import com.bank.account.dto.AuditDto;
import com.bank.account.service.AccountAuditService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountAuditControllerTest {

    @Mock
    private AccountAuditService service;

    @InjectMocks
    private AccountAuditController controller;

    @Test
    @DisplayName("Чтение AccountDetailsDto по id, позитивный сценарий")
    void readByIdPositiveTest() {
        final long id = 1L;
        final AuditDto auditDto = new AuditDto();
        when(service.findById(id)).thenReturn(auditDto);

        final AuditDto resDto = controller.read(id);

        assertEquals(auditDto, resDto);
    }
}




