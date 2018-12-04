package takeout.clz.tangchaoke.com.takeoutdeliver.orderhall.delivery;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import takeout.clz.tangchaoke.com.takeoutdeliver.R;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.MyBaseQuickAdapter;
import takeout.clz.tangchaoke.comm.toolutils.CommStringUtils;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.DeliveryTaskDetailDown;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/24
 */
public class DeliveryTaskDetailDishesAdapter extends MyBaseQuickAdapter<DeliveryTaskDetailDown.DataBean.DishesInfoBean> {
    Context context;
    List<DeliveryTaskDetailDown.DataBean.DishesInfoBean> dishesInfoBeanArrayList;

    public DeliveryTaskDetailDishesAdapter(int layoutResId, @Nullable List<DeliveryTaskDetailDown.DataBean.DishesInfoBean> data,Context context) {
        super(layoutResId, data);
        this.dishesInfoBeanArrayList = data;
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DeliveryTaskDetailDown.DataBean.DishesInfoBean item) {
        helper.setText(R.id.tv_name, item.getDishes_name());
        helper.setText(R.id.tv_num, "X" + item.getNumber());
        helper.setText(R.id.tv_price, context.getString(R.string.dollars) + CommStringUtils.format2Decimals(item.getDishes_price()));

    }
}
