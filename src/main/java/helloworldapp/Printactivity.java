package helloworldapp;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface

public interface Printactivity {
    @ActivityMethod
    String printActivity(String print);

}
