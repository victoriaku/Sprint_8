import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AccountNameTest {

    String description;
    String name;
    boolean isValid;

    public AccountNameTest(String description, String name, boolean isValid) {
        this.description = description;
        this.name = name;
        this.isValid = isValid;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0} - \"{1}\", валидна - {2}")
    public static Object[][] names(){
        Faker faker = new Faker();
        return new Object[][]{
                {"Строка длиной 3 символа", faker.regexify("[а-я]{1} [а-я]{1}"), true},
                {"Строка длиной 4 символа", faker.regexify("[а-я]{2} [а-я]{1}"), true},
                {"Строка длиной 10 символов", faker.regexify("[а-я]{5} [а-я]{4}"), true},
                {"Строка длиной 18 символов", faker.regexify("[а-я]{6} [а-я]{11}"), true},
                {"Строка длиной 19 символов", faker.regexify("[а-я]{7} [а-я]{11}"), true},
                {"Пустая строка", "", false},
                {"Строка длиной 1 символ", faker.regexify("[а-я]{1}"), false},
                {"Строка длиной 2 символа",faker.regexify("[а-я]{2}"), false},
                {"Строка длиной 20 символов", faker.regexify("[а-я]{8} [а-я]{11}"), false},
                {"Строка длиной 21 символ", faker.regexify("[а-я]{9} [а-я]{11}"), false},
                {"Строка длиной 30 символов", faker.regexify("[а-я]{18} [а-я]{11}"), false},
                {"Строка без пробела", faker.regexify("[а-я]{7}"), false},
                {"Строка c пробелом в начале", faker.regexify(" [а-я]{8}"), false},
                {"Строка c пробелом в конце", faker.regexify("[а-я]{8} "), false},
                {"Строка c несколькими пробелами", faker.regexify("[а-я]{3} [а-я]{3} [а-я]{3}"), false}
        };
    }

    @DisplayName("Тест на метод валидации строки класса Account")
    @Test
    public void accountNameTest(){
        Account account = new Account(name);
        assertEquals(account.checkNameToEmboss(), isValid);
    }
}
