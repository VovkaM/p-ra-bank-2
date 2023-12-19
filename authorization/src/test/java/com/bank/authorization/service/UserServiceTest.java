package com.bank.authorization.service;

import com.bank.authorization.dto.UserDto;
import com.bank.authorization.entity.UserEntity;
import com.bank.authorization.mapper.UserMapper;
import com.bank.authorization.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest  {

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    UserServiceImpl service;

    @Test
    @DisplayName("поиск по id, позитивный сценарий")
    void findUserByIdEntityExist() {
        final long id = 1L;
        final UserEntity entity = new UserEntity();
        final UserDto dto = new UserDto();
        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toDTO(entity)).thenReturn(dto);

        final UserDto finalDto = service.findById(1L);

        Assertions.assertEquals(dto, finalDto);
    }

    @Test
    @DisplayName("поиск по несуществующему id, негативный сценарий")
    void findUserByNonExistIdNegativeTest() {
        long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> service.findById(id));
    }

    @Test
    @DisplayName("поиск по листу id, позитивный сценарий")
    void findAllUserByIdListEntityExistPositiveTest() {
        final List<Long> idList = Arrays.asList(1L, 2L);
        final List<UserEntity> entitieList = Arrays.asList(
                new UserEntity(),
                new UserEntity()
        );
        final List<UserDto> expectedDto = Arrays.asList(
                new UserDto(),
                new UserDto()
        );

        when(repository.findById(1L)).thenReturn(Optional.of(entitieList.get(0)));
        when(repository.findById(2L)).thenReturn(Optional.of(entitieList.get(1)));
        when(mapper.toDtoList(entitieList)).thenReturn(expectedDto);

        final List<UserDto> actualDto = service.findAllByIds(idList);

        Assertions.assertEquals(expectedDto, actualDto);
    }

    @Test
    @DisplayName("сохранение User, позитивный сценарий")
    void saveUserEntityExistPositiveTest() {

        final UserDto dto = new UserDto();
        final UserEntity entity = new UserEntity();
        entity.setPassword("12345");
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDTO(entity)).thenReturn(dto);
        final UserDto finalDto = service.save(dto);

        Assertions.assertEquals(finalDto, dto);
    }

    @Test
    @DisplayName("сохранение User c нулевым аргументом, негативный сценарий")
    void saveUserByNullNegativeTest() {
        UserDto userDto = null;

        assertThrows(NullPointerException.class, () -> service.save(userDto));
    }

    @Test
    @DisplayName("изменение User по id, позитивный сценарий")
    void updateUserByIdPositiveTest() {
        final long id = 1L;
        UserDto dto = new UserDto();
        UserEntity entity = new UserEntity();
        UserEntity updateEntity = new UserEntity();
        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.mergeToEntity(dto,entity)).thenReturn(updateEntity);
        when(repository.save(updateEntity)).thenReturn(updateEntity);
        when(mapper.toDTO(updateEntity)).thenReturn(dto);

        final UserDto update = service.update(id, dto);
        Assertions.assertEquals(update, dto);
    }

    @Test
    @DisplayName("изменение User по id, негативный сценарий")
    void updateUserByIdNotExistNegativeTest() {
        final Long id = 1L;
        final UserDto dto = new UserDto();

        when(repository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> service.update(id, dto));
    }
}
