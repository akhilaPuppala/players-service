package com.demo.PlayerMicroService.DTO;

import lombok.Data;

@Data
public class PlayerResponseDTO {
    private int id;
    private String name;
    private int age;
    private String playerRole;
    private String country;
}
