# Unit-тестирование операций банковского приложения


## Цель проекта

> Проект по unit-тестированию был выполнен в рамках курса "Инженер по тестированию" от Нетологии. Проект выполнялся в команде из двух тестировщиков. Цель проекта — протестировать приложение для операций с банковскими счетами, отработать навык написания unit-тестов, а также проверки рода и совместной разработки.

**Что входило в работу над проектом:**

- Каждому тестировщику нужно протестировать свою часть проекта
- Составить баг-репорты на найденные дефекты
- Закрыть составленные коллегой-тестировщиком баг-репорты, исправив баги


## Описание проекта

- Логика приложения содержится в классах [Account](https://github.com/softpaw-mango-cat/unit-tests-java/blob/main/src/main/java/ru/netology/javaqadiplom/Account.java), [Bank](https://github.com/softpaw-mango-cat/unit-tests-java/blob/main/src/main/java/ru/netology/javaqadiplom/Bank.java), [CreditAccount](https://github.com/softpaw-mango-cat/unit-tests-java/blob/main/src/main/java/ru/netology/javaqadiplom/CreditAccount.java) и [SavingAccount](https://github.com/softpaw-mango-cat/unit-tests-java/blob/main/src/main/java/ru/netology/javaqadiplom/SavingAccount.java)

- Классы для описания двух видов банковских счетов - сберегательного `SavingAccount` и кредитного `CreditAccount`

- Оба счёта имеют три основные операции, описанные в классе `Account`: пополнение `add`, покупку `pay` и прогноз процентов за год `yearChange`

- В классе `Bank` есть операция перевода `transfer` с одного счёта на другой

- Над каждым методом в коде есть подробное описание того, как он должен работать. При этом часть методов в этих классах не реализована, часть реализована с дефектами

**Задача тестировщиков** — исправить эти дефекты и дописать нереализованные методы.

## Итоги тестирования

- [Unit-тесты CreditAccount](https://github.com/softpaw-mango-cat/unit-tests-java/blob/main/src/test/java/ru/netology/javaqadiplom/CreditAccountTest.java)
- [Unit-тесты SavingAccount](https://github.com/softpaw-mango-cat/unit-tests-java/blob/main/src/test/java/ru/netology/javaqadiplom/SavingAccountTest.java)
- [Баг-репорты | Issues](https://github.com/softpaw-mango-cat/unit-tests-java/issues?q=is%3Aissue%20state%3Aclosed)



