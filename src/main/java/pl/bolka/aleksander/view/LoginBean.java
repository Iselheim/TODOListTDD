package pl.bolka.aleksander.view;

import org.apache.log4j.Logger;
import org.omnifaces.cdi.ViewScoped;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class LoginBean implements Serializable {

    private static Logger logger = Logger.getLogger(LoginBean.class);

    private String login;

    private String password;

    @PostConstruct
    public void init(){
        logger.info("constructor");
    }

    public void loginAction(){
        logger.info("login: " + login + ", password: " + password);
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
