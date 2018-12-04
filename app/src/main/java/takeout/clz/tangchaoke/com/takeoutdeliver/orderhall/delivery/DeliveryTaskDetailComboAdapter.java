package takeout.clz.tangchaoke.com.takeoutdeliver.orderhall.delivery;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

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
public class DeliveryTaskDetailComboAdapter extends MyBaseQuickAdapter<DeliveryTaskDetailDown.DataBean.DishesInfoBean> {
    Context context;
    List<DeliveryTaskDetailDown.DataBean.DishesInfoBean> dishesInfoBeanArrayList;
    public DeliveryTaskDetailComboAdapter(int layoutResId, @Nullable List<DeliveryTaskDetailDown.DataBean.DishesInfoBean> data) {
        super(layoutResId, data);
        this.dishesInfoBeanArrayList = data;
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DeliveryTaskDetailDown.DataBean.DishesInfoBean item) {
//        holder.mTvDishCount.setText("X" + dishesInfoBeanArrayList.getNumber());
//        holder.mTvDishName.setText(list.get(position).getDishes_name());
//        holder.mTvDishPrice.setText(mContext.getString(R.string.rmb) + StringUtils.format2Decimals(list.get(position).getDishes_price()));
//        holder.mLvComboList.setAdapter(new ComboItemAdapter(mContext, list.get(position).getDishes_combo()));

        helper.setText(R.id.tv_dish_count,"X" + item.getNumber());
        helper.setText(R.id.tv_dish_name,item.getDishes_name());
        helper.setText(R.id.tv_dish_price,mContext.getString(R.string.rmb) + CommStringUtils.format2Decimals(item.getDishes_price()));
//       helper.getView(R.id.lv_combo_list).set;
       RecyclerView lv= helper.getView(R.id.lv_combo_list);
       lv.setAdapter(new DeliveryTaskDetailComboItemAdapter(R.layout.item_deliverytaskdetailcomboitemadapter,item.getDishes_combo(),context));
    }
}
