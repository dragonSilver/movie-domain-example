package net.dg.generic.base;

import static org.joda.time.Days.*;

public enum DayOfWeek {

    MON(ONE.getDays(), "월요일"),
    TUE(TWO.getDays(), "화요일"),
    WED(THREE.getDays(), "수요일"),
    THU(FOUR.getDays(), "목요일"),
    FRI(FIVE.getDays(), "금요일"),
    SAT(SIX.getDays(), "토요일"),
    SUN(SEVEN.getDays(), "일요일")
    ;

    Integer code;
    String desc;

    DayOfWeek(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
