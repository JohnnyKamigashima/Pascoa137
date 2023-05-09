package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.ComprarPage;
import pages.CursoPage;
import pages.CursosPage;
import pages.HomePage;

import java.time.Duration;

public class CompraAula {
    private WebDriver navegador;
    private HomePage homePage;
    private CursoPage cursoPage;
    private CursosPage cursosPage;
    private ComprarPage comprarPage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
//        options.addArguments("--headless");
        navegador = new ChromeDriver(options);
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        navegador.manage().window().maximize();

        homePage = new HomePage(navegador);
        cursoPage = new CursoPage(navegador);
        cursosPage = new CursosPage(navegador);
        comprarPage = new ComprarPage(navegador);

        homePage.acessarSite();
    }
    @After
    public void tearDown() {
        navegador.quit();
    }
    @Dado("que eu esteja no site do Testando Eveclass")
    public void que_eu_esteja_no_site_do_testando_eveclass() {
        homePage
                .acessarSite();
    }
    @Quando("eu localizar a aula {string} e realizar a compra")
    public void eu_localizar_a_aula_e_realizar_a_compra(String aula) {
        homePage
                .validaLinkCursos()
                .clicarBotaoCursos()
                .cursoEscolhido(aula)
                .clicarBotaoComprar();
    }
    @Então("eu devo ver a tela de compra mostrando {string}")
    public void eu_devo_ver_a_tela_de_compra_mostrando(String aula) {
        WebElement tituloCurso = comprarPage.tituloCurso(aula);
        tituloCurso.isDisplayed();
        tituloCurso.getText().contains(aula);
    }
}
