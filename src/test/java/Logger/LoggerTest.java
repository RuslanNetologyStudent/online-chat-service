package Logger;

import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LoggerTest {
    private static final Logger logger = Logger.getInstance();

    @Test
    public void getInstanceTestNotNull() {
        Assert.assertNotNull(logger);
    }

    @Test
    public void getInstanceTest() {
        Assert.assertEquals(Logger.getInstance(), logger);
    }

    @Test
    public void formatMessageLogTest() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String expected = "[" + formatter.format(calendar.getTime()) + " ] : " + "test";
        Assert.assertEquals(expected, logger.formatMessageLog("test"));
    }


}