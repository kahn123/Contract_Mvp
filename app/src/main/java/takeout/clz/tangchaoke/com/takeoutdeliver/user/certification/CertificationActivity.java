package takeout.clz.tangchaoke.com.takeoutdeliver.user.certification;

import android.content.Intent;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.kongzue.dialog.listener.OnMenuItemClickListener;
import com.kongzue.dialog.v2.BottomMenu;
import com.kongzue.dialog.v2.WaitDialog;
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
import takeout.clz.tangchaoke.comm.commutils.PictureConverter;
import takeout.clz.tangchaoke.com.takeoutdeliver.R;
import takeout.clz.tangchaoke.com.takeoutdeliver.base.MyBaseActivity;
import takeout.clz.tangchaoke.data.dao.datadownbean.user.CertificationDown;
import takeout.clz.tangchaoke.data.dao.rx.RxViewUtils;

/**
 * 类说明
 *
 * @author 肖峰
 * @date 2018/8/17
 */
public class CertificationActivity extends MyBaseActivity<CertificationPresenterImpl> implements CertificationContract.CertificationView {

    //身份证正面
    @BindView(R.id.id_card_front)
    public ImageView id_card_front;
    @BindView(R.id.rl_left)
    public RelativeLayout rl_left;

    //身份证反面
    @BindView(R.id.id_card_back)
    public ImageView id_card_back;
    @BindView(R.id.rl_right)
    public RelativeLayout rl_right;

    //手持身份证
    @BindView(R.id.id_card_person)
    public ImageView id_card_person;
    @BindView(R.id.rl_person)
    public RelativeLayout rl_person;

    //驾驶证
    @BindView(R.id.id_driving_licence)
    public ImageView id_driving_licence;
    @BindView(R.id.rl_driving_licence)
    public RelativeLayout rl_driving_licence;

    //行驶证
    @BindView(R.id.id_driving_licence2)
    public ImageView id_driving_licence2;
    @BindView(R.id.rl_driving_licence2)
    public RelativeLayout rl_driving_licence2;

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    ArrayList<File> fileList = new ArrayList<>();
    List<LocalMedia> selectList = new ArrayList<>();
    int selectImageview;
    int photoPosition;

    @Override
    protected void initDagger() {
        getComponent().inject(this);
    }

    @Override
    protected void initData() {
        String carriage_id = getIntent().getStringExtra("carriage_id");
        String token = getIntent().getStringExtra("token");
        addSubscribe(RxViewUtils.throttleFirstView(ivBack, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
                finish();

            }
        }));
        addSubscribe(RxViewUtils.throttleFirstView(rl_left, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
                selectDcimOrCamera(0);

            }
        }));

        addSubscribe(RxViewUtils.throttleFirstViewLong(rl_left, new RxViewUtils.CallBackLong() {
            @Override
            public void onLongClick() {
                checkPicture();

            }
        }));
        addSubscribe(RxViewUtils.throttleFirstView(rl_right, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
                selectDcimOrCamera(1);
            }
        }));

        addSubscribe(RxViewUtils.throttleFirstViewLong(rl_right, new RxViewUtils.CallBackLong() {
            @Override
            public void onLongClick() {
                checkPicture();
            }
        }));
        addSubscribe(RxViewUtils.throttleFirstView(rl_person, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
                selectDcimOrCamera(2);
            }
        }));

        addSubscribe(RxViewUtils.throttleFirstViewLong(rl_person, new RxViewUtils.CallBackLong() {
            @Override
            public void onLongClick() {
                checkPicture();
            }
        }));
        addSubscribe(RxViewUtils.throttleFirstView(rl_driving_licence, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
                selectDcimOrCamera(3);
            }
        }));

        addSubscribe(RxViewUtils.throttleFirstViewLong(rl_driving_licence, new RxViewUtils.CallBackLong() {
            @Override
            public void onLongClick() {
                checkPicture();
            }
        }));
        addSubscribe(RxViewUtils.throttleFirstView(rl_driving_licence2, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
                selectDcimOrCamera(4);
            }
        }));

        addSubscribe(RxViewUtils.throttleFirstViewLong(rl_driving_licence2, new RxViewUtils.CallBackLong() {
            @Override
            public void onLongClick() {
                checkPicture();
            }
        }));
        addSubscribe(RxViewUtils.throttleFirstView(btnSubmit, new RxViewUtils.CallBack() {
            @Override
            public void onClick() {
                try {
                    presenter.getCertification(carriage_id, token, "肖峰", "120101198512052054", "1", "123"
                            , "456", "321", "2", fileList.get(0), fileList.get(1), fileList.get(2), fileList.get(3), fileList.get(4));
                    showDialog("加载中...");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }));
    }

    @Override
    protected void setupView() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_certification;
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
                        File file1 = new File(path2);
                        fileList.add(photoPosition, file1);
                        try {
                            if (selectImageview == 0) {
                                saveAndDisplayPicture(selectImageview, id_card_front, file1.getPath());
                                PictureFileUtils.deleteCacheDirFile(CertificationActivity.this);
                            } else if (selectImageview == 1) {
                                saveAndDisplayPicture(selectImageview, id_card_back, file1.getPath());
                                PictureFileUtils.deleteCacheDirFile(CertificationActivity.this);
                            } else if (selectImageview == 2) {
                                saveAndDisplayPicture(selectImageview, id_card_person, file1.getPath());
                                PictureFileUtils.deleteCacheDirFile(CertificationActivity.this);
                            } else if (selectImageview == 3) {
                                saveAndDisplayPicture(selectImageview, id_driving_licence, file1.getPath());
                                PictureFileUtils.deleteCacheDirFile(CertificationActivity.this);
                            } else if (selectImageview == 4) {
                                saveAndDisplayPicture(selectImageview, id_driving_licence2, file1.getPath());
                                PictureFileUtils.deleteCacheDirFile(CertificationActivity.this);
                            }
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
     * 打开相机相册
     */
    public void selectDcimOrCamera(int i) {
        List<String> list = new ArrayList<>();
        list.add("相册");
        list.add("相机");
        BottomMenu.show(CertificationActivity.this, list, new OnMenuItemClickListener() {
            @Override
            public void onClick(String text, int index) {
                if (index == 0) {
                    selectImageview = i;
                    openDcim();
                    photoPosition = i;
                } else if (index == 1) {
                    selectImageview = i;
                    openCamera();
                    photoPosition = i;
                }
            }
        }, true).setTitle("这里是标题测试").setCustomView(customView);
    }

    /**
     * 预览图片
     */
    public void checkPicture() {
        if (selectList.size() > 0) {
            LocalMedia media = selectList.get(0);
            String pictureType = media.getPictureType();
            int mediaType = PictureMimeType.pictureToVideo(pictureType);
            switch (mediaType) {
                case 1:
                    // 预览图片
                    PictureSelector.create(CertificationActivity.this).themeStyle(R.style.picture_default_style).openExternalPreview(0, selectList);
                default:
                    break;
            }
        }
    }

    /**
     * 预览图片
     */
    public void saveAndDisplayPicture(int i, View view, String str) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(CertificationActivity.this)
                .load(str)
                .apply(options)
                .into((ImageView) view);

    }

    /**
     * 打开相机
     */
    public void openCamera() {
        PictureSelector.create(CertificationActivity.this)
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

    /**
     * 打开相册
     */
    public void openDcim() {
        PictureSelector.create(CertificationActivity.this)
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

    @Override
    public void certificationSuccess(CertificationDown certificationDown) {
//        showSuccess(certificationDown.getMessage());
        WaitDialog.dismiss();
    }

    @Override
    public void certificationFail(CertificationDown certificationDown) {
        showFail(certificationDown.getMessage());
    }

    @Override
    public void certificationError(Throwable throwable) {
        showError(throwable);
    }
}
