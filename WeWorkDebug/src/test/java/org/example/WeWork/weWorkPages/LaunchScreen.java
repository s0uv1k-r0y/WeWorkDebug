package org.example.WeWork.weWorkPages;

import org.example.base.AppiumConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import java.time.Duration;

public class LaunchScreen extends AppiumConfiguration {

    public static void isLoaderSymbolDisplayed() throws InterruptedException {
        Thread.sleep(2000);
        WebElement loadSymbol = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View"));
        FluentWait wait = new FluentWait(driver);
        wait.withTimeout(Duration.ofSeconds(15));
        wait.pollingEvery(Duration.ofSeconds(1));
        wait.ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(loadSymbol));
        Assert.assertTrue(loadSymbol.isDisplayed());
        Thread.sleep(3000);
    }

    public static void launchScreenWithoutInternet() throws InterruptedException {
        Thread.sleep(2000);
        WebElement noInternet = driver.findElement(By.xpath("//android.view.View[@content-desc=\"No Internet\"]"));
        FluentWait wait = new FluentWait(driver);
        wait.withTimeout(Duration.ofSeconds(15));
        wait.pollingEvery(Duration.ofSeconds(1));
        wait.ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(noInternet));
        Assert.assertTrue(noInternet.isDisplayed());
        Thread.sleep(3000);
    }

    public static void clickLoadCachedData() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement loadCachedDataBtn = driver.findElement(By.xpath("//android.view.View[@content-desc=\"Load Cached Data\"]"));
        loadCachedDataBtn.click();
        Thread.sleep(13000);

    }
}
