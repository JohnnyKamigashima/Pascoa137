package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ComprarPage extends Base {
    public ComprarPage(WebDriver navegador) {
        super(navegador);
    }

    public WebElement tituloCurso(String curso) {
        return navegador.findElement(By.ByXPath.xpath("//p[normalize-space()='" + curso + "']"));
    }

}
