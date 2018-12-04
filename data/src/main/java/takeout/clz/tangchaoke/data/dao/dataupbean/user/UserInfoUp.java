package takeout.clz.tangchaoke.data.dao.dataupbean.user;

import takeout.clz.tangchaoke.data.dao.dataupbean.BaseModelUp;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/9/3
 */
public class UserInfoUp extends BaseModelUp {
    String carriage_id;
    String token;
    String city_id;

    public UserInfoUp(String carriage_id, String token, String city_id) {
        this.carriage_id = carriage_id;
        this.token = token;
        this.city_id = city_id;
    }

    public String getCarriage_id() {
        return carriage_id;
    }

    public void setCarriage_id(String carriage_id) {
        this.carriage_id = carriage_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }
}
