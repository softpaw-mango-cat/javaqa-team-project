package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    /**
     * проверки метода add()
     * - нельзя добавить отрицательное значение
     * (при добавлении отрицательного баланс не меняется)
     * - нельзя добавить нулевое значение
     * (при добавлении нулевого значения баланс не меняется)
     * --- граничные значения ---
     * - нельзя добавить сумму при которой баланс станет больше максимального
     * (при макс. балансе 10000 проверяем значение при котором он станет 10001)
     * - можно добавить сумму при которой баланс станет равен максимальному
     * (при макс. балансе 10000 проверяем значение при котором он станет ровно 10000)
     * - можно добавить сумму при которой баланс станет меньше максимального
     * (при макс. балансе 10000 проверяем значение при котором он станет 999)
     */
    @Test
    public void shouldNotAddNegativeValue() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(-1);
        int expected = 2_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotAddZeroValue() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(0);
        int expected = 2_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotAddMoreThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        int limit = account.maxBalance + 1; // 10001
        int addAmount = limit - account.balance; // 8001

        account.add(addAmount);

        int expected = 2_000;
        int actual = account.getBalance();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldAddEqualsToMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        int limit = account.maxBalance; // 10000
        int addAmount = limit - account.balance; // 8000

        account.add(addAmount);
        int expected = 10_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        int limit = account.maxBalance - 1; // 9999
        int addAmount = limit - account.balance; // 7999

        account.add(addAmount);

        int expected = 9_999;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    /**
     * проверки метода pay()
     * - сумма покупки не может быть отрицательной
     * - сумма покупки не может быть нулевой
     * --- граничные значения ---
     * - нельзя сделать покупку при которой баланс станет меньше минимального
     * (при мин. балансе 1000 проверяем значение при котором он станет 999)
     * - можно сделать покупку при которой баланс станет равен минимальному
     * (при мин. балансе 1000 проверяем значение при котором он станет ровно 1000)
     * - можно сделать покупку при которой баланс станет больше минимального
     * (при мин. балансе 1000 проверяем значение при котором он станет ровно 1001)
     */

    @Test
    public void shouldNotSubtractNegativeValue() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(-1);
        int expected = 2_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotSubtractZeroValue() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(0);
        int expected = 2_000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotSubtractLessThanMinBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        int limit = account.minBalance - 1; // 999
        int payAmount = account.balance - limit; // 1001

        account.pay(payAmount);

        int expected = 2_000;
        int actual = account.getBalance();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSubtractEqualToMinBalance() {
        SavingAccount account = new SavingAccount(
                3_000,
                1_000,
                10_000,
                5
        );

        int limit = account.minBalance; // 1000
        int payAmount = account.balance - limit; // 2000

        account.pay(payAmount);

        int expected = 1_000;
        int actual = account.getBalance();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldSubtractMoreThanMinBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        int limit = account.minBalance + 1; // 1001
        int payAmount = account.balance - limit; // 999

        account.pay(payAmount);

        int expected = 1_001;
        int actual = account.getBalance();
        Assertions.assertEquals(expected, actual);
    }
}
