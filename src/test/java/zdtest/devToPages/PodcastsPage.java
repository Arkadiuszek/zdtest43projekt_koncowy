package zdtest.devToPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PodcastsPage {

    WebDriver driver;
    public String url = "https://dev.to/pod";

    @FindBy(css = ".content > h3:first-child")
    public WebElement firstPodcast;

    @FindBy(css = "#substories > a:first-child")
    public WebElement firstPodcastHrefElement;

    public PodcastsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void selectFirstPodcast() {
        firstPodcast.click();
    }
}
