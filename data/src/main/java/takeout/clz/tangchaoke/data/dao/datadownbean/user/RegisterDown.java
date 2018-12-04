package takeout.clz.tangchaoke.data.dao.datadownbean.user;

import takeout.clz.tangchaoke.data.dao.datadownbean.BaseModelDown;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/16
 */
public class RegisterDown extends BaseModelDown {
    private String carriage_id;
    private String c_head;
    private String token;

    public String getCarriage_id() {
        return carriage_id;
    }

    public void setCarriage_id(String carriage_id) {
        this.carriage_id = carriage_id;
    }

    public String getC_head() {
        return c_head;
    }

    public void setC_head(String c_head) {
        this.c_head = c_head;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
