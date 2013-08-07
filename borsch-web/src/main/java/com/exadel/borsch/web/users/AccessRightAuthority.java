package com.exadel.borsch.web.users;

import com.exadel.borsch.entiry.AccessRight;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author Andrey Zhilka
 */
public final class AccessRightAuthority {
   private AccessRightAuthority() {

   }

   public static GrantedAuthority convert(final AccessRight right) {
       return new GrantedAuthority() {
           @Override
           public String getAuthority() {
               return right.toString();
           }
       };
   }
}
