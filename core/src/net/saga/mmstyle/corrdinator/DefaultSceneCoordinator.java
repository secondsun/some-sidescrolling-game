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
package net.saga.mmstyle.corrdinator;

import com.badlogic.gdx.files.FileHandle;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.saga.mmstyle.Scene;
import net.saga.mmstyle.screen.TitleScene;

public class DefaultSceneCoordinator implements SceneCoordinator{
    
    private static final String NAME_KEY = "name";
    private static final String CLASS_KEY = "class";
    
    private final Map<String, Class<? extends Scene>> scenes;
    private Scene currentScene;
    
    public DefaultSceneCoordinator(final FileHandle scenesFile) {
        
        final JsonArray scenesJSONArray = new JsonParser().parse(scenesFile.reader()).getAsJsonArray();
        scenes = new HashMap<>(scenesJSONArray.size());
        buildScenes(scenesJSONArray);
        setFirstScene(scenesJSONArray.get(0).getAsJsonObject());
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    private void buildScenes(Iterable<JsonElement> scenesJSONArray) {
        for (JsonElement sceneElement : scenesJSONArray) {
            final JsonObject sceneObject = sceneElement.getAsJsonObject();
            final String sceneName = sceneObject.get(NAME_KEY).getAsString();
            final String className = sceneObject.get(CLASS_KEY).getAsString();
            
            try {
                scenes.put(sceneName, (Class<? extends Scene>) Class.forName(className));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DefaultSceneCoordinator.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
            
        }
        
    }

    /**
     * Sets the first scene to the className specified in the json object.
     * 
     * @param firstSceneJsonObject the jsonObject describing the first scene
     */
    private void setFirstScene(JsonObject firstSceneJsonObject) {
        try {
            final String sceneName = firstSceneJsonObject.get(NAME_KEY).getAsString();
            Class<? extends Scene> sceneClass = scenes.get(sceneName);
            currentScene = sceneClass.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DefaultSceneCoordinator.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void transition() {
        this.currentScene = new TitleScene();
    }
    
    
    
}
