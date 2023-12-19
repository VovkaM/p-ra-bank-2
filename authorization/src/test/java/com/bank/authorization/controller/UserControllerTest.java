package com.bank.authorization.controller;

import com.bank.authorization.dto.UserDto;
import com.bank.authorization.service.UserService;
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

@ContextConfiguration(classes = {GlobalRestExceptionHandler.class})
@WebMvcTest(controllers = UserController.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Mock
    private UserService service;

    @InjectMocks
    private UserController controller;

    @Mock
    private GlobalRestExceptionHandler exceptionReturner;

    private final long id = 1L;

    @Test
    @DisplayName("Создание UserDto, позитивный сценарий")
    void createUserPositiveTest() {
        final UserDto userDto = new UserDto();
        when(service.save(userDto)).thenReturn(userDto);

        final ResponseEntity<UserDto> responseEntity = controller.create(userDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Создание UserDto, негативный сценарий")
    void createNegativeTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/create")).
                andDo(print()).andExpect(status().isNotFound()).andExpect(exception -> exception.getHandler().
                        getClass().equals(NotFoundException.class)).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(response, "");
    }

    @Test
    @DisplayName("Чтение UserDto по id, позитивный сценарий")
    void readByIdPositiveTest() {
        final UserDto userDto = new UserDto();
        when(service.findById(id)).thenReturn(userDto);

        final ResponseEntity<UserDto> responseEntity = controller.read(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Чтение UserDto по id, не переданы параметры, негативный сценарий")
    void readByIdNegativeTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/read/" + id))
                .andDo(print()).andExpect(status().isNotFound()).andExpect(exception -> exception.getHandler().
                        getClass().equals(NotFoundException.class)).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(response, "");
    }

    @Test
    @DisplayName("Редактирование UserDto по id, позитивный сценарий")
    void updateUserByIdPositiveTest() {
        final UserDto userDto = new UserDto();
        when(service.update(id, userDto)).thenReturn(userDto);

        final ResponseEntity<UserDto> responseEntity = controller.update(id, userDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    @DisplayName("Редактирование UserDto, не переданы параметры, негативный сценарий")
    void updateByIdNegativeTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(put("/update" + id)).
                andDo(print()).andExpect(status().isNotFound()).andExpect(exception -> exception.getHandler().
                        getClass().equals(NotFoundException.class)).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(response, "");
    }

    @Test
    @DisplayName("Чтение UserDto по List id, позитивный сценарий")
    void readAllByIdListPositiveTest() {
        final List<Long> ids = Arrays.asList(1L, 2L, 3L);
        final List<UserDto> UserDtoList = Arrays.asList(new UserDto(), new UserDto(), new UserDto());
        when(service.findAllByIds(ids)).thenReturn(UserDtoList);

        final ResponseEntity<List<UserDto>> listResponseEntity = controller.readAll(ids);

        assertEquals(HttpStatus.OK, listResponseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Чтение UserDto по List id, не переданы параметры, негативный сценарий")
    void readAllByIdListNegativeTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/read/all")).
                andDo(print()).andExpect(status().isNotFound()).andExpect(exception -> exception.getHandler().
                        getClass().equals(NotFoundException.class)).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(response, "");
    }
}
