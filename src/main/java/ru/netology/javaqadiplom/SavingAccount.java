package ru.netology.javaqadiplom;

/**
 * Сберегательный счёт
 * Может иметь баланс только в пределах от указанного минимального до указанного максимального включительно.
 * Не может уходить в минус (минимальный баланс не может быть отрицательным).
 * Имеет ставку - количество процентов годовых на остаток.
 */
public class SavingAccount extends Account {
    protected int minBalance;
    protected int maxBalance;

    /**
     * Создаёт новый объект сберегательного счёта с заданными параметрами.
     * Если параметры некорректны (мин. баланс больше максимального и так далее), то
     * должно выкидываться исключения вида IllegalArgumentException.
     * @param initialBalance - начальный баланс
     * @param minBalance - минимальный баланс
     * @param maxBalance - максимальный баланс
     * @param rate - неотрицательное число, ставка в процентах годовых на остаток
     */
    // Issue #5, 6, 7, 8, 11, 14, 15, 16
    public SavingAccount(int initialBalance, int minBalance, int maxBalance, int rate) {
        // 5. Проверка ставки
        if (rate < 0) {
            throw new IllegalArgumentException(
                    "Накопительная ставка не может быть отрицательной, а у вас: " + rate
            );
        }
        if (rate == 0) {
            throw new IllegalArgumentException(
                    "Накопительная ставка не может быть нулевой, а у вас: " + rate
            );
        }

        // 6. Проверка minBalance на отрицательное
        if (minBalance < 0) {
            throw new IllegalArgumentException(
                    "Минимальный баланс не может быть отрицательным: " + minBalance
            );
        }
        // 7. Проверка maxBalance на отрицательное
        if (maxBalance < 0) {
            throw new IllegalArgumentException(
                    "Максимальный баланс не может быть отрицательным: " + maxBalance
            );
        }
        // 8. Проверка minBalance > maxBalance
        if (minBalance > maxBalance) {
            throw new IllegalArgumentException(
                    "Минимальный баланс не может быть больше максимального"
            );
        }
        // 11. Проверка minBalance == maxBalance
        if (minBalance == maxBalance) {
            throw new IllegalArgumentException(
                    "Минимальный баланс не может быть равен максимальному"
            );
        }
        // 14. Проверка initialBalance на отрицательное
        if (initialBalance < 0) {
            throw new IllegalArgumentException(
                    "Баланс счёта не может быть отрицательным: " + initialBalance
            );
        }
        // 15. Проверка initialBalance < minBalance
        if (initialBalance < minBalance) {
            throw new IllegalArgumentException(
                    "Баланс счёта не может быть меньше минимального"
            );
        }
        // 16. Проверка initialBalance > maxBalance
        if (initialBalance > maxBalance) {
            throw new IllegalArgumentException(
                    "Баланс счёта не может быть больше максимального"
            );
        }

        this.balance = initialBalance;
        this.minBalance = minBalance;
        this.maxBalance = maxBalance;
        this.rate = rate;
    }

    /**
     * Операция оплаты с карты на указанную сумму.
     * В результате успешного вызова этого метода, баланс должен уменьшиться
     * на сумму покупки. Если же операция может привести к некорректному
     * состоянию счёта (например, баланс может уйти в минус), то операция должна
     * завершиться вернув false и ничего не поменяв на счёте.
     * @param amount - сумма покупки
     * @return true если операция прошла успешно, false иначе.
     */
    // Issue #4
    @Override
    public boolean pay(int amount) {
        if (amount <= 0) {
            return false;
        }

        // Сначала проверяем, не станет ли баланс меньше минимального
        if (balance - amount >= minBalance) {  // Проверка ДО изменения
            balance = balance - amount;        // Только если проверка пройдена
            return true;
        } else {
            return false;                      // Без изменения баланса
        }
    }

    /**
     * Операция пополнения карты на указанную сумму.
     * В результате успешного вызова этого метода, баланс должен увеличиться
     * на сумму покупки. Если же операция может привести к некорректному
     * состоянию счёта, то операция должна
     * завершиться вернув false и ничего не поменяв на счёте.
     * @param amount - сумма пополнения
     * @return true если операция прошла успешно, false иначе.
     * @param amount
     * @return
     */
    // Issue #2, #3
    @Override
    public boolean add(int amount) {
        if (amount <= 0) {
            return false;
        }
        // Проверяем, не превысит ли пополнение максимальный баланс
        if (balance + amount <= maxBalance) {  // Изменено с < на <=
            balance = balance + amount;  //  добавляем amount к balance
            return true;
        } else {
            return false;
        }
    }

    /**
     * Операция расчёта процентов на остаток счёта при условии, что
     * счёт не будет меняться год. Сумма процентов приводится к целому
     * числу через отбрасывание дробной части (так и работает целочисленное деление).
     * Пример: если на счёте 200 рублей, то при ставке 15% ответ должен быть 30.
     * @return
     */
    @Override
    public int yearChange() {
        return balance / 100 * rate;
    }

    public int getMinBalance() {
        return minBalance;
    }

    public int getMaxBalance() {
        return maxBalance;
    }
}
