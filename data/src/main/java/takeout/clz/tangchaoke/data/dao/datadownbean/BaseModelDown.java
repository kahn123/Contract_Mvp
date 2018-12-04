package takeout.clz.tangchaoke.data.dao.datadownbean;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/15
 */
public class BaseModelDown {

    private String flag;
    private String message;
    private String error_code;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }
}
