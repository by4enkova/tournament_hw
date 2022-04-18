package ru.netology.tournament;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.NotRegisteredException;
import ru.netology.domain.Player;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game = new Game();
    private Player player1 = new Player(1, "Danya", 1);
    private Player player2 = new Player(2, "Sasha", 2);
    private Player player3 = new Player(3, "Pavel", 3);
    private Player player4 = new Player(4, "Ivan", 3);


    @BeforeEach
    void setUp() {
        game.register(player1);
        game.register(player2);
        game.register(player3);
        game.register(player4);
    }

    @Test
    void shouldFindRegisteredPlayers() {
        assertEquals(List.of(player1,player2,player3,player4), game.findAll());
    }

    @Test
    void shouldFindByName() {
        assertEquals(player3, game.findByName("Pavel"));
    }

    @Test
    void shouldNotFindByName() {
        assertEquals(null, game.findByName("John"));
    }

    @Test
    void shouldWinTheFirstOne() {
        int actual = game.round("Ivan", "Sasha");
        assertEquals(1, actual);
    }


    @Test
    void shouldWinTheSecondOne() {
        int actual = game.round("Danya", "Pavel");
        assertEquals(2, actual);
    }

    @Test
    void shouldBeDraw() {
        int actual = game.round("Pavel", "Ivan");
        assertEquals(0, actual);
    }

    @Test
     void shouldThrowExceptionWhenPlayer1Unreg() {
        assertThrows(NotRegisteredException.class,
                () -> game.round("Dan", "Ivan"));
    }

    @Test
    void shouldThrowExceptionWhen2PlayersUnreg() {
        assertThrows(NotRegisteredException.class,
                () -> game.round("Vic", "Alex"));
    }

    @Test
    void shouldThrowExceptionWhenPlayer2Unreg() {
        assertThrows(NotRegisteredException.class,
                () -> game.round("Sasha", "Vlad"));
    }

}