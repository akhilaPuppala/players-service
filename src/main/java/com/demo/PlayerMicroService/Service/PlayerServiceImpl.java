package com.demo.PlayerMicroService.Service;


import com.demo.PlayerMicroService.Entity.Player;
import com.demo.PlayerMicroService.Repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player getPlayerById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found"));
    }

    @Override
    public Player getPlayerByUserId(Long userId) {
        return playerRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Player not found with User ID"));
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }
    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

}
