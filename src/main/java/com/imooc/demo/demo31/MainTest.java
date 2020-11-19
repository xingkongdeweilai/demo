package com.imooc.demo.demo31;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MainTest {

    public static void main(String[] args) {
        System.out.println(NumberFormat.getCurrencyInstance(Locale.CHINA).format(5000.56));
        System.out.println(NumberFormat.getCurrencyInstance(Locale.CHINESE).format(5000.56));
        System.out.println(NumberFormat.getCurrencyInstance(Locale.SIMPLIFIED_CHINESE).format(5000.56));
        System.out.println(NumberFormat.getCurrencyInstance(Locale.TRADITIONAL_CHINESE).format(5000.56));
        System.out.println(NumberFormat.getCurrencyInstance(Locale.US).format(5000.56));

        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance();
        decimalFormat.applyPattern("00.0##");
        System.out.println(decimalFormat.format(1.3676));
    }

}
