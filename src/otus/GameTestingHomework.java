
package otus;

import otus.game.*;

public class GameTestingHomework {
    public static class DiceTest1 {
        public void test1() {
            String firstVariant = "Тест 1. Проверка на  выпадающие значения кубика в диапазон от 1 до 6";
            try {
                DiceImpl diceNew = new DiceImpl();
                int x = diceNew.roll();
                if (!(x >= 1 && x <= 6)) {
                    RuntimeException errorTest1 = new RuntimeException("Кубик выбросил число не попадающее в диапазоне 1-6.");
                    throw errorTest1; // выбрасываем ошибку throw new RuntimeException("Кубик выбросил число не в диапазоне 1-6");
                }
                System.out.printf("\"%s\" Результат: Тест пройден! %n", firstVariant);
            } catch (Throwable e) {
                System.err.printf("\"%s\" Результат: Ошибка! - \"%s\" %n", firstVariant, e.getMessage());
            }
        }
    }

    public static class GameTest2 {
        public void test2() {
            class DiceImplTest implements Dice {
                //заново имплиментируем интерфейс Dice,
                // таким образом, что бы у нас "выпадало" одно и тоже чило.
                //т.е. нет победителя - ничья
                // вы данном примере значение кубика = 1.
                @Override
                public int roll() {
                    return 1;
                }
            }
            class ConsolePrinter implements GameWinnerPrinter {
                //заново имплиментируем интерфейс GameWinnerPrinter,
                //таким образом, что бы в случае, если программа все таки выберет победителя,
                // выводилось сообщение об ошибке.
                @Override
                public void printWinner(Player winner) {
                    if (winner != null) {
                        throw new RuntimeException("При одинаковом выпадении очков не может быть победителя");
                    }
                }
            }
            String secondVariant = "Тест 2. Проверка для случая, когда выпадают оинакоые значения кубика.";
            try {
                DiceImplTest diceImplTest2 = new DiceImplTest();
                ConsolePrinter consolePrinterTest2 = new ConsolePrinter();
                Game gameTest2 = new Game(diceImplTest2, consolePrinterTest2);
                gameTest2.playGame(new Player("Вася"), new Player("Игорь"));

                System.out.printf("\"%s\" Результат: Тест пройден! %n", secondVariant);
            } catch (Throwable e) {
                System.err.printf("\"%s\" Результат: Ошибка! -  \"%s\" %n", secondVariant, e.getMessage());
            }
        }
    }

    public static class GameTest3 {
        public void test3() {

            class DiceImplTest3 implements Dice {
                // имплиментируем интерфейс Dice,
                // таким образом, что бы у нас "выпадала" сначала 5
                //а во второй раз 6
                // т.е победителем должен быть игрок 2  - Игорь.
                private int i = 5;

                @Override
                public int roll() {
                    i = i + 1;
                    return i;
                }

            }

            class ConsolePrinter3 implements GameWinnerPrinter {
                // имплиментируем интерфейс GameWinnerPrinter,
                //таким образом, что бы в случае, если программа выберет победителем победителем первого игрока,
                // выводилось сообщение об ошибке.
                @Override
                public void printWinner(Player winner) {
                    System.out.println("Тест 3. Фиксируем выпадание большего значения кубика у игрока 2");
                    System.out.printf("Победитель: %s%n", winner.getName());
                    if (!(winner.getName() == "Игорь")) {
                        throw new RuntimeException("Ошибка, победитель - Игорь");
                    }
                }
            }
            String thirdVariant = "Тест 3. Фиксируем выпадание большего значения кубика у игрока 2";
            try {
                DiceImplTest3 diceImplTest3 = new DiceImplTest3();
                ConsolePrinter3 winnerPrinter = new ConsolePrinter3();
                Game gameTest3 = new Game(diceImplTest3, winnerPrinter);
                gameTest3.playGame(new Player("Вася"), new Player("Игорь"));

                System.out.printf(" Результат: Тест пройден! %n");
            } catch (Throwable e) {
                System.err.printf("\"%s\" Результат: Ошибка! -   \"%s\" %n", thirdVariant, e.getMessage());
            }
        }
    }

    public static class GameTest4 {
        public void test4() {

            class DiceImplTest4 implements Dice {
                // имплиментируем интерфейс Dice,
                // таким образом, что бы у нас "выпадала" сначала 5
                //а во второй раз 4
                // т.е победителем должен быть игрок 1  - Вася.
                private int i = 5;

                @Override
                public int roll() {
                    i = i - 1;
                    return i;
                }

            }

            class ConsolePrinter4 implements GameWinnerPrinter {
                // имплиментируем интерфейс GameWinnerPrinter,
                //таким образом, что бы в случае, если программа выберет победителем победителем второго игрока,
                // выводилось сообщение об ошибке.
                @Override
                public void printWinner(Player winner) {
                    System.out.println("Тест 4. Фиксируем выпадание большего значения кубика у игрока 1");
                    System.out.printf("Победитель: %s%n", winner.getName());
                    if (!(winner.getName() == "Вася")) {
                        throw new RuntimeException("Ошибка, победитель - Вася");
                    }
                }
            }
            String thirdVariant = "Тест 4. Фиксируем выпадание большего значения кубика у игрока 1";
            try {
                DiceImplTest4 diceImplTest3 = new DiceImplTest4();
                ConsolePrinter4 winnerPrinter = new ConsolePrinter4();
                Game gameTest3 = new Game(diceImplTest3, winnerPrinter);
                gameTest3.playGame(new Player("Вася"), new Player("Игорь"));

                System.out.printf(" Результат: Тест пройден! %n");
            } catch (Throwable e) {
                System.err.printf("\"%s\" Результат: Ошибка! -   \"%s\" %n", thirdVariant, e.getMessage());
            }
        }
    }

    /*
        Заготовка для ДЗ представляет собой игру в кости.
        При вызове game.playGame(), два игрока кидают кости.
        Выигрывает игрок, у кого результат (1-6) больше

        Написать тесты (минимум 4) на классы DiceImpl и Game.
        Тесты должны найти не менее двух ошибок.

        Информацию о пройденном тесте предлагается выводить в System.out, а об упавшем в System.err
     */


    public static void main(String[] args) {

        new otus.GameTestingHomework.DiceTest1().test1();
        new otus.GameTestingHomework.GameTest2().test2();
        new otus.GameTestingHomework.GameTest3().test3();
        new otus.GameTestingHomework.GameTest4().test4();

    }

}






