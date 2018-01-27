package pl.bolka.aleksander.service.authentication;

import pl.bolka.aleksander.manager.authentication.LoginManager;
import pl.bolka.aleksander.model.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginService {

    @Inject
    private LoginManager loginManager;

    //TODO jak to upiększyć?
    public LoginResult login(String login, String password) {
        LoginResult loginResult = new LoginResult();
        if (null == login || login.isEmpty()) {
            loginResult.setSuccess(false);
            loginResult.setMessage("Login jest pusty!");
            return loginResult;
        }
        if (null == password || password.isEmpty()){
            loginResult.setSuccess(false);
            loginResult.setMessage("Hasło jest puste!");
            return loginResult;
        }
        User user = loginManager.getUserByLogin(login);

        if (null == user){
            loginResult.setSuccess(false);
            loginResult.setMessage("Użytkownik nie istnieje");
            return loginResult;
        }

        if (user.getPassword().equals(password)) {
            loginResult.setSuccess(true);
            loginResult.setMessage("Zalogowano poprawnie");
            return loginResult;
        }else {
            loginResult.setSuccess(false);
            loginResult.setMessage("Błędne hasło!");
            return loginResult;
        }

    }


}
