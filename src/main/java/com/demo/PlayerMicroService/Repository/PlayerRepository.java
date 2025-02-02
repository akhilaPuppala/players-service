package com.demo.PlayerMicroService.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.PlayerMicroService.Entity.Player;
@Repository

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    Optional<Player> findById(int id);
    
    Optional<Player> findByUserId(int userId);
}
