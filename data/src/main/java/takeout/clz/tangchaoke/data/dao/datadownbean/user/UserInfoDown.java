package takeout.clz.tangchaoke.data.dao.datadownbean.user;

import java.util.List;

import takeout.clz.tangchaoke.data.dao.datadownbean.BaseModelDown;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/9/3
 */
public class UserInfoDown extends BaseModelDown {

    public DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * carriage_id : 10
         * phone : 13388066759
         * real_name : 车厘子
         * cumulate_cash : 0.00
         * c_head : http://hrhj.tangchaoke.com/Public/Uploads/carriageface/20161029/5813fbf1143ce.png
         * withdraw_cash : 0.23
         * evaluate_score : 9.7
         * customer_phone : 022-83869916
         */

        private String carriage_id;
        private String phone;
        private String real_name;
        private String cumulate_cash;
        private String self_invitation_code;
        private String c_head;
        private String evaluate_score;
        private String customer_phone;
        private List<AdvertListBean> advert_list;

        public String getSelf_invitation_code() {
            return self_invitation_code;
        }

        public void setSelf_invitation_code(String self_invitation_code) {
            this.self_invitation_code = self_invitation_code;
        }

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

        public String getCumulate_cash() {
            return cumulate_cash;
        }

        public void setCumulate_cash(String cumulate_cash) {
            this.cumulate_cash = cumulate_cash;
        }

        public String getC_head() {
            return c_head;
        }

        public void setC_head(String c_head) {
            c_head = c_head;
        }

        public String getEvaluate_score() {
            return evaluate_score;
        }

        public void setEvaluate_score(String evaluate_score) {
            this.evaluate_score = evaluate_score;
        }

        public String getCustomer_phone() {
            return customer_phone;
        }

        public void setCustomer_phone(String customer_phone) {
            this.customer_phone = customer_phone;
        }

        public List<AdvertListBean> getAdvert_list() {
            return advert_list;
        }

        public void setAdvert_list(List<AdvertListBean> advert_list) {
            this.advert_list = advert_list;
        }

        public static class AdvertListBean {
            /**
             * advert_id : 3 ###
             * province_id : 2
             * city_id : 2
             * advert_class : 2
             * jump_class : 2
             * jump_sign : 1
             * advert_pic : http://canyanwang-file-storage.oss-cn-beijing.aliyuncs.com/advert/20171127180674045a1c0403cb925.jpg  ###
             * advert_url :
             * sort_order : 1
             * advert_desc : 跳转商家ID
             * ctime : 1511751426
             * utime : 1511785476
             * status : 0
             */

            private String advert_id;
            private String province_id;
            private String city_id;
            private String advert_class;
            private String jump_class;
            private String jump_sign;
            private String advert_pic;
            private String advert_url;
            private String sort_order;
            private String advert_desc;
            private String ctime;
            private String utime;
            private String status;

            public String getAdvert_id() {
                return advert_id;
            }

            public void setAdvert_id(String advert_id) {
                this.advert_id = advert_id;
            }

            public String getProvince_id() {
                return province_id;
            }

            public void setProvince_id(String province_id) {
                this.province_id = province_id;
            }

            public String getCity_id() {
                return city_id;
            }

            public void setCity_id(String city_id) {
                this.city_id = city_id;
            }

            public String getAdvert_class() {
                return advert_class;
            }

            public void setAdvert_class(String advert_class) {
                this.advert_class = advert_class;
            }

            public String getJump_class() {
                return jump_class;
            }

            public void setJump_class(String jump_class) {
                this.jump_class = jump_class;
            }

            public String getJump_sign() {
                return jump_sign;
            }

            public void setJump_sign(String jump_sign) {
                this.jump_sign = jump_sign;
            }

            public String getAdvert_pic() {
                return advert_pic;
            }

            public void setAdvert_pic(String advert_pic) {
                this.advert_pic = advert_pic;
            }

            public String getAdvert_url() {
                return advert_url;
            }

            public void setAdvert_url(String advert_url) {
                this.advert_url = advert_url;
            }

            public String getSort_order() {
                return sort_order;
            }

            public void setSort_order(String sort_order) {
                this.sort_order = sort_order;
            }

            public String getAdvert_desc() {
                return advert_desc;
            }

            public void setAdvert_desc(String advert_desc) {
                this.advert_desc = advert_desc;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getUtime() {
                return utime;
            }

            public void setUtime(String utime) {
                this.utime = utime;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }


    }
}
