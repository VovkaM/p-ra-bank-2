package com.bank.account.service;

import com.bank.account.dto.AccountDetailsDto;
import com.bank.account.entity.AccountDetailsEntity;
import com.bank.account.mapper.AccountDetailsMapper;
import com.bank.account.repository.AccountDetailsRepository;
import com.bank.account.service.common.ExceptionReturner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountDetailsServiceImplTest {

    @Mock
    private AccountDetailsRepository repository;

    @Mock
    private AccountDetailsMapper mapper;

    @Mock
    private ExceptionReturner exceptionReturner;

    @InjectMocks
    AccountDetailsServiceImpl service;

    @Test
    @DisplayName("поиск по id, позитивный сценарий")
    void findByIdPositiveTest() {
        final long id = 1L;
        final AccountDetailsEntity entity = new AccountDetailsEntity();
        final AccountDetailsDto dto = new AccountDetailsDto();
        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        final AccountDetailsDto finalDto = service.findById(1L);

        assertEquals(dto, finalDto);
    }

    @Test
    @DisplayName("поиск по несуществующему id, негативный сценарий")
    void findByNonExistIdNegativeNest() {
        long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());
        when(exceptionReturner.getEntityNotFoundException(anyString())).thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> service.findById(id));
    }

    @Test
    @DisplayName("поиск по листу id, позитивный сценарий")
    void findAllByIdListEntityExistPositiveTest() {
        final List<Long> idList = Arrays.asList(1L, 2L);
        final List<AccountDetailsEntity> entitieList = Arrays.asList(
                new AccountDetailsEntity(),
                new AccountDetailsEntity()
        );
        final List<AccountDetailsDto> expectedDto = Arrays.asList(
                new AccountDetailsDto(),
                new AccountDetailsDto()
        );

        when(repository.findById(1L)).thenReturn(Optional.of(entitieList.get(0)));
        when(repository.findById(2L)).thenReturn(Optional.of(entitieList.get(1)));
        when(mapper.toDtoList(entitieList)).thenReturn(expectedDto);

        final List<AccountDetailsDto> actualDto = service.findAllById(idList);

        assertEquals(expectedDto, actualDto);
    }

    @Test
    @DisplayName("сохранение AccountDetails, позитивный сценарий")
    void saveEntityExistPositiveTest() {
        final AccountDetailsDto dto = new AccountDetailsDto();
        final AccountDetailsEntity entity = new AccountDetailsEntity();
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        final AccountDetailsDto finalDto = service.save(dto);

        assertEquals(finalDto, dto);
    }

    @Test
    @DisplayName("изменение AccountDetails по id, позитивный сценарий")
    void updateByIdPositiveTest() {
        final long id = 1L;
        AccountDetailsDto dto = new AccountDetailsDto();
        AccountDetailsEntity entity = new AccountDetailsEntity();
        AccountDetailsEntity updateEntity = new AccountDetailsEntity();
        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.mergeToEntity(entity, dto)).thenReturn(updateEntity);
        when(repository.save(updateEntity)).thenReturn(updateEntity);
        when(mapper.toDto(updateEntity)).thenReturn(dto);

        final AccountDetailsDto update = service.update(id, dto);

        assertEquals(update, dto);
    }

    @Test
    @DisplayName("изменение AccountDetails по id, негативный сценарий")
    void updateByNotExistNegativeNest() {
        final Long id = 1L;
        final AccountDetailsDto dto = new AccountDetailsDto();
        when(repository.findById(id)).thenReturn(Optional.empty());
        when(exceptionReturner.getEntityNotFoundException(anyString())).thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> service.update(id, dto));
    }

}