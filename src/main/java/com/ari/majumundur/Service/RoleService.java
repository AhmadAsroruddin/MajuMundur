package com.ari.majumundur.Service;

import com.ari.majumundur.Constant.ERole;
import com.ari.majumundur.Models.Entities.Role;

public interface RoleService {

    Role getOrSave(ERole role);

}
