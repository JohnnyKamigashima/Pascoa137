package TestandoEveclass;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

@DisplayName("Login do Aluno Testando Eveclass")
public class LoginTestAluno {


        WebDriver navegador;

        @BeforeEach
        void setUp() throws InterruptedException { // Faz login basico com o persona do Professor Joao Avelino

            //Configura
//            String usuario = "nowaj57236@dogemn.com"; //O Eveclass pede confirmação por email, por isso para aluno estou logando por cookie
//            String password="Evening137"; //e não será usado usuario e senha
            String chromeDriverPath = "/opt/homebrew/bin/chromedriver";//Configura caminho do chromedriver
            String testandoEveclassUrl = "https://testando.eveclass.com/pt/auth/entrar"; //Configura URL do site

            //Configura o Chrome Driver
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");


            //Instancia o navegador
            navegador = new ChromeDriver(options);

            //Maximiza a janela do navegador
            navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            navegador.manage().window().maximize();


            //Navega para a página do Eveclass
            navegador.get(testandoEveclassUrl);

            //Adiciona Cookies da aluna Gabriela Silva
            Cookie cookie = new Cookie("ev_dv_62bf145fd8ff1ef247cd4eda","6434e0108672e6ddc5337cbd");
            Cookie cookie2 = new Cookie("ev_at_62bf145fd8ff1ef247cd4eda","333b97d0-d820-11ed-8ca3-9f3e6d93da1c0b29316f-8964-4735-a0ca-bda5422cb7b2");
            navegador.manage().addCookie(cookie);
            navegador.manage().addCookie(cookie2);

        }
        @AfterEach
        void tearDown() {
            navegador.quit();
        }
        @Test
        @DisplayName("Valida Login do Aluno")
        public void validaLoginAdmin() throws InterruptedException {
            //Confere se está logado como Aluno e o perfil está marcando como GS
            navegador.get("https://testando.eveclass.com/pt/conta/meus-cursos");
            assert navegador.findElement(By.cssSelector("p[data-v-3653e854='']")).getText().equals("GS");
        }

    }


