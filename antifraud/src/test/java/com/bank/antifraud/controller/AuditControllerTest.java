package com.bank.antifraud.controller;

import com.bank.antifraud.dto.AuditDto;
import com.bank.antifraud.service.AuditService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.webjars.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuditControllerTest {
    @Mock
    private AuditService service;
    @InjectMocks
    private AuditController controller;
    private final long id = 1L;

    @Test
    @DisplayName("Чтение AuditDto по id, позитивный сценарий")
    void readByIdPositiveTest() {

        final AuditDto auditDto = new AuditDto();
        when(service.findById(id)).thenReturn(auditDto);

        final AuditDto resDto = controller.read(id);

        assertEquals(auditDto, resDto);
    }

    @Test
    @DisplayName("Чтение AuditDto по id, негативный сценарий")
    void readByEmptyIdNegativeTest() {
        String massage = "Неверные данные";
        doThrow(new NotFoundException(massage)).when(service).findById(null);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> controller.read(null));
        assertEquals("Неверные данные", exception.getMessage());
    }
}
