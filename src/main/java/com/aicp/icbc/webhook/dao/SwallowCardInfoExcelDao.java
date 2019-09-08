package com.aicp.icbc.webhook.dao;

import com.aicp.icbc.webhook.dto.SwallowCardInfoDto;
import com.aicp.icbc.webhook.utils.FilterSetterUtil;
import com.aicp.icbc.webhook.utils.RequestUtils;
import com.aicp.icbc.webhook.xmlVO.SwallowCardInfoVo;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: 吴开云
 * @Date: 2019/8/19 0019
 * @Version： 1.0
 */
@Slf4j
@Component
public class SwallowCardInfoExcelDao {
    /**
     * 查询Excel文档中的全部信息
     * @return
     */
    public List<SwallowCardInfoDto> getAllInfoList() {
        List<SwallowCardInfoDto> infoDtoList = new ArrayList<>();
        //访问的classes-路径下的，Excel文件名
        String fileName = "ICBC-DATA.xlsx";
        //调用easyexcel 访问数据
        InputStream in = null;
        try {

            //获取输入流
            in = this.getClass().getClassLoader().getResourceAsStream(fileName);

            AnalysisEventListener<SwallowCardInfoDto> listener = new AnalysisEventListener<SwallowCardInfoDto>() {

                //访问，每一行数据
                @Override
                public void invoke(SwallowCardInfoDto object, AnalysisContext context) {
                    // System.err.println("Row:" + context.getCurrentRowNum() + "  Data:" + object);
                    //给卡号后四位赋值 -- 卡号前六位赋值
                    String cardNum = object.getCardNumber();
                    if (!StringUtils.isEmpty(cardNum)){
                        object.setCardNumberFour(cardNum.substring((cardNum.length() - 4), cardNum.length()));
                        object.setCardNumberPreSix(cardNum.substring(0,6));
                    }
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
                excelReader.read(new Sheet(3, 2, SwallowCardInfoDto.class));
            }else{
                //读取xls后缀的Excel内容
                ExcelReader excelReader = new ExcelReader(in, ExcelTypeEnum.XLS, null, listener);
                // 第一个参数表示sheet页（第几页），第二个参数为表头行数，按照实际设置
                excelReader.read(new Sheet(3, 2, SwallowCardInfoDto.class));
            }
        }   finally {
            try {
                //关闭输入流
                if(in != null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                log.error("找不到 " + fileName);
            }
        }

        return infoDtoList;
    }

    /**
     * 访问行方接口，获取数据
     * @return
     */
    public SwallowCardInfoDto getFromBank(String cardNo){
        String from = "S_SWALLOW_CARD";
        String result = RequestUtils.loadXmlStrFromBank(from, cardNo);

        Document document = null;
        try {
            document = DocumentHelper.parseText(result);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        //获取顶层节点 -- 行方默认为CC
        Element element = document.getRootElement();
        //获取目标子节点
        Element RC = element.element("OUT").element("RESULT").element("RC");
        List<Element> rcList = RC.elements();
        //对VO实体进行赋值
        SwallowCardInfoVo vo = new SwallowCardInfoVo();
        FilterSetterUtil<SwallowCardInfoVo> filterSetterUtil = new FilterSetterUtil<>();
        //循环遍历xml节点
        for (Element perElement:rcList) {
            String nodeName = perElement.getName();
            String nodeStr = (String) perElement.getData();
            filterSetterUtil.seDtoValue(nodeName, nodeStr,vo);
        }
        //对DTO实体进行赋值
        SwallowCardInfoDto dto = new SwallowCardInfoDto();
        dto.setUserName(vo.getNAME());
        dto.setCardNumber(vo.getCARDNO());
        dto.setBank(vo.getEXTREMELY_LOCATION());
        dto.setEatLocation(vo.getEXTREMELY_LOCATION());
        dto.setEatTime(vo.getSWALLOW_TIME());
        dto.setMachineNum(vo.getEXTREMELYNO());
        dto.setPhoneNumber(vo.getCONTACT_NUMBER());
        dto.setIdCardNumber(vo.getIDNUMBER());
        dto.setCardNumberFour(vo.getCARDNO().substring((vo.getCARDNO().length() - 4), vo.getCARDNO().length()));
        dto.setCardNumberPreSix(vo.getCARDNO().substring(0,6));
        return dto;
    }
}
