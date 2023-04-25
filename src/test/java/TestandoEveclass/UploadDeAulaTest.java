package TestandoEveclass;

import TestandoEveclass.pages.LoginPage;
import TestandoEveclass.pages.PainelAdminPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class UploadDeAulaTest {
    WebDriver navegador;

    //Configura
    String usuario = "febib47506@loongwin.com";
    String password="joaoavelino137";
    String chromeDriverPath = "/opt/homebrew/bin/chromedriver";//Configura caminho do chromedriver
    String testandoEveclassUrl = "https://testando.eveclass.com/pt/auth/entrar"; //Configura URL do site

    @BeforeEach
    void setUp() throws InterruptedException{

        //Configura o Chrome Driver
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        //Instancia o navegador
        navegador = new ChromeDriver(options);

        //Maximiza a janela do navegador
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        navegador.manage().window().maximize();

        String usuarioLogado = new LoginPage(navegador)
                .visitaPágina(testandoEveclassUrl)
                .preencheEmail(usuario)
                .preencheSenha(password)
                .clicarEntrar()
                .validaLoginAdmin();
        assert usuarioLogado.equals("Admin");
    }

    @AfterEach
    void tearDown(){
        navegador.quit();
    }

    @Test
    @DisplayName("Faz upload de um arquivo mp4")
    public void TesteUploadDeAulaVideo() throws InterruptedException {


        List<String> tags = new ArrayList<String>();
        tags.add("aula");
        tags.add("automatizado");
        tags.add("selenium");

        new PainelAdminPage(navegador)
                .clicarBotaoAulas()
                .clicarNovaAula()
                .clicarEnviarArquivo("/src/test/resources/Aula1Login.mp4")
                .preencheTituloDaAula("Aula sobre Login no Eveclass")
                .preencheDescricaoDaAula("Aula teste para mostrar como funciona um login automatizado no site do Eveclass.")
                .selecionaAutorDaAula("João Avelino")
                .adicionaTags(tags)
                .clicaEmConcluir()
                .clicaEmSalvar();
        assert navegador.findElement(By.xpath("//h1[normalize-space()='Aula sobre Login no Eveclass']")).isDisplayed();
    }
}