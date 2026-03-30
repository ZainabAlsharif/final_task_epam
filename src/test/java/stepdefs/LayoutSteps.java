package stepdefs;

import hooks.Hooks;
import pages.InventoryPage;
import pages.LoginPage;

import java.util.List;
import org.testng.Assert;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class LayoutSteps {
    private LoginPage loginPage() {
        return new LoginPage(Hooks.getDriver());
    }

    private InventoryPage inventoryPage() {
        return new InventoryPage(Hooks.getDriver());
    }

    @Given("^I open the SauceDemo login page$")
    public void openLogin() {
        Hooks.getDriver().get("https://www.saucedemo.com");
    }

    @Then("^I see the \"([^\"]*)\" field and \"([^\"]*)\" field$")
    public void verifyFields(String f1, String f2) {
        Assert.assertTrue(loginPage().isUserFieldDisplayed());
        Assert.assertTrue(loginPage().isPassFieldDisplayed());
    }

    @And("^I see the \"([^\"]*)\" button and \"([^\"]*)\" header text$")
    public void verifyLoginHeader(String b1, String h1) {
        Assert.assertTrue(loginPage().isLoginBtnDisplayed());
        Assert.assertEquals(loginPage().getLogoText(), h1);
    }

    @Given("^I am logged in as \"([^\"]*)\" with password \"([^\"]*)\"$")
    public void doLogin(String u, String p) {
        openLogin();
        loginPage().login(u, p);
    }

    @Then("^I am redirected to the \"([^\"]*)\" page$")
    public void verifyRedirect(String page) {
        Assert.assertTrue(Hooks.getDriver().getCurrentUrl().contains(page));
    }

    @And("^I see the \"([^\"]*)\" header and \"([^\"]*)\"$")
    public void verifyInventoryHeader(String h, String c) {
        Assert.assertEquals(inventoryPage().getHeaderText(), h);
        Assert.assertTrue(inventoryPage().isCartDisplayed());
    }

    @And("^I see social links for \"([^\"]*)\", \"([^\"]*)\", and \"([^\"]*)\"$")
    public void verifySocial(String s1, String s2, String s3) {
        Assert.assertTrue(inventoryPage().areSocialLinksVisible());
    }

    @And("^the sorting dropdown contains Name and Price options$")
    public void verifySort() {
        List<String> options = inventoryPage().getSortOptions();
        Assert.assertTrue(options.contains("Name (A to Z)"));
        Assert.assertTrue(options.contains("Price (low to high)"));
    }

    @When("^I click the menu icon$")
    public void clickMenu() { inventoryPage().openMenu(); }

    @And("^the item page keeps inventory header, cart, menu button, and social links$")
    public void verifyItemPageCommonElements() {
        Assert.assertEquals(inventoryPage().getHeaderText(), "Swag Labs");
        Assert.assertTrue(inventoryPage().isCartDisplayed());
        Assert.assertTrue(inventoryPage().areSocialLinksVisible());
    }

    @Then("^the menu contains \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", and \"([^\"]*)\"$")
    public void verifyMenu(String m1, String m2, String m3, String m4) {
        List<String> items = inventoryPage().getMenuItems();
        Assert.assertTrue(items.contains(m1));
        Assert.assertTrue(items.contains(m2));
    }

    @When("^I click on any inventory item$")
    public void clickItem() { inventoryPage().clickFirstItem(); }

    @Then("^I see the product image, name, description, and price$")
    public void verifyItemDetails() {
        Assert.assertTrue(inventoryPage().isItemImageDisplayed());
        Assert.assertTrue(inventoryPage().isItemNameDisplayed());
        Assert.assertTrue(inventoryPage().isItemDescDisplayed());
        Assert.assertTrue(inventoryPage().isItemPriceDisplayed());
    }

    @And("^I see the \"([^\"]*)\" button and \"([^\"]*)\" link$")
    public void verifyItemButtons(String b, String l) {
        Assert.assertTrue(inventoryPage().isAddToCartBtnDisplayed());
        Assert.assertTrue(inventoryPage().isBackToProductsLinkDisplayed());
    }
}