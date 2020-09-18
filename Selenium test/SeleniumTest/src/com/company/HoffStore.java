package com.company;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class HoffStore {

    WebDriver driver;

    public void openBrowser() {
        try {
            System.setProperty("webdriver.chrome.driver",
                    "C:\\Users\\Administrator\\Google Drive\\Programmering\\Javagrejer\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

            driver.get("http://hoff.is/store/index.html");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("openBrowser failure");
        }
    }

    public void closeBrowser() {
        try {
            String homeWindow = driver.getWindowHandle();
            Set<String> allWindows = driver.getWindowHandles();

            Iterator<String> windowIterator = allWindows.iterator();
            while (windowIterator.hasNext()) {
                String childWindow = windowIterator.next();
                if (homeWindow.equals(childWindow)) {
                    driver.switchTo().window(childWindow);
                    driver.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("closeBrowser failure");
        }
    }

    public void selectApples() {
        try {
            Select dropdown = new Select(driver.findElement(By.className("form-control")));
            dropdown.selectByValue("1");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("selectApples failure");
        }
    }

    public void selectTV() {
        try {
            Select dropdown = new Select(driver.findElement(By.className("form-control")));
            dropdown.selectByValue("10");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("selectTV failure");
        }
    }

    public void enterAmount(String number) {
        try {
            driver.findElement(By.id("buyAmount")).clear();
            driver.findElement(By.id("buyAmount")).sendKeys(number);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("enterAmount failure");
        }
    }

    public void pressEnterInAmountBox() {
        try {
            driver.findElement(By.id("buyAmount")).sendKeys(Keys.ENTER);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("pressEnter failure");
        }
    }

    public void clickBuyButton() {
        try {
            driver.findElement(By.cssSelector(".btn-primary")).click();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("clickBuyButton failure");
        }
    }

    public String getPurchaseConfirmation() {
        String message = "No message";
        try {
            message = driver.findElement(By.id("message")).getText();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("clickBuyButton failure");
        }
        return message;
    }

    public void clickFirstSellButton() {
        try {
            WebElement tbody = driver.findElement(By.id("bought"));
            tbody.findElement(By.cssSelector("input, button")).click();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("clickFirstSellButton failure");
        }
    }

    public void clickSecondSellButton() {
        try {
            driver.findElement((By.xpath("//button[contains(@onclick,'sellThis(1)')]"))).click();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("clickSecondSellButton failure");
        }
    }

    public String getVATSum() {
        String vat = "No VAT found";
        try {
            vat = driver.findElement(By.id("totalVAT")).getText();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("getVATSum failure");
        }
        return vat;
    }

    public String getReceiptTotal() {
        String total = "No total found";
        try {
            total = driver.findElement(By.id("totalPrice")).getText();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("getReceiptTotal failure");
        }
        return total;
    }

    public String getMoney() {
        String money = "No money found";
        try {
            money = driver.findElement(By.id("money")).getText();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("getMoney failure");
        }
        return money;
    }

    public List<String> getReceiptList() {
        List<String> receiptList = new ArrayList<>();
        try {
            WebElement tbody = driver.findElement(By.id("bought"));
            for(WebElement row : tbody.findElements(By.xpath("./tr"))) {
                String product = row.getText();
                product = product.substring(0, product.indexOf(' '));
                receiptList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("getReceiptList failure");
        }
        return receiptList;
    }

    public boolean isProductFound(List<String> products, String findProduct) {
        boolean isFound = false;
        for(String product : products) {
            if(product.equals(findProduct)) {
                isFound = true;
                break;
            }
        }
        return isFound;
    }
}
