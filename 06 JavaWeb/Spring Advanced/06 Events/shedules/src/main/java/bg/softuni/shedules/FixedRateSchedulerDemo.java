package bg.softuni.shedules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FixedRateSchedulerDemo {
    public static final Logger LOGGER = LoggerFactory.getLogger(FixedRateSchedulerDemo.class);

    //This fixed delay waits N milies and start next execution (don`t care does previous task finish)
    @Scheduled(fixedRate = 5000)
    public void showTimeWithCron(){
        LOGGER.info("Hello from FixedRate scheduler at: {}", LocalDateTime.now());
    }

}
