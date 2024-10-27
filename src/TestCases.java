import java.time.Duration;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCases {
	WebDriver driver = new ChromeDriver();
	String theWebsite = "https://automationteststore.com/";
	String[] firstNames = { "ahmad", "ali", "omar", "ayat", "alla", "sawsan", "rama" };
	String[] lastNames = { "mohammad", "mustafa", "abdullah", "malek", "saleh", "akram", "zaid" };
	Random rand = new Random();
	String globalUserName = "";
	String globalUserNameforTheLogin = "";
	String password = "Ilovemymom123@";

	@BeforeTest
	public void setUp() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get(theWebsite);
	}

	@Test(priority = 1, enabled = false)
	public void signUp() throws InterruptedException {
		int randomIndexForTheFirstName = rand.nextInt(firstNames.length);
		int randomIndexForTheLastName = rand.nextInt(lastNames.length);
		String userFirstName = firstNames[randomIndexForTheFirstName];
		String userLastName = lastNames[randomIndexForTheLastName];
		int randomNumberForTheEmail = rand.nextInt(564548);
		String domainName = "@gmail.com";
		String email = userFirstName + userLastName + randomNumberForTheEmail + domainName;
		driver.findElement(By.linkText("Login or register")).click();
		WebElement signUpButton = driver.findElement(By.xpath("//button[@title='Continue']"));
		signUpButton.click();
		Thread.sleep(2000);
		WebElement firstNameInput = driver.findElement(By.id("AccountFrm_firstname"));
		firstNameInput.sendKeys(userFirstName);
		globalUserName = userFirstName;
		WebElement lastNameInput = driver.findElement(By.id("AccountFrm_lastname"));
		lastNameInput.sendKeys(userLastName);
		WebElement emailInput = driver.findElement(By.id("AccountFrm_email"));
		emailInput.sendKeys(email);
		WebElement addressInput = driver.findElement(By.id("AccountFrm_address_1"));
		addressInput.sendKeys("Amman");
		WebElement cityInput = driver.findElement(By.id("AccountFrm_city"));
		cityInput.sendKeys("Capital City");
		WebElement countryInput = driver.findElement(By.id("AccountFrm_country_id"));
		Select selector2 = new Select(countryInput);
		int randomCountry = rand.nextInt(1, 240);
		selector2.selectByIndex(randomCountry);
		Thread.sleep(3000);
		WebElement zoneIdInput = driver.findElement(By.id("AccountFrm_zone_id"));
		Select selector = new Select(zoneIdInput);
		int randomState = rand.nextInt(1, 5);
		selector.selectByIndex(randomState);
		WebElement postalCodeInput = driver.findElement(By.id("AccountFrm_postcode"));
		postalCodeInput.sendKeys("13310");
		WebElement loginNameInput = driver.findElement(By.id("AccountFrm_loginname"));
		String localUserName = userFirstName + userLastName + randomNumberForTheEmail;
		loginNameInput.sendKeys(localUserName);
		globalUserNameforTheLogin = localUserName;
		WebElement passwordInput = driver.findElement(By.id("AccountFrm_password"));
		passwordInput.sendKeys(password);
		WebElement confirmPasswordInput = driver.findElement(By.id("AccountFrm_confirm"));
		confirmPasswordInput.sendKeys(password);
		WebElement agreeCheckBox = driver.findElement(By.id("AccountFrm_agree"));
		agreeCheckBox.click();
		WebElement continueButton = driver.findElement(By.xpath("//button[@title='Continue']"));
		continueButton.click();
	}

	@Test(priority = 2, enabled = false)
	public void logOut() throws InterruptedException {
//		Thread.sleep(5000);
//		String logOutUrl = "https://automationteststore.com/index.php?rt=account/logout";
//		driver.get(logOutUrl);
		Thread.sleep(2000);
		WebElement userNav = driver.findElement(By.id("customernav"));
		Actions action = new Actions(driver);
		action.moveToElement(userNav).perform();
		Thread.sleep(2000);
		WebElement logOutClick = driver.findElement(By.linkText("Not " + globalUserName + "? Logoff"));
		logOutClick.click();
	}

	@Test(priority = 3, enabled = false)
	public void logIn() {
		driver.findElement(By.linkText("Login or register")).click();
		WebElement loginInput = driver.findElement(By.id("loginFrm_loginname"));
		loginInput.sendKeys(globalUserNameforTheLogin);
		WebElement passwordInput = driver.findElement(By.id("loginFrm_password"));
		passwordInput.sendKeys(password);
		WebElement logIinButton = driver.findElement(By.xpath("//button[@title='Login']"));
		logIinButton.click();
	}

	@Test(priority = 4)
	public void addItemToCart() throws InterruptedException {
//		Thread.sleep(3000);
//		WebElement listOfItem = driver.findElement(By.cssSelector(".nav-pills.categorymenu"));
//		List<WebElement> theItemInsidTheListSubCategories = listOfItem.findElements(By.tagName("li"));
//			//	listOfItem.findElements(By.className("subcategories"));
//		theItemInsidTheListSubCategories.get(2).click();

		String[] websitesForTheItems = { "https://automationteststore.com/index.php?rt=product/category&path=68",
				"https://automationteststore.com/index.php?rt=product/category&path=36",
				"https://automationteststore.com/index.php?rt=product/category&path=43",
				"https://automationteststore.com/index.php?rt=product/category&path=49",
				"https://automationteststore.com/index.php?rt=product/category&path=58",
				"https://automationteststore.com/index.php?rt=product/category&path=52",
				"https://automationteststore.com/index.php?rt=product/category&path=65" };

		int randomIndexForTheMainLists = rand.nextInt(websitesForTheItems.length);
		driver.get(websitesForTheItems[randomIndexForTheMainLists]);

		// define for the webelement which is a UL tag
		WebElement listOfSubCategory = driver.findElement(By.cssSelector(".thumbnails.row"));
		// each UL tag has always a li items (list items ) here i am getting the total
		// number of li inside the ul
		int totalNumberOfSubCategory = listOfSubCategory.findElements(By.tagName("li")).size();
		// create a random index based on the total number of sub category that i got in
		// the previous line
		int randomIndexForTheSubList = rand.nextInt(totalNumberOfSubCategory);
		// sleep just to see the results before click on the sub category
		Thread.sleep(3000);
		// inside the sub category i need selenium to click on a random sub category
		listOfSubCategory.findElements(By.tagName("li")).get(randomIndexForTheSubList).click();
		// i defined the container of all items in a Container variable to loop over all
		// items inside using the css selector
		WebElement container = driver.findElement(By.cssSelector(".thumbnails.grid.row.list-inline"));
		// we need to see how many items that selenium found inside the container
		int totalNumberOfItems = container.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12")).size();
		// random index for the item to randomly select one item each time
		int randomNumberForTheItems = rand.nextInt(totalNumberOfItems);
		// the randomly item that we generated using rand.nextInt we need to click on
		// that item
		container.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12")).get(randomNumberForTheItems).click();
		Thread.sleep(5000);
		// here i have an UL tag which contains either the Add to cart or out of the
		// stock
		WebElement ulList = driver.findElement(By.className("productpagecart"));
		// inside the UL that i found in the previous line of code i am searching about
		// the a tag noting:-(a) tag if the item in the stock and span tag if the item
		// out of the stock
		int liItem = ulList.findElements(By.tagName("li")).get(0).findElements(By.tagName("a")).size();
		int spanItem = ulList.findElements(By.tagName("li")).get(0).findElements(By.tagName("span")).size();
		// this liItem will give me either 0 or 1 ( 0 if the item out of the stock so it
		// will go back to the home page and print a message says that the item out of
		// the stock , if it gives 1 that means the item inside the stock and i can
		// press on the add to cart button by using it's name which is cart
		if (liItem > 0) {
			driver.findElement(By.className("cart")).click();
			System.out.println("Added to cart");
		} else if (spanItem > 0) {
			driver.get(theWebsite);
			System.out.println("sorry the item out of the stock");
		}
		else {
			driver.findElement(By.className("call_to_order")).click();
			System.out.println("Thanks for calling");
		}
	}
}
