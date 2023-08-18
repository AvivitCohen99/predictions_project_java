package world.termination;

import org.w3c.dom.Element;
import world.ParseException;

import java.util.Optional;

public class Termination {

    private Integer ticks;
    private Integer seconds;

    public static Termination parse(Element terminationElement) throws ParseException {
        Element ticksElement = (Element) terminationElement.getElementsByTagName("PRD-by-ticks").item(0);
        Integer ticks = ticksElement != null ? Integer.parseInt(ticksElement.getAttribute("count")) : -1;

        Element secondsElement = (Element) terminationElement.getElementsByTagName("PRD-by-second").item(0);
        Integer seconds = secondsElement != null ? Integer.parseInt(secondsElement.getAttribute("count")) : -1;

        if (ticks.equals(-1) && seconds.equals(-1)) {
            throw new ParseException("both ticks and seconds not defined");
        }

        return new Termination(Optional.of(ticks), Optional.of(seconds));
    }

    public Termination(Optional<Integer> ticks, Optional<Integer> seconds) {
        this.ticks = ticks.orElse(-1);
        this.seconds = seconds.orElse(-1);
    }

    boolean shouldTerminate(Integer currentTicks, Integer currentSeconds) {
        return (ticks > 0 && currentTicks >= ticks) || (seconds > 0 && currentSeconds >= seconds);
    }

    public TerminationDetails getDetails() {
        return new TerminationDetails(ticks, seconds);
    }
}
