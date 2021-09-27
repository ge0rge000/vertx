package helloworldapp;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;
import java.util.Scanner;

public class HelloWorldWorkflowImpl implements HelloWorldWorkflow {

    ActivityOptions options = ActivityOptions.newBuilder()
            .setScheduleToCloseTimeout(Duration.ofSeconds(10))
            .build();

    // ActivityStubs enable calls to Activities as if they are local methods, but actually perform an RPC.

    private final Api currency=Workflow.newActivityStub(Api.class,options);


    @Override
    public String getGreeting(String name,String country_main,String price) {

        return currency.receiveApi(name,country_main,price);
    }
}