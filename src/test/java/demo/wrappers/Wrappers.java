package demo.wrappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

     public static void enterText(WebElement element, String text){
        try{
            element.clear();
            element.sendKeys(text);
        }catch(Exception e){
            e.printStackTrace();
        } 
     }

     public static void clickOnElement(ChromeDriver driver, WebElement element){
        try{
            JavascriptExecutor js=(JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }catch(Exception e){
            e.printStackTrace();
        }
     }

     public static void radioButton(ChromeDriver driver, String radioButtonText){
        try{
            WebElement element=driver.findElement(By.xpath("//span[contains(@class,'OvPDhc') and contains(text(), '"+radioButtonText+"')]/../../..//div[@class='vd3tt']"));
            element.click();
        }catch(Exception e){
            e.printStackTrace();
        }
     }

     public static void checkBox(ChromeDriver driver, String checkBoxText){
        try{
            WebElement element=driver.findElement(By.xpath("//span[contains(@class,'aDTYNe') and contains(text(),'"+checkBoxText+"')]/../../preceding-sibling::div[@role='checkbox']"));
            element.click();
        }catch(Exception e){
            e.printStackTrace();
        }
     }

     public static void dropDown(ChromeDriver driver, String dropdownText){
        try{
            WebElement element=driver.findElement(By.xpath("//div[contains(@class,'ncFHed')]//span[contains(text(),'"+dropdownText+"')]"));
            element.click();
        }catch(Exception e){
            e.printStackTrace();
        }
     }

     public static String getDate7DaysAgo(){
        LocalDate currentDate=LocalDate.now();
        //Subtract 7 days
        LocalDate Date7DaysAgo=currentDate.minusDays(7);
        //Defining date formate
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //Formating current date
        String formatedDate=Date7DaysAgo.format(formatter);

        return formatedDate;
     }

     public static String getEpochTimeAsString(){
        //Create epoch time string
        long epochTime = System.currentTimeMillis()/1000; 
        String epochTimeString=String.valueOf(epochTime);
        return epochTimeString;
     }

}
