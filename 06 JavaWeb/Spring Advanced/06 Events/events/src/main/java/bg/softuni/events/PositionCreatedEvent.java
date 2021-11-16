package bg.softuni.events;

import org.springframework.context.ApplicationEvent;

public class PositionCreatedEvent extends ApplicationEvent {

    private final String positionId;

    public PositionCreatedEvent(Object source, String positionId) {
        super(source);
        this.positionId = positionId;
    }

    public String getPositionId() {
        return positionId;
    }
}
