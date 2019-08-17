package com.aicp.icbc.webhook.dao;

import com.aicp.icbc.webhook.dto.UserInfoDto;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: 吴开云
 * @Date: 2019/8/16 0016
 * @Version： 1.0
 */
@Slf4j
@Component
public class UserInfoExcelDao {

    /**
     * 查询Excel文档中的全部userInfo信息
     * @return
     */
    public List<UserInfoDto> getAllUserInfoList() {
        List<UserInfoDto> userInfoDtoList = new ArrayList<>();
        //访问的classes-路径下的，Excel文件名
        String fileName = "核身流程-身份信息.xlsx";
        //调用easyexcel 访问数据
        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);) {
            AnalysisEventListener<UserInfoDto> listener = new AnalysisEventListener<UserInfoDto>() {

                //访问，每一行数据
                @Override
                public void invoke(UserInfoDto object, AnalysisContext context) {
                    System.err.println("Row:" + context.getCurrentRowNum() + "  Data:" + object);
                    userInfoDtoList.add(object);
                }
                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    System.err.println("doAfterAllAnalysed...");
                }
            };
            if(fileName.indexOf(".xlsx") > 0){
                //读取xlsx后缀的Excel内容
                ExcelReader excelReader = new ExcelReader(in, ExcelTypeEnum.XLSX, null, listener);
                // 第二个参数为表头行数，按照实际设置
                excelReader.read(new Sheet(1, 1, UserInfoDto.class));
            }else{
                //读取xls后缀的Excel内容
                ExcelReader excelReader = new ExcelReader(in, ExcelTypeEnum.XLS, null, listener);
                // 第二个参数为表头行数，按照实际设置
                excelReader.read(new Sheet(1, 1, UserInfoDto.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("找不到 核身流程-身份信息.xlsx");
        }

        return userInfoDtoList;
    }
}
