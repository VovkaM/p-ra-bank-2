package com.bank.antifraud.service.impl;

import com.bank.antifraud.dto.AuditDto;
import com.bank.antifraud.entity.AuditEntity;
import com.bank.antifraud.mappers.AuditMapper;
import com.bank.antifraud.repository.AuditRepository;
import com.bank.antifraud.service.common.ExceptionReturner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AuditServiceImplTest {
    @Mock
    private AuditRepository repository;

    @Mock
    private AuditMapper mapper;

    @Mock
    private ExceptionReturner exceptionReturner;

    @InjectMocks
    private AuditServiceImpl service;

    private long id = 1L;

    @Test
    @DisplayName("Поиск по id, позитивный сценарий")
    void findByIdPositiveTest() {

        AuditEntity entity = new AuditEntity();
        AuditDto dto = new AuditDto();

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        AuditDto finalDto = service.findById(id);

        assertEquals(dto, finalDto);
    }

    @Test
    @DisplayName("Поиск по несуществующему id, негативный сценарий")
    void findByNonExistIdNegativeNest() {
        when(repository.findById(id)).thenReturn(Optional.empty());
        when(exceptionReturner.getEntityNotFoundException(anyString())).thenThrow(new EntityNotFoundException());
        assertThrows(EntityNotFoundException.class, () -> service.findById(id));
        verify(repository, times(1)).findById(1L);

    }
}
