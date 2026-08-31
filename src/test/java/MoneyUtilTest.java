import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MoneyUtilTest {

    @Test
    public void moneyTest() {
        String money = MoneyUtil.format(1000.0);
        assertEquals("$1000.00", money);
    }

    @Test
    public void negativemoneyTest() {
        String money = MoneyUtil.format(-1000.0);
        assertEquals("-$1000.00", money);
    }
}