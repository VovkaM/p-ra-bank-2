package com.bank.antifraud.mappers;

import com.bank.antifraud.dto.SuspiciousPhoneTransferDto;
import com.bank.antifraud.entity.SuspiciousPhoneTransferEntity;
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
public class SuspiciousPhoneTransferMapperTest {
    @InjectMocks
    private SuspiciousPhoneTransferMapperImpl mapper;

    @Test
    @DisplayName("Преобразование в SuspiciousPhoneTransferEntity, на вход подан null")
    void toDtoNullTest() {
        final SuspiciousPhoneTransferEntity result = mapper.toEntity(null);

        assertNull(result);
    }

    @Test
    @DisplayName("Преобразование в SuspiciousPhoneTransferEntity")
    void toDtoTest() {
        final SuspiciousPhoneTransferDto suspiciousPhoneTransferDto = new SuspiciousPhoneTransferDto();
        suspiciousPhoneTransferDto.setPhoneTransferId(1234L);
        suspiciousPhoneTransferDto.setIsBlocked(false);
        suspiciousPhoneTransferDto.setIsSuspicious(false);
        suspiciousPhoneTransferDto.setBlockedReason("Breaking the rules");
        suspiciousPhoneTransferDto.setSuspiciousReason("Suspicious guy");


        final SuspiciousPhoneTransferEntity suspiciousPhoneTransferEntity =
                mapper.toEntity(suspiciousPhoneTransferDto);
        assertAll(
                () -> assertEquals(suspiciousPhoneTransferDto.getPhoneTransferId(),
                        suspiciousPhoneTransferEntity.getPhoneTransferId()),
                () -> assertEquals(suspiciousPhoneTransferDto.getIsBlocked(),
                        suspiciousPhoneTransferEntity.getIsBlocked()),
                () -> assertEquals(suspiciousPhoneTransferDto.getIsSuspicious(),
                        suspiciousPhoneTransferEntity.getIsSuspicious()),
                () -> assertEquals(suspiciousPhoneTransferDto.getBlockedReason(),
                        suspiciousPhoneTransferEntity.getBlockedReason()),
                () -> assertEquals(suspiciousPhoneTransferDto.getSuspiciousReason(),
                        suspiciousPhoneTransferEntity.getSuspiciousReason())
        );
    }


    @Test
    @DisplayName("Преобразование в SuspiciousPhoneTransferDto, на вход подан null")
    void toEntityNullTest() {
        final SuspiciousPhoneTransferDto result = mapper.toDto(null);

        assertNull(result);
    }

    @Test
    @DisplayName("Преобразование в SuspiciousPhoneTransferDto")
    void toEntityTest() {
        final SuspiciousPhoneTransferEntity suspiciousPhoneTransferEntity = new SuspiciousPhoneTransferEntity();
        suspiciousPhoneTransferEntity.setId(1L);
        suspiciousPhoneTransferEntity.setPhoneTransferId(1234L);
        suspiciousPhoneTransferEntity.setIsBlocked(false);
        suspiciousPhoneTransferEntity.setIsSuspicious(false);
        suspiciousPhoneTransferEntity.setBlockedReason("Breaking the rules");
        suspiciousPhoneTransferEntity.setSuspiciousReason("Suspicious guy");

        final SuspiciousPhoneTransferDto suspiciousPhoneTransferDto =
                mapper.toDto(suspiciousPhoneTransferEntity);
        assertAll(
                () -> assertEquals(suspiciousPhoneTransferEntity.getId(),
                        suspiciousPhoneTransferDto.getId()),
                () -> assertEquals(suspiciousPhoneTransferEntity.getPhoneTransferId(),
                        suspiciousPhoneTransferDto.getPhoneTransferId()),
                () -> assertEquals(suspiciousPhoneTransferEntity.getIsBlocked(),
                        suspiciousPhoneTransferDto.getIsBlocked()),
                () -> assertEquals(suspiciousPhoneTransferEntity.getIsSuspicious(),
                        suspiciousPhoneTransferDto.getIsSuspicious()),
                () -> assertEquals(suspiciousPhoneTransferEntity.getBlockedReason(),
                        suspiciousPhoneTransferDto.getBlockedReason()),
                () -> assertEquals(suspiciousPhoneTransferEntity.getSuspiciousReason(),
                        suspiciousPhoneTransferDto.getSuspiciousReason())
        );

    }

    @Test
    @DisplayName("Преобразование List<SuspiciousPhoneTransferEntity> " +
            "в List<SuspiciousPhoneTransferDto>, на вход подан null")
    void toEntityListNullTest() {
        final List<SuspiciousPhoneTransferDto> dtoList = mapper.toListDto(null);

        assertNull(dtoList);
    }

    @Test
    @DisplayName("Преобразование List<SuspiciousPhoneTransferEntity> в List<SuspiciousCardTransferDto>")
    void toEntityListTest() {
        SuspiciousPhoneTransferEntity suspiciousPhoneTransferEntity1 = new SuspiciousPhoneTransferEntity();
        suspiciousPhoneTransferEntity1.setId(1L);
        suspiciousPhoneTransferEntity1.setPhoneTransferId(1234L);
        suspiciousPhoneTransferEntity1.setIsBlocked(false);
        suspiciousPhoneTransferEntity1.setIsSuspicious(false);
        suspiciousPhoneTransferEntity1.setBlockedReason("Breaking the rules");
        suspiciousPhoneTransferEntity1.setSuspiciousReason("Suspicious guy");

        SuspiciousPhoneTransferEntity suspiciousPhoneTransferEntity2 = new SuspiciousPhoneTransferEntity();
        suspiciousPhoneTransferEntity2.setId(2L);
        suspiciousPhoneTransferEntity2.setPhoneTransferId(5678L);
        suspiciousPhoneTransferEntity2.setIsBlocked(false);
        suspiciousPhoneTransferEntity2.setIsSuspicious(false);
        suspiciousPhoneTransferEntity2.setBlockedReason("Breaking the rules 2");
        suspiciousPhoneTransferEntity2.setSuspiciousReason("Suspicious guy2");

        final List<SuspiciousPhoneTransferEntity> entityList =
                Arrays.asList(suspiciousPhoneTransferEntity1,
                              suspiciousPhoneTransferEntity2);

        final List<SuspiciousPhoneTransferDto> dtoList = mapper.toListDto(entityList);

        assertAll(
                () -> assertEquals(entityList.size(), dtoList.size()),
                () -> assertEquals(suspiciousPhoneTransferEntity1.getId(),
                        dtoList.get(0).getId()),
                () -> assertEquals(suspiciousPhoneTransferEntity2.getId(),
                        dtoList.get(1).getId()),
                () -> assertEquals(suspiciousPhoneTransferEntity1.getPhoneTransferId(),
                        dtoList.get(0).getPhoneTransferId()),
                () -> assertEquals(suspiciousPhoneTransferEntity2.getIsBlocked(),
                        dtoList.get(1).getIsBlocked())
        );
    }

    @Test
    @DisplayName("Слияние в SuspiciousPhoneTransferEntity, на вход подан null")
    void mergeToEntityNullTest() {
        final SuspiciousPhoneTransferEntity suspiciousPhoneTransferEntity = new SuspiciousPhoneTransferEntity();
        final SuspiciousPhoneTransferEntity mergedPhoneDetailsEntity =
                mapper.mergeToEntity(null, suspiciousPhoneTransferEntity);
        assertEquals(suspiciousPhoneTransferEntity, mergedPhoneDetailsEntity);

    }

    @Test
    @DisplayName("Слияние в SuspiciousPhoneTransferEntity")
    void mergeToEntityTest() {
        final SuspiciousPhoneTransferEntity suspiciousPhoneTransferEntity = new SuspiciousPhoneTransferEntity();
        suspiciousPhoneTransferEntity.setPhoneTransferId(1234L);
        suspiciousPhoneTransferEntity.setIsBlocked(false);
        suspiciousPhoneTransferEntity.setIsSuspicious(false);
        suspiciousPhoneTransferEntity.setBlockedReason("Breaking the rules");
        suspiciousPhoneTransferEntity.setSuspiciousReason("Suspicious guy");

        final SuspiciousPhoneTransferDto suspiciousPhoneTransferDto = new SuspiciousPhoneTransferDto();
        suspiciousPhoneTransferDto.setPhoneTransferId(1234L);
        suspiciousPhoneTransferDto.setIsBlocked(false);
        suspiciousPhoneTransferDto.setIsSuspicious(false);
        suspiciousPhoneTransferDto.setBlockedReason("Breaking the rules");
        suspiciousPhoneTransferDto.setSuspiciousReason("Suspicious guy");

        final SuspiciousPhoneTransferEntity mergedPhoneDetailsEntity =
                mapper.mergeToEntity(suspiciousPhoneTransferDto, suspiciousPhoneTransferEntity);
        assertAll(
                () -> assertEquals(suspiciousPhoneTransferDto.getPhoneTransferId(),
                        mergedPhoneDetailsEntity.getPhoneTransferId()),
                () -> assertEquals(suspiciousPhoneTransferDto.getIsBlocked(),
                        mergedPhoneDetailsEntity.getIsBlocked()),
                () -> assertEquals(suspiciousPhoneTransferDto.getIsSuspicious(),
                        mergedPhoneDetailsEntity.getIsSuspicious()),
                () -> assertEquals(suspiciousPhoneTransferDto.getBlockedReason(),
                        mergedPhoneDetailsEntity.getBlockedReason()),
                () -> assertEquals(suspiciousPhoneTransferDto.getSuspiciousReason(),
                        mergedPhoneDetailsEntity.getSuspiciousReason())
        );
    }
}
