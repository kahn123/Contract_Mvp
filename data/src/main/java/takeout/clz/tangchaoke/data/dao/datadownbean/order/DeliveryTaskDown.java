package takeout.clz.tangchaoke.data.dao.datadownbean.order;

import takeout.clz.tangchaoke.data.dao.datadownbean.BaseModelDown;

public class DeliveryTaskDown extends BaseModelDown {
    private int total_page;
    private DeliveryTaskDataDown data;

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public DeliveryTaskDataDown getData() {
        return data;
    }

    public void setData(DeliveryTaskDataDown data) {
        this.data = data;
    }
}
