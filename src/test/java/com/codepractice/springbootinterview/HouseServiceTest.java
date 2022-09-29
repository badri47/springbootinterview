package com.codepractice.springbootinterview;


import com.codepractice.springbootinterview.dto.HouseRequest;
import com.codepractice.springbootinterview.exception.HouseNotFoundException;
import com.codepractice.springbootinterview.model.House;
import com.codepractice.springbootinterview.repository.HouseRepository;
import com.codepractice.springbootinterview.service.HouseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class HouseServiceTest {

    @InjectMocks
    private HouseService houseService;

    @Mock
    private HouseRepository houseRepository;

    @Test
    public void testSaveHouseSuccess() {
        HouseRequest houseRequest =  new HouseRequest();
        houseRequest.setHouseName("test");
        houseRequest.setHouseAddress("test Address");
        houseRequest.setHousePinCode("12345");
        houseRequest.setHouseCity("test City");

        House house = new House();
        house.setHouseName("test");
        house.setHouseAddress("test Address");
        house.setHousePinCode("12345");
        house.setHouseCity("test City");

        Mockito.when(houseRepository.save(house)).thenReturn(house);

        House house1 = houseService.saveHouse(houseRequest);

        assertThat(house1.getHouseName()).isEqualTo("test");
    }

    @Test
    public void testGetAllHouses() {
        House house = new House(1L, "test", "test Address", "45678", "test city");
        House house1 = new House(2L, "test 1", "test Address 1", "45658", "test city 1");

        List<House> houseList = Arrays.asList(house1, house);
        Mockito.when(houseRepository.findAll()).thenReturn(houseList);
        List<House> houseList1 = houseService.getAllHouses();
        assertThat(houseList1.size()).isEqualTo(2);
    }

    @Test
    public void testGetHouseDetails() throws HouseNotFoundException {
        House house = new House(1L, "test", "test Address", "45678", "test city");

        Mockito.when(houseRepository.findByHouseId(1L)).thenReturn(house);
        House house1 = houseService.getHouseDetails(1L);

        assertThat(house1.getHouseId()).isEqualTo(1L);
    }

    @Test
    public void testGetHouseDetailsException() throws HouseNotFoundException {
        Assertions.assertThrows(HouseNotFoundException.class, () -> {
            houseService.getHouseDetails(1L);
        });
    }


    @Test
    public void testDeleteHouse() throws HouseNotFoundException {
        House house = new House(1L, "test", "test Address", "45678", "test city");

        Mockito.when(houseRepository.findByHouseId(1L)).thenReturn(house);
        Map<String, String> messageMap = houseService.deleteHouse(1L);

        assertThat(messageMap.get("message")).isEqualTo("House Deleted Successfully");
    }

    @Test
    public void testDeleteHouseException() throws HouseNotFoundException {
        Assertions.assertThrows(HouseNotFoundException.class, () -> {
            Map<String, String> messageMap = houseService.deleteHouse(1L);
        });
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

        Mockito.when(houseRepository.findByHouseId(1L)).thenReturn(house);
        Mockito.when(houseRepository.save(house)).thenReturn(house);

        House house1 = houseService.updateHouseDetails(houseRequest, 1L);

        assertThat(house1.getHouseName()).isEqualTo("test");
    }

    @Test
    public void testUpdateHouseDetailsException() throws HouseNotFoundException {
        HouseRequest houseRequest =  new HouseRequest();
        houseRequest.setHouseName("test");
        houseRequest.setHouseAddress("test Address");
        houseRequest.setHousePinCode("12345");
        houseRequest.setHouseCity("test City");

        Assertions.assertThrows(HouseNotFoundException.class, () -> {
            House house = houseService.updateHouseDetails(houseRequest,1L);
        });
    }
}
