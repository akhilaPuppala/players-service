package com.demo.PlayerMicroService.Entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "player_details")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Long id;


    @Column(unique = true ,name="user_id")
    private Long userId;


    @Column(name = "player_name")
    private String name;

    @Column(name = "player_age")
    private Integer age;
    @Column(name = "player_gender")
    private String gender;
    @Column(name = "player_type")
    private String playerType;

    // Getters and Setters
}

