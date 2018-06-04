package org.zzy.driver.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.zzy.quick.utils.TimeUtils;

import org.zzy.driver.R;
import org.zzy.driver.mvp.model.bean.response.ResponsePrice;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import static android.text.TextUtils.isEmpty;


/**
 * yangqiangyu on 14/11/2016 11:38
 */

public class CommonCalendarView extends FrameLayout implements View.OnClickListener {

    private ViewPager mViewPager;
    private TextView mMonthTv;
    private Context mContext;
    private android.widget.ImageButton mLeftMonthBtn;
    private android.widget.ImageButton mRightMonthBtn;

    private SparseArray<GridView> mViewMap = new SparseArray<>();

    private Map<String, List> mYearMonthMap;
    private DatePickerController mController;
    private CalendarAdapter adapter;
    private Date maxDate;
    private Date minDate;
    private static final int PAD_LIMIT = 8192;

    private OnClickPreNextListerner mOnListener = null;

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public CommonCalendarView(Context context) {
        this(context, null);
    }

    public CommonCalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }


    public void setOnListener(OnClickPreNextListerner mOnListener) {
        this.mOnListener = mOnListener;
    }

    public void init(DatePickerController controller) {
        if (controller == null) {
            mController = new DatePickerController() {
                @Override
                public int getMaxYear() {
                    return TimeUtils.getCurrentYear() + 1;
                }

//                 @Override
//                 public int getMaxMonth() {
//                     return 1;
//                 }

                @Override
                public void onDayOfMonthSelected(int year, int month, int day) {
                    Toast.makeText(mContext, String.format("%s-%s-%s", year, leftPad(String.valueOf(month), 2, "0"),
                            leftPad(String.valueOf(day), 2, "0")), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onDayOfMonthAndDataSelected(int year, int month, int day, List obj) {

                }

                @Override
                public void showOtherFields(Object obj, View view, int gridItemYear, int gridItemMonth, int gridItemDay) {

                }

                @Override
                public Map<String, List> getDataSource() {
                    return null;
                }

            };
        } else {
            mController = controller;
        }
        this.mYearMonthMap = mController.getDataSource();
        adapter = new CalendarAdapter(mContext);
        mViewPager.setPageTransformer(true, new DepthPageTransformer());
        mViewPager.setAdapter(adapter);


        if (minDate != null) {
            mMonthTv.setText(String.format("%s年%s月", TimeUtils.getYear(minDate), leftPad(String.valueOf(TimeUtils.getMonth(minDate)), 2, "0")));
        } else {
            mMonthTv.setText(String.format("%s年%s月", TimeUtils.getCurrentYear(), leftPad(String.valueOf(TimeUtils.getCurrentMonth() + 1), 2, "0")));
        }

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mMonthTv.setText(adapter.getPageTitle(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_page_calendar_price, this, true);
        this.mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        this.mRightMonthBtn = (ImageButton) view.findViewById(R.id.right_month_btn);
        this.mMonthTv = (TextView) view.findViewById(R.id.month_tv);
        this.mLeftMonthBtn = (ImageButton) view.findViewById(R.id.left_month_btn);
        this.mLeftMonthBtn.setOnClickListener(this);
        this.mRightMonthBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_month_btn:
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
                break;
            case R.id.right_month_btn:
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
                break;
        }
    }


    /**
     * ViewPager的adapter
     */
    class CalendarAdapter extends PagerAdapter implements AdapterView.OnItemClickListener {

        protected static final int MONTHS_IN_YEAR = 12;
        private final Calendar calendar = Calendar.getInstance();
        private Integer firstMonth = calendar.get(Calendar.MONTH);
        private LayoutInflater inflater;
        private Integer lastMonth = (calendar.get(Calendar.MONTH) - 1) % MONTHS_IN_YEAR;
        private Integer startYear = calendar.get(Calendar.YEAR);

        public CalendarAdapter(Context context) {
            inflater = LayoutInflater.from(context);
            mContext = context;
            if (maxDate != null) {
                lastMonth = TimeUtils.getMonth(maxDate) - 1;
            }
            if (minDate != null) {
                startYear = TimeUtils.getYear(minDate);
                firstMonth = TimeUtils.getMonth(minDate) - 1;
            }
        }


        @Override
        public CharSequence getPageTitle(int position) {
            int year = position / MONTHS_IN_YEAR + startYear + ((firstMonth + (position % MONTHS_IN_YEAR)) / MONTHS_IN_YEAR);
            int month = (firstMonth + (position % MONTHS_IN_YEAR)) % MONTHS_IN_YEAR;
            return String.format("%s年%s月", year, leftPad(String.valueOf(month + 1), 2, "0"));
        }

        @Override
        public int getCount() {
            //原来通过年份来确定最多显示几个月的逻辑，注释掉，不要删除
            int maxYear = mController.getMaxYear();
            int minYear = calendar.get(Calendar.YEAR);
            if (maxDate != null) {
                maxYear = TimeUtils.getYear(maxDate);
            }
            if (minDate != null) {
                minYear = TimeUtils.getYear(minDate);
            }

            int itemCount = (maxYear - minYear + 1) * MONTHS_IN_YEAR;

            if (firstMonth != -1)
                itemCount -= firstMonth;

            if (lastMonth != -1)
                itemCount -= (MONTHS_IN_YEAR - lastMonth) - 1;

            return itemCount;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            GridView mGridView = mViewMap.get(position);
            if (mGridView == null) {
                mGridView = (GridView) inflater.inflate(R.layout.item_page_month_day, container, false);
                mViewMap.put(position, mGridView);
            }
            int year = position / MONTHS_IN_YEAR + startYear + ((firstMonth + (position % MONTHS_IN_YEAR)) / MONTHS_IN_YEAR);
            int month = (firstMonth + (position % MONTHS_IN_YEAR)) % MONTHS_IN_YEAR;
            DateBean dateBean = new DateBean(year, month + 1);
            mGridView.setOnItemClickListener(this);

            mGridView.setAdapter(new MyGridAdapter(dateBean));
            container.addView(mGridView);
            return mGridView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MyGridAdapter gridAdapter = (MyGridAdapter) parent.getAdapter();
            int day = (int) gridAdapter.getItem(position);
            if (day == -1) {
                return;
            }
            DateBean bean = gridAdapter.getDateBean();
            List<ResponsePrice> list = gridAdapter.getProductDatePriceList();
            if (mController != null) {
                if (list != null && !list.isEmpty()) {
                    mController.onDayOfMonthAndDataSelected(bean.currentYear, bean.currentMonth, day + 1, list);
                } else {
                    mController.onDayOfMonthSelected(bean.currentYear, bean.currentMonth, day + 1);
                    //当点击的日期是大于当前日期的才更新item
                    if(!TimeUtils.isPreDate(bean.currentYear, bean.currentMonth, day + 1)) {
                        gridAdapter.setSelectedPosition(position);
                        gridAdapter.notifyDataSetChanged();
                    }
                }
            }
        }

    }


    /**
     * GridView的Adapter
     **/
    class MyGridAdapter extends BaseAdapter {


        private DateBean mDateBean;
        private int days;
        private int dayOfWeeks;
        private List mProductDatePriceList;
        private int selectedPosition=-1;

        public DateBean getDateBean() {
            return mDateBean;
        }

        public MyGridAdapter(DateBean dateBean) {
            this.mDateBean = dateBean;
            if (mYearMonthMap != null) {
                this.mProductDatePriceList = mYearMonthMap.get(String.format("%s-%s", dateBean.currentYear, leftPad(dateBean.currentMonth + "", 2, "0")));
            }
            GregorianCalendar c = new GregorianCalendar(dateBean.currentYear, dateBean.currentMonth - 1, 0);
            days = TimeUtils.getDaysOfMonth(dateBean.currentYear, dateBean.currentMonth); //返回当前月的总天数。
            dayOfWeeks = c.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeeks == 7) {
                dayOfWeeks = 0;
            }
        }

        public void setSelectedPosition(int position){
            selectedPosition=position;
        }

        public List getProductDatePriceList() {
            return mProductDatePriceList;
        }

        @Override
        public int getCount() {
            return days + dayOfWeeks;
        }

        @Override
        public Object getItem(int i) {
            if (i < dayOfWeeks) {
                return -1;
            } else {
                return i - dayOfWeeks;
            }
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            GridViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_day, viewGroup, false);
                viewHolder = new GridViewHolder();
                viewHolder.mTextView = (TextView) view.findViewById(R.id.day_tv);
                viewHolder.mPriceTv = (TextView) view.findViewById(R.id.price_tv);
                viewHolder.mLineView = view.findViewById(R.id.line_view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (GridViewHolder) view.getTag();
            }
            int item = (int) getItem(i);
            //在这个月第一天之前的，不显示
            if (item == -1) {
                viewHolder.mTextView.setText("");
                //viewHolder.mPriceTv.setText("");
            } else {
                viewHolder.mTextView.setText(String.valueOf(item + 1));
                //viewHolder.mPriceTv.setText("");
                //这块渲染的先后顺序不要改，先判断是否是选择的，选择的背景为橘黄色
                if(selectedPosition==i){
                    viewHolder.mTextView.setBackgroundResource(R.drawable.shape_circle_orange);
                    viewHolder.mTextView.setTextColor(Color.WHITE);
                }else{
                    //没有选择的，背景为透明
                    viewHolder.mTextView.setBackgroundColor(Color.TRANSPARENT);
                    //先判断目前item的日期是否是当前日期之前，如果是之前就把字体设置成灰色
                    if(TimeUtils.isPreDate(mDateBean.currentYear, mDateBean.currentMonth,item + 1)){
                        viewHolder.mTextView.setTextColor(Color.parseColor("#babbbc"));
                    }else{
                        //如果是周六日 显示为橘黄色
                        if (i % 7 == 0 || i % 7 == 6) {
                            viewHolder.mTextView.setTextColor(Color.parseColor("#fe5600"));
                        }else {
                            //如果不是在当前日期之前，并且不是周六日，就渲染成黑色
                            viewHolder.mTextView.setTextColor(Color.parseColor("#4D4D4D"));
                        }
                    }

                }



                //司机端没有价格日历，以下代码不会执行
                if (mProductDatePriceList != null) {
                    //日期的颜色为灰色
                    viewHolder.mTextView.setEnabled(false);
                    //整个item背景为白色
                    view.setEnabled(false);
                    for (Object obj : mProductDatePriceList) {//用于展示价格等额外的属性
                        if (mController != null) {
                            mController.showOtherFields(obj, view, mDateBean.currentYear, mDateBean.currentMonth, item + 1);
                        }
                    }
                }
            }
            return view;
        }
    }

    static class DateBean {
        private int currentYear;
        private int currentMonth;

        public DateBean(int currentYear, int currentMonth) {
            this.currentYear = currentYear;
            this.currentMonth = currentMonth;
        }
    }

    public static class GridViewHolder {
        public TextView mTextView;
        public View mLineView;
        public TextView mPriceTv;
    }

    class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        @Override
        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }


    public interface DatePickerController {


        /**
         * 得到最多显示年数
         **/
        int getMaxYear();

        /**
         * 得到最多显示几个月
         **/
        //int getMaxMonth();

        void onDayOfMonthSelected(int year, int month, int day);

        void onDayOfMonthAndDataSelected(int year, int month, int day, List obj);

        //展示其它属性(用于扩展数据  日期相等时设置显示效果)
        void showOtherFields(Object obj, View view, int gridItemYear, int gridItemMonth, int gridItemDay);

        Map<String, List> getDataSource();
    }

    public interface OnClickPreNextListerner {
        /**
         * 下一个月的点击回调
         */
        void nextMonth();

        /**
         * 上一个月的点击回调
         */
        void preMonth();
    }


    /**
     * <p>Left pad a String with a specified String.</p>
     * <p>
     * <p>Pad to a size of <code>size</code>.</p>
     * <p>
     * <pre>
     * StringUtils.leftPad(null, *, *)      = null
     * StringUtils.leftPad("", 3, "z")      = "zzz"
     * StringUtils.leftPad("bat", 3, "yz")  = "bat"
     * StringUtils.leftPad("bat", 5, "yz")  = "yzbat"
     * StringUtils.leftPad("bat", 8, "yz")  = "yzyzybat"
     * StringUtils.leftPad("bat", 1, "yz")  = "bat"
     * StringUtils.leftPad("bat", -1, "yz") = "bat"
     * StringUtils.leftPad("bat", 5, null)  = "  bat"
     * StringUtils.leftPad("bat", 5, "")    = "  bat"
     * </pre>
     *
     * @param str    the String to pad out, may be null
     * @param size   the size to pad to
     * @param padStr the String to pad with, null or empty treated as single space
     * @return left padded String or original String if no padding is necessary,
     * <code>null</code> if null String input
     */
    public static String leftPad(String str, int size, String padStr) {
        if (str == null) {
            return null;
        }
        if (isEmpty(padStr)) {
            padStr = " ";
        }
        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (padLen == 1 && pads <= PAD_LIMIT) {
            return leftPad(str, size, padStr.charAt(0));
        }

        if (pads == padLen) {
            return padStr.concat(str);
        } else if (pads < padLen) {
            return padStr.substring(0, pads).concat(str);
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return new String(padding).concat(str);
        }
    }

    /**
     * <p>Left pad a String with a specified character.</p>
     * <p>
     * <p>Pad to a size of <code>size</code>.</p>
     * <p>
     * <pre>
     * StringUtils.leftPad(null, *, *)     = null
     * StringUtils.leftPad("", 3, 'z')     = "zzz"
     * StringUtils.leftPad("bat", 3, 'z')  = "bat"
     * StringUtils.leftPad("bat", 5, 'z')  = "zzbat"
     * StringUtils.leftPad("bat", 1, 'z')  = "bat"
     * StringUtils.leftPad("bat", -1, 'z') = "bat"
     * </pre>
     *
     * @param str     the String to pad out, may be null
     * @param size    the size to pad to
     * @param padChar the character to pad with
     * @return left padded String or original String if no padding is necessary,
     * <code>null</code> if null String input
     * @since 2.0
     */
    public static String leftPad(String str, int size, char padChar) {
        if (str == null) {
            return null;
        }
        int pads = size - str.length();
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (pads > PAD_LIMIT) {
            return leftPad(str, size, String.valueOf(padChar));
        }
        return padding(pads, padChar).concat(str);
    }

    /**
     * <p>Returns padding using the specified delimiter repeated
     * to a given length.</p>
     * <p>
     * <pre>
     * StringUtils.padding(0, 'e')  = ""
     * StringUtils.padding(3, 'e')  = "eee"
     * StringUtils.padding(-2, 'e') = IndexOutOfBoundsException
     * </pre>
     * <p>
     * <p>Note: this method doesn't not support padding with
     * <a href="http://www.unicode.org/glossary/#supplementary_character">Unicode Supplementary Characters</a>
     * as they require a pair of <code>char</code>s to be represented.
     * If you are needing to support full I18N of your applications
     * consider using  instead.
     * </p>
     *
     * @param repeat  number of times to repeat delim
     * @param padChar character to repeat
     * @return String with repeated character
     * @throws IndexOutOfBoundsException if <code>repeat &lt; 0</code>
     */
    private static String padding(int repeat, char padChar) throws IndexOutOfBoundsException {
        if (repeat < 0) {
            throw new IndexOutOfBoundsException("Cannot pad a negative amount: " + repeat);
        }
        final char[] buf = new char[repeat];
        for (int i = 0; i < buf.length; i++) {
            buf[i] = padChar;
        }
        return new String(buf);
    }

}
