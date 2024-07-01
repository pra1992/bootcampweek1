package bootcamp3pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FilesPage {
 
	private WebDriver driver;
	
	public FilesPage(WebDriver driver) {
		this.driver = driver;
	}
	
	WebElement ddNavigationMenu = driver.findElement(By.xpath("//*[@aria-label = 'Show Navigation Menu']"));
	
    WebElement tblHeaderLastModified = driver.findElement(By.xpath("//table[@data-aura-class='uiVirtualDataTable']//thead//th[@title='Last Modified Date']"));
    
    List<WebElement> dateLastModifiedDate = driver.findElements(By.xpath("//table[@data-aura-class='uiVirtualDataTable']//thead//th[@title='Last Modified Date']/../../following-sibling::tbody//*[@class='slds-truncate uiOutputDateTime']"));
    
    List<WebElement> colTitle = driver.findElements(By.xpath("//table[@data-aura-class='uiVirtualDataTable']//thead//th[@title='Title']/../../following-sibling::tbody//*[@class='itemTitle desktop outputTextOverride uiOutputText']"));
    
    WebElement linkPublic = driver.findElement(By.xpath("//*[text()='Public Link']"));
    
    WebElement toggleExpirationDate = driver.findElement(By.xpath("//*[text()='Expiration date']/..//*[@part='indicator']"));
    
    WebElement btnCreateLink = driver.findElement(By.xpath("//*[@title='Create Link']"));
    
    WebElement btnCreate = driver.findElement(By.xpath("//*[@title='Create']"));
    
    WebElement btnCopyLink = driver.findElement(By.xpath("//*[@title='Copy Link']"));
    
    WebElement txtReadOnlyPublicUrl = driver.findElement(By.xpath("//lightning-input[contains(@class,'recordViewUrl ')]"));
    
    WebElement btnClosePopup = driver.findElement(By.xpath("//*[@title='Close this window']"));
    
    WebElement btnDownload = driver.findElement(By.xpath("//*[text()='Download']"));
    
    WebElement btnShare = driver.findElement(By.xpath("//*[text()='Share']"));
    
    WebElement txtSearchPeople = driver.findElement(By.xpath("//*[@title='Search People']"));
    
    WebElement errorMessageCannotShare = driver.findElement(By.xpath("//*[@class='form-element__help']"));
    
    WebElement deleteShareWith = driver.findElement(By.xpath("//*[@class='deleteIcon']"));
    
    WebElement txtAddMessage = driver.findElement(By.xpath("//*[@placeholder='Add a message...']"));
    
    WebElement successMessageShare = driver.findElement(By.xpath("//*[@class='toastMsg uiOutputText forceActionsText']"));
    
    WebElement btnUpload = driver.findElement(By.xpath("//a[@title='Upload Files']"));
    
    WebElement messageSuccessUpload = driver.findElement(By.xpath("//*[@data-aura-class='forceToastMessage']"));
    
    WebElement messageFileUpload = driver.findElement(By.xpath("//*[@data-aura-class='forceContentPanelFooter']"));
    
    WebElement btnDone = driver.findElement(By.xpath("//*[text()='Done']"));
    
    List<WebElement> ddAction = driver.findElements(By.xpath("//table[@data-aura-class='uiVirtualDataTable']//thead//th[@title='Title']/../../following-sibling::tbody//a[@role='button']"));
    
    
    
    
    
    
    
    
}
