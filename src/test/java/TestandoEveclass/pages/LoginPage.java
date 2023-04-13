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

    public PainelAdminPage clicarEntrar() throws InterruptedException {
        Cookie cookie = new Cookie("ev_dv_62bf145fd8ff1ef247cd4eda", "64361997fbe0ecf5b9896fad");
        Cookie cookie2 = new Cookie("ev_at_62bf145fd8ff1ef247cd4eda", "137beec0-d8db-11ed-8211-573ca8dd2d5ac377413b-457c-4d7b-b26a-51a7c998e927");
        navegador.manage().addCookie(cookie);
        navegador.manage().addCookie(cookie2);
        navegador.get("https://testando.eveclass.com/pt/admin/conteudo");
//        navegador.findElement(By.cssSelector("span[class='button-text']")).click();
        return new PainelAdminPage(navegador);
    }


}
