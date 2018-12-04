package takeout.clz.tangchaoke.data.dao.datadownbean.order;

import takeout.clz.tangchaoke.data.dao.datadownbean.BaseModelDown;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/24
 */
public class AcceptOrderDown extends BaseModelDown {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private boolean address_change;

        public boolean isAddress_change() {
            return address_change;
        }

        public void setAddress_change(boolean address_change) {
            this.address_change = address_change;
        }
    }
}
