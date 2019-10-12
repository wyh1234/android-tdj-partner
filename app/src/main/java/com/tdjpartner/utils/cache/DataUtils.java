package com.tdjpartner.utils.cache;

/**
 *
 * 保存字段
 */


public class DataUtils {
    private static final String SP_NAME = "Partner_Data";
    private static final String FirstStartup = "FirstStartup";
    private static final String KEY_BG = "KEY_BG";

    private final SPUtils mSPUtils = SPUtils.newInstance(SP_NAME);

    private static class Holder {
        private static final DataUtils INSTANCE = new DataUtils();
    }

    public static DataUtils getInstance() {
        return Holder.INSTANCE;
    }

    private DataUtils() {
    }

    public void setFirstStartup(Boolean firstStartup) {
        mSPUtils.save(FirstStartup, firstStartup);
    }

    public Boolean getFirstStartup() {
        return mSPUtils.get(FirstStartup, false);
    }

    public void setBg(String icon) {
        mSPUtils.save(KEY_BG, icon);
    }

    public String getBg() {
        return mSPUtils.get(KEY_BG, "");
    }
}
