package com.bank.history.mapper;

import com.bank.history.dto.HistoryDto;
import com.bank.history.entity.HistoryEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class HistoryMapperImplTest {

    @InjectMocks
    HistoryMapperImpl historyMapper;

    @Test
    @DisplayName("преобразование в entity при dto = null")
    void toEntityNegativeTest() {
        HistoryEntity result = historyMapper.toEntity(null);

        assertNull(result);
    }

    @Test
    @DisplayName("преобразование в entity при валидном dto")
    void toEntityPositiveTest() {
        HistoryDto historyDto = new HistoryDto(
                1L,
                2L,
                3L,
                4L,
                5L,
                6L,
                7L
        );

        HistoryEntity historyEntity = historyMapper.toEntity(historyDto);
        historyEntity.setId(historyDto.getId());

        assertAll(
                () -> assertNotNull(historyEntity),
                () -> assertEquals(historyDto.getId(), historyEntity.getId()),
                () -> assertEquals(historyDto.getPublicBankInfoAuditId(), historyEntity.getPublicBankInfoAuditId()),
                () -> assertEquals(historyDto.getProfileAuditId(), historyEntity.getProfileAuditId()),
                () -> assertEquals(historyDto.getAntiFraudAuditId(), historyEntity.getAntiFraudAuditId()),
                () -> assertEquals(historyDto.getAuthorizationAuditId(), historyEntity.getAuthorizationAuditId()),
                () -> assertEquals(historyDto.getAccountAuditId(), historyEntity.getAccountAuditId()),
                () -> assertEquals(historyDto.getTransferAuditId(), historyEntity.getTransferAuditId())
        );
    }

    @Test
    @DisplayName("преобразование в dto при entity = null")
    void toDtoNegativeTest() {
        HistoryDto historyDto = historyMapper.toDto(null);

        assertNull(historyDto);
    }

    @Test
    @DisplayName("преобразование в dto при валидном entity")
    void toDtoPositiveTest() {
        HistoryEntity historyEntity = new HistoryEntity(
                1L,
                2L,
                3L,
                4L,
                5L,
                6L,
                7L
        );

        HistoryDto historyDto = historyMapper.toDto(historyEntity);
        historyDto.setId(historyEntity.getId());

        assertAll(
                () -> assertNotNull(historyDto),
                () -> assertEquals(historyEntity.getId(), historyDto.getId()),
                () -> assertEquals(historyEntity.getPublicBankInfoAuditId(), historyDto.getPublicBankInfoAuditId()),
                () -> assertEquals(historyEntity.getProfileAuditId(), historyDto.getProfileAuditId()),
                () -> assertEquals(historyEntity.getAntiFraudAuditId(), historyDto.getAntiFraudAuditId()),
                () -> assertEquals(historyEntity.getAuthorizationAuditId(), historyDto.getAuthorizationAuditId()),
                () -> assertEquals(historyEntity.getAccountAuditId(), historyDto.getAccountAuditId()),
                () -> assertEquals(historyEntity.getTransferAuditId(), historyDto.getTransferAuditId())
        );
    }

    @Test
    @DisplayName("обновление entity значениями dto = null")
    void mergeToEntityNegativeTest() {
        HistoryEntity historyEntity = new HistoryEntity();

        assertEquals(historyEntity, historyMapper.mergeToEntity(null, historyEntity));
    }

    @Test
    @DisplayName("обновление entity значениями dto")
    void mergeToEntityPositiveTest() {
        HistoryEntity historyEntity = new HistoryEntity(
                1L,
                2L,
                3L,
                4L,
                5L,
                6L,
                7L
        );
        HistoryDto historyDto = new HistoryDto(
                7L,
                6L,
                5L,
                4L,
                3L,
                2L,
                1L
        );

        HistoryEntity updatedHistoryEntity = historyMapper.mergeToEntity(historyDto, historyEntity);
        updatedHistoryEntity.setId(historyDto.getId());

        assertAll(
                () -> assertEquals(historyDto.getId(), updatedHistoryEntity.getId()),
                () -> assertEquals(historyDto.getPublicBankInfoAuditId(), updatedHistoryEntity.getPublicBankInfoAuditId()),
                () -> assertEquals(historyDto.getProfileAuditId(), updatedHistoryEntity.getProfileAuditId()),
                () -> assertEquals(historyDto.getAntiFraudAuditId(), updatedHistoryEntity.getAntiFraudAuditId()),
                () -> assertEquals(historyDto.getAuthorizationAuditId(), updatedHistoryEntity.getAuthorizationAuditId()),
                () -> assertEquals(historyDto.getAccountAuditId(), updatedHistoryEntity.getAccountAuditId()),
                () -> assertEquals(historyDto.getTransferAuditId(), updatedHistoryEntity.getTransferAuditId())
        );
    }

    @Test
    @DisplayName("преобразование списка entity = null в список dto")
    void toListDtoNegativeTest() {

        assertNull(historyMapper.toListDto(null));

    }

    @Test
    @DisplayName("преобразование списка entity в список dto")
    void toListDtoPositiveTest() {

        HistoryEntity historyEntityOne = new HistoryEntity(
                1L,
                2L,
                3L,
                4L,
                5L,
                6L,
                7L
        );
        HistoryEntity historyEntityTwo = new HistoryEntity(
                2L,
                3L,
                4L,
                5L,
                6L,
                7L,
                8L
        );
        List<HistoryEntity> historyEntityList = Arrays.asList(historyEntityOne, historyEntityTwo);

        List<HistoryDto> dtoList = historyMapper.toListDto(historyEntityList);

        assertAll(
                () -> assertEquals(historyEntityList.size(), dtoList.size()),
                () -> assertEquals(historyEntityOne.getId(), dtoList.get(0).getId()),
                () -> assertEquals(historyEntityTwo.getId(), dtoList.get(1).getId())
        );
    }
}
