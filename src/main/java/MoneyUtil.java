import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MoneyUtil {

    public static String format(double money) {
        String symbol ="$";

        if (money<0){
            symbol = "-" + symbol;
            money = money *(-1);
        }
        BigDecimal rounded =BigDecimal.valueOf(money).setScale(2,BigDecimal.ROUND_HALF_UP);
        return symbol +rounded;


    }



}
