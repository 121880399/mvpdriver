package org.zzy.driver.mvp.ui.activity;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linchaolong.android.imagepicker.ImagePicker;
import com.linchaolong.android.imagepicker.cropper.CropImage;
import com.linchaolong.android.imagepicker.cropper.CropImageView;
import com.zzy.quick.mvp.ui.BaseActivity;
import com.zzy.quick.utils.FileUtils;
import com.zzy.quick.utils.ToastUtils;

import org.zzy.driver.R;
import org.zzy.driver.mvp.presenter.PersonCenterPresenter;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhou on 2018/5/11.
 */

public class PersonInfoActivity extends BaseActivity<PersonCenterPresenter> {


    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.ll_head)
    LinearLayout llHead;
    @BindView(R.id.tv_userName)
    TextView tvUserName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.ll_contact)
    LinearLayout llContact;
    @BindView(R.id.tv_baseName)
    TextView tvBaseName;
    @BindView(R.id.layout_region)
    LinearLayout layoutRegion;
    @BindView(R.id.tv_vehicleCode)
    TextView tvVehicleCode;
    @BindView(R.id.tv_userType)
    TextView tvUserType;
    @BindView(R.id.tv_company)
    TextView tvCompany;
    @BindView(R.id.ll_resetpassword)
    LinearLayout llResetpassword;
    @BindView(R.id.ll_exit)
    LinearLayout llExit;

    private ImagePicker imagePicker = new ImagePicker();

    @Override
    public PersonCenterPresenter newPresenter() {
        return new PersonCenterPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_personinfo;
    }

    @Override
    public void initView() {
        super.initView();
        getTopbar().setTitle("个人信息");
        getTopbar().setLeftImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void initData() {
        //设置裁剪图片
        imagePicker.setCropImage(true);
    }

    @Override
    public void showError(String msg) {

    }

    /**
     * @function 修改头像
     * */
    @OnClick(R.id.ll_head)
    public void clickUpdateHead(){
        startCameraOrGallery();
    }


    /**
     * @function 修改手机号
     * */
    public void clickUpdatePhone(){

    }

    /**
     * @function 修改驻地
     * */
    public void clickUpdateBaseName(){

    }

    /**
     * @function 修改登录密码
     * */
    public void clickUpdatePassword(){

    }


    /**
     * @function 点击退出，清除SP中用户数据
     * */
    public void clickExit(){

    }


    /**
     * 弹出对话框，选择相册或者启动摄像头拍照
     * */
    private void startCameraOrGallery() {
        new AlertDialog.Builder(this).setTitle("设置头像")
                .setItems(new String[] { "从相册中选取图片", "拍照" }, new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        // 回调
                        ImagePicker.Callback callback = new ImagePicker.Callback() {
                            @Override public void onPickImage(Uri imageUri) {
                            }

                            @Override public void onCropImage(Uri imageUri) {
                                File file=FileUtils.uri2File(imageUri);
                                if(file!=null) {
                                    getPresenter().setUserHead(file);
                                }else{
                                    ToastUtils.showShort("图片地址错误！");
                                }
                            }

                            @Override
                            public void cropConfig(CropImage.ActivityBuilder builder) {
                                super.cropConfig(builder);
                                builder
                                        // 是否启动多点触摸
                                        .setMultiTouchEnabled(false)
                                        // 设置网格显示模式
                                        .setGuidelines(CropImageView.Guidelines.OFF)
                                        // 圆形/矩形
                                        .setCropShape(CropImageView.CropShape.OVAL)
                                        // 调整裁剪后的图片最终大小
                                        .setRequestedSize(960, 540)
                                        // 宽高比
                                        .setAspectRatio(9, 9);
                            }
                        };
                        if (which == 0) {
                            // 从相册中选取图片
                            imagePicker.startGallery(PersonInfoActivity.this, callback);
                        } else {
                            // 拍照
                            imagePicker.startCamera(PersonInfoActivity.this, callback);
                        }
                    }
                })
                .show()
                .getWindow()
                .setGravity(Gravity.BOTTOM);
    }


}
