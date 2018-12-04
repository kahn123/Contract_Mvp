package takeout.clz.tangchaoke.data.dao.dataupbean.user;

import java.io.File;

import takeout.clz.tangchaoke.data.dao.dataupbean.BaseModelUp;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/17
 */
public class CertificationUp extends BaseModelUp {

    String carriage_id;
    String token;
    String real_name;
    String idcard_code;
    String carriage_class;
    String register_province_id;
    String register_city_id;
    String register_town_id;
    String carriage_tool;
    File idcard_front;
    File idcard_back;
    File idcard_person;
    File driving_license;
    File vehicle_travel_license;

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

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getIdcard_code() {
        return idcard_code;
    }

    public void setIdcard_code(String idcard_code) {
        this.idcard_code = idcard_code;
    }

    public String getCarriage_class() {
        return carriage_class;
    }

    public void setCarriage_class(String carriage_class) {
        this.carriage_class = carriage_class;
    }

    public String getRegister_province_id() {
        return register_province_id;
    }

    public void setRegister_province_id(String register_province_id) {
        this.register_province_id = register_province_id;
    }

    public String getRegister_city_id() {
        return register_city_id;
    }

    public void setRegister_city_id(String register_city_id) {
        this.register_city_id = register_city_id;
    }

    public String getRegister_town_id() {
        return register_town_id;
    }

    public void setRegister_town_id(String register_town_id) {
        this.register_town_id = register_town_id;
    }

    public String getCarriage_tool() {
        return carriage_tool;
    }

    public void setCarriage_tool(String carriage_tool) {
        this.carriage_tool = carriage_tool;
    }

    public File getIdcard_front() {
        return idcard_front;
    }

    public void setIdcard_front(File idcard_front) {
        this.idcard_front = idcard_front;
    }

    public File getIdcard_back() {
        return idcard_back;
    }

    public void setIdcard_back(File idcard_back) {
        this.idcard_back = idcard_back;
    }

    public File getIdcard_person() {
        return idcard_person;
    }

    public void setIdcard_person(File idcard_person) {
        this.idcard_person = idcard_person;
    }

    public File getDriving_license() {
        return driving_license;
    }

    public void setDriving_license(File driving_license) {
        this.driving_license = driving_license;
    }

    public File getVehicle_travel_license() {
        return vehicle_travel_license;
    }

    public void setVehicle_travel_license(File vehicle_travel_license) {
        this.vehicle_travel_license = vehicle_travel_license;
    }
}
