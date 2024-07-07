package org.viethm.json;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface JSONKeyWrapperMapper {
    String key();
}
