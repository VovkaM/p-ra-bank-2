package com.bank.history.service;

import com.bank.history.dto.HistoryDto;
import com.bank.history.entity.HistoryEntity;
import com.bank.history.mapper.HistoryMapper;
import com.bank.history.repository.HistoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HistoryServiceImplTest {

    private static final Long ID_1 = 1L;
    private  static final Long ID_2 = 2L;

    @Mock
    private HistoryRepository historyRepository;

    @Mock
    private HistoryMapper historyMapper;

    @InjectMocks
    private HistoryServiceImpl historyService;

    @Test
    @DisplayName("чтение по id, позитивный сценарий")
    void readByIdPositiveTest() {
        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setId(ID_1);
        HistoryDto historyDto = new HistoryDto();
        historyDto.setId(ID_1);

        when(historyRepository.findById(ID_1)).thenReturn(Optional.of(historyEntity));
        when(historyMapper.toDto(historyEntity)).thenReturn(historyDto);

        assertAll(
                () -> assertNotNull(historyService.readById(ID_1)),
                () -> assertEquals(historyDto, historyService.readById(ID_1))
        );
    }

    @Test
    @DisplayName("чтение по id, негативный сценарий")
    void readByIdNegativeTest() {
        when(historyRepository.findById(ID_1)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> historyService.readById(ID_1));

        assertEquals("история по указанному id не найдена", exception.getMessage());
        verify(historyRepository, times(1)).findById(ID_1);
    }

    @Test
    @DisplayName("чтение по списку id, позитивный сценарий")
    void readAllByIdPositiveTest() {
        List<Long> listId = Arrays.asList(ID_1, ID_2);
        List<HistoryEntity> historyEntities = new ArrayList<>();
        historyEntities.add(new HistoryEntity());
        historyEntities.add(new HistoryEntity());
        List<HistoryDto> listDto = Arrays.asList(new HistoryDto(), new HistoryDto());

        when(historyRepository.findAllById(listId)).thenReturn(historyEntities);
        when(historyMapper.toListDto(historyEntities)).thenReturn(listDto);

        assertEquals(listDto, historyService.readAllById(listId));
        verify(historyRepository, times(1)).findAllById(listId);
        verify(historyMapper, times(1)).toListDto(historyEntities);
    }

    @Test
    @DisplayName("чтение по списку id, негативный сценарий")
    void readAllByIdNegativeTest() {
        List<Long> listId = Arrays.asList(ID_1, ID_2);
        List<HistoryEntity> historyEntities = new ArrayList<>();

        when(historyRepository.findAllById(listId)).thenReturn(historyEntities);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> historyService.readAllById(listId));

        assertEquals("истории по указанным id не найдены", exception.getMessage());
        verify(historyRepository, times(1)).findAllById(listId);
    }

    @Test
    @DisplayName("создание истории, позитивный сценарий")
    void createPositiveTest() {
        HistoryDto historyDto = new HistoryDto();
        HistoryEntity historyEntity = new HistoryEntity();

        when(historyRepository.save(historyEntity)).thenReturn(historyEntity);
        when(historyMapper.toDto(historyEntity)).thenReturn(historyDto);
        when(historyMapper.toEntity(historyDto)).thenReturn(historyEntity);

        assertEquals(historyDto, historyService.create(historyDto));
        verify(historyRepository, times(1)).save(historyEntity);
        verify(historyMapper, times(1)).toEntity(historyDto);
        verify(historyMapper, times(1)).toDto(historyEntity);
    }

    @Test
    @DisplayName("изменение истории, позитивный сценарий")
    void updatePositiveTest() {
        HistoryDto historyDto = new HistoryDto();
        historyDto.setId(ID_1);

        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setId(ID_1);

        when(historyRepository.findById(ID_1)).thenReturn(Optional.of(historyEntity));
        when(historyMapper.mergeToEntity(historyDto, historyEntity)).thenReturn(historyEntity);
        when(historyRepository.save(historyEntity)).thenReturn(historyEntity);
        when(historyMapper.toDto(historyEntity)).thenReturn(historyDto);

        assertEquals(historyDto, historyService.update(ID_1, historyDto));
        verify(historyRepository, times(1)).findById(ID_1);
        verify(historyMapper, times(1)).mergeToEntity(historyDto, historyEntity);
        verify(historyRepository, times(1)).save(historyEntity);
        verify(historyMapper, times(1)).toDto(historyEntity);
    }
}
