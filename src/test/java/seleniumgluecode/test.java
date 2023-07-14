package seleniumgluecode;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class test {

    private ChromeDriver driver;
    private String uniqueUsername;


    @Given("^El Usuario se encuentra en la Pagina Principal$")
    public void el_Usuario_se_encuentra_en_la_Pagina_Principal() throws Throwable {
      System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
      driver = new ChromeDriver();
      driver.get("https://www.demoblaze.com/");
      driver.manage().window().maximize();

    }

    @Given("^Hace click en el botón Sign Up$")
    public void hace_click_en_el_boton() throws Throwable {

        // Hacer clic en el enlace de registro
        WebElement signUpLink = driver.findElement(By.id("signin2"));
        signUpLink.click();


    }

    @And("^Rellena el formulario de registro con usuario y contraseña$")
    public void RellenA_el_formulario_de_registro_con_el_usuario_y_la_contraseña() throws Throwable {

        //Generar un nombre de usuario unico

        uniqueUsername = generateUniqueUsername();

        // Esperar a que aparezca el cuadro de registro
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement signUpModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signInModal")));

        // Rellenar el formulario de registro con los valores recibidos
        WebElement usernameInput = driver.findElement(By.id("sign-username"));
        usernameInput.sendKeys(uniqueUsername);

        WebElement passwordInput = driver.findElement(By.id("sign-password"));
        passwordInput.sendKeys("123");
    }

    @When("^Hace click en el boton de registro$")
    public void hace_click_en_el_boton_de_registro() throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement signUpModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signInModal")));

        WebElement signUpButton = signUpModal.findElement(By.xpath("//button[text()='Sign up']"));
        signUpButton.click();

        // Esperar a que aparezca la alerta
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept(); // Aceptar la alerta

    }
    @And("^Inicia sesión con usuario y contraseña")
    public void Inicia_sesión_con_usuario_y_contraseña() throws Throwable{

        WebElement signInLink = driver.findElement(By.id("login2"));
        signInLink.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement signUpModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logInModal")));

        // Rellenar el formulario de registro con los valores recibidos
        WebElement usernameInput = driver.findElement(By.id("loginusername"));
        usernameInput.sendKeys(uniqueUsername);

        WebElement passwordInput = driver.findElement(By.id("loginpassword"));
        passwordInput.sendKeys("123");

        // Hacer click en Log In
        WebElement logInButton = signUpModal.findElement(By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]"));
        logInButton.click();

        // Cambiar de vuelta al frame principal
        driver.switchTo().defaultContent();



    }

    @When("^Agrega una laptop al carrito$")
    public void agrega_una_laptop_al_carrito() throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver, 10);

        Thread.sleep(3000);
        // Seleccionar laptop
        WebElement laptopLink = driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[8]/div/div/h4/a"));
        laptopLink.click();
        Thread.sleep(2000);

        // Agregar al carrito
        WebElement addCarrito = driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/a"));
        addCarrito.click();

        // Esperar a que aparezca la alerta
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept(); // Aceptar la alerta
    }



    @Then("^Verifica que se haya agregado al carrito$")
    public void verifica_que_se_agregó_al_carrito() throws Throwable {

        //Click en carrito
        WebElement cartBtn = driver.findElement(By.id("cartur"));
        cartBtn.click();
        Thread.sleep(2000);

        // Verificar que el precio sea de 790
        WebElement priceElement = driver.findElement(By.id("totalp"));
        String price = priceElement.getText();
        String expectedPrice = "790";

        if (price.equals(expectedPrice)) {
            System.out.println("El precio coincide: " + price);
        } else {
            System.out.println("¡Error! El precio no coincide. Precio actual: " + price + ", Precio esperado: " + expectedPrice);
            Assert.fail("El precio no coincide");
        }

        // Cerrar el navegador
        driver.quit();

    }
    // Resto de los métodos...

    private String generateUniqueUsername() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = now.format(formatter);
        return timestamp + "user";
    }


}
