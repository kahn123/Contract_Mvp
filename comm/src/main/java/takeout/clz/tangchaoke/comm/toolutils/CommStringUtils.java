package takeout.clz.tangchaoke.comm.toolutils;


import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import takeout.clz.tangchaoke.comm.R;


/**
 * explain:
 * Created by: Zhao
 * Created time: 2016/10/13 10:03
 */

public class CommStringUtils {
    Context context;
    //手机号正则表达式
    public final static String PHONE = "^1[123456789]\\d{9}$";

    /**
     * 判断一个字符串是否是null或空字符串""
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        boolean result = s == null || "".equals(s) || "null".equals(s);
        return result;
    }

    /**
     * 是否是正确的金额格式
     *
     * @param money
     * @return
     */
    public static boolean isExactlyMoney(String money) {

        return money.matches("^[0-9]+(.[0-9])?$");
    }

    /**
     * 将价格保存为两位小数
     *
     * @param price
     * @return
     */
    public static String priceFormat(String price) {
        if (isEmpty(price)) {
            return null;
        }
        float pF = Float.valueOf(price);
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        return nf.format(pF);
    }

    /**
     * 根据提现状态显示不同的富文本
     *
     * @param string 要转化的字符串
     * @return 返回的富文本对象
     */
    public static CharSequence getFormatStringOne(String string) {
        SpannableString ss = new SpannableString(string);
        RelativeSizeSpan textSizeSpan = new RelativeSizeSpan(1.5f);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        ss.setSpan(textSizeSpan, 1, string.length(), SpannableString.SPAN_EXCLUSIVE_INCLUSIVE);
        ss.setSpan(boldSpan, 1, string.length(), SpannableString.SPAN_EXCLUSIVE_INCLUSIVE);
        return ss;
    }

    /**
     * 根据提现状态显示不同的富文本
     *
     * @param string 要转化的字符串
     * @return 返回的富文本对象
     */
    public static CharSequence getFormatStringZero(String string) {
        SpannableString ss = new SpannableString(string);
        RelativeSizeSpan textSizeSpan = new RelativeSizeSpan(1.5f);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        ss.setSpan(textSizeSpan, 0, string.length(), SpannableString.SPAN_EXCLUSIVE_INCLUSIVE);
        ss.setSpan(boldSpan, 0, string.length(), SpannableString.SPAN_EXCLUSIVE_INCLUSIVE);
        return ss;
    }
    /**
     * 根据提现状态显示不同的富文本
     *
     * @param string 要转化的字符串
     * @return 返回的富文本对象
     */
    public static CharSequence getFormatCharSequence(String string,Context context) {
        SpannableString ss = new SpannableString(string);
        RelativeSizeSpan textSizeSpan = new RelativeSizeSpan(1.5f);
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(context, R.color.color_333333));
        int index = string.indexOf('：');
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        ss.setSpan(colorSpan, 0, index + 1, SpannableString.SPAN_INCLUSIVE_INCLUSIVE);
        ss.setSpan(textSizeSpan, index + 2, string.length(), SpannableString.SPAN_EXCLUSIVE_INCLUSIVE);
        ss.setSpan(boldSpan, index + 2, string.length(), SpannableString.SPAN_EXCLUSIVE_INCLUSIVE);
        return ss;
    }

    /**
     * 获取格式化时间，如：2017-12-20 12:30:00
     *
     * @param timeStamp 时间戳
     * @return 格式化的时间
     */
    public static String getAccurateTime(String timeStamp) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(Long.parseLong(timeStamp) * 1000);
            return format.format(date);
        } catch (Exception e) {
//            Log.e("时间转化方法异常：" + e.getMessage());
        }
        return timeStamp;
    }

    /**
     * 将字符串格式化为带两位小数的字符串
     *
     * @param str 字符串
     * @return
     */
    public static String format2Decimals(String str) {
        DecimalFormat df = new DecimalFormat("#.00");
        if (df.format(stringToDouble(str)).startsWith(".")) {
            return "0" + df.format(stringToDouble(str));
        } else {
            return df.format(stringToDouble(str));
        }
    }

    /**
     * 字符串转换成double ,转换失败将会 return 0;
     *
     * @param str 字符串
     * @return
     */
    public static double stringToDouble(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        } else {
            try {
                return Double.parseDouble(str);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }


//    public static void clearNotication() {
//        NotificationManager notificationManager = (NotificationManager) App.getApplication().getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.cancelAll();
//    }
}

