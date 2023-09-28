package cubes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	private WebDriver driver;
	private static final String PAGE_URL = "http://testblog.kurs-qa.cubes.edu.rs/login";

	@FindBy(name = "email")
	private WebElement Email;
	@FindBy(name = "password")
	private WebElement Password;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement Button;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.driver.get(PAGE_URL);
		this.driver.manage().window().maximize();
		PageFactory.initElements(driver, this);
	}
	
	public void loginSuccess() {
		Email.sendKeys("kursqa@cubes.edu.rs");
		Password.sendKeys("cubesqa");
		Button.click();
	}
	
	public void login(String email, String password) {
		Email.sendKeys(email);
		Password.sendKeys(password);
		Button.click();
	}
	
}
