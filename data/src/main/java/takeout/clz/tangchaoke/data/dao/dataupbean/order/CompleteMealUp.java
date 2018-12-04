package takeout.clz.tangchaoke.data.dao.dataupbean.order;

import takeout.clz.tangchaoke.data.dao.dataupbean.BaseModelUp;

public class CompleteMealUp extends BaseModelUp {
    String carriage_id;
    String token;
    String page;

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

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
