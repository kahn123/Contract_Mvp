package takeout.clz.tangchaoke.data.dao.dataupbean.order;

import takeout.clz.tangchaoke.data.dao.dataupbean.BaseModelUp;

public class DeliveryTaskUp extends BaseModelUp {

    String carriage_id;
    String token;
    String province_id;
    String city_id;
    String town_id;
    String lat;
    String lng;
    String order_class;
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

    public String getTown_id() {
        return town_id;
    }

    public void setTown_id(String town_id) {
        this.town_id = town_id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getOrder_class() {
        return order_class;
    }

    public void setOrder_class(String order_class) {
        this.order_class = order_class;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
