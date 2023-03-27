package modulos.produto;

import java.net.MalformedURLException;
import java.net.URL;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

@DisplayName("Testes Mobile do Site SauceDemo")
public class ProdutoTest {

    private WebDriver navegador;

    @BeforeEach
    public void beforeEach() throws MalformedURLException{
        DesiredCapabilities capacidades = new DesiredCapabilities();
        capacidades.setCapability("deviceName","emulator-5554");
        capacidades.setCapability("udid","emulator-5554");
        capacidades.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
        capacidades.setCapability(CapabilityType.PLATFORM_NAME, "Android");

        this.navegador = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capacidades); // Ver dados no Appium
        this.navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }



    @DisplayName("Acessar o site com sucesso")
    @Test
    public void testValidarAcessoAoSiteComSucesso() throws MalformedURLException { 
        // Navegar até a página inicial do site
        this.navegador.get("https://www.saucedemo.com/");

        // Preencher informações de login e fazer login
        this.navegador.findElement(By.id("user-name")).sendKeys("standard_user");
        this.navegador.findElement(By.id("password")).sendKeys("secret_sauce");
        this.navegador.findElement(By.id("login-button")).click();

        // Verificar se o login foi bem-sucedido
        assertTrue(this.navegador.getCurrentUrl().contains("/inventory.html"));

    }


    @DisplayName("Acesso com senha inválida")
    @Test
    public void testAcessoComSenhaInvalida() throws MalformedURLException {
        // Navegar até a página inicial do site
        this.navegador.get("https://www.saucedemo.com/");

        // Preencher informações de login com uma senha inválida e fazer login
        this.navegador.findElement(By.id("user-name")).sendKeys("standard_user");
        this.navegador.findElement(By.id("password")).sendKeys("senha_invalida");
        this.navegador.findElement(By.id("login-button")).click();

        // Verificar se o login falhou e se aparece a mensagem de erro correta
        assertTrue(this.navegador.findElement(By.cssSelector(".error-button")).isDisplayed());
    }

    @DisplayName("Acesso com usuário em branco")
    @Test
    public void testAcessoComUsuarioEmBranco() throws MalformedURLException {
        // Navegar até a página inicial do site
        this.navegador.get("https://www.saucedemo.com/");

        // Preencher informações de login com o usuário em branco e fazer login
        this.navegador.findElement(By.id("user-name")).sendKeys("");
        this.navegador.findElement(By.id("password")).sendKeys("senha_valida");
        this.navegador.findElement(By.id("login-button")).click();

        // Verificar se o login falhou e se aparece a mensagem de erro correta
        assertTrue(this.navegador.findElement(By.cssSelector(".error-button")).isDisplayed());
    }


    @DisplayName("Acesso com senha em branco")
    @Test
    public void testAcessoComSenhaEmBranco() throws MalformedURLException {
        // Navegar até a página inicial do site
        this.navegador.get("https://www.saucedemo.com/");

        // Preencher informações de login com a senha em branco e fazer login
        this.navegador.findElement(By.id("user-name")).sendKeys("standard_user");
        this.navegador.findElement(By.id("password")).sendKeys("");
        this.navegador.findElement(By.id("login-button")).click();

        // Verificar se o login falhou e se aparece a mensagem de erro correta
        assertTrue(this.navegador.findElement(By.cssSelector(".error-button")).isDisplayed());
    }


    @DisplayName("Adicionar produto ao carrinho e validar valor")
    @Test
    public void testAdicionarProdutoAoCarrinhoEValidarValor() throws MalformedURLException {
        // Acessar o site e fazer login
        this.navegador.get("https://www.saucedemo.com/");
        this.navegador.findElement(By.id("user-name")).sendKeys("standard_user");
        this.navegador.findElement(By.id("password")).sendKeys("secret_sauce");
        this.navegador.findElement(By.id("login-button")).click();

        // Adicionar produto ao carrinho
        this.navegador.findElement(By.cssSelector(".inventory_item_name")).click();
        this.navegador.findElement(By.cssSelector(".btn_primary.btn_inventory")).click();

        // Ir para o carrinho e validar o valor
        this.navegador.findElement(By.cssSelector(".shopping_cart_link")).click();
        String valor = this.navegador.findElement(By.cssSelector(".inventory_item_price")).getText();
        assertTrue(valor.equals("$29.99"));
    }

    @DisplayName("Validar texto de erro na página de checkout")
    @Test
    public void testValidarTextoDeErroNaPaginaDeCheckout() {
        // Navegar até a página inicial do site
        this.navegador.get("https://www.saucedemo.com/");

        // Preencher informações de login e fazer login
        this.navegador.findElement(By.id("user-name")).sendKeys("standard_user");
        this.navegador.findElement(By.id("password")).sendKeys("secret_sauce");
        this.navegador.findElement(By.id("login-button")).click();

        // Clicar no carrinho no topo da página
        this.navegador.findElement(By.className("shopping_cart_link")).click();

        // Clicar no botão Checkout na página do carrinho
        this.navegador.findElement(By.className("checkout_button")).click();

        // Preencher informações do checkout
        this.navegador.findElement(By.id("first-name")).sendKeys("Maria");
        this.navegador.findElement(By.id("last-name")).sendKeys("Silva");
        this.navegador.findElement(By.className("cart_button")).click();

        // Verificar se o texto de erro é exibido corretamente
        assertTrue(this.navegador.getPageSource().contains("Error: Postal Code is required"));
    }



}