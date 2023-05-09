package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CursoPage extends Base{
    public CursoPage(WebDriver navegador) {
        super(navegador);
    }
    public By botaoComprar = By.ByXPath.xpath("(//span[contains(text(),'Comprar')])[1]");
    public ComprarPage clicarBotaoComprar() {
        navegador.findElement(botaoComprar).click();
        return new ComprarPage(navegador);
    }
}
