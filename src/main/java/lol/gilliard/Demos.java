package lol.gilliard;

import com.amdelamar.jotp.OTP;
import com.amdelamar.jotp.type.Type;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

// This code uses Austin Delamar's JOTP: https://github.com/amdelamar/jotp

public class Demos {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        hotp();
        totp();
    }

    private static void hotp() throws NoSuchAlgorithmException, InvalidKeyException {


        //  Use this to generate the secret when the user first signs up
        //    String superSecretSecret = OTP.randomBase32(20);


        // Assuming a pre-existing user, we have fetched their secret from our DB
        String superSecretSecret = "4NHEK6KWH5MVZEXR6M34BCHIC6IQBTOE";

        // increment this to generate a new code, ie after every successful login
        String counter = "0";

        String hotpCode = OTP.create(superSecretSecret, counter, 6, Type.HOTP);

        System.out.println("HOTP code: " + hotpCode);

    }


    public static void totp() throws IOException, NoSuchAlgorithmException, InvalidKeyException {

        //  note as above ^^
        //        String superSecretSecret = OTP.randomBase32(20);
        var superSecretSecret = "4NHEK6KWH5MVZEXR6M34BCHIC6IQBTOE";

        var totpCode = OTP.create(superSecretSecret, OTP.timeInHex(), 6, Type.TOTP);

        // output changes every 30s
        System.out.println("TOTP code: " + totpCode);

        // Share the superSecretSecret with the client by generating a QR code from this URL
        // Using a free online QR code generator, you can scan with your Authentication app
        // (Google Authenticator or similar). I had to add the `&label=` for some apps.
        String url = OTP.getURL(superSecretSecret, 6, Type.TOTP, "2fa2furious", "matthew.gilliard@gmail.com") + "&label=2FA2Furious";
        System.out.println(url);
    }
}
