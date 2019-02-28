package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public Result index() {
        return ok("Your new application is ready.");
    }

    public Result hello() {
        return ok("Your application is ready");
    }

}
