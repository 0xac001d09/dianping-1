package nju.agile.dianping.service.impl;

import nju.agile.dianping.common.BusinessException;
import nju.agile.dianping.common.EmBusinessError;
import nju.agile.dianping.dal.CategoryModelMapper;
import nju.agile.dianping.model.CategoryModel;
import nju.agile.dianping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Description: TODO
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryModelMapper categoryModelMapper;

    @Override
    @Transactional
    public CategoryModel create(CategoryModel categoryModel) throws BusinessException {
        categoryModel.setCreatedAt(new Date());
        categoryModel.setUpdatedAt(new Date());
        try {
        } catch (DuplicateKeyException ex) {
            throw new BusinessException(EmBusinessError.CATEGORY_NAME_DUPLICATED);
        }
        categoryModelMapper.insert(categoryModel);
        return get(categoryModel.getId());
    }

    @Override
    public CategoryModel get(Integer id) {
        return categoryModelMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CategoryModel> selectAll() {
        return categoryModelMapper.selectAll();
    }

    @Override
    public Integer countAllCategory() {
        return categoryModelMapper.countAllCategory();
    }
}
