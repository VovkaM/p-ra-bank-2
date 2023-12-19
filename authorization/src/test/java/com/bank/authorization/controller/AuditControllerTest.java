package com.bank.authorization.controller;

import com.bank.authorization.dto.AuditDto;
import com.bank.authorization.service.AuditService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.webjars.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuditControllerTest {

    @Mock
    private AuditService service;

    @InjectMocks
    private AuditController controller;

    @Test
    @DisplayName("Чтение AuditDto по id, позитивный сценарий")
    void readAuditDtoByIdPositiveTest() {
        final long id = 1L;
        final AuditDto auditDto = new AuditDto();
        when(service.findById(id)).thenReturn(auditDto);

        final AuditDto resDto = controller.read(id);

        assertEquals(auditDto, resDto);
    }

    @Test
    @DisplayName("Чтение AuditDto по id, негативный сценарий")
    void readAuditDtoByIdNegativeTest() {
        final long id = 1L;
        final AuditDto auditDto = new AuditDto();
        when(service.findById(id)).thenThrow(new NotFoundException("AuditDto не найден"));

        assertThrows(NotFoundException.class, () -> controller.read(id));
    }
}
