package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public interface HelperBase extends AppManager{

     default void click(By locator) {
         wd.findElement(locator).click();
    }

    default void type(By locator, String text) {
        WebElement element = wd.findElement(locator);
//        element.click();
        element.clear();
        element.sendKeys(text);
    }

    default void pause(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    default boolean isElementPresent(By locator){
        return wd.findElements(locator).size() > 0;
    }

    default void hideAds(){
        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("document.querySelector('#adplus-anchor').style.display='none'");
    }
    default void hideFooter(){
        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("document.querySelector('footer').style.display='none'");
    }
    default void hideDiv(){
        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("document.querySelector('#fixedban').style.zIndex=-1");
    }




}
