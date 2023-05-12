package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PainelAdminPage extends Base {

    public PainelAdminPage(WebDriver navegador) {
        super(navegador);
    }

    public AdicionarAulasPage clicarBotaoAulas() {
        navegador.findElement(By.cssSelector(".fal.fa-chalkboard-teacher")).click();
        return new AdicionarAulasPage(navegador);
    }

    public CriarTestesPage clicarBotaoTestes(){
        navegador.findElement(By.cssSelector(".fal.fa-file-certificate")).click();
        return new CriarTestesPage(navegador);
    }

    public String validaLoginAdmin() throws InterruptedException {
        //Confere se está logado como Admin e o perfil está marcando como JA
        return navegador.findElement(By.cssSelector("div[class='app-context-title'] span")).getText();
    }

    public String validaLoginProfessor() throws InterruptedException {
        //Confere se está logado como Professor e o perfil está marcando como JA
        return navegador.findElement(By.cssSelector("p[data-v-3653e854='']")).getText();
    }

    public String obtemIniciaisUsuarioLogado() throws InterruptedException {
        //Confere se está logado como Admin e o perfil está marcando como JA
        return navegador.findElement(By.cssSelector("p[data-v-3653e854='']")).getText();
    }
}
