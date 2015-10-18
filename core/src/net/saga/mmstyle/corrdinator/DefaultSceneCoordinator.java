package net.saga.mmstyle.corrdinator;

import com.badlogic.gdx.Gdx;
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

public class DefaultSceneCoordinator implements SceneCoordinator{
    
    private static final String NAME_KEY = "name";
    private static final String CLASS_KEY = "class";
    
    private final Map<String, Class<? extends Scene>> scenes;
    private Scene currentScene;
    
    public DefaultSceneCoordinator(String jsonFilename) {
        final FileHandle scenesFile = Gdx.files.internal(jsonFilename);
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
    
    
    
}
