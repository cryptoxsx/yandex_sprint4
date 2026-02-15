package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;

public class MainPage extends BasePage {
    // Кнопка для оформления заказа в header
    private final By orderButtonHeader = By.xpath("//button[@class='Button_Button__ra12g']");
    // Кнопка для оформления заказа в body
    private final By orderButtonBody = By.xpath("//button[contains(@class, 'Button_Button__ra12g Button_Middle__1CSJM') and text()='Заказать']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public OrderPage clickOrderHeader() {
        driver.findElement(orderButtonHeader).click();
        return new OrderPage(driver);
    }

    public OrderPage clickOrderBody() {
        WebElement orderButtonBodyElement = driver.findElement(orderButtonBody);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonBodyElement));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", orderButtonBodyElement);
        orderButtonBodyElement.click();
        return new OrderPage(driver);
    }

    public void findQuestion(String questionText) {
        // Локатор для кнопки Вопроса
        By questionLocator = By.xpath(String.format("//*[contains(text(),'%s')]/parent::div", questionText));
        WebElement questionElement = driver.findElement(questionLocator);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(questionElement));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", questionElement);
        questionElement.click();
    }


    public String getAnswerForQuestion(String questionText) {
        // Локатор для текста Ответа
        By answerLocator = By.xpath(String.format("//*[contains(text(),'%s')]/following::div[contains(@class,'accordion__panel')]", questionText));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        return driver.findElement(answerLocator).getText();
    }
}