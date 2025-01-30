package com.demo.PlayerMicroService.DTO;

import lombok.Data;

@Data
public class PlayerRequestDTO {
    private String name;
    private int age;
    private String playerRole; // e.g., Batsman, Bowler, All-rounder
    private String country;
}
