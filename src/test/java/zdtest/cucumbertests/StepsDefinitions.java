package zdtest.cucumbertests;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import zdtest.devToPages.MainPage;
import zdtest.devToPages.PodcastsPage;
import zdtest.devToPages.SinglePodcastPage;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class StepsDefinitions {

    WebDriver driver;
    WebDriverWait wait;
    String podcastTitleFromList;
    String firstPodcastFromListLink;
    String firstVideoUrl;

    MainPage mainPage;
    PodcastsPage podcastsPage;
    SinglePodcastPage singlePodcastPage;

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Given("DevTo main page is open")
    public void devto_main_page_is_open() {
        mainPage = new MainPage(driver);
    }

    @When("User click on podcasts")
    public void user_click_on_podcasts() {
        mainPage.goToPodcasts();
    }
    @When("User select first podcast")
    public void user_select_first_podcast() {
        podcastsPage = new PodcastsPage(driver);
        wait.until(ExpectedConditions.urlToBe(podcastsPage.url));
        podcastTitleFromList = podcastsPage.firstPodcast.getText();
        firstPodcastFromListLink = podcastsPage.firstPodcastHrefElement.getAttribute("href");
        podcastsPage.selectFirstPodcast();
    }

    @Then("User can see its title")
    public void user_can_see_its_title() {
        wait.until(ExpectedConditions.urlToBe(firstPodcastFromListLink));
        singlePodcastPage = new SinglePodcastPage(driver);
        String podcastTitleText = singlePodcastPage.podcastTitle.getText();
        assertTrue(podcastTitleFromList.contains(podcastTitleText));
    }
    @Then("User can play it")
    public void user_can_play_it() {
        singlePodcastPage.playPodcast();
        wait.until(ExpectedConditions.invisibilityOf(singlePodcastPage.initializing));
        String classAttribute = singlePodcastPage.recordWrapper.getAttribute("class");
        Boolean isPodcastPlayed = classAttribute.contains("playing");
        assertTrue(isPodcastPlayed);
    }

    @When("User click on Videos")
    public void user_click_on_Videos() {
        driver.get("https://dev.to");
        WebElement sideBarVideo = driver.findElement(By.partialLinkText("Videos"));
        sideBarVideo.click();
    }

    @When("User click on first video")
    public void user_click_on_first_video() {
        wait.until(ExpectedConditions.urlToBe("https://dev.to/videos"));
        WebElement firstVideo = driver.findElement(By.className("video-image"));
        firstVideoUrl = driver.findElement(By.cssSelector("#video-collection > a:first-child")).getAttribute("href");
        firstVideo.click();
    }

    @Then("User can see first video page")
    public void user_can_see_first_video_page() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("crayons-article__video"))));
        String currentUrl = driver.getCurrentUrl();
        assertEquals(firstVideoUrl,currentUrl);
    }


    @When("User search {string}")
    public void userSearch(String searchingPhrase) {
        WebElement searchBar = driver.findElement(By.name("q"));
        searchBar.sendKeys(searchingPhrase);
        searchBar.sendKeys(Keys.RETURN);
    }

    @Then("Top result should contain the word {string}")
    public void topResultShouldContainTheWord(String expectedPhrase) {
        wait.until(ExpectedConditions.urlContains("search?q=" + expectedPhrase));
        WebElement topResult = driver.findElement(By.className("crayons-story__title"));
        String topResultTitle = topResult.getText();
        topResultTitle = topResultTitle.toLowerCase();
        assertTrue(topResultTitle.contains(expectedPhrase));
    }
}
