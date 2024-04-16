package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage {

    By makeAppointmentButton = By.id("btn-make-appointment");
    By usernameTxt = By.xpath("//input[@aria-describedby='demo_username_label']");
    By passwordTxt = By.xpath("//input[@aria-describedby='demo_password_label']");
    By labelUsername = By.xpath("//label[@for='txt-username']");

    By userField = By.id("txt-username");
    By passwordField = By.id("txt-password");

    By loginButton = By.id("btn-login");
    By bookAppointment =  By.id("btn-book-appointment");

    By loginFailed = By.xpath("//p[contains(text(),'Login failed')]");

    public void goToHome(WebDriver driver) throws InterruptedException {

        driver.findElement(makeAppointmentButton).click();
        Thread.sleep(3000);
    }

    public void doLogin(WebDriver driver) throws InterruptedException {

        String user = driver.findElement(usernameTxt).getAttribute("value");
        String password = driver.findElement(passwordTxt).getAttribute("value");

        Assert.assertEquals(driver.findElement(labelUsername).getText(),"Username");

        driver.findElement(userField).clear();

        //driver.findElement(By.id("txt-username")).sendKeys("John Doe");
        driver.findElement(userField).sendKeys(user);

        driver.findElement(passwordField).clear();
        // driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
        driver.findElement(passwordField).sendKeys(password);

        driver.findElement(loginButton).click();

        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(bookAppointment).isDisplayed());


    }

    public void setUsername(WebDriver driver) throws InterruptedException {

        // Aqui se mete la espera
        String user = driver.findElement(usernameTxt).getAttribute("value");
        Assert.assertEquals(driver.findElement(labelUsername).getText(),"Username");

        driver.findElement(userField).clear();
        driver.findElement(userField).sendKeys(user);

        Thread.sleep(3000);

    }

    public void setPassword(WebDriver driver) throws InterruptedException {
        String password = driver.findElement(passwordTxt).getAttribute("value");
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);

        Thread.sleep(3000);
    }

    public void setPasswordKO(WebDriver driver) throws InterruptedException {
        String password = "1234";
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);

        Thread.sleep(3000);
    }

    public  void clickLogin(WebDriver driver) throws InterruptedException {
        driver.findElement(loginButton).click();
        Thread.sleep(3000);

    }

    public void doLogin2(WebDriver driver) throws InterruptedException {

        setUsername(driver);
        setPassword(driver);
        clickLogin(driver);
        validationOK(driver);

    }

    public void validationOK(WebDriver driver){
        Assert.assertTrue(driver.findElement(bookAppointment).isDisplayed());

    }

    public void validationKO(WebDriver driver){
        String txt = driver.findElement(loginFailed).getText();
        Assert.assertTrue(txt.contains("Please ensure the username and password are valid"));
    }

}
