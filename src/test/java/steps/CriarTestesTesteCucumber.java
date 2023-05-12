package steps;

import pages.AdicionarAulasPage;
import pages.CriarTestesPage;
import pages.LoginPage;
import pages.PainelAdminPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.es.Dado;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Entao;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CriarTestesTesteCucumber {
    WebDriver navegador;
    //Configura
    String usuario = "febib47506@loongwin.com";
    String password = "joaoavelino137";
    String chromeDriverPath = "/opt/homebrew/bin/chromedriver";//Configura caminho do chromedriver
    String testandoEveclassUrl = "https://testando.eveclass.com/pt/auth/entrar"; //Configura URL do site
    LoginPage loginPage;
    //    = new LoginPage(navegador);
    PainelAdminPage painelAdminPage;
    //    = new PainelAdminPage(navegador);
    CriarTestesPage criarTestesPage;
//    =    new CriarTestesPage(navegador);
    ;
    String tituloRemovido;

    @Before
    public void setUp() throws InterruptedException { // Faz login basico com o persona do Professor Joao Avelino

        //Configura o Chrome Driver
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless");

        //Instancia o navegador
        navegador = new ChromeDriver(options);


        //Maximiza a janela do navegador
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        navegador.manage().window().maximize();

        loginPage = new LoginPage(navegador);
        painelAdminPage = new PainelAdminPage(navegador);
        criarTestesPage = new CriarTestesPage(navegador);



        //Faz Login
        loginPage.visitaPagina(testandoEveclassUrl)
                .preencheEmail(usuario)
                .preencheSenha(password)
                .clicarEntrar();
    }

    @After
    public void tearDown() throws InterruptedException {
        WebElement testeRemovido = criarTestesPage.ClicarEmRemover()
                .ClicarEmConfirmarRemover()
                .obterItemComTitulo(tituloRemovido);
        assertTrue(testeRemovido.isDisplayed());

        navegador.quit();
    }


    @Dado("que estou logado como professor")
    public void que_estou_logado_como_professor() throws InterruptedException {

        String usuario = painelAdminPage
                .validaLoginAdmin();
        assert usuario.equals("Admin");
    }

    @Dado("crio uma prova com título {string} com {string} minutos de duração, enunciado {string} e as tags {string},  {string}, {string}")
    public void crio_uma_prova_com_título_com_minutos_de_duração_enunciado_e_as_tags(String titulo, String tempo, String enunciadoProva, String tag1, String tag2, String tag3) throws InterruptedException {

        List<String> tags = new ArrayList<>();
        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);
        tituloRemovido = titulo;

        // Adiciona questão de escolha unica
        painelAdminPage
                .clicarBotaoTestes()
                .ClicaNovoTeste()
                .ClicarSelecionar()
                .ClicarProsseguir()
                .PreencherTitulo(titulo)
                .PreencherTempoDaProva(tempo)
                .PreencheEnunciado(enunciadoProva)
                .AdicionarTagsProva(tags);
    }

    @Dado("crio um teste com questão de escolha unica com título {string}, tags {string},  {string}, {string}, enunciado {string}, alternativas {string}, {string}, {string}")
    public void crio_um_teste_com_questão_de_escolha_unica_com_título_tags_enunciado_alternativas(String titQuestao1, String tag1, String tag2, String tag3, String enunciadoQuestao, String alternativa1, String alternativa2, String alternativa3) {
        List<String> tags = new ArrayList<>();
        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);

        WebElement questao = criarTestesPage
                .ClicarEmAdicionar()
                .ClicarEmSelecionarSimplesEscolha()
                .ClicarEmProsseguir()
                .AdicionarTituloSimplesEscolha(titQuestao1)
                .AdicionarTagsSimplesEscolha(tags)
                .AdicionarEnunciadoSimplesEscolha(enunciadoQuestao)
                .AdicionarAlternativa1(alternativa1)
                .AdicionarAlternativa2(alternativa2)
                .AdicionarAlternativa3(alternativa3)
                .SelecionarAlternativaSimplesEscolha3()
                .ClicarEmSalvar()
                .obterItemComTitulo(titQuestao1);

        assertTrue(questao.isDisplayed());
    }

    @Dado("crio teste com questão de escolhas múltiplas com título {string}, tags {string},  {string}, {string}, enunciado {string}, alternativas {string}, {string}, {string}")
    public void crio_teste_com_questão_de_escolhas_múltiplas_com_título_tags_enunciado_alternativas(String titQuestao2, String tag1, String tag2, String tag3, String enunciadoQuestao, String alternativa1, String alternativa2, String alternativa3) {
        List<String> tags = new ArrayList<>();
        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);

        //Adiciona questão de escolha multipla
        WebElement questao = criarTestesPage
                .ClicarEmAdicionar()
                .ClicarEmSelecionarMultiplaEscolha()
                .ClicarEmProsseguir()
                .AdicionarTituloSimplesEscolha(titQuestao2)
                .AdicionarTagsSimplesEscolha(tags)
                .AdicionarEnunciadoSimplesEscolha(enunciadoQuestao)
                .AdicionarAlternativa1(alternativa1)
                .AdicionarAlternativa2(alternativa2)
                .AdicionarAlternativa3(alternativa3)
                .SelecionarAlternativaMultiplaEscolha1()
                .SelecionarAlternativaMultiplaEscolha3()
                .ClicarEmSalvar()
                .obterItemComTitulo(titQuestao2);
        assertTrue(questao.isDisplayed());
    }

    @Dado("crio teste com questão de completar com título {string}, tags {string},  {string}, {string}, enunciado {string}, {string}")
    public void crio_teste_com_questão_de_completar_com_título_tags_enunciado(String titQuestao3, String tag1, String tag2, String tag3, String enunciadoQuestao, String textoParaPreenchimento) {
        List<String> tags = new ArrayList<>();
        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);

        //Adiciona questão de completar
        WebElement questao = criarTestesPage
                .ClicarEmAdicionar()
                .ClicarEmSelecionarCompletar()
                .ClicarEmProsseguir()
                .AdicionarTituloSimplesEscolha(titQuestao3)
                .AdicionarTagsSimplesEscolha(tags)
                .AdicionarEnunciadoSimplesEscolha(enunciadoQuestao)
                .AdicionarTextoParaPreenchimento(textoParaPreenchimento)
                .AdicionarFeedbackPreenchimento(textoParaPreenchimento)
                .ClicarEmSalvar()
                .obterItemComTitulo(titQuestao3);
        assertTrue(questao.isDisplayed());
    }

    @Dado("crio teste com questão dissertativa com o título {string} com as tags {string},  {string}, {string}, enunciado {string}, {string}")
    public void crio_teste_com_questão_dissertativa_com_o_título_com_as_tags_enunciado(String titQuestao4, String tag1, String tag2, String tag3, String enunciadoQuestao, String textoParaPreenchimento) {
        List<String> tags = new ArrayList<>();
        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);

        //Adiciona questão dissertativa
        WebElement questao = criarTestesPage
                .ClicarEmAdicionar()
                .ClicarEmSelecionarDissertativa()
                .ClicarEmProsseguir()
                .AdicionarTituloSimplesEscolha(titQuestao4)
                .AdicionarTagsSimplesEscolha(tags)
                .AdicionarEnunciadoSimplesEscolha(enunciadoQuestao)
                .AdicionarFeedbackDissertativa(textoParaPreenchimento)
                .ClicarEmSalvar()
                .obterItemComTitulo(titQuestao4);
        assertTrue(questao.isDisplayed());
    }

    @Quando("eu clico em salvar {string}")
    public void eu_clico_em_salvar(String string) {
        //Salva o teste
        criarTestesPage.ClicarEmSalvarTeste();
    }

    @Entao("vejo que o {string} é mostrado na lista de testes")
    public void vejo_que_o_é_mostrado_na_lista_de_testes(String titulo) {
        WebElement teste = criarTestesPage
                .obterItemComTitulo(titulo);
        assertTrue(teste.isDisplayed());
        teste.click();
    }
}
