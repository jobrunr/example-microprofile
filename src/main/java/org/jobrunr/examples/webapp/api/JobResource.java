package org.jobrunr.examples.webapp.api;

import org.jobrunr.examples.services.MyService;
import org.jobrunr.examples.services.MyServiceInterface;
import org.jobrunr.jobs.context.JobContext;
import org.jobrunr.scheduling.JobScheduler;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("jobs")
@ApplicationScoped
public class JobResource {

    @Inject
    private MyServiceInterface myService;
    @Inject
    private JobScheduler jobScheduler;

    @GET
    @Path("/simple-job")
    @Produces(MediaType.APPLICATION_JSON)
    public SimpleResponse simpleJob(@DefaultValue("World") @QueryParam("name") String name) {
        jobScheduler.<MyService>enqueue(myService -> myService.doSimpleJob("Hello " + name));

        return new SimpleResponse("Job Enqueued");
    }

    @GET
    @Path("/simple-job-instance")
    @Produces(MediaType.APPLICATION_JSON)
    public SimpleResponse simpleJobUsingInstance(@DefaultValue("World") @QueryParam("name") String name) {
        jobScheduler.enqueue(() -> myService.doSimpleJob("Hello " + name));

        return new SimpleResponse("Job Enqueued");
    }

    @GET
    @Path("/long-running-job")
    @Produces(MediaType.APPLICATION_JSON)
    public SimpleResponse longRunningJob(@DefaultValue("World") @QueryParam("name") String name) {
        jobScheduler.<MyService>enqueue(myService -> myService.doLongRunningJob("Hello " + name));

        return new SimpleResponse("Job Enqueued");
    }

    @GET
    @Path("/long-running-job-with-job-context")
    @Produces(MediaType.APPLICATION_JSON)
    public SimpleResponse longRunningJobWithJobContext(@DefaultValue("World") @QueryParam("name") String name) {
        jobScheduler.<MyService>enqueue(myService -> myService.doLongRunningJobWithJobContext("Hello " + name, JobContext.Null));

        return new SimpleResponse("Job Enqueued");
    }

}
