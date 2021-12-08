package org.example;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    protected static AppiumDriver<MobileElement> appiumDriver;
    final static Logger logger= Logger.getLogger(BasePage.class.getName());

    protected boolean localAndroid = true;
    @BeforeScenario
    public void Education() throws MalformedURLException {
        PropertyConfigurator.configure("src/test/java/resources/log4j.properties");

        if(localAndroid){
            logger.info("Android testi başlıyor");
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities
                    .setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"RF8N21BA2QX");
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.ozdilek.ozdilekteyim");
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.ozdilek.ozdilekteyim.MainActivity");
            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 3000);
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            appiumDriver = new AndroidDriver(url, desiredCapabilities);
        }else{
            System.out.println("İos testi başlıyor");
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities
                    .setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            desiredCapabilities
                    .setCapability(MobileCapabilityType.UDID, "00008030-00157936018B802E");
            desiredCapabilities
                    .setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.pozitron.hepsiburada");
            desiredCapabilities
                    .setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone");
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14.7.1");
            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 3000);
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            appiumDriver = new IOSDriver(url, desiredCapabilities);
        }



    }
    @AfterScenario
    public void afterScenario() {
        if(appiumDriver != null)
            appiumDriver.quit();
    }
}
