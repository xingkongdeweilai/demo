package com.imooc.demo.demo30;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class UrlTest {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://web.travelsky.com/siat-order-manager/manager/login");
        Scanner input = new Scanner(url.openStream());
        while (input.hasNext()) {
            System.out.println(input.nextLine());
        }
    }
}
