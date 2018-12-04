package takeout.clz.tangchaoke.data.dao.dataupbean.order;

import takeout.clz.tangchaoke.data.dao.dataupbean.BaseModelUp;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/23
 */
public class RecoveryTaskDeatilUp extends BaseModelUp {
    String logistic_id;
    String carriage_id;
    String token;

    public String getLogistic_id() {
        return logistic_id;
    }

    public void setLogistic_id(String logistic_id) {
        this.logistic_id = logistic_id;
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
}
