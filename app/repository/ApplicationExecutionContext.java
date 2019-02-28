package repository;

import akka.actor.ActorSystem;
import play.libs.concurrent.CustomExecutionContext;

import javax.inject.Inject;

public class ApplicationExecutionContext extends CustomExecutionContext {
    @Inject
    public ApplicationExecutionContext(ActorSystem actorSystem) {
        super(actorSystem, "application.dispatcher");

    }
}
