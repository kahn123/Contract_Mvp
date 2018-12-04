package takeout.clz.tangchaoke.com.takeoutdeliver.orderhall.recovery;

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
import takeout.clz.tangchaoke.data.dao.cache.RecoveryCache;
import takeout.clz.tangchaoke.data.dao.rx.RxBus;
import takeout.clz.tangchaoke.com.takeoutdeliver.R;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.MyBaseFragment;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.RecoveryTaskDataDown;
import takeout.clz.tangchaoke.data.dao.datadownbean.order.RecoveryTaskDown;
import takeout.clz.tangchaoke.data.dao.tool.ParameterString;
import takeout.clz.tangchaoke.com.takeoutdeliver.comm.TaskNumber;

public class RecoveryTaskFragment extends MyBaseFragment<RecoveryTaskPresenterImpl> implements RecoveryTaskContract.RecoveryTaskView, RecoveryTaskAdapter.DataCallBack, OnRefreshListener, OnLoadMoreListener {
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    RecoveryTaskAdapter recoveryTaskAdapter;
    ArrayList<RecoveryTaskDataDown.ListBean> recoveryTaskDownArrayList = new ArrayList<>();
    OnDataTransmissionListener mListener;
    String a_order_count;
    String b_order_count;



    @Override
    protected void initDagger() {
        getComponent().inject(this);
    }

    @Override
    protected void initData() {
        smartRefreshLayout.setOnRefreshListener(this);
        smartRefreshLayout.setOnLoadMoreListener(this);
    }

    public void initRecycleview() {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recoveryTaskAdapter = new RecoveryTaskAdapter(R.layout.item_recoverytaskadapter, RecoveryCache.getOrders(), getActivity());
        addLifeObserver(recoveryTaskAdapter);
        recoveryTaskAdapter.setDataCallBack(this::callBack);
        recyclerView.setAdapter(recoveryTaskAdapter);
    }




    public void refresh() {
        smartRefreshLayout.autoRefresh(200);
    }

    @Override
    protected void setupView() {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
        initRecycleview();
//        "lng" -> "117.249771" "province_idd" -> "2" "town_id" -> "57" "page" -> "1" "lat" -> "39.074064" "city_id" -> "45058" "order_class" -> "a"
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_recoverytask;
    }

    @Override
    public void recoveryTaskListSuccess(RecoveryTaskDown recoveryTaskDown) {

        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
        if( recoveryTaskDown.getData().getList()==null){
            return;
        }
        recoveryTaskDownArrayList = (ArrayList<RecoveryTaskDataDown.ListBean>) recoveryTaskDown.getData().getList();
        if (RecoveryCache.getOrders().size() == 0) {
            RecoveryCache.addOrder(recoveryTaskDownArrayList);
        } else {
            RecoveryCache.getOrders().addAll(RecoveryCache.getOrders().size(), recoveryTaskDownArrayList);
            for (int i = 0; i < RecoveryCache.getOrders().size(); i++) {
                for (int j = 0; j < i; j++) {
                    if (RecoveryCache.getOrders().get(i).equals(RecoveryCache.getOrders().get(j))) {
                        RecoveryCache.getOrders().remove(i);
                        //下标会减1
                        i = i - 1;
                        break;
                    }
                }
            }
        }
        a_order_count=recoveryTaskDown.getData().getCount().getA_order_logistic_number();
        b_order_count=recoveryTaskDown.getData().getCount().getB_order_logistic_number();
        if (mListener != null) {
//            mListener.dataTransmission(myTaskNum);
            mListener.updateMainTabCount(a_order_count, b_order_count);
        }
        recoveryTaskAdapter.setNewData(RecoveryCache.getOrders());
        RxBus.getDefault().post(new TaskNumber("2",String.valueOf(RecoveryCache.getOrders().size())));
    }

    @Override
    public void recoveryTaskListFail(RecoveryTaskDown recoveryTaskDown) {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();

    }

    @Override
    public void recoveryTaskListError(Throwable throwable) {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();

    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        if (RecoveryCache.getOrders().size() != 0 && RecoveryCache.getOrders().size() % 12 == 0) {
            getOrderList(RecoveryCache.getOrders().size() / 12 + 1);
        } else if (RecoveryCache.getOrders().size() < 12) {
            smartRefreshLayout.finishLoadMore(false);
        } else {
            ToastUtils.showToastShort(getActivity(), "没有数据了！");
            smartRefreshLayout.finishLoadMore(0);
        }

    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        RecoveryCache.getOrders().clear();
        recoveryTaskDownArrayList.clear();
        getOrderList(1);
    }

    @Override
    public void callBack(View view, int position, Object data) {
        switch (view.getId()) {
            case R.id.btn_get_order:
                ToastUtils.showToastShort(getActivity(), "aaaaaaaaaaaaaaaaa");
                break;
            case R.id.cardView:
                String logistic_id=((RecoveryTaskDataDown.ListBean) data).getLogistic_id();
                String mctLat=((RecoveryTaskDataDown.ListBean) data).getMct_lat();
                String mctLng=((RecoveryTaskDataDown.ListBean) data).getMct_lng();
                String addressLat=((RecoveryTaskDataDown.ListBean) data).getAddress_lat();
                String addressLng=((RecoveryTaskDataDown.ListBean) data).getAddress_lng();
                Intent intent = new Intent(getActivity(),RecoveryTaskDetailActivity.class);
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

    public void getOrderList(int page) {
        presenter.getRecoveryTaskList(SharedPreferencesUtils.readStringFromSharedPreferences(getActivity(), ParameterString.CARRIAGE_ID), SharedPreferencesUtils.readStringFromSharedPreferences(getActivity(), ParameterString.TOKEN),
                "2", "45058", "57", "117.249771", "39.074064", "b", String.valueOf(page));
    }

    public interface OnDataTransmissionListener {
        void dataTransmission(String data);     //更新我的任务数量

        void updateMainTabCount(String a_order_count, String b_order_count);
    }

    public void setOnDataTransmissionListener(OnDataTransmissionListener listener) {
        this.mListener = listener;
    }



}
