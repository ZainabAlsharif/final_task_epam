package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage {
    private WebDriver driver;

    // UC-2 Locators
    private By header = By.className("app_logo");
    private By cart = By.cssSelector(".shopping_cart_link");
    private By twitter = By.xpath("//a[text()='Twitter']");
    private By facebook = By.xpath("//a[text()='Facebook']");
    private By linkedin = By.xpath("//a[text()='LinkedIn']");
    private By sortBox = By.className("product_sort_container");
    private By menuBtn = By.id("react-burger-menu-btn");
    private By menuLinks = By.cssSelector(".bm-item-list a.menu-item");

    // UC-3 Locators
    private By firstItem = By.className("inventory_item_name");
    private By itemImage = By.className("inventory_details_img");
    private By itemName = By.className("inventory_details_name");
    private By itemDesc = By.className("inventory_details_desc");
    private By itemPrice = By.className("inventory_details_price");
    private By addToCartBtn = By.xpath("//button[text()='Add to cart']");
    private By backToProducts = By.id("back-to-products");

    public InventoryPage(WebDriver driver) { this.driver = driver; }

    public String getHeaderText() { return driver.findElement(header).getText(); }
    public boolean isCartDisplayed() { return driver.findElement(cart).isDisplayed(); }
    public void clickFirstItem() { driver.findElement(firstItem).click(); }
    public void openMenu() {
        //wait for the button to be ready
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(menuBtn));

        // We use JavaScript to click because Selenium clicks sometimes fail if an animation is still happening in the background
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }


    public boolean areSocialLinksVisible() {
        return driver.findElement(twitter).isDisplayed() &&
                driver.findElement(facebook).isDisplayed() &&
                driver.findElement(linkedin).isDisplayed();
    }

    public List<String> getSortOptions() {
        Select select = new Select(driver.findElement(sortBox));
        return select.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getMenuItems() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(menuLinks));

        return driver.findElements(menuLinks).stream()
                .map(WebElement::getText)
                .filter(text -> !text.trim().isEmpty())
                .collect(Collectors.toList());
    }

    // UC-3 Validation Methods
    public boolean isItemImageDisplayed() { return driver.findElement(itemImage).isDisplayed(); }
    public boolean isItemNameDisplayed() { return driver.findElement(itemName).isDisplayed(); }
    public boolean isItemDescDisplayed() { return driver.findElement(itemDesc).isDisplayed(); }
    public boolean isItemPriceDisplayed() { return driver.findElement(itemPrice).isDisplayed(); }
    public boolean isAddToCartBtnDisplayed() { return driver.findElement(addToCartBtn).isDisplayed(); }
    public boolean isBackToProductsLinkDisplayed() { return driver.findElement(backToProducts).isDisplayed(); }
}