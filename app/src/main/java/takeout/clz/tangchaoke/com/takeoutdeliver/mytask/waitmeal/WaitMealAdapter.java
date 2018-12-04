package takeout.clz.tangchaoke.com.takeoutdeliver.mytask.waitmeal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import takeout.clz.tangchaoke.comm.toolutils.CommStringUtils;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.WaitMealDown;
import takeout.clz.tangchaoke.data.dao.rx.RxViewUtils;
import takeout.clz.tangchaoke.com.takeoutdeliver.R;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.MyBaseQuickAdapter;

public class WaitMealAdapter extends MyBaseQuickAdapter<WaitMealDown.DataBean> {
    Context context;
    List<WaitMealDown.DataBean> list;
    DataCallBack dataCallBack;

    public void setDataCallBack(DataCallBack dataCallBack) {
        this.dataCallBack = dataCallBack;
    }

    public interface DataCallBack {
        void callBack(View view, int position, Object data);
    }

    public WaitMealAdapter(int layoutResId, @Nullable List<WaitMealDown.DataBean> data, Context context) {
        super(layoutResId, data);
        this.list = data;
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, WaitMealDown.DataBean item) {
        try {
            if (item.getOrder_class().equals("a")) {

                helper.setText(R.id.tv_mct_name, item.getMct_name());
                helper.setText(R.id.tv_mine_distance, item.getA_distance() + "km");
                helper.setText(R.id.tv_send_address, item.getMct_address());
                helper.setText(R.id.tv_get_distance, item.getB_distance() + "km");
                helper.setText(R.id.tv_get_address, item.getAddress());
                helper.getView(R.id.iv_order_class).setVisibility(View.VISIBLE);
                helper.getView(R.id.iv_order_class).setBackgroundResource(R.mipmap.bill_song);
                helper.setText(R.id.tv_get_time, item.pei_delivery_time);
                helper.setText(R.id.tv_arrive_time, item.member_delivery_time);
            } else {
                // 客户名称
                helper.setText(R.id.tv_mct_name, item.getConsignee());
                // 取货距离
                helper.setText(R.id.tv_get_distance, item.getB_distance() + "km");
                // 取货地址
                helper.setText(R.id.tv_get_address, item.getMct_address());
                // 收货距离
                helper.setText(R.id.tv_mine_distance, item.getA_distance() + "km");
                // 收货地址
                helper.setText(R.id.tv_send_address, item.getAddress());
                helper.getView(R.id.iv_order_class).setVisibility(View.VISIBLE);
                helper.getView(R.id.iv_order_class).setBackgroundResource(R.mipmap.bill_shou);
                helper.setText(R.id.tv_get_time, item.member_delivery_time);
                helper.setText(R.id.tv_arrive_time, item.pei_delivery_time);
            }
            if (TextUtils.isEmpty(item.box_count)) {
                helper.setText(R.id.tv_box_count, String.format(context.getString(R.string.box_count), "1"));
            } else {
                helper.setText(R.id.tv_box_count, String.format(context.getString(R.string.box_count), item.box_count));
            }
            // 收入
            CharSequence formatString = CommStringUtils.getFormatCharSequence("一口价：¥" + CommStringUtils.format2Decimals(item.getDelivery_fee()), context);
            helper.setText(R.id.tv_income, formatString);
            if (!TextUtils.isEmpty(item.getAttach_service_content())) {
                helper.setText(R.id.tv_demand, item.getAttach_service_content());
            } else {
                helper.setText(R.id.tv_demand, "无");
            }
            if (item.getIs_park().equals("0") || item.getIs_park().equals("2")) {
                helper.setText(R.id.tv_is_park, "不可进车");
            } else if (item.getIs_park().equals("1")) {
                helper.setText(R.id.tv_is_park, "可进车");
            }
            if (item.getIs_lift().equals("0") || item.getIs_lift().equals("2")) {
                helper.setText(R.id.tv_is_lift, "无电梯");
            } else if (item.getIs_lift().equals("1")) {
                helper.setText(R.id.tv_is_lift, "有电梯");
            }
            // 确认取货
            helper.getView(R.id.btn_confirm_arrive).setVisibility(View.VISIBLE);
            // order_id
            helper.setText(R.id.tv_order_id, "order_id = " + item.getOrder_id());
            // 收货按钮设置监听
            View v1 = helper.getView(R.id.btn_confirm_arrive);
            addSubscribe(RxViewUtils.throttleFirstView(v1, new RxViewUtils.CallBack() {
                @Override
                public void onClick() {
                    Log.i("aaaaaaaaaaa", "aaaaaaaaaaaaaaa");
                    Log.i("aaaaaaaaaaa", "position:" + helper.getLayoutPosition());
                    dataCallBack.callBack(v1, helper.getLayoutPosition(), list.get(helper.getLayoutPosition()));
                }
            }));

            // 为电话图标设置监听
            View v2 = helper.getView(R.id.iv_phone);
            addSubscribe(RxViewUtils.throttleFirstView(v2, new RxViewUtils.CallBack() {
                @Override
                public void onClick() {
                    Log.i("aaaaaaaaaaa", "aaaaaaaaaaaaaaa");
                    Log.i("aaaaaaaaaaa", "position:" + helper.getLayoutPosition());
                    dataCallBack.callBack(v2, helper.getLayoutPosition(), list.get(helper.getLayoutPosition()));
                }
            }));
            // 地图跳转区域
            View v3 = helper.getView(R.id.ll_loc);
            addSubscribe(RxViewUtils.throttleFirstView(v3, new RxViewUtils.CallBack() {
                @Override
                public void onClick() {
                    Log.i("aaaaaaaaaaa", "aaaaaaaaaaaaaaa");
                    Log.i("aaaaaaaaaaa", "position:" + helper.getLayoutPosition());
                    dataCallBack.callBack(v3, helper.getLayoutPosition(), list.get(helper.getLayoutPosition()));
                }
            }));


        } catch (Exception e) {
            e.printStackTrace();
//            LogUtils.e("方法异常：" + e.getMessage());
        }


    }
}
