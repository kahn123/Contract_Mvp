package takeout.clz.tangchaoke.com.takeoutdeliver.orderhall.delivery;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import takeout.clz.tangchaoke.com.takeoutdeliver.R;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.MyBaseQuickAdapter;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.DeliveryTaskDetailDown;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/24
 */
public class DeliveryTaskDetailComboItemAdapter extends MyBaseQuickAdapter<DeliveryTaskDetailDown.DataBean.DishesInfoBean.DishesComboBean> {
    Context context;
    List<DeliveryTaskDetailDown.DataBean.DishesInfoBean.DishesComboBean> dishesComboBeanList;
    public DeliveryTaskDetailComboItemAdapter(int layoutResId, @Nullable List<DeliveryTaskDetailDown.DataBean.DishesInfoBean.DishesComboBean> data,Context context) {
        super(layoutResId, data);
        this.dishesComboBeanList =data;
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DeliveryTaskDetailDown.DataBean.DishesInfoBean.DishesComboBean item) {

        helper.setText(R.id.tv_dish_count,mContext.getString(R.string.multiply) + item.getNumber());
        helper.setText(R.id.tv_dish_name,item.getDishes_name());
    }
}
