package com.codepractice.springbootinterview.service;

import com.codepractice.springbootinterview.dto.HouseRequest;
import com.codepractice.springbootinterview.exception.HouseNotFoundException;
import com.codepractice.springbootinterview.model.House;
import com.codepractice.springbootinterview.repository.HouseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HouseService {

    private static final Logger logger = LoggerFactory.getLogger(HouseService.class);

    @Autowired
    private HouseRepository houseRepository;
    public House saveHouse(HouseRequest houseRequest) {
        logger.info("Entered into the saveHouse Method in HouseService");
        House house = new House();
        BeanUtils.copyProperties(houseRequest, house);
        return houseRepository.save(house);
    }

    public House getHouseDetails(Long houseId) throws HouseNotFoundException {
        logger.info("Entered into the getHouseDetails Method in HouseService");
        House house = houseRepository.findByHouseId(houseId);
        if (house !=null) {
            return house;
        } else {
            throw new HouseNotFoundException("House not found with id: "+ houseId);
        }
    }

    public List<House> getAllHouses() {
        logger.info("Entered into the getAllHouses Method in HouseService");

        List<House> houseList = houseRepository.findAll();
        List<House> houseList1 = houseList.stream().sorted(Comparator.comparingLong(House::getHouseId).reversed()).collect(Collectors.toList());
        return houseList1;
    }

    public Map<String, String> deleteHouse(Long houseId) throws HouseNotFoundException {
        logger.info("Entered into the deleteHouse Method in HouseService");
        House house = houseRepository.findByHouseId(houseId);
        if (house !=null) {
            houseRepository.deleteById(houseId);
            Map<String, String> messageMap = new HashMap<>();
            messageMap.put("message", "House Deleted Successfully");
            return messageMap;
        } else {
            throw new HouseNotFoundException("House not found with id: "+ houseId);
        }
    }

    public House updateHouseDetails(HouseRequest houseRequest, Long houseId) throws HouseNotFoundException {
        logger.info("Entered into the updateHouseDetails Method in HouseService");
        House house = houseRepository.findByHouseId(houseId);
        if (house !=null) {
            House houseDetails = new House();
            BeanUtils.copyProperties(houseRequest, houseDetails);
            houseDetails.setHouseId(houseId);
            return houseRepository.save(houseDetails);
        } else {
            throw new HouseNotFoundException("House not found with id: "+ houseId);
        }
    }
}
