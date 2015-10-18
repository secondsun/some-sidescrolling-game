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
import net.saga.mmstyle.screen.SceneResult;

public class DefaultSceneCoordinator implements SceneCoordinator{
    
    private static final String NAME_KEY = "name";
    private static final String CLASS_KEY = "class";
    private static final String TRANSITIONS_KEY = "transitions";
    private static final String TRANSITION_ON_KEY = "on";
    private static final String TRANSITION_TO_KEY = "to";
    
    private final Map<String, SceneHolder> scenes;
    
    private String currentSceneName;
    private Scene currentScene;
    
    public DefaultSceneCoordinator(final FileHandle scenesFile) {
        
        final JsonArray scenesJSONArray = new JsonParser().parse(scenesFile.reader()).getAsJsonArray();
        scenes = new HashMap<>(scenesJSONArray.size());
        buildScenes(scenesJSONArray);
        setFirstScene(scenesJSONArray.get(0).getAsJsonObject());
    }

    @Override
    public Scene getCurrentScene() {
        return currentScene;
    }

    private void buildScenes(Iterable<JsonElement> scenesJSONArray) {
        for (JsonElement sceneElement : scenesJSONArray) {
            final JsonObject sceneObject = sceneElement.getAsJsonObject();
            final String sceneName = sceneObject.get(NAME_KEY).getAsString();
            scenes.put(sceneName, new SceneHolder(sceneObject));
            
        }
        
    }

    /**
     * Sets the first scene to the className specified in the json object.
     * 
     * @param firstSceneJsonObject the jsonObject describing the first scene
     */
    private void setFirstScene(JsonObject firstSceneJsonObject) {
        try {
            currentSceneName = firstSceneJsonObject.get(NAME_KEY).getAsString();
            Class<? extends Scene> sceneClass = scenes.get(currentSceneName).klazz;
            currentScene = sceneClass.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DefaultSceneCoordinator.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void transition(SceneResult result) {
        SceneHolder holder = scenes.get(currentSceneName);
        final String transitionTo = holder.sceneTransitions.get(result.getKey());
        if (transitionTo == null) {
            throw new SceneNotFoundException(result.getKey() + " Is not a valid transition");
        }
        
        holder = scenes.get(transitionTo);
        
        if (holder == null) {
            throw new SceneNotFoundException(transitionTo + " Is not a valid transition");
        }
        this.currentSceneName = transitionTo;
        this.currentScene = this.newInstance(holder.klazz);
    }

    /**
     * Returns a new instance and wraps checked exceptions in Runtime Exceptions.
     * @param className the class we want
     * @return a new Instance of the cleas
     * @throws RuntimeException if the classes aren't setup right
     */ 
    private Scene newInstance(Class<? extends Scene> className) {
        try {
            return className.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DefaultSceneCoordinator.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }
    
    private static class SceneHolder {

        private final Class<? extends Scene> klazz;
        private final Map<String, String> sceneTransitions;
        
        public SceneHolder(JsonObject sceneObject) {
            try {
                final String className = sceneObject.get(CLASS_KEY).getAsString();
                final JsonArray transitions = sceneObject.get(TRANSITIONS_KEY).getAsJsonArray();
                
                this.klazz = (Class<? extends Scene>) Class.forName(className);
                this.sceneTransitions = new HashMap<>(transitions.size());
                
                for (JsonElement transition : transitions) {
                    final String transitionOn = transition.getAsJsonObject().get(TRANSITION_ON_KEY).getAsString();
                    final String transitionTo = transition.getAsJsonObject().get(TRANSITION_TO_KEY).getAsString();
                    sceneTransitions.put(transitionOn, transitionTo);
                }
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DefaultSceneCoordinator.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        }
    }
    
    
    
}

