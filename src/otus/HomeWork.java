package otus;

import otus.game.*;
/*

class Assertions {
    public static void assertEquals(int expected, int actual) {
        if (expected != actual) {
            throw new AssertionError(String.format("Expected %d = %d", expected, actual));
        }
    }

    public static void assertEquals(long expected, long actual) {
        if (expected != actual) {
            throw new AssertionError(String.format("Expected %d = %d", expected, actual));
        }
    }

    public static void assertEquals(String expected, String actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError(String.format("Expected \"%s\" = \"%s\"", expected, actual));
        }
    }

    public static void assertEquals(Class<? extends Throwable> expected, Class<? extends Throwable> actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError(String.format("Expected \"%s\" = \"%s\"", expected, actual));
        }
    }

    public static void assertThrows(Class<? extends Throwable> expected, Runnable code) {
        Throwable actual = null;
        try {
            code.run();
        } catch (Throwable e) {
            actual = e;
        }
        if (actual == null) {
            throw new AssertionError("Given code does not throw any exception");
        }

        if (!actual.getClass().equals(expected)) {
            throw new AssertionError(String.format("Expected \"%s\" = \"%s\"", expected.getSimpleName(), actual.getClass().getSimpleName()));
        }
    }

}

*/

public class HomeWork {
    public static class DiceTest1 {
        public void test1() {
            String firstVariant = "Тест 1";
            try {
                DiceImpl diceNew = new DiceImpl();
                int x = diceNew.roll();
                if (!(x >= 1 && x <= 6)) {
                    RuntimeException error = new RuntimeException("Кубик выбросил чило не в диапазоне 1-6");
                    throw error; // выбрасываем ошибку throw new RuntimeException("Кубик выбросил чило не в диапазоне 1-6");
                }
                System.out.printf("\"%s\" passed %n", firstVariant);
            } catch (Throwable e) {
                System.err.printf("\"%s\" fails with message \"%s\" %n", firstVariant, e.getMessage());
            }
        }
    }

    public static class GameTest2 {
        public void test2() {
            class DiceImplTest implements Dice {
                //private int i =0;
                @Override
                public int roll() {
                    return 1;
                    //i = i+1;
                    //return i;
                }
            }
            class ConsolePrinter implements GameWinnerPrinter{

                @Override
                public void printWinner(Player winner) {
                    if (winner != null) {
                        throw new RuntimeException("При одинаковом выпадении очков не может быть победителя");
                    }
                    //winner.getName().equals("Игорь");
                }
            }
            String firstVariant = "Тест 2";
            try {
                DiceImplTest x = new DiceImplTest();
                ConsolePrinter z = new ConsolePrinter();
                Game y = new Game(x,z);
//                new Game(() -> 1, (winner) -> {
//                    if (winner != null) {
//                        throw new RuntimeException("При одинаковом выпадении очков не может быть победителя");
//                    }
//                });
                y.playGame(new Player("Вася"), new Player("Игорь"));

                System.out.printf("\"%s\" passed %n", firstVariant);
            } catch (Throwable e) {
                System.err.printf("\"%s\" fails with message \"%s\" %n", firstVariant, e.getMessage());
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

        new DiceTest1().test1();
        new GameTest2().test2();

    }

}


    
