package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.view.StatsView;
import bg.softuni.mobilelele.service.StatsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class StatsServiceImpl implements StatsService {

    private int anonymousRequest, authRequest;

    @Override
    public void onRequest() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication!=null && (authentication.getPrincipal() instanceof UserDetails)){
            authRequest++;
        } else {
            anonymousRequest++;
        }

        System.out.println();
    }

    @Override
    public StatsView getStats() {
        return new StatsView(authRequest, anonymousRequest);
    }
}
