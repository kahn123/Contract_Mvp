package takeout.clz.tangchaoke.com.takeoutdeliver.orderhall.delivery;


import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by luoxw on 2016/8/10.
 */
public class Level0Item extends AbstractExpandableItem<Level1Item> implements MultiItemEntity {
   public String number;
     String dishesName;
     String dishesPrice;
     List<Level1Item> list;

//    public Level0Item( String title, String subTitle) {
//        this.subTitle = subTitle;
//        this.title = title;
//    }

    @Override
    public int getItemType() {
        return MissionHallOrderMoneyAdapter.TYPE_LEVEL_0;
    }

    @Override
    public int getLevel() {
        return 0;
    }
}
