package pages;

import org.openqa.selenium.WebDriver;

public class Base {
    public WebDriver navegador;
    public Base(WebDriver navegador){
        this.navegador = navegador;
    }
}
