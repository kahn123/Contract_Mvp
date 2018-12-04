package takeout.clz.tangchaoke.data.dao.dataupbean.user;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/15
 */
public class LoginUp {

    String phone;
    String password;

    public LoginUp(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
