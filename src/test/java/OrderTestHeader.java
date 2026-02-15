import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import page.MainPage;
import page.OrderPage;
import java.util.Arrays;

@RunWith(Parameterized.class)
public class OrderTestHeader {
    private WebDriver driver;
    private MainPage mainPage;
    private OrderPage orderPage;

    private String name, surname, address, metro, phone, date, period, comment;

    public OrderTestHeader(String name, String surname, String address, String metro, String phone,
                           String date, String period, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.period = period;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Андрей", "Петров", "Санкт-Петербург, Ленина, 3", "Китай-город", "+79107778899", "15.02.2026", "сутки", "Какой-то коммент"},
                {"Алена", "Иванова", "Москва, ул. Победы, 25", "Люблино", "+79098877788", "14.02.2026", "двое суток", "Другой коммент"}
        });
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
        orderPage = mainPage.clickOrderHeader();
    }

    @Test
    public void makeOrder() {
        orderPage.firstStep(name, surname, address, metro, phone)
                .secondStep(date, period, comment)
                .sendOrder();
        org.junit.Assert.assertTrue(orderPage.checkSuccessOrder());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}