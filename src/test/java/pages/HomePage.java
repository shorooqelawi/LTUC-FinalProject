package pages;
import utils.*;
import data.Data;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;

public class HomePage {
    WebDriver driver;

    WebElement allFiltersContainer;
    WebElement filltersContainer;
    WebElement cardsContainer;
    By searchBox = By.id("search-query");
    By searchButton = By.cssSelector("button[type='submit']");



    By itemsContainer = By.className("col-md-9");
    By itemsCards = By.className("card-img-wrapper");
    By itemsPrices = By.cssSelector("[data-test='product-price']");
    By itemsName=By.cssSelector("[data-test='product-name']");

    By maxSlider = By.className("ngx-slider-pointer-max");
    By minSlider = By.className("ngx-slider-pointer-min");
    By minValue = By.className("ngx-slider-model-value");
    By maxValue = By.className("ngx-slider-model-high");

    By byBrandFillter = By.xpath("//*[@id=\"filters\"]/fieldset[2]");
    By brands = By.xpath("//*[@id=\"filters\"]/fieldset[2]/div[1]/label");


    By footer = By.className("container-fluid");
    By footerAncures = By.tagName("a");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        allFiltersContainer = CommonUtils.waitForVisibility(driver, By.id("filters"), 3);
        filltersContainer = CommonUtils.waitForVisibility(driver, By.xpath("//div/fieldset"), 3);
    }

    public void searchByName(String item) {
        WebElement searchBoxElem = allFiltersContainer.findElement(searchBox);
        WebElement searchButtonElem = allFiltersContainer.findElement(searchButton);

        searchBoxElem.sendKeys(item);
        searchButtonElem.click();
    }
    
    public void getItemsNames()
    {
    	  cardsContainer = CommonUtils.waitForVisibility(driver, itemsContainer, 3);
          List<WebElement> itemsNames = cardsContainer.findElements(itemsName);
          Data.setCategory(itemsNames.get(CommonUtils.random(itemsNames.size())).getText());
    }

    public void minSlider() throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement minSliderElement = driver.findElement(minSlider);

        actions.clickAndHold(minSliderElement)
            .moveByOffset(CommonUtils.random(200), 0)
            .release()
            .perform();
        Thread.sleep(1000);
        Data.setMinValue(Integer.parseInt(allFiltersContainer.findElement(minValue).getText()));
    }

    public void maxSlider() throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement maxSliderElement = allFiltersContainer.findElement(maxSlider);

        actions.clickAndHold(maxSliderElement)
            .moveByOffset(CommonUtils.random(200), 0)
            .release()
            .perform();
        Thread.sleep(1000);

        Data.setMaxValue(Integer.parseInt(allFiltersContainer.findElement(maxValue).getText()));
    }

    public void getPrice() {
        WebElement allItemsContainer = driver.findElement(itemsContainer);
        List<WebElement> prices = allItemsContainer.findElements(itemsPrices);
        for (int m = 1; m < prices.size(); m++) {
            Data.setItemsPrices(Integer.parseInt(prices.get(m).getText().replace("$", "")));
        }
    }

    public void chooseFillters() {
        List<WebElement> fillters = filltersContainer.findElements(By.className("checkbox"));

        int rand = CommonUtils.random(fillters.size());

        fillters.get(rand).click();

        if (rand == 0 || rand == 8 || rand == 13) {
            int i = 0;
            switch (rand) {
                case 0: i = 1; break;
                case 8: i = 2; break;
                case 13: i = 3; break;
            }

            WebElement subFilters = CommonUtils.waitForVisibility(
                driver,
                By.xpath(String.format("//*[@id='filters']/fieldset[1]/div[%d]", i)),
                3
            );

            
            List<WebElement> allChoicEs = subFilters.findElements(By.xpath("//label[input[@type='checkbox']]"));

            for (int m = 1; m < allChoicEs.size(); m++) {
                Data.setChoosenFilltersNames(allChoicEs.get(m).getText());
            }
        } else {
            Data.setChoosenFilltersNames(fillters.get(rand).getText());
        }
    }
    public void byBrand() {
        WebElement byBrandContainer = allFiltersContainer.findElement(byBrandFillter);
        List<WebElement> byBrandElement = byBrandContainer.findElements(brands);
     
        Data.setChoosenBrand(byBrandElement.get(CommonUtils.random(byBrandElement.size())).getText());}

    public void clickOnItems() {
        cardsContainer = CommonUtils.waitForVisibility(driver, itemsContainer, 3);
        List<WebElement> cards = cardsContainer.findElements(itemsCards);
        cards.get(CommonUtils.random(cards.size())).click();
    }
    


    public void hyperLinkes() {
        WebElement ancuresContainer = CommonUtils.waitForVisibility(driver, footer, 3);
        List<WebElement> ancures = ancuresContainer.findElements(footerAncures);
        int randIndex = CommonUtils.random(ancures.size());
        
        String originalHandle = driver.getWindowHandle();  // التاب الأساسي

        ancures.get(randIndex).click();

        if (randIndex == 2) {
            // لو اندكس 3 نرجع بالباك ونخزن الرابط
            String currentUrl = driver.getCurrentUrl();
           Data.setLinks(currentUrl);

            driver.navigate().back();

        } else {
            // غير هيك: افتح التاب الجديد، خذ الرابط، اسكر التاب، وروح للتاب الأساسي
            Set<String> allHandles = driver.getWindowHandles();

            // استنى التاب الجديد يفتح (ممكن تستخدم WebDriverWait لو حابب)
            for (String handle : allHandles) {
                if (!handle.equals(originalHandle)) {
                    driver.switchTo().window(handle);
                    break;
                }
            }

            String currentUrl = driver.getCurrentUrl();
            Data.setLinks(currentUrl);

            driver.close(); // سكّر التاب الجديد
            driver.switchTo().window(originalHandle); // رجع للتاب الأساسي
        }
    }

   
}
