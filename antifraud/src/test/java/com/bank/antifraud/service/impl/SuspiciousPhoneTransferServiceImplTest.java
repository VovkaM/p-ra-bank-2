package com.bank.antifraud.service.impl;

import com.bank.antifraud.controller.SuspiciousPhoneTransferController;
import com.bank.antifraud.dto.SuspiciousPhoneTransferDto;
import com.bank.antifraud.service.SuspiciousPhoneTransferService;
import com.bank.common.handler.GlobalRestExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.webjars.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {GlobalRestExceptionHandler.class})
@WebMvcTest(controllers = SuspiciousPhoneTransferController.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@MockitoSettings(strictness = Strictness.LENIENT)
public class SuspiciousPhoneTransferServiceImplTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Mock
    private SuspiciousPhoneTransferService service;

    @InjectMocks
    private SuspiciousPhoneTransferController controller;
    @Mock
    private GlobalRestExceptionHandler exceptionReturner;
    private final long id = 1L;

    @Test
    @DisplayName("Создание Dto, позитивный сценарий")
    void createPositiveTest() {
        final SuspiciousPhoneTransferDto suspiciousPhoneTransferDto = new SuspiciousPhoneTransferDto();
        when(service.save(suspiciousPhoneTransferDto)).thenReturn(suspiciousPhoneTransferDto);

        final ResponseEntity<SuspiciousPhoneTransferDto> responseEntity =
                controller.create(suspiciousPhoneTransferDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Создание Dto, не переданы параметры, негативный сценарий")
    void createNegativeTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/suspicious/phone/transfer/create"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(exception -> exception.getHandler()
                        .getClass().equals(NotFoundException.class))
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(response, "");
    }

    @Test
    @DisplayName("Обновление по id, позитивный сценарий")
    void updateByIdPositiveTest() {

        final SuspiciousPhoneTransferDto suspiciousPhoneTransferDto = new SuspiciousPhoneTransferDto();
        when(service.update(id, suspiciousPhoneTransferDto)).thenReturn(suspiciousPhoneTransferDto);

        final ResponseEntity<SuspiciousPhoneTransferDto> responseEntity =
                controller.update(suspiciousPhoneTransferDto, id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Обновление по id, не переданы параметры, негативный сценарий")
    void updateByIdNegativeTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(put("/suspicious/phone/transfer/" + id))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(exception -> exception.getHandler()
                        .getClass().equals(NotFoundException.class))
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(response, "");
    }

    @Test
    @DisplayName("Чтение Dto по id, позитивный сценарий")
    void readByIdPositiveTest() {

        final SuspiciousPhoneTransferDto suspiciousPhoneTransferDto = new SuspiciousPhoneTransferDto();
        when(service.findById(id)).thenReturn(suspiciousPhoneTransferDto);

        final ResponseEntity<SuspiciousPhoneTransferDto> responseEntity = controller.read(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());


    }

    @Test
    @DisplayName("Чтение Dto по id, не переданы параметры, негативный сценарий")
    void readByIdNegativeTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/suspicious/phone/transfer/" + id))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(exception -> exception.getHandler()
                        .getClass().equals(NotFoundException.class))
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(response, "");
    }


    @Test
    @DisplayName("Чтение Dto по List id, позитивный сценарий")
    void readAllByIdListPositiveTest() {
        final List<Long> idsList = Arrays.asList(1L, 2L, 3L);
        final List<SuspiciousPhoneTransferDto> suspiciousPhoneTransferDtoList =
                Arrays.asList(new SuspiciousPhoneTransferDto(),
                        new SuspiciousPhoneTransferDto(),
                        new SuspiciousPhoneTransferDto());
        when(service.findAllById(idsList)).thenReturn(suspiciousPhoneTransferDtoList);

        final ResponseEntity<List<SuspiciousPhoneTransferDto>> listResponseEntity = controller.readAll(idsList);


        assertEquals(HttpStatus.OK, listResponseEntity.getStatusCode());


    }

    @Test
    @DisplayName("Чтение по списку id, не переданы параметры, негативный сценарий")
    void readAllByIdListNegativeTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/suspicious/account/transfer/"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(exception -> exception.getHandler()
                        .getClass().equals(NotFoundException.class))
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(response, "");
    }
}
