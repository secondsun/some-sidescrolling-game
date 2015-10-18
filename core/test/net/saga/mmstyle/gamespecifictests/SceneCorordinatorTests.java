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
package net.saga.mmstyle.gamespecifictests;

import com.badlogic.gdx.files.FileHandle;
import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.saga.mmstyle.corrdinator.DefaultSceneCoordinator;
import net.saga.mmstyle.corrdinator.SceneCoordinator;
import net.saga.mmstyle.corrdinator.SceneNotFoundException;
import net.saga.mmstyle.screen.GameScene;
import net.saga.mmstyle.screen.IntroScene;
import net.saga.mmstyle.screen.MainMenu;
import net.saga.mmstyle.screen.SceneResult;
import net.saga.mmstyle.screen.Settings;
import net.saga.mmstyle.screen.TitleScene;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * This class tests that the Game object properly sets up the Game
 */
public class SceneCorordinatorTests {
 
    @Test
    public void testDefaultSceneCoordinatorLoadsScenesJsonFileAndSetsFirstSceneToFirstElement() {
        SceneCoordinator coordinator = new DefaultSceneCoordinator(new FileHandle(getFile("scenes.json")));
        assertTrue(coordinator.getCurrentScene() instanceof IntroScene);
    }
    
    @Test
    public void testDefaultSceneCoordinatorTransitionsIntroToTitle() {
        SceneCoordinator coordinator = new DefaultSceneCoordinator(new FileHandle(getFile("scenes.json")));
        coordinator.transition(SceneResult.newInstance("title"));
        assertTrue(coordinator.getCurrentScene() instanceof TitleScene);
    }
    
    @Test(expected = SceneNotFoundException.class)
    public void testDefaultSceneCoordinatorBadTransitionsThrowException() {
        SceneCoordinator coordinator = new DefaultSceneCoordinator(new FileHandle(getFile("scenes.json")));
        coordinator.transition(SceneResult.newInstance("fail"));
    }
    
    @Test
    public void testDefaultSceneCoordinatorTransitionsBasedOnSceneResult() {
        SceneCoordinator coordinator = new DefaultSceneCoordinator(new FileHandle(getFile("branching_scenes.json")));
        assertTrue(coordinator.getCurrentScene() instanceof MainMenu);
        coordinator.transition(SceneResult.newInstance("settings"));
        assertTrue(coordinator.getCurrentScene() instanceof Settings);
        coordinator.transition(SceneResult.newInstance("menu"));
        assertTrue(coordinator.getCurrentScene() instanceof MainMenu);
        coordinator.transition(SceneResult.newInstance("game"));
        assertTrue(coordinator.getCurrentScene() instanceof GameScene);
        coordinator.transition(SceneResult.newInstance("end"));
        assertTrue(coordinator.getCurrentScene() instanceof MainMenu);
        
    }
    
    private File getFile(String fileName) {
        try {
            return Paths.get(ClassLoader.getSystemResource(fileName).toURI()).toFile();
        } catch (URISyntaxException ex) {
            Logger.getLogger(SceneCorordinatorTests.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }
    
}

