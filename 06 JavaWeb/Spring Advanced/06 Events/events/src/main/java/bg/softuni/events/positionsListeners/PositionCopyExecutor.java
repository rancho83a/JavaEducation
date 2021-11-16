package bg.softuni.events.positionsListeners;


import bg.softuni.events.PositionCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PositionCopyExecutor {
    private Logger LOGGER = LoggerFactory.getLogger(PositionCopyExecutor.class);


    @EventListener(PositionCreatedEvent.class)
    public  void onPositionCreated(PositionCreatedEvent positionCreatedEvent){
        LOGGER.info("Position no {} has been created. I`m going to calculate Win/loss count positions of your trader.",
                positionCreatedEvent.getPositionId());

//TODO - calculate the count of win and loss position
    }

}
