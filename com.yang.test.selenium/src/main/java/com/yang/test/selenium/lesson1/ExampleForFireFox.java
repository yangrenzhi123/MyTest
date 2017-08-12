package com.yang.test.selenium.lesson1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExampleForFireFox {
	public static void main(String[] args) {
		// ������ FireFox û�а�װ��Ĭ��Ŀ¼����ô�����ڳ���������
		System.setProperty("webdriver.firefox.bin", "C://Program Files (x86)//Mozilla Firefox//firefox.exe");
		// ����һ�� FireFox �������ʵ��
		WebDriver driver = new FirefoxDriver();

		// ����������� Baidu
		driver.get("http://mail.163.com");
		// ���������Ҳ����ʵ��
		// driver.navigate().to("http://www.baidu.com");

		// ��ȡ ��ҳ�� title
		System.out.println("1 Page title is: " + driver.getTitle());

		// ͨ�� id �ҵ� input �� DOM
		WebElement element = driver.findElement(By.id("kw"));

		// ����ؼ���
		element.sendKeys("zTree");

		// �ύ input ���ڵ� form
		element.submit();

		// ͨ���ж� title ���ݵȴ�����ҳ�������ϣ����10��
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase().endsWith("ztree");
			}
		});

		// ��ʾ�������ҳ��� title
		System.out.println("2 Page title is: " + driver.getTitle());

		// �ر������
		driver.quit();
	}
}