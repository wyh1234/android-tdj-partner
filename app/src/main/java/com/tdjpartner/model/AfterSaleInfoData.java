package com.tdjpartner.model;

import java.util.List;

/**
 * Created by wyh on 2021/3/18.
 */
public class AfterSaleInfoData {

    public int buTotalNum = 0; //2
    public int huanTotalNum = 0; //2
    public int geting_size = 0; //5
    public int over_size = 0; //0
    public int huanFinishNum = 0; //0
    public int buFinishNum = 0; //0
    public int tab = 0; //0
    public int tuiTotalNum = 0; //1
    public int tuiFinishNum = 0; //0

    public List<AfterSaleInfo> buGeting_list = null;
    public List<AfterSaleInfo> buOver_list = null;
    public List<AfterSaleInfo> tuiGeting_list = null;
    public List<AfterSaleInfo> huanGeting_list = null;
    public List<AfterSaleInfo> tuiOver_list = null;
    public List<AfterSaleInfo> huanOver_list = null;


    @Override
    public String toString() {
        return "AfterSaleInfoData{" +
                "buTotalNum=" + buTotalNum +
                ", huanTotalNum=" + huanTotalNum +
                ", geting_size=" + geting_size +
                ", over_size=" + over_size +
                ", huanFinishNum=" + huanFinishNum +
                ", buFinishNum=" + buFinishNum +
                ", tab=" + tab +
                ", tuiTotalNum=" + tuiTotalNum +
                ", tuiFinishNum=" + tuiFinishNum +
                ", buGeting_list=" + buGeting_list +
                ", buOver_list=" + buOver_list +
                ", tuiGeting_list=" + tuiGeting_list +
                ", huanGeting_list=" + huanGeting_list +
                ", tuiOver_list=" + tuiOver_list +
                ", huanOver_list=" + huanOver_list +
                '}';
    }
}
