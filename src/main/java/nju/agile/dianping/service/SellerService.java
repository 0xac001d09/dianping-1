package nju.agile.dianping.service;

import nju.agile.dianping.common.BusinessException;
import nju.agile.dianping.model.SellerModel;

import java.util.List;

/**
 * @Description: TODO
 */
public interface SellerService {
    SellerModel create(SellerModel sellerModel);

    SellerModel get(Integer id);

    List<SellerModel> selectAll();

    SellerModel changeStatus(Integer id, Integer disableFlse) throws BusinessException;

    Integer countAllSeller();

}
