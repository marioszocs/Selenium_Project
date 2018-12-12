import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import java.util.logging.Logger;

public class Selenium {

    private static WebDriver driver;
    private static JavascriptExecutor jse;
    private static String URL = "https://www.otpbanka.sk/otp-hypo-uver";
    private final static Logger LOGGER = Logger.getLogger(Selenium.class.getName());

    //setUp method
    public static void setUp() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\mario\\Selenium\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        Thread.sleep(2000);
     }

    //scroll method
    public static void scroll() {
        jse = (JavascriptExecutor)driver;
        WebElement scrollToElement = driver.findElement(By.xpath("//div[contains(@class,'Slider ui-draggable ui-draggable-handle')]"));
        jse.executeScript("arguments[0].scrollIntoView(true);",scrollToElement);
     }


    public static void main(String[] args) throws InterruptedException {

        //setUp method
        setUp();

        //Meno:
        driver.findElement(By.name("fieldId-2704-name")).sendKeys("Jozko");
        //alebo podla xpaht
        //driver.findElement(By.xpath("//input[contains(@name,'fieldId-2704-name')]")).sendKeys("Jozko");

        //Priezvisko:
        driver.findElement(By.name("fieldId-2707-surname")).sendKeys("Mrkvicka");

        //Telefonne cislo:
        driver.findElement(By.name("fieldId-2710-phone")).sendKeys("0902111222");

        //Email:
        driver.findElement(By.name("fieldId-2713-email")).sendKeys("jozko.mrkvicka@gmail.com");
        LOGGER.info("Zakladne udaje su vyplnene");

        //Cookies -> OK
        driver.findElement(By.xpath("//a[contains(@class,'button green cookies__close')]")).click();

        //Kontaktna pobocka:
        driver.findElement(By.cssSelector(".selectize-control")).click();
        driver.findElement(By.xpath("//div[@data-value='4001'][contains(.,'Banská Bystrica')]")).click();
        LOGGER.info("Kontaktna pobocka OK!");

        //Checkbox -> "Suhlasim so spr. os. udajov"
        WebElement checkbox = driver.findElement(By.xpath("//input[contains(@class,'tx-widget tx-field tx-checkbox')]"));
        checkbox.click();
        LOGGER.info("Checkbox OK!");

        //scroll method
        scroll();

        //Sipka doprava (Draggable Slider):
        WebElement slider = driver.findElement(By.xpath("//div[contains(@class,'Slider ui-draggable ui-draggable-handle')]"));
        Actions a = new Actions (driver);
        a.clickAndHold(slider).moveByOffset(300, 0).release(slider).build().perform();
        LOGGER.info("Slider OK!");

        //Odoslat:
        driver.findElement(By.xpath("//input[contains(@name,'send')]")).submit();

        //Quit driver
        //Thread.sleep(5000);
        //driver.quit();

    }
}
