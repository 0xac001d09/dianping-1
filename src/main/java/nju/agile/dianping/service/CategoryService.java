package nju.agile.dianping.service;

import nju.agile.dianping.common.BusinessException;
import nju.agile.dianping.model.CategoryModel;

import java.util.List;

/**
 * @Description: TODO
 */
public interface CategoryService {
    CategoryModel create(CategoryModel categoryModel) throws BusinessException;

    CategoryModel get(Integer id);

    List<CategoryModel> selectAll();

    Integer countAllCategory();
}
