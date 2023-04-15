import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.ru.*;

public class TriangleStep {
	WebDriver driver;

	@Дано("^открыта страница с калькулятором треугольника$")
	public void i_launch() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to("https://www.calculator.net/triangle-calculator.html");
	}

	@Когда("^сбросим все параметры$")
	public void Clear_Defoult_Values() {
		driver.findElement(By.name("vc")).click();
		driver.findElement(By.name("vc")).clear();
		driver.findElement(By.name("vx")).click();
		driver.findElement(By.name("vx")).clear();
		driver.findElement(By.name("vy")).click();
		driver.findElement(By.name("vy")).clear();
	}

	@И("^введем значения \"2, 2, 3\" для сторон \"A, B, C\" и нажмем кнопку \"Calculate\"$")
	public void Set_Sides() {
		driver.findElement(By.name("vx")).sendKeys("2");
		driver.findElement(By.name("vy")).sendKeys("2");
		driver.findElement(By.name("vz")).sendKeys("3");
		driver.findElement(By.cssSelector("tr:nth-child(5) input")).click();
	}

	@Тогда("^результат равен \"Obtuse Isosceles Triangle\"$")
	public void i_verify() throws Exception {
		assertEquals(driver.findElement(By.cssSelector("td > h3")).getText(), ("Obtuse Isosceles Triangle"));
		driver.quit();
	}

}