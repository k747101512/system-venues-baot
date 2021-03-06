package com.cm.venuebooking.controller.app.apis.venuesproject;

import com.cm.common.annotation.CheckRequestBodyAnnotation;
import com.cm.common.base.AbstractController;
import com.cm.common.constants.ISystemConstant;
import com.cm.common.exception.RemoveException;
import com.cm.common.exception.SearchException;
import com.cm.common.pojo.ListPage;
import com.cm.common.result.ErrorResult;
import com.cm.common.result.SuccessResult;
import com.cm.common.result.SuccessResultList;
import com.cm.venuebooking.pojo.dtos.venuesproject.VenuesProjectDTO;
import com.cm.venuebooking.pojo.vos.venuesproject.VenuesProjectVO;
import com.cm.venuebooking.service.venuesproject.IVenuesProjectService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: VenuesProjectAppController
 * @Description: 场馆项目表
 * @Author: WenG
 * @Date: 2020-04-26 12:07
 * @Version: 1.0
 **/
@Api(tags = ISystemConstant.API_TAGS_APP_PREFIX + "场馆项目表接口")
@RestController
@RequestMapping(ISystemConstant.APP_PREFIX + "/venuesproject")
public class VenuesProjectAppController extends AbstractController {

    @Autowired
    private IVenuesProjectService venuesProjectService;

    @ApiOperation(value = "新增场馆项目表", notes = "新增场馆项目表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "header")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @PostMapping("savevenuesproject")
    @CheckRequestBodyAnnotation
    public SuccessResult saveVenuesProject(@RequestHeader("token") String token, @RequestBody VenuesProjectVO venuesProjectVO) throws Exception {
        return venuesProjectService.saveVenuesProjectByToken(token, venuesProjectVO);
    }

    @ApiOperation(value = "删除场馆项目表(id列表)", notes = "删除场馆项目表(id列表)接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "header"),
            @ApiImplicitParam(name = "ids", value = "ID列表，用下划线分隔", paramType = "path", example = "1_2_3")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @DeleteMapping("removevenuesproject/{ids}")
    public SuccessResult removeVenuesProject(@RequestHeader("token") String token, @PathVariable("ids") String ids) throws RemoveException {
        return venuesProjectService.removeVenuesProjectByToken(token, ids);
    }

    @ApiOperation(value = "修改场馆项目表", notes = "修改场馆项目表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "header"),
            @ApiImplicitParam(name = "venuesProjectId", value = "场馆项目表ID", paramType = "path")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @PutMapping("updatevenuesproject/{venuesProjectId}")
    @CheckRequestBodyAnnotation
    public SuccessResult updateVenuesProject(@RequestHeader("token") String token, @PathVariable("venuesProjectId") String venuesProjectId, @RequestBody VenuesProjectVO venuesProjectVO) throws Exception {
        return venuesProjectService.updateVenuesProjectByToken(token, venuesProjectId, venuesProjectVO);
    }

    @ApiOperation(value = "场馆项目表详情(通过ID)", notes = "场馆项目表详情(通过ID)接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "header"),
            @ApiImplicitParam(name = "venuesProjectId", value = "场馆项目表ID", paramType = "path")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @GetMapping("getvenuesprojectbyid/{venuesProjectId}")
    public VenuesProjectDTO getVenuesProjectById(@RequestHeader("token") String token, @PathVariable("venuesProjectId") String venuesProjectId) throws SearchException {
        return venuesProjectService.getVenuesProjectById(venuesProjectId);
    }

    @ApiOperation(value = "场馆项目表列表", notes = "场馆项目表列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "header")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @GetMapping("listvenuesproject")
    public List<VenuesProjectDTO> listVenuesProject(@RequestHeader("token") String token) throws SearchException {
        Map<String, Object> params = requestParams();
        return venuesProjectService.listVenuesProject(params);
    }

    @ApiOperation(value = "场馆项目表分页列表", notes = "场馆项目表分页列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", paramType = "header"),
            @ApiImplicitParam(name = "page", value = "当前页码", paramType = "form", dataType = "Integer", defaultValue = "1"),
            @ApiImplicitParam(name = "rows", value = "显示数量", paramType = "form", dataType = "Integer", defaultValue = "20"),
            @ApiImplicitParam(name = "keywords", value = "关键字", paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", paramType = "form", dataType = "String")
    })
    @ApiResponses({@ApiResponse(code = 400, message = "请求失败", response = ErrorResult.class)})
    @GetMapping("listpagevenuesproject")
    public SuccessResultList<List<VenuesProjectDTO>> listPageVenuesProject(@RequestHeader("token") String token, ListPage page) throws SearchException {
        Map<String, Object> params = requestParams();
        page.setParams(params);
        return venuesProjectService.listPageVenuesProject(page);
    }

}