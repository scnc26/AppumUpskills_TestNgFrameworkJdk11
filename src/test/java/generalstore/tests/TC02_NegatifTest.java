package generalstore.tests;

import generalstore.pages.FormSayfasi;
import org.testng.annotations.Test;

import static generalstore.utils.Driver.uygulamayiKapat;

public class TC02_NegatifTest {
     /*
    Uygulama: GeneralStore
        GeneralStore uygulamasına gir
        Menüden Angola seçeneğini seç
        Your Name kutusunu boş bırak
        Let’s Shop butonuna tıkla
        Hata mesajını doğrula (Toast Message)
     */

    @Test
    public void tc02NegativeTest() {
        FormSayfasi formSayfasi = new FormSayfasi();
        formSayfasi.ulkeSec("Angola");
        formSayfasi.letsShopClick();
        formSayfasi.hataMesajininGorundugunuDogrula("Please enter your name");
        uygulamayiKapat();

    }
}
