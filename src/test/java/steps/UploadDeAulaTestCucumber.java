package steps;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.es.Dado;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Entao;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public class UploadDeAulaTestCucumber {
    private WebDriver navegador;
    String usuario = "febib47506@loongwin.com";
    String password="joaoavelino137";
    String chromeDriverPath = "/opt/homebrew/bin/chromedriver";//Configura caminho do chromedriver
    String testandoEveclassUrl = "https://testando.eveclass.com/pt/auth/entrar"; //Configura URL do site
    @Before
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        //Instancia o navegador
        navegador = new ChromeDriver(options);
        //Maximiza a janela do navegador
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        navegador.manage().window().maximize();
        //Navega para a pagina do site
        navegador.get(testandoEveclassUrl);
        navegador.findElement(By.cssSelector("[id^='email-i-']")).sendKeys(usuario); //Localiza campo nome
//        campoNome.click();
        navegador.findElement(By.cssSelector("[id^='senha-i-']")).sendKeys(password); //Localiza campo senha
//        campoSenha.click();
        //Clica no botão entrar
        String mensagem = "Sua requisição é inválida.";
        while (mensagem.contains("Sua requisição é inválida."))
        {
            if (navegador.findElement(By.cssSelector("span[class='button-text']")).isDisplayed())
            {
                navegador.findElement(By.cssSelector("span[class='button-text']")).click();
                Thread.sleep(100);
                mensagem = navegador.findElement(By.cssSelector("#swal2-title.swal2-title")).getText();
                if (mensagem.contains("Bem-vindo(a)"))
                {
                    Thread.sleep(1000);
                }
                System.out.println(mensagem);
            }
        }
    }

    @After
    public void tearDown() throws InterruptedException {
        navegador.quit();
    }
    @Dado("que eu esteja na pagina de administrador")
    public void que_eu_esteja_na_pagina_de_administrador() throws InterruptedException {
    }
    @Quando("eu adicionar uma nova Aula")
    public void eu_adicionar_uma_nova_aula() throws InterruptedException {
        navegador.findElement(By.cssSelector(".fal.fa-chalkboard-teacher")).click();
        navegador.findElement(By.xpath("//span[contains(text(),'Nova Aula')]")).click();
    }
    @Quando("clicar em enviar arquivo {string}")
    public void clicar_em_enviar_arquivo(String arquivo) throws InterruptedException {
        navegador.findElement(By.xpath("//button[normalize-space()='Enviar arquivo']")).click();
        String absolutePath = System.getProperty("user.dir") + arquivo;

        WebElement fileInput = navegador.findElement(By.xpath("//input[contains(@id,'video da aula-')]"));
        fileInput.sendKeys(absolutePath); // Upload the file using the absolute path
        Thread.sleep(500);
    }
    @Quando("preencher como titulo {string}")
    public void preencher_como_titulo(String titulo) {
        WebElement campoTitulo = navegador.findElement(By.xpath("//input[contains(@id,'titulo da aula-i-')]"));
        campoTitulo.sendKeys(Keys.chord(Keys.COMMAND, "a"));
        campoTitulo.sendKeys(Keys.DELETE);
        campoTitulo.sendKeys(titulo);
    }
    @Quando("preencher como descricao {string}")
    public void preencher_como_descricao(String descricao) {
        WebElement campoDescricao = navegador.findElement(By.xpath("//textarea[contains(@id,'descricao-i-')]"));
        campoDescricao.sendKeys(descricao);
    }
    @Quando("selecionar o autor {string}")
    public void selecionar_o_autor(String autor) {
        WebElement campoAutor = navegador.findElement(By.xpath("//input[@class='vs__search']"));
        campoAutor.sendKeys(autor);
        campoAutor.sendKeys(Keys.ENTER);
    }
    @Quando("adicionar as tags")
    public void adicionar_as_tags(List<String> tags) {
        System.out.println("Adicionando as tags");
        System.out.println(tags);
        for (String tag : tags) {
            System.out.println(tag);
            WebElement campoTags = navegador.findElement(By.xpath("(//input[@type='search'])[2]"));
            campoTags.sendKeys(tag);
            campoTags.sendKeys(Keys.ENTER);
        }
    }
    @Quando("concluir e salvar")
    public void concluir_e_salvar() {
        navegador.findElement(By.xpath("//span[contains(text(),'concluir')]")).click();
        navegador.findElement(By.xpath("//span[contains(text(),'Salvar')]")).click();
    }
    @Entao("a mensagem {string} deve ser exibida")
    public void a_mensagem_deve_ser_exibida(String mensagem) {
        assert navegador.findElement(By.xpath("//h1[normalize-space()='" + mensagem + "']")).isDisplayed();
    }

}
