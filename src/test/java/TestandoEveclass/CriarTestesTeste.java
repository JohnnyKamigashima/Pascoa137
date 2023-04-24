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

            //Faz Login
            String usuarioLogado = new LoginPage(navegador)
                    .visitaPágina(testandoEveclassUrl)
                    .preencheEmail(usuario)
                    .preencheSenha(password)
                    .clicarEntrar()
                    .validaLoginAdmin();
            assert usuarioLogado.equals("Admin");

            String iniciaisUsuario = new PainelAdminPage(navegador).obtemIniciaisUsuarioLogado();
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
            WebElement questao;

            // Adiciona questão de escolha unica
            questao = new PainelAdminPage(navegador)
                    .clicarBotaoTestes()
                    .ClicaNovoTeste()
                    .ClicarSelecionar()
                    .ClicarProsseguir()
                    .PreencherTitulo(titulo)
                    .PreencherTempoDaProva(tempo)
                    .PreencheEnunciado(enunciadoProva)
                    .AdicionarTagsProva(tags)
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

            //Adiciona questão de escolha multipla
            questao = new CriarTestesPage(navegador)
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

            //Adiciona questão de completar
            questao = new CriarTestesPage(navegador)
                    .ClicarEmAdicionar()
                    .ClicarEmSelecionarCompletar()
                    .ClicarEmProsseguir()
                    .AdicionarTituloSimplesEscolha(titQuestao3)
                    .AdicionarTagsSimplesEscolha(tags)
                    .AdicionarEnunciadoSimplesEscolha(enunciadoQuestao)
                    .AdicionarTextoParaPreenchimento( textoParaPreenchimento)
                    .AdicionarFeedbackPreenchimento(textoParaPreenchimento)
                    .ClicarEmSalvar()
                    .obterItemComTitulo(titQuestao3);
            assertTrue(questao.isDisplayed());

        //Adiciona questão dissertativa
        questao = new CriarTestesPage(navegador)
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

        //Salva o teste
        WebElement teste = new CriarTestesPage(navegador)
                .ClicarEmSalvarTeste()
                .obterItemComTitulo(titulo);
        assertTrue(teste.isDisplayed());
        teste.click();

        WebElement testeRemovido = new CriarTestesPage(navegador)
                .ClicarEmRemover()
                .ClicarEmConfirmarRemover()
                .obterItemComTitulo(titulo);
        assertTrue(testeRemovido.isDisplayed());
        }

    }


