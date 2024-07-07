package org.viethm.json;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface JSONRootKeyMapper {
    String key();
}
