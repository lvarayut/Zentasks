package controllers;

import models.Project;
import play.mvc.Result;
import play.mvc.Security;
import play.*;
import play.mvc.Http;
public class Secured extends Security.Authenticator{
    @Override
    public String getUsername(Http.Context ctx) {
        return ctx.session().get("email");
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        return redirect(controllers.routes.Application.login());
    }

    public static boolean isMemberOf(Long project) {
        return Project.isMember(
                project,
                Http.Context.current().request().username()
        );
    }
}