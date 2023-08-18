package world.termination;

import java.util.Optional;

public class TerminationDetails {
    public Integer ticks;
    public Integer seconds;

    public TerminationDetails(Integer ticks, Integer seconds) {
        this.ticks = ticks;
        this.seconds = seconds;
    }
}
