package com.demo.PlayerMicroService.Service;


import com.demo.PlayerMicroService.DTO.PlayerRequestDTO;
import com.demo.PlayerMicroService.DTO.PlayerResponseDTO;
import com.demo.PlayerMicroService.DTO.PlayerStatsDTO;
import com.demo.PlayerMicroService.Entity.Player;
import com.demo.PlayerMicroService.Repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<PlayerResponseDTO> getAllPlayers() {
        return playerRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public PlayerResponseDTO getPlayerById(Long id) {
        Player player = playerRepository.findById(id).orElse(null);
        return player != null ? convertToResponseDTO(player) : null;
    }

    public PlayerResponseDTO createPlayer(PlayerRequestDTO requestDTO) {
        Player player = new Player();
        player.setName(requestDTO.getName());
        player.setAge(requestDTO.getAge());
        player.setRole(requestDTO.getRole());
        player.setCountry(requestDTO.getCountry());
        Player savedPlayer = playerRepository.save(player);
        return convertToResponseDTO(savedPlayer);
    }

    public PlayerResponseDTO updatePlayer(Long id, PlayerRequestDTO requestDTO) {
        Player player = playerRepository.findById(id).orElse(null);
        if (player != null) {
            player.setName(requestDTO.getName());
            player.setAge(requestDTO.getAge());
            player.setRole(requestDTO.getRole());
            player.setCountry(requestDTO.getCountry());
            Player updatedPlayer = playerRepository.save(player);
            return convertToResponseDTO(updatedPlayer);
        }
        return null;
    }

    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }

    // Fetch player statistics from TeamPlayer Microservice

    private PlayerResponseDTO convertToResponseDTO(Player player) {
        PlayerResponseDTO responseDTO = new PlayerResponseDTO();
        responseDTO.setId(player.getId());
        responseDTO.setName(player.getName());
        responseDTO.setAge(player.getAge());
        responseDTO.setRole(player.getRole());
        responseDTO.setCountry(player.getCountry());
        return responseDTO;
    }
    // ... existing code ...

    // Remove the first getPlayerStats method and keep this one
    @Value("${teamplayer.service.base.url}")
    private String teamPlayerServiceBaseUrl;

    public PlayerStatsDTO getPlayerStats(Long playerId) {
        try {
            return webClientBuilder.build()
                    .get()
                    .uri(teamPlayerServiceBaseUrl + "/api/teamplayers/stats/" + playerId)
                    .retrieve()
                    .bodyToMono(PlayerStatsDTO.class)
                    .block();
        } catch (WebClientResponseException.NotFound e) {
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching player stats", e);
        }
    }


    private void validatePlayerInput(PlayerRequestDTO requestDTO) {
        if (requestDTO == null) {
            throw new RuntimeException("Player data cannot be null");
        }
        if (requestDTO.getName() == null || requestDTO.getName().trim().isEmpty()) {
            throw new RuntimeException("Player name is required");
        }
        if (requestDTO.getAge() == null || requestDTO.getAge() < 0) {
            throw new RuntimeException("Valid player age is required");
        }
        if (requestDTO.getRole() == null || requestDTO.getRole().trim().isEmpty()) {
            throw new RuntimeException("Player role is required");
        }
        if (requestDTO.getCountry() == null || requestDTO.getCountry().trim().isEmpty()) {
            throw new RuntimeException("Player country is required");
        }
    }

    private void updatePlayerFields(Player player, PlayerRequestDTO requestDTO) {
        player.setName(requestDTO.getName().trim());
        player.setAge(requestDTO.getAge());
        player.setRole(requestDTO.getRole().trim());
        player.setCountry(requestDTO.getCountry().trim());
    }
    // ... existing code ...


    }

