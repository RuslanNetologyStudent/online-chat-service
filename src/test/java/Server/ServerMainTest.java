package Server;

import org.junit.Assert;
import org.junit.Test;

public class ServerMainTest {
    @Test
    public void goTest() {
        Assert.assertNotNull(Server.threadList);
    }

}