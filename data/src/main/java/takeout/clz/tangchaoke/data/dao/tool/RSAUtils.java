package takeout.clz.tangchaoke.data.dao.tool;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * Created by company on 2018/1/4.
 */

public class RSAUtils {
    /**
     * 将Base64编码后的公钥字符串转成PublicKey实例
     */
    public static PublicKey getPublicKey(String publicKey) {
        try {
            byte[] keyBytes = Base64Utils.decode(publicKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA", new BouncyCastleProvider());
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
//            LogUtils.e("获取公钥方法异常：" + e.getMessage());
        }
        return null;
    }


    /**
     * 公钥加密
     */
    public static byte[] encrypt(byte[] content, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");//java 默认"RSA"="RSA/ECB/PKCS1Padding"
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }
}
