package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *
 * @author Christian
 */
public interface IEntityProcessingService {

    /**
     *
     * @param gameData
     * @param world
     * The process method is used by classes implementing this to process the service that the module provides
     */
    void process(GameData gameData, World world);
}
