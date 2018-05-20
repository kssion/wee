package cc.nsurl.wee.model;

import java.util.List;

public class Result {
    private int status;
    private String msg;
    private Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    Result(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static Result simple(Object data) {
        return simple("Not Found", data);
    }

    public static Result simple(String errMsg, Object data) {
        if (data instanceof List) {
            return success("ok", data);
        }
        boolean e = data == null;
        return new Result(e ? 1 : 0, e ? errMsg : "ok", data);
    }

    public static Result error(String msg) {
        return new Result(1, msg, null);
    }

    public static Result error(String msg, Object data) {
        return new Result(1, msg, data);
    }

    public static Result success(String msg) {
        return new Result(0, msg, null);
    }

    public static Result success(String msg, Object data) {
        return new Result(0, msg, data);
    }
}

