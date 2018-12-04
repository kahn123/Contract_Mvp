package takeout.clz.tangchaoke.data.dao.dataupbean.user;

import takeout.clz.tangchaoke.data.dao.dataupbean.BaseModelUp;

public class ForgetPwdUp extends BaseModelUp {
    String phone;
    String code;
    String new_password;
    String re_password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getRe_password() {
        return re_password;
    }

    public void setRe_password(String re_password) {
        this.re_password = re_password;
    }
}
