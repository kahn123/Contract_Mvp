package takeout.clz.tangchaoke.data.dao.datadownbean.order;

import java.util.List;

import takeout.clz.tangchaoke.data.dao.datadownbean.BaseModelDown;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/23
 */
public class RecoveryTaskDeatilDown extends BaseModelDown {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * logistic_id : 50
         * order_class : a
         * order_id : 154
         * order_sn : 20171228233712bo9v
         * order_number : A2
         * order_state : 3
         * delivery_time : 1514477760
         * member_delivery_time : 12-29 00:16 前送达
         * order_address_id : 147
         * address_id : 51
         * consignee : 赵云鹏
         * sex : 1
         * mobile : 17695515459
         * user_province_id : 2
         * user_city_id : 45058
         * user_town_id : 71
         * address : 华润豪景酒店
         * address_detail : 前台
         * address_lng : 0.000000
         * address_lat : 0.000000
         * is_park : 0
         * is_lift : 0
         * mct_name : 千里之外
         * mct_phone :
         * mct_province_id : 2
         * mct_city_id : 45058
         * mct_town_id : 71
         * mct_address :
         * mct_lng : 117.073589
         * mct_lat : 38.844194
         * invoice_title :
         * invoice_number :
         * invoice_person :
         * person_phone :
         * invoice_address :
         * um_distance : 0.00
         * ctime : 1514475432
         * status : 0
         * delivery_fee : 0
         * c_delivery_fee : 0
         * red_reward_fee : 0.00
         * attach_fee : 0.00
         * dishes_info : [{"dishes_id":2149,"dishes_class":2,"dishes_name":"川菜套餐1","dishes_price":"0.01","deposit_price":0,"dishes_combo":[{"dishes_id":2148,"dishes_class":1,"dishes_name":"宫保鸡丁","dishes_price":"0.01","deposit_price":0,"dishes_combo":[],"number":"1"},{"dishes_id":2147,"dishes_class":1,"dishes_name":"鱼香肉丝","dishes_price":"0.01","deposit_price":0,"dishes_combo":[],"number":"1"}],"number":"2"},{"dishes_id":2148,"dishes_class":1,"dishes_name":"宫保鸡丁","dishes_price":"0.01","deposit_price":0,"dishes_combo":[],"number":"2"}]
         * attach_service : [{"service_id":"123","service_name":"洗苹果","service_money":"300"},{"service_id":"123","service_name":"洗大枣","service_money":"0.1"},{"service_id":"123","service_name":"洗车","service_money":"8868"}]
         * order_dynamic_list : [{"dynamic_id":861,"dynamic_class":1,"operate_class":"order_payment","order_id":154,"description":"订单支付成功","ctime":1514475464},{"dynamic_id":863,"dynamic_class":1,"operate_class":"merchant_accept","order_id":154,"description":"商家已接单","ctime":1514475480},{"dynamic_id":865,"dynamic_class":1,"operate_class":"merchant_call_car","order_id":154,"description":"商家已叫车","ctime":1514475484}]
         * user_address_detail : 天津市天津市静海区华润豪景酒店
         * mct_address_detail : 天津市天津市静海区
         * carriage_class : 1
         * carriage_tool : 1
         * customer_phone : null
         * a_distance : 1.77
         * b_distance : 0.00
         */

        private String logistic_id;
        public String box_count;
        private String order_class;
        private String order_id;
        private String order_sn;
        private String order_number;
        private String order_state;
        private String delivery_time;
        private String order_state_info;
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
        private String mct_province_id;
        private String mct_city_id;
        private String mct_town_id;
        private String mct_address;
        private String mct_lng;
        private String mct_lat;
        private String invoice_title;
        private String invoice_number;
        private String invoice_person;
        private String person_phone;
        private String invoice_address;
        private String um_distance;
        private String ctime;
        private String status;
        private String delivery_fee;
        private String c_delivery_fee;
        private String red_reward_fee;
        private String attach_fee;
        private String user_address_detail;
        private String mct_address_detail;
        private String carriage_class;
        private String carriage_tool;
        private String customer_phone;
        private double a_distance;
        private String b_distance;
        public String pei_delivery_time;
        public String member_delivery_time;
        private List<DishesInfoBean> dishes_info;
        private List<AttachServiceBean> attach_service;
        private List<OrderDynamicListBean> order_dynamic_list;


        public String c_fetch_punish;
        public String c_arrive_punish;
        public String punish_actual_delivery_fee;

        public String arrive_delay_time;//送达晚点
        public String fetch_delay_time;//取货晚点

        public String delivery_class;//配送方式 1:平台配送 2:商家配送


        public String getDelivery_class() {
            return delivery_class;
        }

        public void setDelivery_class(String delivery_class) {
            this.delivery_class = delivery_class;
        }

        public String getArrive_delay_time() {
            return arrive_delay_time;
        }

        public void setArrive_delay_time(String arrive_delay_time) {
            this.arrive_delay_time = arrive_delay_time;
        }

        public String getFetch_delay_time() {
            return fetch_delay_time;
        }

        public void setFetch_delay_time(String fetch_delay_time) {
            this.fetch_delay_time = fetch_delay_time;
        }


        public String getC_fetch_punish() {
            return c_fetch_punish;
        }

        public void setC_fetch_punish(String c_fetch_punish) {
            this.c_fetch_punish = c_fetch_punish;
        }

        public String getC_arrive_punish() {
            return c_arrive_punish;
        }

        public void setC_arrive_punish(String c_arrive_punish) {
            this.c_arrive_punish = c_arrive_punish;
        }

        public String getPunish_actual_delivery_fee() {
            return punish_actual_delivery_fee;
        }

        public void setPunish_actual_delivery_fee(String punish_actual_delivery_fee) {
            this.punish_actual_delivery_fee = punish_actual_delivery_fee;
        }

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

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getOrder_number() {
            return order_number;
        }

        public void setOrder_number(String order_number) {
            this.order_number = order_number;
        }

        public String getOrder_state() {
            return order_state;
        }

        public void setOrder_state(String order_state) {
            this.order_state = order_state;
        }

        public String getDelivery_time() {
            return delivery_time;
        }

        public void setDelivery_time(String delivery_time) {
            this.delivery_time = delivery_time;
        }

        public String getOrder_state_info() {
            return order_state_info;
        }

        public void setOrder_state_info(String order_state_info) {
            this.order_state_info = order_state_info;
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

        public String getPerson_phone() {
            return person_phone;
        }

        public void setPerson_phone(String person_phone) {
            this.person_phone = person_phone;
        }

        public String getInvoice_address() {
            return invoice_address;
        }

        public void setInvoice_address(String invoice_address) {
            this.invoice_address = invoice_address;
        }

        public String getUm_distance() {
            return um_distance;
        }

        public void setUm_distance(String um_distance) {
            this.um_distance = um_distance;
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

        public String getDelivery_fee() {
            return delivery_fee;
        }

        public void setDelivery_fee(String delivery_fee) {
            this.delivery_fee = delivery_fee;
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

        public String getUser_address_detail() {
            return user_address_detail;
        }

        public void setUser_address_detail(String user_address_detail) {
            this.user_address_detail = user_address_detail;
        }

        public String getMct_address_detail() {
            return mct_address_detail;
        }

        public void setMct_address_detail(String mct_address_detail) {
            this.mct_address_detail = mct_address_detail;
        }

        public String getCarriage_class() {
            return carriage_class;
        }

        public void setCarriage_class(String carriage_class) {
            this.carriage_class = carriage_class;
        }

        public String getCarriage_tool() {
            return carriage_tool;
        }

        public void setCarriage_tool(String carriage_tool) {
            this.carriage_tool = carriage_tool;
        }

        public String getCustomer_phone() {
            return customer_phone;
        }

        public void setCustomer_phone(String customer_phone) {
            this.customer_phone = customer_phone;
        }

        public double getA_distance() {
            return a_distance;
        }

        public void setA_distance(double a_distance) {
            this.a_distance = a_distance;
        }

        public String getB_distance() {
            return b_distance;
        }

        public void setB_distance(String b_distance) {
            this.b_distance = b_distance;
        }

        public List<DishesInfoBean> getDishes_info() {
            return dishes_info;
        }

        public void setDishes_info(List<DishesInfoBean> dishes_info) {
            this.dishes_info = dishes_info;
        }

        public List<AttachServiceBean> getAttach_service() {
            return attach_service;
        }

        public void setAttach_service(List<AttachServiceBean> attach_service) {
            this.attach_service = attach_service;
        }

        public List<OrderDynamicListBean> getOrder_dynamic_list() {
            return order_dynamic_list;
        }

        public void setOrder_dynamic_list(List<OrderDynamicListBean> order_dynamic_list) {
            this.order_dynamic_list = order_dynamic_list;
        }

        public static class DishesInfoBean {
            /**
             * dishes_id : 2149
             * dishes_class : 2
             * dishes_name : 川菜套餐1
             * dishes_price : 0.01
             * deposit_price : 0
             * dishes_combo : [{"dishes_id":2148,"dishes_class":1,"dishes_name":"宫保鸡丁","dishes_price":"0.01","deposit_price":0,"dishes_combo":[],"number":"1"},{"dishes_id":2147,"dishes_class":1,"dishes_name":"鱼香肉丝","dishes_price":"0.01","deposit_price":0,"dishes_combo":[],"number":"1"}]
             * number : 2
             */

            private String dishes_id;
            private String dishes_class;
            private String dishes_name;
            private String dishes_price;
            private String deposit_price;
            private String number;
            private List<DishesComboBean> dishes_combo;

            public String getDishes_id() {
                return dishes_id;
            }

            public void setDishes_id(String dishes_id) {
                this.dishes_id = dishes_id;
            }

            public String getDishes_class() {
                return dishes_class;
            }

            public void setDishes_class(String dishes_class) {
                this.dishes_class = dishes_class;
            }

            public String getDishes_name() {
                return dishes_name;
            }

            public void setDishes_name(String dishes_name) {
                this.dishes_name = dishes_name;
            }

            public String getDishes_price() {
                return dishes_price;
            }

            public void setDishes_price(String dishes_price) {
                this.dishes_price = dishes_price;
            }

            public String getDeposit_price() {
                return deposit_price;
            }

            public void setDeposit_price(String deposit_price) {
                this.deposit_price = deposit_price;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public List<DishesComboBean> getDishes_combo() {
                return dishes_combo;
            }

            public void setDishes_combo(List<DishesComboBean> dishes_combo) {
                this.dishes_combo = dishes_combo;
            }

            public static class DishesComboBean {
                /**
                 * dishes_id : 2148
                 * dishes_class : 1
                 * dishes_name : 宫保鸡丁
                 * dishes_price : 0.01
                 * deposit_price : 0
                 * dishes_combo : []
                 * number : 1
                 */

                private String dishes_id;
                private String dishes_class;
                private String dishes_name;
                private String dishes_price;
                private String deposit_price;
                private String number;

                public String getDishes_id() {
                    return dishes_id;
                }

                public void setDishes_id(String dishes_id) {
                    this.dishes_id = dishes_id;
                }

                public String getDishes_class() {
                    return dishes_class;
                }

                public void setDishes_class(String dishes_class) {
                    this.dishes_class = dishes_class;
                }

                public String getDishes_name() {
                    return dishes_name;
                }

                public void setDishes_name(String dishes_name) {
                    this.dishes_name = dishes_name;
                }

                public String getDishes_price() {
                    return dishes_price;
                }

                public void setDishes_price(String dishes_price) {
                    this.dishes_price = dishes_price;
                }

                public String getDeposit_price() {
                    return deposit_price;
                }

                public void setDeposit_price(String deposit_price) {
                    this.deposit_price = deposit_price;
                }

                public String getNumber() {
                    return number;
                }

                public void setNumber(String number) {
                    this.number = number;
                }
            }
        }

        public static class AttachServiceBean {
            /**
             * service_id : 123
             * service_name : 洗苹果
             * service_money : 300
             */

            private String service_id;
            private String service_name;
            private String service_money;

            private String service_name2;
            private String service_money2;
            private String timeout_service_name;
            private String timeout_service_money;
            private String timeout_service_name2;
            private String timeout_service_money2;


            public String getService_name2() {
                return service_name2;
            }

            public void setService_name2(String service_name2) {
                this.service_name2 = service_name2;
            }

            public String getService_money2() {
                return service_money2;
            }

            public void setService_money2(String service_money2) {
                this.service_money2 = service_money2;
            }

            public String getTimeout_service_name() {
                return timeout_service_name;
            }

            public void setTimeout_service_name(String timeout_service_name) {
                this.timeout_service_name = timeout_service_name;
            }

            public String getTimeout_service_money() {
                return timeout_service_money;
            }

            public void setTimeout_service_money(String timeout_service_money) {
                this.timeout_service_money = timeout_service_money;
            }

            public String getTimeout_service_name2() {
                return timeout_service_name2;
            }

            public void setTimeout_service_name2(String timeout_service_name2) {
                this.timeout_service_name2 = timeout_service_name2;
            }

            public String getTimeout_service_money2() {
                return timeout_service_money2;
            }

            public void setTimeout_service_money2(String timeout_service_money2) {
                this.timeout_service_money2 = timeout_service_money2;
            }

            public String getService_id() {
                return service_id;
            }

            public void setService_id(String service_id) {
                this.service_id = service_id;
            }

            public String getService_name() {
                return service_name;
            }

            public void setService_name(String service_name) {
                this.service_name = service_name;
            }

            public String getService_money() {
                return service_money;
            }

            public void setService_money(String service_money) {
                this.service_money = service_money;
            }
        }

        public static class OrderDynamicListBean {
            /**
             * dynamic_id : 861
             * dynamic_class : 1
             * operate_class : order_payment
             * order_id : 154
             * description : 订单支付成功
             * ctime : 1514475464
             */

            private String dynamic_id;
            private String dynamic_class;
            private String operate_class;
            private String order_id;
            private String description;
            private String ctime;

            public String getDynamic_id() {
                return dynamic_id;
            }

            public void setDynamic_id(String dynamic_id) {
                this.dynamic_id = dynamic_id;
            }

            public String getDynamic_class() {
                return dynamic_class;
            }

            public void setDynamic_class(String dynamic_class) {
                this.dynamic_class = dynamic_class;
            }

            public String getOperate_class() {
                return operate_class;
            }

            public void setOperate_class(String operate_class) {
                this.operate_class = operate_class;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }
        }
    }
}
