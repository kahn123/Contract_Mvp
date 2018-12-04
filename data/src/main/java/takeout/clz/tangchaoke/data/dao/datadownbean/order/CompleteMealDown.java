package takeout.clz.tangchaoke.data.dao.datadownbean.order;

import java.util.List;

import takeout.clz.tangchaoke.data.dao.datadownbean.BaseModelDown;

public class CompleteMealDown extends BaseModelDown {

    private int total_page;

    private DataBean data;

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }
    public static class DataBean {
        private String cumulate_cash;

        private List<ListBean> list;

        public String getCumulate_cash() {
            return cumulate_cash;
        }

        public void setCumulate_cash(String cumulate_cash) {
            this.cumulate_cash = cumulate_cash;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String logistic_id;
            private String order_id;
            private String order_class;
            private String delivery_class;
            private String delivery_state;
            private String settle_state;
            private String attach_service_content;
            private String delivery_time;
            private String c_delivery_fee;
            private String red_reward_fee;
            private String attach_fee;
            private String consignee;
            private String address;
            private String address_lng;
            private String address_lat;
            private String is_park;
            private String is_lift;
            private String mct_name;
            private String mct_address;
            private String delivery_fee;
            public String box_count;

            public String getDelivery_fee() {
                return delivery_fee;
            }

            public void setDelivery_fee(String delivery_fee) {
                this.delivery_fee = delivery_fee;
            }

            private String lng;
            private String lat;

            public String getLogistic_id() {
                return logistic_id;
            }

            public void setLogistic_id(String logistic_id) {
                this.logistic_id = logistic_id;
            }

            public String getOrder_class() {
                return order_class;
            }

            public void setOrder_class(String order_class) {
                this.order_class = order_class;
            }

            public String getDelivery_class() {
                return delivery_class;
            }

            public void setDelivery_class(String delivery_class) {
                this.delivery_class = delivery_class;
            }

            public String getDelivery_state() {
                return delivery_state;
            }

            public void setDelivery_state(String delivery_state) {
                this.delivery_state = delivery_state;
            }

            public String getSettle_state() {
                return settle_state;
            }

            public void setSettle_state(String settle_state) {
                this.settle_state = settle_state;
            }

            public String getAttach_service_content() {
                return attach_service_content;
            }

            public void setAttach_service_content(String attach_service_content) {
                this.attach_service_content = attach_service_content;
            }

            public String getDelivery_time() {
                return delivery_time;
            }

            public void setDelivery_time(String delivery_time) {
                this.delivery_time = delivery_time;
            }

            public String getC_delivery_fee() {
                return c_delivery_fee;
            }

            public void setC_delivery_fee(String c_delivery_fee) {
                this.c_delivery_fee = c_delivery_fee;
            }

            public String getRed_reward_fee() {
                return red_reward_fee;
            }

            public void setRed_reward_fee(String red_reward_fee) {
                this.red_reward_fee = red_reward_fee;
            }

            public String getAttach_fee() {
                return attach_fee;
            }

            public void setAttach_fee(String attach_fee) {
                this.attach_fee = attach_fee;
            }

            public String getConsignee() {
                return consignee;
            }

            public void setConsignee(String consignee) {
                this.consignee = consignee;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getAddress_lng() {
                return address_lng;
            }

            public void setAddress_lng(String address_lng) {
                this.address_lng = address_lng;
            }

            public String getAddress_lat() {
                return address_lat;
            }

            public void setAddress_lat(String address_lat) {
                this.address_lat = address_lat;
            }

            public String getIs_park() {
                return is_park;
            }

            public void setIs_park(String is_park) {
                this.is_park = is_park;
            }

            public String getIs_lift() {
                return is_lift;
            }

            public void setIs_lift(String is_lift) {
                this.is_lift = is_lift;
            }

            public String getMct_name() {
                return mct_name;
            }

            public void setMct_name(String mct_name) {
                this.mct_name = mct_name;
            }

            public String getMct_address() {
                return mct_address;
            }

            public void setMct_address(String mct_address) {
                this.mct_address = mct_address;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }
            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }
        }
    }


}
