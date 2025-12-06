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
        CreditAccount account = new CreditAccount(0, 5_000, 15);
        account.pay(200);
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
        CreditAccount account = new CreditAccount(largeValue, 0, 15);
        boolean result = account.add(1000);

        Assertions.assertFalse(result);
        Assertions.assertEquals(largeValue, account.getBalance());
    }

    @Test
    public void DecreaseBalanceByAmountOnSuccessfulPay() {
        CreditAccount account = new CreditAccount(500, 5_000, 15);
        boolean result = account.pay(200);

        Assertions.assertTrue(result);
        Assertions.assertEquals(300, account.getBalance());
    }
    @Test
    public void shouldThrowExceptionWhenRateIsZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(0, 5_000, 0);
        });
    }
    @Test
    public void shouldReturnFalseWhenPayAmountIsZero() {
        CreditAccount account = new CreditAccount(1000, 5000, 15);
        boolean result = account.pay(0);
        Assertions.assertFalse(result);
    }

    @Test
    public void shouldReturnFalseWhenPayAmountIsNegative() {
        CreditAccount account = new CreditAccount(1000, 5000, 15);
        boolean result = account.pay(-100);
        Assertions.assertFalse(result);
    }

    @Test
    public void shouldPayWhenBalanceGoesNegativeWithinLimit() {
        CreditAccount account = new CreditAccount(100, 5000, 15);
        boolean result = account.pay(600); // -500 в пределах лимита
        Assertions.assertTrue(result);
        Assertions.assertEquals(-500, account.getBalance());
    }
    @Test
    public void shouldReturnFalseWhenAddAmountIsZero() {
        CreditAccount account = new CreditAccount(1000, 5000, 15);
        boolean result = account.add(0);
        Assertions.assertFalse(result);
    }

    @Test
    public void shouldReturnFalseWhenAddAmountIsNegative() {
        CreditAccount account = new CreditAccount(1000, 5000, 15);
        boolean result = account.add(-100);
        Assertions.assertFalse(result);
    }

    @Test
    public void shouldAddSuccessfullyWithoutOverflow() {
        CreditAccount account = new CreditAccount(Integer.MAX_VALUE - 100, 5000, 15);
        boolean result = account.add(100);
        Assertions.assertTrue(result);
        Assertions.assertEquals(Integer.MAX_VALUE, account.getBalance());
    }
    @Test
    public void shouldReturnZeroForPositiveBalance() {
        CreditAccount account = new CreditAccount(100, 5000, 15);
        int result = account.yearChange();
        Assertions.assertEquals(0, result);
    }

    @Test
    public void shouldCalculateCorrectlyForNegativeBalance() {
        CreditAccount account = new CreditAccount(0, 5000, 15);
        account.pay(200);
        int result = account.yearChange();
        Assertions.assertEquals(-30, result); // -200 * 15 / 100 = -30
    }

    @Test
    public void shouldHandleLargeNegativeBalance() {
        CreditAccount account = new CreditAccount(0, 10000, 10);
        account.pay(1000);
        int result = account.yearChange();
        Assertions.assertEquals(-100, result); // -1000 * 10 / 100 = -100
    }
}
