package Setting;

import org.junit.Assert;
import org.junit.Test;

public class SettingTest {
    private static final int setting = Setting.readPort();

    @Test
    public void readPortTest() {
        int expected = 8090;
        Assert.assertEquals(expected, setting);
    }

    @Test
    public void readPortTestNotNull() {
        Assert.assertNotNull(setting);

    }

}