package com.tdjpartner.http;

/**
 * Created by Administrator on 2018/3/24.
 */

public class BaseResponse<T> {
    private int code;
    private String msg;
    private T data;
    private PageinfoBean pageinfo;
    public T getData() {
        return data;
    }
    public PageinfoBean getPageinfo() {
        return pageinfo;
    }

    public void setPageinfo(PageinfoBean pageinfo) {
        this.pageinfo = pageinfo;
    }
    public void setData(T data) {
        this.data = data;
    }

    public void setCode(int err) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;

    }


    public boolean isSuccess(){
        return code == 0;
    }
    public static class PageinfoBean {
        /**
         * curPage : 1
         * pageSize : 15
         * totalPages : 1
         */

        private int curPage;
        private int pageSize;
        private int totalPages;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }
    }
}
