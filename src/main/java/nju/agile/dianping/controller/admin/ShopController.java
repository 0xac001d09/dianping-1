package nju.agile.dianping.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import nju.agile.dianping.common.AdminPermission;
import nju.agile.dianping.common.BusinessException;
import nju.agile.dianping.common.CommonUtil;
import nju.agile.dianping.common.EmBusinessError;
import nju.agile.dianping.model.CategoryModel;
import nju.agile.dianping.model.ShopModel;
import nju.agile.dianping.request.CategoryCreateReq;
import nju.agile.dianping.request.PageQuery;
import nju.agile.dianping.request.ShopCreateReq;
import nju.agile.dianping.service.CategoryService;
import nju.agile.dianping.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: TODO
 */
@Controller("/admin/shop")
@RequestMapping("/admin/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

//    // 门店列表
//    @RequestMapping("/index")
//    @AdminPermission
//    public ModelAndView index(PageQuery pageQuery) {
//        // 分页查询操作，会自动在sql后添加limit，数值为配置的参数
//        PageHelper.startPage(pageQuery.getPage(), pageQuery.getSize());
//        List<ShopModel> shopModelList  = shopService.selectAll();
//        // pageinfo将页数信息和list封装在一起返回给前端
//        PageInfo<ShopModel> shopModelPageInfo = new PageInfo<>(shopModelList);
//        ModelAndView modelAndView = new ModelAndView("/admin/shop/index.html");
//        modelAndView.addObject("data", shopModelPageInfo);
//        modelAndView.addObject("CONTROLLER_NAME", "shop");
//        modelAndView.addObject("ACTION_NAME", "index");
//        return modelAndView;
//    }

    @RequestMapping("/index")
    @AdminPermission
    public ModelAndView index(PageQuery pageQuery) {

        //使用pagehelper插件
        PageHelper.startPage(pageQuery.getPage(), pageQuery.getSize());
        //获取数据库内的门店
        List<ShopModel> shopModelList = shopService.selectAll();
        PageInfo<ShopModel> shopModelPageInfo = new PageInfo<>(shopModelList);

        ModelAndView modelAndView = new ModelAndView("admin/shop/index.html");
        modelAndView.addObject("data", shopModelPageInfo);
        modelAndView.addObject("CONTROLLER_NAME", "shop");
        modelAndView.addObject("ACTION_NAME", "index");
        return modelAndView;
    }



    @RequestMapping("createpage")
    @AdminPermission
    public ModelAndView createPage() {
        List<ShopModel> shopModelList = shopService.selectAll();
        ModelAndView modelAndView = new ModelAndView("admin/shop/create.html");

        modelAndView.addObject("data", shopModelList);
        modelAndView.addObject("CONTROLLER_NAME", "shop");
        modelAndView.addObject("ACTION_NAME", "create");
        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @AdminPermission
    public String create(@Valid ShopCreateReq shopCreateReq, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }

        ShopModel shopModel = new ShopModel();
        shopModel.setIconUrl(shopCreateReq.getIconUrl());
        shopModel.setAddress(shopCreateReq.getAddress());
        shopModel.setCategoryId(shopCreateReq.getCategoryId());
        shopModel.setEndTime(shopCreateReq.getEndTime());
        shopModel.setStartTime(shopCreateReq.getStartTime());
        shopModel.setLongtitude(shopCreateReq.getLongitude());
        shopModel.setLatitude(shopCreateReq.getLatitude());
        shopModel.setName(shopCreateReq.getName());
        shopModel.setPricePerMan(shopCreateReq.getPricePerMan());
        shopModel.setSellerId(shopCreateReq.getSellerId());
        shopModel.setTags(shopCreateReq.getTags());
        shopModel.setRemarkScore(new BigDecimal(0));

        shopService.create(shopModel);


        return "redirect:/admin/shop/index";
    }

}
