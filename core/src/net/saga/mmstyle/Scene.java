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
package net.saga.mmstyle;

import com.badlogic.gdx.graphics.GL20;

/**
 * A Scene is a logical state in a game. A Scene defines the lifecycle of
 * related assets, event listeners, etc. Scenes are controlled by the main
 * {@link Game} object.
 *
 * Examples of Scenes are Intro, Title Screen, Main Menus, Game, etc.
 *
 */
public interface Scene {

    /**
     * Responsible for initializing a scene
     */ 
    void init();
    
    /**
     * The render loop of a scene
     * @param gl the gl context to use.
     */ 
    void render(GL20 gl);
    
    /**
     * The deconstruct the scene, free resources, etc.  
     */
    void destroy();
    
}
