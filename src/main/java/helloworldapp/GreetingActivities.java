package helloworldapp;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface  GreetingActivities {

    @ActivityMethod
    String composeActivity(String greeting, String name);
}