package dk.sdu.mmmi.cbse.common.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author jcs
 */
public class World {

    private final Map<String, Entity> entityMap = new ConcurrentHashMap<>();

    public String addEntity(Entity entity) {
        entityMap.put(entity.getID(), entity);
        return entity.getID();
    }

    public void removeEntity(String entityID) {
        entityMap.remove(entityID);
    }

    public void removeEntity(Entity entity) {
        entityMap.remove(entity.getID());
    }
    
    public Collection<Entity> getEntities() {
        return entityMap.values();
    }

    public List<Entity> getEntities(EntityType... entityTypes) {
        List<Entity> r = new ArrayList<>();
        for (Entity e : getEntities()) {
            for (EntityType entityType : entityTypes) {
                if (entityType.equals(e.getType())) {
                    r.add(e);
                }
            }
        }
        return r;
    }
}
