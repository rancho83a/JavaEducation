package bg.softuni.shedules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FixedDelaySchedulerDemo {
    public static final Logger LOGGER = LoggerFactory.getLogger(FixedDelaySchedulerDemo.class);

    //This fixed delay waits N milies after the EXECUTION  of previous task end
    @Scheduled(fixedDelay = 20000, initialDelay = 10000)
    public void showTimeWithCron(){
        LOGGER.info("Hello from FixedDelay scheduler at: {}", LocalDateTime.now());
    }

}
