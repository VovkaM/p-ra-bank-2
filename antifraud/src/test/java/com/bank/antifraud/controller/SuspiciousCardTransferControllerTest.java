package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousCardTransferDto;
import com.bank.antifraud.service.SuspiciousCardTransferService;
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
@WebMvcTest(controllers = SuspiciousCardTransferController.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@MockitoSettings(strictness = Strictness.LENIENT)
public class SuspiciousCardTransferControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Mock
    private SuspiciousCardTransferService service;

    @InjectMocks
    private SuspiciousCardTransferController controller;
    @Mock
    private GlobalRestExceptionHandler exceptionReturner;

    private final long id = 1L;

    @Test
    @DisplayName("Создание Dto, позитивный сценарий")
    void createPositiveTest() {
        final SuspiciousCardTransferDto suspiciousCardTransferDto = new SuspiciousCardTransferDto();
        when(service.save(suspiciousCardTransferDto)).thenReturn(suspiciousCardTransferDto);

        final ResponseEntity<SuspiciousCardTransferDto> responseEntity =
                controller.create(suspiciousCardTransferDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Создание, не переданы параметры, негативный сценарий")
    void createNegativeTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/suspicious/card/transfer/create"))
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

        final SuspiciousCardTransferDto suspiciousCardTransferDto = new SuspiciousCardTransferDto();
        when(service.update(id, suspiciousCardTransferDto)).thenReturn(suspiciousCardTransferDto);

        final ResponseEntity<SuspiciousCardTransferDto> responseEntity =
                controller.update(suspiciousCardTransferDto, id);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Обновление по id, не переданы параметры, негативный сценарий")
    void updateByIdNegativeTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(put("/suspicious/card/transfer/" + id))
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

        final SuspiciousCardTransferDto suspiciousCardTransferDto = new SuspiciousCardTransferDto();
        when(service.findById(id)).thenReturn(suspiciousCardTransferDto);

        final ResponseEntity<SuspiciousCardTransferDto> responseEntity = controller.read(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Чтение Dto по id, не переданы параметры, негативный сценарий")
    void readByIdNegativeTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/suspicious/card/transfer/" + id))
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
        final List<SuspiciousCardTransferDto> suspiciousCardTransferDtoList =
                Arrays.asList(new SuspiciousCardTransferDto(),
                        new SuspiciousCardTransferDto(),
                        new SuspiciousCardTransferDto());
        when(service.findAllById(idsList)).thenReturn(suspiciousCardTransferDtoList);

        final ResponseEntity<List<SuspiciousCardTransferDto>> listResponseEntity = controller.readAll(idsList);

        assertEquals(HttpStatus.OK, listResponseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Чтение Dto по List id,, не переданы параметры, негативный сценарий")
    void readAllByIdListNegativeTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/suspicious/card/transfer/"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(exception -> exception.getHandler()
                        .getClass().equals(NotFoundException.class))
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(response, "");
    }
}
