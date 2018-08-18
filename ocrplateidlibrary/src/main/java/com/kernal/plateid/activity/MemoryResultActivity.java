package com.kernal.plateid.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kernal.plateid.R;
import com.kernal.plateid.model.bean.Result;
import com.kernal.plateid.utills.I;
import com.kernal.plateid.utills.MFGT;
import com.kernal.plateid.utills.OkHttpUtils;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MemoryResultActivity extends Activity{

	private TextView number,color;
	private Button confirm;
	private int width,height;
	private TextView text_num,text_color,text_title;
	private ImageView image,image_back;
	private String bitmapPath;
	private Bitmap bitmap = null;
	private boolean recogType;// 记录进入此界面时是拍照识别界面还是视频识别界面   	 true:视频识别 		false:拍照识别
	private String truk_num;
	private static final String TAG = "MemoryResultActivity";
	private static final String PATH = Environment
			.getExternalStorageDirectory().toString() + "/DCIM/Camera/";
	private File file;
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

//		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 竖屏
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
		setContentView(R.layout.memoryresult_activity);
		findView();
		truk_num = getIntent().getCharSequenceExtra("number").toString();
		Log.e(TAG, "truk_num="+truk_num);
		recogType  = getIntent().getBooleanExtra("recogType", false);
		System.out.println("识别时间："+getIntent().getStringExtra("time"));

	}

/**
 *
* @Title: findView
* @Description: TODO(这里用一句话描述这个方法的作用)
* @param
* @return void    返回类型
* @throws
 * */
	private void findView() {
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		width = metric.widthPixels; // 屏幕宽度（像素）
		height = metric.heightPixels; // 屏幕高度（像素）
		number = (TextView) findViewById(R.id.plate_number);
		color = (TextView) findViewById(R.id.plate_color);
		confirm = (Button) findViewById(R.id.confirm);
		text_num=(TextView) findViewById(R.id.text_number);
		text_color = (TextView) findViewById(R.id.text_color);
		image = (ImageView) findViewById(R.id.plate_image);
		image_back = (ImageView) findViewById(R.id.plate_back);
		text_title  = (TextView) findViewById(R.id.plate_title);

		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		layoutParams.addRule(RelativeLayout.BELOW,R.id.plate_image);
		layoutParams.leftMargin = width/4;
		layoutParams.bottomMargin = height/6;
		text_num.setLayoutParams(layoutParams);

		layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		layoutParams.addRule(RelativeLayout.ALIGN_LEFT, R.id.text_number);
		layoutParams.addRule(RelativeLayout.BELOW,R.id.plate_image);
		layoutParams.leftMargin = width/5;
		layoutParams.bottomMargin = height/8;
		number.setLayoutParams(layoutParams);

		layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		layoutParams.addRule(RelativeLayout.BELOW,R.id.text_number);
		layoutParams.leftMargin = width/4;
		layoutParams.bottomMargin = height/10;
		text_color.setLayoutParams(layoutParams);

		layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		layoutParams.addRule(RelativeLayout.ALIGN_LEFT, R.id.text_color);
		layoutParams.addRule(RelativeLayout.BELOW,R.id.text_number);
		layoutParams.leftMargin = width/5;
		layoutParams.bottomMargin = height/10;
		color.setLayoutParams(layoutParams);

		layoutParams = new RelativeLayout.LayoutParams(width/4, RelativeLayout.LayoutParams.WRAP_CONTENT);
		layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,RelativeLayout.TRUE);
		layoutParams.bottomMargin = height/5;
		confirm.setLayoutParams(layoutParams);

		int bm_width = (int) (width*0.5);
		int bm_height = bm_width*1;
		layoutParams= new RelativeLayout.LayoutParams(bm_width, bm_height);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
		layoutParams.leftMargin = width/4;
		layoutParams.topMargin = height/8;
		image.setLayoutParams(layoutParams);

		int back_h = (int) (height * 0.066796875);
		int back_w = (int) (back_h * 1);
		layoutParams= new RelativeLayout.LayoutParams(back_w, back_h);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
		layoutParams.leftMargin =  (int) (width * 0.05);
		image_back.setLayoutParams(layoutParams);

		layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
		layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		text_title.setLayoutParams(layoutParams);




		bitmapPath = getIntent().getStringExtra("path");
		int left = getIntent().getIntExtra("left", -1);
		int top  = getIntent().getIntExtra("top", -1);
		int w =getIntent().getIntExtra("width", -1);
		int h = getIntent().getIntExtra("height", -1);
		Log.e("MemoryResultActivity", "视频流图片路径" + bitmapPath);
		if(bitmapPath!=null&&!bitmapPath.equals("")){
			bitmap = BitmapFactory.decodeFile(bitmapPath);
			//在使用图片路径识别模式跳入本界面时   请将下面这行代码注释
			Bitmap bitmap1=Bitmap.createBitmap(bitmap, left, top, w, h);
			if(bitmap1!=null){
				image.setImageBitmap(bitmap1);
			}
		}

		text_title.setTextSize(20);

		number.setText(getIntent().getCharSequenceExtra("number"));
		color.setText(getIntent().getCharSequenceExtra("color"));
		color.setTextColor(Color.BLACK);
		number.setTextColor(Color.BLACK);
		text_num.setTextColor(Color.BLACK);
		text_color.setTextColor(Color.BLACK);
		image_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MemoryResultActivity.this,MemoryCameraActivity.class);
				intent.putExtra("camera", recogType);//
				startActivity(intent);
				finish();
			}
		});
		confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
//				Intent intent  = new Intent("kernal.plateid.MainActivity");
//				startActivity(intent);
//				finish();
				OkHttpUtils<Result> utils = new OkHttpUtils<Result>(MemoryResultActivity.this);
				utils.setRequestUrl(I.DETECT_TRUCKSTATUS)
						.addParam("truckNum",truk_num)
						.targetClass(Result.class)
						.execute(new OkHttpUtils.OnCompleteListener<Result>() {
							@Override
							public void onSuccess(Result result) {
								if (null != result) {
									Log.e(TAG, "result="+result.toString());
									if (result.getRetCode() == 200) {
										intent=new Intent(MemoryResultActivity.this, TruckInspectionActivity.class);
										intent.putExtra("result_data",result);
										upLoadPic();

									} else {
										Log.e(TAG, "进入提示代码处");
										showAlert("料车状态异常提醒","当前料车未处于待验收状态请现场核查");
									}
								} else {
									Log.e(TAG, "result=null");
									showAlert("網絡請求異常","請求結果為空");
								}
							}
							@Override
							public void onError(String error) {
								Log.e(TAG, "error=" + error);
								showAlert("網絡請求異常","網絡請求返回錯誤");
							}
						});
			}
		});
	}

	//图片上传的代码
	private void upLoadPic() {
		MediaType MEDIA_TYPE_JPG = MediaType.parse("image/jpg");
		OkHttpClient client=new OkHttpClient();
		MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
		file = new File(bitmapPath);
		builder.addFormDataPart("file", file.getName(), RequestBody.create(MEDIA_TYPE_JPG, file));
		final MultipartBody requestBody=builder.build();
		final Request request = new Request.Builder()
				.url(I.SERVER_ROOT + "uploadpic")
				.post(requestBody)
				.build();
		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				Log.e(TAG, "e=" + e);
				Toast.makeText(MemoryResultActivity.this, "图片上传失败", Toast.LENGTH_LONG).show();
				showRegMsg("图片上传失败");
				MFGT.startActivity(MemoryResultActivity.this, intent);
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				Log.e(TAG, "图片上传成功");
				file.delete();
				showRegMsg("图片上传成功");
				MFGT.startActivity(MemoryResultActivity.this, intent);


			}
		});




	}

	private void showRegMsg(final String msg) {
		runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MemoryResultActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });
	}

	private void showAlert(String title,String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(MemoryResultActivity.this);
		builder.setTitle(title)
				.setMessage(msg)
				.setCancelable(true)
				.show();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		if (bitmap!=null) {
			bitmap = null;
		}
	}
	
}
