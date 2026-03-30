package pages;

import org.openqa.selenium.*;

public class LoginPage {
    private WebDriver driver;
    private By userField = By.cssSelector("#user-name");
    private By passField = By.cssSelector("#password");
    private By loginBtn = By.xpath("//input[@id='login-button']");
    private By logo = By.className("login_logo");

    public LoginPage(WebDriver driver) { this.driver = driver; }

    public void login(String user, String pass) {
        driver.findElement(userField).sendKeys(user);
        driver.findElement(passField).sendKeys(pass);
        driver.findElement(loginBtn).click();
    }

    public boolean isUserFieldDisplayed() { return driver.findElement(userField).isDisplayed(); }
    public boolean isPassFieldDisplayed() { return driver.findElement(passField).isDisplayed(); }
    public boolean isLoginBtnDisplayed() { return driver.findElement(loginBtn).isDisplayed(); }
    public String getLogoText() { return driver.findElement(logo).getText(); }
}