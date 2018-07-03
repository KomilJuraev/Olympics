
package olympicmedals;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OlympicsMedals2016 {

	WebDriver driver;

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");
	}

	@AfterClass
	public void end() {
		driver.close();
	}

	@Test(priority = 1)
	public void test_Case_1() {
		List<WebElement> elements = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]"));
		ArrayList<Integer> ranks = new ArrayList<Integer>();
		for (int i = 0; i < elements.size() - 1; i++) {
			WebElement element = elements.get(i);
			String each = element.getText();
			ranks.add(Integer.parseInt(each));
		}
		// System.out.println("Ranks in ascending order " + ranks);
		ArrayList<Integer> sortedOne = new ArrayList<Integer>();
		sortedOne.addAll(ranks);
		Collections.sort(sortedOne);
		assertTrue(ranks.equals(sortedOne));

		// ===========================================================================
		// 3&4
		driver.findElement(
				By.xpath("//Table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/thead/tr/th[2]"))
				.click();
		elements = driver.findElements(
				By.xpath("//Table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"));
		// System.out.println(elements.size());
		ArrayList<String> countryNames = new ArrayList<String>();
		for (int i = 0; i < elements.size(); i++) {
			countryNames.add(elements.get(i).getText());
		}
		// System.out.println(countryNames);
		ArrayList<String> sortedByName = new ArrayList<String>();
		sortedByName.addAll(countryNames);
		Collections.sort(sortedByName);
		assertTrue(countryNames.equals(sortedByName));

		// ============================================================================
		// 5
		elements = driver.findElements(
				By.xpath("//Table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]"));
		ArrayList<Integer> notAscendingOrder = new ArrayList<Integer>();
		for (int i = 0; i < elements.size() - 1; i++) {
			WebElement element = elements.get(i);
			String each = element.getText();
			notAscendingOrder.add(Integer.parseInt(each));
		}
		// System.out.println("Ranks is not ascending Order " + notAscendingOrder);
		ArrayList<Integer> order = new ArrayList<Integer>(notAscendingOrder);
		Collections.sort(notAscendingOrder);
		assertFalse(notAscendingOrder.equals(order));

	}

	@Test(priority = 2)
	public void test_Case_2() throws InterruptedException {
		// 1
		driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics");
		// ===============================================================
		// 2
		String xpath = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/thead/tr/th[3]";
		isItUpOrDown(xpath);
		Thread.sleep(1500);
		String goldCountry = driver
				.findElement(By
						.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"))
				.getText();
		System.out.println("Country with most gold " + goldCountry);
		assertEquals(goldCountry, "United States");
		// ===============================================================
		// 3
		xpath = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/thead/tr/th[4]";
		isItUpOrDown(xpath);
		Thread.sleep(1500);
		String silverCountry = driver
				.findElement(By
						.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"))
				.getText();
		System.out.println("Country with most silver " + silverCountry);
		assertEquals(silverCountry, "United States");
		// ===============================================================
		// 4
		xpath = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/thead/tr/th[5]";
		isItUpOrDown(xpath);
		Thread.sleep(1500);
		String bronzeCountry = driver
				.findElement(By
						.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"))
				.getText();
		System.out.println("Country with most bronze " + bronzeCountry);
		assertEquals(bronzeCountry, "United States");
		// ==============================================================
		// 5
		xpath = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/thead/tr/th[6]";
		isItUpOrDown(xpath);
		Thread.sleep(1500);
		String mostMedals = driver
				.findElement(By
						.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"))
				.getText();
		System.out.println("Country with most medals " + mostMedals);
		assertEquals(mostMedals, "United States");
	}

	public void isItUpOrDown(String xpath) {
		String isItAscendingOrder = driver.findElement(By.xpath(xpath)).getAttribute("title");
		System.out.println("Ascending or not  " + isItAscendingOrder);
		while (!isItAscendingOrder.equals("Sort ascending")) {
			driver.findElement(By.xpath(xpath)).click();
		}
	}

	@Test(priority = 3)
	public void test_case_3() {
		// 1
		driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics");
		// ===============================================================
		// 2
		List<WebElement> elementsForSilver = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[3]"));
		List<WebElement> elementsForCountry = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"));
		System.out.println(elementsForSilver.size());

		ArrayList<Integer> count = new ArrayList<Integer>();
		ArrayList<String> countryList = new ArrayList<String>();

		for (int i = 0; i < elementsForSilver.size() - 1; i++) {
			String silvers = elementsForSilver.get(i).getText();
			count.add(Integer.parseInt(silvers));
			String countryName = elementsForCountry.get(i).getText();

			if (count.get(i) == 18) {
				countryList.add(countryName);
			}
		}

		System.out.println("Countries with 18 silver " + countryList);
		assertTrue(countryList.contains("China"));
		assertTrue(countryList.contains("France"));
	}

	@Test(priority = 4)
	public void test_case_4() {
		String str = japanTest("Japan");
		String[] arr = str.split(",");
		int col = Integer.valueOf(arr[0]);
		int row = Integer.valueOf(arr[1]);
		assertTrue(col == 6);
		assertTrue(row == 2);

	}

	public String japanTest(String countryName) {
		driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics");
		List<WebElement> countryNames = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr"));
		int count = 0;
		int col = 0;
		for (int i = 0; i < countryNames.size() - 1; i++) {
			count++;

			String str = countryNames.get(i).getText();

			if (str.contains(countryName)) {
				col = count;
			}
		}
		int row = 0;
		int countRow = 0;
		List<WebElement> column = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/thead/tr/th"));
		for (int i = 0; i < column.size(); i++) {
			String columnOrder = column.get(i).getText();
			countRow++;
			if (columnOrder.contains("NOC")) {
				row = countRow;
			}
		}
		return col + "," + row;

	}

	@Test(priority = 5)
	public void test_case_5() {
		String countries = sumOf2();
//		for(int i = 0; i < countries.length; i++) {
//		System.out.println("Bla Bla" + countries[i]);
//		}
		assertEquals(countries, "[Italy, Australia]");
	}

	public String sumOf2() {
		driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics");
		List<WebElement> bronze = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[4]"));
		List<WebElement> countryName = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"));

		ArrayList<Integer> bronzeList = new ArrayList<Integer>();
		ArrayList<String> countryList = new ArrayList<String>();

		for (int i = 0; i < bronze.size() - 1; i++) {
			String str = bronze.get(i).getText();
			bronzeList.add(Integer.parseInt(str));
			countryList.add(countryName.get(i).getText());
		}
		System.out.println("Bronze List " + bronzeList);
		System.out.println("Country List " + countryList);

		ArrayList<String> bronzeBoys = new ArrayList<String>();

		for (int i = 0, k = 0; i < bronzeList.size(); i++) {
			int num1 = bronzeList.get(i);
			for (int j = 0; j < bronzeList.size(); j++) {
				int num2 = bronzeList.get(j);
				if (i != j) {
					int result = num1 + num2;

					String country = countryList.get(i);
					if (result == 18) {
						bronzeBoys.add(country);
					}
				}
			}
		}
		System.out.println("asfdf" + bronzeBoys);
		return bronzeBoys.toString();
	}
}
