
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class MoneyUtilTest {
    @Test
    public void moneyTest() {
        String money = MoneyUtil.format(1000.0);
        Assertions.assertEquals("$1000.00" ,money);
    }
    @Test
    public void negativemoneyTest() {
        String money = MoneyUtil.format(-1000.0);
        Assertions.assertEquals("-$1000.00" ,money);
    }

}
