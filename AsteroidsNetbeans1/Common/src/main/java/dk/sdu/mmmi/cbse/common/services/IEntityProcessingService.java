package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import java.util.Map;

/**
 *
 * @author Christian
 */
public interface IEntityProcessingService {

    /**
     *
     * @param gameData
     * @param world
     * The process method is used by classes implementing this to process the entity that the class is used to make
     */
    void process(GameData gameData, World world);
}
