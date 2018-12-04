package takeout.clz.tangchaoke.data.dao.tool;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author leo, ZhangWei
 * @date 2018/6/19
 */
public class HttpTool {

    /**
     * 通过私有构造隐藏默认公共构造方法禁止该类的公共构造
     */
    private HttpTool() {
    }

    public static RequestBody stringToRequestBody(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }

    public static MultipartBody.Part fileToPart(File value) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), value);
        return MultipartBody.Part.createFormData("file", value.getName(), requestFile);
    }

    public static Map<String, Object> getMap() {
        return new HashMap<>();
    }
    public static Map<String, String> getMapString() {
        return new HashMap<>();
    }
    public static Map<String, RequestBody> getMapRequestBody() {
        return new HashMap<>();
    }
}
