package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.es.Dado;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Então;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Disabled;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComprarPassagemPt {
    private WebDriver driver;
    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless");


        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS);
    }

    @After
    public void teardown() {
        driver.quit();
    }
    @Dado("que acesso o site Blazedemo")
    public void que_acesso_o_site_blazedemo() {
        driver.get("https://blazedemo.com/");
        String tituloPagina = driver.getTitle();
        assertEquals("BlazeDemo", tituloPagina);
    }
    @Quando("seleciono a origem como {string} e destino {string}")
    public void seleciono_a_origem_como_e_destino(String origem, String destino) {
        final String byOrigem = "option[value=\"" + origem + "\"]";
        final String byDestino = "option[value=\"" + destino + "\"]";

        driver.findElement(By.cssSelector(byOrigem)).click();
        driver.findElement(By.cssSelector(byDestino)).click();
    }
    @Quando("clico em Procurar Voo")
    public void clico_em_procurar_voo() {
        driver.findElement(By.cssSelector("input[value]")).click();
    }
    @Então("exibe a frase indicando voo entre {string} e {string}")
    public void exibe_a_frase_indicando_voo_entre_e(String origem, String destino) {
        assertEquals("BlazeDemo - reserve", driver.getTitle());
        assertEquals(MessageFormat.format("Flights from {0} to {1}:", origem, destino), driver.findElement(By.cssSelector("div.container h3")).getText());
    }

}
