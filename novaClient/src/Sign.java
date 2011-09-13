import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
/**
 * 
 * @author ezzze
 *
 */
public class Sign {

	/**
	 * log
	 */

	public static final String SIGN_ALGORITHM = "HmacSHA256";

	public static final String SIGN_VERSION = "2";

	public static void sign(ThorRequest request) throws Exception {

		request.setParam("AWSAccessKeyId", request.getCredentials()
				.getAWSAccessKeyId() + ":" + request.getProject());

		String stringToSign = ThorRequestUtils.calculateStringToSign(request);
		System.out.println("--------------------------------");
		System.out.println("stringToSign :" + stringToSign);
		String signature = sign(stringToSign, request.getCredentials()
				.getAWSSecretKey());
		System.out.println("--------------------------------");

		System.out.println("signature " + signature);
		request.setParam("Signature", signature);
	}

	/**
	 * Computes an RFC 2104-compliant HMAC signature.
	 */
	public static String sign(String data, String key) throws Exception {
		try {
			return sign(data.getBytes(ThorRequestUtils.DEFAULT_ENCODING), key,
					SIGN_ALGORITHM);
		} catch (Exception e) {
			throw new Exception("Unable to calculate a request signature: "
					+ e.getMessage(), e);
		}
	}

	/**
	 * Computes an RFC 2104-compliant HMAC signature for an array of bytes.
	 */
	public static String sign(byte[] data, String key, String algorithm)
			throws Exception {
		try {
			Mac mac = Mac.getInstance(algorithm.toString());
			mac.init(new SecretKeySpec(key.getBytes(), algorithm));
			byte[] signature = Base64.encodeBase64(mac.doFinal(data));
			return new String(signature);
		} catch (Exception e) {
			throw new Exception("Unable to calculate a request signature: "
					+ e.getMessage(), e);
		}
	}
}
