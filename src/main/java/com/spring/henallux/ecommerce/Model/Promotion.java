package com.spring.henallux.ecommerce.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {
    private int id;
    private String labelEn;
    private String labelFr;
    private Date beginDate;
    private Date endDate;
    private int percentage;
    private String type;

    public Boolean isPromotionValid() {
        Date currentDate = new Date();
        return currentDate.after(beginDate) && currentDate.before(endDate);
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "id=" + id +
                ", labelEn='" + labelEn + '\'' +
                ", labelFr='" + labelFr + '\'' +
                ", beginDate='" + beginDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", percentage='" + percentage + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
