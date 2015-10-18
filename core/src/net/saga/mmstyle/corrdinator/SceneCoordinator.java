/**
 * Saga of the Realms Some Side Scrolling Game Copyright (C) 2015 Summers
 * Pittman III
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package net.saga.mmstyle.corrdinator;

import net.saga.mmstyle.Scene;
import net.saga.mmstyle.screen.SceneResult;

/**
 * This class coordinates the transitions between scenes.
 *
 * For instance if Game receives a endScene event it should resolve some kind of
 * Result object. This class will consume that result and return the next scene.
 * Basically it is like a navigation class for large game states.
 *
 */
public interface SceneCoordinator {

    /**
     * Return a reference to the current scene
     *
     * @return The current scene
     */
    Scene getCurrentScene();

    /**
     * The current scene is finished, move to the next scene.
     *
     * @param result The result of the current Scene. This is used to transition
     * to the next scene
     */
    public void transition(SceneResult result);

}
