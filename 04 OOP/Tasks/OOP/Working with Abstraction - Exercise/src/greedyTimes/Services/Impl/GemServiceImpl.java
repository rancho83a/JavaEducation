package greedyTimes.Services.Impl;

import greedyTimes.Gem;
import greedyTimes.Services.GemService;

import java.util.List;

public class GemServiceImpl implements GemService {
    private final List<Gem> gem;

    public GemServiceImpl(List<Gem> gem) {
        this.gem = gem;
    }

    @Override
    public boolean doesContainsGem(String name) {
        //return this.getGems().stream().anyMatch(i->i.getName().equals(name));
        for (Gem gem : this.gem) {
            if (gem.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public long getSummaryAmountGem() {
        return this.gem.stream().mapToLong(Gem::getAmount).sum();

    }

    @Override
    public Gem findGemByName(String name) {
        for (Gem gem : this.gem) {
            if(gem.getName().equals(name)){
                return gem;
            }
        }
        return null;
    }
}
