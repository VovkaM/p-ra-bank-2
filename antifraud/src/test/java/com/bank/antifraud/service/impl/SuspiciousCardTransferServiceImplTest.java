package com.bank.antifraud.service.impl;

import com.bank.antifraud.dto.SuspiciousCardTransferDto;
import com.bank.antifraud.entity.SuspiciousCardTransferEntity;
import com.bank.antifraud.mappers.SuspiciousCardTransferMapper;
import com.bank.antifraud.repository.SuspiciousCardTransferRepository;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class SuspiciousCardTransferServiceImplTest {
    @Mock
    private SuspiciousCardTransferRepository repository;

    @Mock
    private SuspiciousCardTransferMapper mapper;

    @Mock
    private ExceptionReturner exceptionReturner;

    @InjectMocks
    SuspiciousCardTransferServiceImpl service;

    private final long id = 1L;

    @Test
    @DisplayName("Поиск по id, позитивный сценарий")
    void findByIdPositiveTest() {

        final SuspiciousCardTransferEntity entity = new SuspiciousCardTransferEntity();
        final SuspiciousCardTransferDto dto = new SuspiciousCardTransferDto();
        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        final SuspiciousCardTransferDto finalDto = service.findById(1L);

        assertEquals(dto, finalDto);
    }

    @Test
    @DisplayName("Поиск по несуществующему id, негативный сценарий")
    void findByNonExistIdNegativeTest() {

        when(repository.findById(id)).thenReturn(Optional.empty());
        when(exceptionReturner.getEntityNotFoundException(anyString())).thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> service.findById(id));
    }

    @Test
    @DisplayName("Поиск по List<id>, позитивный сценарий")
    void findAllByIdPositiveTest() {
        final List<Long> idList = Arrays.asList(1L, 2L);
        final List<SuspiciousCardTransferEntity> entitieList = Arrays.asList(
                new SuspiciousCardTransferEntity(),
                new SuspiciousCardTransferEntity()
        );
        final List<SuspiciousCardTransferDto> expectedDto = Arrays.asList(
                new SuspiciousCardTransferDto(),
                new SuspiciousCardTransferDto()
        );

        when(repository.findById(1L)).thenReturn(Optional.of(entitieList.get(0)));
        when(repository.findById(2L)).thenReturn(Optional.of(entitieList.get(1)));
        when(mapper.toListDto(entitieList)).thenReturn(expectedDto);

        final List<SuspiciousCardTransferDto> actualDto = service.findAllById(idList);

        assertEquals(expectedDto, actualDto);
    }

    @Test
    @DisplayName("чтение по списку id, негативный сценарий")
    void readAllByNonExistIdNegativeTest() {
        final List<Long> idList = Arrays.asList(1L, 2L);
        final List<SuspiciousCardTransferEntity> entitieList = Arrays.asList(
                new SuspiciousCardTransferEntity(),
                new SuspiciousCardTransferEntity()
        );
        final List<SuspiciousCardTransferDto> expectedDto = Arrays.asList(
                new SuspiciousCardTransferDto(),
                new SuspiciousCardTransferDto()
        );
        final SuspiciousCardTransferDto dto = new SuspiciousCardTransferDto();
        when(repository.findAllById(idList)).thenReturn(entitieList);
        when(exceptionReturner.getEntityNotFoundException(anyString())).thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> service.findAllById(idList));
    }

    @Test
    @DisplayName("Сохранение, позитивный сценарий")
    void saveEntityPositiveTest() {
        final SuspiciousCardTransferDto dto = new SuspiciousCardTransferDto();
        final SuspiciousCardTransferEntity entity = new SuspiciousCardTransferEntity();
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        final SuspiciousCardTransferDto finalDto = service.save(dto);

        assertEquals(finalDto, dto);
    }

    @Test
    @DisplayName("Изменение по id, позитивный сценарий")
    void updateByIdPositiveTest() {

        SuspiciousCardTransferDto dto = new SuspiciousCardTransferDto();
        SuspiciousCardTransferEntity entity = new SuspiciousCardTransferEntity();
        SuspiciousCardTransferEntity updateEntity = new SuspiciousCardTransferEntity();
        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.mergeToEntity(dto, entity)).thenReturn(updateEntity);
        when(repository.save(updateEntity)).thenReturn(updateEntity);
        when(mapper.toDto(updateEntity)).thenReturn(dto);

        final SuspiciousCardTransferDto update = service.update(id, dto);

        assertEquals(update, dto);
    }

    @Test
    @DisplayName("Изменение по id, негативный сценарий")
    void updateByNotExistNegativeNest() {

        final SuspiciousCardTransferDto dto = new SuspiciousCardTransferDto();
        when(repository.findById(id)).thenReturn(Optional.empty());
        when(exceptionReturner.getEntityNotFoundException(anyString())).thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> service.update(id, dto));
    }
}
