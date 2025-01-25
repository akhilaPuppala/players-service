package com.demo.PlayerMicroService.Repository;

import com.demo.PlayerMicroService.Entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findById(Long id);
}
