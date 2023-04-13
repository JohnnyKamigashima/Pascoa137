package TestandoEveclass.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    public WebDriver navegador;

    public LoginPage(WebDriver navegador) {
        this.navegador = navegador;
    }

  public LoginPage visitaPágina(String url) {
      navegador.get(url);
      return this;
  }

  public LoginPage preencheEmail(String usuario) {
      WebElement campoNome = navegador.findElement(By.cssSelector("[id^='email-i-']")); //Localiza campo nome
      campoNome.click();
      campoNome.sendKeys(usuario);
      return this;
  }

  public LoginPage preencheSenha(String senha) {
          WebElement campoSenha = navegador.findElement(By.cssSelector("[id^='senha-i-']")); //Localiza campo senha
          campoSenha.click();
          campoSenha.sendKeys(senha);
          return this;
    }

    public PainelAdminPage clicarEntrar() {
        navegador.findElement(By.cssSelector("span[class='button-text']")).click();
        return new PainelAdminPage(navegador);
    }


}
