package helloworldapp;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class HelloWorldWorkflowImpl implements HelloWorldWorkflow {

    ActivityOptions options = ActivityOptions.newBuilder()
            .setScheduleToCloseTimeout(Duration.ofSeconds(10))
            .build();

    // ActivityStubs enable calls to Activities as if they are local methods, but actually perform an RPC.

    private final Api currency=Workflow.newActivityStub(Api.class,options);

    @Override
    public String getGreeting(String name) {
      

        return currency.receiveApi(name);
}
}