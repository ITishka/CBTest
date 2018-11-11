package ru.kos.ve;

import com.google.common.base.Function;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Ктомуже;
import cucumber.api.java.ru.Тогда;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;


public class CbMethodTest {

    private WebDriver webDriver;
    private File screenshot;

    public CbMethodTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        webDriver = new ChromeDriver();
//        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.23.0-win64\\geckodriver.exe");
//        webDriver = new FirefoxDriver();
    }

    @Когда("^пользователь на странице (.*)$")
    public void пользовательНаСтранице(String arg1) {
        webDriver.get(arg1);
    }

    @Ктомуже("^отображается поле \"([^\"]*)\"$")
    public void отображаетсяПоле(String arg1) throws Throwable {
        try {
            webDriver.findElement(By.id(arg1)).isDisplayed();
        } catch (Exception e) {
            throw new Exception("Поле ввода не появилось ");
        }
    }

    @Тогда("^вводим в поле \"([^\"]*)\" \"([^\"]*)\"$")
    public void вводимВПоле(String arg1, String text) {
         webDriver.findElement(By.id(arg1)).sendKeys(text);
    }

    @Когда("^пользователь нажмет клавишу (.*)$")
    public void пользовательНажметКлавишу(String keyName) {
        webDriver.findElement(By.id("lst-ib")).sendKeys(Keys.valueOf(keyName));
    }

    @Когда("^пользователь нажмет на ссылку \"([^\"]*)\"$")
    public void пользовательНажметНаСсылку(String link) {
        WebElement element = webDriver.findElement(By.partialLinkText(link));
        String href = element.getAttribute("href");
        webDriver.get(href);
    }

    @Тогда("^пользователь на полностью загруженном сайте (.*)$")
    public void пользовательНаПолностьюЗагруженномСайте(String arg1) throws Throwable {
        if (!webDriver.getCurrentUrl().equals(arg1)) {
            throw new Exception("Url сайта не соответствует переданному аргументу");
        }
        waitForLoad(webDriver);
    }

    @Когда("^чек бокс \"([^\"]*)\"$")
    public void чекБокс(String arg1) {
        String elementFor = webDriver.findElement(By.xpath("//*[contains(text(), '" + arg1 + "')]"))
                .getAttribute("for");
        webDriver.findElement(By.id(elementFor)).click();
    }

    @Когда("^делаем скриншот$")
    public void делаемСкриншот() {
        screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
    }

    @Тогда("^отправляем скриншот на e-mail \"([^\"]*)\"$")
    public void отправляемСкриншотНаEMail(String mail) {
        EmailUtil.sendMail(screenshot, mail);
    }

    private void waitForLoad(WebDriver webDriver) {
        new WebDriverWait(webDriver, 10).until(new Function<WebDriver, Object>() {
            @Override
            public Object apply(WebDriver input) {
                return ((JavascriptExecutor) input).executeScript("return document.readyState")
                        .equals("complete");
            }
        });
    }
}






