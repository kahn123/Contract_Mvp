package takeout.clz.tangchaoke.data.dao.dataupbean.user;

import java.io.File;

import takeout.clz.tangchaoke.data.dao.dataupbean.BaseModelUp;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/9/14
 */
public class PhotoUp extends BaseModelUp {

    String carriage_id;
    File c_head;
    String token;

    public PhotoUp(String carriage_id, File c_head, String token) {
        this.carriage_id = carriage_id;
        this.c_head = c_head;
        this.token = token;
    }

    public String getCarriage_id() {
        return carriage_id;
    }

    public void setCarriage_id(String carriage_id) {
        this.carriage_id = carriage_id;
    }

    public File getC_head() {
        return c_head;
    }

    public void setC_head(File c_head) {
        this.c_head = c_head;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
