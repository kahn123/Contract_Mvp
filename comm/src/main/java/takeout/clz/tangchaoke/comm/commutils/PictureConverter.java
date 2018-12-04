package takeout.clz.tangchaoke.comm.commutils;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/17
 */
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PictureConverter {
    public static void convertToJpg(String pngFilePath, String jpgFilePath) {
        Bitmap bitmap = BitmapFactory.decodeFile(pngFilePath);
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(jpgFilePath))) {
            if (bitmap.compress(Bitmap.CompressFormat.PNG, 70, bos)) {
                bos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}