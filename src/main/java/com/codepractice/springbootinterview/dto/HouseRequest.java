package com.codepractice.springbootinterview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseRequest {

    @NotBlank(message = "House Name is required")
    private String houseName;
    @NotBlank(message = "House Address is required")
    private String houseAddress;

    @NotBlank(message = "House Pin Code is required")
    @Size(min = 5, max = 5)
    private String housePinCode;
    @NotBlank(message = "House City is required")
    private String houseCity;
}
