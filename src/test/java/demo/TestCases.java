package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */
    @Test
    public void testCase01() throws InterruptedException{
        //Navigating to google form
        driver.get("https://forms.gle/wjPkzeSEk1CM7KgGA");
        Thread.sleep(3000);

        //Entering text in 'Name' text field
        WebElement name=driver.findElement(By.xpath("(//input[@class='whsOnd zHQkBf'])[1]"));
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(name));
        System.out.println("Wait 1");
        Wrappers.enterText(name, "Crio Learner");

        //Entering text in 'Why are you practicing Automation?' text field
        WebElement practicingAutomationReason=driver.findElement(By.xpath("//textarea[@aria-label='Your answer']"));
        String practiceAutomationString="I want to be the best QA Engineer!";
        String epochTime=Wrappers.getEpochTimeAsString();
        System.out.println("Wait 2");
        Thread.sleep(3000);
        Wrappers.enterText(practicingAutomationReason, practiceAutomationString+""+epochTime);

        //Selecting 'Automation Testing experience' radio button
        Thread.sleep(5000);
        System.out.println("Wait 3");
        Wrappers.radioButton(driver, "0 - 2");

        //Selecting 'Which of the following have you learned in Crio.Do for Automation Testing?' check box
        Thread.sleep(5000);
        System.out.println("Wait 4");
        Wrappers.checkBox(driver, "Java");
        Wrappers.checkBox(driver, "Selenium");
        Wrappers.checkBox(driver, "TestNG");

        //Selecting 'How should you be addressed?' drop down field
        WebElement element=driver.findElement(By.xpath("//div[contains(@class, 'DEh1R')]"));
        System.out.println("Wait 5");
        Thread.sleep(5000);
        Wrappers.clickOnElement(driver, element);
        Thread.sleep(2000);
        Wrappers.dropDown(driver, "Mrs");

        //Entering 'What was the date 7 days ago?' date
        WebElement dateField=driver.findElement(By.xpath("//input[@type='date']"));
        Thread.sleep(3000);
        String date=Wrappers.getDate7DaysAgo();
        Thread.sleep(5000);
        System.out.println("Wait 6");
        Wrappers.enterText(dateField, date);

        //Providing 'What is the time right now?' answer as 7:30
        WebElement hourElement=driver.findElement(By.xpath("//input[@aria-label='Hour']"));
        WebElement minuteElement=driver.findElement(By.xpath("//input[@aria-label='Minute']"));
        Wrappers.enterText(hourElement, "07");
        Wrappers.enterText(minuteElement, "30");

        //Clicking submit button
        WebElement submitBtn=driver.findElement(By.xpath("//span[text()='Submit']"));
        Wrappers.clickOnElement(driver, submitBtn);

        Thread.sleep(5000);
        System.out.println("Wait 7");
        WebElement successMessage=driver.findElement(By.xpath("//div[@class='vHW8K']"));
        String expectedMessage="Thanks for your response, Automation Wizard!";
        Assert.assertEquals(successMessage.getText().trim(), expectedMessage);
    }
     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}