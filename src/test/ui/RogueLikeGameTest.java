//package ui;
//
//import model.HealthBar;
//import model.Position;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class RogueLikeGameTest {
//    RogueLikeGame rogueLikeGame;
//
//    @BeforeEach
//    void setup() {
//        rogueLikeGame = new RogueLikeGame(
//                RogueLikeGameMainMenu.GAME_TERMINAL_WIDTH,
//                RogueLikeGameMainMenu.GAME_TERMINAL_HEIGHT);
//    }
//
//    @Test
//    void testHandleCollisions() {
//        // set position to 2 blocks below spike tile
//        rogueLikeGame.getGame().player().setPosition(new Position(9,11));
//
//        // move up one block
//        rogueLikeGame.getGame().player().move("w",rogueLikeGame.getGame());
//        assertTrue(rogueLikeGame.getGame().player().getPosition().equals((new Position(9,10))));
//        assertEquals(rogueLikeGame.getGame().player().getHealthBar().getHealth(), HealthBar.MAX_HEALTH);
//        assertTrue(rogueLikeGame.handleCollisions());
//
//
//        // move up another block
//        rogueLikeGame.getGame().player().move("w",rogueLikeGame.getGame());
//        assertTrue(rogueLikeGame.getGame().player().getPosition().equals((new Position(9,10))));
//        assertTrue(rogueLikeGame.getGame().player().getHealthBar().isZero());
//        assertFalse(rogueLikeGame.handleCollisions());
//
//    }
//}
