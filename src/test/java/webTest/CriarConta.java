package webTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class CriarConta {
    private WebDriver navegador;
    //Configura
    String usuario = "novoUsuario";
    String password="password123456";
    String email = "meuemail@hotmail.com";
    String chromeDriverPath = "/opt/homebrew/bin/chromedriver";//Configura caminho do chromedriver
    String testandoEveclassUrl = "https://testando.eveclass.com/pt/auth/registrar"; //Configura URL do site

    @Before
    public void setUp() {

        // Configura o Chrome Driver
         System.setProperty("webdriver.chrome.driver", chromeDriverPath);
         ChromeOptions options = new ChromeOptions();
         options.addArguments("--remote-allow-origins=*");

        // Instancia o navegador Chrome

         navegador = new ChromeDriver(options);

        //Maximiza a janela do navegador
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        navegador.manage().window().maximize();
    }
    @After
    public void tearDown() throws InterruptedException {
        //Fecha o navegador
        Thread.sleep(3000);
//        navegador.quit();
    }
    @Test
    public void CriarConta() throws InterruptedException {
        navegador.get(testandoEveclassUrl);
        navegador.findElement(By.cssSelector("input[id*='seu nome completo']")).sendKeys(usuario);
        navegador.findElement(By.cssSelector("input[id*='seu email']")).sendKeys(email);
        navegador.findElement(By.cssSelector("input[id*='confirme seu email']")).sendKeys(email);
        navegador.findElement(By.cssSelector("button[class*='button  button-default']")).click();
        navegador.findElement(By.cssSelector("button[class*='button  button-default']")).click();
    }
}
