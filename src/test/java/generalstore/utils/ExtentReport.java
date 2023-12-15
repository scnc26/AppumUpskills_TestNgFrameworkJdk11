package generalstore.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReport {

    public static ExtentReports extent;
    public static ExtentTest test;
//extentreports 5 versiyonundan sonraki hata veriyor
    public static void raporOlustur() {
        if (extent == null) {
            String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
            String path = System.getProperty("user.dir") + File.separator + "raporlar" + File.separator + "rapor.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(path);//onemli
            reporter.config().setReportName("GeneralStore Extent Raporu");
            reporter.config().setDocumentTitle("GeneralStore Smoke Test");
            extent = new ExtentReports();//önemli
            extent.attachReporter(reporter);
            extent.setSystemInfo("tester", "murat");

        }



    }


    public static void testOlustur(String testAdi) {

        test = extent.createTest(testAdi);//onemli

    }


    public static void bilgiNotu(String bilgiNotu) {
        if (test != null) {
            test.info(bilgiNotu);
        }

    }


    public static void raporuKaydet() {
        extent.flush();
    }


    public static void raporuAc(){
        // Rapor dosyasının tam yolu
        String raporYolu = System.getProperty("user.dir") + File.separator + "raporlar" + File.separator + "rapor.html";

        try {
            // Google Chrome'un çalıştırılması
            String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
            String command = chromePath + " " + raporYolu;
            Process process = Runtime.getRuntime().exec(command);

            // İşlemi bekleyin
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
