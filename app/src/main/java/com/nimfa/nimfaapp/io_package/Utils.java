package com.nimfa.nimfaapp.io_package;

import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nimfa.nimfaapp.R;
import com.nimfa.nimfaapp.dataModels.Currency;
import com.nimfa.nimfaapp.dataModels.Details;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * NimfaApp
 * Created by Alex on 05.07.2017.
 * contact on luck.alex13@gmail.com
 * © Alexander Novikov 2017
 */

public class Utils {
    private static final String TAG = "LOG_";
    private static boolean DEBUG = true;


    public static void p(Object obj) {
        if (!DEBUG)
            return;

        if (obj != null)
            Log.d(TAG, obj.toString());
    }

    public static void p(String tag, Object obj) {
        if (!DEBUG)
            return;

        if (obj != null)
            Log.d(TAG + tag, obj.toString());
    }

    public static String getFormattedNextDate(long timeStamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp * 1000);
        p(TAG, calendar.getTimeInMillis());
        DateFormat sdf = new SimpleDateFormat("dd.MM HH:mm", new Locale("ru", "RU"));
        return sdf.format(calendar.getTime());
    }

    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    public static void setPaddings(View v, int l, int t, int r, int b) {
        v.setPadding(l, t, r, b);
    }

    public static void copyText2Clipboard(String text, Context context) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Main", text);
        clipboard.setPrimaryClip(clip);
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showToast(Context context, int message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showToastShort(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showToastShort(Context context, int message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static PendingIntent createPendingIntent(Context context, Class<?> clazz) {
        return PendingIntent.getActivity(context, 102, createIntent(context, clazz), 0);
    }

    public static PendingIntent createPendingIntent(Context context, Intent intent) {
        return PendingIntent.getActivity(context, 103, intent, 0);
    }

    public static Intent createIntent(Context context, Class<?> clazz) {
        return new Intent(context, clazz);
    }

    public static Intent createIntent(Context context, Class<?> clazz, String action) {
        Intent intent = new Intent(context, clazz);
        intent.setAction(action);
        return intent;
    }

    public static Intent createIntent(Context context, Class<?> clazz, String action, String keyData, String data) {
        Intent intent = new Intent(context, clazz);
        intent.setAction(action);
        intent.putExtra(keyData, data);
        return intent;
    }

    public static Intent createIntent(Context context, Class<?> clazz, String action, String keyData, int data) {
        Intent intent = new Intent(context, clazz);
        intent.setAction(action);
        intent.putExtra(keyData, data);
        return intent;
    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    public static String getAndroidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static ArrayList<Currency> generateItems(Context context) {
        ArrayList<Currency> currencies = new ArrayList<>();
        ArrayList<Details> bitcoin = new ArrayList<>();
        bitcoin.add(new Details(context.getString(R.string.market_cap), "$39,055,604,858 "));
        bitcoin.add(new Details(context.getString(R.string.volume_24), "$1,590,080,000 "));
        bitcoin.add(new Details(context.getString(R.string.circulating_supply), "16,441,350 BTC"));
        bitcoin.add(new Details(context.getString(R.string.max_supply), "21,000,000 BTC "));
        currencies.add(new Currency("Bitcoin", 2610, R.drawable.ic_bitcoin_gold, bitcoin, "https://bitcoin.org/ru/"));

        ArrayList<Details> ethereum = new ArrayList<>();
        ethereum.add(new Details(context.getString(R.string.market_cap), "$2,409,843,179"));
        ethereum.add(new Details(context.getString(R.string.volume_24), "$488,334,000"));
        ethereum.add(new Details(context.getString(R.string.circulating_supply), "51,942,432 LTC"));
        ethereum.add(new Details(context.getString(R.string.max_supply), "84,000,000 LTC"));

        currencies.add(new Currency("Ethereum", 286, R.drawable.ic_ethereum, ethereum, "https://www.ethereum.org/"));
        currencies.add(new Currency("Ripple", 0.2605f, R.drawable.ic_ripple, ethereum, "https://ripple.com/"));
        currencies.add(new Currency("Litecoin", 50, R.drawable.ic_litecoin, ethereum, "https://litecoin.com/"));
        return currencies;
    }

    public static ArrayList<Currency> generateBagItems() {
        ArrayList<Currency> currencies = new ArrayList<>();
        currencies.add(new Currency("Bitcoin", 2610, R.drawable.ic_bitcoin_gold, 20));
        currencies.add(new Currency("Ethereum", 286, R.drawable.ic_ethereum, 15));
        currencies.add(new Currency("Ripple", 0.2605f, R.drawable.ic_ripple, 10));
        currencies.add(new Currency("Litecoin", 50, R.drawable.ic_litecoin, 10));
        return currencies;
    }

    public static String getString(Context context, int string) {
        return context.getString(string);
    }
}
