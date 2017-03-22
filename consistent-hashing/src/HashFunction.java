import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by nirav on 10/25/2016.
 */
public class HashFunction {

    public BigInteger hash(Object toHash) {
        String hashString = toHash.toString();
        BigInteger hashValue = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestValue = md.digest(hashString.getBytes());
            hashValue = new BigInteger(1, digestValue);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hashValue;
    }
}
