package TestandoEveclass;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

@DisplayName("Login do Testando Eveclass")
public class LoginTestAdmin {
    WebDriver navegador;

    @BeforeEach
    void setUp() throws InterruptedException { // Faz login basico com o persona do Professor Joao Avelino

        //Configura
        String usuario = "febib47506@loongwin.com";
        String password="joaoavelino137";
        String chromeDriverPath = "/opt/homebrew/bin/chromedriver";//Configura caminho do chromedriver
        String testandoEveclassUrl = "https://testando.eveclass.com/pt/auth/entrar"; //Configura URL do site

        //Configura o Chrome Driver
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        //Instancia o navegador
        navegador = new ChromeDriver(options);

        //Maximiza a janela do navegador
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        navegador.manage().window().maximize();

        //Navega para a página do Eveclass
        navegador.get(testandoEveclassUrl);

        //Preenche no campo nome
        WebElement campoNome = navegador.findElement(By.cssSelector("[id^='email-i-']")); //Localiza campo nome
        campoNome.click();
        campoNome.sendKeys(usuario);

        //Preenche Senha
        WebElement campoSenha = navegador.findElement(By.cssSelector("[id^='senha-i-']")); //Localiza campo senha
        campoSenha.click();
        campoSenha.sendKeys(password);

        // Clica entrar
        navegador.findElement(By.cssSelector("span[class='button-text']")).click();

    }
@AfterEach
    void tearDown() {
        navegador.quit();
    }
@Test
@DisplayName("Valida Login do Administrador")
    public void validaLoginAdmin() throws InterruptedException {
    //Confere se está logado como Admin e o perfil está marcando como JA
    assert navegador.findElement(By.cssSelector("div[class='app-context-title'] span")).getText().equals("Admin");
    assert navegador.findElement(By.cssSelector("p[data-v-3653e854='']")).getText().equals("JA");
}

}
