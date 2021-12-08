package org.example;

import com.github.javaparser.ParseResult;
import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;


import java.util.List;
import java.util.Random;

public class BasePage extends BaseTest{
    @Step("<wait> saniye bekle")
    public void waitForsecond(int wait) throws InterruptedException {
        Thread.sleep(1000*wait);
        logger.info(wait+" saniye bekliyor");
        System.out.printf(wait+"saniye bekle");
    }
    @Step("<element> tıkla")
    public void elementClick(String selectorid) {
        appiumDriver.findElement(By.id(selectorid)).click();
        logger.info(selectorid+ " adlı elemente tıklanıyor");
    }
    @Step("<key> id'li element <keyword> text değerini içeridiğini kontrol et")
    public void textControl(String key,String keyword){
        System.out.println(appiumDriver.findElement(By.id(key)).getText());

        Assert.assertFalse("Element hatalı",appiumDriver.findElement(By.id(key)).isDisplayed());
    }
    @Step("<key> id kontrol")
    public void idControl(String key){
        logger.info("Sayfa kontrolü yapılıyor");
        Assert.assertTrue("",appiumDriver.findElement(By.id(key)).isDisplayed());
        logger.info(appiumDriver.findElement(By.id(key)).toString());
    }
    @Step("<key> xpath kontrol")
    public void xpathControl(String selectorid){
        logger.info("Sayfada olup olmadığımız kontrol ediliyor.");
     Assert.assertTrue(   appiumDriver.findElement(By.xpath(selectorid)).isDisplayed());
    }
    @Step("<xpath> xpath tıkla")
    public void xpathClick(String xpath){
        appiumDriver.findElement(By.xpath(xpath)).click();
        logger.info(xpath+ " tıklanıyor");

    }
    @Step("Scroll Yap")
    public void scroll(){
        logger.info("scroll yapılıyor");
        Dimension dimension = appiumDriver.manage().window().getSize();
        int startx =(int)(dimension.getWidth()*0.5);
        int starty= (int)(dimension.getHeight()*0.8);
        int endx =536;
        int endy =0;
        TouchAction touchAction = new TouchAction(appiumDriver);
        touchAction.longPress(PointOption.point(startx, starty)).moveTo(PointOption.point(endx, endy)).release().perform();
        logger.info("scroll işlemi bitti");

    }
    @Step("<xpath> Random ürün seç")
    public void randomProduct(String xpath){
        logger.info("Random ürün seçiliyor");
            List<MobileElement> elements = appiumDriver.findElements(By.xpath(xpath));
            Random random = new Random();
            int randomInt = random.nextInt(elements.size());
      logger.info("Random seçilen ürün "+elements.get(randomInt).getText());
      logger.info("Ürünün detay sayfası açılıyor");
            elements.get(randomInt).click();

    }
    @Step("<selectorid> id'li elemente <text> text değerini yaz")
    public void sendKeysbyid(String id,String text){
        logger.info("Deper yazılmaya başlıyor");
        appiumDriver.findElement(By.id(id)).sendKeys(text);
        logger.info(text+"Değeri yazıldı");
    }
}
