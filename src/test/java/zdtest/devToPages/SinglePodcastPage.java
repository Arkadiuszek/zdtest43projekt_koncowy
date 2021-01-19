package zdtest.devToPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SinglePodcastPage {

    WebDriver driver;

    @FindBy(css = ".title > h1:nth-child(2)")
    public WebElement podcastTitle;

    @FindBy(className = "record")
    public WebElement record;

    @FindBy(className = "status-message")
    public WebElement initializing;

    @FindBy(className = "record-wrapper")
    public WebElement recordWrapper;

    public SinglePodcastPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void playPodcast() {
        record.click();
    }
}
