package TestandoEveclass.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CriarTestesPage {
    WebDriver navegador;
    public CriarTestesPage(WebDriver navegador) {
        this.navegador = navegador;
    }

    public CriarTestesPage ClicaNovoTeste(){
        navegador.findElement(By.xpath("//span[contains(text(),'Novo teste')]")).click();
        return this;
    }

    public CriarTestesPage ClicarSelecionar(){
        navegador.findElement(By.xpath("//div[@class='card-radio-options']//div[1]//button[1]")).click();
        return this;
    }

    public CriarTestesPage ClicarProsseguir(){
        navegador.findElement(By.xpath("//span[contains(text(),'Prosseguir')]")).click();
        return this;
    }

    public CriarTestesPage PreencherTitulo(String titulo){
        navegador.findElement(By.xpath("//input[contains(@id,'titulo do simulado-i-')]")).sendKeys(titulo);
        return this;
    }

    public CriarTestesPage PreencherTempoDaProva(String tempo){
        navegador.findElement(By.xpath("//input[contains(@id,'tempo maximo para execucao-i-')]")).sendKeys(tempo);
        return this;
    }

    public CriarTestesPage PreencheEnunciado(String enunciado){
        navegador.findElement(By.xpath("//textarea[contains(@id,'descricao-i-')]")).sendKeys(enunciado);
        return this;
    }

    public CriarTestesPage AdicionarTagsProva(List<String> tags) throws InterruptedException {
            WebElement campoTags = navegador.findElement(By.xpath("(//input[@type='search'])[1]"));
        for (String tag : tags) {
            campoTags.sendKeys(tag);
            campoTags.sendKeys(Keys.ENTER);
        }
        campoTags.sendKeys(Keys.TAB);
        return this;
    }

    public CriarTestesPage ClicarEmAdicionar(){
        navegador.findElement(By.xpath("//span[contains(text(),'Adicionar')]")).click();
        return this;
    }

    public CriarTestesPage ClicarEmSelecionarSimplesEscolha(){
        navegador.findElement(By.xpath("//div[contains(@class,'card-radio-options')]//div[1]//button[1]")).click();
        return this;
    }
    public CriarTestesPage ClicarEmSelecionarMultiplaEscolha(){
        navegador.findElement(By.xpath("//div[contains(@class,'card-radio-options')]//div[2]//button[1]")).click();
        return this;
    }
    public CriarTestesPage ClicarEmSelecionarCompletar(){
        navegador.findElement(By.xpath("//div[contains(@class,'card-radio-options')]//div[3]//button[1]")).click();
        return this;
    }
    public CriarTestesPage ClicarEmSelecionarDissertativa(){
        navegador.findElement(By.xpath("//div[contains(@class,'card-radio-options')]//div[4]//button[1]")).click();
        return this;
    }

    public CriarTestesPage ClicarEmProsseguir(){
        navegador.findElement(By.xpath("//span[contains(text(),'Prosseguir')]")).click();
        return this;
    }

    public CriarTestesPage AdicionarTituloSimplesEscolha(String titulo){
        navegador.findElement(By.xpath("//input[contains(@id,'titulo do exercicio-i-')]")).sendKeys(titulo);
        return this;
    }
    public CriarTestesPage AdicionarTagsSimplesEscolha(List<String> tags){
        WebElement campoTags = navegador.findElement(By.xpath("(//input[contains(@type,'search')])[2]"));
        for (String tag : tags) {
            campoTags.sendKeys(tag);
            campoTags.sendKeys(Keys.ENTER);
        }
        return this;
    }

    public CriarTestesPage AdicionarEnunciadoSimplesEscolha(String enunciado){
        navegador.findElement(By.xpath("(//div[contains(@class,'ProseMirror')])[1]")).sendKeys(enunciado);
        return this;
    }

    public CriarTestesPage AdicionarAlternativa1(String alternativa1){
        navegador.findElement(By.xpath("(//div[contains(@class,'ProseMirror')])[2]")).sendKeys(alternativa1);
        return this;
    }

    public CriarTestesPage AdicionarAlternativa2(String alternativa2){
        navegador.findElement(By.xpath("(//div[contains(@class,'ProseMirror')])[3]")).sendKeys(alternativa2);
        return this;
    }

    public CriarTestesPage AdicionarAlternativa3(String alternativa3){
        navegador.findElement(By.xpath("(//div[contains(@class,'ProseMirror')])[4]")).sendKeys(alternativa3);
        return this;
    }

    public CriarTestesPage SelecionarAlternativaSimplesEscolha1(){
        navegador.findElement(By.xpath("(//i[contains(@class,'far fa-check-circle')])[1]")).click();
        return this;
    }

    public CriarTestesPage SelecionarAlternativaSimplesEscolha2(){
        navegador.findElement(By.xpath("(//i[contains(@class,'far fa-check-circle')])[2]")).click();
        return this;
    }

    public CriarTestesPage SelecionarAlternativaSimplesEscolha3(){
        navegador.findElement(By.xpath("(//i[contains(@class,'far fa-check-circle')])[3]")).click();
        return this;
    }

    public CriarTestesPage SelecionarAlternativaMultiplaEscolha1(){
        navegador.findElement(By.xpath("//div[@class='options-list radio']//div//div[1]//div[2]//i[1]")).click();
        return this;
    }
    public CriarTestesPage SelecionarAlternativaMultiplaEscolha2(){
        navegador.findElement(By.xpath("//div[@class='options-list radio']//div//div[2]//div[2]//i[1]")).click();
        return this;
    }
    public CriarTestesPage SelecionarAlternativaMultiplaEscolha3(){
        navegador.findElement(By.xpath("//div[@class='options-list radio']//div//div[3]//div[2]//i[1]")).click();
        return this;
    }

    public CriarTestesPage AdicionarTextoParaPreenchimento(String texto){
        navegador.findElement(By.xpath("//textarea[contains(@id,'auto-i-')]")).sendKeys(texto);
        return this;
    }

    public CriarTestesPage AdicionarFeedbackPreenchimento(String feedback){
        navegador.findElement(By.xpath("(//div[@class='ProseMirror'])[2]")).sendKeys(feedback);
        return this;
    }

    public CriarTestesPage AdicionarFeedbackDissertativa(String feedback){
        WebElement campoFeedback = navegador.findElement(By.xpath("//body/div/div/div[contains(@class,'context-admin')]/div/div[contains(@class,'app-content')]/div[contains(@class,'container has-sidebar')]/div/div[contains(@class,'page')]/div/form[contains(@class,'form test-form')]/div[contains(@class,'form-group')]/div[contains(@class,'form-group-collapsable show animate')]/div[contains(@class,'form-group-content')]/div/div[contains(@class,'modal-mask')]/div[contains(@class,'modal-wrapper')]/div[contains(@class,'modal-container')]/div[contains(@class,'modal-scroll')]/div/form[contains(@class,'form')]/div/div[contains(@class,'form-group')]/div[contains(@class,'form-group-collapsable show animate')]/div[contains(@class,'form-group-content')]/div[contains(@class,'article-editor-wrapper')]/div[contains(@class,'article-editor')]/div[contains(@class,'form-blocks')]/span/div[contains(@class,'post-block text-editor')]/div[contains(@class,'post-block-content')]/div[contains(@class,'editor')]/div[contains(@class,'editor-content')]/div[1]"));
        campoFeedback.click();
        campoFeedback.sendKeys(feedback);
        return this;
    }
    public CriarTestesPage ClicarEmSalvar(){
        navegador.findElement(By.xpath("(//span[contains(text(),'Salvar')])[2]")).click();
        return this;
    }

    public CriarTestesPage ClicarEmSalvarTeste(){
        navegador.findElement(By.xpath("(//span[contains(text(),'Salvar')])[1]")).click();
        return this;
    }

    public CriarTestesPage ClicarEmRemover() throws InterruptedException {
        Thread.sleep(1000);
        navegador.findElement(By.xpath("(//button[contains(@clear,'true')])[1]")).click();
        return this;
    }

    public CriarTestesPage ClicarEmConfirmarRemover() throws InterruptedException {
        Thread.sleep(1000);
        navegador.findElement(By.xpath("(//button[normalize-space()='Confirmar'])[1]")).click();
        return this;
    }

    public WebElement obterItemComTitulo(String titulo){
        return navegador.findElement(By.xpath("//h1[normalize-space()='"+ titulo +"']"));
    }
}
