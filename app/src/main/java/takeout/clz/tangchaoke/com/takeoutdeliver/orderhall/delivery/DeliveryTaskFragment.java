package takeout.clz.tangchaoke.com.takeoutdeliver.orderhall.delivery;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.leo.utils.SharedPreferencesUtils;
import com.leo.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import takeout.clz.tangchaoke.com.takeoutdeliver.R;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.MyBaseFragment;
import takeout.clz.tangchaoke.com.takeoutdeliver.comm.TaskNumber;
import takeout.clz.tangchaoke.data.dao.cache.DeliveryCache;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.DeliveryTaskDataDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.DeliveryTaskDown;
import takeout.clz.tangchaoke.data.dao.rx.RxBus;
import takeout.clz.tangchaoke.data.dao.tool.ParameterString;

public class DeliveryTaskFragment extends MyBaseFragment<DeliveryTaskPresenterImpl> implements DeliveryTaskContract.DeliveryTaskView, DeliveryTaskAdapter.DataCallBack, OnRefreshListener, OnLoadMoreListener {
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    DeliveryTaskAdapter deliveryTaskAdapter;
    ArrayList<DeliveryTaskDataDown.ListBean> deliveryTaskDownArrayList = new ArrayList<>();
    OnDataTransmissionListener mListener;
    String a_order_count;
    String b_order_count;


    public void initRecycleview() {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        deliveryTaskAdapter = new DeliveryTaskAdapter(R.layout.item_deliverytaskadapter, DeliveryCache.getOrders(), getActivity());
        addLifeObserver(deliveryTaskAdapter);
        deliveryTaskAdapter.setDataCallBack(this::callBack);
        recyclerView.setAdapter(deliveryTaskAdapter);
    }

    @Override
    protected void onVisibleToUser() {
        refresh();
    }

    public void refresh() {
       smartRefreshLayout.autoRefresh(200);
    }


    @Override
    protected void initDagger() {
        getComponent().inject(this);
    }

    @Override
    protected void initData() {
        smartRefreshLayout.setOnRefreshListener(this);
        smartRefreshLayout.setOnLoadMoreListener(this);
    }

    @Override
    protected void setupView() {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
        initRecycleview();
//        "lng" -> "117.249771" "province_idd" -> "2" "town_id" -> "57" "page" -> "1" "lat" -> "39.074064" "city_id" -> "45058" "order_class" -> "a"
//        getOrderList(1);
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_deliverytask;
    }


    @Override
    public void deliveryTaskListSuccess(DeliveryTaskDown deliveryTaskDown) {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
        if (deliveryTaskDown.getData().getList() == null) {
            return;
        }
        deliveryTaskDownArrayList = (ArrayList<DeliveryTaskDataDown.ListBean>) deliveryTaskDown.getData().getList();
        if (DeliveryCache.getOrders().size() == 0) {
            DeliveryCache.addOrder(deliveryTaskDownArrayList);
        } else {
            DeliveryCache.getOrders().addAll(DeliveryCache.getOrders().size(), deliveryTaskDownArrayList);
            for (int i = 0; i < DeliveryCache.getOrders().size(); i++) {
                for (int j = 0; j < i; j++) {
                    if (DeliveryCache.getOrders().get(i).equals(DeliveryCache.getOrders().get(j))) {
                        DeliveryCache.getOrders().remove(i);
                        //下标会减1
                        i = i - 1;
                        break;
                    }
                }
            }
        }
        a_order_count = deliveryTaskDown.getData().getCount().getA_order_logistic_number();
        b_order_count = deliveryTaskDown.getData().getCount().getB_order_logistic_number();
        if (mListener != null) {

            mListener.updateMainTabCount(a_order_count, b_order_count);
        }
        deliveryTaskAdapter.setNewData(DeliveryCache.getOrders());
        RxBus.getDefault().post(new TaskNumber("1", String.valueOf(DeliveryCache.getOrders().size())));
    }

    @Override
    public void deliveryTaskListFail(DeliveryTaskDown deliveryTaskDown) {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();

    }

    @Override
    public void deliveryTaskListError(Throwable throwable) {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();

    }

    @Override
    public void callBack(View view, int position, Object data) {
        switch (view.getId()) {
            case R.id.btn_get_order:
                ToastUtils.showToastShort(getActivity(), "aaaaaaaaaaaaaaaaa");
                break;
            case R.id.cardView:
//            DeliveryTaskDataDown.ListBean listBean = (DeliveryTaskDataDown.ListBean) data;
                String logistic_id=((DeliveryTaskDataDown.ListBean) data).getLogistic_id();
                String mctLat=((DeliveryTaskDataDown.ListBean) data).getMct_lat();
                String mctLng=((DeliveryTaskDataDown.ListBean) data).getMct_lng();
                String addressLat=((DeliveryTaskDataDown.ListBean) data).getAddress_lat();
                String addressLng=((DeliveryTaskDataDown.ListBean) data).getAddress_lng();
                Intent intent = new Intent(getActivity(),DeliveryTaskDetailActivity.class);
                intent.putExtra("logistic_id",logistic_id);
                intent.putExtra("is_navigate","0");
                intent.putExtra("mctLat",mctLat);
                intent.putExtra("mctLng",mctLng);
                intent.putExtra("addressLat",addressLat);
                intent.putExtra("addressLng",addressLng);
                startActivity(intent);
                break;
            default:
                break;
        }
    }


    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        DeliveryCache.getOrders().clear();
        deliveryTaskDownArrayList.clear();
        getOrderList(1);
    }

    public void getOrderList(int page) {
        presenter.getDeliveryTaskList(SharedPreferencesUtils.readStringFromSharedPreferences(getActivity(), ParameterString.CARRIAGE_ID), SharedPreferencesUtils.readStringFromSharedPreferences(getActivity(), ParameterString.TOKEN),
                "2", "45058", "57", "117.249771", "39.074064", "a", String.valueOf(page));
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        if (DeliveryCache.getOrders().size() != 0 && DeliveryCache.getOrders().size() % 12 == 0) {
            getOrderList(DeliveryCache.getOrders().size() / 12 + 1);
        } else if (DeliveryCache.getOrders().size() < 12) {
            smartRefreshLayout.finishLoadMore(false);
        } else {
            ToastUtils.showToastShort(getActivity(), "没有数据了！");
            smartRefreshLayout.finishLoadMore(1000);
        }
    }

    public interface OnDataTransmissionListener {
        void dataTransmission(String data);     //更新我的任务数量

        void updateMainTabCount(String a_order_count, String b_order_count);

        void updateMainFragmentTitle();
    }

    public void setOnDataTransmissionListener(OnDataTransmissionListener listener) {
        this.mListener = listener;
    }

}
