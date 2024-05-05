_**Задача для аттестациия**_
Имеется некая система, которая обрабатывает авиаперелёты.

   **Перелёт** — это перевозка пассажира из одной точки в другую с возможными промежуточными посадками.

Это значит, что перелёт можно представить как набор из одного или нескольких элементарных перемещений, называемых сегментами.

  **Сегмент** — это атомарная перевозка, которую для простоты будем характеризовать всего двумя атрибутами: дата/время вылета и дата/время прилёта.

Ваша задача — написать модуль, который будет заниматься фильтрацией набора перелётов согласно различным правилам.

    Правил фильтрации может быть очень много.

    Наборы перелётов также могут быть очень большими.

    Правила могут выбираться и задаваться динамически в зависимости от контекста выполнения операции фильтрации.
**Что нужно сделать?**
Продумать структуру модуля, создать необходимые классы и интерфейсы. Если знакомы с JUnit — покрыть свой код тестами. 
Пользовательский интерфейс не рассматривайте. 
Достаточно вывода информации в консоль.
Никаких сторонних библиотек использовать не нужно.
**Что нужно учесть?**
 Для проверочного запуска создать публичный класс Main c методом main(). Этот метод должен выдать в консоль результаты обработки тестового набора перелётов. Получить тестовый набор нужно методом FlightBuilder.createFlights().
    Исключите из тестового набора перелёты по следующим правилам (по каждому правилу нужен отдельный вывод списка перелётов):
   _    1. Вылет до текущего момента времени_.
      _ 2. Сегменты с датой прилёта раньше даты вылета._
_       3. Перелеты, где общее время, проведённое на земле, превышает два часа (время на земле — это интервал между прилётом одного сегмента и вылетом следующего за ним)._
