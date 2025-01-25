package com.demo.PlayerMicroService.Controller;


import com.demo.PlayerMicroService.DTO.PlayerRequestDTO;
import com.demo.PlayerMicroService.DTO.PlayerResponseDTO;
import com.demo.PlayerMicroService.DTO.PlayerStatsDTO;
import com.demo.PlayerMicroService.Service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public List<PlayerResponseDTO> getAllPlayers() {
        return playerService.getAllPlayers();
    }




    @PutMapping("/{id}")
    public ResponseEntity<PlayerResponseDTO> updatePlayer(@PathVariable Long id, @RequestBody PlayerRequestDTO requestDTO) {
        PlayerResponseDTO responseDTO = playerService.updatePlayer(id, requestDTO);
        return responseDTO != null ? ResponseEntity.ok(responseDTO) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/stats")
    public ResponseEntity<PlayerStatsDTO> getPlayerStats(@PathVariable Long id) {
        PlayerStatsDTO statsDTO = playerService.getPlayerStats(id);
        return statsDTO != null ? ResponseEntity.ok(statsDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createPlayer(@RequestBody PlayerRequestDTO requestDTO) {
        try {
            log.info("Creating new player: {}", requestDTO);
            PlayerResponseDTO player = playerService.createPlayer(requestDTO);
            return ResponseEntity.ok(player);
        } catch (RuntimeException e) {
            log.error("Error creating player: {}", e.getMessage());
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            log.error("Unexpected error creating player: {}", e.getMessage());
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Internal server error"));
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getPlayerById(@PathVariable Long id) {
        try {
            log.info("Fetching player with ID: {}", id);
            PlayerResponseDTO player = playerService.getPlayerById(id);
            if (player != null) {
                return ResponseEntity.ok(player);
            }
            return ResponseEntity.status(404)
                    .body(Map.of("error", "Player not found with ID: " + id));
        } catch (Exception e) {
            log.error("Error fetching player: {}", e.getMessage());
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Internal server error"));
        }
    }
}
