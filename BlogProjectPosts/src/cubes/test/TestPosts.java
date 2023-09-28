package cubes.test;

import static org.junit.Assert.*;
import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import cubes.LoginPage;
import cubes.webpages.PostsFormPage;
import cubes.webpages.PostsListPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestPosts {

	
	private static FirefoxDriver driver;
	private static LoginPage loginPage;
	private static PostsListPage postListPage;
	private static PostsFormPage postsFormPage;
	
	private final String TITLE_VALID_1 = "Title need 20 characters";
	private final String TITLE_VALID_2 = "New title need 20 characters";
	private final String TITLE_INVALID = "Title";
	private final String DESCRIPTION_VALID_1 = "Description need at least 50 characters to be saved";
	private final String DESCRIPTION_VALID_2 = "New description need at least 50 characters to be saved";
	private final String DESCRIPTION_INVALID = "Description";
	private final String CONTENT_1 = "Content 1";
	private final String CONTENT_2 = "Content 2";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.gecko.driver", "D:\\Selenium\\Webdriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(7));
		
		loginPage = new LoginPage(driver);
		postListPage = new PostsListPage(driver, driverWait);
		postsFormPage = new PostsFormPage(driver, driverWait);
		
		loginPage.loginSuccess();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.close();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void tc04TestMenuLinks() {
		postListPage.openPage();
		checkMenuLinks("Sliders list", "https://testblog.kurs-qa.cubes.edu.rs/admin/sliders");
		checkMenuLinks("Add Slider", "https://testblog.kurs-qa.cubes.edu.rs/admin/sliders/add");
		checkMenuLinks("Post Categories list", "https://testblog.kurs-qa.cubes.edu.rs/admin/post-categories");
		checkMenuLinks("Add Post Category", "https://testblog.kurs-qa.cubes.edu.rs/admin/post-categories/add");
		checkMenuLinks("Tags list", "https://testblog.kurs-qa.cubes.edu.rs/admin/tags");
		checkMenuLinks("Add Tag", "https://testblog.kurs-qa.cubes.edu.rs/admin/tags/add");
		checkMenuLinks("Posts list", "https://testblog.kurs-qa.cubes.edu.rs/admin/posts");
		checkMenuLinks("Add Post", "https://testblog.kurs-qa.cubes.edu.rs/admin/posts/add");
		checkMenuLinks("Comments List", "https://testblog.kurs-qa.cubes.edu.rs/admin/comments");
		checkMenuLinks("Users List", "https://testblog.kurs-qa.cubes.edu.rs/admin/users");
		checkMenuLinks("Add User", "https://testblog.kurs-qa.cubes.edu.rs/admin/users/add");
	}

	@Test
	public void tc06TestNavigationLinks() {
		postListPage.openPage();
		checkNavigationLinks("Home", "https://testblog.kurs-qa.cubes.edu.rs/admin");
		postsFormPage.openPage();
		checkNavigationLinks("Home", "https://testblog.kurs-qa.cubes.edu.rs/admin");
		postsFormPage.openPage();
		checkNavigationLinks("Post", "https://testblog.kurs-qa.cubes.edu.rs/admin/posts");
	}
	
	@Test
	public void tc07TestEverythingEmptyClickOnSave() {
		postsFormPage.openPage();
		
		postsFormPage.inputStringInTitle("");
		postsFormPage.inputStringInDescription("");
		postsFormPage.inputStringInContent("");
		
		postsFormPage.clickSave();
		
		assertEquals(postsFormPage.getTitleError(), "This field is required.");
		assertEquals(postsFormPage.getDescriptionError(), "This field is required.");
		assertEquals(postsFormPage.getTagError(), "This field is required.");
		assertEquals(postsFormPage.getContentError(), "The content field is required.");
	}
	
	@Test
	public void tc08TestEverythingEmptyTitleValid() {
		postsFormPage.openPage();
		
		postsFormPage.inputStringInTitle(TITLE_VALID_1);
		postsFormPage.inputStringInDescription("");
		postsFormPage.inputStringInContent("");
		
		postsFormPage.clickSave();
		
		assertEquals(postsFormPage.getDescriptionError(), "This field is required.");
		assertEquals(postsFormPage.getTagError(), "This field is required.");
		assertEquals(postsFormPage.getContentError(), "The content field is required.");
	}
	
	@Test
	public void tc09TestEverythingEmptyDescriptionValid() {
		postsFormPage.openPage();
		
		postsFormPage.inputStringInTitle("");
		postsFormPage.inputStringInDescription(DESCRIPTION_VALID_1);
		postsFormPage.inputStringInContent("");
		
		postsFormPage.clickSave();
		
		assertEquals(postsFormPage.getTitleError(), "This field is required.");
		assertEquals(postsFormPage.getTagError(), "This field is required.");
		assertEquals(postsFormPage.getContentError(), "The content field is required.");
	}
	
	@Test
	public void tc10TestEverythingEmptyTagsChecked() {
		postsFormPage.openPage();
		
		postsFormPage.clickOnTag("Default TAG NE BRISATI");
		postsFormPage.inputStringInTitle("");
		postsFormPage.inputStringInDescription("");
		postsFormPage.inputStringInContent("");
		
		postsFormPage.clickSave();
		
		assertEquals(postsFormPage.getTitleError(), "This field is required.");
		assertEquals(postsFormPage.getDescriptionError(), "This field is required.");
		assertEquals(postsFormPage.getContentError(), "The content field is required.");
	}
	
	@Test
	public void tc11TestEverythingEmptyContentEnter() {
		postsFormPage.openPage();
		
		postsFormPage.inputStringInTitle("");
		postsFormPage.inputStringInDescription("");
		postsFormPage.inputStringInContent(CONTENT_1);
		
		postsFormPage.clickSave();
		
		assertEquals(postsFormPage.getTitleError(), "This field is required.");
		assertEquals(postsFormPage.getDescriptionError(), "This field is required.");
		assertEquals(postsFormPage.getTagError(), "This field is required.");
	}
	
	@Test
	public void tc12TestEverythingEmptyTitleInvalid() {
		postsFormPage.openPage();
		
		postsFormPage.inputStringInTitle(TITLE_INVALID);
		postsFormPage.inputStringInDescription("");
		postsFormPage.inputStringInContent("");
		
		postsFormPage.clickSave();
		
		assertEquals(postsFormPage.getTitleError(), "Please enter at least 20 characters.");
		assertEquals(postsFormPage.getDescriptionError(), "This field is required.");
		assertEquals(postsFormPage.getTagError(), "This field is required.");
		assertEquals(postsFormPage.getContentError(), "The content field is required.");
	}
	
	@Test
	public void tc13TestEverythingEmptyDescriptionInvalid() {
		postsFormPage.openPage();
		
		postsFormPage.inputStringInTitle("");
		postsFormPage.inputStringInDescription(DESCRIPTION_INVALID);
		postsFormPage.inputStringInContent("");
		
		postsFormPage.clickSave();
		
		assertEquals(postsFormPage.getTitleError(), "This field is required.");
		assertEquals(postsFormPage.getDescriptionError(), "Please enter at least 50 characters.");
		assertEquals(postsFormPage.getTagError(), "This field is required.");
		assertEquals(postsFormPage.getContentError(), "The content field is required.");
	}
	
	@Test
	public void tc14TestEverythingEmptyTitleInvalidDescriptionInvalid() {
		postsFormPage.openPage();
		
		postsFormPage.inputStringInTitle(TITLE_INVALID);
		postsFormPage.inputStringInDescription(DESCRIPTION_INVALID);
		postsFormPage.inputStringInContent("");
		
		postsFormPage.clickSave();
		
		assertEquals(postsFormPage.getTitleError(), "Please enter at least 20 characters.");
		assertEquals(postsFormPage.getDescriptionError(), "Please enter at least 50 characters.");
		assertEquals(postsFormPage.getTagError(), "This field is required.");
		assertEquals(postsFormPage.getContentError(), "The content field is required.");
	}
	
	@Test
	public void tc15TestEverythingEmptyTitleValidDescriptionValid() {
		postsFormPage.openPage();
		
		postsFormPage.inputStringInTitle(TITLE_VALID_1);
		postsFormPage.inputStringInDescription(DESCRIPTION_VALID_1);
		postsFormPage.inputStringInContent("");
		
		postsFormPage.clickSave();
		
		assertEquals(postsFormPage.getTagError(), "This field is required.");
		assertEquals(postsFormPage.getContentError(), "The content field is required.");
	}
	
	@Test
	public void tc16TestTitleValidDescriptionValidTagsCheckedContentEnter() {
		postsFormPage.openPage();
		
		postsFormPage.clickOnTag("Default TAG NE BRISATI");
		postsFormPage.inputStringInTitle(TITLE_VALID_1);
		postsFormPage.inputStringInDescription(DESCRIPTION_VALID_1);
		postsFormPage.inputStringInContent("");
		
		postsFormPage.clickSave();
		
		assertEquals(postsFormPage.getContentError(), "The content field is required.");
	}
	
	@Test
	public void tc17TestEverythingEmptyTitleValidTagsChecked() {
		postsFormPage.openPage();
		
		postsFormPage.clickOnTag("Default TAG NE BRISATI");
		postsFormPage.inputStringInTitle(TITLE_VALID_1);
		postsFormPage.inputStringInDescription("");
		postsFormPage.inputStringInContent("");
		
		postsFormPage.clickSave();
		
		assertEquals(postsFormPage.getDescriptionError(), "This field is required.");
		assertEquals(postsFormPage.getContentError(), "The content field is required.");
	}
	
	@Test
	public void tc18TestEverythingEmptyDescriptionValidTagsChecked() {
		postsFormPage.openPage();
		
		postsFormPage.clickOnTag("Default TAG NE BRISATI");
		postsFormPage.inputStringInTitle("");
		postsFormPage.inputStringInDescription(DESCRIPTION_VALID_1);
		postsFormPage.inputStringInContent("");
		
		postsFormPage.clickSave();
		
		assertEquals(postsFormPage.getTitleError(), "This field is required.");
		assertEquals(postsFormPage.getContentError(), "The content field is required.");
	}
	
	@Test
	public void tc19TestEverythingEmptyTitleValidContentEnter() {
		postsFormPage.openPage();
		
		postsFormPage.inputStringInTitle(TITLE_VALID_1);
		postsFormPage.inputStringInDescription("");
		postsFormPage.inputStringInContent(CONTENT_1);
		
		postsFormPage.clickSave();
		
		assertEquals(postsFormPage.getDescriptionError(), "This field is required.");
		assertEquals(postsFormPage.getTagError(), "This field is required.");
	}
	
	@Test
	public void tc20TestEverythingEmptyDescriptionValidContentEnter() {
		postsFormPage.openPage();
		
		postsFormPage.inputStringInTitle("");
		postsFormPage.inputStringInDescription(DESCRIPTION_VALID_1);
		postsFormPage.inputStringInContent(CONTENT_1);
		
		postsFormPage.clickSave();
		
		assertEquals(postsFormPage.getTitleError(), "This field is required.");
		assertEquals(postsFormPage.getTagError(), "This field is required.");
	}
	
	@Test
	public void tc21TestEverythingEmptyTagsCheckedContentEnter() {
		postsFormPage.openPage();
		
		postsFormPage.clickOnTag("Default TAG NE BRISATI");
		postsFormPage.inputStringInTitle("");
		postsFormPage.inputStringInDescription("");
		postsFormPage.inputStringInContent(CONTENT_1);
		
		postsFormPage.clickSave();
		
		assertEquals(postsFormPage.getTitleError(), "This field is required.");
		assertEquals(postsFormPage.getDescriptionError(), "This field is required.");
	}
	
	@Test
	public void tc22TestEverythingEmptyChoseCategory() {
		postsFormPage.openPage();
		
		postsFormPage.chooseCategory(2);
		postsFormPage.inputStringInTitle("");
		postsFormPage.inputStringInDescription("");
		postsFormPage.inputStringInContent("");
		
		postsFormPage.clickSave();
		
		assertEquals(postsFormPage.getTitleError(), "This field is required.");
		assertEquals(postsFormPage.getDescriptionError(), "This field is required.");
		assertEquals(postsFormPage.getTagError(), "This field is required.");
		assertEquals(postsFormPage.getContentError(), "The content field is required.");
	}
	
	@Test
	public void tc23TestEverythingEmptyClickOnCancel() {
		postsFormPage.openPage();
		
		postsFormPage.inputStringInTitle("");
		postsFormPage.inputStringInDescription("");
		postsFormPage.inputStringInContent("");
		
		postsFormPage.clickCancel();
		
		assertEquals(driver.getCurrentUrl(), "https://testblog.kurs-qa.cubes.edu.rs/admin/posts");
	}
	
	@Test
	public void tc24TestEverythingValidClickOnCancel() {
		postsFormPage.openPage();
		
		postsFormPage.clickOnTag("Default TAG NE BRISATI");
		postsFormPage.inputStringInTitle(TITLE_VALID_1);
		postsFormPage.inputStringInDescription(DESCRIPTION_VALID_1);
		postsFormPage.inputStringInContent(CONTENT_1);
		
		postsFormPage.clickCancel();
		
		assertEquals(driver.getCurrentUrl(), "https://testblog.kurs-qa.cubes.edu.rs/admin/posts");
	}
	
	@Test
	public void tc25TestAddPostEverythingValid() {
		postsFormPage.openPage();
		
		postsFormPage.chooseCategory(2);
		postsFormPage.clickOnTag("Default TAG NE BRISATI");
		postsFormPage.inputStringInTitle(TITLE_VALID_1);
		postsFormPage.clickImportantNo();
		postsFormPage.inputStringInDescription(DESCRIPTION_VALID_1);
		postsFormPage.inputStringInContent(CONTENT_1);
		
		postsFormPage.clickSave();
		
		assertEquals(postListPage.getToastMessage(), "New post has been added");
	}
	
	@Test
	public void tc26TestPostListAddNewPostLink() {
		postListPage.openPage();
		
		postListPage.addNewPostLink();
		
		assertEquals(driver.getCurrentUrl(), "https://testblog.kurs-qa.cubes.edu.rs/admin/posts/add");
	}
	
	@Test
	public void tc27TestSearchByTitle() {
		postListPage.openPage();
		
		postListPage.searchByTitle(TITLE_VALID_1);
		
		assertEquals(postListPage.isPostInList(TITLE_VALID_1), true);
	}
	
	@Test
	public void tc28TestSearchByTitlePartial() {
		postListPage.openPage();
		
		postListPage.searchByTitle("Title");
		
		boolean partialTitle = postListPage.getTitle().contains("Title");
		
		assertEquals(partialTitle, true);
	}
	
	@Test
	public void tc29TestSearchByAuthor() {
		postListPage.openPage();
		
		postListPage.searchByAuthor("Polaznik Kursa");
		
		assertEquals(postListPage.isPostInList("Polaznik Kursa"), true);
	}
	
	@Test
	public void tc30TestSearchByCategory() {
		postListPage.openPage();
		
		postListPage.searchByCategory("Default Post Category NE BRISATI");
		
		assertEquals(postListPage.isPostInListCategory("Default Post Category NE BRISATI"), true);
	}
	
	@Test
	public void tc31TestSearchByImportant() {
		postListPage.openPage();
		
		postListPage.searchByImportant("no");
		
		assertEquals(postListPage.isPostInListImportant("No"), true);
	}
	
	@Test
	public void tc32TestSearchByStatus() {
		postListPage.openPage();
		
		postListPage.searchByStatus("enabled");
		
		assertEquals(postListPage.isPostInListStatus("enabled"), true);
	}
	
	@Test
	public void tc33TestSearchByWithTag() {
		postListPage.openPage();
		
		postListPage.searchByWithTag("Default TAG NE BRISATI");
		
		assertEquals(postListPage.isPostInList("Default TAG NE BRISATI"), true);
	}
	
	@Test
	public void tc34TestOpenPost() {
		postListPage.openPage();
		
		postListPage.searchByTitle(TITLE_VALID_1);
		
		String winHandleBefore = driver.getWindowHandle();
		
		postListPage.clickOnViewPost();
		
//		postListPage.switchToNewTab();
//		boolean partialurl = driver.getCurrentUrl().contains("title-need-20-characters");
//		assertEquals(partialurl, true);
		
		assertEquals(postListPage.isPostOpened(TITLE_VALID_1), true);
		
		driver.close();
		driver.switchTo().window(winHandleBefore);
	}
	
	@Test
	public void tc35TestDisabelEnablePost() throws InterruptedException {
		postListPage.openPage();
		postListPage.searchByTitle(TITLE_VALID_1);
		
		postListPage.clickOnDisableButton();
		postListPage.clickOnDialogCancelDisable();
		assertEquals(postListPage.isPostInListStatus("enabled"), true);
		
		postListPage.clickOnDisableButton();
		postListPage.clickOnDialogDisable();
		Thread.sleep(350);
		assertEquals(postListPage.isPostInListStatus("disabled"), true);
		
		postListPage.clickOnEnableButton();
		postListPage.clickOnDialogCancelEnable();
		Thread.sleep(350);
		assertEquals(postListPage.isPostInListStatus("disabled"), true);
		
		postListPage.clickOnEnableButton();
		postListPage.clickOnDialogEnable();
		Thread.sleep(350);
		assertEquals(postListPage.isPostInListStatus("enabled"), true);
	}
	
	@Test
	public void tc36TestImportantUnimportantPost() throws InterruptedException {
		postListPage.openPage();
		postListPage.searchByTitle(TITLE_VALID_1);
		
		postListPage.clickOnImportantButton();
		postListPage.clickOnDialogCancelImportant();
		assertEquals(postListPage.isPostInListImportant("No"), true);
		
		postListPage.clickOnImportantButton();
		postListPage.clickOnDialogImportant();
		Thread.sleep(350);
		assertEquals(postListPage.isPostInListImportant("Yes"), true);
		
		postListPage.clickOnUnimportantButton();
		postListPage.clickOnDialogCancelUnimportant();
		Thread.sleep(350);
		assertEquals(postListPage.isPostInListImportant("Yes"), true);
		
		postListPage.clickOnUnimportantButton();
		postListPage.clickOnDialogUnimportant();
		Thread.sleep(350);
		assertEquals(postListPage.isPostInListImportant("No"), true);
	}
	
	@Test
	public void tc37TestSearchByTitleBar() {
		postListPage.openPage();
		
		postListPage.searchByTitleBar(TITLE_VALID_1);
		
		assertEquals(postListPage.isPostInList(TITLE_VALID_1), true);
	}
	
	@Test
	public void tc38TestEditPostCancel() {
		postListPage.openPage();
		
		postListPage.searchByTitle(TITLE_VALID_1);
		postListPage.clickOnEditButton();
		
		postsFormPage.clickCancel();
		
		postListPage.searchByTitle(TITLE_VALID_1);
		
		assertEquals(driver.getCurrentUrl(), "https://testblog.kurs-qa.cubes.edu.rs/admin/posts");
		assertEquals(postListPage.isPostInList(TITLE_VALID_1), true);
		assertEquals(postListPage.isPostInListCategory("Default Post Category NE BRISATI"), true);
		assertEquals(postListPage.isPostInList("Default TAG NE BRISATI"), true);
		assertEquals(postListPage.isPostInList("Polaznik Kursa"), true);
		assertEquals(postListPage.isPostInListImportant("No"), true);
		assertEquals(postListPage.isPostInListStatus("enabled"), true);
		checkDescriptionOrContent(DESCRIPTION_VALID_1);
		checkDescriptionOrContent(CONTENT_1);
	}
	
	@Test
	public void tc39TestEditPostSave() {
		postListPage.openPage();
		
		postListPage.searchByTitle(TITLE_VALID_1);
		postListPage.clickOnEditButton();
		
		postsFormPage.clickSave();
		
		postListPage.searchByTitle(TITLE_VALID_1);
		
		assertEquals(postListPage.isPostInList(TITLE_VALID_1), true);
		assertEquals(postListPage.isPostInListCategory("Default Post Category NE BRISATI"), true);
		assertEquals(postListPage.isPostInList("Default TAG NE BRISATI"), true);
		assertEquals(postListPage.isPostInList("Polaznik Kursa"), true);
		assertEquals(postListPage.isPostInListImportant("No"), true);
		assertEquals(postListPage.isPostInListStatus("enabled"), true);
		checkDescriptionOrContent(DESCRIPTION_VALID_1);
		checkDescriptionOrContent(CONTENT_1);
	}
	
	@Test
	public void tc40TestEditPostChangeTitleCancel() {
		postListPage.openPage();

		postListPage.searchByTitle(TITLE_VALID_1);
		postListPage.clickOnEditButton();
		
		postsFormPage.inputStringInTitle(TITLE_VALID_2);
		postsFormPage.clickCancel();
		
		postListPage.searchByTitleBar(TITLE_VALID_1);
		
		assertEquals(driver.getCurrentUrl(), "https://testblog.kurs-qa.cubes.edu.rs/admin/posts");
		assertEquals(postListPage.isPostInList(TITLE_VALID_1), true);
	}
	
	@Test
	public void tc41TestEditPostChangeTitleSave() {
		postListPage.openPage();

		postListPage.searchByTitle(TITLE_VALID_1);
		postListPage.clickOnEditButton();
		
		postsFormPage.inputStringInTitle(TITLE_VALID_2);
		postsFormPage.clickSave();
		
		postListPage.searchByTitleBar(TITLE_VALID_2);
		
		assertEquals(driver.getCurrentUrl(), "https://testblog.kurs-qa.cubes.edu.rs/admin/posts");
		assertEquals(postListPage.getToastMessage(), "Post has been edited");
		assertEquals(postListPage.isPostInList(TITLE_VALID_2), true);
	}
	
	@Test
	public void tc42TestEditPostChangeDescriptionCancel() {
		postListPage.openPage();

		postListPage.searchByTitle(TITLE_VALID_2);
		postListPage.clickOnEditButton();
		
		postsFormPage.inputStringInDescription(DESCRIPTION_VALID_2);
		postsFormPage.clickCancel();
		
		postListPage.searchByTitle(TITLE_VALID_2);
		
		assertEquals(driver.getCurrentUrl(), "https://testblog.kurs-qa.cubes.edu.rs/admin/posts");
		
		checkDescriptionOrContent(DESCRIPTION_VALID_1);
	}
	
	@Test
	public void tc43TestEditPostChangeDescriptionSave() {
		postListPage.openPage();

		postListPage.searchByTitle(TITLE_VALID_2);
		postListPage.clickOnEditButton();
		
		postsFormPage.inputStringInDescription(DESCRIPTION_VALID_2);
		postsFormPage.clickSave();
		
		postListPage.searchByTitle(TITLE_VALID_2);
		
		assertEquals(driver.getCurrentUrl(), "https://testblog.kurs-qa.cubes.edu.rs/admin/posts");
		assertEquals(postListPage.getToastMessage(), "Post has been edited");
		
		checkDescriptionOrContent(DESCRIPTION_VALID_2);
	}
	
	@Test
	public void tc44TestEditPostChangeTagsCancel() {
		postListPage.openPage();

		postListPage.searchByTitle(TITLE_VALID_2);
		postListPage.clickOnEditButton();
		
		postsFormPage.clickOnTag("Default TAG NE BRISATI");
		postsFormPage.clickOnTag("Default TAG2 NE BRISATI");
		postsFormPage.clickCancel();
		
		postListPage.searchByTitle(TITLE_VALID_2);
		
		assertEquals(driver.getCurrentUrl(), "https://testblog.kurs-qa.cubes.edu.rs/admin/posts");
		assertEquals(postListPage.isPostInList("Default TAG NE BRISATI"), true);
	}
	
	@Test
	public void tc45TestEditPostChangeTagsSave() {
		postListPage.openPage();

		postListPage.searchByTitle(TITLE_VALID_2);
		postListPage.clickOnEditButton();
		
		postsFormPage.clickOnTag("Default TAG NE BRISATI");
		postsFormPage.clickOnTag("Default TAG2 NE BRISATI");
		postsFormPage.clickSave();
		
		postListPage.searchByTitle(TITLE_VALID_2);
		
		assertEquals(driver.getCurrentUrl(), "https://testblog.kurs-qa.cubes.edu.rs/admin/posts");
		assertEquals(postListPage.getToastMessage(), "Post has been edited");
		assertEquals(postListPage.isPostInList("Default TAG2 NE BRISATI"), true);
	}
	
	@Test
	public void tc46TestEditPostChangeContentCancel() {
		postListPage.openPage();

		postListPage.searchByTitle(TITLE_VALID_2);
		postListPage.clickOnEditButton();
		
		
		postsFormPage.inputStringInContent(CONTENT_2);
		postsFormPage.clickCancel();
		
		postListPage.searchByTitle(TITLE_VALID_2);
		
		assertEquals(driver.getCurrentUrl(), "https://testblog.kurs-qa.cubes.edu.rs/admin/posts");
		checkDescriptionOrContent(CONTENT_1);
	}
	
	@Test
	public void tc47TestEditPostChangeContentSave() {
		postListPage.openPage();

		postListPage.searchByTitle(TITLE_VALID_2);
		postListPage.clickOnEditButton();
		
		
		postsFormPage.inputStringInContent(CONTENT_2);
		postsFormPage.clickSave();
		
		postListPage.searchByTitle(TITLE_VALID_2);
		
		assertEquals(driver.getCurrentUrl(), "https://testblog.kurs-qa.cubes.edu.rs/admin/posts");
		assertEquals(postListPage.getToastMessage(), "Post has been edited");
		checkDescriptionOrContent(CONTENT_2);
	}
	
	@Test
	public void tc48TestEditPostDeletePhoto() {
		postListPage.openPage();

		postListPage.searchByTitle(TITLE_VALID_2);
		postListPage.clickOnEditButton();
		
		postsFormPage.deletePhoto();
		
		assertNotEquals("Error while deleteing photo", postListPage.getToastMessage());
	}
	
	@Test
	public void tc49TestEditPostInvalidTitleSave() {
		postListPage.openPage();

		postListPage.searchByTitle(TITLE_VALID_2);
		postListPage.clickOnEditButton();
		
		postsFormPage.inputStringInTitle(TITLE_INVALID);
		postsFormPage.clickSave();
		

		assertEquals(postsFormPage.getTitleError(), "Please enter at least 20 characters.");
	}
	
	@Test
	public void tc50TestEditPostInvalidDescriptionSave() {
		postListPage.openPage();

		postListPage.searchByTitle(TITLE_VALID_2);
		postListPage.clickOnEditButton();
		
		postsFormPage.inputStringInDescription(DESCRIPTION_INVALID);
		postsFormPage.clickSave();
		

		assertEquals(postsFormPage.getDescriptionError(), "Please enter at least 50 characters.");
	}
	
	@Test
	public void tc51TestEditPostInvalidTitleInvalidDescriptionSave() {
		postListPage.openPage();

		postListPage.searchByTitle(TITLE_VALID_2);
		postListPage.clickOnEditButton();
		
		postsFormPage.inputStringInTitle(TITLE_INVALID);
		postsFormPage.inputStringInDescription(DESCRIPTION_INVALID);
		postsFormPage.clickSave();
		

		assertEquals(postsFormPage.getTitleError(), "Please enter at least 20 characters.");
		assertEquals(postsFormPage.getDescriptionError(), "Please enter at least 50 characters.");
	}
	
	@Test
	public void tc52TestEditPostChangeTitleDescriptionSave() {
		postListPage.openPage();

		postListPage.searchByTitle(TITLE_VALID_2);
		postListPage.clickOnEditButton();
		
		postsFormPage.inputStringInTitle(TITLE_VALID_1);
		postsFormPage.inputStringInDescription(DESCRIPTION_VALID_1);
		postsFormPage.clickSave();
		
		postListPage.searchByTitle(TITLE_VALID_1);
		
		assertEquals(driver.getCurrentUrl(), "https://testblog.kurs-qa.cubes.edu.rs/admin/posts");
		assertEquals(postListPage.getToastMessage(), "Post has been edited");
		assertEquals(postListPage.isPostInList(TITLE_VALID_1), true);
		checkDescriptionOrContent(DESCRIPTION_VALID_1);
	}
	
	@Test
	public void tc53TestEditPostChangeTagsContentSave() {
		postListPage.openPage();
		
		postListPage.searchByTitle(TITLE_VALID_1);
		postListPage.clickOnEditButton();
		
		postsFormPage.clickOnTag("Default TAG2 NE BRISATI");
		postsFormPage.clickOnTag("Default TAG NE BRISATI");
		postsFormPage.inputStringInContent(CONTENT_1);
		postsFormPage.clickSave();
		
		postListPage.searchByTitle(TITLE_VALID_1);
		
		assertEquals(driver.getCurrentUrl(), "https://testblog.kurs-qa.cubes.edu.rs/admin/posts");
		assertEquals(postListPage.getToastMessage(), "Post has been edited");
		assertEquals(postListPage.isPostInList("Default TAG NE BRISATI"), true);
		checkDescriptionOrContent(CONTENT_1);
	}
	
	@Test
	public void tc54TestEditPostUncheckTagsSave() {
		postListPage.openPage();
		
		postListPage.searchByTitle(TITLE_VALID_1);
		postListPage.clickOnEditButton();
		
		postsFormPage.clickOnTag("Default TAG NE BRISATI");
		postsFormPage.clickSave();
		
		assertEquals(postsFormPage.getTagError(), "This field is required.");
	}
	
	@Test
	public void tc55TestEditPostContentEmptySave() {
		postListPage.openPage();
		
		postListPage.searchByTitle(TITLE_VALID_1);
		postListPage.clickOnEditButton();
		
		postsFormPage.inputStringInContent("");
		postsFormPage.clickSave();
		
		assertEquals(postsFormPage.getContentError(), "The content field is required.");
	}
	
	@Test
	public void tc56TestEditPostTitleDescriptionEmptySave() {
		postListPage.openPage();
		
		postListPage.searchByTitle(TITLE_VALID_1);
		postListPage.clickOnEditButton();
		
		postsFormPage.inputStringInTitle("");
		postsFormPage.inputStringInDescription("");
		postsFormPage.clickSave();
		
		assertEquals(postsFormPage.getTitleError(), "This field is required.");
		assertEquals(postsFormPage.getDescriptionError(), "This field is required.");
	}
	
	@Test
	public void tc57TestEditPostTitleDescriptionTagsEmptySave() {
		postListPage.openPage();
		
		postListPage.searchByTitle(TITLE_VALID_1);
		postListPage.clickOnEditButton();
		
		postsFormPage.inputStringInTitle("");
		postsFormPage.inputStringInDescription("");
		postsFormPage.clickOnTag("Default TAG NE BRISATI");
		postsFormPage.clickSave();
		
		assertEquals(postsFormPage.getTitleError(), "This field is required.");
		assertEquals(postsFormPage.getDescriptionError(), "This field is required.");
		assertEquals(postsFormPage.getTagError(), "This field is required.");
	}
	
	@Test
	public void tc58TestEditPostTitleDescriptionTagsContentEmptySave() {
		postListPage.openPage();
		
		postListPage.searchByTitle(TITLE_VALID_1);
		postListPage.clickOnEditButton();
		
		postsFormPage.inputStringInTitle("");
		postsFormPage.inputStringInDescription("");
		postsFormPage.clickOnTag("Default TAG NE BRISATI");
		postsFormPage.inputStringInContent("");
		postsFormPage.clickSave();
		
		assertEquals(postsFormPage.getTitleError(), "This field is required.");
		assertEquals(postsFormPage.getDescriptionError(), "This field is required.");
		assertEquals(postsFormPage.getTagError(), "This field is required.");
		assertEquals(postsFormPage.getContentError(), "The content field is required.");
	}
	
	@Test
	public void tc59TestEditPostChangeImportantSave() {
		postListPage.openPage();
		
		postListPage.searchByTitle(TITLE_VALID_1);
		postListPage.clickOnEditButton();
		
		postsFormPage.clickImportantYes();
		postsFormPage.clickSave();
		
		postListPage.searchByTitle(TITLE_VALID_1);
		
		assertEquals(driver.getCurrentUrl(), "https://testblog.kurs-qa.cubes.edu.rs/admin/posts");
		assertEquals(postListPage.getToastMessage(), "Post has been edited");
		assertEquals(postListPage.isPostInListImportant("Yes"), true);
	}
	
	@Test
	public void tc60TestEditPostChangeCategorySave() {
		postListPage.openPage();
		
		postListPage.searchByTitle(TITLE_VALID_1);
		postListPage.clickOnEditButton();
		
		postsFormPage.chooseCategory(3);
		postsFormPage.clickSave();
		
		postListPage.searchByTitle(TITLE_VALID_1);
		
		assertEquals(driver.getCurrentUrl(), "https://testblog.kurs-qa.cubes.edu.rs/admin/posts");
		assertEquals(postListPage.getToastMessage(), "Post has been edited");
		assertEquals(postListPage.isPostInListCategory("Default Post Category2 NE BRISATI"), true);
	}
	
	@Test
	public void tc61TestEditPostChangeTitleDescriptionContentTagsSave() {
		postListPage.openPage();
		
		postListPage.searchByTitle(TITLE_VALID_1);
		postListPage.clickOnEditButton();
		
		postsFormPage.clickOnTag("Default TAG2 NE BRISATI");
		postsFormPage.inputStringInTitle(TITLE_VALID_2);
		postsFormPage.inputStringInDescription(DESCRIPTION_VALID_2);
		postsFormPage.inputStringInContent(CONTENT_2);
		postsFormPage.clickSave();
		
		postListPage.searchByTitle(TITLE_VALID_2);
		
		assertEquals(driver.getCurrentUrl(), "https://testblog.kurs-qa.cubes.edu.rs/admin/posts");
		assertEquals(postListPage.getToastMessage(), "Post has been edited");
		assertEquals(postListPage.isPostInList(TITLE_VALID_2), true);
		checkDescriptionOrContent(DESCRIPTION_VALID_2);
		assertEquals(postListPage.isPostInList("Default TAG NE BRISATI, Default TAG2 NE BRISATI"), true);
		checkDescriptionOrContent(CONTENT_2);
	}
	
	@Test
	public void tc62TestDeletePostCancel() {
		postListPage.openPage();
		
		postListPage.searchByTitle(TITLE_VALID_2);
		
		postListPage.clickOnDeleteButton();
		postListPage.clickOnDialogCancelDelete();
		
		assertEquals(postListPage.isPostInList(TITLE_VALID_2), true);
	}
	@Test
	public void tc63TestDeletePost() {
		postListPage.openPage();
		
		postListPage.searchByTitle(TITLE_VALID_2);
		
		postListPage.clickOnDeleteButton();
		postListPage.clickOnDialogDelete();
		
		assertEquals(postListPage.getToastMessage(), "Post has been deleted");
	}
	
	
	
	
	public void checkMenuLinks(String title, String url) {
		postListPage.openParentLinkInMenu(title);
		postListPage.clickOnLinkInMenu(title);
		
		assertEquals(driver.getCurrentUrl(), url);
	}
	
	public void checkNavigationLinks(String title, String url) {
		postListPage.clickOnNavigationLink(title);
		
		assertEquals(url, driver.getCurrentUrl());
	}
	
	public void checkDescriptionOrContent(String text) {
		String winHandleBefore = driver.getWindowHandle();
		
		assertEquals(postListPage.isPostInListDescriptionOrContentCheck(text), true);
		
		driver.close();
		driver.switchTo().window(winHandleBefore);
	}
}
