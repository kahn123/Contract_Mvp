package takeout.clz.tangchaoke.data.dao.datadownbean.order;

import java.util.List;

import takeout.clz.tangchaoke.data.dao.datadownbean.BaseModelDown;

public class DeliveryMealDown extends BaseModelDown {

    private int total_page;
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public static class DataBean {

        private String logistic_id;
        private String order_id;
        private String order_class;
        private String settle_state;
        private String carriage_id;
        private String delivery_time;
        private String c_delivery_fee;
        private String red_reward_fee;
        private String attach_fee;
        private String order_address_id;
        private String address_id;
        private String consignee;
        private String sex;
        private String mobile;
        private String user_province_id;
        private String user_city_id;
        private String user_town_id;
        private String address;
        private String address_detail;
        private String address_lng;
        private String address_lat;
        private String is_park;
        private String is_lift;
        private String mct_name;
        private String mct_phone;
        private String attach_service_content;
        private String mct_province_id;
        private String mct_city_id;
        private String mct_town_id;
        private String mct_address;
        private String mct_lng;
        private String mct_lat;
        private String invoice_title;
        private String invoice_number;
        private String invoice_person;
        private String invoice_address;
        private String ctime;
        private String status;
        private String b_distance;
        private String a_distance;
        private String delivery_fee;
        public String pei_delivery_time;
        public String member_delivery_time;
        public String box_count;

        public String getAttach_service_content() {
            return attach_service_content;
        }

        public void setAttach_service_content(String attach_service_content) {
            this.attach_service_content = attach_service_content;
        }

        public String getDelivery_fee() {
            return delivery_fee;
        }

        public void setDelivery_fee(String delivery_fee) {
            this.delivery_fee = delivery_fee;
        }

        private String order_state_info;

        public String getOrder_state_info() {
            return order_state_info;
        }

        public void setOrder_state_info(String order_state_info) {
            this.order_state_info = order_state_info;
        }

        public String getLogistic_id() {
            return logistic_id;
        }

        public void setLogistic_id(String logistic_id) {
            this.logistic_id = logistic_id;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_class() {
            return order_class;
        }

        public void setOrder_class(String order_class) {
            this.order_class = order_class;
        }

        public String getSettle_state() {
            return settle_state;
        }

        public void setSettle_state(String settle_state) {
            this.settle_state = settle_state;
        }

        public String getCarriage_id() {
            return carriage_id;
        }

        public void setCarriage_id(String carriage_id) {
            this.carriage_id = carriage_id;
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

        public String getOrder_address_id() {
            return order_address_id;
        }

        public void setOrder_address_id(String order_address_id) {
            this.order_address_id = order_address_id;
        }

        public String getAddress_id() {
            return address_id;
        }

        public void setAddress_id(String address_id) {
            this.address_id = address_id;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getUser_province_id() {
            return user_province_id;
        }

        public void setUser_province_id(String user_province_id) {
            this.user_province_id = user_province_id;
        }

        public String getUser_city_id() {
            return user_city_id;
        }

        public void setUser_city_id(String user_city_id) {
            this.user_city_id = user_city_id;
        }

        public String getUser_town_id() {
            return user_town_id;
        }

        public void setUser_town_id(String user_town_id) {
            this.user_town_id = user_town_id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAddress_detail() {
            return address_detail;
        }

        public void setAddress_detail(String address_detail) {
            this.address_detail = address_detail;
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

        public String getMct_phone() {
            return mct_phone;
        }

        public void setMct_phone(String mct_phone) {
            this.mct_phone = mct_phone;
        }

        public String getMct_province_id() {
            return mct_province_id;
        }

        public void setMct_province_id(String mct_province_id) {
            this.mct_province_id = mct_province_id;
        }

        public String getMct_city_id() {
            return mct_city_id;
        }

        public void setMct_city_id(String mct_city_id) {
            this.mct_city_id = mct_city_id;
        }

        public String getMct_town_id() {
            return mct_town_id;
        }

        public void setMct_town_id(String mct_town_id) {
            this.mct_town_id = mct_town_id;
        }

        public String getMct_address() {
            return mct_address;
        }

        public void setMct_address(String mct_address) {
            this.mct_address = mct_address;
        }

        public String getMct_lng() {
            return mct_lng;
        }

        public void setMct_lng(String mct_lng) {
            this.mct_lng = mct_lng;
        }

        public String getMct_lat() {
            return mct_lat;
        }

        public void setMct_lat(String mct_lat) {
            this.mct_lat = mct_lat;
        }

        public String getInvoice_title() {
            return invoice_title;
        }

        public void setInvoice_title(String invoice_title) {
            this.invoice_title = invoice_title;
        }

        public String getInvoice_number() {
            return invoice_number;
        }

        public void setInvoice_number(String invoice_number) {
            this.invoice_number = invoice_number;
        }

        public String getInvoice_person() {
            return invoice_person;
        }

        public void setInvoice_person(String invoice_person) {
            this.invoice_person = invoice_person;
        }

        public String getInvoice_address() {
            return invoice_address;
        }

        public void setInvoice_address(String invoice_address) {
            this.invoice_address = invoice_address;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getB_distance() {
            return b_distance;
        }

        public void setB_distance(String b_distance) {
            this.b_distance = b_distance;
        }

        public String getA_distance() {
            return a_distance;
        }

        public void setA_distance(String a_distance) {
            this.a_distance = a_distance;
        }

        public static class BDistanceBean {
            /**
             * distance_value : 12188.46
             * distance : 12188.46km
             */

            private double distance_value;
            private String distance;

            public double getDistance_value() {
                return distance_value;
            }

            public void setDistance_value(double distance_value) {
                this.distance_value = distance_value;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }
        }

        public static class ADistanceBean {
            /**
             * distance_value : 1.15
             * distance : 1.15km
             */

            private double distance_value;
            private String distance;

            public double getDistance_value() {
                return distance_value;
            }

            public void setDistance_value(double distance_value) {
                this.distance_value = distance_value;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }
        }
    }
}
