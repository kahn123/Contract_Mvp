package takeout.clz.tangchaoke.com.takeoutdeliver.mytask.completemeal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import takeout.clz.tangchaoke.comm.toolutils.CommStringUtils;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.CompleteMealDown;
import takeout.clz.tangchaoke.data.dao.rx.RxViewUtils;
import takeout.clz.tangchaoke.com.takeoutdeliver.R;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.MyBaseQuickAdapter;

public class CompleteMealAdapter extends MyBaseQuickAdapter<CompleteMealDown.DataBean.ListBean> {

    Context context;
    List<CompleteMealDown.DataBean.ListBean> list;
    DataCallBack dataCallBack;

    public void setDataCallBack(DataCallBack dataCallBack) {
        this.dataCallBack = dataCallBack;
    }

    public interface DataCallBack {
        void callBack(View view, int position, Object data);
    }


    public CompleteMealAdapter(int layoutResId, @Nullable List<CompleteMealDown.DataBean.ListBean> data,Context context) {
        super(layoutResId, data);
        this.list = data;
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, CompleteMealDown.DataBean.ListBean item) {
        try {
            if (item.getOrder_class().equals("a")) {
                // 店铺名称
                helper.setText(R.id.tv_mct_name,item.getMct_name());
                helper.getView(R.id.iv_order_class).setVisibility(View.VISIBLE);
                helper.getView(R.id.iv_order_class).setBackgroundResource(R.mipmap.bill_song);
                // 取货地址
                helper.setText(R.id.tv_send_address,item.getMct_address());
                // 收货地址
                helper.setText(R.id.tv_get_address,item.getAddress());

            } else if (item.getOrder_class().equals("b")) {
                helper.setText(R.id.tv_mct_name,item.getConsignee());
                helper.getView(R.id.iv_order_class).setVisibility(View.VISIBLE);
                helper.getView(R.id.iv_order_class).setBackgroundResource(R.mipmap.bill_shou);
                helper.setText(R.id.tv_send_address,item.getAddress());
                helper.setText(R.id.tv_get_address,item.getMct_address());
            }
//            TextView box_count = viewHolder.getTextView(R.id.tv_box_count);
            if (TextUtils.isEmpty(item.box_count)) {
                helper.setText(R.id.tv_box_count,String.format(context.getString(R.string.box_count), "1"));
//                box_count.setText(String.format(context.getString(R.string.box_count), "1"));
            } else {
                helper.setText(R.id.tv_box_count,String.format(context.getString(R.string.box_count), item.box_count));
//                box_count.setText(String.format(context.getString(R.string.box_count), item.box_count));
            }
            CharSequence formatString = CommStringUtils.getFormatCharSequence("一口价：¥" + CommStringUtils.format2Decimals(item.getDelivery_fee()),context);
//            viewHolder.getTextView(R.id.tv_income).setText(formatString);
            helper.setText(R.id.tv_income,formatString);
            // 收入
//            if(item.getDelivery_class().equals("2")){
//                viewHolder.getTextView(R.id.tv_income).setText("商家配送");
//                viewHolder.getTextView(R.id.tv_income).setTextColor(Color.YELLOW);
//            }else{
//                CharSequence formatString = StringUtils.getFormatCharSequence("一口价：¥" + StringUtils.format2Decimals(item.getDelivery_fee()));
//                viewHolder.getTextView(R.id.tv_income).setText(formatString);
//            }

            if (!TextUtils.isEmpty(item.getAttach_service_content())) {
                helper.setText(R.id.tv_demand,item.getAttach_service_content());
//                viewHolder.getTextView(R.id.tv_demand).setText(item.getAttach_service_content());
            } else {
                helper.setText(R.id.tv_demand,"无");
//                viewHolder.getTextView(R.id.tv_demand).setText("无");
            }
            if (item.getIs_park().equals("0") || item.getIs_park().equals("2")) {
                helper.setText(R.id.tv_is_park,"不可进车");
//                viewHolder.getTextView(R.id.tv_is_park).setText("不可进车");
            } else if (item.getIs_park().equals("1")) {
                helper.setText(R.id.tv_is_park,"可进车");
//                viewHolder.getTextView(R.id.tv_is_park).setText("可进车");
            }
            if (item.getIs_lift().equals("0") || item.getIs_lift().equals("2")) {
                helper.setText(R.id.tv_is_lift,"无电梯");
//                viewHolder.getTextView(R.id.tv_is_lift).setText("无电梯");
            } else if (item.getIs_lift().equals("1")) {
                helper.setText(R.id.tv_is_lift,"有电梯");
//                viewHolder.getTextView(R.id.tv_is_lift).setText("有电梯");
            }
            // order_id
//            viewHolder.getTextView(R.id.tv_order_id).setText("order_id = " + item.getOrder_id());
            helper.setText(R.id.tv_order_id,"order_id = " + item.getOrder_id());
            // 电话icon隐藏
            helper.getView(R.id.iv_phone).setVisibility(View.GONE);
//            viewHolder.getImageView(R.id.iv_phone).setVisibility(View.GONE);
//            LinearLayout ll_loc = (LinearLayout) viewHolder.getView(R.id.ll_loc);
//            ll_loc.setOnClickListener(viewHolder);
            // 地图跳转区域
            View v1 = helper.getView(R.id.ll_loc);
            addSubscribe(RxViewUtils.throttleFirstView(v1, new RxViewUtils.CallBack() {
                @Override
                public void onClick() {
                    Log.i("aaaaaaaaaaa", "aaaaaaaaaaaaaaa");
                    Log.i("aaaaaaaaaaa", "position:" + helper.getLayoutPosition());
                    dataCallBack.callBack(v1, helper.getLayoutPosition(), list.get(helper.getLayoutPosition()));
                }
            }));

        } catch (Exception e) {
            e.printStackTrace();
//            LogUtils.e("方法异常：" + e.getMessage());
        }
    }
}
