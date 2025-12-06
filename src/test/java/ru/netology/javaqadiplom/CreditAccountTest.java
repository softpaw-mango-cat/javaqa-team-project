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
    @Test
    public void NotChangeBalanceIfPayIsMoreThanLimit() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);
        boolean result = account.pay(6_000);

        Assertions.assertFalse(result);
        Assertions.assertEquals(0, account.getBalance());
    }
    @Test
    public void shouldAddToExistingBalance() {
        CreditAccount account = new CreditAccount(1_000, 5_000, 15);
        boolean result = account.add(500);

        Assertions.assertTrue(result);
        Assertions.assertEquals(1_500, account.getBalance());
    }
    @Test
    public void CalculateYearChangeForNegativeBalance() {
        CreditAccount account = new CreditAccount(-200, 5_000, 15);
        int actual = account.yearChange();
        int expected = -30;

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void CorrectlyCalculateBalanceAfterPay() {
        CreditAccount account = new CreditAccount(100, 5_000, 15);
        boolean result = account.pay(200);

        Assertions.assertTrue(result);
        Assertions.assertEquals(-100, account.getBalance());
    }
    @Test
    public void shouldHandleExtremelyLargeAmount() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);
        boolean result = account.pay(Integer.MAX_VALUE);

        Assertions.assertFalse(result);
        Assertions.assertEquals(0, account.getBalance());
    }
    @Test
    public void NotOverflowOnLargeValues() {

        int largeValue = Integer.MAX_VALUE - 100;
        CreditAccount account = new CreditAccount(largeValue, 0, 0);
        account.add(1000);

        Assertions.assertTrue(account.getBalance() > 0);
    }
    @Test
    public void DecreaseBalanceByAmountOnSuccessfulPay() {
        CreditAccount account = new CreditAccount(500, 5_000, 15);
        boolean result = account.pay(200);

        Assertions.assertTrue(result);
        Assertions.assertEquals(300, account.getBalance());
    }
}
