package generalstore.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.time.Duration;

public class Driver {

    //java-client/ testng/extentreports4,1,7 /io appium dependency yukle
    public static AndroidDriver driver;
    public static AppiumDriverLocalService server;
    public static AndroidDriver getDriver() {

        //her page acildiğinda consructor ından dolayı driver tekrar calisır no suchelement verir bunu engellemek icin
        //if kullanarak driver null ise yeni driver olustur sartını koyarız
        if (driver == null) {
            String appUrl = System.getProperty("user.dir")
                    + File.separator + "src"
                    + File.separator + "test"
                    + File.separator + "resources"
                    + File.separator + ConfigReader.getProperty("apkName");

            UiAutomator2Options options = new UiAutomator2Options()
                    .setUiautomator2ServerInstallTimeout(Duration.ofSeconds(60))
                    .setApp(appUrl);

//            URL url = null;
//            try {
//                url = new URL("http://0.0.0.0:4723");
//            } catch (MalformedURLException e) {
//                throw new RuntimeException(e);
//            }
           // server kurulunca url yi serverdan aldık
 //driver = new AndroidDriver(url, options);
            driver = new AndroidDriver(server.getUrl(), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }
        return driver;
    }


    public static void serverBaslat(String ipAdres,int port) {
        //where appium npm ye kadar yolu kopyalayıp klasor yoluna yapistirdik sonraki yollalar ile main js ye ulastık
        server=new AppiumServiceBuilder()
                .withAppiumJS(new File("C:\\Users\\aao\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress(ipAdres)
                .usingPort(port)
                .build();
        //server.start();
        //serverı otomatik calistirmak icin server.start() calistir
    }


    public static void serverKapat() {
        //server.stop();
        //serverı otomatik kapatmak icin icin server.stop() calistir
    }

    public static void uygulamayiKapat() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}