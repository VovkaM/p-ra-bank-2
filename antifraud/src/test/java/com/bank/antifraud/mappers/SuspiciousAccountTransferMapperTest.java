package com.bank.antifraud.mappers;

import com.bank.antifraud.dto.SuspiciousAccountTransferDto;
import com.bank.antifraud.entity.SuspiciousAccountTransferEntity;
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
public class SuspiciousAccountTransferMapperTest {
    @InjectMocks
    private SuspiciousAccountTransferMapperImpl mapper;

    @Test
    @DisplayName("мапинг в Entity, на вход подан null")
    void toDtoNullTest() {
        final SuspiciousAccountTransferEntity result = mapper.toEntity(null);

        assertNull(result);
    }

    @Test
    @DisplayName("мапинг в Entity")
    void toDtoTest() {
        final SuspiciousAccountTransferDto suspiciousAccountTransferDto = new SuspiciousAccountTransferDto();
        suspiciousAccountTransferDto.setAccountTransferId(1234L);
        suspiciousAccountTransferDto.setIsBlocked(false);
        suspiciousAccountTransferDto.setIsSuspicious(false);
        suspiciousAccountTransferDto.setBlockedReason("Breaking the rules");
        suspiciousAccountTransferDto.setSuspiciousReason("Suspicious guy");


        final SuspiciousAccountTransferEntity suspiciousAccountTransferEntity =
                mapper.toEntity(suspiciousAccountTransferDto);

        assertAll(
                () -> assertEquals(suspiciousAccountTransferDto.getAccountTransferId(),
                        suspiciousAccountTransferEntity.getAccountTransferId()),
                () -> assertEquals(suspiciousAccountTransferDto.getIsBlocked(),
                        suspiciousAccountTransferEntity.getIsBlocked()),
                () -> assertEquals(suspiciousAccountTransferDto.getIsSuspicious(),
                        suspiciousAccountTransferEntity.getIsSuspicious()),
                () -> assertEquals(suspiciousAccountTransferDto.getBlockedReason(),
                        suspiciousAccountTransferEntity.getBlockedReason()),
                () -> assertEquals(suspiciousAccountTransferDto.getSuspiciousReason(),
                        suspiciousAccountTransferEntity.getSuspiciousReason())
        );
    }


    @Test
    @DisplayName("мапинг в Dto, на вход подан null")
    void toEntityNullTest() {
        final SuspiciousAccountTransferDto result = mapper.toDto(null);

        assertNull(result);
    }

    @Test
    @DisplayName("маппинг в Dto")
    void toEntityTest() {
        final SuspiciousAccountTransferEntity suspiciousAccountTransferEntity = new SuspiciousAccountTransferEntity();
        suspiciousAccountTransferEntity.setId(1L);
        suspiciousAccountTransferEntity.setAccountTransferId(1234L);
        suspiciousAccountTransferEntity.setIsBlocked(false);
        suspiciousAccountTransferEntity.setIsSuspicious(false);
        suspiciousAccountTransferEntity.setBlockedReason("Breaking the rules");
        suspiciousAccountTransferEntity.setSuspiciousReason("Suspicious guy");

        final SuspiciousAccountTransferDto suspiciousAccountTransferDto = mapper.toDto(suspiciousAccountTransferEntity);

        assertAll(
                () -> assertEquals(suspiciousAccountTransferEntity.getId(),
                        suspiciousAccountTransferDto.getId()),
                () -> assertEquals(suspiciousAccountTransferEntity.getAccountTransferId(),
                        suspiciousAccountTransferDto.getAccountTransferId()),
                () -> assertEquals(suspiciousAccountTransferEntity.getIsBlocked(),
                        suspiciousAccountTransferDto.getIsBlocked()),
                () -> assertEquals(suspiciousAccountTransferEntity.getIsSuspicious(),
                        suspiciousAccountTransferDto.getIsSuspicious()),
                () -> assertEquals(suspiciousAccountTransferEntity.getBlockedReason(),
                        suspiciousAccountTransferDto.getBlockedReason()),
                () -> assertEquals(suspiciousAccountTransferEntity.getSuspiciousReason(),
                        suspiciousAccountTransferDto.getSuspiciousReason())

        );
    }

    @Test
    @DisplayName("маппинг в List<Dto>, на вход подан null")
    void toEntityListNullTest() {
        final List<SuspiciousAccountTransferDto> dtoList = mapper.toListDto(null);

        assertNull(dtoList);
    }

    @Test
    @DisplayName("маппинг в List<Dto> ")
    void toEntityListTest() {
        SuspiciousAccountTransferEntity suspiciousAccountTransferEntity1 = new SuspiciousAccountTransferEntity();
        suspiciousAccountTransferEntity1.setId(1L);
        suspiciousAccountTransferEntity1.setAccountTransferId(1234L);
        suspiciousAccountTransferEntity1.setIsBlocked(false);
        suspiciousAccountTransferEntity1.setIsSuspicious(false);
        suspiciousAccountTransferEntity1.setBlockedReason("Breaking the rules");
        suspiciousAccountTransferEntity1.setSuspiciousReason("Suspicious guy");

        SuspiciousAccountTransferEntity suspiciousAccountTransferEntity2 = new SuspiciousAccountTransferEntity();
        suspiciousAccountTransferEntity2.setId(2L);
        suspiciousAccountTransferEntity2.setAccountTransferId(5678L);
        suspiciousAccountTransferEntity2.setIsBlocked(false);
        suspiciousAccountTransferEntity2.setIsSuspicious(false);
        suspiciousAccountTransferEntity2.setBlockedReason("Breaking the rules 2");
        suspiciousAccountTransferEntity2.setSuspiciousReason("Suspicious guy2");

        final List<SuspiciousAccountTransferEntity> entityList =
                Arrays.asList(suspiciousAccountTransferEntity1,
                              suspiciousAccountTransferEntity2);

        final List<SuspiciousAccountTransferDto> dtoList = mapper.toListDto(entityList);

        assertAll(
                () -> assertEquals(entityList.size(), dtoList.size()),
                () -> assertEquals(suspiciousAccountTransferEntity1.getId(), dtoList.get(0).getId()),
                () -> assertEquals(suspiciousAccountTransferEntity2.getId(), dtoList.get(1).getId()),
                () -> assertEquals(suspiciousAccountTransferEntity1.getAccountTransferId(),
                        dtoList.get(0).getAccountTransferId()),
                () -> assertEquals(suspiciousAccountTransferEntity2.getIsBlocked(), dtoList.get(1).getIsBlocked())
        );
    }

    @Test
    @DisplayName("Слияние в Entity, на вход подан null")
    void mergeToEntityNullTest() {
        final SuspiciousAccountTransferEntity suspiciousAccountTransferEntity = new SuspiciousAccountTransferEntity();
        final SuspiciousAccountTransferEntity mergedAccountDetailsEntity =
                mapper.mergeToEntity(null, suspiciousAccountTransferEntity);
        assertEquals(suspiciousAccountTransferEntity, mergedAccountDetailsEntity);

    }

    @Test
    @DisplayName("Слияние в Entity")
    void mergeToEntityTest() {
        final SuspiciousAccountTransferEntity suspiciousAccountTransferEntity =
                new SuspiciousAccountTransferEntity();
        suspiciousAccountTransferEntity.setAccountTransferId(1234L);
        suspiciousAccountTransferEntity.setIsBlocked(false);
        suspiciousAccountTransferEntity.setIsSuspicious(false);
        suspiciousAccountTransferEntity.setBlockedReason("Breaking the rules");
        suspiciousAccountTransferEntity.setSuspiciousReason("Suspicious guy");

        final SuspiciousAccountTransferDto suspiciousAccountTransferDto = new SuspiciousAccountTransferDto();
        suspiciousAccountTransferDto.setAccountTransferId(1234L);
        suspiciousAccountTransferDto.setIsBlocked(false);
        suspiciousAccountTransferDto.setIsSuspicious(false);
        suspiciousAccountTransferDto.setBlockedReason("Breaking the rules");
        suspiciousAccountTransferDto.setSuspiciousReason("Suspicious guy");

        final SuspiciousAccountTransferEntity mergedAccountDetailsEntity =
                mapper.mergeToEntity(suspiciousAccountTransferDto, suspiciousAccountTransferEntity);

        assertAll(
                () -> assertEquals(suspiciousAccountTransferDto.getAccountTransferId(),
                        mergedAccountDetailsEntity.getAccountTransferId()),
                () -> assertEquals(suspiciousAccountTransferDto.getIsBlocked(),
                        mergedAccountDetailsEntity.getIsBlocked()),
                () -> assertEquals(suspiciousAccountTransferDto.getIsSuspicious(),
                        mergedAccountDetailsEntity.getIsSuspicious()),
                () -> assertEquals(suspiciousAccountTransferDto.getBlockedReason(),
                        mergedAccountDetailsEntity.getBlockedReason()),
                () -> assertEquals(suspiciousAccountTransferDto.getSuspiciousReason(),
                        mergedAccountDetailsEntity.getSuspiciousReason())
        );
    }
}
