package com.cm.venuebooking.controller.app.apis.venuesinfo;

import com.cm.common.annotation.CheckRequestBodyAnnotation;
import com.cm.common.base.AbstractController;
import com.cm.common.constants.ISystemConstant;
import com.cm.common.exception.RemoveException;
import com.cm.common.exception.SearchException;
import com.cm.common.pojo.ListPage;
import com.cm.common.result.ErrorResult;
import com.cm.common.result.SuccessResult;
import com.cm.common.result.SuccessResultList;
import com.cm.venuebooking.pojo.dtos.venuesinfo.VenuesInfoDTO;
import com.cm.venuebooking.pojo.vos.venuesinfo.VenuesInfoVO;
import com.cm.venuebooking.service.venuesinfo.IVenuesInfoService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: VenuesInfoAppController
 * @Description: 场馆信息表
 * @Author: WenG
 * @Date: 2020-04-22 11:46
 * @Version: 1.0
 **/
@Api(tags = ISystemConstant.API_TAGS_APP_PREFIX + "场馆信息表接口")
@RestController
@RequestMapping(ISystemConstant.APP_PREFIX + "/venuesinfo")
public class VenuesInfoAppController extends AbstractController {

    @Autowired
    private IVenuesInfoService venuesInfoService;

    @ApiOperation(value = "新增场馆信息表", notes = "新增场馆信息表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "header")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @PostMapping("savevenuesinfo")
    @CheckRequestBodyAnnotation
    public SuccessResult saveVenuesInfo(@RequestHeader("token") String token, @RequestBody VenuesInfoVO venuesInfoVO) throws Exception {
        return venuesInfoService.saveVenuesInfoByToken(token, venuesInfoVO);
    }

    @ApiOperation(value = "删除场馆信息表(id列表)", notes = "删除场馆信息表(id列表)接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "header"),
            @ApiImplicitParam(name = "ids", value = "ID列表，用下划线分隔", paramType = "path", example = "1_2_3")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @DeleteMapping("removevenuesinfo/{ids}")
    public SuccessResult removeVenuesInfo(@RequestHeader("token") String token, @PathVariable("ids") String ids) throws RemoveException {
        return venuesInfoService.removeVenuesInfoByToken(token, ids);
    }

    @ApiOperation(value = "修改场馆信息表", notes = "修改场馆信息表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "header"),
            @ApiImplicitParam(name = "venuesInfoId", value = "场馆信息表ID", paramType = "path")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @PutMapping("updatevenuesinfo/{venuesInfoId}")
    @CheckRequestBodyAnnotation
    public SuccessResult updateVenuesInfo(@RequestHeader("token") String token, @PathVariable("venuesInfoId") String venuesInfoId, @RequestBody VenuesInfoVO venuesInfoVO) throws Exception {
        return venuesInfoService.updateVenuesInfoByToken(token, venuesInfoId, venuesInfoVO);
    }

    @ApiOperation(value = "场馆信息表详情(通过ID)", notes = "场馆信息表详情(通过ID)接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "header"),
            @ApiImplicitParam(name = "venuesInfoId", value = "场馆信息表ID", paramType = "path")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @GetMapping("getvenuesinfobyid/{venuesInfoId}")
    public VenuesInfoDTO getVenuesInfoById(@RequestHeader("token") String token, @PathVariable("venuesInfoId") String venuesInfoId) throws SearchException {
        return venuesInfoService.getVenuesInfoById(venuesInfoId);
    }

    @ApiOperation(value = "场馆信息表列表", notes = "场馆信息表列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "header")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @GetMapping("listvenuesinfo")
    public List<VenuesInfoDTO> listVenuesInfo(@RequestHeader("token") String token) throws SearchException {
        Map<String, Object> params = requestParams();
        return venuesInfoService.listVenuesInfo(params);
    }

    @ApiOperation(value = "场馆信息表分页列表", notes = "场馆信息表分页列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "header"),
            @ApiImplicitParam(name = "page", value = "当前页码", paramType = "form", dataType = "Integer", defaultValue = "1"),
            @ApiImplicitParam(name = "rows", value = "显示数量", paramType = "form", dataType = "Integer", defaultValue = "20"),
            @ApiImplicitParam(name = "keywords", value = "关键字", paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", paramType = "form", dataType = "String")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @GetMapping("listpagevenuesinfo")
    public SuccessResultList<List<VenuesInfoDTO>> listPageVenuesInfo(@RequestHeader("token") String token, ListPage page) throws SearchException {
        Map<String, Object> params = requestParams();
        page.setParams(params);
        return venuesInfoService.listPageVenuesInfo(page);
    }

}