package lol.gilliard;

import com.amdelamar.jotp.OTP;
import com.amdelamar.jotp.type.Type;

public class Demos {





    // This code uses Austin Delamar's JOTP: https://github.com/amdelamar/jotp

    private static class HOTP {
        public static void main(String... a) throws Exception {

            //  Use this to generate the secret when the user first signs up
            //    String superSecretSecret = OTP.randomBase32(20);

            // Assuming a pre-existing user, we have fetched their secret from our DB
            String superSecretSecret = "4NHEK6KWH5MVZEXR6M34BCHIC6IQBTOE";

            // increment this to generate a new code, ie after every successful login
            String counter = "0";

            String hotpCode = OTP.create(superSecretSecret, counter, 6, Type.HOTP);

            System.out.println("HOTP code: " + hotpCode);

        }
    }










    private static class TOTP {
        public static void main(String... a) throws Exception {

            //  note as above ^^
            //        String superSecretSecret = OTP.randomBase32(20);
            var superSecretSecret = "4NHEK6KWH5MVZEXR6M34BCHIC6IQBTOE";

            var totpCode = OTP.create(superSecretSecret, OTP.timeInHex(), 6, Type.TOTP);

            // output changes every 30s
            System.out.println("TOTP code: " + totpCode);

        }
    }







    private static class TOTPShareSecret {
        public static void main(String[] args) {

            // as before
            var superSecretSecret = "4NHEK6KWH5MVZEXR6M34BCHIC6IQBTOE";

            String url = OTP.getURL(
                superSecretSecret, 6,
                Type.TOTP, "2fa2furious",
                "2fa2furious@example.com");

            System.out.println(url);

        }
    }
}
