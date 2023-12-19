package com.bank.authorization.mapper;

import com.bank.authorization.dto.UserDto;
import com.bank.authorization.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    @InjectMocks
    private UserMapperImpl mapper;

    @Test
    @DisplayName("мапинг в Entity")
    void toEntityTest() {
        final UserDto userDto = new UserDto();
        userDto.setId(null);
        userDto.setRole("ADMIN");
        userDto.setPassword("12345");
        userDto.setProfileId(1L);

        final UserEntity UserEntity = mapper.toEntity(userDto);
        assertAll(
                () -> assertEquals(userDto.getId(), UserEntity.getId()),
                () -> assertEquals(userDto.getRole(), UserEntity.getRole()),
                () -> assertEquals(userDto.getPassword(), UserEntity.getPassword()),
                () -> assertEquals(userDto.getProfileId(), UserEntity.getProfileId())
        );
    }

    @Test
    @DisplayName("мапинг в Entity, на вход подан null")
    void toEntityNullTest() {
        final UserEntity result = mapper.toEntity(null);

        assertNull(result);
    }

    @Test
    @DisplayName("маппинг в Dto")
    void toDtoTest() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setRole("ADMIN");
        userEntity.setPassword("12345");
        userEntity.setProfileId(1L);

        final UserDto userDto = mapper.toDTO(userEntity);
        assertAll(
                () -> assertEquals(userEntity.getId(), userDto.getId()),
                () -> assertEquals(userEntity.getRole(), userDto.getRole()),
                () -> assertEquals(userEntity.getPassword(), userDto.getPassword()),
                () -> assertEquals(userEntity.getProfileId(), userDto.getProfileId())
        );
    }

    @Test
    @DisplayName("маппинг в Dto, на вход подан null")
    void toDtoNullTest() {
        final UserDto result = mapper.toDTO(null);

        assertNull(result);
    }

    @Test
    @DisplayName("маппинг лист Entity в лист Dto")
    void toDtoListWithList() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setPassword("12345");
        userEntity.setRole("ADMIN");
        userEntity.setProfileId(1L);

        UserEntity userEntity1 = new UserEntity();
        userEntity1.setId(2L);
        userEntity1.setPassword("12345");
        userEntity1.setRole("USER");
        userEntity1.setProfileId(2L);

        final List<UserEntity> entityList = Arrays.asList(userEntity, userEntity1);

        final List<UserDto> dtoList = mapper.toDtoList(entityList);
        assertAll(
                () -> assertEquals(entityList.size(), dtoList.size()),
                () -> assertEquals(userEntity.getId(), dtoList.get(0).getId()),
                () -> assertEquals(userEntity1.getId(), dtoList.get(1).getId()),
                () -> assertEquals(userEntity.getPassword(), dtoList.get(0).getPassword()),
                () -> assertEquals(userEntity1.getPassword(), dtoList.get(1).getPassword())
        );
    }

    @Test
    @DisplayName("маппинг лист Entity в лист Dto, на вход подан null")
    void toDtoListNullTest() {
        final List<UserDto> dtoList = mapper.toDtoList(null);

        assertNull(dtoList);
    }

    @Test
    @DisplayName("слияние в Entity")
    void mergeToEntityTest() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setId(1234L);
        userEntity.setRole("USER");
        userEntity.setPassword("12345");
        userEntity.setProfileId(1L);

        final UserDto userDto = new UserDto();
        userDto.setId(1234L);
        userDto.setRole("ADMIN");
        userDto.setPassword("12345");
        userDto.setProfileId(2L);

        final UserEntity mergeToEntity = mapper.mergeToEntity(userDto, userEntity);
        assertAll(
                () -> assertEquals(userDto.getId(), mergeToEntity.getId()),
                () -> assertEquals(userDto.getRole(), mergeToEntity.getRole()),
                () -> assertEquals(userDto.getPassword(), mergeToEntity.getPassword()),
                () -> assertEquals(userDto.getProfileId(), mergeToEntity.getProfileId())
        );
    }

    @Test
    @DisplayName("слияние в Entity, на вход подан null")
    void mergeToEntityNullTest() {
        final UserEntity userEntity = new UserEntity();
        final UserEntity mergedEntity = mapper.mergeToEntity(null, userEntity);

        assertEquals(userEntity, mergedEntity);

    }
}