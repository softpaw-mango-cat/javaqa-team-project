package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void InitialBalanceIsNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(-100, 5_000, 15);
        });
    }

    @Test
    public void CreditLimitIsNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(0, -100, 15);
        });
    }
}