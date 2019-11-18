package com.rhonin.myssm.service;

import com.rhonin.myssm.mapper.PropertyMapper;
import com.rhonin.myssm.pojo.Property;
import com.rhonin.myssm.pojo.PropertyExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    PropertyMapper propertyMapper;

    @Override
    public List<Property> list(int id) {
        PropertyExample propertyExample = new PropertyExample();
        propertyExample.createCriteria().andCategory_idEqualTo(id);
        return propertyMapper.selectByExample(propertyExample);
    }

    @Override
    public int add(Property property) {
        return propertyMapper.insert(property);
    }

    @Override
    public void delete(int id) {
        propertyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByCid(int id) {
        propertyMapper.deleteByCid(id);
    }

    @Override
    public Property findById(int id) {
        return propertyMapper.selectByPrimaryKey(id);

    }

    @Override
    public void update(Property property) {
        propertyMapper.updateByPrimaryKey(property);
    }
}
