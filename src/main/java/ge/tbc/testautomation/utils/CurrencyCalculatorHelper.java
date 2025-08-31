package ge.tbc.testautomation.utils;

import ge.tbc.testautomation.data.Constants;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyCalculatorHelper {

    public String getSellRate(String rate) {
        double rateDouble = Double.parseDouble(rate);
        double sellRate = Constants.DEFAULT_AMOUNT / rateDouble /100;
        BigDecimal rateBD = BigDecimal.valueOf(sellRate).setScale(4, RoundingMode.HALF_UP).stripTrailingZeros();
        return rateBD.toPlainString();
//        return String.format("%.4f", sellRate);
    }
}
