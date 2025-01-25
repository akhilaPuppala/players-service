package com.demo.PlayerMicroService.DTO;

import lombok.Data;

@Data
public class PlayerRequestDTO {
    private String name;
    private Integer age;
    private String role; // e.g., Batsman, Bowler, All-rounder
    private String country;
}
