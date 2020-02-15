package nju.agile.dianping.common;

/**
 * @Description: TODO
 */
public class CommonRes {
    // 表明经请求的返回处理结果， success 或 fail
    private String status;

    // status = success,表为对应的返回的json类数据
    // status = fail,data内将使用通用的错误码对应的格式
    private Object data;

    // 通用的创建返回对象的方法
    public static CommonRes create(Object result){
        return CommonRes.create(result, "success");
    }

    public static CommonRes create(Object result, String status){
        CommonRes commonRes = new CommonRes();
        commonRes.setStatus(status);
        commonRes.setData(result);
        return commonRes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
