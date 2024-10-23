package org.example.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class AppiumConfiguration {

    File appDir;

    public static AndroidDriver driver;

    public static final String PROPERTIES_FILEPATH = System.getProperty("user.dir") + "/src/test/java/org/example/resources/wework.properties";
    public static final String APP_DIRECTORYPATH = System.getProperty("user.dir") + "/src/test/java/org/example/resources/WeWork.apk";

    Properties properties;

    public File getAppDir() {
        return appDir;
    }

    public void setAppDirPath(String appDir) {
        this.appDir = new File(appDir);
    }

    @BeforeMethod
    public void initAppium() throws IOException {
        loadPropertyFile(PROPERTIES_FILEPATH);

        appDir = new File(APP_DIRECTORYPATH);

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel8");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
//        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);

//        try {
//            desiredCapabilities.setCapability("appPackage", getPackageName("packageName"));
//            desiredCapabilities.setCapability("appActivity", getActivityName("activityName"));
//            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), desiredCapabilities);
//        } catch (SessionNotCreatedException ex) {
        desiredCapabilities.setCapability(MobileCapabilityType.APP, appDir.getAbsolutePath());
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), desiredCapabilities);

//        }

    }

    @AfterMethod
    public void tearDown(ITestResult result) throws InterruptedException {
        if (driver != null) {
            clickAllowPermission();
            driver.terminateApp("com.example.wework");
            driver.quit();
        }
    }

    public void loadPropertyFile(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        properties = new Properties();
        properties.load(fileInputStream);
    }

    public String getPackageName(String propertyKey) {
        return properties.getProperty(propertyKey);
    }

    public String getActivityName(String propertyKey) {
        return properties.getProperty(propertyKey);
    }

    public static void clickAllowPermission() throws InterruptedException {
        try {
            driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_one_time_button")).click();
        } catch (NoSuchElementException ignored){
            System.out.println("No permissions pending.");
        }
        Thread.sleep(2000);
    }

    public static void closeApp(){
        driver.terminateApp("com.example.wework");
    }

    public static void launchApp() throws MalformedURLException {
        File appDir = new File(APP_DIRECTORYPATH);
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel8");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        desiredCapabilities.setCapability(MobileCapabilityType.APP, appDir.getAbsolutePath());
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), desiredCapabilities);
    }

    public static void goOfflineOnline(){
        driver.toggleWifi();
        driver.toggleData();
    }

}
