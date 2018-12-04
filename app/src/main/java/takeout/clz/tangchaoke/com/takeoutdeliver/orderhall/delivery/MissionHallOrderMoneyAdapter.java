package takeout.clz.tangchaoke.com.takeoutdeliver.orderhall.delivery;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

import takeout.clz.tangchaoke.com.takeoutdeliver.R;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.MyBaseMultiItemQuickAdapter;
import takeout.clz.tangchaoke.comm.toolutils.CommStringUtils;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/24
 */
public class MissionHallOrderMoneyAdapter extends MyBaseMultiItemQuickAdapter<MultiItemEntity> {
    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;

    public MissionHallOrderMoneyAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.item_deliverytaskdetaildishesadapter);
        addItemType(TYPE_LEVEL_1, R.layout.item_deliverytaskdetailcomboitemadapter);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_LEVEL_0:
                final Level0Item lv0 = (Level0Item) item;
                helper.setText(R.id.tv_num,((Level0Item) item).number);
                helper.setText(R.id.tv_name,((Level0Item) item).dishesName);
                helper.setText(R.id.tv_price,mContext.getString(R.string.rmb) + CommStringUtils.format2Decimals(((Level0Item) item).dishesPrice));
//                        .setImageResource(R.id.iv, lv0.isExpanded() ? R.mipmap.arrow_b : R.mipmap.arrow_r);
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = helper.getAdapterPosition();
                        Log.d(TAG, "Level 0 item pos: " + pos);
                        if (lv0.isExpanded()) {
                            collapse(pos);
                        } else {
//                            if (pos % 3 == 0) {
//                                expandAll(pos, false);
//                            } else {
                            expand(pos);
//                            }
                        }
                    }
                });
                break;
            case TYPE_LEVEL_1:
                final Level1Item lv1 = (Level1Item) item;
                helper.setText(R.id.tv_dish_count,mContext.getString(R.string.multiply) + ((Level1Item) item).number);
                helper.setText(R.id.tv_dish_name,((Level1Item) item).dishesName);
//                        .setImageResource(R.id.iv, lv1.isExpanded() ? R.mipmap.arrow_b : R.mipmap.arrow_r);
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = helper.getAdapterPosition();
                        Log.d(TAG, "Level 1 item pos: " + pos);
                        if (lv1.isExpanded()) {
                            collapse(pos, false);
                        } else {
                            expand(pos, false);
                        }
                    }
                });

                helper.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = helper.getAdapterPosition();
                        remove(pos);
                        return true;
                    }
                });
                break;
            default:
                break;
        }
    }
}

