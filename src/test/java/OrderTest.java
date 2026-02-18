import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import page.MainPage;
import page.OrderPage;
import org.openqa.selenium.By;
import java.util.Arrays;

@RunWith(Parameterized.class)
public class OrderTest extends StartEndTest {
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
                {MainPage.orderButtonHeader, "Андрей", "Петров", "Санкт-Петербург, Ленина, 3", "Китай-город", "+79107778899", "15.02.2026", "сутки", "Какой-то коммент"},
                {MainPage.orderButtonBody, "Алена", "Иванова", "Москва, ул. Победы, 25", "Люблино", "+79098877788", "14.02.2026", "двое суток", "Другой коммент"}
        });
    }

    @Test
    public void makeOrder() {
        mainPage = new MainPage(driver);
        orderPage = mainPage.clickOrderButton(orderButtonSelector);
        orderPage.firstStep(name, surname, address, metro, phone)
                .secondStep(date, period, comment)
                .sendOrder();
        org.junit.Assert.assertTrue(orderPage.checkSuccessOrder());
    }
}