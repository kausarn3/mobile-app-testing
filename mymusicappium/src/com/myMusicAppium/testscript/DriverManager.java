package com.myMusicAppium.testscript;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class DriverManager {

	static Properties prop;
	static ArrayList data;
	static DriverManager dm = new DriverManager();
	static AppiumDriver<MobileElement> driver;

	static {
		try {

			prop = new Properties();
			FileInputStream fs = new FileInputStream(
					"F:\\WorkSpaces\\selenium\\mymusicappium\\src\\com\\myMusicAppium\\objectproperties\\objectrepository.properties");
			prop.load(fs);

			data = new ArrayList();
			FileInputStream file = new FileInputStream("F:\\WorkSpaces\\selenium\\mymusicappium\\src\\com\\myMusicAppium\\testdata\\mymusic.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (cell.getCellTypeEnum() == CellType.STRING) {
						data.add(cell.getStringCellValue());
					}
					if (cell.getCellTypeEnum() == CellType.NUMERIC) {
						data.add(cell.getNumericCellValue());
					}
					if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
						data.add(cell.getBooleanCellValue());
					}

				}
			}

		} catch (Exception e) {

		}

	}

	public static void main(String[] args) {

		try {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("deviceName", "Redmi Note 5 pro");
			cap.setCapability("udid", "964b9090");
			cap.setCapability("platformName", "Android");
			cap.setCapability("platformVersion", "9");
			cap.setCapability("noReset", "true");
			cap.setCapability("fullReset", "false");

			cap.setCapability("appPackage", "com.miui.player");
			cap.setCapability("appActivity", "com.miui.player.ui.MusicBrowserActivity");

			URL url = new URL("http://127.0.0.1:4723/wd/hub");
			driver = new AppiumDriver<MobileElement>(url, cap);
			dm.main();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void main() {
		for (int i = 0; i < data.size(); i++) {

			if (data.get(i).equals("click")) {
				String keywordname = (String) data.get(i);
				String songname = (String) data.get(i + 1);
				String objectname = (String) data.get(i + 2);
				String runmode = (String) data.get(i + 3);
				System.out.println(keywordname + " " + songname + " " + objectname + " " + runmode);
				if (runmode.equals("yes")) {
					dm.click(objectname);

				}

			}
			if (data.get(i).equals("input")) {
				String keywordname = (String) data.get(i);
				String songname = (String) data.get(i + 1);
				String objectname = (String) data.get(i + 2);
				String runmode = (String) data.get(i + 3);
				System.out.println(keywordname + " " + songname + " " + objectname + " " + runmode);
				if (runmode.equals("yes")) {
					dm.input(objectname, songname);

				}

			}
			if (data.get(i).equals("close")) {
				String keywordname = (String) data.get(i);
				String songname = (String) data.get(i + 1);
				String objectname = (String) data.get(i + 2);
				String runmode = (String) data.get(i + 3);
				System.out.println(keywordname + " " + songname + " " + objectname + " " + runmode);
				if (runmode.equals("yes")) {
					dm.close();

				}

			}
			if (data.get(i).equals("waittime")) {
				String keywordname = (String) data.get(i);
				double songname = (double) data.get(i + 1);
				String objectname = (String) data.get(i + 2);
				String runmode = (String) data.get(i + 3);
				System.out.println(keywordname + " " + songname + " " + objectname + " " + runmode);
				if (runmode.equals("yes")) {
					dm.waittime(songname);

				}

			}

		}
	}

	public void click(String objectname) {

		driver.findElement(By.xpath(prop.getProperty(objectname))).click();

	}

	public void input(String objectname, String songname) {

		driver.findElement(By.xpath(prop.getProperty(objectname))).sendKeys(songname);

	}

	public void close() {

		driver.closeApp();

	}

	public void waittime(double songname) {
		try {
			Thread.sleep((long) songname);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}
}