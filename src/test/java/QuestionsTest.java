import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import page.MainPage;
import java.util.List;
import java.util.Arrays;

@RunWith(Parameterized.class)
public class QuestionsTest {
    private WebDriver driver;
    private MainPage mainPage;
    private String questionText;
    private String expectedAnswer;

    public QuestionsTest(String questionText, String expectedAnswer) {
        this.questionText = questionText;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters(name = "Вопрос-ответ")
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        });
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
    }

    @Test
    public void testCheckAnswer() {
            System.out.println("Тест вопроса: " + questionText);
            System.out.println("Ожидаемый ответ: " + expectedAnswer);
            mainPage.findQuestion(questionText);
            String actualAnswer = mainPage.getAnswerForQuestion(questionText);
            System.out.println("Фактический ответ: " + actualAnswer);

            org.junit.Assert.assertTrue(
                    "Ожидали: '" + expectedAnswer + "'\nПолучили: '" + actualAnswer + "'",
                    actualAnswer.contains(expectedAnswer)
            );
            System.out.println("✓ ОК - ответ верный");
        }

    @After
    public void tearDown() {
        driver.quit();
    }
}