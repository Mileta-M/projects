package cubes.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PostsListPage {

	private WebDriver driver;
	private WebDriverWait driverWait;
	private static final String PAGE_URL = "https://testblog.kurs-qa.cubes.edu.rs/admin/posts";

	@FindBy(name = "title")
	WebElement searchTitle;
	@FindBy(xpath="//input[@class='form-control form-control-sm']")
	WebElement searchTitleBar;
	@FindBy(xpath = "//span[text()='--Choose Author --']")
	WebElement chooseAuthor;
	@FindBy(xpath = "//span[@title='--Choose Category --']")
	WebElement chooseCategory;
	@FindBy(name = "important")
	WebElement searchImportant;
	@FindBy(name = "status")
	WebElement searchStatus;
	@FindBy(xpath = "//ul[@class='select2-selection__rendered']")
	WebElement searchWithTag;
	@FindBy(xpath = "//a[@class='btn btn-success']")
	WebElement addNewPost;
	@FindBy(xpath = "//div[@class='toast-message']")
	WebElement toastMessage;
	@FindBy(xpath="//i[@class='fas fa-eye']")
	WebElement viewPost;
	@FindBy(xpath="//i[@class='fas fa-minus-circle']")
	WebElement disableButton;
	@FindBy(xpath="//form[@id='disable-modal']//button[@class='btn btn-danger']")
	WebElement dialogDisable;
	@FindBy(xpath="//form[@id='disable-modal']//button[@class='btn btn-default']")//"//form[@id='disable-modal']//button"
	WebElement dialogCancelDisable;
	@FindBy(xpath="//i[@class='fas fa-check']")
	WebElement enableButton;
	@FindBy(xpath="//form[@id='enable-modal']//button[2]")
	WebElement dialogEnable;
	@FindBy(xpath="//form[@id='enable-modal']//button")
	WebElement dialogCancelEnable;
	@FindBy(xpath="//i[@class='fas fa-bookmark']")
	WebElement importantButton;
	@FindBy(xpath="//form[@id='important-modal']//button[2]")
	WebElement dialogImportant;
	@FindBy(xpath="//form[@id='important-modal']//button")
	WebElement dialogCancelImportant;
	@FindBy(xpath="//i[@class='fas fa-times']")
	WebElement unimportantButton;
	@FindBy(xpath="//form[@id='unimportant-modal']//button[2]")
	WebElement dialogUnimportant;
	@FindBy(xpath="//form[@id='unimportant-modal']//button")
	WebElement dialogCancelUnimportant;
	@FindBy(xpath="//i[@class='fas fa-edit']")
	WebElement editButton;
	@FindBy(xpath="//i[@class='fas fa-trash']")
	WebElement deleteButton;
	@FindBy(xpath="//button[text()='Delete']")
	WebElement dialogDelete;
	@FindBy(xpath="//button[text()='Cancel']")
	WebElement dialogCancelDelete;
	
	public PostsListPage(WebDriver driver, WebDriverWait driverWait) {
		this.driver = driver;
		this.driverWait = driverWait;
		this.driver.manage().window().maximize();
		PageFactory.initElements(driver, this);
	}
	
	public void openPage() {
		this.driver.get(PAGE_URL);
	}

	public void openParentLinkInMenu(String title) {
		WebElement Menu = driver.findElement(By.xpath("//p[text()='" + title + "']//ancestor::li[2]"));

		if (!Menu.getAttribute("class").toString().equalsIgnoreCase("nav-item has-treeview menu-open")) {
			Menu.click();
		}
	}

	public void clickOnLinkInMenu(String title) {
		WebElement Link = driver.findElement(By.xpath("//p[text()='" + title + "']"));
		driverWait.until(ExpectedConditions.visibilityOf(Link));
		Link.click();
	}

	public void clickOnNavigationLink(String title) {
		WebElement Link = driver.findElement(By.xpath("//a[text()='" + title + "']"));
		Link.click();
	}

	public String getToastMessage() {
		driverWait.until(ExpectedConditions.visibilityOf(toastMessage));
		try {
			return toastMessage.getText();
		} catch (Exception e) {
			return "";
		}
	}

	public void addNewPostLink() {
		addNewPost.click();
	}

	public void clickOnEditButton() {
		driverWait.until(ExpectedConditions.visibilityOf(editButton));
		editButton.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void searchByTitle(String title) {
		driverWait.until(ExpectedConditions.visibilityOf(searchTitle));
		searchTitle.sendKeys(title);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void searchByTitleBar(String title) {
		driverWait.until(ExpectedConditions.visibilityOf(searchTitleBar));
		searchTitleBar.sendKeys(title);
	}

	public void searchByAuthor(String author) {
		driverWait.until(ExpectedConditions.visibilityOf(chooseAuthor));
		chooseAuthor.click();
		driver.findElement(By.xpath("//li[text()='"+author+"']")).click();
	}
	
	public void searchByCategory(String category) {
		driverWait.until(ExpectedConditions.visibilityOf(chooseCategory));
		chooseCategory.click();
		driver.findElement(By.xpath("//li[text()='"+category+"']")).click();
	}
	
	public void searchByImportant(String option) {
		driverWait.until(ExpectedConditions.visibilityOf(searchImportant));
		searchImportant.click();
		driver.findElement(By.xpath("//option[text()='"+option+"']")).click();
	}
	
	public void searchByStatus(String option) {
		driverWait.until(ExpectedConditions.visibilityOf(searchStatus));
		searchStatus.click();
		driver.findElement(By.xpath("//option[text()='"+option+"']")).click();
	}
	
	public void searchByWithTag(String tag) {
		driverWait.until(ExpectedConditions.visibilityOf(searchWithTag));
		searchWithTag.click();
		driver.findElement(By.xpath("//li[text()='"+tag+"']")).click();
	}
	
	public boolean isPostInList(String postName) {
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody//tr//td[text()='" + postName + "']")));
		boolean isInList = driver.findElements(By.xpath("//tbody//tr//td[text()='" + postName + "']")).size() > 0;
		return isInList;
	}
	
	public boolean isPostInListCategory(String postName) {
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody//tr//td[contains(text(), '" + postName + "')]")));
		boolean isInList = driver.findElements(By.xpath("//tbody//tr//td[contains(text(), '" + postName + "')]")).size() > 0;
		return isInList;
	}
	
	public boolean isPostInListImportant(String imp) {
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody//tr//td[3]//span[text()='"+imp+"']")));
		boolean isInList = driver.findElements(By.xpath("//tbody//tr//td[3]//span[text()='"+imp+"']")).size() > 0;
		return isInList;
	}
	
	public boolean isPostInListStatus(String status) {
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody//tr//td[4]//span[text()='"+status+"']")));
		boolean isInList = driver.findElements(By.xpath("//tbody//tr//td[4]//span[text()='"+status+"']")).size() > 0;
		return isInList;
	}
	
	public String getTitle() {
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody//tr//td[5]")));
		return driver.findElement(By.xpath("//tbody//tr//td[5]")).getText();
	}
	
	public void clickOnViewPost() {
		driverWait.until(ExpectedConditions.visibilityOf(viewPost));
		viewPost.click();
	}
	
	public void clickOnDisableButton() {
//		driverWait.until(ExpectedConditions.visibilityOf(disableButton));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		disableButton.click();
	}
	
	public void clickOnDialogDisable() {
//		driverWait.until(ExpectedConditions.visibilityOf(dialogDisable));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		dialogDisable.click();
	}
	
	public void clickOnDialogCancelDisable() {
//		driverWait.until(ExpectedConditions.visibilityOf(dialogCancelDisable));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		dialogCancelDisable.click();
	}
	
	public void clickOnEnableButton() {
//		driverWait.until(ExpectedConditions.visibilityOf(enableButton));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		enableButton.click();
	}
	
	public void clickOnDialogEnable() {
//		driverWait.until(ExpectedConditions.visibilityOf(dialogEnable));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		dialogEnable.click();
	}
	
	public void clickOnDialogCancelEnable() {
//		driverWait.until(ExpectedConditions.visibilityOf(dialogCancelEnable));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		dialogCancelEnable.click();
	}
	
	public void clickOnImportantButton() {
//		driverWait.until(ExpectedConditions.visibilityOf(importantButton));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		importantButton.click();
	}
	
	public void clickOnUnimportantButton() {
//		driverWait.until(ExpectedConditions.visibilityOf(unimportantButton));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		unimportantButton.click();
	}
	
	public void clickOnDialogCancelImportant() {
//		driverWait.until(ExpectedConditions.visibilityOf(dialogCancelImportant));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		dialogCancelImportant.click();
	}
	
	public void clickOnDialogImportant() {
//		driverWait.until(ExpectedConditions.visibilityOf(dialogImportant));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		dialogImportant.click();
	}
	
	public void clickOnDialogCancelUnimportant() {
//		driverWait.until(ExpectedConditions.visibilityOf(dialogCancelUnimportant));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		dialogCancelUnimportant.click();
	}
	
	public void clickOnDialogUnimportant() {
//		driverWait.until(ExpectedConditions.visibilityOf(dialogUnimportant));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		dialogUnimportant.click();
	}
	
	public void clickOnDeleteButton() {
//		driverWait.until(ExpectedConditions.visibilityOf(deleteButton));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		deleteButton.click();
	}
	
	public void clickOnDialogCancelDelete() {
//		driverWait.until(ExpectedConditions.visibilityOf(dialogCancelDelete));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		dialogCancelDelete.click();
	}
	
	public void clickOnDialogDelete() {
//		driverWait.until(ExpectedConditions.visibilityOf(dialogDelete));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		dialogDelete.click();
	}
	
	public void switchToNewTab() {
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isPostOpened(String title) {
		switchToNewTab();
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), '"+title+"')]")));
		boolean isOpened = driver.findElements(By.xpath("//h1[contains(text(), '" + title + "')]")).size() > 0;
		return isOpened;
	}
	
	public boolean isPostInListDescriptionOrContentCheck(String text) {
		clickOnViewPost();
		switchToNewTab();
		boolean isInList = driver.findElements(By.xpath("//p[text()='"+text+"']")).size() > 0;
		return isInList;
	}
	
}
