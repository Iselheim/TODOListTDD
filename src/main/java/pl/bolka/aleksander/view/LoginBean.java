package pl.bolka.aleksander.view;

import org.apache.log4j.Logger;
import org.omnifaces.cdi.ViewScoped;
import pl.bolka.aleksander.service.authentication.LoginResult;
import pl.bolka.aleksander.service.authentication.LoginService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class LoginBean implements Serializable {

    private static Logger logger = Logger.getLogger(LoginBean.class);

    private String login;

    private String password;

    @Inject
    private LoginService loginService;

    @PostConstruct
    public void init(){

    }

    public void loginAction(){
        LoginResult result = loginService.login(login, password);

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
