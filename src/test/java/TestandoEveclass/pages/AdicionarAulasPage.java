package TestandoEveclass.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AdicionarAulasPage {
    WebDriver navegador;
    public AdicionarAulasPage(WebDriver navegador) {
        this.navegador = navegador;
    }

    public AdicionarAulasPage clicarNovaAula() {
        navegador.findElement(By.xpath("//span[contains(text(),'Nova Aula')]")).click();
        return this;
    }

    public AdicionarAulasPage clicarEnviarArquivo(String arquivo) throws InterruptedException {
        navegador.findElement(By.xpath("//button[normalize-space()='Enviar arquivo']")).click();
        String absolutePath = System.getProperty("user.dir") + arquivo;

        WebElement fileInput = navegador.findElement(By.xpath("//input[contains(@id,'video da aula-')]"));
        fileInput.sendKeys(absolutePath); // Upload the file using the absolute path
        Thread.sleep(1000);
        return this;
    }

    public AdicionarAulasPage preencheTituloDaAula(String titulo) {
        WebElement campoTitulo = navegador.findElement(By.xpath("//input[contains(@id,'titulo da aula-i-')]"));
        campoTitulo.sendKeys(Keys.chord(Keys.COMMAND, "a"));
        campoTitulo.sendKeys(Keys.DELETE);
        campoTitulo.sendKeys(titulo);
        return this;
    }

    public AdicionarAulasPage preencheDescricaoDaAula(String descricao) {
        WebElement campoDescricao = navegador.findElement(By.xpath("//textarea[contains(@id,'descricao-i-')]"));
        campoDescricao.sendKeys(descricao);
        return this;
    }

    public AdicionarAulasPage selecionaAutorDaAula(String autor) {
        WebElement campoAutor = navegador.findElement(By.xpath("//input[@class='vs__search']"));
        campoAutor.sendKeys(autor);
        campoAutor.sendKeys(Keys.ENTER);
        return this;
    }

    public AdicionarAulasPage adicionaTags(List<String> tags){
        for (String tag : tags) {
            WebElement campoTags = navegador.findElement(By.xpath("(//input[@type='search'])[2]"));
            campoTags.sendKeys(tag);
            campoTags.sendKeys(Keys.ENTER);
        }
        return this;
    }

    public AdicionarAulasPage clicaEmConcluir() {
        navegador.findElement(By.xpath("//span[contains(text(),'concluir')]")).click();
        return this;
    }

    public PainelAdminPage clicaEmSalvar() {
        navegador.findElement(By.xpath("//span[contains(text(),'Salvar')]")).click();
        return new PainelAdminPage(navegador);
    }
}
