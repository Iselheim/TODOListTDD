package pl.bolka.aleksander.service.authentication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import pl.bolka.aleksander.manager.authentication.LoginManager;
import pl.bolka.aleksander.model.User;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    @Mock
    private LoginManager loginManager;

    @InjectMocks
    private LoginService loginService;

    @Before
    public void init(){

        User user = getUser();

        Mockito.when(loginManager.getUserByLogin("login")).thenReturn(user);
    }

    @Test
    public void login_shouldReturnFailMessageIfLoginIsNull() throws Exception {
        LoginResult login = loginService.login(null, null);
        assertThat(login,is(not(nullValue())));
        assertThat(login.isSuccess(),is(false));
        assertThat(login.getMessage(),is("Login jest pusty!"));
    }

    @Test
    public void login_shouldReturnFailMessageIfPasswordIsNull(){
        LoginResult loginResult = loginService.login("login", null);
        assertThat(loginResult, is(not(nullValue())));
        assertThat(loginResult.isSuccess(),is(false));
        assertThat(loginResult.getMessage(),is("Hasło jest puste!"));
    }

    @Test
    public void login_shouldReturnFailMessageIfPasswordIsWrong(){
        LoginResult loginResult = loginService.login("login", "bad password");
        assertThat(loginResult.isSuccess(), is(false));
        assertThat(loginResult.getMessage(),is("Błędne hasło!"));
    }

    @Test
    public void login_shouldReturnFailMessageIfUserDoesNotExist(){
        LoginResult result = loginService.login("badLogin", "password");
        assertThat(result.isSuccess(),is(false));
        assertThat(result.getMessage(),is("Użytkownik nie istnieje"));
    }

    @Test
    public void login_shouldReturnSuccesIfLoginAndPasswordAreCorrect(){
        LoginResult result = loginService.login("login", "password");
        assertThat(result.isSuccess(),is(true));
        assertThat(result.getMessage(),is("Zalogowano poprawnie"));
    }

    private User getUser() {
        User user = new User();
        user.setLogin("login");
        user.setPassword("password");
        return user;
    }

}