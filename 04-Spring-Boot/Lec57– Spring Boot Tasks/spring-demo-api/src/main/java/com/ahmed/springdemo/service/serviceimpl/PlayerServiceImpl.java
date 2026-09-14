package com.ahmed.springdemo.service.serviceimpl;

import com.ahmed.springdemo.model.Player;
import com.ahmed.springdemo.repository.PlayerRepo;
import com.ahmed.springdemo.service.PlayerService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepo playerRepo;

    @Autowired
    public PlayerServiceImpl(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    @Override
    public void savePlayer(Player player) {
        playerRepo.save(player);
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerRepo.findAll();

    }

    @Override
    public Player getPlayerByID(Long id) {
        return playerRepo.getById(id);
    }

    @Override
    public Player updatePlayer( Player player) {
        if (player.getId() == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        Player exitingPlayer = playerRepo.findById(player.getId()).orElseThrow(()->new RuntimeException("Player not found"));

        if(player.getName()!= null){
            exitingPlayer.setName(player.getName());

        }
        if(player.getSalary()!= null){
            exitingPlayer.setSalary(player.getSalary());

        }
        return playerRepo.save(exitingPlayer);
    }

    @Override
    public void deletePlayer(Long id) {
        playerRepo.deleteById(id);

    }
}
