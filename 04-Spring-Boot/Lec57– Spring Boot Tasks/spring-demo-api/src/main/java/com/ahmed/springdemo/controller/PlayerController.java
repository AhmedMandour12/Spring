package com.ahmed.springdemo.controller;

import com.ahmed.springdemo.model.Player;
import com.ahmed.springdemo.service.PlayerService;
import com.ahmed.springdemo.service.serviceimpl.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/players")
@RestController
public class PlayerController {

    PlayerServiceImpl playerService;

    @Autowired
    public PlayerController(PlayerServiceImpl playerService) {
        this.playerService = playerService;
    }

    @GetMapping()
    List<Player> getAllPlayer() {
        return playerService.getAllPlayers();

    }


    @GetMapping("/{id}")
    Player getPlayerByID(@PathVariable Long id) {
        return playerService.getPlayerByID(id);

    }


    @PostMapping
    Player savePlayer(@RequestBody Player player) {
        playerService.savePlayer(player);
        return player;
    }

    @PutMapping()
    Player updatePlayer(@RequestBody Player player) {
        return playerService.updatePlayer( player);
    }
    @DeleteMapping("/{id}")
    void deletPlayer(@PathVariable Long id){
        playerService.deletePlayer(id);

    }


}
