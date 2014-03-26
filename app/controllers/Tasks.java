package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import java.util.*;

import models.*;
import views.html.tasks.*;

@Security.Authenticated(Secured.class)
public class Tasks extends Controller {
    public static Result index(Long project) {
        if(Secured.isMemberOf(project)) {
            return ok(
                    views.html.tasks.index.render(
                            Project.find.byId(project),
                            Task.findByProject(project)
                    )
            );
        } else {
            return forbidden();
        }
    }
}