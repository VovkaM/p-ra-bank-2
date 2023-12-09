package com.bank.history.controller;

import com.bank.history.dto.HistoryDto;
import com.bank.history.service.HistoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HistoryControllerTest {

    @Mock
    private HistoryService historyService;

    @InjectMocks
    private HistoryController historyController;

    @Test
    @DisplayName("чтение по id, позитивный сценарий")
    void readByIdPositiveTest() {

        Long id = 1L;
        HistoryDto historyDto = new HistoryDto();
        historyDto.setId(id);

        when(historyService.readById(id)).thenReturn(historyDto);
        ResponseEntity<HistoryDto> response = historyController.read(id);

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(historyDto, response.getBody())
        );
    }

    @Test
    @DisplayName("чтение по списку id, позитивный сценарий")
    void readAllByListIdPositiveTest() {

        List<Long> listId = new ArrayList<>();
        listId.add(1L);
        listId.add(2L);
        List<HistoryDto> historyDtoList = new ArrayList<>();

        when(historyService.readAllById(listId)).thenReturn(historyDtoList);
        ResponseEntity<List<HistoryDto>> response = historyController.readAll(listId);

        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(historyDtoList, response.getBody())
        );
    }

    @Test
    @DisplayName("создание истории, позитивный сценарий")
    void createPositiveTest() {

        HistoryDto historyDto = new HistoryDto();

        when(historyService.create(historyDto)).thenReturn(historyDto);
        ResponseEntity<HistoryDto> response = historyController.create(historyDto);

        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(historyDto, response.getBody())
        );
    }

    @Test
    @DisplayName("обновление истории, позитивный сценарий")
    void updatePositiveTest() {

        Long id = 1L;
        HistoryDto updatedHistoryDto = new HistoryDto();
        updatedHistoryDto.setId(id);

        when(historyService.update(id, updatedHistoryDto)).thenReturn(updatedHistoryDto);
        ResponseEntity<HistoryDto> response = historyController.update(id, updatedHistoryDto);

        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(id, response.getBody().getId())
        );
    }
}
