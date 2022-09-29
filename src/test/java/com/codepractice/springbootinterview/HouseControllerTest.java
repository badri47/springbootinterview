package com.codepractice.springbootinterview;


import com.codepractice.springbootinterview.controller.HouseController;
import com.codepractice.springbootinterview.dto.HouseRequest;
import com.codepractice.springbootinterview.exception.HouseNotFoundException;
import com.codepractice.springbootinterview.model.House;
import com.codepractice.springbootinterview.service.HouseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class HouseControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private HouseController houseController;

    @Mock
    private HouseService houseService;

    @Test
    public void testSaveHouseSuccess() {
        HouseRequest houseRequest =  new HouseRequest();
        houseRequest.setHouseName("test");
        houseRequest.setHouseAddress("test Address");
        houseRequest.setHousePinCode("12345");
        houseRequest.setHouseCity("test City");

        House house = new House();
        house.setHouseId(1L);
        house.setHouseName("test");
        house.setHouseAddress("test Address");
        house.setHousePinCode("12345");
        house.setHouseCity("test City");
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.when(houseService.saveHouse(houseRequest)).thenReturn(house);

        ResponseEntity<House> responseEntity = houseController.saveHouse(houseRequest);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    }

    @Test
    public void testGetAllHouses() {
        House house = new House(1L, "test", "test Address", "45678", "test city");
        House house1 = new House(2L, "test 1", "test Address 1", "45658", "test city 1");

        List<House> houseList = Arrays.asList(house1, house);
        Mockito.when(houseService.getAllHouses()).thenReturn(houseList);

        ResponseEntity<List<House>> houseListData = houseController.getAllHouses();

        assertThat(houseListData.getBody().size()).isEqualTo(2);

    }

    @Test
    public void testGetHouseDetails() throws HouseNotFoundException {
        House house = new House(1L, "test", "test Address", "45678", "test city");

        Mockito.when(houseService.getHouseDetails(1L)).thenReturn(house);

        ResponseEntity<House> houseDetails = houseController.getHouseDetails(1L);

        assertThat(houseDetails.getBody().getHouseName()).isEqualTo("test");
    }

    @Test
    public void testDeleteHouse() throws HouseNotFoundException {
        House house = new House(1L, "test", "test Address", "45678", "test city");

        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("message", "House Deleted Successfully");
        Mockito.when(houseService.deleteHouse(1L)).thenReturn(messageMap);

        ResponseEntity<Map<String, String>> messageMap1 = houseController.deleteHouse(1L);
        assertThat(messageMap1.getBody().get("message")).isEqualTo("House Deleted Successfully");
    }

    @Test
    public void testUpdateHouseDetails() throws HouseNotFoundException {
        HouseRequest houseRequest =  new HouseRequest();
        houseRequest.setHouseName("test");
        houseRequest.setHouseAddress("test Address");
        houseRequest.setHousePinCode("12345");
        houseRequest.setHouseCity("test City");

        House house = new House();
        house.setHouseId(1L);
        house.setHouseName("test");
        house.setHouseAddress("test Address");
        house.setHousePinCode("12345");
        house.setHouseCity("test City");
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.when(houseService.updateHouseDetails(houseRequest, 1L)).thenReturn(house);

        ResponseEntity<House> responseEntity = houseController.updateHouseDetails(houseRequest, 1L);

        assertThat(responseEntity.getBody().getHouseName()).isEqualTo("test");
    }
}
