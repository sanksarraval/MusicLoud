package com.example.musicloud.objects.application;

import com.example.musicloud.persistence.UserManagement;
import com.example.musicloud.persistence.stubs.UserManagementStub;

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
