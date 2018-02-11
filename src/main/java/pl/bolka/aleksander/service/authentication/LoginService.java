package pl.bolka.aleksander.service.authentication;

import pl.bolka.aleksander.manager.authentication.LoginManager;
import pl.bolka.aleksander.model.User;
import pl.bolka.aleksander.util.OptionalConsumer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginService {

    @Inject
    private LoginManager loginManager;

    public LoginResult login(String login, String password) {
        LoginResult loginResult = new LoginResult();

        OptionalConsumer.of(loginManager.getUserByLogin(login))
                .ifPresent(user1 -> tryLoginUser(password,loginResult,user1))
                .ifNotPresent(() -> userNotExist(loginResult));

        return loginResult;

    }

    private void userNotExist(LoginResult loginResult) {
        loginResult.setSuccess(false);
        loginResult.setMessage("Użytkownik nie istnieje");
    }

    private void tryLoginUser(String password, LoginResult loginResult, User user) {
        if (user.getPassword().equals(password)) {
            loginResult.setSuccess(true);
            loginResult.setMessage("Zalogowano poprawnie");

        }else {
            loginResult.setSuccess(false);
            loginResult.setMessage("Błędne hasło!");

        }
    }


}
