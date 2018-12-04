package takeout.clz.tangchaoke.com.takeoutdeliver.user.setting.account;

import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.kongzue.dialog.listener.OnMenuItemClickListener;
import com.kongzue.dialog.v2.BottomMenu;
import com.leo.utils.SharedPreferencesUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import butterknife.BindView;
import takeout.clz.tangchaoke.com.takeoutdeliver.R;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.MyBaseActivity;
import takeout.clz.tangchaoke.comm.commutils.PictureConverter;
import takeout.clz.tangchaoke.com.takeoutdeliver.comm.PermissionUtils;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.PhotoDown;
import takeout.clz.tangchaoke.data.dao.rx.RxViewUtils;
import takeout.clz.tangchaoke.data.dao.tool.ParameterString;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/9/14
 */
public class AccountInfoActivity extends MyBaseActivity<AccountInfoPresenterImpl> implements AccountInfoContract.AccountInfoView {
    @BindView(R.id.sdv_head)
    de.hdodenhof.circleimageview.CircleImageView sdvHead;
    @BindView(R.id.iv_back)
            ImageView ivBack;
    List<LocalMedia> selectList = new ArrayList<>();
    File file1;

    @Override
    protected void initDagger() {
        getComponent().inject(this);
    }

    @Override
    protected void initData() {
        RxViewUtils.throttleFirstView(ivBack, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
                finish();
            }
        });
        RxViewUtils.throttleFirstView(sdvHead, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
                selectDcimOrCamera();

            }
        });
    }

    /**
     * 打开相机相册
     */
    public void selectDcimOrCamera() {
        List<String> list = new ArrayList<>();
        list.add("相册");
        list.add("相机");
        BottomMenu.show(AccountInfoActivity.this, list, new OnMenuItemClickListener() {
            @Override
            public void onClick(String text, int index) {
                if (index == 0) {
                    checkPermissionDcim();

                } else if (index == 1) {
                    checkPermissionCamera();
                }
            }
        }, true).setTitle("这里是标题测试").setCustomView(customView);
    }

    /**
     * 打开相册
     */
    public void openDcim() {
        PictureSelector.create(AccountInfoActivity.this)
                .openGallery(PictureMimeType.ofImage())
                .theme(R.style.picture_default_style)
                .maxSelectNum(1)
                // 每行显示个数
                .imageSpanCount(4)
                .selectionMode(PictureConfig.SINGLE)
                .previewImage(true)
                // 拍照保存图片格式后缀,默认jpeg
                //.imageFormat(PictureMimeType.PNG)
                .enableCrop(true)
                .compress(true)
                //同步true或异步false 压缩 默认同步
                .synOrAsy(false)
                .sizeMultiplier(1f)
                .withAspectRatio(16, 9)
                .freeStyleCropEnabled(true)
                .openClickSound(true)
                //.selectionMedia(selectList)
                .minimumCompressSize(200)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    /**
     * 打开相机
     */
    public void openCamera() {
        PictureSelector.create(AccountInfoActivity.this)
                .openCamera(PictureMimeType.ofImage())
                .theme(R.style.picture_default_style)
                .maxSelectNum(1)
                .selectionMode(PictureConfig.SINGLE)
                // 是否可预览图片
                .previewImage(true)
                // 是否裁剪
                .enableCrop(true)
                // 是否压缩
                .compress(true)
                // glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .sizeMultiplier(1f)
                // glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                //.glideOverride(1280, 720)
                // 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .withAspectRatio(16, 9)
                // 裁剪框是否可拖拽
                .freeStyleCropEnabled(true)
                .openClickSound(true)
                //异步或者同步
                .synOrAsy(false)
                // 是否传入已选图片
                //.selectionMedia(selectList)
                // 小于100kb的图片不压缩
                .minimumCompressSize(200)
                // 裁剪是否可旋转图片
                .rotateEnabled(false)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    for (LocalMedia media : selectList) {
                        String path = "";
                        if (media.isCut() && !media.isCompressed()) {
                            // 裁剪过
                            path = media.getCutPath();
                        } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {
                            // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                            path = media.getCompressPath();
                        } else {
                            // 原图
                            path = media.getPath();
                        }
                        Date date = new Date(System.currentTimeMillis());
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-ddHH-mm-ss");
                        String picPath = simpleDateFormat.format(date);
                        String path1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/" + picPath + ".jpg";
                        File file = new File(path1);
                        if (file.exists()) {
                            file.delete();
                        }
                        PictureConverter.convertToJpg(path, path1);
                        String path2 = path1;
                        file1 = new File(path2);
                        getHead();
                        try {
                            saveAndDisplayPicture(sdvHead, file1.getPath());
                            PictureFileUtils.deleteCacheDirFile(AccountInfoActivity.this);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 预览图片
     */
    public void saveAndDisplayPicture(View view, String str) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(AccountInfoActivity.this)
                .load(str)
                .apply(options)
                .into((ImageView) view);
    }

    @Override
    protected void setupView() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_accountinfo;
    }

    public void getHead() {
        presenter.getUserHead(SharedPreferencesUtils.readStringFromSharedPreferences(this, ParameterString.CARRIAGE_ID),
                SharedPreferencesUtils.readStringFromSharedPreferences(this, ParameterString.TOKEN), file1);
    }

    @Override
    public void userHeadSuccess(PhotoDown photoDown) {

    }

    @Override
    public void userHeadFail(PhotoDown photoDown) {

    }

    @Override
    public void userHeadError(Throwable throwable) {

    }


    public void checkPermissionDcim() {

        PermissionUtils.requestPermissions(AccountInfoActivity.this, new PermissionUtils.PermissionCallback() {
                    @Override
                    public void onAllPermissionAllowed() {
                        openDcim();
                    }

                    @Override
                    public void onRequestPermissionAgain() {
//                        showAgainPermissionTipDialog("定位权限,存储权限,相机权限为必选项,系统将再次申请");
//                        showRequestPermissionDlg("存储权限为必选项,系统将再次申请");
                    }

                    @Override
                    public void onGuideUserToSettings() {
//                        showPermissionTipDialog("定位权限,存储权限,相机权限为必选项须全部开通才可正常使用app,请到设置中开启" + "\n\n设置路径->系统设置->餐宴网配送->权限");
//                        showOpenAppSettingTipDlg("存储权限为必选项须全部开通才可正常使用app,请到设置中开启,设置路径->系统设置->餐宴网配送->权限");
                    }
                }, Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        );
    }

    public void checkPermissionCamera() {

        PermissionUtils.requestPermissions(AccountInfoActivity.this, new PermissionUtils.PermissionCallback() {
                    @Override
                    public void onAllPermissionAllowed() {
                        openCamera();
                    }

                    @Override
                    public void onRequestPermissionAgain() {
//                        showAgainPermissionTipDialog("定位权限,存储权限,相机权限为必选项,系统将再次申请");
//                        showRequestPermissionDlg("存储权限为必选项,系统将再次申请");
                    }

                    @Override
                    public void onGuideUserToSettings() {
//                        showPermissionTipDialog("定位权限,存储权限,相机权限为必选项须全部开通才可正常使用app,请到设置中开启" + "\n\n设置路径->系统设置->餐宴网配送->权限");
//                        showOpenAppSettingTipDlg("存储权限为必选项须全部开通才可正常使用app,请到设置中开启,设置路径->系统设置->餐宴网配送->权限");
                    }
                }, Manifest.permission.CAMERA
        );
    }
}
