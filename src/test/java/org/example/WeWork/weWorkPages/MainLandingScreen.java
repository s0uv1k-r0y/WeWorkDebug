package org.example.WeWork.weWorkPages;

import io.appium.java_client.AppiumBy;
import org.example.base.AppiumConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.HashMap;

public class MainLandingScreen extends AppiumConfiguration {

    public static void clickSearchButton() {
        WebElement searchField = driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.ImageView"));
        FluentWait wait = new FluentWait(driver);
        wait.withTimeout(Duration.ofSeconds(15));
        wait.pollingEvery(Duration.ofSeconds(1));
        wait.ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(searchField));
        searchField.click();
    }

    public static void enterSearchText(String searchValue) throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.ImageView")).sendKeys(searchValue);
        Thread.sleep(2000);
    }

    public static void clearSearchField(String searchValue) throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//android.widget.ImageView[@text='" + searchValue + "']/android.view.View")).click();
        Thread.sleep(2000);
    }

    public static String verifyLocationInMainScreen() throws InterruptedException {
        Thread.sleep(2000);
        WebElement loc = driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='Google Building 43, Google Building 43\nUnknown, Mountain View, California']"));
        FluentWait wait = new FluentWait(driver);
        wait.withTimeout(Duration.ofSeconds(15));
        wait.pollingEvery(Duration.ofSeconds(1));
        wait.ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(loc));
        String locTxt = loc.getAttribute("content-desc");
        System.out.println(locTxt);
        return locTxt;
    }

    public static boolean denyLocationInMainScreen() throws InterruptedException {
        Thread.sleep(2000);
        WebElement denyLoc = driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_deny_button\"]"));
        FluentWait wait = new FluentWait(driver);
        wait.withTimeout(Duration.ofSeconds(15));
        wait.pollingEvery(Duration.ofSeconds(1));
        wait.ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(denyLoc));
        String locTxt = denyLoc.getText();
        System.out.println(locTxt);
        denyLoc.click();
        Thread.sleep(2000);
        WebElement locDeniedMsg = driver.findElement(By.xpath("//android.view.View[@content-desc=\"Location Permission Denied\"]"));
        return locDeniedMsg.isDisplayed();
    }

    public static HashMap<String, String> searchAndFetchNoResults(boolean nowPlaying, boolean topRated) throws InterruptedException {
        Thread.sleep(2000);
        HashMap<String, String> map = new HashMap<>();

        if (nowPlaying) {
            WebElement nowPlayingNone = driver.findElement(By.xpath("(//android.view.View[@content-desc=\"No results found.\"])[1]"));
            System.out.println(nowPlayingNone.getAttribute("content-desc"));
            map.put("now-playing", nowPlayingNone.getAttribute("content-desc"));
        }
        if (topRated) {
            WebElement topRatedNone = driver.findElement(By.xpath("(//android.view.View[@content-desc=\"No results found.\"])[2]"));
            System.out.println(topRatedNone.getAttribute("content-desc"));
            map.put("top-rated", topRatedNone.getAttribute("content-desc"));
        }
        return map;
    }

    public static boolean scrollIntoElementView(String text) throws InterruptedException {
        WebElement element = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(descriptionContains(\"" + text + "\"));"));
        Thread.sleep(1500);
        return element.isDisplayed();
    }

    public static boolean scrollNowPlayingSection(String text) throws InterruptedException {
        WebElement nowPlayingScroll = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.View[5]"));
        WebElement scroll = nowPlayingScroll.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).setAsHorizontalList().scrollIntoView(descriptionContains(\"" + text + "\"));"));
        Thread.sleep(2000);
        return scroll.isDisplayed();
    }


    public static void verifyAllSectionsDisplayed() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement loc = driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='Google Building 43, Google Building 43\nUnknown, Mountain View, California']"));
        Assert.assertTrue(loc.isDisplayed());
        WebElement search = driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.ImageView"));
        Assert.assertTrue(search.isDisplayed());
        WebElement nowPlaying = driver.findElement(By.xpath("//android.view.View[@content-desc=\"NOW PLAYING\"]"));
        Assert.assertTrue(nowPlaying.isDisplayed());
        WebElement topRated = driver.findElement(By.xpath("//android.view.View[@content-desc=\"TOP RATED\"]"));
        Assert.assertTrue(topRated.isDisplayed());
        WebElement weMovies = driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"We Movies\n" +
                "Tab 1 of 3\"]"));
        Assert.assertTrue(weMovies.isDisplayed());
        WebElement explore = driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Explore\n" +
                "Tab 2 of 3\"]"));
        Assert.assertTrue(explore.isDisplayed());
        WebElement upcoming = driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Upcoming\n" +
                "Tab 3 of 3\"]"));
        Assert.assertTrue(upcoming.isDisplayed());
    }


}
