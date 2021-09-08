package helloworldapp;
import io.temporal.activity.Activity;
import io.temporal.activity.ActivityExecutionContext;
import io.temporal.client.ActivityCompletionClient;

public  class GreetingActivitiesImpl implements GreetingActivities {
    private final ActivityCompletionClient completionClient;

    GreetingActivitiesImpl(ActivityCompletionClient completionClient) {
      this.completionClient = completionClient;
    }
    @Override
    public String composeActivity(String greeting, String name) {
      ActivityExecutionContext context = Activity.getExecutionContext();
      byte[] taskToken = context.getTaskToken(); 
      String result = greeting + " " + name + "!";
      completionClient.complete(taskToken, result);
      context.doNotCompleteOnReturn();
      return "ignored";
    }
 
  }