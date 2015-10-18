/**
 * Saga of the Realms Some Side Scrolling Game
 * Copyright (C) 2015  Summers Pittman III
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.saga.mmstyle.generaltests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import net.saga.mmstyle.Game;
import net.saga.mmstyle.Scene;
import static org.junit.Assert.fail;
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
    
    @Test
    public void whenSceneEndsGameCallsSceneCoordinatorTransition() {
        fail();
    }
    
}
