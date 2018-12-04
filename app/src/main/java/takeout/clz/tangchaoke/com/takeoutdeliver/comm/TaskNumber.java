package takeout.clz.tangchaoke.com.takeoutdeliver.comm;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/21
 */
public class TaskNumber {

    String type;
    String number;

    public TaskNumber(String type, String number) {
        this.type = type;
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
