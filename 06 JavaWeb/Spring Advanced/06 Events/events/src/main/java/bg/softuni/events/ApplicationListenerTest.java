package bg.softuni.events;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationListenerTest implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {

    }
}
