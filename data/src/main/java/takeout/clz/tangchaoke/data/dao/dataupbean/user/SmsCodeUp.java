package takeout.clz.tangchaoke.data.dao.dataupbean.user;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/15
 */
public class SmsCodeUp {

    String phone;
    String type;
    String code;

    public SmsCodeUp(String phone, String type, String code) {
        this.phone = phone;
        this.type = type;
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
