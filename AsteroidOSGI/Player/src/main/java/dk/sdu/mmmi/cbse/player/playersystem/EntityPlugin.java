package dk.sdu.mmmi.cbse.player.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = IGamePluginService.class)
public class EntityPlugin implements IGamePluginService {

    private Entity player;

    public EntityPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        player = createPlayerShip(gameData);
        world.addEntity(player);
        System.out.println("Playerplugin started");
    }

    private Entity createPlayerShip(GameData gameData) {

        Player playerShip = new Player();

        playerShip.setPosition(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);

        playerShip.setMaxSpeed(300);
        playerShip.setAcceleration(200);
        playerShip.setDeacceleration(10);
        playerShip.setRadius(8);

        playerShip.setRadians(3.1415f / 2);
        playerShip.setRotationSpeed(3);

        return playerShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(player);
    }

}
