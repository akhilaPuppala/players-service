package com.demo.PlayerMicroService.Service;



import com.demo.PlayerMicroService.Entity.Player;

import java.util.List;

public interface PlayerService {
    Player createPlayer(Player player);
    Player getPlayerById(Long id);
    Player getPlayerByUserId(Long userId);
    List<Player> getAllPlayers();
    void deletePlayer(Long id);
    Player savePlayer(Player player) ;


}

