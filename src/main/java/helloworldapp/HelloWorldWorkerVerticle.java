package helloworldapp;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import io.temporal.client.ActivityCompletionClient;

import io.vertx.core.AbstractVerticle;

import io.vertx.core.Vertx;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloWorldWorkerVerticle extends AbstractVerticle   {
    public void start() throws Exception {


        // This gRPC stubs wrapper talks to the local docker instance of the Temporal service.
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);
        // Create a Worker factory that can be used to create Workers that poll specific Task Queues.
        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker(Shared.HELLO_WORLD_TASK_QUEUE);
        // This Worker hosts both Workflow and Activity implementations.
        // Workflows are stateful, so you need to supply a type to create instances.
        worker.registerWorkflowImplementationTypes(HelloWorldWorkflowImpl.class);
        // Activities are stateless and thread safe, so a shared instance is used.
        ActivityCompletionClient completionClient = client.newActivityCompletionClient();

        worker.registerActivitiesImplementations( new FormatImpl()
                , new Apimpl(vertx, completionClient), new GreetingActivitiesImpl(completionClient),
                new PostApimpl(vertx,completionClient ));

        // Start polling the Task Queue.
        factory.start();
    }
}