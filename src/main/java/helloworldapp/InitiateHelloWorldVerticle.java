package helloworldapp;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import java.util.concurrent.CompletableFuture;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
public class InitiateHelloWorldVerticle extends AbstractVerticle {
    public void start() throws Exception {

        // This gRPC stubs wrapper talks to the local docker instance of the Temporal service.
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        // WorkflowClient can be used to start, signal, query, cancel, and terminate Workflows.
        WorkflowClient client = WorkflowClient.newInstance(service);
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(Shared.HELLO_WORLD_TASK_QUEUE)
                .build();
        // WorkflowStubs enable calls to methods as if the Workflow object is local, but actually perform an RPC.
        HelloWorldWorkflow workflow = client.newWorkflowStub(HelloWorldWorkflow.class, options);
        // Synchronously execute the Workflow and wait for the response.
        EventBus eb = vertx.eventBus();
        eb.consumer("news.uk.sport", context  -> {

            Convertor msg = (Convertor) context.body();

            CompletableFuture<String> greeting =
                    WorkflowClient.execute(workflow::getGreeting,msg.convertedCurrency,msg.mainCurrency,msg.money);

        });

        //System.exit(0);
    }
}