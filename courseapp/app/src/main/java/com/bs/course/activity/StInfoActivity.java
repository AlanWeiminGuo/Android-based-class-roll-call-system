package com.bs.course.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bs.course.base.BaseActivity;
import com.bs.course.R;
import com.bs.course.bean.ImgBean;
import com.bs.course.bean.StudentBean;
import com.bs.course.utils.AccountValidatorUtil;
import com.bs.course.utils.HttpUtil;
import com.bs.course.utils.JsonUitl;
import com.bs.course.utils.PhotoUtil;
import com.bs.course.utils.Static;
import com.bs.course.utils.UriPathUtils;
import com.bs.course.view.ActionSheetDialog;
import com.bs.course.view.CircleImageView;
import com.bumptech.glide.Glide;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 个人资料
 */
public class StInfoActivity extends BaseActivity {
    @BindView(R.id.lin_back)
    LinearLayout linBack;
    @BindView(R.id.touxiang)
    CircleImageView touxiang;
    @BindView(R.id.ed_username)
    TextView edUsername;
    @BindView(R.id.ed_realname)
    EditText edRealname;
    @BindView(R.id.ed_sex)
    TextView edSex;
    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.btn_up)
    Button btnUp;
    @BindView(R.id.tv_uppass)
    TextView tvUppass;
    private String path;
    private String head = "";

    @Override
    public int getLayoutRes() {
        return R.layout.activity_stinfo;
    }

    @Override
    protected void initData() {
        getinfo();
    }

    @Override
    protected void initListener() {

    }


    private void getinfo() {
        OkHttpUtils
                .post()
                .url(HttpUtil.GETUSERINFO)
                .addParams("id", Static.userId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onResponse(String arg0, int arg1) {
                        StudentBean userEntity = JsonUitl.GsonToBean(arg0,StudentBean.class);
                        if (0 == userEntity.getCode()) {
                            if (userEntity.getUser().getHead() != null) {
                                Glide.with(StInfoActivity.this).load(HttpUtil.IMG_URL + userEntity.getUser().getHead()).into(touxiang);
                            }
                            edRealname.setText(userEntity.getUser().getName());
                            edUsername.setText(userEntity.getUser().getStudentId());
                            edPhone.setText(userEntity.getUser().getPhone());
                            edSex.setText(userEntity.getUser().getSex());
                        } else {
                            Toast.makeText(StInfoActivity.this, userEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call arg0, Exception arg1, int arg2) {
                        Toast.makeText(StInfoActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @OnClick({R.id.lin_back, R.id.touxiang, R.id.btn_up, R.id.ed_sex,R.id.tv_uppass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.touxiang:
                options();
                break;
            case R.id.btn_up:
                upinfo();
                break;
            case R.id.ed_sex:
                change_juese();
                break;
            case R.id.tv_uppass:
                Intent intent = new Intent(StInfoActivity.this,UpPwdActivity.class);
                intent.putExtra("type","1");
                startActivity(intent);
                break;
        }
    }

    protected void options() {
        ActionSheetDialog mDialog = new ActionSheetDialog(this).builder();
        mDialog.setTitle("选择");
        mDialog.setCancelable(false);
        mDialog.addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                PhotoUtil.photograph(StInfoActivity.this);
            }
        }).addSheetItem("从相册选取", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                PhotoUtil.selectPictureFromAlbum(StInfoActivity.this);
            }
        }).show();
    }

    public void change_juese() {
        AlertDialog.Builder builder = new AlertDialog.Builder(StInfoActivity.this); //定义一个AlertDialog
        String[] strarr = {"男", "女"};
        builder.setItems(strarr, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                // 自动生成的方法存根
                if (arg1 == 0) {
                    edSex.setText("男");
                } else {//女
                    edSex.setText("女");
                }
            }
        });
        builder.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == PhotoUtil.NONE)
            return;
        // 拍照
        if (requestCode == PhotoUtil.PHOTOGRAPH) {
            // 设置文件保存路径这里放在跟目录下
            File picture = null;
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                picture = new File(Environment.getExternalStorageDirectory() + PhotoUtil.imageName);
                if (!picture.exists()) {
                    picture = new File(Environment.getExternalStorageDirectory() + PhotoUtil.imageName);
                }
            } else {
                picture = new File(this.getFilesDir() + PhotoUtil.imageName);
                if (!picture.exists()) {
                    picture = new File(StInfoActivity.this.getFilesDir() + PhotoUtil.imageName);
                }
            }
            path = PhotoUtil.getPath(this);// 生成一个地址用于存放剪辑后的图片
            if (TextUtils.isEmpty(path)) {
                return;
            }
            Uri imageUri = UriPathUtils.getUri(this, path);
            PhotoUtil.startPhotoZoom(StInfoActivity.this, Uri.fromFile(picture), PhotoUtil.PICTURE_HEIGHT, PhotoUtil.PICTURE_WIDTH, imageUri);
        }

        if (data == null)
            return;
        // 读取相册缩放图片
        if (requestCode == PhotoUtil.PHOTOZOOM) {
            path = PhotoUtil.getPath(this);// 生成一个地址用于存放剪辑后的图片
            if (TextUtils.isEmpty(path)) {
                return;
            }
            Uri imageUri = UriPathUtils.getUri(this, path);
            PhotoUtil.startPhotoZoom(StInfoActivity.this, data.getData(), PhotoUtil.PICTURE_HEIGHT, PhotoUtil.PICTURE_WIDTH, imageUri);
        }
        // 处理结果
        if (requestCode == PhotoUtil.PHOTORESOULT) {
            /**
             * 在这里处理剪辑结果，可以获取缩略图，获取剪辑图片的地址。得到这些信息可以选则用于上传图片等等操作
             * */
            /**
             * 如，根据path获取剪辑后的图片
             */
            Bitmap bitmap = PhotoUtil.convertToBitmap(path, PhotoUtil.PICTURE_HEIGHT, PhotoUtil.PICTURE_WIDTH);
            if (bitmap != null) {
                touxiang.setImageBitmap(bitmap);
                shangchuan(path);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void shangchuan(String path) {
        OkHttpUtils
                .post()
                .url(HttpUtil.IMGUPLOAD)
                .addFile("file", "file1.jpg", new File(path))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onResponse(String arg0, int arg1) {
                        Log.i("LoginActivity", arg0);
                        ImgBean baseData = JsonUitl.GsonToBean(arg0,ImgBean.class);
                        if (0 == baseData.getCode()) {
                            head = baseData.getFileurl();
                            uptouxiang();
                        } else {
                            Toast.makeText(StInfoActivity.this, baseData.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call arg0, Exception arg1, int arg2) {
                        Toast.makeText(StInfoActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * 修改头像
     */
    private void uptouxiang() {
        OkHttpUtils
                .post()
                .url(HttpUtil.UPINFO)
                .addParams("id", Static.userId)
                .addParams("head", head)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onResponse(String arg0, int arg1) {
                        StudentBean userEntity = JsonUitl.GsonToBean(arg0,StudentBean.class);
                        if (0 == userEntity.getCode()) {
                            Static.head = head;
                            Toast.makeText(StInfoActivity.this, "修改头像成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(StInfoActivity.this, userEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call arg0, Exception arg1, int arg2) {
                        Toast.makeText(StInfoActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    /**
     * 修改资料
     */
    private void upinfo() {
        if (edRealname.getText().toString().trim().equals("")) {
            Toast.makeText(StInfoActivity.this, "姓名不能为空!", Toast.LENGTH_LONG).show();
            return;
        }
        if (edPhone.getText().toString().trim().equals("")) {
            Toast.makeText(StInfoActivity.this, "手机号码不能为空!", Toast.LENGTH_LONG).show();
            return;
        }
        if (AccountValidatorUtil.isMobile(edPhone.getText().toString().trim()) == false) {
            Toast.makeText(StInfoActivity.this, "请输入正确的手机号码!", Toast.LENGTH_LONG).show();
            return;
        }
        OkHttpUtils
                .post()
                .url(HttpUtil.UPINFO)
                .addParams("id", Static.userId)
                .addParams("name", edRealname.getText().toString().trim())
                .addParams("sex", edSex.getText().toString().trim())
                .addParams("phone", edPhone.getText().toString().trim())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onResponse(String arg0, int arg1) {
                        StudentBean userEntity = JsonUitl.GsonToBean(arg0,StudentBean.class);
                        if (0 == userEntity.getCode()) {
                            Toast.makeText(StInfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(StInfoActivity.this, userEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call arg0, Exception arg1, int arg2) {
                        Toast.makeText(StInfoActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });
    }




}
