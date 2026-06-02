package com.banking.common;

import java.util.Random;
import java.util.UUID;

public class CommonBase {

    public static String generateAccountNumber() {

        Random random = new Random();

        long accountNo = 10000000000L + (long) (random.nextDouble() * 90000000000L);

        return String.valueOf(accountNo);
    }

    public static String generateCustomerId() {

        return "CUS" +
                UUID.randomUUID()
                        .toString()
                        .replace("-", "")
                        .substring(0, 7)
                        .toUpperCase();
    }
}
