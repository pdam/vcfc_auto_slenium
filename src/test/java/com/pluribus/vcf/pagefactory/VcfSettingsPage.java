package com.pluribus.vcf.pagefactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VcfSettingsPage extends BasicInfra{
	
	@FindBy(how = How.XPATH, using = ".//a[contains(@href,'#/config/license')]")
	WebElement licenseTab;

	@FindBy(how = How.XPATH, using = ".//a[contains(@href,'#/config/elastic')]")
	WebElement dataNodeTab;

	@FindBy(how = How.CSS, using = "span.fa-stack-1x.pnc-text")
	WebElement pncCloudbutton;
	
	@FindBy(how = How.ID, using = "username")
	WebElement username;
	
	@FindBy(how = How.ID, using = "password")
	WebElement password;
	
	@FindBy(how = How.NAME, using = "ok")
	WebElement okButton;
	
	@FindBy(how = How.NAME, using = "cancel")
	WebElement cancelButton;
	
	@FindBy(how = How.CSS, using = "button.btn.btn-sm.btn-primary")
	WebElement addButton;
	
	@FindBy(how = How.ID, using = "name")
	WebElement nodeName;
	
	@FindBy(how = How.ID, using = "host")
	WebElement nodeHost;
	
	@FindBy(how = How.NAME, using = "sudo")
	WebElement sudo;
	
	@FindBy(how = How.ID, using = "heapsize")
	WebElement heapsize;
	
	@FindBy(how = How.ID, using = "mgmt-ip")
	WebElement mgmtIp;
	
	@FindBy(how = How.CSS, using = "a.list-group-item.category.config-menu")
	WebElement switchMenu;
	
	@FindBy(how = How.CSS, using = "a.list-group-item.category.health-menu")
	WebElement healthMenu;
	
	@FindBy(how = How.CSS, using = "a.list-group-item.category.server-menu")
	WebElement serverMenu;
	
	@FindBy(how = How.CSS, using = "a.list-group-item.category.apps-menu")
	WebElement appsMenu;
	
	@FindBy(how = How.CSS, using = "a.list-group-item.category.certs-menu")
	WebElement certsMenu;
	
	@FindBy(how = How.CSS, using = "a.list-group-item.category.admin-menu")
	WebElement adminMenu;
	
	@FindBy(how = How.XPATH, using = ".//a[contains(@href,'/vcf-center/#/config/switch')]")
	WebElement vcfSettingsIcon;
	
	@FindBy(how = How.CSS, using = "button.btn.btn-primary.btn-xs")
	WebElement AddAuthServer;
	
	@FindBy(how = How.CSS, using = "button.btn.btn-default.btn-sm")
	WebElement dropDown;
	
	@FindBy(how = How.NAME, using = "baseDn")
	WebElement baseDn;
	
	@FindBy(how = How.ID, using = "ldapManagerDn")
	WebElement ldapManagerDn;
	
	@FindBy(how = How.NAME, using = "ldapManagerPass")
	WebElement ldapManagerPass;
	
	@FindBy(how = How.NAME, using = "ldapUserDnPatterns")
	WebElement ldapUserDnPatterns;
	
	@FindBy(how = How.NAME, using = "ldapUserSearchFilter")
	WebElement ldapUserSearchFilter;
	
	@FindBy(how = How.CSS, using = "div.tr.ng-scope")
	WebElement switchList;
	
	@FindBy(how = How.CSS, using = "div.stats.ng-scope")
	WebElement switchStats;
	
	@FindBy(how = How.CSS, using = "div.row.ng-scope")
	WebElement softwareDetail;
	
	@FindBy(how = How.CSS, using = "button.btn.btn-primary.btn-xs")
	WebElement addAdmin;

	public VcfSettingsPage(WebDriver driver) {
		super(driver);
	}
	
	public void vcfSettingsPage() {
		vcfSettingsIcon.click();
	}

	public void addSeedSwitch(String name , String usrname, String mgmtip, String pwd) {
		vcfSettingsIcon.click();
		waitForElementVisibility(addButton,1000);
		addButton.click();
		setValue(nodeName,name);
		setValue(mgmtIp,mgmtip);
		setValue(username,usrname);
		setValue(password,pwd);
		okButton.click();
		waitForElementVisibility(switchList,1000);
		
	}
	
	public String verifySeedSwitch(String name , String usrname, String mgmtip, String pwd) {
		waitForElementVisibility(switchList,100);
		List<WebElement> rows = new ArrayList();
		rows = driver.findElements(By.cssSelector("div.tr.ng-scope"));
		String rowTable = null;
	       for (WebElement row : rows) {
	        	System.out.println("Row text"+row.getText());
	            if (row.getText().contains(name)) {
	                rowTable = row.getText();
	                break;
	            }
	        }
		
		return rowTable;
		
	}
	
	public void addDataNode(String name ,String host, String usrname, String size, String pwd) {
		vcfSettingsIcon.click();
		waitForElementVisibility(licenseTab,1000);
		dataNodeTab.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addButton.click();
		setValue(nodeName,name);
		setValue(nodeHost,host);	
		setValue(username,usrname);
		setValue(password,pwd);
		sudo.click();
		setValue(heapsize,size);
		okButton.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waitForElementVisibility(switchList,100);
		
	}
	
	public String verifyDataNode(String host) {	
		waitForElementVisibility(switchList,100);
		List<WebElement> rows = new ArrayList();
		rows = driver.findElements(By.cssSelector("div.tr.ng-scope"));
		String rowTable = null;
	       for (WebElement row : rows) {
	        	System.out.println("Row text"+row.getText());
	            if (row.getText().contains(host)) {
	                rowTable = row.getText();
	                break;
	            }
	        }
		
		return rowTable;
		
	}
	
	public void logintoPnc(String usrname, String pwd) {
		vcfSettingsIcon.click();
		waitForElementVisibility(licenseTab,1000);
		licenseTab.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pncCloudbutton.click();
		setValue(username,usrname);
		setValue(password,pwd);
		okButton.click();
		waitForElementVisibility(driver.findElement(By.tagName("ng-transclude")),1000);
	}
	
	public void activateLicense(String usrname, String pwd,LicenseTypes type) {
		logintoPnc(usrname , pwd);
	    List<WebElement> rows = new ArrayList();
	     rows = driver.findElement(By.tagName("ng-transclude")).findElements(By.tagName("div"));
	        String rowTable = null;
	        for (WebElement row : rows) {
	        	System.out.println("Row text"+row.getText());
	            if (row.getText().contains(type.toString())) {
	                rowTable = row.getText();
	                row.findElement(By.cssSelector("button.btn.btn-xs.btn-primary.ng-scope")).click();
	                break;
	            }
	        }
	}
	
	
	public void removeLicense(LicenseTypes type) {
		vcfSettingsIcon.click();
		waitForElementVisibility(licenseTab,1000);
		licenseTab.click();
		waitForElementVisibility(driver.findElement(By.tagName("ng-transclude")),1000);
	    List<WebElement> rows = new ArrayList();
	     rows = driver.findElement(By.tagName("ng-transclude")).findElements(By.tagName("div"));
	        String rowTable = null;
	        for (WebElement row : rows) {
	        	System.out.println("Row text"+row.getText());
	            if (row.getText().contains(type.toString())) {
	                rowTable = row.getText();
	                row.findElement(By.cssSelector("span.icon-img-link.fa fa-trash-o.ng-scope")).click();
	                break;
	            }
	        }
	}
	
	
	public void addAuthServer( String pwd, String basedn, String ldapmgmtdn, String ldapuserdnPatterns, String ldapuserSearchFilter, String ldapmanagerPass) {
		vcfSettingsIcon.click();
		waitForElementVisibility(switchMenu,1000);
		serverMenu.click();
		waitForElementVisibility(AddAuthServer,1000);
		AddAuthServer.click();
		setValue(baseDn,basedn);
		setValue(ldapManagerDn,ldapmgmtdn);
		setValue(ldapManagerPass,ldapmanagerPass);
		setValue(ldapUserDnPatterns,ldapuserdnPatterns);
		setValue(ldapUserSearchFilter,ldapuserSearchFilter);
	}
	
	public void navigateToSwitchMenu() {
		vcfSettingsIcon.click();
		waitForElementVisibility(switchMenu,1000);
		switchMenu.click();
		waitForElementVisibility(addButton,40);
	}
	
	public void navigateToSystemhealthMenu () {
		vcfSettingsIcon.click();
		waitForElementVisibility(switchMenu,1000);
		healthMenu.click();		
	}
	
	public void navigateToServerMenu() {
		vcfSettingsIcon.click();
		waitForElementVisibility(switchMenu,1000);
		serverMenu.click();
		waitForElementVisibility(AddAuthServer,40);
	}
	
	public String navigateToAppMenu() {
		vcfSettingsIcon.click();
		waitForElementVisibility(switchMenu,1000);
		appsMenu.click();
		waitForElementVisibility(softwareDetail,40);
		return softwareDetail.getText();
	}
	
	public String navigateTocertsMenu() {
		vcfSettingsIcon.click();
		waitForElementVisibility(switchMenu,1000);
		certsMenu.click();
		waitForElementVisibility(softwareDetail,40);
		return softwareDetail.getText();
	}
	
	public void navigateToadminMenu() {
		vcfSettingsIcon.click();
		waitForElementVisibility(switchMenu,1000);
		adminMenu.click();
		waitForElementVisibility(addAdmin,40);	
	}

}
