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

package net.saga.mmstyle.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.saga.mmstyle.Scene;

/**
 * The intro scene to the game.  Displays opening text currently.
 */
public class IntroScene implements Scene {
    private SpriteBatch batch;
    private BitmapFont font;
    
    @Override
    public void init() {
        batch = new SpriteBatch();    
        font = new BitmapFont();
        font.setColor(Color.RED);
    }
    
    @Override
    public void destroy() {
        batch.dispose();
        font.dispose();
    }
    
    @Override
    public void render(GL20 gl) {
        gl.glClearColor(1, 1, 1, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        font.draw(batch, "Hello World", 200, 200);
        batch.end();
    }
    
}
