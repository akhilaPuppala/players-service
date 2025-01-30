package com.demo.PlayerMicroService.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data 
public class PlayerCreateRequest {
	
	private int userId;
    private String name;

}
