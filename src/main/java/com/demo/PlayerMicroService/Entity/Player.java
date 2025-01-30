package com.demo.PlayerMicroService.Entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "player_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    private int userId;
	    private String name;
	    private int age;
	    private String gender;
	    private String playerType;
}
