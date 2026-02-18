package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

public class OrderPage extends BasePage {
    // Первый шаг отправки заказа
    // Поле Имя
    private final By nameField = By.xpath("//input[@placeholder='* Имя']");
    // Поле Фамилия
    private final By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    // Поле Адрес
    private final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    // Поле Метро
    private final By metroStation = By.className("select-search"); // клик
    // Поле Телефон
    private final By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Поле кнопки Далее
    private final By nextButton = By.xpath("//button[contains(text(), 'Далее')]");

    // Второй шаг отправки заказа
    // Поле Срок доставки
    private final By deliveryDate = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    // Поле Срок аренды
    private final By rentalPeriod = By.xpath("//div[@class='Dropdown-placeholder']");
    // Поле Цвет самоката
    private final By greyColor = By.id("grey");
    // Поле Комментарий для курьера
    private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    // Кнопка Заказать
    private final By orderButton = By.xpath("//button[contains(@class, 'Button_Button__ra12g Button_Middle__1CSJM') and text()='Заказать']");
    // Кнопка Да (подтверждение заказа)
    private final By orderButtonAccept = By.xpath("//button[contains(@class, 'Button_Button__ra12g Button_Middle__1CSJM') and text()='Да']");
    // Модальное окно Заказ оформлен
    private final By successModal = By.xpath("//div[contains(@class, 'Order_ModalHeader__3FDaJ') and text()='Заказ оформлен']");

    public OrderPage(WebDriver driver) {
        super(driver);
    }

    public OrderPage firstStep(String name, String surname, String address, String metro, String phone) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(metroStation).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        // Выбор станции метро из выпадающего списка
        WebElement metroSelect = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//div[contains(@class,'Order_Text__2broi') and contains(text(),'%s')]", metro))));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", metroSelect);
        metroSelect.click();
        driver.findElement(phoneField).sendKeys(phone);
        driver.findElement(nextButton).click();
        return this;
    }

    public OrderPage secondStep(String date, String period, String comment) {
        driver.findElement(deliveryDate).sendKeys(date + Keys.ENTER);
        driver.findElement(rentalPeriod).click();
        // Выбор срока доставки из выпадающего списка
        driver.findElement(By.xpath(String.format("//*[contains(text(), '%s')]", period))).click();
        driver.findElement(greyColor).click();
        driver.findElement(commentField).sendKeys(comment);
        return this;
    }

    public void sendOrder() {
        driver.findElement(orderButton).click();
        driver.findElement(orderButtonAccept).click();
    }

    public boolean checkSuccessOrder() {
        return !driver.findElements(successModal).isEmpty();
    }
}