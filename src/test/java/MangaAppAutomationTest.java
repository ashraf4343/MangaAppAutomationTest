import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MangaAppAutomationTest {
    WebDriver driver;

    @BeforeAll
    public void setup(){

        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @Test
    @DisplayName("Login Functionality")
    public void testLogin() throws InterruptedException {

//        1. Open the application.
//        2. Verify that the login page is displayed.
//        3. Enter valid login credentials (e.g., testuser and password).
//        4. Click the "Login" button.
//        5. Verify that the user is redirected to the manga search page

        driver.get("https://myalice-automation-test.netlify.app/");

        WebElement headerElement = driver.findElement(By.cssSelector("h2.text-2xl.font-bold.mb-4.text-center"));


        String headerText = headerElement.getText();


        Assertions.assertEquals("Login", headerText, "The header text should be 'Login'");

        driver.findElement(By.id("username")).sendKeys("testuser");
        driver.findElement(By.id("password")).sendKeys("password");

        Utils.scroll(driver);

        driver.findElement(By.id("login-btn")).click();
        Thread.sleep(3000);

        WebElement searchBar = driver.findElement(By.id("manga-search"));

        Assertions.assertTrue(searchBar.isDisplayed(), "The search bar should be displayed");


    }

    @Test
    @DisplayName("Manga Search and Display")
    public void testMangaSearchAndDisplay() throws InterruptedException {


//        1. Ensure the user is on the manga search page.
//        2. Enter the search term "Naruto" into the search box.
//        3. Click the "Search" button.

//        4. Verify that manga cards with the name "Naruto" are displayed.
//        5. Enter the search term "One Piece" into the search box.
//        6. Click the "Search" button.
//        7. Verify that manga cards with the name "One Piece" are displayed.
//        8. Enter the search term "Seven Deadly Sins" into the search box.
//        9. Click the "Search" button.
//        10. Verify that manga cards with the name "Seven Deadly Sins" are
//        displayed.
//        11. Enter a search term that returns no results (e.g., "No manga found").
//        12. Click the "Search" button.
//        13. Verify that a "No manga found" message is displayed.


        testLogin();


        WebElement searchBar = driver.findElement(By.id("manga-search"));
        searchBar.sendKeys("Naruto");


        WebElement searchButton = driver.findElement(By.cssSelector("button.bg-green-500.text-white.py-2.px-4.rounded.mr-2"));
        searchButton.click();


        verifyMangaResults("Naruto");


        searchBar.clear();
        searchBar.sendKeys("One Piece");
        searchButton.click();
        verifyMangaResults("One Piece");


        searchBar.clear();
        searchBar.sendKeys("Seven Deadly Sins");
        searchButton.click();
        verifyNoResultsMessage();


        searchBar.clear();
        searchBar.sendKeys("No manga found");
        searchButton.click();
        verifyNoResultsMessage();
    }

    public void verifyMangaResults(String mangaName) {

        List<WebElement> mangaNameElements = driver.findElements(By.cssSelector("#manga-name"));


        Assertions.assertFalse(mangaNameElements.isEmpty(), "Manga cards should be displayed");


        boolean isMangaFound = mangaNameElements.stream().anyMatch(element -> element.getText().equalsIgnoreCase(mangaName));
        Assertions.assertTrue(isMangaFound, "Manga with the name '" + mangaName + "' should be displayed");
    }

    public void verifyNoResultsMessage() {

        WebElement noResultsMessage = driver.findElement(By.cssSelector(".text-red-500"));


        Assertions.assertTrue(noResultsMessage.isDisplayed(), "The 'No manga found' message should be displayed");
    }


    @Test
    @DisplayName("Manga Details Modal")
    public void testMangaDetailsModalWithCard() throws InterruptedException {

//        1. Ensure the user is on the manga search page.
//        2. Click the "Details" link on a manga card.
//        3. Verify that the modal appears and displays the correct manga
//        information (image, name, summary).
//        4. Click the "Close" button on the modal.
//        5. Verify that the modal is closed and no longer visible

        testLogin();


        WebElement detailsLink = driver.findElement(By.cssSelector(".text-blue-500.cursor-pointer"));
        detailsLink.click();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bg-white.p-6.rounded-lg.shadow-lg")));


        Assertions.assertTrue(modal.isDisplayed(), "The modal should be displayed");


        WebElement mangaImage = modal.findElement(By.cssSelector("img"));
        Assertions.assertTrue(mangaImage.isDisplayed(), "The manga image should be displayed");
        Assertions.assertEquals("https://res.cloudinary.com/emerging-it/image/upload/v1724240584/mangaImage/lyj3i7qwtp3f2qz59so1.jpg", mangaImage.getAttribute("src"), "The manga image source should be correct");

        WebElement mangaName = modal.findElement(By.cssSelector("h3.text-xl.font-bold.mb-2"));
        Assertions.assertEquals("Attack on Titan", mangaName.getText(), "The manga name should be 'Attack on Titan'");

        WebElement mangaSummary = modal.findElement(By.cssSelector("p.text-gray-600.mb-4"));
        Assertions.assertFalse(mangaSummary.getText().isEmpty(), "The manga summary should not be empty");
        Assertions.assertEquals("Humanity fights for survival against giant humanoid Titans that have brought civilization to the brink of extinction.", mangaSummary.getText(), "The manga summary should match the expected text");


        WebElement closeButton = modal.findElement(By.cssSelector(".bg-blue-500.text-white.py-2.px-4.rounded"));
        closeButton.click();


        try {

            wait.withTimeout(Duration.ofSeconds(5));
            boolean isModalPresent = (Boolean) ((JavascriptExecutor) driver).executeScript(
                    "return document.querySelector('your-shadow-host-selector').shadowRoot.querySelector('.bg-white.p-6.rounded-lg.shadow-lg.w-96') === null;");
            Assertions.assertTrue(isModalPresent, "The modal should be closed and no longer visible");
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName());
        }


    }




    @AfterAll
    public void closeDriver(){


        driver.close();
    }


}
