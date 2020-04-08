package Pages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class QaAutomate extends RandomData {

	@Test
	public void placeOrder() throws InterruptedException {

		String emailAddress = RandomData.randomStringGenerator() + "@gmail.com";
		String pwd = RandomData.randomStringGenerator() + "@12";
		String fName = RandomData.randomStringGenerator();
		String lName = RandomData.randomStringGenerator();
		String address = RandomData.randomStringGenerator() + ",door#10,Plot#50";
		String phoneNo = RandomData.randomIntegerGenerator();

		System.setProperty("webdriver.chrome.driver", "./src/test/resources/ChromeDriver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("http://automationpractice.com/index.php");
		Reporter.log("Launched Application");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// 1. Create Account under Sign In.
		// YOUR PERSONAL INFORMATION
		driver.findElement(By.xpath("//a[contains(text(),'Sign in')]")).click();
		js.executeScript("window.scrollTo(0,300)");
		driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("#SubmitCreate > span")).click();
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0,300)");
		driver.findElement(By.id("id_gender2")).click();
		driver.findElement(By.id("customer_firstname")).sendKeys(fName);
		driver.findElement(By.id("customer_lastname")).sendKeys(lName);
		driver.findElement(By.id("passwd")).sendKeys(pwd);
		Reporter.log("Entered Personal Information details");

		// YOUR ADDRESS
		js.executeScript("window.scrollTo(0,300)");
		driver.findElement(By.id("firstname")).sendKeys(fName);
		driver.findElement(By.id("lastname")).sendKeys(lName);
		driver.findElement(By.id("company")).sendKeys("HOME");
		driver.findElement(By.id("address1")).sendKeys(address);
		driver.findElement(By.id("city")).sendKeys("Los Angeles");
		Select select = new Select(driver.findElement(By.id("id_state")));
		select.selectByVisibleText("California");
		driver.findElement(By.id("postcode")).sendKeys("90003");
		driver.findElement(By.id("phone_mobile")).sendKeys(phoneNo);
		driver.findElement(By.cssSelector("#alias")).sendKeys(" Home");
		driver.findElement(By.cssSelector("#submitAccount > span")).click();
		Reporter.log("Entered Address details");
		Reporter.log("Registered user sucessfully");

		// Sign Out
		driver.findElement(By.xpath("//a[contains(text(),'Sign out')]")).click();
		Thread.sleep(2000);
		Reporter.log("signed out from Application");

		// 2. Login with new user credentials.
		driver.findElement(By.id("email")).sendKeys(emailAddress);
		driver.findElement(By.id("passwd")).sendKeys(pwd);
		driver.findElement(By.xpath("//*[@id='SubmitLogin']")).click();
		Reporter.log("Logged in with registered user name and password");

		// 3. On WOMAN section, select any 1 product by clicking on QUICK VIEW
		// and adding 2 Qty for the product to cart.

		driver.findElement(By.xpath("//a[contains(text(),'Women')]")).click();
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0,800)");
		Reporter.log("Clicked on Women Section");

		Actions actions = new Actions(driver);
		actions.moveToElement( driver.findElement(By.xpath("//*[@id='center_column']/ul/li[2]/div/div[1]/div/a[1]/img"))).build().perform();
		driver.findElement(By.xpath("//div[@id='center_column']/ul/li[2]/div/div/div/a[2]/span")).click();
		Reporter.log("Mouse over on product and clicked on Quick view");
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fancybox-inner']//iframe")));
		driver.findElement(By.cssSelector(".icon-plus")).click();
		Reporter.log("Switched to the frame and increased the product amount to 2");
		driver.findElement(By.xpath("//span[contains(text(),'Add to cart')]")).click();
		Reporter.log("Clicked on Add to cart button");
		Thread.sleep(2000);

		// 4. Complete the checkout process by completing the payment flow and
		// in process verifying the total cost of product wherever visible.
		driver.findElement(By.xpath("//span[contains(text(),'Proceed to checkout')]")).click();
		Reporter.log("Clicked on Proceed to checkout button");
		js.executeScript("window.scrollTo(0,800)");
		Thread.sleep(2000);
		String totalPrice = driver.findElement(By.xpath("//td[@class='cart_total']//span")).getText();
		String totalPriceWithTax = driver.findElement(By.xpath("//td[@id='total_price_container']//span")).getText();

		System.out.println("Price:  " + totalPrice);
		System.out.println("PriceWithTax:  " + totalPriceWithTax);
		Reporter.log("TotalPriceWithTax:  " + totalPriceWithTax);

		driver.findElement(By.xpath("//*[@id='center_column']/p[2]/a[1]")).click();
		Reporter.log("Clicked on Proceed to checkout button");
		js.executeScript("window.scrollTo(0,500)");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='center_column']/form/p/button")).click();
		Reporter.log("Clicked on Proceed to checkout button");
		js.executeScript("window.scrollTo(0,500)");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='cgv']")).click();
		Reporter.log("Clicked on 'Accept Terms and Services' radio button");
		driver.findElement(By.xpath("//*[@id='form']/p/button")).click();
		Reporter.log("Clicked on Proceed to checkout button");


		js.executeScript("window.scrollTo(0,500)");
		Thread.sleep(2000);
		Assert.assertEquals("$56.00", totalPriceWithTax);
		Reporter.log("Verified the Total product amount");

		driver.findElement(By.xpath("//a[@class='bankwire']")).click();
		Reporter.log("Clicked on 'pay by Bank wire' payment method");
		js.executeScript("window.scrollTo(0,500)");
		Thread.sleep(2000);
		String textMsg = driver.findElement(By.xpath("//*[@id='center_column']/form/div/p[2]")).getText();
		Assert.assertTrue(textMsg.contains(totalPriceWithTax));
		Reporter.log("Verified the Total product amount");

		driver.findElement(By.xpath("//span[contains(text(),'I confirm my order')]")).click();
		Reporter.log("Clicked on 'I confirm my order'");

		js.executeScript("window.scrollTo(0,500)");
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.xpath("//p[@class='cheque-indent']")).getText());

		String textAmountMsg = driver.findElement(By.xpath("//strong[contains(text(),'$56.00')]")).getText();
		Assert.assertEquals("$56.00", textAmountMsg);
		Reporter.log("Verified the Total product amount");

		// 5. After completing the payment process, under Profile page, verify
		// the amount of order under ORDER HISTORY.s
		driver.findElement(By.xpath("//a[@class='account']//span")).click();
		Reporter.log("Clicked on My Account");

		driver.findElement(By.xpath("//span[contains(text(),'Order history and details')]")).click();
		Reporter.log("Clicked on 'Order history and details' tap");

		String orderAmount = driver.findElement(By.xpath("//*[@id='order-list']/tbody/tr/td[3]")).getText();
		Assert.assertEquals("$56.00", orderAmount);
		Reporter.log("Verified the Total product amount");

		driver.quit();

	}

}
