package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MakeAppointment {

    // Definir todos los elementos tipo By
    By facilitySelect = By.id("combo_facility");
    String facilityValue = "Tokyo CURA Healthcare Center";
    String facilityVisibleTxt = "Seoul CURA Healthcare Center";
    By defectValue = By.xpath("//select[@id='combo_facility']");
    By checkedHospital = By.xpath("//*[@id='chk_hospotal_readmission']");
    By visitDateTxt = By.id("txt_visit_date");
    By commentField = By.id("txt_comment");
    By bookingButton = By.id("btn-book-appointment");
    String expectedConfirmationTxT = "Appointment Confirmation";
    By contirmationLabel = By.xpath("//*[@id='summary']//h2");

    //*******//

    public void setFacility(WebDriver driver){

        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(facilitySelect));
        Select select = new Select(driver.findElement(facilitySelect));
        select.selectByIndex(1);
        select.getOptions().size();
        select.selectByValue(facilityValue);
        select.selectByVisibleText(facilityVisibleTxt);
        driver.findElement(defectValue).sendKeys(facilityValue);

    }

    public void checkHospital(WebDriver driver){
        if (!driver.findElement(checkedHospital).isSelected())
        {
            driver.findElement(checkedHospital).click();
        }

    }

    public void setDate(WebDriver driver){

        Date fechaActual = new Date();
        // Define el formato de fecha deseado
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        // Formatea la fecha de hoy según el formato especificado
        String fechaFormateada = formato.format(fechaActual);
        //meter wait untl field is visible
        driver.findElement(visitDateTxt).sendKeys(fechaFormateada);


    }

    public void setComment(WebDriver driver){
        driver.findElement(commentField).click();
        driver.findElement(commentField).sendKeys("Automation test");
    }

    public void bookMakeAppointment(WebDriver driver){

        driver.findElement(bookingButton).click();
        String actualConfirmationTxT = driver.findElement(contirmationLabel).getText();
        Assert.assertEquals(actualConfirmationTxT,expectedConfirmationTxT);

    }






}
