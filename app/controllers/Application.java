package controllers;

import models.Project;
import models.Task;
import models.User;
import play.*;
import play.data.*;
import play.mvc.*;


import views.html.*;

public class Application extends Controller {

    @Security.Authenticated(Secured.class)
    public static Result index() {
        return ok(index.render(
                Project.findInvolving(request().username()),
                Task.findTodoInvolving(request().username()),
                User.find.byId(request().username())
        ));
    }

    public static Result login(){
        return ok(
                login.render(Form.form(Login.class))
        );
    }

    public static Result authenticate(){
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        if(loginForm.hasErrors()){
            return badRequest(login.render(loginForm));
        }
        else{
            session().clear();
            session("email", loginForm.get().email);
            return redirect(controllers.routes.Application.index());
        }
    }


    public static class Login{
        public String email;
        public String password;

        public String validate(){
            if (User.authenticate(email, password) == null){
                return "Invalid user or password";
            }
            return null;
        }
    }

    public static Result logout(){
        session().clear();
        flash("success", "You've been logged out");
        return redirect(controllers.routes.Application.login());
    }

    public static Result javascriptRoutes() {
        response().setContentType("text/javascript");
        return ok(
                Routes.javascriptRouter("jsRoutes",
                        controllers.routes.javascript.Projects.add(),
                        controllers.routes.javascript.Projects.delete(),
                        controllers.routes.javascript.Projects.rename(),
                        controllers.routes.javascript.Projects.addGroup()
                )
        );
    }

}
