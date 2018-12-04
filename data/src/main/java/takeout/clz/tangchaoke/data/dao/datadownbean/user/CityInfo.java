package takeout.clz.tangchaoke.data.dao.datadownbean.user;

import java.util.List;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/9/11
 */
public class CityInfo {

    private List<CityBean> citylist;

    public List<CityBean> getCitylist() {
        return citylist;
    }

    public void setCitylist(List<CityBean> citylist) {
        this.citylist = citylist;
    }

    public static class CityBean {
        /**
         * area_id : 1
         * area_name : 北京市
         * merchant_score : 2
         * area_level : 1
         * parent_id : 1
         */

        private String area_id;
        private String area_name;
        private String level;
        private String area_level;
        private String parent_id;

        public String getArea_id() {
            return area_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getArea_level() {
            return area_level;
        }

        public void setArea_level(String area_level) {
            this.area_level = area_level;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        @Override
        public String toString() {
            return area_name;
        }
    }

}
