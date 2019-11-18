package com.rhonin.myssm.service;

import com.rhonin.myssm.mapper.PropertyValueMapper;
import com.rhonin.myssm.pojo.PropertyValue;
import com.rhonin.myssm.pojo.PropertyValueExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyValueServiceImpl implements PropertyValueService {
    @Autowired
    PropertyValueMapper propertyValueMapper;

    @Override
    public List<PropertyValue> list(int id) {
        PropertyValueExample propertyValueExample = new PropertyValueExample();
        propertyValueExample.createCriteria().andProduct_idEqualTo(id);
        return propertyValueMapper.selectByExample(propertyValueExample);
    }

    @Override
    public int update(PropertyValue propertyValue) {
        return propertyValueMapper.updateByPrimaryKeySelective(propertyValue);
    }

    @Override
    public void deletebyPropertyId(int id) {
        propertyValueMapper.deleteByPropertyId(id);
    }

    @Override
    public void deleteByProductId(int id) {
        propertyValueMapper.deleteByProductId(id);
    }

    @Override
    public void add(PropertyValue propertyValue) {
        propertyValueMapper.insert(propertyValue);
    }
}
