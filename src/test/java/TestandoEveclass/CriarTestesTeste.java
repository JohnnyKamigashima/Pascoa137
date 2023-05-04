package TestandoEveclass;

import TestandoEveclass.pages.CriarTestesPage;
import TestandoEveclass.pages.LoginPage;
import TestandoEveclass.pages.PainelAdminPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CriarTestesTeste {
        WebDriver navegador;
        LoginPage loginPage;
        PainelAdminPage painelAdminPage;
        CriarTestesPage criarTestesPage;

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

            loginPage = new LoginPage(navegador);
            painelAdminPage = new PainelAdminPage(navegador);
            criarTestesPage = new CriarTestesPage(navegador);

            //Faz Login
            loginPage.visitaPagina(testandoEveclassUrl);
            loginPage.preencheEmail(usuario);
            loginPage.preencheSenha(password);
            loginPage.clicarEntrar();
            String usuarioLogado = painelAdminPage.validaLoginAdmin();
            assert usuarioLogado.equals("Admin");

            String iniciaisUsuario = painelAdminPage.obtemIniciaisUsuarioLogado();
            assert iniciaisUsuario.equals("JA");

        }
        @AfterEach
        void tearDown() {
            navegador.quit();
        }
        @DisplayName("Cria um teste como Professor")
        @ParameterizedTest
        @CsvFileSource(resources = "/csv/CadastroProvasProfessor.csv", numLinesToSkip = 1, delimiter = ';')
        public void adicionaTestesTest(String titulo, String tempo, String enunciadoProva, String tag1, String tag2,String tag3,
                                       String titQuestao1,String titQuestao2,String titQuestao3, String titQuestao4,String enunciadoQuestao,String alternativa1,
                                       String alternativa2, String alternativa3, String  textoParaPreenchimento) throws InterruptedException {

            List<String> tags = new ArrayList<>();
            tags.add(tag1);
            tags.add(tag2);
            tags.add(tag3);

            // Adiciona questão de escolha unica
            painelAdminPage.clicarBotaoTestes();
            criarTestesPage.ClicaNovoTeste();
            criarTestesPage.ClicarSelecionar();
            criarTestesPage.ClicarProsseguir();
            criarTestesPage.PreencherTitulo(titulo);
            criarTestesPage.PreencherTempoDaProva(tempo);
            criarTestesPage.PreencheEnunciado(enunciadoProva);
            criarTestesPage.AdicionarTagsProva(tags);
            criarTestesPage.ClicarEmAdicionar();
            criarTestesPage.ClicarEmSelecionarSimplesEscolha();
            criarTestesPage.ClicarEmProsseguir();
            criarTestesPage.AdicionarTituloSimplesEscolha(titQuestao1);
            criarTestesPage.AdicionarTagsSimplesEscolha(tags);
            criarTestesPage.AdicionarEnunciadoSimplesEscolha(enunciadoQuestao);
            criarTestesPage.AdicionarAlternativa1(alternativa1);
            criarTestesPage.AdicionarAlternativa2(alternativa2);
            criarTestesPage.AdicionarAlternativa3(alternativa3);
            criarTestesPage.SelecionarAlternativaSimplesEscolha3();
            criarTestesPage.ClicarEmSalvar();
            WebElement questao = criarTestesPage.obterItemComTitulo(titQuestao1);

            assertTrue(questao.isDisplayed());

            //Adiciona questão de escolha multipla
            criarTestesPage.ClicarEmAdicionar();
            criarTestesPage.ClicarEmSelecionarMultiplaEscolha();
            criarTestesPage.ClicarEmProsseguir();
            criarTestesPage.AdicionarTituloSimplesEscolha(titQuestao2);
            criarTestesPage.AdicionarTagsSimplesEscolha(tags);
            criarTestesPage.AdicionarEnunciadoSimplesEscolha(enunciadoQuestao);
            criarTestesPage.AdicionarAlternativa1(alternativa1);
            criarTestesPage.AdicionarAlternativa2(alternativa2);
            criarTestesPage.AdicionarAlternativa3(alternativa3);
            criarTestesPage.SelecionarAlternativaMultiplaEscolha1();
            criarTestesPage.SelecionarAlternativaMultiplaEscolha3();
            criarTestesPage.ClicarEmSalvar();
            questao = criarTestesPage.obterItemComTitulo(titQuestao2);
            assertTrue(questao.isDisplayed());

            //Adiciona questão de completar
            criarTestesPage.ClicarEmAdicionar();
            criarTestesPage.ClicarEmSelecionarCompletar();
            criarTestesPage.ClicarEmProsseguir();
            criarTestesPage.AdicionarTituloSimplesEscolha(titQuestao3);
            criarTestesPage.AdicionarTagsSimplesEscolha(tags);
            criarTestesPage.AdicionarEnunciadoSimplesEscolha(enunciadoQuestao);
            criarTestesPage.AdicionarTextoParaPreenchimento( textoParaPreenchimento);
            criarTestesPage.AdicionarFeedbackPreenchimento(textoParaPreenchimento);
            criarTestesPage.ClicarEmSalvar();
            questao=criarTestesPage.obterItemComTitulo(titQuestao3);
            assertTrue(questao.isDisplayed());

            //Adiciona questão dissertativa
            criarTestesPage.ClicarEmAdicionar();
            criarTestesPage.ClicarEmSelecionarDissertativa();
            criarTestesPage.ClicarEmProsseguir();
            criarTestesPage.AdicionarTituloSimplesEscolha(titQuestao4);
            criarTestesPage.AdicionarTagsSimplesEscolha(tags);
            criarTestesPage.AdicionarEnunciadoSimplesEscolha(enunciadoQuestao);
            criarTestesPage.AdicionarFeedbackDissertativa(textoParaPreenchimento);
            criarTestesPage.ClicarEmSalvar();
            questao = criarTestesPage.obterItemComTitulo(titQuestao4);
            assertTrue(questao.isDisplayed());

            //Salva o teste
            criarTestesPage.ClicarEmSalvarTeste();
            WebElement teste = criarTestesPage.obterItemComTitulo(titulo);
            assertTrue(teste.isDisplayed());
            teste.click();

            criarTestesPage.ClicarEmRemover();
            criarTestesPage.ClicarEmConfirmarRemover();
            WebElement testeRemovido = criarTestesPage.obterItemComTitulo(titulo);
            assertTrue(testeRemovido.isDisplayed());
        }

    }


