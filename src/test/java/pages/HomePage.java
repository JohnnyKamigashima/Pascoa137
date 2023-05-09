package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends Base{
    public HomePage(WebDriver navegador) {
        super(navegador);
    }
    public String baseUrl = "https://testando.eveclass.com/pt";
    public By  botaoCursos = By.ByXPath.xpath("//div[contains(text(),'Cursos')]");

    public HomePage acessarSite() {
        navegador.get(baseUrl);
        return this;
    }

    public CursosPage clicarBotaoCursos() {
        navegador.findElement(botaoCursos).click();
        navegador.navigate().refresh();
        return new CursosPage(navegador);
    }

    public HomePage validaLinkCursos() {
        navegador.findElement(botaoCursos).isDisplayed();
        return this;
    }
}
