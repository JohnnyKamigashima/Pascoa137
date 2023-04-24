package webTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class testeWebWell {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // Aponta onde está o chrome driver
        driver = new ChromeDriver(options);
        js = (JavascriptExecutor) driver;
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testEveclass() {
        driver.get("https://testando.eveclass.com/pt");
        driver.manage().window().setSize(new Dimension(1366, 728));
        driver.findElement(By.linkText("Começar Agora")).click();
        {
            WebElement element = driver.findElement(By.cssSelector(".no-link-style:nth-child(1)"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        driver.findElement(By.cssSelector("#\\31 6237703116770 > .item-pill")).click();
        String expectedUrl = "https://testando.eveclass.com/pt/blog";
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); // aumenta o tempo de espera implícito
        assertEquals(expectedUrl, driver.getCurrentUrl());

        }

    }


