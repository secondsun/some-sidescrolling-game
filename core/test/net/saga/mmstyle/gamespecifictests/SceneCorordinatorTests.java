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

import net.saga.mmstyle.corrdinator.DefaultSceneCoordinator;
import net.saga.mmstyle.corrdinator.SceneCoordinator;
import net.saga.mmstyle.screen.IntroScene;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * This class tests that the Game object properly sets up the Game
 */
public class SceneCorordinatorTests {
 
    @Test
    public void testLoadsScenesJsonFile() {
        SceneCoordinator coordinator = new DefaultSceneCoordinator("scenes.json");
        assertTrue(coordinator.getCurrentScene() instanceof IntroScene);
    }
    
}