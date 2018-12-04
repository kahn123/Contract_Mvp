package takeout.clz.tangchaoke.data.dao.tool;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/9/7
 */

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;
public class StringResponseBodyConverter implements Converter<ResponseBody, String> {
    @Override
    public String convert(ResponseBody value) throws IOException {
        try {
            return value.string();
        } finally {
            value.close();
        }
    }
}
