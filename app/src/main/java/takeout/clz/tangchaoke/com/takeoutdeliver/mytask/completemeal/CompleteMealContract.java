package takeout.clz.tangchaoke.com.takeoutdeliver.mytask.completemeal;

import takeout.clz.tangchaoke.data.dao.datadownbean.order.CompleteMealDown;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.BaseFragmentView;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.BasePresenter;

public interface CompleteMealContract {

    interface CompleteMealView extends BaseFragmentView{

        void completeMealSuccess(CompleteMealDown completeMealDown);

        void completeMealFail(CompleteMealDown completeMealDown);

        void completeMealError(Throwable throwable);
    }

    interface CompleteMealPresenter extends BasePresenter<CompleteMealView>{
        void getCompleteMealList(String carriage_id, String token, String page);
    }
}
