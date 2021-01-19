package zdtest.devToPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    
    WebDriver driver;
    String url = "https://dev.to";
    
    @FindBy(partialLinkText = "Podcasts")
    WebElement podcastsBtn;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        driver.get(url);
        PageFactory.initElements(driver,this);
    }

    public void goToPodcasts() {
        podcastsBtn.click();
    }
}
