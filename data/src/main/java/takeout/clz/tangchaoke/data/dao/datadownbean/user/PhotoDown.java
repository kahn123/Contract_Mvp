package takeout.clz.tangchaoke.data.dao.datadownbean.user;

import takeout.clz.tangchaoke.data.dao.datadownbean.BaseModelDown;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/9/14
 */
public class PhotoDown extends BaseModelDown {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * address_change : true
         */

        private String c_head;

        public String getC_head() {
            return c_head;
        }

        public void setC_head(String c_head) {
            this.c_head = c_head;
        }
    }


}
