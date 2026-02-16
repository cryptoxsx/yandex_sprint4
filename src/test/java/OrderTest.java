import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.BasePage;
import page.MainPage;
import page.OrderPage;
import org.openqa.selenium.By;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private MainPage mainPage;
    private OrderPage orderPage;

    private By orderButtonSelector;
    private String name, surname, address, metro, phone, date, period, comment;

    public OrderTest(By orderButtonSelector, String name, String surname, String address, String metro, String phone,
                     String date, String period, String comment) {
        this.orderButtonSelector = orderButtonSelector;
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
                {By.xpath("//button[@class='Button_Button__ra12g']"), "Андрей", "Петров", "Санкт-Петербург, Ленина, 3", "Китай-город", "+79107778899", "15.02.2026", "сутки", "Какой-то коммент"},
                {By.xpath("//button[contains(@class, 'Button_Button__ra12g Button_Middle__1CSJM') and text()='Заказать']"), "Алена", "Иванова", "Москва, ул. Победы, 25", "Люблино", "+79098877788", "14.02.2026", "двое суток", "Другой коммент"}
        });
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(BasePage.MainPageUrl);
        mainPage = new MainPage(driver);
        orderPage = mainPage.clickOrderButton(orderButtonSelector);
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