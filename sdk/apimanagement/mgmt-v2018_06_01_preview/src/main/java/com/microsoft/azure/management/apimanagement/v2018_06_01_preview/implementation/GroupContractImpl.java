/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 *
 */

package com.microsoft.azure.management.apimanagement.v2018_06_01_preview.implementation;

import com.microsoft.azure.management.apimanagement.v2018_06_01_preview.GroupContract;
import com.microsoft.azure.arm.model.implementation.CreatableUpdatableImpl;
import rx.Observable;
import com.microsoft.azure.management.apimanagement.v2018_06_01_preview.GroupCreateParameters;
import com.microsoft.azure.management.apimanagement.v2018_06_01_preview.GroupType;
import rx.functions.Func1;

class GroupContractImpl extends CreatableUpdatableImpl<GroupContract, GroupContractInner, GroupContractImpl> implements GroupContract, GroupContract.Definition, GroupContract.Update {
    private String resourceGroupName;
    private String serviceName;
    private String groupId;
    private String cifMatch;
    private String uifMatch;
    private GroupCreateParameters createOrUpdateParameter;
    private final ApiManagementManager manager;

    GroupContractImpl(String name, ApiManagementManager manager) {
        super(name, new GroupContractInner());
        this.manager = manager;
        // Set resource name
        this.groupId = name;
        //
        this.createOrUpdateParameter = new GroupCreateParameters();
    }

    GroupContractImpl(GroupContractInner inner, ApiManagementManager manager) {
        super(inner.name(), inner);
        this.manager = manager;
        // Set resource name
        this.groupId = inner.name();
        // set resource ancestor and positional variables
        this.resourceGroupName = IdParsingUtils.getValueFromIdByName(inner.id(), "resourceGroups");
        this.serviceName = IdParsingUtils.getValueFromIdByName(inner.id(), "service");
        this.groupId = IdParsingUtils.getValueFromIdByName(inner.id(), "groups");
        // set other parameters for create and update
        this.createOrUpdateParameter = new GroupCreateParameters();
    }

    @Override
    public ApiManagementManager manager() {
        return this.manager;
    }

    @Override
    public Observable<GroupContract> createResourceAsync() {
        GroupsInner client = this.manager().inner().groups();
        return client.createOrUpdateAsync(this.resourceGroupName, this.serviceName, this.groupId, this.createOrUpdateParameter, this.cifMatch)
            .map(new Func1<GroupContractInner, GroupContractInner>() {
               @Override
               public GroupContractInner call(GroupContractInner resource) {
                   resetCreateUpdateParameters();
                   return resource;
               }
            })
            .map(innerToFluentMap(this));
    }

    @Override
    public Observable<GroupContract> updateResourceAsync() {
        GroupsInner client = this.manager().inner().groups();
        return client.createOrUpdateAsync(this.resourceGroupName, this.serviceName, this.groupId, this.createOrUpdateParameter, this.uifMatch)
            .map(new Func1<GroupContractInner, GroupContractInner>() {
               @Override
               public GroupContractInner call(GroupContractInner resource) {
                   resetCreateUpdateParameters();
                   return resource;
               }
            })
            .map(innerToFluentMap(this));
    }

    @Override
    protected Observable<GroupContractInner> getInnerAsync() {
        GroupsInner client = this.manager().inner().groups();
        return null; // NOP getInnerAsync implementation as get is not supported
    }

    @Override
    public boolean isInCreateMode() {
        return this.inner().id() == null;
    }

    private void resetCreateUpdateParameters() {
        this.createOrUpdateParameter = new GroupCreateParameters();
    }

    @Override
    public Boolean builtIn() {
        return this.inner().builtIn();
    }

    @Override
    public String description() {
        return this.inner().description();
    }

    @Override
    public String displayName() {
        return this.inner().displayName();
    }

    @Override
    public String externalId() {
        return this.inner().externalId();
    }

    @Override
    public GroupType groupContractType() {
        return this.inner().groupContractType();
    }

    @Override
    public String id() {
        return this.inner().id();
    }

    @Override
    public String name() {
        return this.inner().name();
    }

    @Override
    public String type() {
        return this.inner().type();
    }

    @Override
    public GroupContractImpl withResourceGroupName(String resourceGroupName) {
        this.resourceGroupName = resourceGroupName;
        return this;
    }

    @Override
    public GroupContractImpl withServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    @Override
    public GroupContractImpl withDisplayName(String displayName) {
        this.createOrUpdateParameter.withDisplayName(displayName);
        return this;
    }

    @Override
    public GroupContractImpl withIfMatch(String ifMatch) {
        if (isInCreateMode()) {
            this.cifMatch = ifMatch;
        } else {
            this.uifMatch = ifMatch;
        }
        return this;
    }

    @Override
    public GroupContractImpl withDescription(String description) {
        this.createOrUpdateParameter.withDescription(description);
        return this;
    }

    @Override
    public GroupContractImpl withExternalId(String externalId) {
        this.createOrUpdateParameter.withExternalId(externalId);
        return this;
    }

    @Override
    public GroupContractImpl withType(GroupType type) {
        this.createOrUpdateParameter.withType(type);
        return this;
    }

}
