package com.lib4.pinterestui;

import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

public class PinterestUI extends LinearLayout {
	private int NUM_COLUMN = 3;
	Context context;
	int SCREEN_WIDTH = 0;
	int SCREEN_HEIGHT = 0;
	int[] HeightArray;
	LinearLayout NextLayout;
	int TOTAL_NUM_ITEMS = 10;
	int ITEM_DRAWN_INDEX = 0;

	public PinterestUI(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public PinterestUI(Context context) {
		super(context);
		this.context = context;
		setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metrics);

		SCREEN_WIDTH = metrics.widthPixels;
		HeightArray = new int[NUM_COLUMN];
		
		ViewTreeObserver vto = getViewTreeObserver();
		vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@SuppressLint("NewApi")
			@Override
			public void onGlobalLayout() {
				ITEM_DRAWN_INDEX++;
				NextLayout = (LinearLayout) getChildAt(getLayoutIndexToAdd());

				if (ITEM_DRAWN_INDEX >= TOTAL_NUM_ITEMS) {

					ViewTreeObserver obs = getViewTreeObserver();

					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
						obs.removeOnGlobalLayoutListener(this);
					} else {
						obs.removeGlobalOnLayoutListener(this);
					}
				} else {
					draw();
				}
			}

		});
	}

	public void createLayout() {

		System.out.println("IM HERE>>>>>>>>>>>" + SCREEN_WIDTH);
		for (int i = 0; i < NUM_COLUMN; i++) {

			LinearLayout mLinearLayout = new LinearLayout(context);
			mLinearLayout.setLayoutParams(new LayoutParams(SCREEN_WIDTH
					/ NUM_COLUMN, LayoutParams.WRAP_CONTENT));

			mLinearLayout.setOrientation(LinearLayout.VERTICAL);
			setOrientation(LinearLayout.HORIZONTAL);
			addView(mLinearLayout);

			System.out.println("IM HERE>>>>>>>>>>> Layout Creation" + i);
		}

		for (int i = 0; i < 1; i++) {

			draw();
		}

	}

	private void draw() {
		if (NextLayout == null) {
			NextLayout = (LinearLayout) getChildAt(0);
		}
		
		LinearLayout mLinearLayout	=	new LinearLayout(context);
		mLinearLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT
				, LayoutParams.WRAP_CONTENT));
		mLinearLayout.setPadding(20, 20, 20, 20);
		mLinearLayout.setBackgroundColor(Color.argb(100, 176, 176, 176));
		
		ImageView imageView = new ImageView(context);
		Random rand = new Random();
		int  n = rand.nextInt(1000) + 1;
		imageView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				n));
		imageView.setScaleType(ScaleType.FIT_XY);
		imageView.setImageResource(R.drawable.first);
		mLinearLayout.addView(imageView);
		NextLayout.addView(mLinearLayout);
	}

	private int getLayoutIndexToAdd() {
		int ChildCount = getChildCount();
		int i = 0;
		int fewer = 0;
		int layoutIndex = 0;
		while (i < ChildCount) {
			System.out.println("IM HERE>>>>>>>>>>> Layout getChildAt(i) " + i
					+ "        " + getChildAt(i).getHeight());
			if (i == 0) {
				fewer = getChildAt(i).getHeight();
				layoutIndex = i;
			} else {

				if (getChildAt(i).getHeight() < fewer) {
					fewer = getChildAt(i).getHeight();
					layoutIndex = i;
				}
			}
			i++;
		}

		System.out.println("IM HERE>>>>>>>>>>> Layout layoutIndex"
				+ layoutIndex);
		return layoutIndex;
	}

}
