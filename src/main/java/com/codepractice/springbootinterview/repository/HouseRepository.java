package com.codepractice.springbootinterview.repository;

import com.codepractice.springbootinterview.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
    House findByHouseId(Long houseId);
}
