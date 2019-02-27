import java.io.IOException;
import java.sql.Time;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Main {
    static int i = 1;
    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner sc = new Scanner(System.in);
        System.setProperty(
                "webdriver.chrome.driver",
                "d:\\java_prj\\MonneyMachine\\src\\main\\java\\chromedriver.exe");


//        System.out.println("Введи ссылку");
//        String addr = sc.nextLine();
//        System.out.println("Введи xpath к кнопке, не забудь экранацию");
//        String xpath1 = sc.nextLine();
        Parser.parse_IP();
        Parser.parse_Agent();
        WebDriver webDriver;


        while(true) {
            webDriver = StartWebDriver();


//            String originalHandle = webDriver.getWindowHandle();
//            for(String handle : webDriver.getWindowHandles()) {
//                if (!handle.equals(originalHandle)) {
//                    webDriver.switchTo().window(handle);
//                    webDriver.close();
//                }
//            }
//            webDriver.switchTo().window(originalHandle);

            try{
                webDriver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
                webDriver.navigate().to("http://linkshrink.net/7CJKeW");
//                "http://linkshrink.net/7CJKeW"

                System.out.println("Зашли");
                Thread.sleep(10000);

                try {
                    WebElement webElement = webDriver.findElement(By.xpath("//*[@id=\"btd\"]"));
////                    "//*[@id=\"btd\"]"
                    webElement.click();
                    System.out.println("Есть контакт");
                }
                catch(Exception e)
                {
                    System.out.println("Тут либ прокси мертв, либ капча");
                }
                System.out.println("Вышли");
                Thread.sleep(2000);
                webDriver.close();
                webDriver.quit();
                Thread.sleep(2000);
            }
            catch (Exception e){
                System.out.println("Грузит долго, давай сначала");
                webDriver.close();
                webDriver.quit();
            }
        }



    }
    public static WebDriver StartWebDriver () throws IOException, InterruptedException {
        WebDriver webDriver;
        String proxy1 = Parser.GetIP(i);
        System.out.println(Parser.GetIP(i));
        ChromeOptions oo = new ChromeOptions().addArguments("--proxy-server=" + proxy1);
//        oo.setCapability("proxy", proxy);
//        System.out.println(proxy);


        oo.addArguments("--headless");
        System.out.println(Parser.getUserAgent(i));
        oo.addArguments("--user-agent=" + Parser.getUserAgent(i));
//        System.out.println(Parser.getUserAgent(i));
        oo.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        i++;
        if(i == 49){
            i = 1;
            Parser.parse_IP();
            Parser.parse_Agent();
        }
        webDriver = new ChromeDriver(oo);

        return webDriver;
    }
}