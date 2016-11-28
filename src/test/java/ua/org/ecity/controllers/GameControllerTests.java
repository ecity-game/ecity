package ua.org.ecity.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import ua.org.ecity.entities.Game;
import ua.org.ecity.entities.GameInfo;
import ua.org.ecity.entities.MoveResult;
import ua.org.ecity.services.GameService;
import ua.org.ecity.services.UserService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
public class GameControllerTests {
//
//    @InjectMocks
//    GameController gameController;
//
//    @Mock
//    UserDetails userDetails;
//
//    @Mock (answer = Answers.RETURNS_DEEP_STUBS)
//    UserService userService;
//
//    @Mock
//    GameService gameService;
//
//    @Before
//    public void setUp() {
//        //gameController = new GameController();
//    }

//    @Test
//    public void gameStatusTest() {
//        Game game = new Game();
//        game.setId(3);
//
//        when(userDetails.getUsername()).thenReturn("john");
//        when(userService.getUser(anyString()).getId()).thenReturn(5);
//        when(gameService.findGameForStatus(5)).thenReturn(game);
//
//        GameInfo result = gameController.gameStatus(userDetails);
//
//        String msg = "{id=3, gameStatus={code=0, message='Game exists'}}";
//        assertThat(result, is(msg));
//    }

//    @Test
//    public void gameStatusNoGameExistsTest() {
//        Game game = new Game();
//        game.setId(3);
//
//        when(userDetails.getUsername()).thenReturn("john");
//        when(userService.getUser(anyString()).getId()).thenReturn(5);
//        when(gameService.findGameForStatus(5)).thenReturn(null);
//
//        GameInfo result = gameController.gameStatus(userDetails);
//
//        assertThat(result, is("{id=null, gameStatus={code=1, message='Game doesn't exist'}}"));
//    }
}
