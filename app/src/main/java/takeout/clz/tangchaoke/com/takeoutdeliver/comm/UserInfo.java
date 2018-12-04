//package takeout.clz.tangchaoke.comtakeoutdeliver.comm;
//
///**
// * 类说明
// *
// * @author 肖峰
// * @date 2018/8/17
// */
//
//import com.leo.utils.SharedPreferencesUtils;
//
///**
// * Created by Administrator on 2016/4/20.
// * 将用户id设为全局变量
// */
//public class UserInfo {
//
//
////    public static long getUSERID() {
////        try {
////            Long carriageId = Long.parseLong(App.getInfo(App.CARRIAGEID));
////            return carriageId;
////        } catch (Exception e) {
////            LogUtils.e("方法异常：" + e.getMessage());
////        }
////        return -1;
////    }
////
////    public static void setUSERID(long carriageId) {
////        App.saveInfo(App.CARRIAGEID, carriageId + "");
////    }
//
//    public static void setHeadImage(String carriage_id) {
//        SharedPreferencesUtils.writeToSharedPreferences("carriage_id", carriage_id);
//    }
//
//    public static String getHeadImage() {
//        return SharedPreferencesUtils.readStringFromSharedPreferences("", "0");
//    }
//
//    public static void setAccount(String headImage) {
//        SharedPreferencesUtils.saveStringData("account", headImage);
//    }
//
//    public static String getAccount() {
//        return SharedPreferencesUtils.getStringData("account", "");
//    }
//
//    public static void setUserName(String userName) {
//        SharedPreferencesUtils.saveStringData("user_name", userName);
//    }
//
//    public static String getUserName() {
//        return SharedPreferencesUtils.getStringData("user_name", "");
//    }
//
//    public static void setServicePhone(String servicePhone) {
//        SharedPreferencesUtils.saveStringData("service_phone", servicePhone);
//    }
//
//    public static String getServicePhone() {
//        return SharedPreferencesUtils.getStringData("service_phone", "400-022-5888");
//    }
//
//    public static String getMct_carriage_state() {
//        return SharedPreferencesUtils.getStringData("Mct_carriage_state", "123456");
//    }
//    public static void setMct_carriage_state(String Mct_carriage_state) {
//        SharedPreferencesUtils.saveStringData("Mct_carriage_state", Mct_carriage_state);
//    }
//
//
//
//
//
//    public static String getMct_id() {
//        return SharedPreferencesUtils.getStringData("mct_id", "123456");
//    }
//    public static void setMct_id(String Mct_id) {
//        SharedPreferencesUtils.saveStringData("mct_id", Mct_id);
//    }
//
//
//    public static String getMct_name() {
//        return SharedPreferencesUtils.getStringData("mct_name", "123456");
//    }
//    public static void setMct_name(String Mct_name) {
//        SharedPreferencesUtils.saveStringData("mct_name", Mct_name);
//    }
//
//    /**
//     * 保存邀请码
//     * @param inviteCode 邀请码
//     */
//    public static void setInviteCode(String inviteCode) {
//        SharedPreferencesUtils.saveStringData("invite_code", inviteCode);
//    }
//
//    /**
//     * 获取邀请码
//     * @return
//     */
//    public static String getInviteCode() {
//        return SharedPreferencesUtils.getStringData("invite_code", "");
//    }
//
