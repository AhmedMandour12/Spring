package com.ahmed.springdemo.service;

import com.ahmed.springdemo.model.Player;

import java.util.List;

public interface PlayerService {
    void savePlayer(Player player);
    List<Player>getAllPlayers();
    Player getPlayerByID(Long id);
    Player updatePlayer(Player player);
    void deletePlayer(Long id);
}
