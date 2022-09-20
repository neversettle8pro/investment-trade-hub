package com.proj.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author JerryLiu
 * @Date 2022/9/5 14:14
 * @PackageName:com.proj.po
 * @ClassName: ProductInfo
 * @Description: TODO
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo {
    private Integer pid;
    private Integer amount;
    private BigDecimal price;
    private BigDecimal rate;
    private String delivery;
    private Date date;

    @Override
    public String toString() {
        Calendar cal = Calendar.getInstance();

        Date date1 = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.format(date1);
        cal.setTime(date);
        if((cal.get(cal.MONTH)+1)<10) {
            return "ProductInfo{" +
                    "pid=" + pid +
                    ", amount=" + amount +
                    ", price=" + price +
                    ", rate=" + rate +
                    ", delivery='" + delivery + '\'' +
                    ", date=" + cal.get(cal.YEAR) + '-' + 0 + (cal.get(cal.MONTH)+1) + '-' + cal.get(cal.DATE) +
                    '}';
        }
        else {
            return "ProductInfo{" +
                    "pid=" + pid +
                    ", amount=" + amount +
                    ", price=" + price +
                    ", rate=" + rate +
                    ", delivery='" + delivery + '\'' +
                    ", date=" + cal.get(cal.YEAR) + '-' + (cal.get(cal.MONTH)+1) + '-' + cal.get(cal.DATE) +
                    '}';
        }
    }
}
