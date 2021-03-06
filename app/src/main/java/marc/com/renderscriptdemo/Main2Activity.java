package marc.com.renderscriptdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

	/**
	 * 原始图片控件
	 */
	private ImageView mOriginImg;
	/**
	 * 模糊后的图片控件
	 */
	private ImageView mBluredImage;
	/**
	 * 进度条SeekBar
	 */
	private SeekBar mSeekBar;
	/**
	 * 显示进度的文字
	 */
	private TextView mProgressTv;
	/**
	 * 透明度
	 */
	private int mAlpha;
	/**
	 * 原始图片
	 */
	private Bitmap mTempBitmap;
	/**
	 * 模糊后的图片
	 */
	private Bitmap mFinalBitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 初始化视图
		initViews();
		// 获取图片
		mTempBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gn);
		mFinalBitmap = BlurBitmap.blur(this, mTempBitmap);
		// 填充模糊后的图像和原图
		mBluredImage.setImageBitmap(mFinalBitmap);
		mOriginImg.setImageBitmap(mTempBitmap);
		// 处理seekbar滑动事件
		setSeekBar();
	}
	/**
	 * 初始化视图
	 */
	private void initViews() {
		mBluredImage = (ImageView) findViewById(R.id.activity_main_blured_img);
		mOriginImg = (ImageView) findViewById(R.id.activity_main_origin_img);
		mSeekBar = (SeekBar) findViewById(R.id.activity_main_seekbar);
		mProgressTv = (TextView) findViewById(R.id.activity_main_progress_tv);
	}
	/**
	 * 处理seekbar滑动事件
	 */
	private void setSeekBar() {
		mSeekBar.setMax(100);
		mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				mAlpha = progress;
				mOriginImg.setAlpha((int) (255 - mAlpha * 2.55));
				mProgressTv.setText(String.valueOf(mAlpha));
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()){
			case MotionEvent.EDGE_TOP:
				mOriginImg.setAlpha((int) (255 - 100 * 2.55));
				/*WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
				Display display = wm.getDefaultDisplay();
				Point point = new Point();
				display.getSize(point);
// 获取到ImageView的高度
				int height = point.y;
				ViewGroup.LayoutParams params = imageView.getLayoutParams();
				params.width = ViewGroup.LayoutParams.MATCH_PARENT;
// 将ImageView的高度增加100
				params.height = height + 100;
// 应用更改设置
				imageView.requestLayout();*/

				break;
			case MotionEvent.EDGE_BOTTOM:
				mOriginImg.setAlpha((int) (255 - 0 * 2.55));
				break;
		}
		return super.onTouchEvent(event);
	}
}
