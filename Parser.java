import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {
    public String ip;
    public String UserAgent;
    public static String[] arr = new String[50];
    public static String[] arr2 = new String[50];
    public static int exps = 0;
    public static void parse_IP() throws IOException, InterruptedException {
        try {
            System.out.println("Парсинг IP начался");
            ChromeOptions co = new ChromeOptions();
            co.addArguments("--headless");
            WebDriver webDriver = new ChromeDriver(co);

            webDriver.navigate().to("https://hidemyna.me/ru/proxy-list/?maxtime=1000&type=h#list");
            Thread.sleep(13000);
            WebElement webElement1;
            String text_ip, text_port;
            for (int i = 1; i < 50; i++) {
                webElement1 = webDriver.findElement(By.xpath("//*[@id=\"content-section\"]/section[1]/div/table/tbody/tr[" + i + "]/td[1]"));
                text_ip = webElement1.getText();
//            Exception in thread "main" org.openqa.selenium.StaleElementReferenceException: stale element reference: element is not attached to the page document
                webElement1 = webDriver.findElement(By.xpath("//*[@id=\"content-section\"]/section[1]/div/table/tbody/tr[" + i + "]/td[2]"));
                text_port = webElement1.getText();
                arr2[i] = text_ip + ":" + text_port;
//            System.out.println(arr2[i]);
            }
            webDriver.close();
            webDriver.quit();
            exps = 0;
            System.out.println("Парсинг IP окончен");
        }
        catch (Exception e){
            exps++;
            if (exps >= 5){
                return;
            }
            System.out.println("Ошибка при парсинге IP");
            Parser.parse_IP();
        }
    }
    public static void parse_Agent() throws IOException, InterruptedException {
        try {
            System.out.println("Парсинг UAgenta начался");
            ChromeOptions co = new ChromeOptions();
            co.addArguments("--headless");
            WebDriver webDriver = new ChromeDriver(co);

            webDriver.navigate().to("https://hidemyna.me/ru/proxy-list/?maxtime=1000&type=h#list");
            Thread.sleep(13000);
            WebElement webElement1;
            webDriver.navigate().to("https://myip.ms/browse/comp_browseragents/Computer_Browser_Agents.html");
            Thread.sleep(5000);
            String usag;
            for (int i = 1; i < 50; i++) {
                webElement1 = webDriver.findElement(By.xpath("//*[@id=\"comp_browseragents_tbl\"]/tbody/tr[" + i + "]/td[2]/a"));
                arr[i] = webElement1.getText();

            }
            webDriver.close();
            webDriver.quit();
            exps = 0;
            System.out.println("Парсинг UAgenta окончен");
        }
        catch (Exception e){
            exps++;
            if (exps >= 5){
                return;
            }
            System.out.println("Ошибка при парсинге UAgenta");
            Parser.parse_Agent();
        }
    }

    public static String getUserAgent(int z){
        return arr[z];
    }
    public static String GetIP(int z){
        return arr2[z];
    }
}

//////*[@id="content-section"]/section[1]/div/table/tbody/tr[1]/td[1] - ip
// //*[@id="content-section"]/section[1]/div/table/tbody/tr[1]/td[2] - port
// //*[@id="content-section"]/section[1]/div/table/tbody/tr[2]/td[1] - next ip
//*[@id="comp_browseragents_tbl"]/tbody/tr[1]/td[2]/a
//*[@id="comp_browseragents_tbl"]/tbody/tr[2]/td[2]/a