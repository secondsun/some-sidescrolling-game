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

import java.util.Objects;

/**
 * A scene result is a wrapper for a key to be used by a SceneCoordinator for
 * transitioning between scenes
 */
public class SceneResult<T> {
    private final T key;
    
    public static <T> SceneResult<T> newInstance(T key) {
        return new SceneResult<>(key);
    }
    
    private SceneResult(T key) {
        this.key = key;
    }
    
    public T getKey() {
        return key;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.key);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SceneResult<?> other = (SceneResult<?>) obj;
        if (!Objects.equals(this.key, other.key)) {
            return false;
        }
        return true;
    }
    
    
    
}
