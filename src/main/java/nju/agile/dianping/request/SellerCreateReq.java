package nju.agile.dianping.request;

import javax.validation.constraints.NotBlank;

/**
 * @Description: TODO
 */
public class SellerCreateReq {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank(message = "商户名不能为空")
    private String name;
}
