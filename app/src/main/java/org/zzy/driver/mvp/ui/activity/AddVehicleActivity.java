package org.zzy.driver.mvp.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.linchaolong.android.imagepicker.ImagePicker;
import com.zzy.quick.image.ImageFactory;
import com.zzy.quick.mvp.ui.BaseActivity;
import com.zzy.quick.router.Router;

import org.zzy.driver.R;
import org.zzy.driver.common.AppConfig;
import org.zzy.driver.mvp.model.bean.request.RequestVehicleInfo;
import org.zzy.driver.mvp.model.bean.response.ResponseVehicleType;
import org.zzy.driver.mvp.model.bean.response.ResponseContainerType;
import org.zzy.driver.mvp.model.bean.response.ResponseLinkType;
import org.zzy.driver.mvp.presenter.AddVehiclePresenter;
import org.zzy.driver.view.ActionSheet;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by zhou on 2018/6/21.
 */

public class AddVehicleActivity extends BaseActivity<AddVehiclePresenter> {
    private static final int ADD_DRIVER_LICENSE=1;//添加车辆行驶证
    private static final int ADD_DRIVER_VEHICLE_PHOTO=2;//添加司机与车辆的合影
    private static final int ADD_OPERATION_CERTIFICATE=3;//货车运营证书

    private static final int VEHICLETYPECODE = 1;//车辆类型
    private static final int LINKTYPECODE = 2;//拖挂形式
    private static final int CONTAINERTYPECODE = 3;//箱型箱类

    @BindView(R.id.et_vehicleNum)
    EditText etVehicleNum;
    @BindView(R.id.tv_carType)
    TextView tvCarType;
    @BindView(R.id.ll_carType)
    LinearLayout llCarType;
    @BindView(R.id.tv_linkType)
    TextView tvLinkType;
    @BindView(R.id.ll_linkType)
    LinearLayout llLinkType;
    @BindView(R.id.rb_havePeg)
    RadioButton rbHavePeg;
    @BindView(R.id.rb_noPeg)
    RadioButton rbNoPeg;
    @BindView(R.id.rg_peg)
    RadioGroup rgPeg;
    @BindView(R.id.tv_boxType)
    TextView tvBoxType;
    @BindView(R.id.ll_boxType)
    LinearLayout llBoxType;
    @BindView(R.id.et_vehicleLength)
    EditText etVehicleLength;
    @BindView(R.id.et_vehicleWeight)
    EditText etVehicleWeight;
    @BindView(R.id.iv_drivingLicense)
    ImageView ivDrivingLicense;
    @BindView(R.id.iv_groupPhoto)
    ImageView ivGroupPhoto;
    @BindView(R.id.iv_operationCertificate)
    ImageView ivOperationCertificate;
    @BindView(R.id.btn_next)
    Button btnNext;

    private ImagePicker imagePicker = new ImagePicker();
    List<ResponseVehicleType> mVehicleTypeList;
    List<ResponseContainerType> mContainerTypeList;
    List<ResponseLinkType> mLinkTypeList;
    //是否使用相机
    private boolean isCamera =false;
    private RequestVehicleInfo vehicleInfo;


    @Override
    public AddVehiclePresenter newPresenter() {
        return new AddVehiclePresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_addvehicle;
    }

    @Override
    public void initData() {
        getTopbar().setTitle("车辆信息");
        getTopbar().setLeftImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getTopbar().setRightImage(R.drawable.ic_newcall);
        getTopbar().setRightImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.newIntent(AddVehicleActivity.this)
                        .setAction(Intent.ACTION_DIAL)
                        .setUri(Uri.parse("tel:" + AppConfig.CALLCENTER))
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .launch();
            }
        });
        //不裁剪图片
        imagePicker.setCropImage(false);
        vehicleInfo=new RequestVehicleInfo();
        vehicleInfo.setContainerId(new ArrayList<Integer>());
        vehicleInfo.setImageFileMap(new HashMap<String, File>());
        getPresenter().getVehicleBasicInfo();
    }


    public void setVehicleTypeList(List<ResponseVehicleType> vehicleTypeList) {
        this.mVehicleTypeList = vehicleTypeList;
    }

    public void setContainerTypeList(List<ResponseContainerType> containerTypeList) {
        this.mContainerTypeList = containerTypeList;
    }

    public void setLinkTypeList(List<ResponseLinkType> linkTypeList) {
        this.mLinkTypeList = linkTypeList;
    }

    /**
     * 点击选择车辆类型
     * */
    @OnClick(R.id.ll_carType)
    public void clickVehicleType(){
        Router.newIntent(this)
                .to(VehicleTypeActivity.class)
                .putSerializable("vehicleType",mVehicleTypeList)
                .requestCode(VEHICLETYPECODE)
                .launch();
    }


    /**
     * 点击选择拖挂形式
     * */
    @OnClick(R.id.ll_linkType)
    public void clickLinkType(){
        Router.newIntent(this)
                .to(LinkTypeActivity.class)
                .putSerializable("linkType",mLinkTypeList)
                .requestCode(LINKTYPECODE)
                .launch();
    }


    /**
     * 点击选择箱型箱类
     * */
    @OnClick(R.id.ll_boxType)
    public void clickContainerType(){
        Router.newIntent(this)
                .to(ContainerTypeActivity.class)
                .putSerializable("containerType",mContainerTypeList)
                .requestCode(CONTAINERTYPECODE)
                .launch();
    }


    /**
     * 添加车辆行驶证照片
     * */
    @OnClick(R.id.iv_drivingLicense)
    public void addDriverLicense(){
        startCameraOrGallery(ADD_DRIVER_LICENSE);
    }

    /**
     * 添加车辆与司机的合影
     * */
    @OnClick(R.id.iv_groupPhoto)
    public void addDriverVehiclePhoto(){
        startCameraOrGallery(ADD_DRIVER_VEHICLE_PHOTO);
    }


    /**
     * 添加车辆运营证书
     * */
    @OnClick(R.id.iv_operationCertificate)
    public void addOperationCertificate(){
        startCameraOrGallery(ADD_OPERATION_CERTIFICATE);
    }

    /**
     * 提交添加车辆
     * */
    @OnClick(R.id.btn_next)
    public void addVehicle(){
        vehicleInfo.setVehicleCode(etVehicleNum.getText().toString());
        vehicleInfo.setVehicleLength(etVehicleLength.getText().toString());
        vehicleInfo.setVehicleWeight(etVehicleWeight.getText().toString());
        getPresenter().addVehicle(vehicleInfo);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        imagePicker.onRequestPermissionsResult(this,requestCode,permissions,grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imagePicker.onActivityResult(this,requestCode,resultCode,data);
        if(requestCode==VEHICLETYPECODE &&  resultCode==RESULT_OK){
            String vehicleTypeName=data.getStringExtra("name");
            vehicleInfo.setVehicleType(vehicleTypeName);
            vehicleInfo.setVehicleTypeCode(data.getStringExtra("code"));
            tvCarType.setText(vehicleTypeName);
        }else if(requestCode==LINKTYPECODE &&  resultCode==RESULT_OK){
            String linkTypeName=data.getStringExtra("name");
            vehicleInfo.setLinkType(linkTypeName);
            vehicleInfo.setLinkTypeCode(data.getStringExtra("code"));
            tvLinkType.setText(linkTypeName);
        }else if(requestCode==CONTAINERTYPECODE &&  resultCode==RESULT_OK){
            HashMap<String, ResponseContainerType> containerTypeMap= (HashMap<String, ResponseContainerType>) data.getSerializableExtra("containerTypeList");
            //重新初始化字符串和界面
            StringBuilder nameBuilder = new StringBuilder();
            for (String key : containerTypeMap.keySet()) {
                ResponseContainerType containerType = (ResponseContainerType) containerTypeMap.get(key);
                nameBuilder.append(containerType.getName());
                vehicleInfo.getContainerId().add(containerType.getId());
                nameBuilder.append("\n");
            }
            String route = nameBuilder.toString().trim();
            tvBoxType.setText(route);
            vehicleInfo.setContainerType(route);
        }
    }

    private void startCameraOrGallery(final int type) {
        ActionSheet.Builder mBuilder; mBuilder = ActionSheet.createBuilder(this, getSupportFragmentManager()).setOtherButtonTitles(titles).setCancelButtonTitle(R.string.text_cancle).setListener(new ActionSheet.ActionSheetListener() {
            @Override
            public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

            }

            @Override
            public void onOtherButtonClick(ActionSheet actionSheet, int index) {
                // 回调
                ImagePicker.Callback callback = new ImagePicker.Callback() {
                    @Override public void onPickImage(Uri imageUri) {
                        File file=getPresenter().uri2File(imageUri,isCamera);
                        //当选择图片时回调
                        switch (type){
                            case ADD_DRIVER_LICENSE:
                                //这里先将得到的uri转化成file，并且改名再显示，跟gilde的顺序不要换
                                //因为glide要加载的是修改文件名以后的文件
                                vehicleInfo.getImageFileMap().put("carLicenseFront",file);
                                ImageFactory.getImageLoader().loadFile(file,ivDrivingLicense,null);
                                break;
                            case ADD_DRIVER_VEHICLE_PHOTO:
                                vehicleInfo.getImageFileMap().put("groupPhoto",file);
                                ImageFactory.getImageLoader().loadFile(file,ivGroupPhoto,null);
                                break;
                            case ADD_OPERATION_CERTIFICATE:
                                vehicleInfo.getImageFileMap().put("truckOperator",file);
                                ImageFactory.getImageLoader().loadFile(file,ivOperationCertificate,null);
                                break;
                        }
                    }
                };
                switch (index) {
                    case 0:
                        //拍照
                        imagePicker.startCamera(AddVehicleActivity.this, callback);
                        isCamera =true;
                        break;
                    case 1:
                        //相册
                        imagePicker.startGallery(AddVehicleActivity.this, callback);
                        isCamera =false;
                        break;
                }
            }
        });
        mBuilder.show();
    }


}
