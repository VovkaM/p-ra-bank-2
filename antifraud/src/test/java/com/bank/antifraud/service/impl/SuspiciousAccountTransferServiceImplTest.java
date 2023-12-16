package com.bank.antifraud.service.impl;

import com.bank.antifraud.dto.SuspiciousAccountTransferDto;
import com.bank.antifraud.entity.SuspiciousAccountTransferEntity;
import com.bank.antifraud.mappers.SuspiciousAccountTransferMapper;
import com.bank.antifraud.repository.SuspiciousAccountTransferRepository;
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
public class SuspiciousAccountTransferServiceImplTest {
    @Mock
    private SuspiciousAccountTransferRepository repository;

    @Mock
    private SuspiciousAccountTransferMapper mapper;

   @Mock
    private ExceptionReturner exceptionReturner;

    @InjectMocks
    SuspiciousAccountTransferServiceImpl service;

    private final long id = 1L;

    @Test
    @DisplayName("Поиск по id, позитивный сценарий")
    void findByIdPositiveTest() {

        final SuspiciousAccountTransferEntity entity = new SuspiciousAccountTransferEntity();
        final SuspiciousAccountTransferDto dto = new SuspiciousAccountTransferDto();
        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        final SuspiciousAccountTransferDto finalDto = service.findById(1L);

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
        final List<SuspiciousAccountTransferEntity> entitieList = Arrays.asList(
                new SuspiciousAccountTransferEntity(),
                new SuspiciousAccountTransferEntity()
        );
        final List<SuspiciousAccountTransferDto> expectedDto = Arrays.asList(
                new SuspiciousAccountTransferDto(),
                new SuspiciousAccountTransferDto()
        );

        when(repository.findById(1L)).thenReturn(Optional.of(entitieList.get(0)));
        when(repository.findById(2L)).thenReturn(Optional.of(entitieList.get(1)));
        when(mapper.toListDto(entitieList)).thenReturn(expectedDto);

        final List<SuspiciousAccountTransferDto> actualDto = service.findAllById(idList);

        assertEquals(expectedDto, actualDto);

    }
    @Test
    @DisplayName("чтение по списку id, негативный сценарий")
    void readAllByNonExistIdNegativeTest() {
        final List<Long> idList = Arrays.asList(1L, 2L);
        final List<SuspiciousAccountTransferEntity> entitieList = Arrays.asList(
                new SuspiciousAccountTransferEntity(),
                new SuspiciousAccountTransferEntity()
        );
        final List<SuspiciousAccountTransferDto> expectedDto = Arrays.asList(
                new SuspiciousAccountTransferDto(),
                new SuspiciousAccountTransferDto()
        );
        final SuspiciousAccountTransferDto dto = new SuspiciousAccountTransferDto();
        when(repository.findAllById(idList)).thenReturn(entitieList);
        when(exceptionReturner.getEntityNotFoundException(anyString())).thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> service.findAllById(idList));

    }
    @Test
    @DisplayName("Сохранение, позитивный сценарий")
    void saveEntityPositiveTest() {
        final SuspiciousAccountTransferDto dto = new SuspiciousAccountTransferDto();
        final SuspiciousAccountTransferEntity entity = new SuspiciousAccountTransferEntity();
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        final SuspiciousAccountTransferDto finalDto = service.save(dto);

        assertEquals(finalDto, dto);
    }


    @Test
    @DisplayName("Изменение по id, позитивный сценарий")
    void updateByIdPositiveTest() {

        SuspiciousAccountTransferDto dto = new SuspiciousAccountTransferDto();
        SuspiciousAccountTransferEntity entity = new SuspiciousAccountTransferEntity();
        SuspiciousAccountTransferEntity updateEntity = new SuspiciousAccountTransferEntity();
        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.mergeToEntity(dto, entity)).thenReturn(updateEntity);
        when(repository.save(updateEntity)).thenReturn(updateEntity);
        when(mapper.toDto(updateEntity)).thenReturn(dto);

        final SuspiciousAccountTransferDto update = service.update(id, dto);

        assertEquals(update, dto);
    }

    @Test
    @DisplayName("Изменение по id, негативный сценарий")
    void updateByNotExistNegativeNest() {

        final SuspiciousAccountTransferDto dto = new SuspiciousAccountTransferDto();
        when(repository.findById(id)).thenReturn(Optional.empty());
        when(exceptionReturner.getEntityNotFoundException(anyString())).thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> service.update(id, dto));
    }
}
