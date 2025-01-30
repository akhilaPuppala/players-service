package com.demo.PlayerMicroService.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.PlayerMicroService.DTO.PlayerCreateRequest;
import com.demo.PlayerMicroService.Entity.Player;
import com.demo.PlayerMicroService.Service.PlayerService;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

	    @Autowired
	    private PlayerService playerService;

	    // Create or Update a Player
	    @PostMapping("/create")
	    public void createPlayer(@RequestBody PlayerCreateRequest request) {
	        Player player = new Player();
	        player.setUserId(request.getUserId());
	        player.setName(request.getName());
	        // Initialize other fields with default values or leave null
	        player.setAge(0); // Default age
	        player.setGender(null); // Gender not provided yet
	        player.setPlayerType(null); // Player type not provided yet
	        playerService.savePlayer(player);
	    }

	    // Get all Players
	    @GetMapping("/all")
	    public ResponseEntity<List<Player>> getAllPlayers() {
	        List<Player> players = playerService.getAllPlayers();
	        return ResponseEntity.ok(players);
	    }

	    // Get a Player by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<Player> getPlayerById(@PathVariable int id) {
	        Optional<Player> player = playerService.getPlayerById(id);
	        return player.map(ResponseEntity::ok)
	                .orElse(ResponseEntity.notFound().build());
	    }

	    // Delete a Player by ID
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deletePlayerById(@PathVariable int id) {
	        Optional<Player> player = playerService.getPlayerById(id);
	        if (player.isPresent()) {
	            playerService.deletePlayerById(id);
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
}
