package com.items.item_1.model;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Map;

public class PageJSON<T> implements Serializable {

    private static final long serialVersionUID = -2873394781227582425L;
    @Valid
    private T formJSON = null;
    private Map<String, String> requestParameterMap;
    private Map<String, Object> requestAttrMap;

    public PageJSON() {
    }

    public T getFormJSON() {
        return this.formJSON;
    }

    public Map<String, String> getRequestParameterMap() {
        return this.requestParameterMap;
    }

    public Map<String, Object> getRequestAttrMap() {
        return this.requestAttrMap;
    }

    public void setFormJSON(T formJSON) {
        this.formJSON = formJSON;
    }

    public void setRequestParameterMap(Map<String, String> requestParameterMap) {
        this.requestParameterMap = requestParameterMap;
    }

    public void setRequestAttrMap(Map<String, Object> requestAttrMap) {
        this.requestAttrMap = requestAttrMap;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PageJSON)) {
            return false;
        } else {
            PageJSON<?> other = (PageJSON)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$formJSON = this.getFormJSON();
                    Object other$formJSON = other.getFormJSON();
                    if (this$formJSON == null) {
                        if (other$formJSON == null) {
                            break label47;
                        }
                    } else if (this$formJSON.equals(other$formJSON)) {
                        break label47;
                    }

                    return false;
                }

                Object this$requestParameterMap = this.getRequestParameterMap();
                Object other$requestParameterMap = other.getRequestParameterMap();
                if (this$requestParameterMap == null) {
                    if (other$requestParameterMap != null) {
                        return false;
                    }
                } else if (!this$requestParameterMap.equals(other$requestParameterMap)) {
                    return false;
                }

                Object this$requestAttrMap = this.getRequestAttrMap();
                Object other$requestAttrMap = other.getRequestAttrMap();
                if (this$requestAttrMap == null) {
                    if (other$requestAttrMap != null) {
                        return false;
                    }
                } else if (!this$requestAttrMap.equals(other$requestAttrMap)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof PageJSON;
    }

    @Override
    public int hashCode() {
        //int PRIME = true;
        int result = 1;
        Object $formJSON = this.getFormJSON();
        result = result * 59 + ($formJSON == null ? 43 : $formJSON.hashCode());
        Object $requestParameterMap = this.getRequestParameterMap();
        result = result * 59 + ($requestParameterMap == null ? 43 : $requestParameterMap.hashCode());
        Object $requestAttrMap = this.getRequestAttrMap();
        result = result * 59 + ($requestAttrMap == null ? 43 : $requestAttrMap.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "PageJSON(formJSON=" + this.getFormJSON() + ", requestParameterMap=" + this.getRequestParameterMap() + ", requestAttrMap=" + this.getRequestAttrMap() + ")";
    }
}
