package com.demo.PlayerMicroService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.PlayerMicroService.Entity.Player;
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    Player findByUserId(int userId);
}
