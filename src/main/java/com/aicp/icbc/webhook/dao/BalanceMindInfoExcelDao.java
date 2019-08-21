package com.aicp.icbc.webhook.dao;

import com.aicp.icbc.webhook.dto.BalanceMindInfoDto;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 收不到余额变动提醒流程-ExcelDAO
 * @Author: 吴开云
 * @Date: 2019/8/19 0019
 * @Version： 1.0
 */
@Slf4j
@Component
public class BalanceMindInfoExcelDao {
    /**
     * 查询Excel文档中的全部信息
     * @return
     */
    public List<BalanceMindInfoDto> getAllInfoList() {
        List<BalanceMindInfoDto> infoDtoList = new ArrayList<>();
        //访问的classes-路径下的，Excel文件名
        String fileName = "ICBC-DATA.xlsx";
        //调用easyexcel 访问数据
        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);) {
            AnalysisEventListener<BalanceMindInfoDto> listener = new AnalysisEventListener<BalanceMindInfoDto>() {

                //访问，每一行数据
                @Override
                public void invoke(BalanceMindInfoDto object, AnalysisContext context) {
//                    System.err.println("Row:" + context.getCurrentRowNum() + "  Data:" + object);
                    infoDtoList.add(object);
                }
                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    log.info(fileName + "数据读取完毕..." + "共读取：" + infoDtoList.size() + "条数据");
                }
            };
            if(fileName.indexOf(".xlsx") > 0){
                //读取xlsx后缀的Excel内容
                ExcelReader excelReader = new ExcelReader(in, ExcelTypeEnum.XLSX, null, listener);
                // 第一个参数表示sheet页（第几页），第二个参数为表头行数，按照实际设置
                excelReader.read(new Sheet(6, 2, BalanceMindInfoDto.class));
            }else{
                //读取xls后缀的Excel内容
                ExcelReader excelReader = new ExcelReader(in, ExcelTypeEnum.XLS, null, listener);
                // 第一个参数表示sheet页（第几页），第二个参数为表头行数，按照实际设置
                excelReader.read(new Sheet(6, 2, BalanceMindInfoDto.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("找不到 " + fileName);
        }

        return infoDtoList;
    }
}
