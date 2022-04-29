package Scrapping1;

import org.openqa.selenium.By;

public class Locators 
{
   public static By userName = By.name("username");
   public static By password = By.name("password");
   public static By saveLoginInfo = By.xpath("//*[@role='main']/descendant::section/following-sibling::div");
   public static By turnOnNotifications = By.xpath("//*[@role='dialog']/descendant::button[2]");
   public static By searchBox = By.xpath("//*[@placeholder='Search']/following-sibling::div/div");
   public static By selectingPageFromList = By.xpath("//*[@placeholder='Search']/following-sibling::div[3]/descendant::a[1]");
   public static By followersCount  = By.xpath("//*[@id='react-root']/descendant::section[2]/ul/li[2]/descendant::div/span");
   public static By pageID = By.xpath("//*[@id='react-root']/descendant::section[2]/div[1]/h2");
   public static By followingCount  = By.xpath("//*[@id='react-root']/descendant::section[2]/ul/li[3]/descendant::div/span");
   public static By URL = By.xpath("//*[@id='react-root']/descendant::section[2]/div[2]/a/div");
}
