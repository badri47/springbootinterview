package com.codepractice.springbootinterview.controller;

import com.codepractice.springbootinterview.dto.HouseRequest;
import com.codepractice.springbootinterview.exception.HouseNotFoundException;
import com.codepractice.springbootinterview.model.House;
import com.codepractice.springbootinterview.service.HouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 *  This Controller is used to do CRUD Operations
 */
@RestController
@RequestMapping(value = "/houses")
public class HouseController {

    private static final Logger logger = LoggerFactory.getLogger(HouseController.class);

    @Autowired
    private HouseService houseService;

    /**
     * This API is used to save House details
     * @param houseRequest house details
     * @return House Details
     */
    @PostMapping(value = "/")
    public ResponseEntity<House> saveHouse(@RequestBody @Valid HouseRequest houseRequest) {
        logger.info("Entered into saveHouse request in HouseController");
        return new ResponseEntity<>(houseService.saveHouse(houseRequest), HttpStatus.CREATED);
    }

    /**
     * This API is used to get house details using the house Id
     * @param houseId House Id
     * @return House Details
     * @throws HouseNotFoundException
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<House> getHouseDetails(@PathVariable("id") Long houseId) throws HouseNotFoundException {
        logger.info("Entered into getHouseDetails request in HouseController");
        return ResponseEntity.ok(houseService.getHouseDetails(houseId));
    }

    /**
     * This API is used to fetch All Houses
     * @return Houses List
     */
    @GetMapping(value = "/list")
    public ResponseEntity<List<House>> getAllHouses() {
        logger.info("Entered into getAllHouses request in HouseController");
        return ResponseEntity.ok(houseService.getAllHouses());
    }

    /**
     * This API is used to delete House details
     * @param houseId House Id
     * @return String message
     * @throws HouseNotFoundException
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Map<String, String>> deleteHouse(@PathVariable("id") Long houseId) throws HouseNotFoundException {
        logger.info("Entered into deleteHouse request in HouseController");
        return ResponseEntity.ok(houseService.deleteHouse(houseId));
    }


    /**
     * This API is used to update House details
     * @param houseRequest House request details
     * @param houseId House Id
     * @return House Updated Details
     * @throws HouseNotFoundException
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<House> updateHouseDetails(
            @RequestBody @Valid HouseRequest houseRequest, @PathVariable("id") Long houseId) throws HouseNotFoundException {
        logger.info("Entered into updateHouseDetails request in HouseController");
        return new ResponseEntity<>(houseService.updateHouseDetails(houseRequest, houseId), HttpStatus.OK);
    }

}
