package nju.agile.dianping.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import nju.agile.dianping.common.AdminPermission;
import nju.agile.dianping.common.BusinessException;
import nju.agile.dianping.common.CommonUtil;
import nju.agile.dianping.common.EmBusinessError;
import nju.agile.dianping.model.CategoryModel;
import nju.agile.dianping.request.CategoryCreateReq;
import nju.agile.dianping.request.PageQuery;
import nju.agile.dianping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description: TODO
 */

@Controller("/admin/category")
@RequestMapping("/admin/category")
public class CategoryCroller {

    @Autowired
    private CategoryService categoryService;

    //     品类列表
    @RequestMapping("/index")
    @AdminPermission
    public ModelAndView index(PageQuery pageQuery) {

        // 分页查询操作，会自动在sql后添加limit，数值为配置的参数
        PageHelper.startPage(pageQuery.getPage(), pageQuery.getSize());
        List<CategoryModel> categoryModelList  = categoryService.selectAll();
        // pageinfo将页数信息和list封装在一起返回给前端
        PageInfo<CategoryModel> categoryModelPageInfo = new PageInfo<>(categoryModelList);
        ModelAndView modelAndView = new ModelAndView("admin/category/index.html");
        modelAndView.addObject("data", categoryModelPageInfo);
        modelAndView.addObject("CONTROLLER_NAME", "category");
        modelAndView.addObject("ACTION_NAME", "index");
        return modelAndView;
    }

    @RequestMapping("createpage")
    @AdminPermission
    public ModelAndView createPage() {
        List<CategoryModel> categoryModelList = categoryService.selectAll();
        ModelAndView modelAndView = new ModelAndView("admin/category/create.html");

        modelAndView.addObject("data", categoryModelList);
        modelAndView.addObject("CONTROLLER_NAME", "category");
        modelAndView.addObject("ACTION_NAME", "create");
        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @AdminPermission
    public String create(@Valid CategoryCreateReq categoryCreateReq, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }

        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setName(categoryCreateReq.getName());
        categoryModel.setIconUrl(categoryCreateReq.getIconUrl());
        categoryModel.setSort(categoryCreateReq.getSort());
        categoryService.create(categoryModel);

        return "redirect:/admin/category/index";
    }
}
