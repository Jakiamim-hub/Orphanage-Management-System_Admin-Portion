package Project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class logout {
    public static void main(String[] args) {
        // Set up ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mim\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        try {
            // Navigate to the login page
            driver.get("http://localhost/Orphanage-Management-System/Views/login.php");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Perform login
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
            emailField.sendKeys("jakiamim@gmail.com");

            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("password123");

            WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit' and @value='Login']"));
            submitButton.click();

            // Verify redirection after login
            String expectedDashboardUrl = "http://localhost/Orphanage-Management-System/Views/admin_dashboard.php";
            wait.until(ExpectedConditions.urlToBe(expectedDashboardUrl));

            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.equals(expectedDashboardUrl)) {
                System.out.println("Login Test: Passed");

                // Perform logout by navigating to logout URL
                driver.navigate().to("http://localhost/Orphanage-Management-System/Controllers/logout.php");

                // Verify redirection to the login page after logout
                String expectedLoginUrl = "http://localhost/Orphanage-Management-System/Views/login.php";
                wait.until(ExpectedConditions.urlToBe(expectedLoginUrl));

                String afterLogoutUrl = driver.getCurrentUrl();
                if (afterLogoutUrl.equals(expectedLoginUrl)) {
                    System.out.println("Logout Test: Passed");
                } else {
                    System.out.println("Logout Test: Failed");
                }
            } else {
                System.out.println("Login Test: Failed");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
