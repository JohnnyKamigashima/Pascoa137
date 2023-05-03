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
        Thread.sleep(1000);
        this.navegador.findElement(By.cssSelector("span[class='button-text']")).click();
        return new PainelAdminPage(this.navegador);
    }


}
