package com.tdjpartner.utils.cache;



public class LoginCache {
//    private final static String fileName = "zukehuose_logincache";
//    private static LoginCache instance = null;
//    private ACache mAcache = null;
//
//    public LoginCache() {
//        if (MyApplication.getAppContext() != null) {
//            mAcache = ACache.get(MyApplication.getAppContext(), fileName);
//        }
//    }
//
//    public static LoginCache getInstance() {
//        if (instance == null) {
//            synchronized (LoginCache.class) {
//                if (instance == null) {
//                    instance = new LoginCache();
//                }
//            }
//        }
//        return instance;
//    }
//
//    public void save(Userinfo userinfo) {
//        if (mAcache != null) {
//            mAcache.put(this.getClass().getSimpleName(),userinfo);
//        } else {
//            if (MyApplication.getAppContext() != null) {
//                mAcache = ACache.get(MyApplication.getAppContext(), fileName);
//                mAcache.put(this.getClass().getName(), userinfo);
//            }
//        }
//    }
//
//    public Userinfo get() {
//        if (mAcache == null)
//            return null;
//        return (Userinfo) mAcache.getAsObject(this.getClass().getSimpleName());
//    }
//
//    public boolean remove() {
//        if (mAcache == null)
//            return false;
//        return mAcache.remove(this.getClass().getSimpleName());
//    }
}
