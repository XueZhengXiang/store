package com.project.store.util;

import lombok.Data;
/**json格式*/
import java.io.Serializable;

@Data
public class JsonResult<E> implements Serializable {
    private Integer state;//状态
    private E data;//数据
    private String message;//信息

    public JsonResult() {
    }

    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }

    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }

    public JsonResult(int state) {
        this.state=state;
    }
}
