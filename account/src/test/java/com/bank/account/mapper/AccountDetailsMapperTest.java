package com.bank.account.mapper;

import com.bank.account.dto.AccountDetailsDto;
import com.bank.account.entity.AccountDetailsEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class AccountDetailsMapperTest {

    @InjectMocks
    private AccountDetailsMapperImpl mapper;

    @Test
    @DisplayName("мапинг в энтити, на вход подан null")
    void noDtoNullTest() {
        final AccountDetailsEntity result = mapper.toEntity(null);

        assertNull(result);
    }

    @Test
    @DisplayName("мапинг в энтити")
    void toDtoTest() {
        final AccountDetailsDto accountDetailsDto = new AccountDetailsDto();
        accountDetailsDto.setPassportId(1234L);
        accountDetailsDto.setAccountNumber(9876L);
        accountDetailsDto.setBankDetailsId(1L);
        accountDetailsDto.setMoney(new BigDecimal(1000));
        accountDetailsDto.setNegativeBalance(false);
        accountDetailsDto.setProfileId(1L);

        final AccountDetailsEntity accountDetailsEntity = mapper.toEntity(accountDetailsDto);

        assertAll(
                () -> assertEquals(accountDetailsDto.getPassportId(), accountDetailsEntity.getPassportId()),
                () -> assertEquals(accountDetailsDto.getAccountNumber(), accountDetailsEntity.getAccountNumber()),
                () -> assertEquals(accountDetailsDto.getBankDetailsId(), accountDetailsEntity.getBankDetailsId()),
                () -> assertEquals(accountDetailsDto.getMoney(), accountDetailsEntity.getMoney()),
                () -> assertEquals(accountDetailsDto.getNegativeBalance(), accountDetailsEntity.getNegativeBalance()),
                () -> assertEquals(accountDetailsDto.getProfileId(), accountDetailsEntity.getProfileId())
        );
    }


    @Test
    @DisplayName("маппинг в дто, на вход подан null")
    void noEntityNullTest() {
        final AccountDetailsDto result = mapper.toDto(null);

        assertNull(result);
    }

    @Test
    @DisplayName("маппинг в дто")
    void toEntityTest() {
        final AccountDetailsEntity accountDetailsEntity = new AccountDetailsEntity();
        accountDetailsEntity.setId(1L);
        accountDetailsEntity.setPassportId(1234L);
        accountDetailsEntity.setAccountNumber(9876L);
        accountDetailsEntity.setBankDetailsId(1L);
        accountDetailsEntity.setMoney(new BigDecimal(1000));
        accountDetailsEntity.setNegativeBalance(false);
        accountDetailsEntity.setProfileId(1L);

        final AccountDetailsDto accountDetailsDto = mapper.toDto(accountDetailsEntity);

        assertAll(
                () -> assertEquals(accountDetailsEntity.getId(), accountDetailsDto.getId()),
                () -> assertEquals(accountDetailsEntity.getPassportId(), accountDetailsDto.getPassportId()),
                () -> assertEquals(accountDetailsEntity.getAccountNumber(), accountDetailsDto.getAccountNumber()),
                () -> assertEquals(accountDetailsEntity.getBankDetailsId(), accountDetailsDto.getBankDetailsId()),
                () -> assertEquals(accountDetailsEntity.getMoney(), accountDetailsDto.getMoney()),
                () -> assertEquals(accountDetailsEntity.getNegativeBalance(), accountDetailsDto.getNegativeBalance()),
                () -> assertEquals(accountDetailsEntity.getProfileId(), accountDetailsDto.getProfileId())
        );
    }

    @Test
    @DisplayName("маппинг лист энтити в лист дто, на вход подан null")
    void noEntityListNullTest() {
        final List<AccountDetailsDto> dtoList = mapper.toDtoList(null);

        assertNull(dtoList);
    }

    @Test
    @DisplayName("маппинг лист энтити в лист дто")
    void toEntityListTest() {
        AccountDetailsEntity accountDetailsEntity1 = new AccountDetailsEntity();
        accountDetailsEntity1.setId(1L);
        accountDetailsEntity1.setPassportId(1234L);
        accountDetailsEntity1.setAccountNumber(9876L);
        accountDetailsEntity1.setBankDetailsId(1L);
        accountDetailsEntity1.setMoney(new BigDecimal(1000));
        accountDetailsEntity1.setNegativeBalance(false);
        accountDetailsEntity1.setProfileId(1L);

        AccountDetailsEntity accountDetailsEntity2 = new AccountDetailsEntity();
        accountDetailsEntity2.setId(2L);
        accountDetailsEntity2.setPassportId(9876L);
        accountDetailsEntity2.setAccountNumber(1234L);
        accountDetailsEntity2.setBankDetailsId(2L);
        accountDetailsEntity2.setMoney(new BigDecimal(1000));
        accountDetailsEntity2.setNegativeBalance(true);
        accountDetailsEntity2.setProfileId(2L);

        final List<AccountDetailsEntity> entityList = Arrays.asList(accountDetailsEntity1, accountDetailsEntity2);

        final List<AccountDetailsDto> dtoList = mapper.toDtoList(entityList);

        assertAll(
                () -> assertEquals(entityList.size(), dtoList.size()),
                () -> assertEquals(accountDetailsEntity1.getId(), dtoList.get(0).getId()),
                () -> assertEquals(accountDetailsEntity2.getId(), dtoList.get(1).getId()),
                () -> assertEquals(accountDetailsEntity1.getPassportId(), dtoList.get(0).getPassportId()),
                () -> assertEquals(accountDetailsEntity2.getPassportId(), dtoList.get(1).getPassportId())
        );
    }

    @Test
    @DisplayName("слияние в энтити, на вход подан null")
    void mergeToEntityNullTest() {
        final AccountDetailsEntity accountDetailsEntity = new AccountDetailsEntity();
        final AccountDetailsEntity mergedAccountDetailsEntity = mapper.mergeToEntity(accountDetailsEntity, null);
        assertEquals(accountDetailsEntity, mergedAccountDetailsEntity);

    }

    @Test
    @DisplayName("слияние в энтити")
    void mergeToEntityTest() {
        final AccountDetailsEntity accountDetailsEntity = new AccountDetailsEntity();
        accountDetailsEntity.setPassportId(1234L);
        accountDetailsEntity.setAccountNumber(9876L);
        accountDetailsEntity.setBankDetailsId(1L);
        accountDetailsEntity.setMoney(new BigDecimal(1000));
        accountDetailsEntity.setNegativeBalance(false);
        accountDetailsEntity.setProfileId(1L);

        final AccountDetailsDto accountDetailsDto = new AccountDetailsDto();
        accountDetailsDto.setPassportId(9876L);
        accountDetailsDto.setAccountNumber(1234L);
        accountDetailsDto.setBankDetailsId(2L);
        accountDetailsDto.setMoney(new BigDecimal(1000));
        accountDetailsDto.setNegativeBalance(true);
        accountDetailsDto.setProfileId(2L);

        final AccountDetailsEntity mergedAccountDetailsEntity = mapper.mergeToEntity(accountDetailsEntity, accountDetailsDto);


        assertAll(
                () -> assertEquals(accountDetailsDto.getPassportId(), mergedAccountDetailsEntity.getPassportId()),
                () -> assertEquals(accountDetailsDto.getAccountNumber(), mergedAccountDetailsEntity.getAccountNumber()),
                () -> assertEquals(accountDetailsDto.getBankDetailsId(), mergedAccountDetailsEntity.getBankDetailsId()),
                () -> assertEquals(accountDetailsDto.getMoney(), mergedAccountDetailsEntity.getMoney()),
                () -> assertEquals(accountDetailsDto.getNegativeBalance(), mergedAccountDetailsEntity.getNegativeBalance()),
                () -> assertEquals(accountDetailsDto.getProfileId(), mergedAccountDetailsEntity.getProfileId())
        );
    }
}