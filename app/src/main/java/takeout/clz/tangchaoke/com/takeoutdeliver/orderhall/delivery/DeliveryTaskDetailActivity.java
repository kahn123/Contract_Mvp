package takeout.clz.tangchaoke.com.takeoutdeliver.orderhall.delivery;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.leo.utils.SharedPreferencesUtils;
import com.leo.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import takeout.clz.tangchaoke.com.takeoutdeliver.R;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.MyBaseActivity;
import takeout.clz.tangchaoke.com.takeoutdeliver.comm.MultiStateView;
import takeout.clz.tangchaoke.com.takeoutdeliver.mytask.MyTaskActivity;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.AcceptOrderDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.DeliveryTaskDetailDown;
import takeout.clz.tangchaoke.data.dao.rx.RxViewUtils;
import takeout.clz.tangchaoke.data.dao.tool.ParameterString;
import static takeout.clz.tangchaoke.comm.toolutils.CommStringUtils.getFormatStringZero;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/23
 */
public class DeliveryTaskDetailActivity extends MyBaseActivity<DeliveryTaskDetailPresenterImpl> implements DeliveryTaskDetailContract.DeliveryTaskDetailView, MultiStateView.StateListener {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.mStateView)
    MultiStateView mStateView;
    @BindView(R.id.tv_empty_info)
    TextView mTvEmptyInfo;
    @BindView(R.id.tv_status_error)
    TextView mTvStatusError;
    @BindView(R.id.btn_take_order)
    Button btnTakeOrder;
    @BindView(R.id.tv_arrive_time)
    TextView tv_arrive_time;            //送达时间
    @BindView(R.id.tv_income)
    TextView tv_income;                 //收入
    @BindView(R.id.tv_mct_name)
    TextView tv_mct_name;               //取货地址名称
    @BindView(R.id.tv_mct_address)
    TextView tv_mct_address;            //取货地址
    @BindView(R.id.tv_consignee)
    TextView tv_consignee;              //收货地址名称
    @BindView(R.id.tv_address)
    TextView tv_address;                //收货地址
    @BindView(R.id.rv_dishes)                 //菜品集合
    RecyclerView rv_dishes;
    @BindView(R.id.lv_dishes_combo)           //套餐集合 listView
     RecyclerView rv_dishes_combo;
    @BindView(R.id.tv_expect_arrive_time)
    TextView tv_expect_arrive_time;   //预计送达时间
    @BindView(R.id.tv_attach_service)         //附加服务按钮
    ImageView tv_attach_service;
    @BindView(R.id.rl_attach_service)
    RelativeLayout rl_attach_service;
    @BindView(R.id.rv_attach_service)         //附加服务
    RecyclerView rv_attach_service;
    @BindView(R.id.tv_navigation)             //地图导航
    TextView tv_navigation;
    @BindView(R.id.ll_order_detail)
     LinearLayout ll_order_detail;
    @BindView(R.id.tv_store_name)
     TextView tv_store_name;
    @BindView(R.id.tv_box_count)
     TextView tv_box_count; // 餐箱数量

    String logistic_id;
    String is_navigate;
    String mctLat;
    String mctLng;
    String addressLat;
    String addressLng;
    String order_class;
    MissionHallOrderMoneyAdapter missionHallOrderMoneyAdapter;
    ArrayList<MultiItemEntity> list = new ArrayList<>() ;
    @Override
    protected void initDagger() {
        getComponent().inject(this);
    }

    @Override
    protected void initData() {
        if (mStateView != null) {
            mStateView.setStateListener(this);
        }
        logistic_id = getIntent().getStringExtra("logistic_id");
        is_navigate = getIntent().getStringExtra("is_navigate");
        mctLat = getIntent().getStringExtra("mctLat");
        mctLng = getIntent().getStringExtra("mctLng");
        addressLat = getIntent().getStringExtra("addressLat");
        addressLng = getIntent().getStringExtra("addressLng");
        getDetail();
//
        rv_dishes.setItemAnimator(new DefaultItemAnimator());
        missionHallOrderMoneyAdapter = new MissionHallOrderMoneyAdapter(list);
        addLifeObserver(missionHallOrderMoneyAdapter);
        rv_dishes.setAdapter(missionHallOrderMoneyAdapter);
        rv_dishes.setLayoutManager((new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false)));
        missionHallOrderMoneyAdapter.expandAll();












        RxViewUtils.throttleFirstView(ivBack, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
                finish();
            }
        });

        RxViewUtils.throttleFirstView(btnTakeOrder, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
                getAccept();
            }
        });

    }

    @Override
    protected void setupView() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_deliverytaskdetail;
    }

    @Override
    public void deliveryTaskSuccess(DeliveryTaskDetailDown deliveryTaskDetailDown) {
        mStateView.setViewState(MultiStateView.STATE_CONTENT);
        order_class = deliveryTaskDetailDown.getData().getOrder_class();

        if (TextUtils.isEmpty(deliveryTaskDetailDown.getData().box_count)) {
            tv_box_count.setText(getFormatStringZero(String.format(getString(R.string.box_count), "1")));
        } else {
            tv_box_count.setText(getFormatStringZero(String.format(getString(R.string.box_count), deliveryTaskDetailDown.getData().box_count)));
        }
        // 根据订单类型填写取货收货地址
        if (deliveryTaskDetailDown.getData().getOrder_class().equals("a")) {
            tv_mct_name.setText(deliveryTaskDetailDown.getData().getMct_name());                // 店铺名称
            tv_mct_address.setText(deliveryTaskDetailDown.getData().getMct_address_detail());   // 店铺地址
            tv_consignee.setText(deliveryTaskDetailDown.getData().getConsignee());              // 收货人名称
            tv_address.setText(deliveryTaskDetailDown.getData().getUser_address_detail());                  // 收货人地址
            tv_expect_arrive_time.setText(deliveryTaskDetailDown.getData().member_delivery_time);
            tv_arrive_time.setText(deliveryTaskDetailDown.getData().pei_delivery_time); // 送达时间
        } else if (deliveryTaskDetailDown.getData().getOrder_class().equals("b")) {
            tv_mct_name.setText(deliveryTaskDetailDown.getData().getConsignee());
            tv_mct_address.setText(deliveryTaskDetailDown.getData().getUser_address_detail());
            tv_consignee.setText(deliveryTaskDetailDown.getData().getMct_name());
            tv_address.setText(deliveryTaskDetailDown.getData().getMct_address_detail());
            tv_arrive_time.setText(deliveryTaskDetailDown.getData().member_delivery_time); // 送达时间
            tv_expect_arrive_time.setText(deliveryTaskDetailDown.getData().pei_delivery_time);
        }
        tv_store_name.setText(deliveryTaskDetailDown.getData().getMct_name());
//        if (deliveryTaskDetailDown.getData().getDishes_info() != null && deliveryTaskDetailDown.getData().getDishes_info().size() > 0) {
////            initRecyclerView(deliveryTaskDetailDown.getData().getDishes_info());
//            for (int i = 0; i <deliveryTaskDetailDown.getData().getDishes_info().size() ; i++) {
//                Level0Item level0Item =  new Level0Item();
//                level0Item.number=deliveryTaskDetailDown.getData().getDishes_info().get(i).getNumber();
//                level0Item.dishesName=deliveryTaskDetailDown.getData().getDishes_info().get(i).getDishes_name();
//                level0Item.dishesPrice=deliveryTaskDetailDown.getData().getDishes_info().get(i).getDishes_price();
//                Level1Item level1Item = new Level1Item();
//                if(deliveryTaskDetailDown.getData().getDishes_info().get(i).getDishes_class().equals("2")){
//                    level1Item.dishesName = deliveryTaskDetailDown.getData().getDishes_info().get(i).getDishes_combo().get(i).getDishes_name();
//                    level1Item.number = deliveryTaskDetailDown.getData().getDishes_info().get(i).getDishes_combo().get(i).getNumber();
//                    level0Item.addSubItem(level1Item);
//                    list.add(level0Item);
//                }else{
//
//                }
//
//                list.add(level0Item);
//            }


            List<DeliveryTaskDetailDown.DataBean.DishesInfoBean> dishesList = new ArrayList<>(); // 普通菜品
            List<DeliveryTaskDetailDown.DataBean.DishesInfoBean> dishesComboList = new ArrayList<>(); // 套餐
            Level0Item level0Item =  new Level0Item();
            Level1Item level1Item = new Level1Item();
            for (int i = 0; i < deliveryTaskDetailDown.getData().getDishes_info().size(); i++) {
                if ("1".equals(deliveryTaskDetailDown.getData().getDishes_info().get(i).getDishes_class())) {
//                    dishesList.add(deliveryTaskDetailDown.getData().getDishes_info().get(i));
                    level0Item.number=deliveryTaskDetailDown.getData().getDishes_info().get(i).getNumber();
                    level0Item.dishesName=deliveryTaskDetailDown.getData().getDishes_info().get(i).getDishes_name();
                    level0Item.dishesPrice=deliveryTaskDetailDown.getData().getDishes_info().get(i).getDishes_price();
//                    list.add(level0Item);
                } else if ("2".equals(deliveryTaskDetailDown.getData().getDishes_info().get(i).getDishes_class())) {
//                    dishesComboList.add(deliveryTaskDetailDown.getData().getDishes_info().get(i));
                    dishesList=  deliveryTaskDetailDown.getData().getDishes_info();
                    dishesList.get(0).getDishes_combo().size();
                    level1Item.dishesName = deliveryTaskDetailDown.getData().getDishes_info().get(i).getDishes_combo().get(i).getDishes_name();
                    level1Item.number = deliveryTaskDetailDown.getData().getDishes_info().get(i).getDishes_combo().get(i).getNumber();
                    level0Item.addSubItem(level1Item);

                }
                list.add(level0Item);


                missionHallOrderMoneyAdapter.setNewData(list);
                missionHallOrderMoneyAdapter.expandAll();
            }

//        rv_orderDetail.setNestedScrollingEnabled(false);
//            final GridLayoutManager manager = new GridLayoutManager(this, 1);
//            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                @Override
//                public int getSpanSize(int position) {
//                    return missionHallOrderMoneyAdapter.getItemViewType(position) == MissionHallOrderMoneyAdapter.TYPE_LEVEL_1 ? 1 : manager.getSpanCount();
//                }
//            });
//            rv_orderDetail.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));





        }


    // 展示菜品的数据
    private void initRecyclerView(List<DeliveryTaskDetailDown.DataBean.DishesInfoBean> data) {

        List<DeliveryTaskDetailDown.DataBean.DishesInfoBean> dishesList = new ArrayList<>(); // 普通菜品
        List<DeliveryTaskDetailDown.DataBean.DishesInfoBean> dishesComboList = new ArrayList<>(); // 套餐

        for (int i = 0; i < data.size(); i++) {
            if ("1".equals(data.get(i).getDishes_class())) {
                dishesList.add(data.get(i));
            } else if ("2".equals(data.get(i).getDishes_class())) {
                dishesComboList.add(data.get(i));
            }
        }
        DeliveryTaskDetailDishesAdapter mAdapter = new DeliveryTaskDetailDishesAdapter(R.layout.item_deliverytaskdetaildishesadapter,dishesList,DeliveryTaskDetailActivity.this);
//        rv_dishes.setAdapter(mAdapter);
//        rv_dishes.setLayoutManager(new LinearLayoutManager(this));
//        lv_dishes_combo.setAdapter(new ComboAdapter(this, dishesComboList));
        //nestedScrollview 滑动惯性
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);

        rv_dishes.setLayoutManager(layoutManager);
        rv_dishes.setHasFixedSize(true);
        rv_dishes.setNestedScrollingEnabled(false);
        rv_dishes.setAdapter(mAdapter);
        DeliveryTaskDetailComboAdapter deliveryTaskDetailComboAdapter = new DeliveryTaskDetailComboAdapter(R.layout.item_deliverytaskdetailcomboadapter, dishesComboList);
        rv_dishes_combo.setAdapter(deliveryTaskDetailComboAdapter);


    }



    @Override
    public void deliveryTaskFail(DeliveryTaskDetailDown deliveryTaskDetailDown) {
        mStateView.setViewState(MultiStateView.STATE_EMPTY);
        mTvEmptyInfo.setText("本订单无详情！");
        ToastUtils.showToastShort(DeliveryTaskDetailActivity.this, deliveryTaskDetailDown.getMessage());
    }

    @Override
    public void deliveryTaskError(Throwable throwable) {
        ToastUtils.showToastShort(DeliveryTaskDetailActivity.this, throwable.getMessage());
    }

    @Override
    public void acceptOrderSuccess(AcceptOrderDown acceptOrderDown) {
        startActivity(new Intent(DeliveryTaskDetailActivity.this, MyTaskActivity.class));
        finish();
    }

    @Override
    public void acceptOrderFail(AcceptOrderDown acceptOrderDown) {
        ToastUtils.showToastShort(DeliveryTaskDetailActivity.this, acceptOrderDown.getMessage());
    }

    @Override
    public void acceptOrderError(Throwable throwable) {
        ToastUtils.showToastShort(DeliveryTaskDetailActivity.this, throwable.getMessage());
    }
    //订单详情
    public void getDetail() {
        presenter.getDeliveryTaskDetail(logistic_id, SharedPreferencesUtils.readStringFromSharedPreferences(DeliveryTaskDetailActivity.this, ParameterString.CARRIAGE_ID),
                SharedPreferencesUtils.readStringFromSharedPreferences(DeliveryTaskDetailActivity.this, ParameterString.TOKEN));
    }
    //接单
    public void getAccept() {
        presenter.getAcceptOrder(logistic_id, SharedPreferencesUtils.readStringFromSharedPreferences(DeliveryTaskDetailActivity.this, ParameterString.CARRIAGE_ID),
                SharedPreferencesUtils.readStringFromSharedPreferences(DeliveryTaskDetailActivity.this, ParameterString.TOKEN));
    }

    @Override
    public void onStateChanged(int viewState) {
        switch (viewState) {
            case MultiStateView.STATE_EMPTY:
                break;
            case MultiStateView.STATE_CONTENT:
                break;
            case MultiStateView.STATE_UNKNOWN:
                break;
            case MultiStateView.STATE_ERROR:
                break;
            case MultiStateView.STATE_LOADING:
                break;
            default:
                break;
        }
    }
}
