package com.omrbranch.baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClassTestNg extends BaseClass {

	public WebDriver driver;
	Select select;
	JavascriptExecutor executor;
	Alert alert;
	TakesScreenshot screenshot;
	Actions actions;


	public void browserLaunch(String browserName) {
		switch (browserName) {
		case "chrome":
			driver = new ChromeDriver();
			break;
			
		case "firefox":
			driver = new FirefoxDriver();
			break;
			
		case "internetExplorer":
			driver = new InternetExplorerDriver();
			break;
			
		case "edge":
			driver = new EdgeDriver();
			break;

		default:
			break;
		}
	}
	
	
	public void enterApplnUrl(String url) {
		driver.get(url);
	}

	public void maximizeWindow() {
		driver.manage().window().maximize();
	}

	public void elementSendKeys(WebElement element, String data) {
		visibilityOfElement(element);
		if (elementIsDisplayed(element) && elementIsEnabled(element)) {
			element.sendKeys(data);
		}
	}

	public void elementClick(WebElement element) {
		visibilityOfElement(element);
		if (elementIsDisplayed(element) && elementIsEnabled(element)) {
			element.click();
		}
	}

	public String getTitle() {
		String title = driver.getTitle();
		return title;
	}

	public WebElement findLocatorById(String attributeValue) {
		WebElement element = driver.findElement(By.id(attributeValue));
		return element;
	}

	public WebElement findLocatorByName(String attributeValue) {
		WebElement element = driver.findElement(By.name(attributeValue));
		return element;
	}

	public WebElement findLocatorByClassName(String attributeValue) {
		WebElement element = driver.findElement(By.className(attributeValue));
		return element;
	}

	public WebElement findLocatorByXpath(String exp) {
		WebElement element = driver.findElement(By.xpath(exp));
		return element;
	}

	public String getApplnUrl() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}

	public void closeWindow() {
		driver.quit();
	}

	public String elementGetText(WebElement element) {
		visibilityOfElement(element);
		String text = element.getText();
		return text;
	}

	//99%-->value is fixed
	public String getDomProperty(WebElement element) {
		visibilityOfElement(element);
		String domProperty = element.getDomProperty("value");
		return domProperty;
	}

	// 1%-->value is NOT fixed
	public String getDomProperty(WebElement element, String attributeName) {
		visibilityOfElement(element);
		String domProperty = element.getDomProperty(attributeName);
		return domProperty;
	}

	public void acceptAlert() {
		alert = driver.switchTo().alert();
		alert.accept();
	}
	
	public void cancelAlert() {
		alert = driver.switchTo().alert();
		alert.dismiss();
	}
	
	public void selectOptionByText(WebElement element, String text) {
		visibilityOfElement(element);
		select = new Select(element);
		select.selectByVisibleText(text);
	}
	
	public void selectOptionByValue(WebElement element, String text) {
		visibilityOfElement(element);
		select = new Select(element);
		select.selectByValue(text);
	}
	
	public void selectOptionByIndex(WebElement element, int index) {
		visibilityOfElement(element);
		select = new Select(element);
		select.selectByIndex(index);
	}
	
	public void elementSendKeysJs(WebElement element, String data) {
		executor = (JavascriptExecutor) driver;
		visibilityOfElement(element);
		executor.executeScript("arguments[0].setAttribute('value','" + data + "')", element);
	}
	
	public void elementClickjs(WebElement element) {
		executor = (JavascriptExecutor) driver;
		visibilityOfElement(element);
		executor.executeScript("arguments[0].click()",element);
	}
	
	public void switchToChildWindow() {
		String parentWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		
		for (String eachWindow : allWindows) {
			if (!parentWindow.equals(eachWindow)) {
				driver.switchTo().window(eachWindow);
			}
		}
	}
	
	public void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}
	
	public void switchToFrame(String nameOrId) {
		driver.switchTo().frame(nameOrId);
	}
	
	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
	}
	
	public List<String> elementGetAllOptionsFromDropdownAsText(WebElement element) {
		List<String> allOptionsText = new ArrayList<>();
		
		visibilityOfElement(element);
		select = new Select(element);
		List<WebElement> options = select.getOptions();
		
		for (WebElement webElement : options) {
			String text = webElement.getText();
			allOptionsText.add(text);
		}
		return allOptionsText;
	}
	
	public List<String> elementGetAllOptionsFromDropdownAsValue(WebElement element) {
		List<String> allOptionsValue = new ArrayList<>();
		
		visibilityOfElement(element);
		select = new Select(element);
		List<WebElement> options = select.getOptions();
		
		for (WebElement webElement : options) {
			String domProperty = webElement.getDomProperty("value");
			allOptionsValue.add(domProperty);
		} 
		return allOptionsValue;
	}
	
	public String elementGetFirstSelectedOptionTextFromDropdown(WebElement element) {
		visibilityOfElement(element);
		select = new Select(element);
		WebElement firstSelectedOption = select.getFirstSelectedOption();
		String firstSelectedOptionText = firstSelectedOption.getText();
		return firstSelectedOptionText;
	}
	
	public boolean elementDropdownMultiSelect(WebElement element) {
		visibilityOfElement(element);
		select = new Select(element);
		boolean multiple = select.isMultiple();
		return multiple;
	}
	
	public void implicitWait(int secs) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(secs));
	}
	
	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	}

	public void visibilityOfElement(WebElement element) {
		WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(60));
		driverWait.until(ExpectedConditions.visibilityOf(element));
	}
	
	//public void elementFluentwait(WebElement element) {
	//	Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofSeconds(5)).ignoring(Throwable.class);
	//	wait.until(ExpectedConditions.visibilityOf(element));
	//}
	
	public boolean elementIsEnabled(WebElement element) {
		boolean enabled = element.isEnabled();
		return enabled;
	}

	public boolean elementIsDisplayed(WebElement element) {
		boolean displayed = element.isDisplayed();
		return displayed;
	}
	
	public boolean elementisSelected(WebElement element) {
		boolean selected = element.isSelected();
		return selected;
	}
	
	public void deselectOptionByText(WebElement element, String text) {
		visibilityOfElement(element);
		select = new Select(element);
		select.deselectByVisibleText(text);
	}
	
	public void deselectOptionByValue(WebElement element, String text) {
		visibilityOfElement(element);
		select = new Select(element);
		select.deselectByValue(text);
	}
	
	public void deselectOptionByIndex(WebElement element, int index) {
		visibilityOfElement(element);
		select = new Select(element);
		select.deselectByIndex(index);
	}
	
	public void deselectAllSelectedOptions(WebElement element) {
		visibilityOfElement(element);
		select = new Select(element);
		select.deselectAll();
	}
	
	public void clearValuesInTextbox(WebElement element) {
		element.clear();
	}
	
	public void screenshot(String fileName) throws IOException {
		screenshot = (TakesScreenshot) driver;
		File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, new File(
				"D:\\Java Training\\Practice\\OMRBranchFrameworksTasks_NarendiraGanesh\\Output" + fileName + ".png"));
	}
	
	public void screenshot(WebElement element, String fileName) throws IOException {
		visibilityOfElement(element);
		File screenshotAs = element.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, new File(
				"D:\\Java Training\\Practice\\OMRBranchFrameworksTasks_NarendiraGanesh\\Output" + fileName + ".png"));
	}

	public void mouseHoverAction(WebElement element) {
		visibilityOfElement(element);
		actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}
	
	public void dragAndDrop(WebElement src, WebElement dest) {
		visibilityOfElement(src);
		visibilityOfElement(dest);
		actions = new Actions(driver);
		actions.dragAndDrop(src,dest).perform();
	}
	
	public void elementRightClick(WebElement element) {
		visibilityOfElement(element);
		actions = new Actions(driver);
		actions.contextClick(element).perform();
	}
	
	public void elementDoubleClick(WebElement element) {
		visibilityOfElement(element);
		actions = new Actions(driver);
		actions.doubleClick(element).perform();
	}
	
	public void elementInsertValueAndEnter(WebElement element, String data) {
		visibilityOfElement(element);
		if (elementIsDisplayed(element) && elementIsEnabled(element)) {
		element.sendKeys(data,Keys.ENTER);
		}
	}

	public void navigateTo(String url) {
		driver.navigate().to(url);
	}

	public void navigateForward() {
		driver.navigate().forward();
	}
	
	public void navigateBackward() {
		driver.navigate().back();
	}
	
	public void refreshWebpage() {
		driver.navigate().refresh();
	}

	public void scroll(WebElement element) {
		executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView()",element);
	}
	
	
	public void createCellAndSetData(String sheetName, int rownum, int cellnum, String data) throws IOException {
		File file = new File(
				"D:\\\\Java Training\\\\Practice\\\\OMRBranchFrameworksTasks_NarendiraGanesh\\\\Excel Sheet\\\\TestDataSignUp.xlsx");
		FileInputStream fileInputStream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(fileInputStream);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rownum);
		Cell cell = row.createCell(cellnum);
		cell.setCellValue(data);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		workbook.write(fileOutputStream);
		workbook.close();

	}

	public void updateCellData(String sheetName, int rownum, int cellnum, String oldData, String newData)
			throws IOException {
		File file = new File(
				"D:\\\\Java Training\\\\Practice\\\\OMRBranchFrameworksTasks_NarendiraGanesh\\\\Excel Sheet\\\\TestDataSignUp.xlsx");
		FileInputStream fileInputStream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(fileInputStream);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rownum);
		Cell cell = row.getCell(cellnum);
		String value = cell.getStringCellValue();
		if (value.equals(oldData)) {
			cell.setCellValue(newData);
		}
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		workbook.write(fileOutputStream);
		workbook.close();
	}

	public String getCellData(String sheetName, int rownum, int cellnum) throws IOException {
		String res = null;
		File file = new File(
				"D:\\Java Training\\Practice\\OMRBranchAPITasks_NarendiraGanesh\\Excel Sheet\\TestDataSignUp.xlsx");
		FileInputStream fileInputStream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(fileInputStream);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rownum);
		Cell cell = row.getCell(cellnum);
		CellType cellType = cell.getCellType();
		switch (cellType) {
		case STRING:
			res = cell.getStringCellValue();

			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				Date dateCellValue = cell.getDateCellValue();
				SimpleDateFormat dateForma = new SimpleDateFormat("dd-MMM-yy");
				res = dateForma.format(dateCellValue);
			} else {
				double numericCellValue = cell.getNumericCellValue();

				long round = Math.round(numericCellValue);
				if (round == numericCellValue) {
					res = String.valueOf(round);
				} else {
					res = String.valueOf(numericCellValue);
				}

			}

			break;

		default:
			break;
		}
		workbook.close();
		return res;
	}

}
