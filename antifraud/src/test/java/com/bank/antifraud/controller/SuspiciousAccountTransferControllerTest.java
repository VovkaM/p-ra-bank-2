package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousAccountTransferDto;
import com.bank.antifraud.service.SuspiciousAccountTransferService;
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
@WebMvcTest(controllers = SuspiciousAccountTransferController.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@MockitoSettings(strictness = Strictness.LENIENT)
public class SuspiciousAccountTransferControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Mock
    private SuspiciousAccountTransferService service;

    @InjectMocks
    private SuspiciousAccountTransferController controller;
    @Mock
    private GlobalRestExceptionHandler exceptionReturner;
    private final long id = 1L;

    @Test
    @DisplayName("Создание Dto, позитивный сценарий")
    void createPositiveTest() {
        final SuspiciousAccountTransferDto suspiciousAccountTransferDto = new SuspiciousAccountTransferDto();
        when(service.save(suspiciousAccountTransferDto)).thenReturn(suspiciousAccountTransferDto);

        final ResponseEntity<SuspiciousAccountTransferDto> responseEntity = controller.
                create(suspiciousAccountTransferDto
                );
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Создание, негативный сценарий")
    void createNegativeTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/suspicious/account/transfer/create")).
                andDo(print()).andExpect(status().isNotFound()).andExpect(exception -> exception.getHandler().
                        getClass().equals(NotFoundException.class)).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(response, "");
    }

    @Test
    @DisplayName("Обновление по id, позитивный сценарий")
    void updateByIdPositiveTest() {

        final SuspiciousAccountTransferDto suspiciousAccountTransferDto = new SuspiciousAccountTransferDto();
        when(service.update(id, suspiciousAccountTransferDto)).thenReturn(suspiciousAccountTransferDto);

        final ResponseEntity<SuspiciousAccountTransferDto> responseEntity = controller.
                update(suspiciousAccountTransferDto, id
                );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Обновление, не переданы параметры, негативный сценарий")
    void updateByIdNegativeTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(put("/suspicious/account/transfer/" + id)).
                andDo(print()).andExpect(status().isNotFound()).andExpect(exception -> exception.getHandler().
                        getClass().equals(NotFoundException.class)).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(response, "");
    }

    @Test
    @DisplayName("Чтение Dto по id, позитивный сценарий")
    void readByIdPositiveTest() {

        final SuspiciousAccountTransferDto suspiciousAccountTransferDto = new SuspiciousAccountTransferDto();
        when(service.findById(id)).thenReturn(suspiciousAccountTransferDto);

        final ResponseEntity<SuspiciousAccountTransferDto> responseEntity = controller.read(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Чтение Dto по id, не переданы параметры, негативный сценарий")
    void readByIdNegativeTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/suspicious/account/transfer/" + id))
                .andDo(print()).andExpect(status().isNotFound()).andExpect(exception -> exception.getHandler().
                        getClass().equals(NotFoundException.class)).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(response, "");
    }


    @Test
    @DisplayName("Чтение Dto по List id, позитивный сценарий")
    void readAllByIdListPositiveTest() {
        final List<Long> idsList = Arrays.asList(1L, 2L, 3L);
        final List<SuspiciousAccountTransferDto> suspiciousAccountTransferDtoList = Arrays.asList(
                new SuspiciousAccountTransferDto(), new SuspiciousAccountTransferDto(),
                new SuspiciousAccountTransferDto());
        when(service.findAllById(idsList)).thenReturn(suspiciousAccountTransferDtoList);

        final ResponseEntity<List<SuspiciousAccountTransferDto>> listResponseEntity = controller.readAll(idsList);


        assertEquals(HttpStatus.OK, listResponseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Чтение Dto по List id, не переданы параметры, негативный сценарий")
    void readAllByIdListNegativeTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/suspicious/account/transfer/")).
                andDo(print()).andExpect(status().isNotFound()).andExpect(exception -> exception.getHandler().
                        getClass().equals(NotFoundException.class)).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(response, "");
    }
}


