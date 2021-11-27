package io.renren.modules.generator.common;

import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductConstants {

    /**
     * 计算单位类型
     */
    @Getter
    public enum ProductBillingType{
        MI(1,"米"),
        GE(0,"个");

        private int billingType;
        private String desc;

        ProductBillingType(int billingType,String desc){
            this.billingType = billingType;
            this.desc = desc;
        }

        public static String getName(int typeId) {
            for(ProductBillingType t : ProductBillingType.values()) {
                if(t.getBillingType() == typeId) {
                    return t.name();
                }
            }
            return "";
        }
        public static String  getDescByType(int typeId){
            for (ProductBillingType value : ProductBillingType.values()) {
                if(value.getBillingType()==typeId){
                    return value.desc;
                }
            }
            return "";
        }

    }

    /**
     * 产品类型
     */
    @Getter
    public enum ProductType {
        GJG(1, "钢架管", ProductBillingType.MI),
        KJ(2, "扣件", ProductBillingType.GE),
        DT(3, "顶托", ProductBillingType.GE),
        PKJ(4, "盘扣架", ProductBillingType.MI);

        private int productType;
        private String desc;
        private ProductBillingType billingType;

        ProductType(int productType, String desc,ProductBillingType billingType) {
            this.productType = productType;
            this.desc = desc;
            this.billingType = billingType;
        }

        public static String getName(int typeId) {
            for(ProductType t : ProductType.values()) {
                if(t.getProductType() == typeId) {
                    return t.name();
                }
            }
            return "";
        }
        public static String  getDescByType(int typeId){
            for (ProductType value : ProductType.values()) {
                if(value.getProductType()==typeId){
                    return value.desc;
                }
            }
            return "";
        }

        public static List toList() {
            List list = Lists.newArrayList();
            for (ProductType value : ProductType.values()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("productType", value.productType);
                map.put("desc", value.desc);
                list.add(map);
            }
            return list;
        }

    }
}
