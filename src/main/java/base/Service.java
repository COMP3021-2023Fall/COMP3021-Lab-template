package base;
import java.time.Duration;
import java.util.*;

public class Service implements EventEmitter {
    private final List<Listener> listeners = new ArrayList<>();
    Timer timer;

    // TODO 1: Implement the methods addListener, removeListener and emitEvent in EventEmitter interface
    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        this.listeners.remove(listener);
    }

    public void emitEvent(Event event) {
        listeners.forEach(listener -> listener.handle(event));
    }

    /**
     * TODO 2: Complete the method startTick
     * Start time ticking, emit {@link Event} immediately and then periodically emit events with the given time interval.
     * That is to say, if the interval is 1 second,
     * the first event is emitted at 0-th second,
     * second event is emitted at 1-th second, etc.
     * The emitted {@link Event} should use the current time ({@link Date} object) as the value of {@link Event#getSource()}.
     * You are expect to use {@link Timer#schedule(TimerTask, Date, long)} to schedule the task.
     *
     * @param interval the time interval to emit events periodically.
     */
    public void startTick(Duration interval) {
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        var self = this;
        var task = new TimerTask() {
            @Override
            public void run() {
                var now = new Date();
                self.emitEvent(new Event(now));
            }
        };
        timer.schedule(task, 0, interval.toMillis());
    }

    /**
     * TODO 3: Complete the method stopTick
     * Stop the time ticking that is currently working.
     * If no ticker is working, do nothing.
     */
    public void stopTick() {
        if (timer != null) {
            timer.cancel();
        }
        timer = null;
    }
}