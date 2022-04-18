package result;

/**
 * @author zby
 * @title: CMSException
 * @projectName zj-shop
 * @description: 自定义全局异常类
 * @date 2021/7/18:50
 */
public class ZJException extends RuntimeException {

    private Integer code;

    public ZJException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ZJException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "CMSException{" + "code=" + code + ", message=" + this.getMessage() + '}';
    }




}
