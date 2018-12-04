package takeout.clz.tangchaoke.data.dao.http;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.AcceptOrderDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.CompleteMealDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.DeliveryMealDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.DeliveryTaskDetailDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.DeliveryTaskDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.RecoveryTaskDeatilDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.RecoveryTaskDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.WaitMealDown;

public interface OrderService {

    @FormUrlEncoded
    @POST("papi/order/wait_accept_order_list")
    Single<DeliveryTaskDown> deliveryTaskDownSingle(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("papi/order/wait_accept_order_list")
    Single<RecoveryTaskDown> recoveryTaskDownSingle(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("papi/order/wait_fatch_order_list")
    Single<WaitMealDown> waitMealDownSingle(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("papi/order/wait_arrive_order_list")
    Single<DeliveryMealDown> delioveryMealDownSingle(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("papi/order/confirm_arrive_order_list")
    Single<CompleteMealDown> completeMealDownSingle(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("papi/order/carriage_order_info")
    Single<DeliveryTaskDetailDown> deliveryTaskDeatilDownSingle(@FieldMap Map<String,Object> map);

    @FormUrlEncoded
    @POST("papi/order/carriage_order_info")
    Single<RecoveryTaskDeatilDown> recoveryTaskDeatilDownSingle(@FieldMap Map<String,Object> map);

    @FormUrlEncoded
    @POST("papi/order/carriage_accept_order")
    Single<AcceptOrderDown> acceptOrderDownSingle(@FieldMap Map<String,Object> map);
}
