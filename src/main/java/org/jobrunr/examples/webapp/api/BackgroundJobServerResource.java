package org.jobrunr.examples.webapp.api;

import org.jobrunr.dashboard.JobRunrDashboardWebServer;
import org.jobrunr.server.BackgroundJobServer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("jobrunr")
@ApplicationScoped
public class BackgroundJobServerResource {

    @Inject
    private BackgroundJobServer backgroundJobServer;
    @Inject
    private JobRunrDashboardWebServer dashboard;

    @GET
    @Path("/start")
    @Produces(MediaType.APPLICATION_JSON)
    public SimpleResponse start() {
        backgroundJobServer.start();
        dashboard.start();
        return new SimpleResponse("BackgroundJobServer and JobRunr Dashboard started");
    }

    @GET
    @Path("/stop")
    @Produces(MediaType.APPLICATION_JSON)
    public SimpleResponse stop() {
        dashboard.stop();
        backgroundJobServer.stop();

        return new SimpleResponse("BackgroundJobServer and JobRunr Dashboard stopped");
    }

}
