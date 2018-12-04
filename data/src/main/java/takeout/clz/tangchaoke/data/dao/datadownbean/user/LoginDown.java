package takeout.clz.tangchaoke.data.dao.datadownbean.user;

import takeout.clz.tangchaoke.data.dao.datadownbean.BaseModelDown;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/15
 */
public class LoginDown extends BaseModelDown {

    private LoginUserData data;

    public LoginUserData getData() {
        return data;
    }

    public void setData(LoginUserData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LoginUserModel{" +
                "data=" + data +
                '}';
    }
}
