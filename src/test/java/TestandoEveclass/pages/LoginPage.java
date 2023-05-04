package TestandoEveclass.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    public WebDriver navegador;

    public LoginPage(WebDriver navegador) {
        this.navegador = navegador;
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
        //Clica no botão entrar
        String mensagem = "Sua requisição é inválida.";
        while (mensagem.contains("Sua requisição é inválida."))
        {
            if (navegador.findElement(By.cssSelector("span[class='button-text']")).isDisplayed())
            {
                navegador.findElement(By.cssSelector("span[class='button-text']")).click();
                mensagem = navegador.findElement(By.cssSelector("#swal2-title.swal2-title")).getText();
                if (mensagem.contains("Bem-vindo(a)"))
                {
                    Thread.sleep(1000);
                }
                Thread.sleep(300);
                System.out.println(mensagem);
            }
        }
        return new PainelAdminPage(navegador);
    }


}
