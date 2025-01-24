package com.demo.PlayerMicroService.Controller;


import com.demo.PlayerMicroService.Entity.Player;
import com.demo.PlayerMicroService.Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    @Autowired
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        return ResponseEntity.ok(playerService.createPlayer(player));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.getPlayerById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Player> getPlayerByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(playerService.getPlayerByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();

    }
    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody Player updatedPlayer) {
        Player existingPlayer = playerService.getPlayerById(id);
        existingPlayer.setUserId(updatedPlayer.getUserId());
        existingPlayer.setName(updatedPlayer.getName());
        existingPlayer.setAge(updatedPlayer.getAge());
        existingPlayer.setGender(updatedPlayer.getGender());
        existingPlayer.setPlayerType(updatedPlayer.getPlayerType());
        Player savedPlayer = playerService.savePlayer(existingPlayer);
        return ResponseEntity.ok(savedPlayer);
    }

}

