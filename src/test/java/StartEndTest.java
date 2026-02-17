import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.After;
import page.BasePage;

public abstract class StartEndTest {
    protected WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(BasePage.MainPageUrl);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}