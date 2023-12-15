package generalstore.pages;

import generalstore.utils.Driver;
import generalstore.utils.ReusableMethods;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static generalstore.utils.Driver.driver;
import static generalstore.utils.ExtentReport.bilgiNotu;

public class FormSayfasi extends ReusableMethods {
    //consructor olustur-page factory elemanları saklama amaclıdır driver tanıtmak gerekiyor
    //new AppiumFieldDecorator ile daha fazla metoda ulasırız
    //obje olusturdugumuzda once consructor olusur sonra driver calısır
    //FindBy driver sız calismaz test run ile once obje sonra icindeki consructor icindeki driver calisir


    public FormSayfasi() {
        PageFactory.initElements(new AppiumFieldDecorator(Driver.getDriver()), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
    private WebElement ulkemenusu;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement isimAlani;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
    private WebElement cinsiyetMale;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
    private WebElement cinsiyetFemale;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement letsShopButonu;

    @AndroidFindBy(xpath = "//android.widget.Toast")
    private WebElement toastMessage;

    public void ulkeSec(String ulke) {
        ulkemenusu.click();
        driver.hideKeyboard();
        ulkemenusu.click();

        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + ulke + "\"))"));
        //  ReusableMethods.bekle(5);
        //  scrolToElementByText(driver,"Turkey");

        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"" + ulke + "\"]")).click();
    bilgiNotu("ülke menusunden "+ulke+" secildi");
    }

    @Test
    public void isimGir(String isim) {
        isimAlani.sendKeys(isim);
        bilgiNotu("isim kutusuna "+isim+" girildi");
    }

    @Test
    public void cinsiyet(String cinsiyet) {
        if (cinsiyet.equalsIgnoreCase("female")) {
            cinsiyetFemale.click();

        } else cinsiyetMale.click();
  bilgiNotu("cinsiyet seceneklerinen "+cinsiyet+" secildi");
    }



    public void letsShopClick() {
        letsShopButonu.click();
        bilgiNotu("letsShop butonuna tıklandı");
    }


    public void hataMesajininGorundugunuDogrula(String mesaj){
        Assert.assertEquals(toastMessage.getAttribute("name"), mesaj);
        bilgiNotu("hata mesajinin "+mesaj+" oldugu dogrulandi");

    }

@AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
public  WebElement sayfaBasligi;
    public void sayfaBasliginiDogrula(){
        Assert.assertEquals(sayfaBasligi.getText(), "General Store");
        bilgiNotu("sayfa basliginin general satore oldugu dogrulandi");
    }

}