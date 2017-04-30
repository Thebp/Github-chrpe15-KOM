package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *
 * @author Christian
 */
public interface IGamePluginService {
    
    /**
     * 
     * @param gameData
     * @param world
     * The start method enables classes implementing this interface to initialize the gamedata and world for an entity
     */
    void start(GameData gameData, World world);

    /**
     *
     * @param gameData
     * @param world
     * The stop method enables classes implementing this interface to remove the gamedata and world for an entity
     */
    void stop(GameData gameData, World world);
}
