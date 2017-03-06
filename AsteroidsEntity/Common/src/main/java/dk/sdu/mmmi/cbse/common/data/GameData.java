package dk.sdu.mmmi.cbse.common.data;

import dk.sdu.mmmi.cbse.common.events.Event;
import dk.sdu.mmmi.cbse.common.events.EventType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameData {

    private float delta;
    private int displayWidth;
    private int displayHeight;
    private final GameKeys keys = new GameKeys();
    private final List<Event> events = new CopyOnWriteArrayList<>();

    public void addEvent(Event e) {
        events.add(e);
    }

    public void removeEvent(Event e) {
        events.remove(e);
    }

    public List<Event> getEvents() {
        return events;
    }

    public List<Event> getEvents(EventType eventType, String id) {
        List<Event> r = new ArrayList<>();

        for (Event event : events) {
            if (event.getType().equals(eventType) && event.getEntityID().equals(id)) {
                r.add(event);
            }
        }
        return r;
    }

    public GameKeys getKeys() {
        return keys;
    }

    public void setDelta(float delta) {
        this.delta = delta;
    }

    public float getDelta() {
        return delta;
    }

    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayHeight(int height) {
        this.displayHeight = height;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }
}
