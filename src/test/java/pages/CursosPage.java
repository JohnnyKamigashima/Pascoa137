package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CursosPage extends Base {
    public CursosPage(WebDriver navegador) {
        super(navegador);
    }

    public CursoPage cursoEscolhido(String curso) {
        navegador.findElement(By.ByXPath.xpath("//h3[normalize-space()='" + curso + "']")).click();
        return new CursoPage(navegador);
    }
}
