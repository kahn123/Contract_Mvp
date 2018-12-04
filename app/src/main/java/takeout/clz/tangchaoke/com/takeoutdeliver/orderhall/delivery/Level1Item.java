package takeout.clz.tangchaoke.com.takeoutdeliver.orderhall.delivery;


import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by luoxw on 2016/8/10.
 */

public class Level1Item extends AbstractExpandableItem implements MultiItemEntity {
    public String number;
    public String dishesName;


    @Override
    public int getItemType() {
        return MissionHallOrderMoneyAdapter.TYPE_LEVEL_1;
    }

    @Override
    public int getLevel() {
        return 1;
    }
}
