package cubes.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PostsFormPage {

	private WebDriver driver;
	private WebDriverWait driverWait;
	private static final String PAGE_URL = "https://testblog.kurs-qa.cubes.edu.rs/admin/posts/add";

	@FindBy(name = "post_category_id")
	WebElement selectCategory;
	@FindBy(name = "title")
	WebElement inputTitle;
	@FindBy(name="description")
	WebElement inputDescription;
	@FindBy(xpath="//button[@class='btn btn-primary']")
	WebElement buttonSave;
	@FindBy(xpath="//a[text()='Cancel']")
	WebElement buttonCancel;
	@FindBy(id="title-error")
	WebElement titleError;
	@FindBy(id="description-error")
	WebElement descriptionError;
	@FindBy(id="tag_id[]-error")
	WebElement tagError;
	@FindBy(xpath="//div[@class='invalid-feedback']")
	WebElement contentError;
	@FindBy(xpath="//div[@class='col-md-6']//div[4]//div[2]//label")
	WebElement clickImportantYes;
	@FindBy(xpath="//div[@class='col-md-6']//div[4]//div[1]//label")
	WebElement clickImportantNo;
	@FindBy(xpath="//button[@class='btn btn-sm btn-outline-danger']")
	WebElement deletePhoto;
	
	public PostsFormPage(WebDriver driver, WebDriverWait driverWait) {
		this.driver = driver;
		this.driverWait = driverWait;
		this.driver.manage().window().maximize();
		PageFactory.initElements(driver, this);
	}

	public void openPage() {
		this.driver.get(PAGE_URL);
	}
	
	public void clickSave() {
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", buttonSave);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		driverWait.until(ExpectedConditions.visibilityOf(buttonSave));
		buttonSave.click();
	}
	
	public void clickCancel() {
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", buttonCancel);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		driverWait.until(ExpectedConditions.visibilityOf(buttonCancel));
		buttonCancel.click();
	}
	
	public void clickImportantYes() { 
		clickImportantYes.click();
	}
	
	public void clickImportantNo() { 
		clickImportantNo.click();
	}
	
	public void chooseCategory(int number) {
		selectCategory.click();
		driver.findElement(By.xpath("//*[@name='post_category_id']/option["+number+"]")).click();
	}
	
	public void inputStringInTitle(String name) {
		inputTitle.clear();
		inputTitle.sendKeys(name);
	}
	
	public void inputStringInDescription(String text) {
		inputDescription.clear();
		inputDescription.sendKeys(text);
	}
	
	public void clickOnTag(String name) {
		driver.findElement(By.xpath("//label[text()='"+name+"']")).click();
	}
	
	public String getTitleError() {
		return titleError.getText();
	}
	
	public String getDescriptionError() {
		return descriptionError.getText();
	}
	
	public String getTagError() {
		return tagError.getText();
	}
	
	public String getContentError() {
		return contentError.getText();
	}
	
	public void deletePhoto() {
		deletePhoto.click();
	}
	
	public void inputStringInContent(String content) {
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[1]")));
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[1]")));
		WebElement body = driver.findElement(By.cssSelector("body"));
		body.clear();
		body.click();
		body.sendKeys(content);
		driver.switchTo().defaultContent();
	}
}
