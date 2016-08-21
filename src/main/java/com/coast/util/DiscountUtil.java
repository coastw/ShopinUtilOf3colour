package com.coast.util;

import com.coast.exception.CanNotEditException;
import com.coast.model.Discount;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.log4j.Logger;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Coast
 */
public class DiscountUtil {
    
    private final Logger logger = Logger.getLogger(DiscountUtil.class);
    
    private final String FILE_PATH = System.getProperty("user.dir") + File.separator + "discount.xml";

    public Object[][] getDataArrayFromList(List<Discount> list) {
        int listSize = list.size();
        Object[][] data = new Object[listSize][];
        for (int i = 0; i < listSize; i++) {
            Discount discount = list.get(i);
            String discribe = discount.getDiscribe();
            String percent = discount.getPercent();
            String regex = discount.getRegex();
            data[i] = new Object[3];
            data[i][0] = discribe;
            data[i][1] = percent;
            data[i][2] = regex;
        }
        return data;
    }

    public List<Discount> getListFromDataArray(Object[][] data) {
        List<Discount> discunts = new ArrayList<>();
        for (Object[] row : data) {
            Discount discount = new Discount(row[0].toString(), row[1].toString(), row[2].toString());
            discunts.add(discount);
        }
        return discunts;
    }

    public String getPercent(String snCode, List<Discount> discounts) {
        String percent = "";
        for (Discount discount : discounts) {
            if (snCode.matches(discount.getRegex())) {
                return discount.getPercent();
            }
        }
        return percent;
    }

    //using apache configuration
    public List<Discount> read(){
        List<Discount> discounts = new ArrayList<>();
        Configurations configs = new Configurations();
        try {
            //encoding
            FileBasedConfigurationBuilder.setDefaultEncoding(XMLConfiguration.class, "UTF-8");

            //build
            FileBasedConfigurationBuilder<XMLConfiguration> builder = configs.xmlBuilder(FILE_PATH);
            XMLConfiguration config = builder.getConfiguration();
            
            //read
            org.w3c.dom.Document document = config.getDocument();
            NodeList nodeList = document.getElementsByTagName("discount");
            int len = nodeList.getLength();
            for (int i = 0; i < len; i++) {
                Node node = nodeList.item(i);
                NamedNodeMap attributes = node.getAttributes();
                Node discribeAttribute = attributes.item(0);
                String discribe = discribeAttribute.getTextContent();
                Node percentAttribute = attributes.item(1);
                String percent = percentAttribute.getTextContent();
                Node regexAttribute = attributes.item(2);
                String regex = regexAttribute.getTextContent();
                discounts.add(new Discount(discribe, percent, regex));
            }
        }catch(Exception e){
            logger.error(e.toString());
        }
        return discounts;
    }
    
    public void save(int rowIndex, int columnIndex, Object data) {
        Configurations configs = new Configurations();
        try {
            //encoding
            FileBasedConfigurationBuilder.setDefaultEncoding(XMLConfiguration.class, "UTF-8");

            //build
            FileBasedConfigurationBuilder<XMLConfiguration> builder = configs.xmlBuilder(FILE_PATH);
            XMLConfiguration config = builder.getConfiguration();

            //debug
            String discribe = config.getString("discount(" + rowIndex + ")[@discribe]");
            String percent = config.getString("discount(" + rowIndex + ")[@percent]");
            String regex = config.getString("discount(" + rowIndex + ")[@regex]");
            System.out.printf("Before:[discribe=%S, percent=%s , regex=%s]\n", discribe, percent, regex);
            
            //access
            switch (columnIndex) {
                case 1:
                    config.setProperty("discount(" + rowIndex + ")[@percent]", data);
                    break;
                case 2:
                    config.setProperty("discount(" + rowIndex + ")[@regex]", data);
                    break;
                default:
                    throw new CanNotEditException();
            }
            
            //debug
            String AfterPercent = config.getString("discount(" + rowIndex + ")[@percent]");
            String AfterRegex = config.getString("discount(" + rowIndex + ")[@regex]");
            System.out.printf("After:[discribe=%S, percent=%s , regex=%s]\n", discribe, AfterPercent, AfterRegex);
            
            //save
            builder.save();
        } catch (Exception e) {
            logger.error(e.toString());
        }

    }
}
