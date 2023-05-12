package steps;

import pages.AdicionarAulasPage;
import pages.LoginPage;
import pages.PainelAdminPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.es.Dado;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Entao;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public class UploadDeAulaTestCucumber {
    private WebDriver navegador;
    String usuario = "febib47506@loongwin.com";
    String password = "joaoavelino137";
    String chromeDriverPath = "/opt/homebrew/bin/chromedriver";//Configura caminho do chromedriver
    String testandoEveclassUrl = "https://testando.eveclass.com/pt/auth/entrar"; //Configura URL do site
    LoginPage loginPage;
    PainelAdminPage painelAdminPage;
    AdicionarAulasPage adicionarAulasPage;

    @Before
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
//        options.addArguments("--headless");

        //Instancia o navegador
        navegador = new ChromeDriver(options);

        //Maximiza a janela do navegador
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        navegador.manage().window().maximize();

        loginPage = new LoginPage(navegador);
        painelAdminPage = new PainelAdminPage(navegador);
        adicionarAulasPage = new AdicionarAulasPage(navegador);

        //Navega para a pagina do site
        loginPage.visitaPagina(testandoEveclassUrl)
                .preencheEmail(usuario)
                .preencheSenha(password)
                .clicarEntrar();
    }

    @After
    public void tearDown() throws InterruptedException {
        navegador.quit();
    }

    @Dado("que eu esteja na pagina de administrador")
    public void que_eu_esteja_na_pagina_de_administrador() throws InterruptedException {
        String usuarioLogado = painelAdminPage.validaLoginAdmin();
        assert usuarioLogado.equals("Admin");
    }

    @Quando("eu adicionar uma nova Aula")
    public void eu_adicionar_uma_nova_aula() {
        painelAdminPage.clicarBotaoAulas()
                .clicarNovaAula();
    }

    @Quando("clicar em enviar arquivo {string}")
    public void clicar_em_enviar_arquivo(String arquivo) throws InterruptedException {
        adicionarAulasPage.clicarEnviarArquivo(arquivo);
    }

    @Quando("preencher como titulo {string}")
    public void preencher_como_titulo(String titulo) {
        adicionarAulasPage.preencheTituloDaAula(titulo);
    }

    @Quando("preencher como descricao {string}")
    public void preencher_como_descricao(String descricao) {
        adicionarAulasPage.preencheDescricaoDaAula(descricao);
    }

    @Quando("selecionar o autor {string}")
    public void selecionar_o_autor(String autor) {
        adicionarAulasPage.selecionaAutorDaAula(autor);
    }

    @Quando("adicionar as tags")
    public void adicionar_as_tags(List<String> tags) {
        adicionarAulasPage.adicionaTags(tags);
    }

    @Quando("concluir e salvar")
    public void concluir_e_salvar() {
        adicionarAulasPage.clicaEmConcluir()
                .clicaEmSalvar();
    }

    @Entao("a mensagem {string} deve ser exibida")
    public void a_mensagem_deve_ser_exibida(String nomeDaAula) {
        assert navegador.findElement(By.xpath("//h1[normalize-space()='" + nomeDaAula + "']")).isDisplayed();
    }

}
