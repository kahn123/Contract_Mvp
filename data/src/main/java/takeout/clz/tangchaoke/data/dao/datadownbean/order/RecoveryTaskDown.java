package takeout.clz.tangchaoke.data.dao.datadownbean.order;

import takeout.clz.tangchaoke.data.dao.datadownbean.BaseModelDown;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/18
 */
public class RecoveryTaskDown extends BaseModelDown {
    private int total_page;
    private RecoveryTaskDataDown data;

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public RecoveryTaskDataDown getData() {
        return data;
    }

    public void setData(RecoveryTaskDataDown data) {
        this.data = data;
    }
}
