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



    static public class AfterSaleInfo {

        public int driver_id = 0; //7659
        public int product_id = 0; //69909
        public int shipping_line_id = 0; //267
        public int station_id = 0; //9
        public int entity_id = 0; //71987
        public int order_item_id = 0; //11876200
        public int specification_id = 0; //116061
        public int order_id = 0; //10003575
        public int website_id = 0; //3
        public int store_id = 0; //2716
        public int customer_id = 0; //258740
        public float level_2_value = 0; //0
        public float level_3_value = 0; //
        public int level_type = 0; //1
        public int pick_user_type = 0; //2
        public int problem_type = 0; //6
        public int apply_type = 0; //2
        public int after_process_type = 0; //1
        public int province = 0; //13
        public int status = 0; //3
        public int city = 0; //193
        public int isC = 0; //0

        public float original_price = 0.0f; //40
        public float discount_price = 0.0f; //40
        public float shop_coupon_amount = 0.0f; //0
        public float price = 0.0f; //40
        public float original_amount = 0.0f; //1
        public float discount_avg_price = 0.0f; //40
        public float avg_price = 0.0f; //40
        public float discount_total_price = 0.0f; //40
        public float original_total_price = 0.0f; //40
        public float coupon_amount = 0.0f; //0
        public float amount = 0.0f; //1
        public float total_price = 0.0f; //40
        public float pay_amount = 0.0f; //0
        public float commission = 0.0f; //0.8

        public double lat; //"22.539889",
        public double lon; //114.082696",

        public String driver_remark = ""; //
        public String customer_tel = ""; //13995566720
        public String qr_code_id = ""; //9210308000027
        public String addition_remark = ""; //
        public String sku = ""; //
        public String problemDes = ""; //发错货
        public String station_name = ""; //武泰闸配送中心
        public String problem_description = ""; //把
        public String shipping_line_code = ""; //1004-7
        public String customer_address = ""; //汇通新长江中心B座1102室
        public String dispatch_time = ""; //2021-03-08 10:05
        public String create_time = ""; //2021-03-08 10:04
        public String streetNumber = ""; //测试
        public String product_criteria = ""; //1=通, 2=精
        public String operator_account = ""; //13995567535
        public String hotel_name = ""; //测试门店720
        public String operator_user_name = ""; //吴玲
        public String order_pay_time = ""; //2021-03-08 09:57
        public String unit = ""; //斤
        public String out_trade_no = ""; //0003_KQwA3DZcRa20210308095750749
        public String level_2_unit = ""; //
        public String nick_name = ""; //
        public String supplier_tel = ""; //12345678009
        public String name = ""; //土鸡蛋
        public String order_no = ""; //67745084142037934084
        public String commissionerName = ""; //101
        public String receive_user_name = ""; //企业720
        public String receive_user_tel = ""; //13995566720
        public String level_3_unit = ""; //
        public String receive_hotel_name = ""; //测试门店720
        public String after_sales_no = ""; //1615169084894
        public String city_name = ""; //武汉
        public String store_name = ""; //测试环境店铺
        public String submitter_name = ""; //测试门店720
        public String supplier_name = ""; //测试WL
        public String receive_address = ""; //_汇通新长江中心B座1102室(测试)
        public String commissionerPhone = ""; //13995566101
        public String create_order_time = ""; //2021-03-08 09:57
        public String required_delivery_time = ""; //2021-03-08 08:00
        public String pick_user_name = ""; //张家虎
        public String pick_finish_time = ""; //2021-03-08 08:00
        public String pick_user_phone = ""; //12345678106
        public String submitter_tel = ""; //13995566720
        public String avg_unit = ""; //斤
        public String commissioner_name = ""; //吴玲
        public String customer_name = ""; //企业720
        public String certificate_photos = ""; //http://tsp-img.oss-cn-hangzhou.aliyuncs.com/2103081004424009e21f.jpg

        @Override
        public String toString() {
            return "AfterSaleInfo{" +
                    "driver_id=" + driver_id +
                    ", product_id=" + product_id +
                    ", shipping_line_id=" + shipping_line_id +
                    ", station_id=" + station_id +
                    ", entity_id=" + entity_id +
                    ", order_item_id=" + order_item_id +
                    ", specification_id=" + specification_id +
                    ", order_id=" + order_id +
                    ", website_id=" + website_id +
                    ", store_id=" + store_id +
                    ", customer_id=" + customer_id +
                    ", level_2_value=" + level_2_value +
                    ", level_3_value=" + level_3_value +
                    ", level_type=" + level_type +
                    ", pick_user_type=" + pick_user_type +
                    ", problem_type=" + problem_type +
                    ", apply_type=" + apply_type +
                    ", after_process_type=" + after_process_type +
                    ", province=" + province +
                    ", status=" + status +
                    ", city=" + city +
                    ", isC=" + isC +
                    ", original_price=" + original_price +
                    ", discount_price=" + discount_price +
                    ", shop_coupon_amount=" + shop_coupon_amount +
                    ", price=" + price +
                    ", original_amount=" + original_amount +
                    ", discount_avg_price=" + discount_avg_price +
                    ", avg_price=" + avg_price +
                    ", discount_total_price=" + discount_total_price +
                    ", original_total_price=" + original_total_price +
                    ", coupon_amount=" + coupon_amount +
                    ", amount=" + amount +
                    ", total_price=" + total_price +
                    ", pay_amount=" + pay_amount +
                    ", commission=" + commission +
                    ", driver_remark='" + driver_remark + '\'' +
                    ", customer_tel='" + customer_tel + '\'' +
                    ", qr_code_id='" + qr_code_id + '\'' +
                    ", addition_remark='" + addition_remark + '\'' +
                    ", sku='" + sku + '\'' +
                    ", problemDes='" + problemDes + '\'' +
                    ", station_name='" + station_name + '\'' +
                    ", problem_description='" + problem_description + '\'' +
                    ", shipping_line_code='" + shipping_line_code + '\'' +
                    ", customer_address='" + customer_address + '\'' +
                    ", dispatch_time='" + dispatch_time + '\'' +
                    ", create_time='" + create_time + '\'' +
                    ", streetNumber='" + streetNumber + '\'' +
                    ", product_criteria='" + product_criteria + '\'' +
                    ", operator_account='" + operator_account + '\'' +
                    ", hotel_name='" + hotel_name + '\'' +
                    ", operator_user_name='" + operator_user_name + '\'' +
                    ", order_pay_time='" + order_pay_time + '\'' +
                    ", unit='" + unit + '\'' +
                    ", out_trade_no='" + out_trade_no + '\'' +
                    ", level_2_unit='" + level_2_unit + '\'' +
                    ", nick_name='" + nick_name + '\'' +
                    ", supplier_tel='" + supplier_tel + '\'' +
                    ", name='" + name + '\'' +
                    ", order_no='" + order_no + '\'' +
                    ", commissionerName='" + commissionerName + '\'' +
                    ", receive_user_name='" + receive_user_name + '\'' +
                    ", receive_user_tel='" + receive_user_tel + '\'' +
                    ", level_3_unit='" + level_3_unit + '\'' +
                    ", receive_hotel_name='" + receive_hotel_name + '\'' +
                    ", after_sales_no='" + after_sales_no + '\'' +
                    ", city_name='" + city_name + '\'' +
                    ", store_name='" + store_name + '\'' +
                    ", submitter_name='" + submitter_name + '\'' +
                    ", supplier_name='" + supplier_name + '\'' +
                    ", receive_address='" + receive_address + '\'' +
                    ", commissionerPhone='" + commissionerPhone + '\'' +
                    ", create_order_time='" + create_order_time + '\'' +
                    ", required_delivery_time='" + required_delivery_time + '\'' +
                    ", pick_user_name='" + pick_user_name + '\'' +
                    ", pick_finish_time='" + pick_finish_time + '\'' +
                    ", pick_user_phone='" + pick_user_phone + '\'' +
                    ", submitter_tel='" + submitter_tel + '\'' +
                    ", avg_unit='" + avg_unit + '\'' +
                    ", commissioner_name='" + commissioner_name + '\'' +
                    ", customer_name='" + customer_name + '\'' +
                    ", certificate_photos='" + certificate_photos + '\'' +
                    ", store_img='" + store_img + '\'' +
                    ", customer_img='" + customer_img + '\'' +
                    ", product_img='" + product_img + '\'' +
                    '}';
        }

        public String store_img = ""; //http://tsp-img.oss-cn-hangzhou.aliyuncs.com/201116151846db9ccb9b.jpg
        public String customer_img = ""; //http://tsp-img.oss-cn-hangzhou.aliyuncs.com/2008141407060fb47167.jpg
        public String product_img = ""; //http://tsp-img.oss-cn-hangzhou.aliyuncs.com/201120151850ddb36961.jpg

    }
}
