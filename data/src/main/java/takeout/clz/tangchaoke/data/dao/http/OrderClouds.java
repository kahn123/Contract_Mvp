package takeout.clz.tangchaoke.data.dao.http;

import android.support.annotation.NonNull;

import java.util.Map;

import io.reactivex.Single;
import takeout.clz.tangchaoke.data.dao.RetrofitManger;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.AcceptOrderDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.CompleteMealDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.DeliveryMealDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.DeliveryTaskDetailDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.DeliveryTaskDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.RecoveryTaskDeatilDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.RecoveryTaskDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.WaitMealDown;
import takeout.clz.tangchaoke.data.dao.rx.RxUtils;
import takeout.clz.tangchaoke.data.dao.tool.HttpTool;
import takeout.clz.tangchaoke.data.dao.tool.ParameterString;

public class OrderClouds {

    private OrderClouds() {
    }

    private static class ServiceSingleton {
        private static final OrderService SERVICE = RetrofitManger.getInstance().create(OrderService.class);

        static OrderService getService() {
            return SERVICE;
        }
    }


    public static Single<DeliveryTaskDown> getDeliveryList(@NonNull String carriage_id,@NonNull String token,@NonNull String province_id,@NonNull String city_id,@NonNull String town_id,@NonNull String lng,@NonNull String lat,@NonNull String order_class,@NonNull String page) {
        Map<String, Object> map = HttpTool.getMap();
        map.put(ParameterString.CARRIAGE_ID, carriage_id);
        map.put(ParameterString.TOKEN, token);
        map.put(ParameterString.PROVINCE_ID, province_id);
        map.put(ParameterString.CITY_ID, city_id);
        map.put(ParameterString.TOWN_ID, town_id);
        map.put(ParameterString.LNG, lng);
        map.put(ParameterString.LAT, lat);
        map.put(ParameterString.ORDER_CLASS, order_class);
        map.put(ParameterString.PAGE, page);
        return RxUtils.compile(OrderClouds.ServiceSingleton.getService().deliveryTaskDownSingle(map));
    }


    public static Single<RecoveryTaskDown> getRecoveryList(@NonNull String carriage_id, @NonNull String token, @NonNull String province_id, @NonNull String city_id, @NonNull String town_id,@NonNull  String lng,@NonNull String lat,@NonNull String order_class,@NonNull String page) {
        Map<String, Object> map = HttpTool.getMap();
        map.put(ParameterString.CARRIAGE_ID, carriage_id);
        map.put(ParameterString.TOKEN, token);
        map.put(ParameterString.PROVINCE_ID, province_id);
        map.put(ParameterString.CITY_ID, city_id);
        map.put(ParameterString.TOWN_ID, town_id);
        map.put(ParameterString.LNG, lng);
        map.put(ParameterString.LAT, lat);
        map.put(ParameterString.ORDER_CLASS, order_class);
        map.put(ParameterString.PAGE, page);
        return RxUtils.compile(OrderClouds.ServiceSingleton.getService().recoveryTaskDownSingle(map));
    }


    public static Single<WaitMealDown> getWaitMealList(@NonNull String carriage_id,@NonNull String token ,@NonNull String page){
        Map<String, Object> map = HttpTool.getMap();
        map.put(ParameterString.CARRIAGE_ID, carriage_id);
        map.put(ParameterString.TOKEN, token);
        map.put(ParameterString.PAGE, page);
        return  RxUtils.compile(OrderClouds.ServiceSingleton.getService().waitMealDownSingle(map));
    }
    public static Single<DeliveryMealDown> getDeliveryMealList(@NonNull String carriage_id, @NonNull String token , @NonNull String page){
        Map<String, Object> map = HttpTool.getMap();
        map.put(ParameterString.CARRIAGE_ID, carriage_id);
        map.put(ParameterString.TOKEN, token);
        map.put(ParameterString.PAGE, page);
        return  RxUtils.compile(OrderClouds.ServiceSingleton.getService().delioveryMealDownSingle(map));
    }
    public static Single<CompleteMealDown> getCompleteMealList(@NonNull String carriage_id, @NonNull String token , @NonNull String page){
        Map<String, Object> map = HttpTool.getMap();
        map.put(ParameterString.CARRIAGE_ID, carriage_id);
        map.put(ParameterString.TOKEN, token);
        map.put(ParameterString.PAGE, page);
        return  RxUtils.compile(OrderClouds.ServiceSingleton.getService().completeMealDownSingle(map));
    }


    public static Single<DeliveryTaskDetailDown> getDeliveryTaskDetail(@NonNull String logistic_id, @NonNull String carriage_id , @NonNull String token){
        Map<String, Object> map = HttpTool.getMap();
        map.put(ParameterString.LOGISTIC_ID,logistic_id);
        map.put(ParameterString.CARRIAGE_ID,carriage_id);
        map.put(ParameterString.TOKEN,token);
        return  RxUtils.compile(OrderClouds.ServiceSingleton.getService().deliveryTaskDeatilDownSingle(map));
    }


    public static Single<RecoveryTaskDeatilDown> getRecoveryTaskDetail(@NonNull String logistic_id, @NonNull String carriage_id , @NonNull String token){
        Map<String, Object> map = HttpTool.getMap();
        map.put(ParameterString.LOGISTIC_ID,logistic_id);
        map.put(ParameterString.CARRIAGE_ID,carriage_id);
        map.put(ParameterString.TOKEN,token);
        return  RxUtils.compile(OrderClouds.ServiceSingleton.getService().recoveryTaskDeatilDownSingle(map));
    }


    public static Single<AcceptOrderDown> getAcceptOrder(@NonNull String logistic_id, @NonNull String carriage_id , @NonNull String token){
        Map<String, Object> map = HttpTool.getMap();
        map.put(ParameterString.LOGISTIC_ID,logistic_id);
        map.put(ParameterString.CARRIAGE_ID,carriage_id);
        map.put(ParameterString.TOKEN,token);
        return  RxUtils.compile(OrderClouds.ServiceSingleton.getService().acceptOrderDownSingle(map));
    }
}