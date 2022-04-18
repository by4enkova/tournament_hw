package ru.netology.tournament;


import ru.netology.domain.NotRegisteredException;
import ru.netology.domain.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players = new ArrayList<>();

    public List<Player> findAll() {
        return players;
    }

    public void register(Player player) {
        this.players.add(player);
    }

    public int round(String playerName1, String playerName2) {
        Player player1 = findByName(playerName1);
        Player player2 = findByName(playerName2);

        if (findByName(playerName1) == null || findByName(playerName2) == null) {
            throw new NotRegisteredException(playerName1);
        }
        int result = player1.getStrength() - player2.getStrength();
        if (result > 0) {
            return 1;
        } else if (result < 0) {
            return 2;
        } else {
            return 0;
        }
    }

    public Player findByName(String name) {
        for (Player player : players) {
            if (player.getName().equalsIgnoreCase(name)) {
                return player;
            }
        }
        return null;
    }
}
