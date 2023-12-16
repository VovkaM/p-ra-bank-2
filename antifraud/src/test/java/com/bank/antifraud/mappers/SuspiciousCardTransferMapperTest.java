package com.bank.antifraud.mappers;

import com.bank.antifraud.dto.SuspiciousCardTransferDto;
import com.bank.antifraud.entity.SuspiciousCardTransferEntity;
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
public class SuspiciousCardTransferMapperTest {
    @InjectMocks
    private SuspiciousCardTransferMapperImpl mapper;

    @Test
    @DisplayName("маппинг в Entity, на вход подан null")
    void toDtoNullTest() {
        final SuspiciousCardTransferEntity result = mapper.toEntity(null);

        assertNull(result);
    }

    @Test
    @DisplayName("маппинг в Entity")
    void toDtoTest() {
        final SuspiciousCardTransferDto suspiciousCardTransferDto = new SuspiciousCardTransferDto();
        suspiciousCardTransferDto.setCardTransferId(1234L);
        suspiciousCardTransferDto.setIsBlocked(false);
        suspiciousCardTransferDto.setIsSuspicious(false);
        suspiciousCardTransferDto.setBlockedReason("Breaking the rules");
        suspiciousCardTransferDto.setSuspiciousReason("Suspicious guy");


        final SuspiciousCardTransferEntity suspiciousCardTransferEntity = mapper.toEntity(suspiciousCardTransferDto);

        assertAll(
                () -> assertEquals(suspiciousCardTransferDto.getCardTransferId(),
                        suspiciousCardTransferEntity.getCardTransferId()),
                () -> assertEquals(suspiciousCardTransferDto.getIsBlocked(),
                        suspiciousCardTransferEntity.getIsBlocked()),
                () -> assertEquals(suspiciousCardTransferDto.getIsSuspicious(),
                        suspiciousCardTransferEntity.getIsSuspicious()),
                () -> assertEquals(suspiciousCardTransferDto.getBlockedReason(),
                        suspiciousCardTransferEntity.getBlockedReason()),
                () -> assertEquals(suspiciousCardTransferDto.getSuspiciousReason(),
                        suspiciousCardTransferEntity.getSuspiciousReason())
        );
    }


    @Test
    @DisplayName("маппинг в Dto, на вход подан null")
    void toEntityNullTest() {
        final SuspiciousCardTransferDto result = mapper.toDto(null);

        assertNull(result);
    }

    @Test
    @DisplayName("маппинг в Dto")
    void toEntityTest() {
        final SuspiciousCardTransferEntity suspiciousCardTransferEntity = new SuspiciousCardTransferEntity();
        suspiciousCardTransferEntity.setId(1L);
        suspiciousCardTransferEntity.setCardTransferId(1234L);
        suspiciousCardTransferEntity.setIsBlocked(false);
        suspiciousCardTransferEntity.setIsSuspicious(false);
        suspiciousCardTransferEntity.setBlockedReason("Breaking the rules");
        suspiciousCardTransferEntity.setSuspiciousReason("Suspicious guy");

        final SuspiciousCardTransferDto suspiciousCardTransferDto = mapper.toDto(suspiciousCardTransferEntity);
        assertAll(
                () -> assertEquals(suspiciousCardTransferEntity.getId(),
                        suspiciousCardTransferDto.getId()),
                () -> assertEquals(suspiciousCardTransferEntity.getCardTransferId(),
                        suspiciousCardTransferDto.getCardTransferId()),
                () -> assertEquals(suspiciousCardTransferEntity.getIsBlocked(),
                        suspiciousCardTransferDto.getIsBlocked()),
                () -> assertEquals(suspiciousCardTransferEntity.getIsSuspicious(),
                        suspiciousCardTransferDto.getIsSuspicious()),
                () -> assertEquals(suspiciousCardTransferEntity.getBlockedReason(),
                        suspiciousCardTransferDto.getBlockedReason()),
                () -> assertEquals(suspiciousCardTransferEntity.getSuspiciousReason(),
                        suspiciousCardTransferDto.getSuspiciousReason())
        );
    }

    @Test
    @DisplayName("Преобразование в List<Dto>, на вход подан null")
    void toEntityListNullTest() {
        final List<SuspiciousCardTransferDto> dtoList = mapper.toListDto(null);

        assertNull(dtoList);
    }

    @Test
    @DisplayName("Слияние в List<Dto>")
    void toEntityListTest() {
        SuspiciousCardTransferEntity suspiciousCardTransferEntity1 = new SuspiciousCardTransferEntity();
        suspiciousCardTransferEntity1.setId(1L);
        suspiciousCardTransferEntity1.setCardTransferId(1234L);
        suspiciousCardTransferEntity1.setIsBlocked(false);
        suspiciousCardTransferEntity1.setIsSuspicious(false);
        suspiciousCardTransferEntity1.setBlockedReason("Breaking the rules");
        suspiciousCardTransferEntity1.setSuspiciousReason("Suspicious guy");

        SuspiciousCardTransferEntity suspiciousCardTransferEntity2 = new SuspiciousCardTransferEntity();
        suspiciousCardTransferEntity2.setId(2L);
        suspiciousCardTransferEntity2.setCardTransferId(5678L);
        suspiciousCardTransferEntity2.setIsBlocked(false);
        suspiciousCardTransferEntity2.setIsSuspicious(false);
        suspiciousCardTransferEntity2.setBlockedReason("Breaking the rules 2");
        suspiciousCardTransferEntity2.setSuspiciousReason("Suspicious guy2");

        final List<SuspiciousCardTransferEntity> entityList =
                Arrays.asList(suspiciousCardTransferEntity1,
                              suspiciousCardTransferEntity2);

        final List<SuspiciousCardTransferDto> dtoList = mapper.toListDto(entityList);

        assertAll(
                () -> assertEquals(entityList.size(), dtoList.size()),
                () -> assertEquals(suspiciousCardTransferEntity1.getId(), dtoList.get(0).getId()),
                () -> assertEquals(suspiciousCardTransferEntity2.getId(), dtoList.get(1).getId()),
                () -> assertEquals(suspiciousCardTransferEntity1.getCardTransferId(), dtoList.get(0).
                        getCardTransferId()),
                () -> assertEquals(suspiciousCardTransferEntity2.getIsBlocked(), dtoList.get(1).getIsBlocked())
        );
    }

    @Test
    @DisplayName("Слияние в Entity, на вход подан null")
    void mergeToEntityNullTest() {
        final SuspiciousCardTransferEntity suspiciousCardTransferEntity = new SuspiciousCardTransferEntity();
        final SuspiciousCardTransferEntity mergedCardDetailsEntity =
                mapper.mergeToEntity(null, suspiciousCardTransferEntity);
        assertEquals(suspiciousCardTransferEntity, mergedCardDetailsEntity);

    }

    @Test
    @DisplayName("Слияние в Entity")
    void mergeToEntityTest() {
        final SuspiciousCardTransferEntity suspiciousCardTransferEntity = new SuspiciousCardTransferEntity();
        suspiciousCardTransferEntity.setCardTransferId(1234L);
        suspiciousCardTransferEntity.setIsBlocked(false);
        suspiciousCardTransferEntity.setIsSuspicious(false);
        suspiciousCardTransferEntity.setBlockedReason("Breaking the rules");
        suspiciousCardTransferEntity.setSuspiciousReason("Suspicious guy");

        final SuspiciousCardTransferDto suspiciousCardTransferDto = new SuspiciousCardTransferDto();
        suspiciousCardTransferDto.setCardTransferId(1234L);
        suspiciousCardTransferDto.setIsBlocked(false);
        suspiciousCardTransferDto.setIsSuspicious(false);
        suspiciousCardTransferDto.setBlockedReason("Breaking the rules");
        suspiciousCardTransferDto.setSuspiciousReason("Suspicious guy");

        final SuspiciousCardTransferEntity mergedCardDetailsEntity =
                mapper.mergeToEntity(suspiciousCardTransferDto, suspiciousCardTransferEntity);
        assertAll(
                () -> assertEquals(suspiciousCardTransferDto.getCardTransferId(),
                        mergedCardDetailsEntity.getCardTransferId()),
                () -> assertEquals(suspiciousCardTransferDto.getIsBlocked(),
                        mergedCardDetailsEntity.getIsBlocked()),
                () -> assertEquals(suspiciousCardTransferDto.getIsSuspicious(),
                        mergedCardDetailsEntity.getIsSuspicious()),
                () -> assertEquals(suspiciousCardTransferDto.getBlockedReason(),
                        mergedCardDetailsEntity.getBlockedReason()),
                () -> assertEquals(suspiciousCardTransferDto.getSuspiciousReason(),
                        mergedCardDetailsEntity.getSuspiciousReason())
        );
    }
}
