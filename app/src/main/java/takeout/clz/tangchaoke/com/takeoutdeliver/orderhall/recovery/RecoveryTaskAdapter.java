package takeout.clz.tangchaoke.com.takeoutdeliver.orderhall.recovery;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;
import takeout.clz.tangchaoke.comm.toolutils.CommStringUtils;
import takeout.clz.tangchaoke.com.takeoutdeliver.R;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.MyBaseQuickAdapter;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.RecoveryTaskDataDown;
import takeout.clz.tangchaoke.data.dao.rx.RxViewUtils;

public class RecoveryTaskAdapter extends MyBaseQuickAdapter<RecoveryTaskDataDown.ListBean> {
    Context context;
    List<RecoveryTaskDataDown.ListBean> list;
    DataCallBack dataCallBack;

    public void setDataCallBack(DataCallBack dataCallBack) {
        this.dataCallBack = dataCallBack;
    }

    public interface DataCallBack {
        void callBack(View view, int position, Object data);
    }

    public RecoveryTaskAdapter(int layoutResId, @Nullable List<RecoveryTaskDataDown.ListBean> data, Context context) {
        super(layoutResId, data);
        this.list = data;
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, RecoveryTaskDataDown.ListBean item) {
        helper.setText(R.id.tv_mct_name, item.getMct_name());
        if (item.getOrder_class().equals("a")) {
            helper.setText(R.id.tv_mct_name, item.getMct_name());               // 店铺名称
        } else if (item.getOrder_class().equals("b")) {
            helper.setText(R.id.tv_mct_name, item.getConsignee());              // 客户名称
        }
        if (item.getDelivery_class().equals("2")) {
            helper.setText(R.id.tv_income, "商家配送");
            helper.setTextColor(R.id.tv_income, Color.YELLOW);
        } else {
            CharSequence formatString = CommStringUtils.getFormatCharSequence("一口价：¥" + CommStringUtils.format2Decimals(item.getDelivery_fee()),context);
            helper.setText(R.id.tv_income,formatString);
        }
        helper.getView(R.id.iv_phone).setVisibility(View.GONE);
        // 取货距离
        helper.setText(R.id.tv_mine_distance,item.getA_distance() + "km");
        // 取货地址
        helper.setText(R.id.tv_send_address,item.getMct_address());
        // 收货距离
        helper.setText(R.id.tv_get_distance,item.getB_distance() + "km");
        // 收货地址
        helper.setText(R.id.tv_get_address,item.getAddress());
        // 取货到达时间
        helper.setText(R.id.tv_get_time,item.pei_delivery_time);
        // 送达时间
        helper.setText(R.id.tv_arrive_time,item.member_delivery_time);

        helper.setText(R.id.tv_box_count,item.member_delivery_time);

        if (TextUtils.isEmpty(item.box_count)) {
            helper.setText(R.id.tv_box_count,String.format(context.getString(R.string.box_count), "1"));
//            box_count.setText(String.format(context.getString(R.string.box_count), "1"));
        } else {
//            box_count.setText(String.format(context.getString(R.string.box_count), item.box_count));
            helper.setText(R.id.tv_box_count,String.format(context.getString(R.string.box_count), item.box_count));
        }


        if (!TextUtils.isEmpty(item.getAttach_service_content())) {
//            viewHolder.getTextView(R.id.tv_demand).setText(item.getAttach_service_content());
            helper.setText(R.id.tv_demand,item.getAttach_service_content());
        } else {
//            viewHolder.getTextView(R.id.tv_demand).setText("无");
            helper.setText(R.id.tv_demand,"无");
        }
        if (item.getIs_park().equals("0") || item.getIs_park().equals("2")) {
            helper.setText(R.id.tv_is_park,"不可进车");
//            viewHolder.getTextView(R.id.tv_is_park).setText("不可进车");
        } else if (item.getIs_park().equals("1")) {
            helper.setText(R.id.tv_is_park,"可进车");
//            viewHolder.getTextView(R.id.tv_is_park).setText("可进车");
        }
        if (item.getIs_lift().equals("0") || item.getIs_lift().equals("2")) {
            helper.setText(R.id.tv_is_lift,"无电梯");
//            viewHolder.getTextView(R.id.tv_is_lift).setText("无电梯");
        } else if (item.getIs_lift().equals("1")) {
            helper.setText(R.id.tv_is_lift,"有电梯");
//            viewHolder.getTextView(R.id.tv_is_lift).setText("有电梯");
        }
        helper.setText(R.id.tv_order_id,"order_id = " + item.getOrder_id());
//        viewHolder.getTextView(R.id.tv_order_id).setText("order_id = " + item.getOrder_id());
        helper.getView(R.id.btn_get_order).setVisibility(View.VISIBLE);
//        viewHolder.getButton(R.id.btn_get_order).setVisibility(View.VISIBLE);                       // 接单按钮
//        viewHolder.getButton(R.id.btn_get_order).setOnClickListener(viewHolder);
        View v1 = helper.getView(R.id.btn_get_order);
        addSubscribe(RxViewUtils.throttleFirstView(v1, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
                Log.i("aaaaaaaaaaa", "aaaaaaaaaaaaaaa");
                Log.i("aaaaaaaaaaa", "position:" + helper.getLayoutPosition());
                dataCallBack.callBack(v1, helper.getLayoutPosition(), list.get(helper.getLayoutPosition()));
            }
        }));

        View v2 = helper.getView(R.id.cardView);
        addSubscribe(RxViewUtils.throttleFirstView(v2, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
                Log.i("aaaaaaaaaaa", "aaaaaaaaaaaaaaa");
                Log.i("aaaaaaaaaaa", "position:" + helper.getLayoutPosition());
                dataCallBack.callBack(v2, helper.getLayoutPosition(), list.get(helper.getLayoutPosition()));
            }
        }));


    }
}

