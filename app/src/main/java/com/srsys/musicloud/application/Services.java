package com.srsys.musicloud.application;

import com.srsys.musicloud.persistence.UserManagement;
import com.srsys.musicloud.persistence.stubs.UserManagementStub;

public class Services {

    private static UserManagement accountManagement = null;

    public static synchronized UserManagement getAccountManagement()
    {
        if (accountManagement == null)
        {
            accountManagement = new UserManagementStub();
        }
        return accountManagement;
    }

}
