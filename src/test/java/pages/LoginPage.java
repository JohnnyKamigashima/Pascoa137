package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends Base {

    public LoginPage(WebDriver navegador) {
        super(navegador);
    }

    public LoginPage visitaPagina(String url) {
        this.navegador.get(url);
        return this;
    }

    public LoginPage preencheEmail(String usuario) {
        WebElement campoNome = this.navegador.findElement(By.cssSelector("[id^='email-i-']")); //Localiza campo nome
        campoNome.click();
        campoNome.sendKeys(usuario);
        return this;

    }

    public LoginPage preencheSenha(String senha) {
        WebElement campoSenha = this.navegador.findElement(By.cssSelector("[id^='senha-i-']")); //Localiza campo senha
        campoSenha.click();
        campoSenha.sendKeys(senha);
        return this;
    }

    public PainelAdminPage clicarEntrar() throws InterruptedException {
        //        Clica no botão entrar
        String mensagem = "";

        while (!mensagem.contains("Bem-vindo(a)")) {
            Thread.sleep(1000);
            try {
                navegador.findElement(By.cssSelector("span[class='button-text']")).click();
            } catch (Exception e) {
                System.out.println("Erro encontrar botão entrar");
            }
            mensagem = navegador.findElement(By.cssSelector("#swal2-title.swal2-title")).getText();
        }
        ;
        return new PainelAdminPage(navegador);
    }


}
