package dk.sdu.mmmi.cbse.common.events;

import java.io.Serializable;

/**
 *
 * @author Mads
 */
public class Event implements Serializable{
    private final EventType type;
    private final String entityID;
    
    public Event(EventType type, String entityID){
        this.type = type;
        this.entityID = entityID;
    }
        
    public EventType getType(){
        return type;
    }

    public String getEntityID() {
        return entityID;
    }     
}
