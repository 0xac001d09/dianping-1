package nju.agile.dianping.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import nju.agile.dianping.common.*;
import nju.agile.dianping.model.SellerModel;
import nju.agile.dianping.request.PageQuery;
import nju.agile.dianping.request.SellerCreateReq;
import nju.agile.dianping.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.List;

/**
 * @Description: TODO
 */

@Controller("/admin/seller")
@RequestMapping("/admin/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    //     商户列表
    @RequestMapping("/index")
    @AdminPermission
    public ModelAndView index(PageQuery pageQuery) {

        // 分页查询操作，会自动在sql后添加limit，数值为配置的参数
        PageHelper.startPage(pageQuery.getPage(), pageQuery.getSize());
        List<SellerModel> sellerModelList = sellerService.selectAll();
        // pageinfo将页数信息和list封装在一起返回给前端
        PageInfo<SellerModel> sellerModelPageInfo = new PageInfo<>(sellerModelList);
        ModelAndView modelAndView = new ModelAndView("admin/seller/index.html");
        modelAndView.addObject("data", sellerModelPageInfo);
        modelAndView.addObject("CONTROLLER_NAME", "seller");
        modelAndView.addObject("ACTION_NAME", "index");
        return modelAndView;
    }

    @RequestMapping("createpage")
    @AdminPermission
    public ModelAndView createPage() {
        List<SellerModel> sellerModelList = sellerService.selectAll();
        ModelAndView modelAndView = new ModelAndView("admin/seller/create.html");

        modelAndView.addObject("data", sellerModelList);
        modelAndView.addObject("CONTROLLER_NAME", "seller");
        modelAndView.addObject("ACTION_NAME", "create");
        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @AdminPermission
    public String create(@Valid SellerCreateReq sellerCreateReq, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }

        SellerModel sellerModel = new SellerModel();
        sellerModel.setName(sellerCreateReq.getName());
        sellerService.create(sellerModel);

        return "redirect:/admin/seller/index";
    }

    @RequestMapping(value = "down", method = RequestMethod.POST)
    @AdminPermission
    @ResponseBody
    public CommonRes down(@RequestParam(value = "id") Integer id) throws BusinessException {
        SellerModel sellerModel = sellerService.changeStatus(id, 1);
        return CommonRes.create(sellerModel);
    }

    @RequestMapping(value = "up", method = RequestMethod.POST)
    @AdminPermission
    @ResponseBody
    public CommonRes up(@RequestParam(value = "id") Integer id) throws BusinessException {
        SellerModel sellerModel = sellerService.changeStatus(id, 0);
        return CommonRes.create(sellerModel);
    }


}
