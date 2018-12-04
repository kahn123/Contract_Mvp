package takeout.clz.tangchaoke.data.dao.dataupbean.user;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/15
 */
public class RegisterUp {

    String phone;
    String password;
    String register_ip;
    String code;

    public RegisterUp(String phone, String password, String register_ip, String code) {
        this.phone = phone;
        this.password = password;
        this.register_ip = register_ip;
        this.code = code;
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

    public String getRegister_ip() {
        return register_ip;
    }

    public void setRegister_ip(String register_ip) {
        this.register_ip = register_ip;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
