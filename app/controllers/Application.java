package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

    public Result index() {
        return ok("Your new applications is ready.");
    }

    public Result hello() {
        return ok("Your applications is ready");
    }

}
