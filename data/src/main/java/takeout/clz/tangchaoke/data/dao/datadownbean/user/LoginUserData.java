package takeout.clz.tangchaoke.data.dao.datadownbean.user;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/15
 */
public class LoginUserData {

    /**
     * carriage_id : 166
     * phone : 15600907885
     * real_name :              真实姓名
     * c_head : http://canyanwang-file-storage.oss-cn-beijing.aliyuncs.com/default/default_face.png?x-oss-process=image/resize,m_fill,h_96,w_96
     * cumulate_cash : 0.00     总余额
     * evaluate_score : 0       平均分数
     * token : 3bb96c5800454cfdcf4bc98bf59c4065
     * register_class : 1    注册类型 1：未完善信息 2：正常
     */

    private String carriage_id;
    private String phone;
    private String real_name;
    private String c_head;
    private String cumulate_cash;
    private String evaluate_score;
    private String token;
    private String register_class;
    private String state;       //是否通过审核 1:申请中 2:已通过 3:已拒绝 4:培训中 5:培训通过 6:培训失败
    private String mct_carriage_state;
    private String mct_id;
    private String mct_name;

    public String getCarriage_id() {
        return carriage_id;
    }

    public void setCarriage_id(String carriage_id) {
        this.carriage_id = carriage_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getC_head() {
        return c_head;
    }

    public void setC_head(String c_head) {
        this.c_head = c_head;
    }

    public String getCumulate_cash() {
        return cumulate_cash;
    }

    public void setCumulate_cash(String cumulate_cash) {
        this.cumulate_cash = cumulate_cash;
    }

    public String getEvaluate_score() {
        return evaluate_score;
    }

    public void setEvaluate_score(String evaluate_score) {
        this.evaluate_score = evaluate_score;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRegister_class() {
        return register_class;
    }

    public void setRegister_class(String register_class) {
        this.register_class = register_class;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMct_carriage_state() {
        return mct_carriage_state;
    }

    public void setMct_carriage_state(String mct_carriage_state) {
        this.mct_carriage_state = mct_carriage_state;
    }

    public String getMct_id() {
        return mct_id;
    }

    public void setMct_id(String mct_id) {
        this.mct_id = mct_id;
    }

    public String getMct_name() {
        return mct_name;
    }

    public void setMct_name(String mct_name) {
        this.mct_name = mct_name;
    }
}
