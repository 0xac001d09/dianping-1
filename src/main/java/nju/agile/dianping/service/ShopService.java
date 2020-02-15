package nju.agile.dianping.service;

import nju.agile.dianping.common.BusinessException;
import nju.agile.dianping.model.ShopModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 */
public interface ShopService {
    ShopModel create(ShopModel shopModel) throws BusinessException;

    ShopModel get(Integer id);

    List<ShopModel> selectAll();

    List<ShopModel> recommond(BigDecimal longitude, BigDecimal latitude);

    Integer countAllShop();

    List<ShopModel> search(BigDecimal longitude, BigDecimal latitude, String keyword,
                           Integer orderby, Integer categoryId, String tags);

    List<Map<String, Object>> searchGroupByTags(String keyword, Integer categoryId, String tags);

}
