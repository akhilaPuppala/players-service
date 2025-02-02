package com.demo.PlayerMicroService.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.PlayerMicroService.Entity.Player;
import com.demo.PlayerMicroService.Repository.PlayerRepository;

@Service
public class PlayerService {

	@Autowired
    private PlayerRepository playerRepository;

    // Create or Update a Player
    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    // Retrieve all Players
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    // Retrieve a Player by ID
    public Optional<Player> getPlayerById(int id) {
        return playerRepository.findById(id);
    }

    // Delete a Player by ID
    public void deletePlayerById(int id) {
        playerRepository.deleteById(id);
    }

    public int getPlayerIdByUserId(int userId) {
        // Find the player by userId
        Player player = playerRepository.findByUserId(userId);
        
        // Check if player is found and return player_id
        if (player != null) {
            return player.getId();
        } else {
            // If no player is found, you can throw an exception or return a default value
            throw new RuntimeException("Player not found for userId: " + userId);
        }
    }
}


