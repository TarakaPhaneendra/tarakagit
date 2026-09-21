package baseTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BaseTest {
	public static WebDriver driver;
	public Logger logger;
@Parameters({"browser"})
@BeforeTest	
public  void onTestStart(@Optional("edge") String browser) throws Exception {
	logger = LogManager.getLogger(this.getClass());
	logger.info("browser started");
	switch(browser) {
	
	
	case "edge":
		
		driver = new EdgeDriver();
		break;
	case "chrome":
	
	 driver  = new ChromeDriver();
	}
	driver.get("https://mvnrepository.com/");
	Properties prop = new Properties();
	FileReader file = new FileReader(".//src//test//java//resources//config.properties");
	prop.load(file);
	System.out.println(prop.getProperty("browser"));
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	logger.info("browser maximized");
	

	
}


public void tearDown() {
	//driver.close();
}
public String captureScreen(String tname) {
	String timestamp = new SimpleDateFormat("ddMMyyyyHHMmmss").format(new Date());
	TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
	
File sourcefile = takesScreenshot.getScreenshotAs(OutputType.FILE);
String targetpath = "./Screenshots/"+"/"+timestamp+".png";
File targetfile = new File(targetpath);
try {
	FileUtils.copyFile(sourcefile,targetfile);
} catch (Exception e) {
	e.printStackTrace();
}
return targetpath;
}
}
