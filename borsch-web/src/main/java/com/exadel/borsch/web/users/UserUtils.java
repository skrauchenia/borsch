package com.exadel.borsch.web.users;

import com.exadel.borsch.entiry.AccessRight;
import com.exadel.borsch.entiry.User;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author zubr
 */
public final class UserUtils {

    private static final Logger LOGGER = Logger.getLogger(UserUtils.class.getName());

    private UserUtils() {
    }

    public static User getUserByPrincipal(Principal principal) {
        if (principal instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
            if (token.getPrincipal() instanceof BorschUserDetails) {
                return ((BorschUserDetails) token.getPrincipal()).getUser();
            }
        }

        LOGGER.log(Level.SEVERE, "The Principal is not BorschUser");
        return null;
    }

    public static void hasRole(Principal principal, AccessRight accessRight) throws AccessDeniedException {
        User user = getUserByPrincipal(principal);
        if (user != null) {
            if (!user.hasAccessRight(accessRight)) {
                throw new AccessDeniedException("Access is denied.");
            }
        } else {
            throw new AccessDeniedException("Access is denied.");
        }
    }

    public static void checkEditor(Principal editor, Long editableUserId) {
        User userEditor = getUserByPrincipal(editor);
        if (userEditor != null) {
            if (!userEditor.getId().equals(editableUserId)) {
                hasRole(editor, AccessRight.ROLE_EDIT_PROFILE);
            }
        } else {
            throw new AccessDeniedException("Access is denied.");
        }
    }
}
