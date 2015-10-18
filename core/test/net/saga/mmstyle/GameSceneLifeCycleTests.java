package net.saga.mmstyle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Matchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

/**
 * This class will test that the Game class respects the Scene's lifecycle 
 * events.
 */
public class GameSceneLifeCycleTests {
    
    private Scene mockScene;
    private Game game;
    
    @BeforeClass
    public static void setupLibGdx() {
        Gdx.gl = mock(GL20.class);
    }
    
    @Before
    public void prepareGameForTest() {
        game = new Game();
        mockScene = Mockito.mock(Scene.class);
        game.setScene(mockScene);
        
    }
    
    @Test
    public void testGameCallsInitOnADefaultScene() {
        game.create();
        Mockito.verify(mockScene, times(1)).init();
    }
    
    @Test
    public void testGameCallsRenderAsPartOfTheRenderLoop() {
        game.render();
        Mockito.verify(mockScene, times(1)).render(any(GL20.class));
    }
    
    @Test
    public void testGameCallsDestroyWhenASceneEnds() {
        game.onSceneEnded();
        Mockito.verify(mockScene, times(1)).destroy();
    }
    
}
