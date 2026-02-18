package page;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    public static final String MainPageUrl = "https://qa-scooter.praktikum-services.ru/";
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}