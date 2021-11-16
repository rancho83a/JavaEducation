package bg.softuni.events.positionsListeners;

import bg.softuni.events.PositionCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SummaryResultCalculator {

    private Logger LOGGER = LoggerFactory.getLogger(SummaryResultCalculator.class);


    @EventListener(PositionCreatedEvent.class)
    public void onPositionCreated(PositionCreatedEvent positionCreatedEvent) {
        LOGGER.info("Position no {} has been created. I`m going to copy this position to all joined investors",
                positionCreatedEvent.getPositionId());

//TODO - copy this positions to all joined investors according their capital
    }
}
