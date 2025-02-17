package com.demo.PlayerMicroService.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PlayerCreateRequest {
	
    private int id;
    private int userId;
    private String name;
    private int age;
    private String gender;
    private String playerType;

}
