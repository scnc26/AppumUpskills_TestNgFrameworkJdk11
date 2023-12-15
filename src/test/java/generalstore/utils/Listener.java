package generalstore.utils;

import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static generalstore.utils.ConfigReader.getProperty;
import static generalstore.utils.Driver.*;
import static generalstore.utils.ExtentReport.*;

public class Listener implements ITestListener {
    //ItestListener daki metodları kullanacagız


    @Override
    public void onStart(ITestContext context) {
        serverBaslat(getProperty("localIPAdres"), Integer.parseInt(getProperty("localPort")));
        raporOlustur();
        //reasauble metoda page factory ekleyip driver her test basında baslatabiliriz
        //reusable metoda cosructor ekleyip new reasaublemetod diye bu consructoru buradan cagırabiliriz
        bilgiNotu("test basladi");
    }

    @Override
    public void onTestStart(ITestResult result) {
        testOlustur(result.getMethod().getMethodName());
        //        testOlustur(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName());
// @Test(testName = "TC01|pozitif test") test name anatosyonunu yazdırır
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.log(Status.PASS, "Test Basarıyla tamalandı");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        //HATA MESAJİ
        test.fail("test basarisiz oldu cunku :" + result.getThrowable().getMessage());
        //ekran goruntusunu alır kaydeder

        File dosya = driver.getScreenshotAs(OutputType.FILE);
        String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu = System.getProperty("user.dir") + File.separator + "raporlar" + File.separator + result.getMethod().getMethodName() + ".png";
        //https://mvnrepository.com/artifact/commons-io/commons-io dependency ekle
        try {
            FileUtils.copyFile(dosya, new File(dosyaYolu));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        uygulamayiKapat();
        //ekran goruntusunu rapora ekleme
        try {
            test.addScreenCaptureFromPath(dosyaYolu);
        } catch (IOException e) {
            throw new RuntimeException("resim bulunamadi", e);
        }
        uygulamayiKapat();
    }

    @Override
    public void onFinish(ITestContext context) {
        uygulamayiKapat();
        raporuKaydet();
        serverKapat();
        raporuAc();
    }
}
