package dw.gameshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class ResourceNotFoundException extends RuntimeException {
        private static final long serialVarsionUID = 1L; //선택사항
        private String resourceName;
        private String fieldName;
        private Object fielValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Object fielValue) {
        super(String.format("%s not found with %s : %s",
                resourceName, fieldName,fielValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fielValue = fielValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFielValue() {
        return fielValue;
    }
}
