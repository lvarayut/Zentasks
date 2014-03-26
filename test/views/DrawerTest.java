package views;

import org.junit.*;

import play.test.*;
import static play.test.Helpers.*;

import org.fluentlenium.core.*;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.fluentlenium.FluentLeniumAssertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import com.google.common.base.*;

import pages.*;
import components.*;

public class DrawerTest extends WithBrowser {

    public Drawer drawer;

    @Before
    public void setUp() {
        start();
        Login login = browser.createPage(Login.class);
        login.go();
        login.login("bob@example.com", "secret");
        drawer = browser.createPage(Dashboard.class).drawer();
    }


}