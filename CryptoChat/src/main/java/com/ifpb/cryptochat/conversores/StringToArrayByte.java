package com.ifpb.cryptochat.conversores;

import java.nio.charset.StandardCharsets;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("convert.ArrayByte")
public class StringToArrayByte implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        return value.getBytes();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        return new String((byte[]) value, StandardCharsets.UTF_8);
    }
    
}
