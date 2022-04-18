package result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zby
 * @title: R
 * @projectName zj-shop
 * @description: 统一结果类
 *               1外接只可以调用统一返回类的方法，不可以直接创建，影刺构造器私有；
 *               2内置静态方法，返回对象；
 *               3为便于自定义统一结果的信息，建议使用链式编程，将返回对象设类本身，即return this;
 *               4响应数据由于为json格式，可定义为JsonObject或Map形式；
 * @date 2021/6/3017:26
 */
@Data
public class Result {
    private Boolean success;

    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap<>();

    // 构造器私有
    private Result(){}

    // 通用返回成功
    public static Result ok() {
        Result r = new Result();
        r.setSuccess(ResultCodeEnum.SUCCESS.getSuccess());
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return r;
    }

    // 通用返回失败，未知错误
    public static Result error() {
        Result r = new Result();
        r.setSuccess(ResultCodeEnum.UNKNOWN_ERROR.getSuccess());
        r.setCode(ResultCodeEnum.UNKNOWN_ERROR.getCode());
        r.setMessage(ResultCodeEnum.UNKNOWN_ERROR.getMessage());
        return r;
    }

    // 设置结果，形参为结果枚举
    public static Result setResult(ResultCodeEnum result) {
        Result r = new Result();
        r.setSuccess(result.getSuccess());
        r.setCode(result.getCode());
        r.setMessage(result.getMessage());
        return r;
    }

    /**------------使用链式编程，返回类本身-----------**/

    // 自定义返回数据
    public Result data(Map<String,Object> map) {
        this.setData(map);
        return this;
    }

    // 通用设置data
    public Result data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    // 自定义状态信息
    public Result message(String message) {
        this.setMessage(message);
        return this;
    }

    // 自定义状态码
    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }

    // 自定义返回结果
    public Result success(Boolean success) {
        this.setSuccess(success);
        return this;
    }


    //===========================================================================================
    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
