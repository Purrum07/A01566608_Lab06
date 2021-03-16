package mx.tec.lab;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlParagraph;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
//import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class TestHtmlApplicationTests {
	private WebClient webClient;
	
	@BeforeEach
	public void init() throws Exception {
		webClient = new WebClient();
	}
	
	@AfterEach
	public void close() throws Exception {
		webClient.close();
	}
	
	@Test
	public void givenAClient_whenEnteringAutomationPractice_thenPageTitleIsCorrect() throws Exception {
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		HtmlPage page = webClient.getPage("http://automationpractice.com/index.php");
		
		assertEquals("My Store", page.getTitleText());
	}
	
	@Test
	public void givenAClient_whenEnteringLoginCredentials_thenAccountPageIsDisplayed()throws Exception {
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		HtmlPage page = webClient.getPage("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		HtmlTextInput emailField = (HtmlTextInput) page.getElementById("email");
		emailField.setValueAttribute("marioalex20007@hotmail.com");
		HtmlPasswordInput passwordField = (HtmlPasswordInput) page.getElementById("passwd");
		passwordField.setValueAttribute("loco2");
		HtmlButton submitButton = (HtmlButton) page.getElementById("SubmitLogin");
		HtmlPage resultPage = submitButton.click();
		
		assertEquals("My account - My Store", resultPage.getTitleText());
	}
	
	
	@Test
	public void givenAClient_whenEnteringWrongLoginCredentials_thenAuthenticationPageIsDisplayed()throws Exception {
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		HtmlPage page = webClient.getPage("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		HtmlTextInput emailField = (HtmlTextInput) page.getElementById("email");
		emailField.setValueAttribute("marioalex2007@hotmail.com");
		HtmlPasswordInput passwordField = (HtmlPasswordInput) page.getElementById("passwd");
		passwordField.setValueAttribute("loo2");
		HtmlButton submitButton = (HtmlButton) page.getElementById("SubmitLogin");
		HtmlPage resultPage = submitButton.click();
		
		assertEquals("Login - My Store", resultPage.getTitleText());
		
		//fail("Test not yet implemented!");
	}
	
	@Test
	public void givenAClient_whenEnteringWrongLoginCredentials_thenErrorMessageIsDisplayed()throws Exception {
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		HtmlPage page = webClient.getPage("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		HtmlTextInput emailField = (HtmlTextInput) page.getElementById("email");
		emailField.setValueAttribute("mario20007@hotmail.com");
		HtmlPasswordInput passwordField = (HtmlPasswordInput) page.getElementById("passwd");
		passwordField.setValueAttribute("loco2");
		HtmlButton submitButton = (HtmlButton) page.getElementById("SubmitLogin");
		HtmlPage resultPage = submitButton.click();
		
		HtmlListItem errorItem = resultPage.getFirstByXPath("//div[@class='alert alert-danger']/ol/li");
		
		assertEquals("Authentication failed.", errorItem.getVisibleText());
		//fail("Test not yet implemented!");
	}
	
	@Test
	public void givenAClient_whenSearchingNotExistingProduct_thenNoResultsDisplayed()throws Exception {
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		HtmlPage page = webClient.getPage("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		HtmlTextInput emailField = (HtmlTextInput) page.getElementById("email");
		emailField.setValueAttribute("marioalex20007@hotmail.com");
		HtmlPasswordInput passwordField = (HtmlPasswordInput) page.getElementById("passwd");
		passwordField.setValueAttribute("loco2");
		HtmlButton submitButton = (HtmlButton) page.getElementById("SubmitLogin");
		HtmlPage loggedInPage = submitButton.click();
		
		HtmlTextInput searchField = (HtmlTextInput) loggedInPage.getElementById("search_query_top");
		searchField.setValueAttribute("camisa");
		HtmlButton searchButton = (HtmlButton) loggedInPage.getElementByName("submit_search");
		HtmlPage resultPage = searchButton.click();
		
		HtmlParagraph paragraphText = resultPage.getFirstByXPath("//p[@class='alert alert-warning']");
		
		assertEquals("No results were found for your search \"camisa\"", paragraphText.getVisibleText());
		//fail("Test not yet implemented!");
	}
	
	@Test
	public void givenAClient_whenSearchingEmptyString_thenPleaseEnterDisplayed()throws Exception {
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		HtmlPage page = webClient.getPage("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		HtmlTextInput emailField = (HtmlTextInput) page.getElementById("email");
		emailField.setValueAttribute("marioalex20007@hotmail.com");
		HtmlPasswordInput passwordField = (HtmlPasswordInput) page.getElementById("passwd");
		passwordField.setValueAttribute("loco2");
		HtmlButton submitButton = (HtmlButton) page.getElementById("SubmitLogin");
		HtmlPage loggedInPage = submitButton.click();
		
		HtmlTextInput searchField = (HtmlTextInput) loggedInPage.getElementById("search_query_top");
		searchField.setValueAttribute("");
		HtmlButton searchButton = (HtmlButton) loggedInPage.getElementByName("submit_search");
		HtmlPage resultPage = searchButton.click();
		
		HtmlParagraph paragraphText = resultPage.getFirstByXPath("//p[@class='alert alert-warning']");
		
		assertEquals("Please enter a search keyword", paragraphText.getVisibleText());
		
		//fail("Test not yet implemented!");
	}
	
	
	@Test
	public void givenAClient_whenSigningOut_thenAuthenticationPageIsDisplayed()throws Exception {
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		HtmlPage page = webClient.getPage("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		HtmlTextInput emailField = (HtmlTextInput) page.getElementById("email");
		emailField.setValueAttribute("marioalex20007@hotmail.com");
		HtmlPasswordInput passwordField = (HtmlPasswordInput) page.getElementById("passwd");
		passwordField.setValueAttribute("loco2");
		HtmlButton submitButton = (HtmlButton) page.getElementById("SubmitLogin");
		HtmlPage loggedInPage = submitButton.click();
		
		HtmlAnchor signOut = (HtmlAnchor) loggedInPage.getFirstByXPath("//a[@class='logout']");
		HtmlPage resultPage = signOut.click();
		
		assertEquals("Login - My Store", resultPage.getTitleText());
		
		//fail("Test not yet implemented!");
	}
	
}

